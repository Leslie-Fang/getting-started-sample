package com.sap.tm.trp;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class TestTMBapi {

	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
	//public static void main(String args[]) throws JCoException {
	public void test()throws JCoException {
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		JCoFunction function = destination.getRepository().getFunction("ZFM_TRP_AUTO_TRQTOR");
		
		if (function == null)
			throw new RuntimeException("BAPI_COMPANYCODE_GETLIST not found in SAP.");

		function.getImportParameterList().setValue("IV_RES_CAT", "RC");
		
		try {
			function.execute(destination);
		} catch (AbapException e) {
			System.out.println(e.toString());
			return;
		}
/*
		JCoStructure returnStructure = function.getExportParameterList().getStructure("RETURN");
		if (!(returnStructure.getString("TYPE").equals("") || returnStructure.getString("TYPE").equals("S"))) {
			throw new RuntimeException(returnStructure.getString("MESSAGE"));
		}

		JCoTable codes = function.getTableParameterList().getTable("COMPANYCODE_LIST");
		for (int i = 0; i < codes.getNumRows(); i++) {
			codes.setRow(i);
			System.out.println(codes.getString("COMP_CODE") + '\t' + codes.getString("COMP_NAME"));
		}
*/
		/*
		// move the table cursor to first row
		codes.firstRow();
		for (int i = 0; i < codes.getNumRows(); i++, codes.nextRow()) {
			function = destination.getRepository().getFunction("ZFM_TRP_AUTO_TRQTOR");
			if (function == null)
				throw new RuntimeException("BAPI_COMPANYCODE_GETDETAIL not found in SAP.");

			function.getImportParameterList().setValue("IV_RES_CAT", "RC");

			// We do not need the addresses, so set the corresponding parameter
			// to inactive.
			// Inactive parameters will be either not generated or at least
			// converted.
			function.getExportParameterList().setActive("COMPANYCODE_ADDRESS", false);

			try {
				function.execute(destination);
			} catch (AbapException e) {
				System.out.println(e.toString());
				return;
			}

			returnStructure = function.getExportParameterList().getStructure("RETURN");
			if (!(returnStructure.getString("TYPE").equals("") || returnStructure.getString("TYPE").equals("S")
					|| returnStructure.getString("TYPE").equals("W"))) {
				throw new RuntimeException(returnStructure.getString("MESSAGE"));
			}

			JCoStructure detail = function.getExportParameterList().getStructure("COMPANYCODE_DETAIL");

			System.out.println(detail.getString("COMP_CODE") + '\t' + detail.getString("COUNTRY") + '\t'
					+ detail.getString("CITY"));
		} // for
		*/
	}
}
