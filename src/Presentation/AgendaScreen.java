package Presentation;

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
        model = new DefaultTableModel();
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

        adicionarButton.addActionListener(this::adicionarLinha);
        excluirButton.addActionListener(this::excluirLinha);

        botoesPanel.add(new JLabel("Codigo:"));
        botoesPanel.add(codigoField);

        botoesPanel.add(new JLabel("Nome:"));
        botoesPanel.add(nomeField);

        botoesPanel.add(adicionarButton);
        botoesPanel.add(excluirButton);

        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }

    private void adicionarLinha(ActionEvent e) {
        long codigo = Long.parseLong(codigoField.getText());
        String nome = nomeField.getText();

        if (!nome.isEmpty() && codigo != 0) {
            Contato novoContato = new Contato(codigo, nome, new Endereco());
            agendaService.CriarContato(novoContato);
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }

        preencherOuAtualizarTabela();
    }
    private void excluirLinha(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            model.removeRow(linhaSelecionada);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }
    private void preencherOuAtualizarTabela(){
        var contatosLista = agendaService.BuscarListaContatosAgenda();

        for (Contato contato : contatosLista){

            String[] novaLinha = {Long.toString(contato.getCodigo()), contato.getNome()};

            model.addRow(novaLinha);
        }
    }
}
