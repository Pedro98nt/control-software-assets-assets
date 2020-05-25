package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.BenPedro;
import br.com.faculdadedelta.modelo.DepartamentoPedro;

public class BenDaoPedro {

	public void incluir(BenPedro benPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO bens(nome_bem,especificacao_bem,id_departamento,valor_bem)"
				+ "VALUES(?,?,?,?)  ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, benPedro.getNome().trim());
			ps.setString(2, benPedro.getEspecificacao().trim());
			ps.setLong(3, benPedro.getDerpartamento().getId());
			ps.setDouble(4, benPedro.getValor());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(BenPedro benPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bens" + " SET nome_bem=?," + "especificacao_bem =?," + "id_departamento=?,"
				+ "valor_bem=?," + " WHERE id_bem=?";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, benPedro.getNome().trim());
			ps.setString(2, benPedro.getEspecificacao().trim());
			ps.setLong(3, benPedro.getDerpartamento().getId());
			ps.setDouble(4, benPedro.getValor());
			ps.setLong(5, benPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(BenPedro benPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM bens  WHERE id_bem =? ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setLong(1, benPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		}

	}
	
	public List<BenPedro> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "  SELECT "
				+"bem.id_bem AS idBem   "
				+"bem.nome_bem AS nomeBem    "
				+"bem.especificacao_bem AS espcBem   "
				+"bem.valor_bem AS valorBem "
				+" dep.id_departamento AS idDepartamento   "
				+" dep.desc_departamento AS descDepartamento "
				+" FROM bens bem"
				+" INNER JOIN departamentos dep ON  bem.id_bem = dep.id_departamento";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<BenPedro> listaRetorno = new ArrayList<BenPedro>();
		
		try {
			
			ps= conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BenPedro benPedro = new BenPedro();
				benPedro.setId(rs.getLong("idBem"));
				benPedro.setEspecificacao(rs.getString("espcBem"));
				benPedro.setNome(rs.getString("nomeBem"));
				benPedro.setValor(rs.getDouble("valorBem"));
				
				
				DepartamentoPedro departamentoPedro = new DepartamentoPedro();
				
				departamentoPedro.setId(rs.getLong("idDepartamento"));
				departamentoPedro.setDepartamento(rs.getString("descDepartamento"));
				
				benPedro.setDerpartamento(departamentoPedro);
				listaRetorno.add(benPedro);
				
				
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		return listaRetorno;
		
	}

}
