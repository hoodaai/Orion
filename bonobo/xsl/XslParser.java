package mhc.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mhc.domain.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.akube.framework.util.BaseUtils;

public class XslParser {
  private static Logger logger = LoggerFactory.getLogger(XslParser.class);

  public static Pattern p = Pattern.compile("([0-9]*\\.?[0-9]*) ?%");


//  public static void main(String[] args) {
//    Matcher matcher = p.matcher("VAT - 3.125 %");
//    if (matcher.find()) {
//      System.out.println(matcher.group(1));
//    }
//  }

  private Set<Product> colProductList = null;

  private static final String PRODUCT_ID = "PRODUCT_ID";
  private static final String VARIANT_ID = "VARIANT_ID";
  private static final String CATEGORY = "CATEGORY";
  private static final String PRODUCT_NAME = "PRODUCT_NAME";
  private static final String BRAND = "BRAND";
  private static final String MANUFACTURER = "MANUFACTURER";
  private static final String MANUFACTURER_NAME = "MANUFACTURER_NAME";
  private static final String MANUFACTURER_WEBSITE = "MANUFACTURER_WEBSITE";
  private static final String MANUFACTURER_DESCRIPTION = "MANUFACTURER_DESCRIPTION";
  private static final String OVERVIEW = "OVERVIEW";
  private static final String DESCRIPTION = "DESCRIPTION";
  private static final String MRP = "MRP";
  private static final String PERCENTAGE_DISCOUNT = "PERCENTAGE_DISCOUNT";
  private static final String COST = "COST";
  private static final String SHIPPING = "SHIPPING";
  private static final String TAX = "TAX";
  private static final String OPTIONS = "OPTIONS";
  private static final String Image = "Image";
  private static final String PROCCESS_MIN_DAY = "PROCCESS_MIN_DAY";
  private static final String PROCCESS_MAX_DAY = "PROCCESS_MAX_DAY";

  public Set<Product> readProductList(File objInFile) throws Exception {

    POIFSFileSystem objInFileSys = new POIFSFileSystem(new FileInputStream(objInFile));

    HSSFWorkbook workbook = new HSSFWorkbook(objInFileSys);

    // Assuming there is only one sheet, the first one only will be picked
    HSSFSheet productSheet = workbook.getSheet("Product");
    HSSFSheet manufacturerSheet = workbook.getSheet("Manufacturer");
    Iterator<Row> objRowIt = productSheet.rowIterator();
    Iterator objCellIterator = null;

    // Declaring data elements
    Product product = null;
    ProductVariant productVariant = null;
    List<ProductVariant> productVariants = null;
    Map<Integer, String> headerMap;
    Map<Integer, String> rowMap;
    colProductList = new HashSet<Product>();

    int rowCount = 1;
    try {
      headerMap = getRowMap(objRowIt);

      // Iterating on the available rows

      while (objRowIt.hasNext()) {
        rowMap = getRowMap(objRowIt);

        // checking if variantId is present. if no variant id is present then tis is a blank row, continue
        String variantId = getCellValue(VARIANT_ID, rowMap, headerMap);
        if (StringUtils.isBlank(variantId)) continue;

        if (StringUtils.isBlank(getCellValue(PRODUCT_ID, rowMap, headerMap))) {
          // this is not a new product. add a new product variant


        } else {
          // this is a new product
          productVariants = new ArrayList<ProductVariant>();

          product = new Product();
          product.setCategories(getCategroyListFromCategoryString(getCellValue(CATEGORY, rowMap, headerMap)));
          product.setId(getCellValue(PRODUCT_ID, rowMap, headerMap));
          product.setName(getCellValue(PRODUCT_NAME, rowMap, headerMap));
          product.setBrand(getCellValue(BRAND, rowMap, headerMap));
          product.setManufacturer(getManufacturerDetails(getCellValue(MANUFACTURER, rowMap, headerMap), manufacturerSheet));
          product.setOverview(getCellValue(OVERVIEW, rowMap, headerMap));
          product.setDescription(getCellValue(DESCRIPTION, rowMap, headerMap));
          product.setThumbUrl(getCellValue(Image, rowMap, headerMap));

          product.setProductVariants(productVariants);

          colProductList.add(product);
        }

        // product options
        List<ProductOption> productOptions = getProductOptions(getCellValue(OPTIONS, rowMap, headerMap));

        productVariant = new ProductVariant();
        productVariant.setId(variantId);
        productVariant.setMarkedPrice(Double.parseDouble(getCellValue(MRP, rowMap, headerMap)));
        productVariant.setDiscountPercent(Double.parseDouble(getCellValue(PERCENTAGE_DISCOUNT, rowMap, headerMap)));
        String costString = getCellValue(COST, rowMap, headerMap);
        if (StringUtils.isNotBlank(costString)) {
          productVariant.setCostPrice(Double.parseDouble(costString));
        }
        productVariant.setHkPrice(productVariant.getMarkedPrice() * (1D - productVariant.getDiscountPercent()));
        productVariant.setShippingBasePrice(Double.parseDouble(getCellValue(SHIPPING, rowMap, headerMap)));
        productVariant.setShippingBaseQty(1L);
        productVariant.setShippingAddPrice(Double.parseDouble(getCellValue(SHIPPING, rowMap, headerMap)));
        productVariant.setShippingAddQty(1L);
        productVariant.setTax(getTaxDetails(getCellValue(TAX, rowMap, headerMap)));
        productVariant.setProductOptions(productOptions);
        productVariant.setOutOfStock(false);
        productVariants.add(productVariant);

        System.out.println("row = " + rowCount);
        rowCount++;
      }

    } catch (Exception e) {
      System.out.println("Exception @ Row:" + rowCount);
      throw new Exception(e);
    }
    return colProductList;

  }

  private Tax getTaxDetails(String taxName) {

    Matcher matcher = p.matcher(taxName);
    String value = null;
    if (matcher.find()) {
      value = matcher.group(1);
    }

    Tax tax = new Tax();
    tax.setName(taxName);
    double percentVal = 0;
    try {
      percentVal = Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new RuntimeException("Ivalid tax value/or unable to parse : "+taxName);
    }
    tax.setValue(percentVal/100);

    return tax;

  }

  /**
   * reads all column values that start with the prefix VAR_
   * returns a list of product options
   * option name = value of string minus the VAR_ prefix
   * option value = value in the cell
   *
   * @param productOptionsStr
   * @return
   */
  private List<ProductOption> getProductOptions(String productOptionsStr) {
    List<ProductOption> productOptions = new ArrayList<ProductOption>();
    if (StringUtils.isBlank(productOptionsStr)) {
      return productOptions;
    }

    String[] productOptionStrArray = StringUtils.split(productOptionsStr, "|");

    for (String productOptionStr : productOptionStrArray) {
      String[] optionNameValueArray = productOptionStr.split(":");
      ProductOption productOption = new ProductOption(optionNameValueArray[0].trim(), optionNameValueArray[1].trim());
      productOptions.add(productOption);
    }

    return productOptions;
  }

  /**
   * Returns a list of categories. Category format Eg:
   * <p/>
   * Baby, Baby Food, Cereal ; Baby, Baby Food, Formula
   * <p/>
   * Here parent relationships are set . left most category is the parent.
   *
   * Eg:<p>
   *
   * Category String = Diabetes>Testing Supplies>Meters>GLUCOCARD01| Home Health Devices>Diabetes Meters>Blood Glucose Meters>GLUCOCARD01
   * <p>
   *
   * Parsing is done as follows for the above string:
   *
   * <pre>
   *   [diabetes: Diabetes ()]
   *
   *            [diabetes>testing supplies: Testing Supplies (Diabetes)]
   *                 |                                   |        |
   *   {fullname->primary key.. md5 of this is taken}    |    {parent display name}
   *                                                     |
   *                                    {display name-> for UI}
   *
   * </pre>
   *
   * and so on...
   *
   *   [diabetes>testing supplies>meters: Meters (Testing Supplies)]
   *   [diabetes>testing supplies>meters>glucocard01: GLUCOCARD01 (Meters)]
   *   [home health devices: Home Health Devices ()]
   *   [home health devices>diabetes meters: Diabetes Meters (Home Health Devices)]
   *   [home health devices>diabetes meters>blood glucose meters: Blood Glucose Meters (Diabetes Meters)]
   *   [home health devices>diabetes meters>blood glucose meters>glucocard01: GLUCOCARD01 (Blood Glucose Meters)]
   *
   *
   *
   * @param categoryString
   * @return
   */
  private List<Category> getCategroyListFromCategoryString(String categoryString) {
    logger.debug("Category -> " + categoryString);
    String[] catFamilies = StringUtils.split(categoryString, "|");
    List<Category> catFamilyList = new ArrayList<Category>();

    for (int i = 0; i < catFamilies.length; i++) {
      String catFamily = catFamilies[i].trim();
      if (catFamily.charAt(catFamily.length() -1) != '>') {
        catFamily = catFamily + ">";
      }

      Category parentCategory = null;
      String categoryTree = "";

      while (catFamily.indexOf('>') != -1) {
        String displayName = catFamily.substring(0, catFamily.indexOf('>'));
        String fullName = categoryTree + displayName;
        categoryTree = categoryTree + displayName + ">";

        Category category = new Category();
        category.setName(displayName);
        category.setSlug(Category.getSlugFromName(displayName));
        catFamilyList.add(category);

        parentCategory = category;

        catFamily = catFamily.substring(catFamily.indexOf('>')+1, catFamily.length());
      }
    }
    logger.debug("catFamilyList: " + catFamilyList.size());
    return catFamilyList;
  }

  public static void main(String[] args) {
    String catString = "Diabetes>Testing Supplies>Meters>GLUCOCARD01| Home Health Devices>Diabetes Meters>Blood Glucose Meters>GLUCOCARD01";
    XslParser xslParser = new XslParser();
    List<Category> categoryList = xslParser.getCategroyListFromCategoryString(catString);
    for (Category category : categoryList) {
      System.out.println(category.toString());
    }
  }

  private String getCellValue(String header, Map<Integer, String> rowMap, Map<Integer, String> headerMap) {
    Integer columnIndex = getColumnIndex(header, headerMap);
    if (columnIndex == null) return null;
    String cellVal = rowMap.get(columnIndex);
    return cellVal == null ? null : cellVal.trim();
  }

  private Integer getColumnIndex(String header, Map<Integer, String> headerMap) {
    Integer columnIndex = null;
    for (Integer key : headerMap.keySet()) {
      if (headerMap.get(key).equals(header)) columnIndex = key;
    }
    return columnIndex;
  }

  private Map<Integer, String> getRowMap(Iterator<Row> objRowIt) {
    // Header are read and related columns are taken care of
    // accordignly.

    Map<Integer, String> headerMap = new HashMap<Integer, String>();

    HSSFRow headers = (HSSFRow) objRowIt.next();
    Iterator objCellIterator = headers.cellIterator();
    while (objCellIterator.hasNext()) {
      HSSFCell headerCell = (HSSFCell) objCellIterator.next();
      int headerColIndex = 0;
      try {
        headerColIndex = headerCell.getColumnIndex();
        Object cellValue = null;
        try {
          cellValue = headerCell.getStringCellValue();
        } catch (Exception e) {
          logger.trace("trying to read as numeric");
          cellValue = headerCell.getNumericCellValue();
        }
        headerMap.put(headerColIndex, cellValue.toString());
      } catch (Exception e) {
        logger.error("error in reading cell value", e);
      }
    }

    return headerMap;
  }

  private Manufacturer getManufacturerDetails(String manufacturerName, HSSFSheet manufacturerSheet) {

    Iterator<Row> rowIterator = manufacturerSheet.rowIterator();
    Map<Integer, String> headerMap = getRowMap(rowIterator);
    Manufacturer manufacturer = null;

    while (rowIterator.hasNext()) {
      Map<Integer, String> rowMap = getRowMap(rowIterator);

      String name = getCellValue(MANUFACTURER_NAME, rowMap, headerMap);
      if (name != null && name.equals(manufacturerName)) {
        manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setDescription(getCellValue(MANUFACTURER_DESCRIPTION, rowMap, headerMap));
        manufacturer.setWebsite(getCellValue(MANUFACTURER_WEBSITE, rowMap, headerMap));
        break;
      }
    }

    return manufacturer;
  }

  public Product getProductFromList(String id) {
    for (Iterator iterator = colProductList.iterator(); iterator.hasNext();) {
      Product p = (Product) iterator.next();
      if (p != null && p.getId() != null && p.getId().equals(id))
        return p;
    }
    return null;
  }

}
