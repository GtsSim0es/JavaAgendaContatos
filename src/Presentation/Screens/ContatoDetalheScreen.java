
package Presentation.Screens;

import Core.Entities.Contato;
import Core.Entities.Endereco;
import Core.Entities.Telefone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContatoDetalheScreen extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private JTextField descricaoField;
    private JTextField dddField;
    private JTextField telefoneField;
    private Contato contato;
    private JTextField logradouroField;
    private JTextField numeroField;
    private JTextField cidadeField;
    private JTextField bairroField;
    private JTextField estadoField;
    private JTextField cepField;
    public ContatoDetalheScreen(Contato contato) {
        this.contato = contato;
        definirConfiguracoesDeJanela();
        preencherOuAtualizarTabela();

        setVisible(true);
    }
    private void definirConfiguracoesDeJanela(){
        setTitle("Agenda de Contatos - Detalhes do Contato");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel enderecoPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        definirConfiguracoesDeMenuEndereco(contato.getEndereco(), enderecoPanel);
        tabbedPane.addTab("Endereço", enderecoPanel);

        JPanel contatoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        definirConfiguracoesDeMenuContato(contatoPanel);
        tabbedPane.addTab("Contato", contatoPanel);

        getContentPane().add(tabbedPane, BorderLayout.NORTH);

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

        model.addColumn("Descrição");
        model.addColumn("DDD");
        model.addColumn("Numero");

        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    private void definirConfiguracoesDeMenu(){
        JPanel botoesPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        descricaoField = new JTextField(15);
        dddField = new JTextField(15);
        telefoneField = new JTextField(15);
        JButton adicionarButton = new JButton("Adicionar Numero");
        JButton excluirButton = new JButton("Excluir Numero");

        adicionarButton.addActionListener(this::adicionarLinha);
        excluirButton.addActionListener(this::excluirLinha);

        botoesPanel.add(new JLabel("Descrição:"));
        botoesPanel.add(descricaoField);

        botoesPanel.add(new JLabel("DDD:"));
        botoesPanel.add(dddField);

        botoesPanel.add(new JLabel("Telefone:"));
        botoesPanel.add(telefoneField);

        botoesPanel.add(adicionarButton);
        botoesPanel.add(excluirButton);

        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }
    private void definirConfiguracoesDeMenuContato(JPanel contatoPanel){
        var field1 = new JTextField(15);
        var field2 = new JTextField(15);

        field1.setText(Long.toString(contato.getCodigo()));
        field2.setText(contato.getNome());
        field1.setEditable(false);
        field2.setEditable(false);
        JButton editarEndereco = new JButton("Editar Endereco");

        contatoPanel.add(new JLabel("Codigo:"));
        contatoPanel.add(field1);

        contatoPanel.add(new JLabel("Nome:"));
        contatoPanel.add(field2);
    }
    private void definirConfiguracoesDeMenuEndereco(Endereco endereco, JPanel enderecoPanel){
        logradouroField = new JTextField(endereco.getLogradouro(), 15);
        numeroField = new JTextField(String.valueOf(endereco.getNumero()), 15);
        cidadeField = new JTextField(endereco.getCidade(), 15);
        bairroField = new JTextField(endereco.getBairro(), 15);
        estadoField = new JTextField(endereco.getEstado(), 15);
        cepField = new JTextField(endereco.getCep(), 15);

        logradouroField.setText(endereco.getLogradouro());
        numeroField.setText(Long.toString(endereco.getNumero()));
        cidadeField.setText(endereco.getCidade());
        bairroField.setText(endereco.getBairro());
        estadoField.setText(endereco.getEstado());
        cepField.setText(endereco.getCep());

        enderecoPanel.add(new JLabel("Logradouro:"));
        enderecoPanel.add(logradouroField);

        enderecoPanel.add(new JLabel("Numero:"));
        enderecoPanel.add(numeroField);

        enderecoPanel.add(new JLabel("Cidade:"));
        enderecoPanel.add(cidadeField);

        enderecoPanel.add(new JLabel("Bairro:"));
        enderecoPanel.add(bairroField);

        enderecoPanel.add(new JLabel("Estado:"));
        enderecoPanel.add(estadoField);

        enderecoPanel.add(new JLabel("CEP:"));
        enderecoPanel.add(cepField);

        enderecoPanel.add(new JLabel("Salvar Alterações do Endereço:"));
        JButton salvarButton = new JButton("Salvar Informações");
        salvarButton.addActionListener(this::salvarEndereco);

        enderecoPanel.add(salvarButton);
    }
    private void adicionarLinha(ActionEvent e) {
        try {
            String descricao = descricaoField.getText();
            String ddd = dddField.getText();
            String telefone = telefoneField.getText();

            if (!descricao.isEmpty() && !ddd.isEmpty() && !telefone.isEmpty()) {
                var novoTelefone = new Telefone(descricao, ddd, telefone);

                var listaTelefones = contato.getListaTelefones();
                listaTelefones.add(novoTelefone);
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
            String numero = (String) model.getValueAt(linhaSelecionada, 1);
            var numeroAtual = contato.getTelefoneFromList(numero);

            contato.getListaTelefones().remove(numeroAtual);

            preencherOuAtualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }
    private void salvarEndereco(ActionEvent e) {
        String logradouro = logradouroField.getText();
        String numeroString = numeroField.getText();
        int numero = Integer.parseInt(numeroString);
        String cidade = cidadeField.getText();
        String bairro = bairroField.getText();
        String estado = estadoField.getText();
        String cep = cepField.getText();

        var novoEndereco = new Endereco(logradouro, numero, cidade, bairro, estado, cep);
        contato.setEndereco(novoEndereco);

        JOptionPane.showMessageDialog(this, "Endereço salvo com sucesso!");
    }
    private void preencherOuAtualizarTabela(){
        model.setNumRows(0);

        var listaTelefones = contato.getListaTelefones();
        for (Telefone telefone : listaTelefones){
            model.addRow(new Object[]{ telefone.getDdd(), telefone.getNumero(), telefone.getDescricao()});
        }
    }
}




