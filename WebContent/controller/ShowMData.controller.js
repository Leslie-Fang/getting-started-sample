sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.ShowMData", {
	   
	   onInit : function () {
		   
		  // var oData =[{key1:"world"}];
		   var oData =[];
  		   var oModel = new JSONModel(oData);
  		   this.getView().setModel(oModel,"test");		
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
	     LookResourceCategory:function()
	     {
	    	 var self = this;
	    	 var params="No thing now!";
	    	 $.post("/getting-started-sample/rest/UserService/GetRsrcCatgry",params, function(result)
	    	{
	    		 console.log(result);	    		
	    		 var oModel = new JSONModel(result);	
	    		 self.getView().setModel(oModel,"test");
	    			 })
	     }
   });
});