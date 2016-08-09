package com.tutorialspoint;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tutorialspoint.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import com.sap.tm.trp.*;

@Path("/UserService")
public class UserService {
	public static String hostname;
	public static String InsatnceNum;
	public static String username;
	public static String password;
	   
   UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> getUsers(){
      return userDao.getAllUsers();
   }
   
   @GET
   @Path("/Goo")
   @Produces(MediaType.TEXT_PLAIN)
   public String getIt() {
       return "Got it!";
   }
   
   @Path("/test")
   @POST
   @Produces(MediaType.TEXT_PLAIN)
   public String getTest(String message){
	   try{
		   System.out.println(message);
		   String[] data=message.split("&"); 
		   hostname=data[0].split("=")[1];
		   InsatnceNum=data[1].split("=")[1];
		   username=data[2].split("=")[1];
		   password=data[3].split("=")[1];		  		    
		   
		   MasterDataCheck rc=new MasterDataCheck();
		   if(!rc.tryconnect(hostname,InsatnceNum,username,password))
           {
			   System.out.println("Conneced failed!");
			   return "False";
           }		   
		   System.out.println("Connect Success!");
	   }
	   catch(Exception e){
		   return "False";
	   };
       return "True";
   }
   
   @POST
   @Path("/CLF")
   @Produces(MediaType.TEXT_PLAIN)
   public String getItp(String message){
	   try{
		 System.out.println(message);
	     CreatLocationFilter  LF=new CreatLocationFilter ();
	     if(!LF.CreatLF()){return "Create LocationFilter Failed! \n LocationFilter exists.";};	        
	   }
	   catch(Exception e){
		   return "Create LocationFilter Failed! LocationFilter exists.";
	   };
       return "Create LocationFilter Success!";
   }
   
   @POST
   @Path("/DLF")
   @Produces(MediaType.TEXT_PLAIN)
   public String DeleteLF(String message){
	   try{
		 System.out.println(message);
	     CreatLocationFilter  LF=new CreatLocationFilter ();        
	     if(!LF.DeleteLF()){return "Delete LocationFilter Failed!";};	        
	   }
	   catch(Exception e){
		   return "Delete LocationFilter Failed!";
	   };
       return "Delete LocationFilter Success!";
   }
   
   @POST
   @Path("/CRF")
   @Produces(MediaType.TEXT_PLAIN)
   public String CreateRF(String message){
	   try{
		   System.out.println(message);
		   CreatResourceFilter  RF=new CreatResourceFilter ();        
		   if(!RF.CreateRF()){return "Create ResourceFilter Failed! ResourceFilter exists.";};	        
	   }
	   catch(Exception e){
		   return "Create ResourceFilter Failed! ResourceFilter exists.";
	   };
	   return "Create ResourceFilter Success!";
   }
   
   @POST
   @Path("/DRF")
   @Produces(MediaType.TEXT_PLAIN)
   public String DeleteRF(String message){
	   try{
		   System.out.println(message);
		   CreatResourceFilter  RF=new CreatResourceFilter ();         
		   if(!RF.DeleteRF()){return "Delete ResourceFilter Failed!";};	        
	   }
	   catch(Exception e){
		   return "Delete ResourceFilter Failed!";
	   };
	   return "Delete ResourceFilter Success!";
   }
   
   @Path("/GetRsrcCatgry")
   @POST
   @Produces("application/json")
   public String getRsrcCatgry(String message){
	  // JSONObject param_up1 = new JSONObject();
	 //  String result="[{";
	   String result="[";
	   try{
		   System.out.println(message);
		   MasterDataCheck rc=new MasterDataCheck();
		   rc.lookData(0);
		   
		   Iterator it=MasterDataCheck.resource_category.iterator();
		   int i=1;
		  while(it.hasNext())
		  {
			  String item=(String)it.next();
			//  param_up1.put("key"+i, item);
			  System.out.println(item);
			  //result=result+"\"key"+i+"\""+":"+"\""+item+"\""+",";
			  result=result+"{"+"\"key\""+":"+"\""+item+"\""+"}"+",";
			  i++;
		  }
		  result=result.substring(0,result.length()-1);
		 // result=result+"}]";
		  result=result+"]";
		  //System.out.println(param_up1);
		  System.out.println(result);
	   }
	   catch(Exception e){
		   return "False";
	   };
       return result;
   }
   
   @POST
   @Path("/ABAPT")
   @Produces(MediaType.TEXT_PLAIN)
   public String ABAPTest(String message){
	   System.out.println(message);
	   try{
		   TestTMBapi  a=new TestTMBapi();
		   a.test();
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "Create ResourceFilter Failed! ResourceFilter exists.";
	   };
	   System.out.println("success!");
	   return "call abap!";
   }
   
   @POST
   @Path("/TESTABAPT")
   @Produces("application/json")
   public String ABAPTest2(String message){
	   System.out.println(message);
	   String result;
	   try{
		   TestTMS  a=new TestTMS();
		   result=a.test();
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "Create ResourceFilter Failed! ResourceFilter exists.";
	   };
	   System.out.println("success!");
	   return result;
   }
   
   @POST
   @Path("/MyABAPT")
   @Produces("application/json")
   public String ABAPMyTest2(String message){
	   System.out.println(message);
	   String result;
	   try{
		   TestMyAbap  a=new TestMyAbap();
		   result=a.test();
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "Create ResourceFilter Failed! ResourceFilter exists.";
	   };
	   System.out.println("success!");
	   return result;
   }

   @POST
   @Path("/GetTU")
   @Produces("application/json")
   public String getTU(String message){
	   System.out.println(message);
	   String result=" ";
	   try{
		   getTU a= new getTU();
		   a.getData(message);
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "failed!";
	   };
	   System.out.println("success!");
	   return result;
   }
   
   @POST
   @Path("/GetFowoder")
   @Produces("application/json")
   public String GetFowoder(String message){
	   System.out.println(message);
	   String result=" ";
	   try{
		   GetFowoder a= new GetFowoder();
		   a.getData(message);
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "failed!";
	   };
	   System.out.println("success!");
	   return result;
   }
   
   @POST
   @Path("/GetFreoder")
   @Produces("application/json")
   public String  GetFreoder(String message){
	   System.out.println(message);
	   String result=" ";
	   try{
		   GetFreoder a= new  GetFreoder();
		   a.getData(message);
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   return "failed!";
	   };
	   System.out.println("success!");
	   return result;
   }
}