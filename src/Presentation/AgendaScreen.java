package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgendaScreen extends JFrame {

    private JTable table;

    public AgendaScreen() {
        setTitle("Agenda");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando uma tabela com dados de exemplo
        String[] colunas = {"Nome", "Telefone"};
        Object[][] dados = {
                {"João", "(11) 1234-5678"},
                {"Maria", "(22) 9876-5432"}
        };
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        table = new JTable(model);

        // Adicionando a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        setVisible(true);
    }
}
