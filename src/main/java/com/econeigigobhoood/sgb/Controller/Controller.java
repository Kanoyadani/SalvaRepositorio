package com.econeigigobhoood.sgb.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import com.econeigigobhoood.sgb.Model.Cadastro;
import com.econeigigobhoood.sgb.Model.Emprestar;
import com.econeigigobhoood.sgb.Model.Livro;
import com.econeigigobhoood.sgb.Model.Tables;

public abstract class Controller implements Cadastro, Emprestar,Tables {
    DefaultTableModel modelo = new DefaultTableModel();

    protected String Nome;
    protected String Autor;
    protected int Paginas;
    protected String Status;

    private List<Livro> biblioteca;

    public Controller() {
        this.biblioteca = new ArrayList<>();
    }

    
    public void adicionarLivro(int Idlivro,String Nome, String Autor, int Paginas) {
        Livro livro = new Livro(Idlivro,Nome, Autor, Paginas);
        biblioteca.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

  
    public void listarLivros() {
        for (Livro livro : biblioteca) {
            System.out.println(livro);
        }
    }


    public void emprestar(String Status,int IdLvro){
        
    }

    public void devolver(String Status,int IdLvro){

    }
    

    
    public DefaultTableModel  table(){
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";
        
       try {
        Connection connection = Tables.conectar();
        ResultSet resultado = Tables.executarSQL("SELECT IdLivro, Nome, Autor, FROM Livros;");
        if(resultado != null){
            while (resultado.next()){
                int IdLivro =  resultado.getInt("IdLivro");
                String Nome = resultado.getString("Nome");
                String Autor = resultado.getString("Autor");
                int Paginas = resultado.getInt("Paginas");
                String Status = resultado.getString("Staus");



                    Livro setlivro = new Livro();
                    Livro.setIdlivro(resultado.getInt("IdLivro"));

                    System.out.println("ID LIBRO: " + setlivro.getIdlivro());

                    
                    Object[] fila = {IdLivro, Nome, Autor, Paginas};
                    modelo.addRow(fila);
            }
        }

            }catch (Exception e){
                e.printStackTrace();
            }finally{
                Tables.desconectar();
            }
              return modelo;   
    }



    public DefaultTableModel Busca(String Id) {
        DefaultTableModel modelo = new DefaultTableModel();
    
        
            modelo.addColumn("IdLivro");


    
        String sql = "SELECT IdLivro, Nome, Autor FROM Livros lb WHERE IdLivro LIKE  ?";
    
        try {
            Tables.conectar();
            PreparedStatement stmt = Tables.conexion.prepareStatement(sql);
            String parametro = "%" + Id + "%";
            stmt.setString(1, parametro);

    
            ResultSet resultado = stmt.executeQuery();
    
            if (resultado != null) {
                while (resultado.next()) {
                    int IdLivro =  resultado.getInt("IdLivro");
                    String Nome = resultado.getString("Nome");
                    String Autor = resultado.getString("Autor");
                    int Paginas = resultado.getInt("Paginas");
    
                        Livro livros = new Livro();
                        Livro.setIdlivro(resultado.getInt("IdLivro"));
    
                        System.out.println("ID LIBRO: " + livros.getIdlivro());
    
                        
                        Object[] fila = {IdLivro, Nome, Autor, Paginas};
                        modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error consultando libros");
        } finally {
            Tables.desconectar(); 
        }
    
        return modelo;
    }



     public static void insertarLibro(Livro Livro) {
    try {
        Tables.conectar();
        String consulta = "INSERT INTO Livros (IdLivro, Nome, Autor, Paginas, Status) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement statement = Tables.conexion.prepareStatement(consulta);
        statement.setInt(1, Livro.getIdlivro());
        statement.setString(2, Livro.getNome());
        statement.setString(3, Livro.getAutor());
        statement.setInt(4, Livro.getPaginas());
        statement.setString(5, Livro.getStatus());

        statement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el libro: " + Livro.getNome());
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        Tables.desconectar(); // Cerrar la conexión
    }
}

    
}



