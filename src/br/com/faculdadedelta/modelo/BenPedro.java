package br.com.faculdadedelta.modelo;

public class BenPedro {

	private Long id;
	private String nome;
	private String especificacao;
	private DepartamentoPedro derpartamento;
	private double valor;
	public BenPedro() {
		super();
	}
	public BenPedro(Long id, String nome, String especificacao, DepartamentoPedro derpartamento, double valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.especificacao = especificacao;
		this.derpartamento = derpartamento;
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecificacao() {
		return especificacao;
	}
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	public DepartamentoPedro getDerpartamento() {
		return derpartamento;
	}
	public void setDerpartamento(DepartamentoPedro derpartamento) {
		this.derpartamento = derpartamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenPedro other = (BenPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	
}
