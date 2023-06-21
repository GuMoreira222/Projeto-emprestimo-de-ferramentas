package Model;

import java.util.*;
import DAO.AmigoDAO;
import java.sql.SQLException;

public class Amigo {

    // Atributos
    private String nome;
    private int id;
    private String telefone;
    private final AmigoDAO dao;

    // Método Construtor de Objeto Vazio
    public Amigo() {
        this.dao = new AmigoDAO(); // inicializado não importa em qual construtor
    }

    // Método Construtor de Objeto, inserindo dados
    public Amigo(String nome, int id, String telefone) {
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.dao = new AmigoDAO(); // inicializado não importa em qual construtor
    }

    // Métodos GET e SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Override necessário para poder retornar os dados de Amigo no toString.
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Telefone: " + this.getTelefone();
    }

    // Retorna a Lista de Amigos(objetos)
    public ArrayList getMinhaLista() {
        return dao.getMinhaLista();
    }

    // Cadastra novo amigo
    public boolean InsertAmigoBD(String nome, String telefone) throws SQLException {
        int id = this.maiorID() + 1;
        Amigo objeto = new Amigo(nome, id, telefone);
        dao.InsertAmigoBD(objeto);
        return true;

    }

    // Deleta um amigo específico pelo seu campo ID
    public boolean DeleteAmigoBD(int id) {
        dao.DeleteAmigoBD(id);
        return true;
    }

    // Edita um amigo específico pelo seu campo ID
    public boolean UpdateAmigoBD(String nome, int id, String telefone) {
        Amigo objeto = new Amigo(nome, id, telefone);
        dao.UpdateAmigoBD(objeto);
        return true;
    }

    // carrega dados de um amigo específico pelo seu ID
    public Amigo carregaAmigo(int id) {
        dao.carregaAmigo(id);
        return null;
    }

    // retorna o maior ID da nossa base de dados
    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
}
