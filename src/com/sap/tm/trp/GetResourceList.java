package com.sap.tm.trp;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class GetResourceList {

	static String ABAP_AS_WITHOUT_POOLED = "ABAP_AS_WITHOUT_POOL";
	
	public static void main(String args[]) throws JCoException {
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_WITHOUT_POOLED);
		JCoFunction function = destination.getRepository().getFunction("BAPI_RSSRVSCMB_GETLIST2");
		if (function == null)
			throw new RuntimeException("BAPI_RSSRVSCMB_GETLIST2 not found in SAP.");

		try {
			function.execute(destination);
		} catch (AbapException e) {
			System.out.println(e.toString());
			return;
		}

/*		JCoStructure returnStructure = function.getExportParameterList().getStructure("SELECTED_ROWS");
		if (!(returnStructure.getString("COMMIT_CONTROL").equals("") || returnStructure.getString("COMMIT_CONTROL").equals("S"))) {
			throw new RuntimeException(returnStructure.getString("MESSAGE"));
		}*/

		JCoTable codes = function.getTableParameterList().getTable("RESOURCE_HEAD");
		System.out.println(codes.getNumRows());
		for (int i = 0; i < codes.getNumRows(); i++) {
			codes.setRow(i);
			System.out.println(codes.getString("RESUID") + '\t' + codes.getString("RESTYPE"));
		}

		// move the table cursor to first row
		/*codes.firstRow();
		for (int i = 0; i < codes.getNumRows(); i++, codes.nextRow()) {
			function = destination.getRepository().getFunction("BAPI_RSSRVSCMB_GETLIST2");
			if (function == null)
				throw new RuntimeException("BAPI_RSSRVSCMB_GETLIST2 not found in SAP.");

			function.getImportParameterList().setValue("COMPANYCODEID", codes.getString("RESUID"));

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
		}*/ // for
	}
}
