package myrh;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import database.ConnectionFactory;

public class PesquisaFrame extends JFrame {

    private JPanel panel;
    private JTextField pesquisaField;
    private JButton pesquisaButton;
    private JTable tabela;
    private Connection connection;

    public PesquisaFrame(Connection connection) {
        this.connection = connection;
        initComponents();
    }

	private void initComponents() {
        panel = new JPanel(new BorderLayout());
        pesquisaField = new JTextField();
        pesquisaButton = new JButton("Pesquisar");
        tabela = new JTable(new DefaultTableModel());

        pesquisaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pesquisar();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(pesquisaField, BorderLayout.NORTH);
        panel.add(pesquisaButton, BorderLayout.EAST);
        panel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        add(panel);
        setTitle("MYRH - Pesquisa de Recrutadores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void pesquisar() throws SQLException {
        String pesquisa = pesquisaField.getText();
        String sql = "SELECT * FROM recrutador WHERE nome LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + pesquisa + "%");
        ResultSet resultSet = statement.executeQuery();

        List<Object[]> dados = new ArrayList<>();
        while (resultSet.next()) {
            Object[] linha = new Object[3];
            linha[0] = resultSet.getInt("id");
            linha[1] = resultSet.getString("nome");
            linha[2] = resultSet.getString("email");
            dados.add(linha);
        }

        String[] colunas = {"ID", "Nome", "E-mail"};
        DefaultTableModel model = new DefaultTableModel(dados.toArray(new Object[dados.size()][]), colunas);
        tabela.setModel(model);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        new PesquisaFrame(connection);
    }
}
