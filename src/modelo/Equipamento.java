package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Equipamento {
	private int codEquipamento;
	private LocalDate dataAquisa;
	private String descricao;
	private BigDecimal custoDiario;
	private String tipoEquipamento;
	private boolean emManutencao;
	
	public Equipamento(int codEquipamento, LocalDate dataAquisa, String descricao, BigDecimal custoDiario,
			String tipoEquipamento, boolean emManutencao) {
		super();
		this.codEquipamento = codEquipamento;
		this.dataAquisa = dataAquisa;
		this.descricao = descricao;
		this.custoDiario = custoDiario;
		this.tipoEquipamento = tipoEquipamento;
		this.emManutencao = emManutencao;
	}

	public int getCodEquipamento() {
		return codEquipamento;
	}

	public LocalDate getDataAquisa() {
		return dataAquisa;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCustoDiario() {
		return custoDiario;
	}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public boolean isEmManutencao() {
		return emManutencao;
	}
			
}