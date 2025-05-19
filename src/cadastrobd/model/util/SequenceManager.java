package cadastrobd.model.util;

import java.sql.*;

public class SequenceManager {

    public static int getValue(String sequenceName) {
        int valor = -1;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConectorBD.getConnection();
            stmt = con.prepareStatement("SELECT NEXT VALUE FOR " + sequenceName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter valor da sequência: " + e.getMessage());
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }

        return valor;
    }
}
