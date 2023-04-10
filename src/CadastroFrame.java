import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import database.ConnectionFactory;

public class CadastroFrame extends JFrame {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel companyLabel;
    private JTextField companyField;
    private JLabel contactLabel;
    private JTextField contactField;
    private JButton saveButton;
    private JButton cancelButton;

    private ConnectionFactory connectionFactory;
	private Connection conn;

	public CadastroFrame(Connection conn) throws SQLException {
	    this.conn = conn;
	    initComponents();
	}


    private void initComponents() {
        nameLabel = new JLabel("Nome:");
        nameField = new JTextField(20);
        phoneLabel = new JLabel("Telefone:");
        phoneField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        companyLabel = new JLabel("Empresa:");
        companyField = new JTextField(20);
        contactLabel = new JLabel("Meio de contato:");
        contactField = new JTextField(20);
        saveButton = new JButton("Salvar");
        cancelButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(companyLabel);
        panel.add(companyField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
        setTitle("Cadastro de Recrutador");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        saveButton.addActionListener(e -> {
            if (insertRecrutador()) {
                JOptionPane.showMessageDialog(null, "Recrutador cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o recrutador.");
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }

    private boolean insertRecrutador() {
        String sql = "INSERT INTO recrutadores (dataContato, nome, telefone, email, empresa, meioDeContato) VALUES (?, ?, ?, ?, ?, ?)";

        if (this.conn == null) {
            System.out.println("A conexão com o banco de dados não foi estabelecida corretamente.");
        }

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(2, nameField.getText());
            stmt.setString(3, phoneField.getText());
            stmt.setString(4, emailField.getText());
            stmt.setString(5, companyField.getText());
            stmt.setString(6, contactField.getText());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
