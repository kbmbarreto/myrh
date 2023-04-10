package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {
    private Connection connection;

    public CadastroDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserirRecrutador(int id, String dataContato, String nome, String telefone, String email, String empresa, String meioDeContato) {
        String sql = "INSERT INTO recrutadores (id, data_contato, nome, telefone, email, empresa, meio_de_contato) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, dataContato);
            stmt.setString(3, nome);
            stmt.setString(4, telefone);
            stmt.setString(5, email);
            stmt.setString(6, empresa);
            stmt.setString(7, meioDeContato);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
