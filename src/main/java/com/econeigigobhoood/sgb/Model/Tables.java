package com.econeigigobhoood.sgb.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tables {

    public static Connection conexion = null;
    
     public static boolean hayConection() {
        return (conexion != null);
    }


    public static Connection conectar() {
        try {
            Class.forName("org.h2.Driver");
            // Estabelece a conexão com o banco de dados H2 em memória
            conexion = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Tables.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }





    public static ResultSet executarSQL(String consultaSQL) throws Exception {
        if (conexion != null) {
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(consultaSQL);
            return resultado;
        } else {
            return null;
        }
    }
    
     public static boolean executarAtualziacaoSQL(String comandoSQL) {

        boolean ok;
        if (hayConection()) {
            PreparedStatement sql;
            try {
                sql = conexion.prepareStatement(comandoSQL);

                ok = sql.executeUpdate() != 0;
                // Importante fechar a conexão
                sql.close();
                sql = null;
                return ok;
            } catch (SQLException ex) {
                Logger.getLogger(Tables.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
     
     public static void desconectar() {
    try {
        if (conexion != null) {
            conexion.close();
            conexion = null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(Tables.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}