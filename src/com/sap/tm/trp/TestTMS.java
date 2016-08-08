package com.sap.tm.trp;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class TestTMS {
	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
	public String test()throws JCoException {
		String result="[";
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		JCoFunction function = destination.getRepository().getFunction("BAPI_LOCSRVAPS_GETLIST2");
		
		if (function == null)
			throw new RuntimeException("BAPI_LOCSRVAPS_GETLIST2 not found in SAP.");
		function.getImportParameterList().setValue("LOGICAL_SYSTEM", "X0MCLNT800");
		//function.getImportParameterList().setValue("IV_RES_CAT", "RC");
		JCoTable table = function.getTableParameterList().getTable("LOCATION_SELECTION");
		table.appendRow();
		table.lastRow();
		table.setValue("SIGN", "I");
		table.setValue("OPTION", "CP");
		table.setValue("LOW", "RC_*");
		
		try {
			function.execute(destination);
		} catch (AbapException e) {
			System.out.println(e.toString());
			
		}
		JCoTable locations = function.getTableParameterList().getTable("LOCATION_HEAD");
		for (int i = 0; i < locations.getNumRows(); i++) {
			locations.setRow(i);
			System.out.println(locations.getString("LOCATION") + '\t' + locations.getString("LONGITUDE"));
			 result=result+"{"+"\"LOCATION\""+":"+"\""+locations.getString("LOCATION")+"\""+","+
					 "\"LONGITUDE\""+":"+"\""+locations.getString("LONGITUDE")+"\""+"}"+",";
		//	result=result+"{"+"\"LOCATION\""+":"+"\""+locations.getString("LOCATION")+"\""+"}"+",";
		}
		  result=result.substring(0,result.length()-1);
		  result=result+"]";
		  System.out.println(result);
		return result;
	}

}
