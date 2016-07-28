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
public class CreatResourceFilter {
    static Vector RFID=new Vector();
    public void CreateRF() throws Exception{
        //String url="http://10.58.184.216:8001/sap/tm/trp/service/user/resourceFilters.json";
        String url="http://"+UserService.hostname+":80"+UserService.InsatnceNum+"/sap/tm/trp/service/user/resourceFilters.json";
        URL obj=new URL(url);
        HttpURLConnection con=(HttpURLConnection)obj.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        String authorization = UserService.username + ":" + UserService.password;
        authorization = "Basic " + new String(new Base64().encode(authorization.getBytes()));
        con.setRequestProperty ("Authorization", authorization);

        JSONArray jsonarry=new JSONArray();
        JSONObject param1 = new JSONObject("{'ID':'RC_10HC','NAME':'RC_10HC'}");
        jsonarry.put(param1);
        JSONObject param2 = new JSONObject("{\"ID\":\"RC_10OT\",\"NAME\":\"RC_10OT\"}");
        jsonarry.put(param2);
        JSONObject param3 = new JSONObject("{\"ID\":\"RC_10RF\",\"NAME\":\"RC_10RF\"}");
        jsonarry.put(param3);

        JSONObject param_up1 = new JSONObject();
        param_up1.put("NAME","TEST_TOOL_GEN_01");
        param_up1.put("VISIBILITY","G");
        param_up1.put("TYPE",1);
        param_up1.put("ITEMS",jsonarry);
        param_up1.put("RESOURCE_CATEGORY","RC");

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
            RFID.add(outjson2.getString("ID"));
            System.out.println(outjson2.getString("ID"));
        } else {
            System.out.println(con.getResponseMessage());
        }
    }

    public void DeleteRF() throws Exception{
        System.out.println(RFID.size());
        while (RFID.size() !=0)
        {
            String DeletID=(String)RFID.firstElement();
            System.out.println(DeletID);
           // String url="http://10.58.184.216:8001/sap/tm/trp/service/user/resourceFilters.json/"+DeletID;
            String url="http://"+UserService.hostname+":80"+UserService.InsatnceNum+"/sap/tm/trp/service/user/resourceFilters.json"+DeletID;
            URL obj=new URL(url);
            HttpURLConnection con=(HttpURLConnection)obj.openConnection();
            con.setDoOutput(true);

            con.setRequestMethod("DELETE");

            //String authorization = "TRPADM" + ":" + "Abcd1234";
            String authorization = UserService.username + ":" + UserService.password;
            authorization = "Basic " + new String(new Base64().encode(authorization.getBytes()));
            con.setRequestProperty ("Authorization", authorization);
            int HttpResult = con.getResponseCode();
            System.out.println(HttpResult);
            if(HttpResult==204) {
                System.out.println("Delete "+DeletID+" successfully!");
            }
            else {
                System.out.println("Delete failed!");
            }
            RFID.removeElementAt(0);
        }
    }
}
