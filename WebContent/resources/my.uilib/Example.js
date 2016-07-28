jQuery.sap.declare("my.uilib.Example");jQuery.sap.require("sap.ui.core.Control");sap.ui.core.Control.extend("my.uilib.Example",{metadata:{library:"my.uilib",properties:{text:{name:"text",type:"string",group:"Misc",defaultValue:null}},aggregations:{},associations:{},events:{click:"click"}}});
my.uilib.Example.prototype.onclick=function(b){this.fireClick({id:this.getId()});};
