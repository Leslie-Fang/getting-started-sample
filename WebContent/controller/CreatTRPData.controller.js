sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.CreatTRPData", {
	   onInit : function () {
		  
	      },	     
	      CreatMasterData:function(){
	    	 var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
	    	 oRouter.navTo("MainMenu");
	     },
	     ShowMasterData:function()
	     {
	    	 var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
	    	 oRouter.navTo("ShowMData");
	     },
	     CreatTRPData:function()
	     {
	    	 var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
	    	 oRouter.navTo("CreatTRPData");
	     },
	     CreatLocationFilter:function()
	     {
	    	// alert("Username or Password Wrong! ");
	    	 var URL="http://localhost:8080/getting-started-sample/rest/UserService/CLF";
	 		 var name=jQuery.sap.syncGet(URL);
	     },
	     DeleteLocalFilter : function()
	     {
	    	 var URL="http://localhost:8080/getting-started-sample/rest/UserService/DLF";
	 		 var name=jQuery.sap.syncGet(URL);
	     },
	     CreatResourceFilter:function()
	     {
	    	 var URL="http://localhost:8080/getting-started-sample/rest/UserService/CRF";
	 		 var name=jQuery.sap.syncGet(URL);
	     },
	     DeleteResourceFilter:function()
	     {
	    	 var URL="http://localhost:8080/getting-started-sample/rest/UserService/DRF";
	 		 var name=jQuery.sap.syncGet(URL);
	     }
	    
   });
});