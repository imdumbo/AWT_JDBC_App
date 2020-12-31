package com.First_App.Service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeService {
    FileInputStream fis =null;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    String status = "";
    public EmployeeService() {
        try {
            fis = new FileInputStream("F:\\java tut\\First_App001\\src\\com\\First_App\\Properties\\First_App.properties");
            Properties p = new Properties();
            p.load(fis);
            String driver_Class = p.getProperty("driver_Class");
            String driver_URL = p.getProperty("driver_URL");
            String db_user = p.getProperty("db_user");
            String db_password = p.getProperty("db_password");
            Class.forName(driver_Class);
            conn = DriverManager.getConnection(driver_URL, db_user, db_password);
            st = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String add(int eno, String ename, float esal, String eaddr){
        try{
            rs = st.executeQuery("select * from emp1 where eno="+eno);
            boolean b = rs.next();
            if(b){
                status = "Employee Existed";
            }else{
                st.executeQuery("insert into emp1 values("+eno+",'"+ename+"',"+esal+",'"+eaddr+"')");
                status = "Employee inserts SuccessFully";
            }
        }catch (Exception e){
            status = "Employee Insertion Failure";
            e.printStackTrace();
        }
        return status;
    }
}
