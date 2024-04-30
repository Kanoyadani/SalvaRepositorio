package com.econeigigobhoood.sgb.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Biblioteca implements Cadastro, Emprestar,Tables {
    protected String Nome;
    protected String Autor;
    protected int Paginas;
    protected String Status;

    private List<Livro> biblioteca;

    public Biblioteca() {
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
    

    
    public void table(){

        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";
        Connection connection = Tables.conectar();
        ResultSet resultado = Tables.executarSQL("SELECT IdLivro, Nome, Autor, FROM Livros;");
        if(resultado != null){
            while (resultado.next()){
                int IdLivro =  resultado.getInt("IdLivro");
                String Nome = resultado.getString("Nome");
                String Autor = resultado.getString("Autor");
                String Painas = resultado.getInt("Paginas");



                    Livro setlivro = new Livro();
                    Livro.setlivro(resultado.getInt("IdLivro"));

                    System.out.println("ID LIBRO: " + librosDTO.getIdLibro());

                    // Agregamos los datos al modelo
                    Object[] fila = {id_libro, isbn, titulo, autor, editorial, tipo_libro, precio, contMaterial, categoria, cantidad};
                    modelo.addRow(fila);
            }


            

        }
        
    }



    
}



