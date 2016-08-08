package com.tutorialspoint;

import com.tutorialspoint.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
/**
 * Created by I331490 on 7/19/2016.
 */
public class CreatLocationFilter {
    static Vector LFID=new Vector();
    boolean CreatLF() throws Exception {
       // String url="http://10.58.184.216:8001/sap/tm/trp/service/user/locationFilters.json";
        String url="http://"+UserService.hostname+":80"+UserService.InsatnceNum+"/sap/tm/trp/service/user/locationFilters.json";
        URL obj=new URL(url);
        HttpURLConnection con=(HttpURLConnection)obj.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        //String authorization = "TRPADM" + ":" + "Abcd1234";
        String authorization = UserService.username + ":" + UserService.password;
        authorization = "Basic " + new String(new Base64().encode(authorization.getBytes()));
        con.setRequestProperty ("Authorization", authorization);

        JSONArray jsonarry=new JSONArray();
        JSONObject param1 = new JSONObject("{'ID':'RAwBT8cq7jIXqNXFfcvUH0','NAME':'AEDXB'}");
        jsonarry.put(param1);
        JSONObject param2 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXpPfp8CssvG\",\"NAME\":\"AEDXBDACE\"}");
        jsonarry.put(param2);
        JSONObject param3 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXqNXFfcuUH0\",\"NAME\":\"AEJEA\"}");
        jsonarry.put(param3);
        JSONObject param4 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXpPfp8CusvG\",\"NAME\":\"AEJEADDPA\"}");
        jsonarry.put(param4);
        JSONObject param5 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXpPfp8CvMvG\",\"NAME\":\"AEJEADDPW\"}");
        jsonarry.put(param5);
        JSONObject param6 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXqNXFfct{H0\",\"NAME\":\"AEKHL\"}");
        jsonarry.put(param6);
        JSONObject param7 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXpPfp8CvsvG\",\"NAME\":\"AEKHLDABU\"}");
        jsonarry.put(param7);
        JSONObject param8 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXqNXFfctUH0\",\"NAME\":\"AEKLF\"}");
        jsonarry.put(param8);
        JSONObject param9 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXpPfsLvQMvG\",\"NAME\":\"AEKLFDKFK\"}");
        jsonarry.put(param9);
        JSONObject param10 = new JSONObject("{\"ID\":\"RAwBT8cq7jIXqNXFfcr{H0\",\"NAME\":\"AESHJ\"}");
        jsonarry.put(param10);

        JSONObject param_up1 = new JSONObject();
        param_up1.put("NAME","TEST_TOOL_GEN_06");
        param_up1.put("VISIBILITY","G");
        param_up1.put("RESOURCE_CATEGORY","RC");
        param_up1.put("TYPE",1);
        param_up1.put("ITEMS",jsonarry);

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(param_up1.toString());
        wr.flush();

        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        //System.out.println(HttpResult);
        if (HttpResult == 201) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            //int n=0;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                // n++;
            }
            // System.out.println(n);
            br.close();
            System.out.println("" + sb.toString());
            //get the the first string which include the ID part
            String CID=sb.substring(0);
            System.out.println(CID);
            JSONObject outjson = new JSONObject(CID);
            JSONObject outjson2 = new JSONObject(outjson.getString("data"));
            LFID.add(outjson2.getString("ID"));
            System.out.println(outjson2.getString("ID"));
            return true;
        } else {
            System.out.println(con.getResponseMessage());
            return false;
        }
    }

    boolean DeleteLF() throws Exception{
        System.out.println(LFID.size());
        boolean suc=true;
        if(LFID.size() ==0)
        {
        	return false;
        }
        while (LFID.size() !=0)
        {
            String DeletID=(String)LFID.firstElement();
            System.out.println(DeletID);
           // String url="http://10.58.184.216:8001/sap/tm/trp/service/user/locationFilters.json/"+DeletID;
            String url="http://"+UserService.hostname+":80"+UserService.InsatnceNum+"/sap/tm/trp/service/user/locationFilters.json"+DeletID;
            URL obj=new URL(url);
            HttpURLConnection con=(HttpURLConnection)obj.openConnection();
            con.setDoOutput(true);

            con.setRequestMethod("DELETE");

           // String authorization = "TRPADM" + ":" + "Abcd1234";
            String authorization = UserService.username + ":" + UserService.password;
            authorization = "Basic " + new String(new Base64().encode(authorization.getBytes()));
            con.setRequestProperty ("Authorization", authorization);
            int HttpResult = con.getResponseCode();
            System.out.println(HttpResult);
            if(HttpResult==204) {
                System.out.println("Delete "+DeletID+" successfully!");
                suc=true;
            }
            else {
                System.out.println("Delete failed!");  
                suc=false;
            }
           // System.out.println(LFID.size());
            LFID.removeElementAt(0);
        }
       // System.out.println("Test");  
       // System.out.println(suc);  
        if(suc ==true)
        {
        //	System.out.println("Test true");  
        	return true;
        }
        else
        {
        //	System.out.println("Test false");  
        	return false;
        }
    }
}
