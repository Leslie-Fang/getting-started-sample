sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.MainMenu", {
	   onInit : function () {
		   var oData=[];
		   var oModel = new JSONModel(oData);
		   this.getView().setModel(oModel,"test");	
		   
		   var oData3=[];
		   var oModel3 = new JSONModel(oData3);
		   this.getView().setModel(oModel3,"test3");	
		   
		   var oData4=[];
		   var oModel4 = new JSONModel(oData4);
		   this.getView().setModel(oModel4,"test4");	
		   
		   var oData2=[{"LOCATION":"A","LONGITUDE":"B"}];
		   var oModel2 = new JSONModel(oData2);
		   this.getView().setModel(oModel2,"TestABAPData");	
		  
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
	     CreatMasterData : function()
	     {
	    	 this.getView().byId("CreatMasterData").setText("Calling ABAP ....");
	    	 var self = this;
	    	 var params="No thing now!";
	    	 
	    	 $.post("/getting-started-sample/rest/UserService/MyABAPT",params, function(result)
	    	 {
	    		 console.log(result);
	    		 var table1=result.TABLE1;
	    		 var oModel = new JSONModel(table1);
	    		 self.getView().setModel(oModel,"test");	
	    		 
	    		 var table3=result.TABLE2;
	    		 var oMode3 = new JSONModel(table3);
	    		 self.getView().setModel(oMode3,"test3");
	    		 
	    		 var table4=result.TABLE3;
	    		 var oMode4 = new JSONModel(table4);
	    		 self.getView().setModel(oMode4,"test4");
	    		 
	    		 self.getView().byId("CreatMasterData").setText("Success!");
	    		    			 })
	    	// var URL="http://localhost:8080/getting-started-sample/rest/UserService/ABAPT";
		 	// var name=jQuery.sap.syncGet(URL);
		 	// this.getView().byId("CreatMasterData").setText("Success!");
	     },
	     TestABAP : function()
	     {
	    	// var URL="http://localhost:8080/getting-started-sample/rest/UserService/TESTABAPT";
		 	// var name=jQuery.sap.syncGet(URL);
	    	 var self = this;
	    	 var params="No thing now!";
	    	 this.getView().byId("TestABAP").setText("Calling ABAP ....");
	    	 $.post("/getting-started-sample/rest/UserService/TESTABAPT",params, function(result)
	    	{
	    	   console.log(result);
	    	   var oModel2 = new JSONModel(result);
	  		   self.getView().setModel(oModel2,"TestABAPData");	
	  		   self.getView().byId("TestABAP").setText("Success!");
	    	//	 var oModel = new JSONModel(result);	
	    		// self.getView().setModel(oModel,"TestABAPData");
	    			 })
	     },
	     /*
	     SelectTU : function ()
	     {
	    	 var test="abc";
	    	 console.log(test);
	    	 this.getView().byId("LinkTU1").setText("Success!");
	    	 alert(" ... ");
	    	 
	     },*/
	     getFowoder : function(evt)
	     {
	    	 var a=evt.getParameters();
	    	 var b=evt.getParameters().rowIndex;
	    	 var c=evt.getParameters().rowBindingContext;
	    	 var d=c.getModel().oData[b].DB_KEY;
	    	 $.post("/getting-started-sample/rest/UserService/GetFowoder",d, function(result)
	    		    	{
	    		    		 console.log(result);})
	     },
	     getTU: function(evt)
	     {
	    	 var a=evt.getParameters();
	    	 var b=evt.getParameters().rowIndex;
	    	 var c=evt.getParameters().rowBindingContext;
	    	 var d=c.getModel().oData[b].DB_KEY;
	    	 $.post("/getting-started-sample/rest/UserService/GetTU",d, function(result)
	    		    	{
	    		    		 console.log(result);})
	     },
	     getFreoder : function(evt)
	     {
	    	 var a=evt.getParameters();
	    	 var b=evt.getParameters().rowIndex;
	    	 var c=evt.getParameters().rowBindingContext;
	    	 var d=c.getModel().oData[b].DB_KEY;
	    	 $.post("/getting-started-sample/rest/UserService/GetFreoder",d, function(result)
	    		    	{
	    		    		 console.log(result);})
	     }
	     
   });
});