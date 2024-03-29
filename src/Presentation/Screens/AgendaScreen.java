package Presentation.Screens;

import Core.Entities.Contato;
import Core.Entities.PessoaFisica;
import Core.Entities.PessoaJuridica;
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
    private JTextField documentoDeCadastro;
    private JComboBox<String> tipoPessoaComboBox;
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
        model.addColumn("Documento De Cadastro");

        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    private void definirConfiguracoesDeMenu(){
        JPanel botoesPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        codigoField = new JTextField(15);
        nomeField = new JTextField(15);
        documentoDeCadastro = new JTextField(15);
        JButton adicionarButton = new JButton("Adicionar");
        JButton excluirButton = new JButton("Excluir");
        JButton verContatoButton = new JButton("Ver Contato");

        adicionarButton.addActionListener(this::adicionarLinha);
        excluirButton.addActionListener(this::excluirLinha);
        verContatoButton.addActionListener(this::verContato);

        botoesPanel.add(new JLabel("Tipo de Pessoa:"));
        tipoPessoaComboBox = new JComboBox<>(new String[]{"Pessoa Física", "Pessoa Jurídica"});
        botoesPanel.add(tipoPessoaComboBox);

        botoesPanel.add(new JLabel("Documento de Cadastro (CPF/CNPJ):"));
        botoesPanel.add(documentoDeCadastro);

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
            String documento = documentoDeCadastro.getText();

            if (!nome.isEmpty() && codigo != 0) {
                Contato novoContato = null;

                if("Pessoa Física".equals(tipoPessoaComboBox.getSelectedItem()))
                    novoContato = new PessoaFisica(codigo, nome, documento);
                else
                    novoContato = new PessoaJuridica(codigo, nome, documento);

                agendaService.CriarContato(novoContato);
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            }

            preencherOuAtualizarTabela();
        } catch (NumberFormatException ex) {
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
            model.addRow(new Object[]{contato.getCodigo(), contato.getNome(), contato.getDocumentoDeCadastro()});
        }
    }
}




