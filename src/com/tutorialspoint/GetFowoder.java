package com.tutorialspoint;

public class GetFowoder {
	
	void getData(String a)
	{
		 try{ String cmdStr="cmd /c start iexplore "
			   		+ "https://ldcix0m.wdf.sap.corp:44311/nwbc/~canvas;window=app/obn/SCMTMS_OBN.fwd_order/"
			   		+ "?KEY="
			   		+ a
			   		+ "&sap-language=EN&sap-client=800";
			// String cmdStr="cmd /c start iexplore http://www.baidu.com";
			 Runtime.getRuntime().exec(cmdStr); 
		  }
		   catch(Exception e){
			   System.out.println(e.toString());
			   return;
		   };
		
	}

}
