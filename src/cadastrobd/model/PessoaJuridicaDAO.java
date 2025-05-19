package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;

public class PessoaJuridicaDAO {

    public Pessoa getPessoa(int id) {
        PessoaJuridica pj = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConectorBD.getConnection();
            stmt = con.prepareStatement("SELECT * FROM Pessoa WHERE id_pessoa = ? AND tipo = 'J'");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pj = new PessoaJuridica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa jurídica: " + e.getMessage());
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }

        return pj;
    }

    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConectorBD.getConnection();
            stmt = con.prepareStatement("SELECT * FROM Pessoa WHERE tipo = 'J'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                lista.add(pj);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pessoas jurídicas: " + e.getMessage());
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }

        return lista;
    }

    public void incluir(Pessoa p) {
        PessoaJuridica pj = (PessoaJuridica) p;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            int id = SequenceManager.getValue("seq_id_pessoa");
            con = ConectorBD.getConnection();

            stmt = con.prepareStatement(
                "INSERT INTO Pessoa (id_pessoa, nome, endereco, telefone, email, tipo, cpf, cnpj) " +
                "VALUES (?, ?, ?, ?, ?, 'J', NULL, ?)"
            );
            stmt.setInt(1, id);
            stmt.setString(2, pj.getNome());
            stmt.setString(3, pj.getEndereco());
            stmt.setString(4, pj.getTelefone());
            stmt.setString(5, pj.getEmail());
            stmt.setString(6, pj.getCnpj());
            stmt.executeUpdate();

            pj.setId(id);

        } catch (SQLException e) {
            System.out.println("Erro ao incluir pessoa jurídica: " + e.getMessage());
        } finally {
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }
    }

    public void alterar(Pessoa p) {
        PessoaJuridica pj = (PessoaJuridica) p;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConectorBD.getConnection();

            stmt = con.prepareStatement(
                "UPDATE Pessoa SET nome = ?, endereco = ?, telefone = ?, email = ?, cnpj = ? " +
                "WHERE id_pessoa = ? AND tipo = 'J'"
            );
            stmt.setString(1, pj.getNome());
            stmt.setString(2, pj.getEndereco());
            stmt.setString(3, pj.getTelefone());
            stmt.setString(4, pj.getEmail());
            stmt.setString(5, pj.getCnpj());
            stmt.setInt(6, pj.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao alterar pessoa jurídica: " + e.getMessage());
        } finally {
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }
    }

    public void excluir(int id) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConectorBD.getConnection();

            stmt = con.prepareStatement("DELETE FROM Pessoa WHERE id_pessoa = ? AND tipo = 'J'");
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pessoa jurídica: " + e.getMessage());
        } finally {
            ConectorBD.close(stmt);
            ConectorBD.close(con);
        }
    }
}
