package DAO;

import Model.Ferramentas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class FerramentaDAO {
    public static ArrayList<Ferramentas> MinhaLista = new ArrayList<Ferramentas>();

    public FerramentaDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_ferramenta");
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

            // Configurar a conexão
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

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    // Retorna a Lista de Ferramentas(objetos)
    public ArrayList getMinhaLista() {
        
        MinhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_ferramenta");
            while (res.next()) {

                String nome = res.getString("nome");
                int id = res.getInt("id");
                String marca = res.getString("marca");
                double custo = res.getDouble("custo");

                Ferramentas objeto = new Ferramentas(nome, id, marca, custo);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    // Cadastra nova ferramenta
    public boolean InsertFerramentaBD(Ferramentas objeto) {
        String sql = "INSERT INTO tb_ferramenta(id,nome,marca,custo) VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getMarca());
            stmt.setDouble(4,objeto.getCusto());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Deleta uma ferramenta específica pelo seu campo ID
    public boolean DeleteFerramentaBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_ferramenta WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    // Edita uma ferramenta específica pelo seu campo ID
    public boolean UpdateFerramentaBD(Ferramentas objeto) {

        String sql = "UPDATE tb_ferramenta set nome = ?, marca = ?, custo = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());            
            stmt.setString(2, objeto.getMarca());
            stmt.setDouble(3,objeto.getCusto());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // carrega dados de uma ferramenta específica pelo seu ID
    public Ferramentas carregaFerramenta(int id) {
        
        Ferramentas objeto = new Ferramentas();
        objeto.setId(id);
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_ferramenta WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setId(res.getInt("id"));
            objeto.setMarca(res.getString("marca"));
            objeto.setCusto(res.getDouble("custo"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto;
    }
}


