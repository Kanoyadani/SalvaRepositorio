package com.econeigigobhoood.sgb.View;

import java.sql.SQLException;

import com.econeigigobhoood.sgb.Controller.Controller;

public class MainMenu {
  
public static void main(String[] args) throws SQLException {
    Controller controller = new Controller();

    controller.criarTabelaLivros();
    //controller.insertarLivro( "Harry Potter", "Jk", 120, "S");
    //controller.insertarLivro( "Pai Rico, Pai Pobre", "Kiosaky", 120, "S");
    //controller.table();
    controller.atualizarLivro("N", 1);
    controller.table();
    controller.desconectar();
}
  
    
  
    

    

}
