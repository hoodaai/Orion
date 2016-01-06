package com.utility.xsl;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.pojo.Deals;

public class XlsParser {

	public ArrayList<Deals> readPatientList(File objInFile) throws Exception {

		POIFSFileSystem objInFileSys = new POIFSFileSystem(new FileInputStream(
				objInFile));

		HSSFWorkbook workbook = new HSSFWorkbook(objInFileSys);

		// Log

		// Assuming there is only one sheet, the first one only will be picked
		HSSFSheet firstSheet = workbook.getSheetAt(0);

		Iterator objRowIt = firstSheet.rowIterator();
		Iterator objCellIterator = null;
		boolean objIsFirstRowSkipped = false;

		// Declaring data elements
		Deals objPatient = null;
		ArrayList<Deals> colPatientList = new ArrayList<Deals>();
		boolean rowFlagBreak = false;
		int rowCount = 1;
		// Iterating on the available rows
		while ((!rowFlagBreak) && objRowIt.hasNext()) {

			rowCount++;
			// Assuming the first row is the header, of no use to the parser
			HSSFRow thisRow = (HSSFRow) objRowIt.next();
			if (!objIsFirstRowSkipped) {// Skipping header

				thisRow = (HSSFRow) objRowIt.next();
				objIsFirstRowSkipped = true;
			}

			objCellIterator = thisRow.cellIterator();
			objPatient = new Deals();

			while (objCellIterator.hasNext()) {
				HSSFCell thisCell = (HSSFCell) objCellIterator.next();

				int colInd = 0;

				try {
					colInd = thisCell.getCellNum();

					if (colInd == 0)
						objPatient.setCOLOUR(thisCell.getRichStringCellValue()
								.toString());

					
				}

				catch (Exception xlParseColEx) {

					System.out.println("Error at Col:" + colInd);
					throw new Exception("Upload Error at Column " + colInd
							+ " Row:" + rowCount
							+ " Check Upload Content and Retry", xlParseColEx);

				}

			}
			colPatientList.add(objPatient);

		}
		colPatientList.trimToSize();
		System.out.println("Rows Parsed:" + colPatientList.size());

		return colPatientList;
	}

}
