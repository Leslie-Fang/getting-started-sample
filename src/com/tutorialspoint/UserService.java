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
   
   @GET
   @Path("/CLF")
   @Produces(MediaType.TEXT_PLAIN)
   public String getItp(){
	   try{
	     CreatLocationFilter  LF=new CreatLocationFilter ();
	     LF.CreatLF();	        
	   }
	   catch(Exception e){
		   return "Wrong!";
	   };
       return "Create LF!";
   }
   
   @GET
   @Path("/DLF")
   @Produces(MediaType.TEXT_PLAIN)
   public String DeleteLF(){
	   try{
	     CreatLocationFilter  LF=new CreatLocationFilter ();        
	     LF.DeleteLF();	        
	   }
	   catch(Exception e){
		   return "Wrong!";
	   };
       return "Delete LF!";
   }
   
   @GET
   @Path("/CRF")
   @Produces(MediaType.TEXT_PLAIN)
   public String CreateRF(){
	   try{
		   CreatResourceFilter  RF=new CreatResourceFilter ();        
	     RF.CreateRF();	        
	   }
	   catch(Exception e){
		   return "Wrong!";
	   };
       return "Create RF!";
   }
   
   @GET
   @Path("/DRF")
   @Produces(MediaType.TEXT_PLAIN)
   public String DeleteRF(){
	   try{
		   CreatResourceFilter  RF=new CreatResourceFilter ();         
	     RF.DeleteRF();	        
	   }
	   catch(Exception e){
		   return "Wrong!";
	   };
       return "Delete RF!";
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
   
}