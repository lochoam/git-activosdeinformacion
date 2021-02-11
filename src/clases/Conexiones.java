/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.sql.*;
/**
 *
 * @author lochoa
 */
public class Conexiones {
    public static Connection conetarActivos(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_actinformacion", "root", "");
            return cn;
        }catch(SQLException e){
            System.err.println("Eroor en la conexión con la base de datos" + e);
        }
        return (null);
    }
}
