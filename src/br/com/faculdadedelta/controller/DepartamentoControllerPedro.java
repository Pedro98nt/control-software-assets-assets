package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.DepartamentoDaoPedro;
import br.com.faculdadedelta.modelo.DepartamentoPedro;

@ManagedBean
@SessionScoped
public class DepartamentoControllerPedro {

	private DepartamentoPedro departamentoPedro = new DepartamentoPedro();
	private DepartamentoDaoPedro dao = new DepartamentoDaoPedro();

	public DepartamentoPedro getDepartamento() {
		return departamentoPedro;
	}

	public void setDepartamento(DepartamentoPedro departamentoPedro) {
		this.departamentoPedro = departamentoPedro;
	}

	public void limparCampo() {
		departamentoPedro = new DepartamentoPedro();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {

			if (departamentoPedro.getId() == null) {
				dao.incluir(departamentoPedro);
				exibirMensagem("inclusão realizada com sucesso");
				limparCampo();

			} else {
				dao.alterar(departamentoPedro);
				exibirMensagem("alteração realizada com sucesso");
				limparCampo();
			}

		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro na operação " + "tente mais tarde" + e.getMessage());

		}

		return "cadastroDepartamento.xhtml";

	}
	
	public String excluir() {
		
		try {
			dao.excluir(departamentoPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		return "listarDepartamento.xhtml";
	}
	
	public String editar() {
	  return "cadastroDepartamento.xhtml";
		
	}
	
	public List<DepartamentoPedro> getLista(){
		List<DepartamentoPedro> listaRetorno = new ArrayList<DepartamentoPedro>();
		
		try {
			listaRetorno=dao.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		
		return listaRetorno;
		
	}

}
