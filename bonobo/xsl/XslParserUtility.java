package com.utility.xsl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.model.mobile.Mobile;

public class XslParserUtility {

	private Set<Mobile> colProductList = null;

	public Set<Mobile> readProductList(File objInFile) throws Exception {
		colProductList = new HashSet<Mobile>();

		POIFSFileSystem objInFileSys = new POIFSFileSystem(new FileInputStream(
				objInFile));

		HSSFWorkbook workbook = new HSSFWorkbook(objInFileSys);

		// Assuming there is only one sheet, the first one only will be picked
		HSSFSheet firstSheet = workbook.getSheetAt(0);

		Iterator objRowIt = firstSheet.rowIterator();
		Iterator objCellIterator = null;

		// Declaring data elements
		Mobile p = null;

		boolean rowFlagBreak = false;
		HashMap<Integer, String> headerMap = new HashMap<Integer, String>();
		try {
			// Header are read and related columns are taken care of
			// accordignly.
			HSSFRow headers = (HSSFRow) objRowIt.next();
			objCellIterator = headers.cellIterator();
			while (objCellIterator.hasNext()) {
				HSSFCell headerCell = (HSSFCell) objCellIterator.next();
				int headerColIndex = 0;
				try {
					headerColIndex = headerCell.getColumnIndex();
					headerMap.put(headerColIndex,
							headerCell.getStringCellValue());
				} catch (Exception e) {

				}
			}

			int rowCount = 1;
			// Iterating on the available rows
			while ((!rowFlagBreak) && objRowIt.hasNext()) {
				rowCount++;

				HSSFRow thisRow = (HSSFRow) objRowIt.next();

				objCellIterator = thisRow.cellIterator();

				String id = "";
				boolean sameProduct = false;
				while (objCellIterator.hasNext()) {
					HSSFCell thisCell = (HSSFCell) objCellIterator.next();
					int colInd = 0;
					try {
						colInd = thisCell.getColumnIndex();
						if (headerMap.get(colInd) != null
								&& headerMap.get(colInd).equals("ID")) {
							id = thisCell.getStringCellValue();
							if (id != null && !id.equals(""))
								p = this.getProductFromList(id);
							else
								sameProduct = true;

						}
						if (p == null) {
							p = new Mobile();
							p.setName(id);

						}

						if (!sameProduct) {

							if (headerMap.get(colInd) != null
									&& headerMap.get(colInd).equals("PRODUCT"))
								p.setName(thisCell.getStringCellValue());
							if (headerMap.get(colInd) != null
									&& headerMap.get(colInd).equals("BRAND"))
								p.setMadel(thisCell.getStringCellValue());
							// Manufacturer

						}

					} catch (Exception xlParseColEx) {
						System.out.println("Error at Col:" + colInd);
						throw new Exception("Upload Error at Column " + colInd
								+ " Row:" + rowCount
								+ " Check Upload Content and Retry",
								xlParseColEx);

					}
				}

				colProductList.add(p);
			}

		} catch (Exception e) {
			throw new Exception(e);
		}
		System.out.println("colProductList: " + colProductList.size());
		return colProductList;
	}

	public Mobile getProductFromList(String id) {
		for (Iterator iterator = colProductList.iterator(); iterator.hasNext();) {
			Mobile p = (Mobile) iterator.next();
			if (p != null && p.getName() != null && p.getMadel().equals(id))
				return p;
		}
		return null;
	}

}
