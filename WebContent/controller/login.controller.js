sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.login", {
	   onInit : function () {
		   var formModel = new JSONModel({
				credential: {hostname:"10.58.184.216",InsatnceNum:"01"}
			});
			this.getView().setModel(formModel, "form");
			
		   var oData = {
		            password :  "World"
		         };
		         var oModel = new JSONModel(oData);
		         this.getView().setModel(oModel);
	         // set data model on view
		   /*
	         var oData = {
	            recipient : "World"
	         };
	         var oModel = new JSONModel(oData);
	         this.getView().setModel(oModel);
	         
	         var i18nModel = new ResourceModel({
	             bundleName: "sap.ui.demo.wt.i18n.i18n"
	          });
	         this.getView().setModel(i18nModel, "i18n");
	         */
	      },	     
      onLogin : function()
      {
    	  var judge=0;
    	  var view = this.getView();
    	  var params = view.getModel("form").getProperty("/credential");
    	  var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
    	  //jQuery.sap.ajax.post()
    	  $.post("/getting-started-sample/rest/UserService/test",params, function(result){
    		 //{"success:true}
    		  //result.success === true
    		  console.log(result);
    		  console.log(judge);
    		  if (result=="True")
    		  {
    			  judge=1;
    			  console.log(judge);
    			  oRouter.navTo("MainMenu");
    		  }
    		  else
    		  {
    			 judge=0;
    			 console.log(judge);
    			 oRouter.navTo("login");
    			 alert("Username or Password Wrong! ");
    			  }  
    	  })
    	 // var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
		 // oRouter.navTo("detail");
      }
   });
});