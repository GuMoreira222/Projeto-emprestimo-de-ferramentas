package Model;

import DAO.FerramentaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ferramentas {

    // Atributos
    private int id;
    private String nome;
    private String marca;
    private double custo;
    private final FerramentaDAO dao;

    // Método Construtor de Objeto Vazio
    public Ferramentas() {
        this.dao = new FerramentaDAO(); // inicializado não importa em qual construtor
    }

    // Método Construtor de Objeto, inserindo dados
    public Ferramentas(String nome, int id, String marca, double custo) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.custo = custo;
        this.dao = new FerramentaDAO(); // inicializado não importa em qual construtor
    }

    // Métodos GET e SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    // Override necessário para poder retornar os dados de Ferramentas no toString.
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n marca: " + this.getMarca()
                + "\n custo: " + this.getCusto();
    }

    // Retorna a Lista de Ferramentas(objetos)
    public ArrayList getMinhaLista() {
        return dao.getMinhaLista();
    }

    // Cadastra nova ferramenta
    public boolean InsertFerramentaBD(String nome, String marca, double custo) throws SQLException {
        int id = this.maiorID() + 1;
        Ferramentas objeto = new Ferramentas(nome, id, marca, custo);
        dao.InsertFerramentaBD(objeto);
        return true;

    }

    // Deleta uma ferramenta específica pelo seu campo ID
    public boolean DeleteFerramentaBD(int id) {
        dao.DeleteFerramentaBD(id);
        return true;
    }

    // Edita uma ferramenta específica pelo seu campo ID
    public boolean UpdateFerramentaBD(String nome, int id, String marca, double custo) {
        Ferramentas objeto = new Ferramentas(nome, id, marca, custo);
        dao.UpdateFerramentaBD(objeto);
        return true;
    }

    // carrega dados de uma ferramenta específica pelo seu ID
    public Ferramentas carregaFerramenta(int id) {
        dao.carregaFerramenta(id);
        return null;
    }

    // retorna o maior ID da nossa base de dados
    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
}
