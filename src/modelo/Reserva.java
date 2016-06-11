package modelo;

import java.time.LocalDate;


public class Reserva {

    private int codReserva;
    private Equipamento equipamento;
    private Funcionario funcionario;
    private LocalDate dataInicial;
    private LocalDate dataFinal;


    public Reserva(int codReserva,Equipamento equipamento ,
                   Funcionario funcionario, LocalDate dataInicial,
                   LocalDate dataFinal) {
        this.codReserva = codReserva;
        this.equipamento = equipamento;
        this.funcionario = funcionario;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public int getCodReserva() {
        return codReserva;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public  int getCodEquipamento(){return  equipamento.getCodEquipamento();}

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public  int getCodMatricula(){return  funcionario.getCodMatricula();}

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    /**
     * @return Data final da reserva.
     */
    public LocalDate getDataFinal() {
        return dataFinal;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codReserva=" + codReserva +
                ", equipamento=" + equipamento +
                ", funcionario=" + funcionario +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                '}';
    }
}
