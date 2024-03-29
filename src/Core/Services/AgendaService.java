package Core.Services;

import Core.Entities.Contato;
import Core.Entities.Telefone;

import java.util.ArrayList;
import java.util.List;

public class AgendaService {
    private List<Contato> listaDeContatos = new ArrayList<>();
    public void CriarContato(Contato novoContato){
        for (Contato contato : listaDeContatos) {
            if (contato.getCodigo() == novoContato.getCodigo()) {
                return;
            }
        }

        listaDeContatos.add(novoContato);
    }

    public void ExcluirContato(Contato contatoParaExcluir){
        listaDeContatos.remove(contatoParaExcluir);
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
