import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame {

    private JPanel panel;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private static Connection conn;

    public MyFrame(Connection conn) {
        MyFrame.conn = conn;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel();
        cadastrarButton = new JButton("Cadastrar");
        pesquisarButton = new JButton("Pesquisar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					CadastroFrame cadastroFrame = new CadastroFrame(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });


        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de pesquisa
            }
        });

        panel.add(cadastrarButton);
        panel.add(pesquisarButton);

        add(panel);
        setTitle("MYRH - Sistema de Cadastro de Recrutadores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame(conn);
    }
}
