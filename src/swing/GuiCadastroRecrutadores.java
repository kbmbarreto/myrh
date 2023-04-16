package swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.RecrutadoresDAO;

public class GuiCadastroRecrutadores extends JFrame {
	
	JLabel lblCodigo, lblDataContato, lblNome, lblTelefone, lblEmail, lblEmpresa, lblMeioDeContato;
	JButton btnGravar, btnAlterar, btnExcluir, btnNovo, btnLocalizar, btnCancelar, btnSair;
	static JTextField txtCodigo, txtDataContato, txtNome, txtTelefone, txtEmail, txtEmpresa, txtMeioDeContato;
	private RecrutadoresDAO recrutadores;
	
	
	public static void main(String[] args) {
		JFrame janela = new GuiCadastroRecrutadores();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
	
	public GuiCadastroRecrutadores() {
		inicializarComponentes();
		definirEventos();
	}
	
	public void inicializarComponentes() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		setTitle("Cadastro de Recrutadores");
		setBounds( 300, 200, 800, 600);
		setLocationRelativeTo(null);
		lblCodigo = new JLabel("Código");
		lblDataContato = new JLabel("Data do contato");
		lblNome = new JLabel("Nome");
		lblTelefone = new JLabel("Telefone");
		lblEmail = new JLabel("E-mail");
		lblEmpresa = new JLabel("Empresa");
		lblMeioDeContato = new JLabel("Meio de contato");
		txtCodigo = new JTextField(10);
		txtDataContato = new JTextField(20);
		txtNome = new JTextField(20);
		txtTelefone = new JTextField(20);
		txtEmail = new JTextField(35);
		txtEmpresa = new JTextField(35);
		txtMeioDeContato = new JTextField(15);
		btnGravar = new JButton("Gravar");
		btnAlterar = new JButton("Editar");
		btnExcluir = new JButton("Excluir");
		btnNovo = new JButton("Novo");
		btnLocalizar = new JButton("Pesquisar");
		btnCancelar = new JButton("Cancelar");
		btnSair = new JButton("Sair");
		btnSair.setToolTipText("Sair");
	
		
		getContentPane().add(lblCodigo);
		getContentPane().add(txtCodigo);
		getContentPane().add(lblDataContato);
		getContentPane().add(txtDataContato);
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(lblTelefone);
		getContentPane().add(txtTelefone);
		getContentPane().add(lblEmail);
		getContentPane().add(txtEmail);
		getContentPane().add(lblEmpresa);
		getContentPane().add(txtEmpresa);
		getContentPane().add(lblMeioDeContato);
		getContentPane().add(txtMeioDeContato);
		getContentPane().add(btnGravar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnExcluir);
		getContentPane().add(btnNovo);
		getContentPane().add(btnLocalizar);
		getContentPane().add(btnCancelar);
		getContentPane().add(btnSair);
		
		setResizable(false);
		setBotoes(true, true, false, false, false, false);
		recrutadores =new RecrutadoresDAO();
		
		if (!recrutadores.connectionFactory.getConnection()) {
			JOptionPane.showMessageDialog(null, "connection failure, the system will be shut down!");
			System.exit(0);
		}
	}
	
	public void definirEventos() {
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recrutadores.connectionFactory.close();
				System.exit(0);
			}
		});
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				setBotoes(false, false, true, false, false, true);
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		
		btnGravar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo Código não pode ser vazio!");
					txtCodigo.requestFocus();
					return;
				}
				
				if (txtDataContato.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo Data de contato não pode ser vazio!");
					txtDataContato.requestFocus();
					return;
				}
				
				if (txtNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo Nome não pode ser vazio!");
					txtNome.requestFocus();
					return;
				}
				
				if (txtEmpresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo Empresa não pode ser vazia!");
					txtEmpresa.requestFocus();
					return;
				}
				
				if (txtMeioDeContato.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo Meio de contato não pode ser vazio!");
					txtMeioDeContato.requestFocus();
					return;
				}
				
				recrutadores.recrutador.setId(txtCodigo.getText());
				recrutadores.recrutador.setData_contato(txtDataContato.getText());
				recrutadores.recrutador.setNome(txtNome.getText());
				recrutadores.recrutador.setTelefone(txtTelefone.getText());
				recrutadores.recrutador.setEmail(txtEmail.getText());
				recrutadores.recrutador.setEmpresa(txtEmpresa.getText());
				recrutadores.recrutador.setMeio_de_contato(txtMeioDeContato.getText());
				
				JOptionPane.showMessageDialog(null, recrutadores.update(RecrutadoresDAO.CREATE));
				limparCampos();
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recrutadores.recrutador.setId(txtCodigo.getText());
				recrutadores.recrutador.setData_contato(txtDataContato.getText());
				recrutadores.recrutador.setNome(txtNome.getText());
				recrutadores.recrutador.setTelefone(txtTelefone.getText());
				recrutadores.recrutador.setEmail(txtEmail.getText());
				recrutadores.recrutador.setEmpresa(txtEmpresa.getText());
				recrutadores.recrutador.setMeio_de_contato(txtMeioDeContato.getText());
				JOptionPane.showMessageDialog(null, recrutadores.update(RecrutadoresDAO.UPDATE));
				limparCampos();
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recrutadores.recrutador.setId(txtCodigo.getText());
				recrutadores.locate();
				
				int n = JOptionPane.showConfirmDialog(null,  recrutadores.recrutador.getNome(),
						" Excluir o recrutador? ", JOptionPane.YES_NO_OPTION);
				
				if (n == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null,  recrutadores.update(RecrutadoresDAO.DELETE));
					limparCampos();
				}
			}
		});
		
		btnLocalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarCampos();
			}
		});
	}
	
	public void limparCampos() {
		txtCodigo.setText("");
		txtDataContato.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtEmpresa.setText("");
		txtMeioDeContato.setText("");
		txtCodigo.requestFocus();
		setBotoes(true, true, false, false, false, false);
	}
	
	public void atualizarCampos() {
		recrutadores.recrutador.setId(txtCodigo.getText());
		
		if (recrutadores.locate()) {
			txtCodigo.setText(recrutadores.recrutador.getId());
			txtDataContato.setText(recrutadores.recrutador.getData_contato());
			txtNome.setText(recrutadores.recrutador.getNome());
			txtTelefone.setText(recrutadores.recrutador.getTelefone());
			txtEmail.setText(recrutadores.recrutador.getEmail());
			txtEmpresa.setText(recrutadores.recrutador.getEmpresa());
			txtMeioDeContato.setText(recrutadores.recrutador.getMeio_de_contato());
			setBotoes(true, true, false, true, true, true);
		} else {
			JOptionPane.showMessageDialog(null, "Recrutador não encontrado.");
			limparCampos();
		}
	}
	
	public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, boolean bAlterar, boolean bExcluir, boolean bCancelar) {
		btnNovo.setEnabled(bNovo);
		btnLocalizar.setEnabled(bLocalizar);
		btnGravar.setEnabled(bGravar);
		btnAlterar.setEnabled(bAlterar);
		btnExcluir.setEnabled(bExcluir);
		btnCancelar.setEnabled(bCancelar);
	}
}
