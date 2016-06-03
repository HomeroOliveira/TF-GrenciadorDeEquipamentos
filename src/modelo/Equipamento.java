package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Equipamento {
	private int codEquipamento;
	private LocalDate dataAquisao;
	private String descricao;
	private BigDecimal custoDiario;
	private String tipoEquipamento;
	private boolean emManutencao;
	
	public Equipamento(int codEquipamento, LocalDate dataAquisao, String descricao, BigDecimal custoDiario,
			String tipoEquipamento, boolean emManutencao) {
		super();
		this.codEquipamento = codEquipamento;
		this.dataAquisao = dataAquisao;
		this.descricao = descricao;
		this.custoDiario = custoDiario;
		this.tipoEquipamento = tipoEquipamento;
		this.emManutencao = emManutencao;
	}

	public int getCodEquipamento() {
		return codEquipamento;
	}


    public LocalDate getDataAquisao() {
		return dataAquisao;
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

    @Override
    public String toString() {
        return "Equipamento{" +
                "codEquipamento=" + codEquipamento +
                ", dataAquisao=" + dataAquisao +
                ", descricao='" + descricao + '\'' +
                ", custoDiario=" + custoDiario +
                ", tipoEquipamento='" + tipoEquipamento + '\'' +
                ", emManutencao=" + emManutencao +
                '}';
    }
}