package Core.Services;

import Core.Entities.Contato;
import Core.Entities.Telefone;

import java.util.List;

public class AgendaService {
    private List<Contato> listaDeContatos;

    public void CriarContato(Contato novoContato){
        listaDeContatos.add(novoContato);
    }

    public void ExcluirContato(Contato contatoParaExcluir){
        listaDeContatos.remove(contatoParaExcluir);
    }

    public void AdicionarTelefoneAoContato(long codigoContato, Telefone telefone){
        Contato contato = ConsultarContato(codigoContato);
        if (contato != null) {
            contato.getListaTelefones().add(telefone);
        }
    }

    public void ExcluirTelefoneAoContato(long codigoContato, Telefone telefone){
        Contato contato = ConsultarContato(codigoContato);
        if (contato != null) {
            contato.getListaTelefones().remove(telefone);
        }
    }

    public Contato ConsultarContato(long codigo){
        for (Contato contato : listaDeContatos) {
            if (contato.getCodigo() == codigo) {
                return contato;
            }
        }

        return null;
    }

    public List<Contato> BuscarListaContatosAgenda(){
        return listaDeContatos;
    }
}
