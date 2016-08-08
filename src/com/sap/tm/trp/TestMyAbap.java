package com.sap.tm.trp;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class TestMyAbap {

	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
	//public static void main(String args[]) throws JCoException {
	public String test()throws JCoException {
		String result="[";
		String result1="[";
		String result2="[";
		//String result1="";
		//String result2="";
		JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		JCoFunction function = destination.getRepository().getFunction("ZFM_TRP_AUTO_OUTPUT");
		System.out.println("1!");
		if (function == null)
		{
			System.out.println("no function!");
			throw new RuntimeException("ZFM_TRP_AUTO_OUTPUT not found in SAP.");
		}

		//function.getImportParameterList().setValue("IM_NUM1", 10);	
		System.out.println("2!");
		try{
			function.execute(destination);
		} catch (AbapException e) {			
			System.out.println(e.toString());
		}
		System.out.println("3!");			
		//JCoTable locations = function.getExportParameterList().getTable("GT_TRQ_HEADER");
		//System.out.println(locations.getNumRows());	
		
		JCoTable locations2 = function.getExportParameterList().getTable("GT_TRQ_HEADER2");
		System.out.println(locations2.getNumRows());	
		
	//	JCoTable locations2 = function.getTableParameterList().getTable("GT_TRQ_HEADER2");
	//	System.out.println(locations2.getNumRows());	
		
		for (int i = 0; i < locations2.getNumRows(); i++) {
			locations2.setRow(i);
			System.out.println(locations2.getString("DB_KEY") + '\t' + locations2.getString("TRQ_ID"));
			 result=result+"{"+"\"DB_KEY\""+":"+"\""+locations2.getString("DB_KEY")+"\""+","+
					 "\"TRQ_ID\""+":"+"\""+locations2.getString("TRQ_ID")+"\""+"}"+",";
			// locations.nextRow();
		}
		result=result.substring(0,result.length()-1);
		result=result+"]";
		System.out.println(result);
		
		
		JCoTable locations3 = function.getExportParameterList().getTable("GT_TU_HEADER2");
		System.out.println(locations3.getNumRows());	
		for (int i = 0; i < locations3.getNumRows(); i++) {
			locations3.setRow(i);
			System.out.println(locations3.getString("DB_KEY")
					+ '\t' + locations3.getString("TOR_ID")
			+ '\t' + locations3.getString("MOVEMENT_CAT")
			+ '\t' + locations3.getString("VOYAGE_ID")
			+ '\t' + locations3.getString("SCH_ID"));
		
		result1=result1+"{"+"\"DB_KEY\""+":"+"\""+locations3.getString("DB_KEY")+"\""
		+","+ "\"TOR_ID\""+":"+"\""+locations3.getString("TOR_ID")+"\""
		+","+ "\"MOVEMENT_CAT\""+":"+"\""+locations3.getString("MOVEMENT_CAT")+"\""
		+","+ "\"VOYAGE_ID\""+":"+"\""+locations3.getString("VOYAGE_ID")+"\""
		+","+ "\"SCH_ID\""+":"+"\""+locations3.getString("SCH_ID")+"\""
		+"}"+",";
			 
		}
		result1=result1.substring(0,result1.length()-1);
		result1=result1+"]";
		System.out.println(result1);
		
		JCoTable locations4 = function.getExportParameterList().getTable("GT_FOFB_HEADER2");
		System.out.println(locations4.getNumRows());	
		for (int i = 0; i < locations4.getNumRows(); i++) {
			locations4.setRow(i);
			System.out.println(locations4.getString("DB_KEY")
					+ '\t' + locations4.getString("TOR_ID")
			+ '\t' + locations4.getString("MOVEMENT_CAT")
			+ '\t' + locations4.getString("VOYAGE_ID")
			+ '\t' + locations4.getString("SCH_ID"));
		
			result2=result2+"{"+"\"DB_KEY\""+":"+"\""+locations4.getString("DB_KEY")+"\""
					+","+ "\"TOR_ID\""+":"+"\""+locations4.getString("TOR_ID")+"\""
					+","+ "\"MOVEMENT_CAT\""+":"+"\""+locations4.getString("MOVEMENT_CAT")+"\""
					+","+ "\"VOYAGE_ID\""+":"+"\""+locations4.getString("VOYAGE_ID")+"\""
					+","+ "\"SCH_ID\""+":"+"\""+locations4.getString("SCH_ID")+"\""
					+"}"+",";
			
		}
		result2=result2.substring(0,result2.length()-1);
		result2=result2+"]";
		System.out.println(result2);
		//result="{"+"\"TABLE1\":"+"\""+result+"\""+"}";
		//result1="{"+"\"TABLE2\":"+"\""+result1+"\""+"}";
		//result2="{"+"\"TABLE3\":"+"\""+result2+"\""+"}";
		
		result="{"+"\"TABLE1\":"+result+",";
		result1="\"TABLE2\":"+result1+",";
		result2="\"TABLE3\":"+result2+"}";
		result=result+result1+result2;
		//result="{"+"\"TABLE1\":"+"\""+result+"\""+","+"\"TABLE2\":"+"\""+result1+"\""+","+"\"TABLE3\":"+"\""+result2+"\""+"}";
		
		//String a=function.getExportParameterList().getValue("SUM").toString();
		//String b=function.getExportParameterList().getValue("AB").toString();
		//System.out.println(a+" : "+b);
		System.out.println(result);
		return result;
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
