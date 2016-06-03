package modelo;

import java.time.LocalDate;

import dao.EquipamentoDao;


public class Reserva {
    private int codEquipamento;
    private Equipamento equipamento;
    private int codMatricula;
    private LocalDate dataInicial;
    private LocalDate dataFinal;


    public Reserva(int codEquipamento, int codMatricula, LocalDate dataInicial, LocalDate dataFinal) {
        this.codEquipamento = codEquipamento;
        this.codMatricula = codMatricula;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public int getCodEquipamento() {
        return codEquipamento;
    }

    public Equipamento getEquipamento() {
		if(equipamento == null){
			equipamento =  new EquipamentoDao().buscarPorCod(codEquipamento);
		}
		return equipamento;
	}
    
    public int getCodMatricula() {
        return codMatricula;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codEquipamento=" + codEquipamento +
                ", codMatricula=" + codMatricula +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                '}';
    }
}
