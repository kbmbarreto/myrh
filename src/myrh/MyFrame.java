package myrh;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import database.ConnectionFactory;

public class MyFrame extends JFrame {

    private JPanel panel;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private Connection connection;

    public MyFrame(Connection connection) {
        this.connection = connection;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        cadastrarButton = new JButton("Cadastrar");
        pesquisarButton = new JButton("Pesquisar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CadastroFrame cadastroFrame = new CadastroFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	PesquisaFrame pesquisaFrame = new PesquisaFrame(connection);
                } catch (Exception ex) {
					ex.printStackTrace();
				}
            }
        });

        // Adiciona o título centralizado
        JLabel titleLabel = new JLabel("Banco de dados de recrutadores e candidaturas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 2;
        titleConstraints.insets = new Insets(10, 10, 20, 10);
        panel.add(titleLabel, titleConstraints);

        // Adiciona os botões com um espaçamento
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(0, 10, 0, 10);
        panel.add(cadastrarButton, buttonConstraints);

        buttonConstraints.gridx = 1;
        panel.add(pesquisarButton, buttonConstraints);

        add(panel);
        setTitle("MYRH - Sistema de Cadastro de Recrutadores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        new MyFrame(connection);
    }
}