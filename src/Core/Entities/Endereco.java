package Core.Entities;

public class Endereco {
    private String logradouro;
    private int numero;
    private String cidade;
    private String bairro;
    private String estado;
    private String cep;
    public Endereco(String logradouro, int numero, String cidade, String bairro, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.estado = estado;
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public int getNumero() {
        return numero;
    }
    public String getCidade() {
        return cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public String getEstado() {
        return estado;
    }
    public String getCep() {
        return cep;
    }
}