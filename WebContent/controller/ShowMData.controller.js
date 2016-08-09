sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel",
   "sap/ui/model/Context"
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
	     },
	     /*test : function(evt)
	     {
	    	 var test="abc";
	    	 console.log(test);
	    	 var a=evt.getParameters();
	    	 var b=evt.getParameters().id;
	    	 var c=this.getView().byId("Look");
	    	 var d=this.getView().byId(b);
	    	 alert(" ... ");
	    	
	     },*/
	     mycelltest : function(evt)
	     {
	    	// var a=this.getParameters.rowIndex();
	    	 //var a=new sap.ui.base.event();
	    	 var a=evt.getParameters();
	    	 var b=evt.getParameters().rowIndex;
	    	 var c=evt.getParameters().rowBindingContext;
	    	 var d=c.getModel().oData[b].key;
	    	// alert(" ... ");
	     }
   });
});