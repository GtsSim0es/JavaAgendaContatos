package Presentation.Screens;

import Core.Entities.Contato;
import Core.Entities.Endereco;
import Core.Services.AgendaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgendaScreen extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private JTextField codigoField;
    private JTextField nomeField;
    private AgendaService agendaService;

    public AgendaScreen() {
        agendaService = new AgendaService();
        definirConfiguracoesDeJanela();

        setVisible(true);
    }
    private void definirConfiguracoesDeJanela(){
        setTitle("Agenda de Contatos");
        setSize(1200, 780);
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
        JPanel botoesPanel = new JPanel();
        codigoField = new JTextField(15);
        nomeField = new JTextField(15);
        JButton adicionarButton = new JButton("Adicionar");
        JButton excluirButton = new JButton("Excluir");
        JButton verContatoButton = new JButton("Ver Contato");

        adicionarButton.addActionListener(this::adicionarLinha);
        excluirButton.addActionListener(this::excluirLinha);
        verContatoButton.addActionListener(this::verContato);

        botoesPanel.add(new JLabel("Codigo:"));
        botoesPanel.add(codigoField);

        botoesPanel.add(new JLabel("Nome:"));
        botoesPanel.add(nomeField);

        botoesPanel.add(adicionarButton);
        botoesPanel.add(excluirButton);
        botoesPanel.add(verContatoButton);

        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }

    private void adicionarLinha(ActionEvent e) {
        try {
            long codigo = Long.parseLong(codigoField.getText());
            String nome = nomeField.getText();

            if (!nome.isEmpty() && codigo != 0) {
                Contato novoContato = new Contato(codigo, nome, new Endereco());
                agendaService.CriarContato(novoContato);
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            }

            preencherOuAtualizarTabela();
        } catch (NumberFormatException ex) {
            // Exibe um diálogo de erro informando que o campo "codigo" só pode conter números.
            JOptionPane.showMessageDialog(this, "O campo \"Código\" só pode conter números.");
        }
    }
    private void excluirLinha(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            long codigo = (long) model.getValueAt(linhaSelecionada, 0);
            var contatoAtual = agendaService.ConsultarContato(codigo);

            agendaService.ExcluirContato(contatoAtual);

            preencherOuAtualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }
    private void verContato(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            long codigo = (long) model.getValueAt(linhaSelecionada, 0);
            var contatoAtual = agendaService.ConsultarContato(codigo);

            SwingUtilities.invokeLater(() -> new ContatoDetalheScreen(contatoAtual));

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para ver o contato.");
        }
    }
    private void preencherOuAtualizarTabela(){
        model.setNumRows(0);

        var contatosLista = agendaService.BuscarListaContatosAgenda();

        for (Contato contato : contatosLista){
            model.addRow(new Object[]{contato.getCodigo(), contato.getNome()});
        }
    }





}




