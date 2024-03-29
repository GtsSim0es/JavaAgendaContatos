package Core.Entities;

public class PessoaJuridica extends Contato {
    private final String cnpj;
    public PessoaJuridica(long codigo, String nome, String cnpj) {
        super(codigo, nome);
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumentoDeCadastro(){
        return cnpj;
    }
}
