package com.tutorialspoint;

public class GetFreoder {
	
	void getData(String a)
	{
		  try{ String cmdStr="cmd /c start iexplore "
			   		+ "https://ldcix0m.wdf.sap.corp:44311/nwbc/~canvas;window=app/obn/SCMTMS_OBN.fre_order/"
			   		+ "?KEY="
			   		+ a
			   		+ "&WDCONFIGURATIONID=%2fSCMTMS%2fTU_CONTAINER&sap-language=EN&sap-client=800";
		  Runtime.getRuntime().exec(cmdStr); 
		  }
		   catch(Exception e){
			   System.out.println(e.toString());
			   return;
		   };
	}

}
