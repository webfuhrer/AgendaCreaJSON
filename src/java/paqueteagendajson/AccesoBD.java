/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteagendajson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class AccesoBD {
    public  static ArrayList<Contacto> devolverContactos() {
          ArrayList<Contacto> lista_contactos=new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
            Statement stmt=conexion.createStatement();
            String sql="SELECT * FROM contactos";
            ResultSet resultados= stmt.executeQuery(sql);
            while(resultados.next())
            {
             String nombre=resultados.getString("nombre");
             String apellido=resultados.getString("apellido");
             String telefono=resultados.getString("telefono");
             Contacto c=new Contacto(nombre, telefono);
             lista_contactos.add(c);
            }
            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_contactos;
    }
}
