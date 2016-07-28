// Provides control my.uilib.Example
jQuery.sap.declare("my.uilib.Example");

/*
 * the require statement allows to import other JavaScript modules, either containing controls or 
 * framework classes / code.
 * Notation:
 * <code> jQuery.sap.require("fully.qualified.name");</code> 
 */
jQuery.sap.require("sap.ui.core.Control");

/**
 * Constructor for a new Example control.
 * 
 * @class
 * An example control that can be used as starting point for custom development. 
 * @name my.uilib.Example
 * @public
 */
sap.ui.core.Control.extend("my.uilib.Example", /** @lends my.uilib.Example */ {
	metadata : {
		library : "my.uilib",
		properties : {
			text : {name : "text", type : "string", group : "Misc", defaultValue : null}
		},
		aggregations : {},
		associations : {},
		events : {
			click : "click"
		}
	}
});	

// *****************************************************************************
// GSS: End of parts that will be generated in full environment
// *****************************************************************************
 
// *****************************************************************************
// GSS: Start of custom control implementation
// *****************************************************************************


// /**
//  * Initialization... if needed
//  */
// my.uilib.Example.prototype.init = function(){
//   //do something for initialization...
// };

/*
 * GSS: In this control implementation, nothing explicit to connect to Browser 
 * events is done.
 * The SAPUI5 framework ensures that browser events triggered on a DOM element 
 * will be handled and forwarded to the instance of the control.
 */
/**
  * Fire Click Event
  * 
  * @param oBrowserEvent
  *            the originating event for further information provisioning.
  */
my.uilib.Example.prototype.onclick = function(oBrowserEvent) {
    this.fireClick({id: this.getId()});
};

// *****************************************************************************
// GSS: End of custom control implementation
// *****************************************************************************
