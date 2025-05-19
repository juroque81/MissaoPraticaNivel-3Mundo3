package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;

public class PessoaFisicaDAO {
    
    public Pessoa getPessoa(int id) {
        Pessoa pessoa = null;
        try {
            Connection con = ConectorBD.getConnection();
            String sql = "SELECT * FROM Pessoa WHERE id_pessoa = ? AND tipo = 'F'";
            PreparedStatement ps = ConectorBD.getPrepared(con, sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaFisica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }

            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(con);

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pessoa física: " + e.getMessage());
        }
        return pessoa;
    }

    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        try {
            Connection con = ConectorBD.getConnection();
            String sql = "SELECT * FROM Pessoa WHERE tipo = 'F'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PessoaFisica pf = new PessoaFisica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                lista.add(pf);
            }

            ConectorBD.close(rs);
            ConectorBD.close(st);
            ConectorBD.close(con);

        } catch (SQLException e) {
            System.out.println("Erro ao listar pessoas físicas: " + e.getMessage());
        }
        return lista;
    }

    public void incluir(Pessoa p) {
        try {
            PessoaFisica pf = (PessoaFisica) p;
            Connection con = ConectorBD.getConnection();
            String sql = "INSERT INTO Pessoa (id_pessoa, nome, endereco, telefone, email, tipo, cpf, cnpj) VALUES (?, ?, ?, ?, ?, 'F', ?, NULL)";
            PreparedStatement ps = ConectorBD.getPrepared(con, sql);
            int id = SequenceManager.getValue("seq_id_pessoa");
            ps.setInt(1, id);
            ps.setString(2, pf.getNome());
            ps.setString(3, pf.getEndereco());
            ps.setString(4, pf.getTelefone());
            ps.setString(5, pf.getEmail());
            ps.setString(6, pf.getCpf());
            ps.executeUpdate();

            ConectorBD.close(ps);
            ConectorBD.close(con);

            pf.setId(id);

        } catch (SQLException e) {
            System.out.println("Erro ao incluir pessoa física: " + e.getMessage());
        }
    }

    public void alterar(Pessoa p) {
        try {
            PessoaFisica pf = (PessoaFisica) p;
            Connection con = ConectorBD.getConnection();
            String sql = "UPDATE Pessoa SET nome=?, endereco=?, telefone=?, email=?, cpf=? WHERE id_pessoa=? AND tipo='F'";
            PreparedStatement ps = ConectorBD.getPrepared(con, sql);
            ps.setString(1, pf.getNome());
            ps.setString(2, pf.getEndereco());
            ps.setString(3, pf.getTelefone());
            ps.setString(4, pf.getEmail());
            ps.setString(5, pf.getCpf());
            ps.setInt(6, pf.getId());
            ps.executeUpdate();

            ConectorBD.close(ps);
            ConectorBD.close(con);

        } catch (SQLException e) {
            System.out.println("Erro ao alterar pessoa física: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        try {
            Connection con = ConectorBD.getConnection();
            String sql = "DELETE FROM Pessoa WHERE id_pessoa = ? AND tipo = 'F'";
            PreparedStatement ps = ConectorBD.getPrepared(con, sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            ConectorBD.close(ps);
            ConectorBD.close(con);

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pessoa física: " + e.getMessage());
        }
    }
}