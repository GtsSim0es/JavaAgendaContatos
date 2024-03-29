package Core.Entities;

public class Telefone {
    private final String ddd;
    private final String numero;
    private final String descricao;

    public Telefone(String ddd, String numero, String descricao) {
        this.ddd = ddd;
        this.numero = numero;
        this.descricao = descricao;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }
}

