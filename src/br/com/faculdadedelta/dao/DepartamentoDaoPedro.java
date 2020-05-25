package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.DepartamentoPedro;

public class DepartamentoDaoPedro {

	public void incluir(DepartamentoPedro departamentoPedro) throws Exception {

		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO departamentos (desc_departamento) VALUES(?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, departamentoPedro.getDepartamento().trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(DepartamentoPedro departamentoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE departamentos SET desc_departamento =? WHERE id_departamento=?  ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, departamentoPedro.getDepartamento().trim());
			ps.setLong(2, departamentoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(DepartamentoPedro departamentoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM departamentos WHERE id_departamento=? ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setLong(1, departamentoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	private DepartamentoPedro popularDepartamento(ResultSet rs) throws SQLException {

		DepartamentoPedro departamentoPedro = new DepartamentoPedro();

		if (rs.next()) {

			departamentoPedro.setId(rs.getLong("id_departamento"));
			departamentoPedro.setDepartamento(rs.getString("desc_departamento"));

		}

		return departamentoPedro;

	}

	public List<DepartamentoPedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();

		String sql = " SELECT id_departamento,desc_departamento FROM departamentos  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DepartamentoPedro> listaRetorno = new ArrayList<DepartamentoPedro>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			DepartamentoPedro departamentoPedro = popularDepartamento(rs);
			listaRetorno.add(departamentoPedro);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
        }finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public DepartamentoPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_departamento,desc_departamento FROM departamentos WHERE id_departamento =?";
		PreparedStatement  ps = null;
		ResultSet rs = null;
		DepartamentoPedro retorno = new DepartamentoPedro();
		
		try {
			ps= conn.prepareStatement(sql);
			rs= ps.executeQuery();
			ps.setLong(1, id);
			
			if(rs.next()) {
				retorno = popularDepartamento(rs);
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
		
	}

}
