package com.example.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Route("")
@PWA(name = "Basic SQL", shortName = "Basic")
public class MainView extends VerticalLayout {
private static final String PERSISTENCE_UNIT_NAME = "DAO";
EntityManager em;
private static EntityManagerFactory factory;
    public MainView() {
        
    try {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        /* em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();*/
        sql_do1("USER_first_name", "USER_second_name", "USER_city","USER_country");
        sql_do();
    } 
    catch (ClassNotFoundException ex) 
    {
        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void sql_do() throws ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        try (
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://217.159.141.147:17000/tester?useSSL=false", "user1", "datapassword1");
            Statement stmt = conn.createStatement();
            ) 
        {
        
        String strSelect = "SELECT first_name, second_name from customer";
        ResultSet rset = stmt.executeQuery(strSelect);
        while(rset.next()) 
        {   
            String fname = rset.getString("first_name");
            String sname = rset.getString("second_name");
            System.out.println(fname + " " +sname);
        }
        }
        catch(SQLException ex) 
        {
         ex.printStackTrace();
        }
    }
    public void sql_do1(String fname, String sname, String city, String country) throws ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        try (
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://217.159.141.147:17000/tester?useSSL=false", "user1", "datapassword1");
            Statement stmt = conn.createStatement();
            ) 
        {
            String in = "INSERT INTO customer (first_name, second_name, city, country) VALUES('"+fname+"','"+sname+"','"+city+"','"+country+"')";
            System.out.println(in);
            stmt.executeUpdate(in);
        }
        catch(SQLException ex) 
        {
         ex.printStackTrace();
        }
    }
}
