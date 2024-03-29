package Core.Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Contato {
    private final long codigo;
    private final String nome;
    private List<Telefone> listaTelefones = new ArrayList<>();
    private Endereco endereco = new Endereco("",0,"", "", "", "");;

    public Contato(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public abstract String getDocumentoDeCadastro();

    public long getCodigo(){
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public Endereco getEndereco(){
        return endereco;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    public List<Telefone> getListaTelefones(){
        return listaTelefones;
    }

    public Telefone getTelefoneFromList(String telefone){
            for (Telefone telefoneAtual : getListaTelefones()) {
                if (telefoneAtual.getNumero().equals(telefone)) {
                    return telefoneAtual;
                }
            }

            return null;
    }
}
