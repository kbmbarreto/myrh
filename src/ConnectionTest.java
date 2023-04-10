

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;

public class ConnectionTest {

    public static void main(String[] args) {
        try {
            Connection conn = null;
			try {
				conn = ConnectionFactory.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Conexão estabelecida com sucesso!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer a conexão com o banco de dados: " + e.getMessage());
        }
    }

}