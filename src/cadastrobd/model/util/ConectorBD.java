package cadastrobd.model.util;

import java.sql.*;

public class ConectorBD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Método para obter PreparedStatement a partir de uma conexão
    public static PreparedStatement getPrepared(Connection con, String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    // Método adicional para uso rápido (abre uma conexão interna)
    public static PreparedStatement getPrepared(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    // Método para executar SELECT diretamente (abre conexão internamente)
    public static ResultSet getSelect(String sql) throws SQLException {
        PreparedStatement ps = getPrepared(sql);
        return ps.executeQuery();
    }

    // Métodos de fechamento seguros

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar Connection: " + e.getMessage());
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
        }
    }

    public static void close(Statement st) {
        try {
            if (st != null && !st.isClosed()) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar Statement: " + e.getMessage());
        }
    }
}
