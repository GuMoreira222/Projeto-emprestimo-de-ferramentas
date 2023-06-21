package DAO;

import Model.Amigo;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmigoDAO {

    public static ArrayList<Amigo> MinhaLista = new ArrayList<Amigo>();

    public AmigoDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_amigo");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public Connection getConexao() {

        Connection connection = null;  //instância da conexão

          try {

            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conex�o
            String server = "localhost"; //caminho do MySQL
            String database = "bd_dados";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "123456789";

            connection = DriverManager.getConnection(url, user, password);

            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NãO CONECTADO!");
            }

        } catch (ClassNotFoundException e) {  //Driver n�o encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            e.printStackTrace();
            //System.out.println(e.getMessage());
            
        }

        return connection;
    }

    // Retorna a Lista de Amigos(objetos)
    public ArrayList getMinhaLista() {
        
        MinhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo");
            while (res.next()) {

                String nome = res.getString("nome");
                int id = res.getInt("id");
                String telefone = res.getString("telefone");

                Amigo objeto = new Amigo(nome, id, telefone);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    // Cadastra novo amigo
    public boolean InsertAmigoBD(Amigo objeto) {
        String sql = "INSERT INTO tb_amigo(id,nome,telefone) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Deleta um amigo específico pelo seu campo ID
    public boolean DeleteAmigoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_amigo WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    // Edita um amigo específico pelo seu campo ID
    public boolean UpdateAmigoBD(Amigo objeto) {

        String sql = "UPDATE tb_amigo set nome = ?, telefone = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());           
            stmt.setString(2, objeto.getTelefone());
            stmt.setInt(3, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // carrega dados de um amigo específico pelo seu ID
    public Amigo carregaAmigo(int id) {
        
        Amigo objeto = new Amigo();
        objeto.setId(id);
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setId(res.getInt("id"));
            objeto.setTelefone(res.getString("telefone"));


            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto;
    }
}
