package com.tutorialspoint;

import java.sql.*;
import java.util.Vector;

/**
 * Created by I331490 on 7/12/2016.
 */
public class MasterDataCheck {
    public static Vector masterDataType=new Vector();
    public static Vector resource_category=new Vector();
    public static Connection connection=null;

    public boolean tryconnect(String myServer,String instanceNumber,String username,String passwd)
    {
        try {
              /*  String myServer="10.58.184.216";
                String username="TRPADM";
                String passwd="Abcd1234";
                */
          //  System.out.print("jdbc:sap://"+myServer+"/?instanceNumber="+instanceNumber);
          //  System.out.print(username);
           // System.out.print(passwd);
            try {
            	Class.forName("com.sap.db.jdbc.Driver");
            	} catch (Exception ex) {
            	  // handle the error
            		System.out.println(ex.toString());
            		System.out.println("Driver wrong!");
            	}
           // connection = DriverManager.getConnection("jdbc:sap://"+myServer+":8001",username,passwd);
            connection = DriverManager.getConnection("jdbc:sap://"+myServer+"/?instanceNumber="+instanceNumber,username,passwd);
        } catch (SQLException e) {
        	System.out.println(e.toString());
            System.err.println("\nConnection Failed. User/Passwd Error?");
            return false;
        }
        return true;
    }

    public boolean connect(String myServer,String instanceNumber,String username,String passwd)
    {
        try {
              /*  String myServer="10.58.184.216";
                String username="TRPADM";
                String passwd="Abcd1234";
                */
            //System.out.print("Connect to:");
            connection = DriverManager.getConnection("jdbc:sap://"+myServer+"/?instanceNumber="+instanceNumber,username,passwd);
        } catch (SQLException e) {
            System.err.println("\nConnection Failed. User/Passwd Error?");
            return false;
        }

        masterDataType.add("Resource Category");
        masterDataType.add("Resource Type");
        return true;
    }

    public boolean lookData(int DataCase)
    {
        String masterDataType;
        switch (DataCase)
        {
            case 0:
                /*looking for resource category*/
                masterDataType="Resource Category";
                if (connection != null) {
                    try {
                        System.out.println("\nConnection to HANA successful!\nLooking for master data: "+masterDataType);
                        Statement stmt = connection.createStatement();
                        ResultSet resultSet = stmt.executeQuery("call \"MASTERDATA_TEST\".\"For_test::ResourceCategory\"(out1=>?)");
                        resource_category.clear();
                        while (resultSet.next()) {
                            resource_category.add(resultSet.getString(1));
                            System.out.println(resultSet.getString(1));
                        }
                    } catch (SQLException e) {
                        System.err.println("Query failed!");
                        return false;
                    }
                }
                System.out.println("Finish looking for data!");
                
                break;
            default:
                break;
        }
        return true;
    }
}
