package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;

public class RecrutadoresDAO {
	
	public Recrutadores recrutador;
	public ConnectionFactory connectionFactory;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte CREATE = 1;
	public static final byte UPDATE = 2;
	public static final byte DELETE = 3;
	
	private List<Recrutadores> listaRecrutadores;
	
	public RecrutadoresDAO() {
		connectionFactory = new ConnectionFactory();
		recrutador = new Recrutadores();
		this.listaRecrutadores = new ArrayList<>();
	}
	
	public boolean locate() {
		sql = "select * from recrutadores where id = ?";
		
		try {
			statement = connectionFactory.connection.prepareStatement(sql); // alteração aqui
			statement.setString(1, recrutador.getId());
			resultSet = statement.executeQuery();
			resultSet.next();
			recrutador.setId(resultSet.getString(1));
			recrutador.setData_contato(resultSet.getString(2));
			recrutador.setNome(resultSet.getString(3));
			recrutador.setTelefone(resultSet.getString(4));
			recrutador.setEmail(resultSet.getString(5));
			recrutador.setEmpresa(resultSet.getString(6));
			recrutador.setMeio_de_contato(resultSet.getString(7));
			return true;
			
		} catch (SQLException ex) {
			return false;
		}
	}
	
	public String update(int operation) {
		men = "Operation completed!";
		
		try {
			if (operation == CREATE) {
				sql = "insert into recrutadores values (?, ?, ?, ?, ?, ?, ?)";
				statement = connectionFactory.connection.prepareStatement(sql); // alteração aqui
				statement.setString(1, recrutador.getId());
				statement.setString(2,recrutador.getData_contato());
				statement.setString(3,recrutador.getNome());
				statement.setString(4,recrutador.getTelefone());
				statement.setString(5,recrutador.getEmail());
				statement.setString(6,recrutador.getEmpresa());
				statement.setString(7,recrutador.getMeio_de_contato());
				
			} else if (operation == UPDATE) {
				sql = "update recrutadores set data_contato = ?, nome = ?, telefone = ?, email = ?, empresa = ?, meio_de_contato = ? where id = ?";
				statement = connectionFactory.connection.prepareStatement(sql); // alteração aqui
				statement.setString(7, recrutador.getId());
				statement.setString(1,recrutador.getData_contato());
				statement.setString(2,recrutador.getNome());
				statement.setString(3,recrutador.getTelefone());
				statement.setString(4,recrutador.getEmail());
				statement.setString(5,recrutador.getEmpresa());
				statement.setString(6,recrutador.getMeio_de_contato());
				
			} else if (operation == DELETE) {
				sql = "delete from recrutadores where id = ?";
				statement = connectionFactory.connection.prepareStatement(sql); // alteração aqui
				statement.setString(1, recrutador.getId());
			}
			
			if (statement.executeUpdate() == 0) {
				men = "Failed in the operation!";
			}
			
		} catch (SQLException ex) {
			men = "Failed in the operation " + ex.toString();
		}
		
		return men;
	}
}