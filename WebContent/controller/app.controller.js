sap.ui.define([
   "sap/ui/core/mvc/Controller",
   "sap/ui/model/json/JSONModel",
   "sap/ui/model/resource/ResourceModel"
], function (Controller,JSONModel, ResourceModel) {
   "use strict";
   return Controller.extend("sap.ui.demo.wt.controller.app", {
	   onInit : function () {
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
      _getDialog : function () {
          if (!this._oDialog) {
             this._oDialog = sap.ui.xmlfragment("sap.ui.demo.wt.view.DialogView", this);
             this.getView().addDependent(this._oDialog);
          }
          return this._oDialog;
       },
	  onShowHello : function () {
	         // show a native JavaScript alert
			 //var sRecipient = this.getView().getModel().getProperty("/recipient");
	         //alert("Hello "+sRecipient);
	         this._getDialog().open();
	      },
	  onCloseDialog : function () {
	         this._getDialog().close();
	      },
      Showi18n : function(){
    	  var oBundle = this.getView().getModel("i18n").getResourceBundle();
    	  var sMsg = oBundle.getText("showHelloButtonText");
    	  var sMsg2 = oBundle.getText("helloMsg","A0");
    	  
    	  alert("Hi "+sMsg+sMsg2);
      },
      Go2Detail : function(){
    	  var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
		  oRouter.navTo("detail");
		  var URL="http://localhost:8080/getting-started-sample/rest/UserService/Goop";
		//  var URL="http://localhost:8082/MyWeb/rest/UserService/users";
		  var name=jQuery.sap.syncGet(URL);
      },
      onLogin : function()
      {
    	  alert("Login! ");
      }
   });
});