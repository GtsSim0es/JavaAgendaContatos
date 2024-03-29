package Core.Entities;

public class PessoaFisica extends Contato {
    private final String cpf;
    public PessoaFisica(long codigo, String nome, String cpf) {
        super(codigo, nome);
        this.cpf = cpf;
    }

    @Override
    public String getDocumentoDeCadastro(){
        return cpf;
    }
}
