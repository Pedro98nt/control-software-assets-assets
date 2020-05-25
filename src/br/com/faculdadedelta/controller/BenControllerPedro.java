package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BenDaoPedro;
import br.com.faculdadedelta.modelo.BenPedro;
import br.com.faculdadedelta.modelo.DepartamentoPedro;

@ManagedBean
@SessionScoped
public class BenControllerPedro {
	
	private BenPedro benPedro = new BenPedro();
	private BenDaoPedro dao = new BenDaoPedro();
	private DepartamentoPedro departamentoSelecionado = new DepartamentoPedro();

	public BenPedro getBen() {
		return benPedro;
	}

	public void setBen(BenPedro benPedro) {
		this.benPedro = benPedro;
	}

	public DepartamentoPedro getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}

	public void setDepartamentoSelecionado(DepartamentoPedro departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}
	
	
	public void limparCampo() {
		benPedro =  new BenPedro();
		departamentoSelecionado = new DepartamentoPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		
		try {
		if(benPedro.getId()==null) {
			benPedro.setDerpartamento(departamentoSelecionado);
				dao.incluir(benPedro);
				limparCampo();
				exibirMensagem("inclusão realizada com sucesso");
			}else {
				benPedro.setDerpartamento(departamentoSelecionado);
				dao.alterar(benPedro);
				limparCampo();
				exibirMensagem("alteração realizada com sucesso");
			}
		
		
			} catch (Exception e) {
				e.printStackTrace();
			exibirMensagem("erro no processo "+"tente mais tarde"+e.getMessage());
		}
		
		return "cadastroBen.xhtml";
		
	}
	
	public String editar() {
		departamentoSelecionado = benPedro.getDerpartamento();
		return "cadastroBen.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(benPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "listaBen.xhtml";
		
	}
	
	public List<BenPedro> getLista(){
	List<BenPedro> listaRetorno = new ArrayList<BenPedro>();
	
	try {
		listaRetorno=dao.listar();
	} catch (Exception e) {
		e.printStackTrace();
		exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
	}
		
		
		return listaRetorno;
		
	}
	
	
	
}
