package Core.Entities;

import java.util.ArrayList;
import java.util.List;

public class Contato {
    private final long codigo;
    private final String nome;
    private List<Telefone> listaTelefones = new ArrayList<>();
    private final Endereco endereco;;

    public Contato(long codigo, String nome, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    public long getCodigo(){
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public Endereco getEndereco(){
        return endereco;
    }

    public List<Telefone> getListaTelefones(){
        return listaTelefones;
    }
}
