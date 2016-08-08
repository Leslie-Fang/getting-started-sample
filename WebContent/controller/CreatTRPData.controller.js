sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.CreatTRPData", {
	   onInit : function () {
		   var oModel = new JSONModel({data: {}});
		   this.getView().setModel(oModel);
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
	    	 var self = this;
	    	 var params="No thing now!";
	    	 
	    	 $.post("/getting-started-sample/rest/UserService/CLF",params, function(result)
	    	{
	    		 console.log(result);	    		
	    		 self.getView().byId("getLValue").setText(result);
	    			 })
	    	// var URL="http://localhost:8080/getting-started-sample/rest/UserService/CLF";
	 		// var name=jQuery.sap.syncGet(URL);
	     },
	     DeleteLocalFilter : function()
	     {
	    	 var self = this;
	    	 var params="No thing now!";
	    	 
	    	 $.post("/getting-started-sample/rest/UserService/DLF",params, function(result)
	    	{
	    		 console.log(result);	    		
	    		 self.getView().byId("getLValue").setText(result);
	    			 })
	    	 //var URL="http://localhost:8080/getting-started-sample/rest/UserService/DLF";
	 		 //var name=jQuery.sap.syncGet(URL);
	     },
	     CreatResourceFilter:function()
	     {
	    	 var self = this;
	    	 var params="No thing now!";
	    	 
	    	 $.post("/getting-started-sample/rest/UserService/CRF",params, function(result)
	    	{
	    		 console.log(result);	    		
	    		 self.getView().byId("getRValue").setText(result);
	    			 })
	    			 
	    	// var URL="http://localhost:8080/getting-started-sample/rest/UserService/CRF";
	 		// var name=jQuery.sap.syncGet(URL);
	     },
	     DeleteResourceFilter:function()
	     {
	    	 var self = this;
	    	 var params="No thing now!";
	    	 
	    	 $.post("/getting-started-sample/rest/UserService/DRF",params, function(result)
	    	{
	    		 console.log(result);	    		
	    		 self.getView().byId("getRValue").setText(result);
	    			 })
	    	// var URL="http://localhost:8080/getting-started-sample/rest/UserService/DRF";
	 		// var name=jQuery.sap.syncGet(URL);
	     }
	    
   });
});