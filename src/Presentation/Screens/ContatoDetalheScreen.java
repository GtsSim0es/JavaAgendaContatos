
package Presentation.Screens;

import Core.Entities.Contato;
import Core.Services.AgendaService;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContatoDetalheScreen extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private JTextField codigoField;
    private JTextField nomeField;
    private AgendaService agendaService;

    public ContatoDetalheScreen(Contato contato) {
        agendaService = new AgendaService();
        definirConfiguracoesDeJanela();

        setVisible(true);
    }
    private void definirConfiguracoesDeJanela(){
        setTitle("Agenda de Contatos - Detalhes do Contato");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        definirConfiguracoesDeTabela();
        definirConfiguracoesDeMenu();
    }
    private void definirConfiguracoesDeTabela(){
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Codigo");
        model.addColumn("Nome");

        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    private void definirConfiguracoesDeMenu(){
    }

}




