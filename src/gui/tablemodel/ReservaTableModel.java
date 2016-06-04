package gui.tablemodel;

import modelo.Reserva;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Junior on 20/05/2016.
 */
public class ReservaTableModel extends AbstractTableModel implements TableModel{
    private static final String[] COLUNAS = {"Equipamento", "Funcionario",
            "Data Inicial", "Data Final"};
    private static final int EQUIPAMENTO = 0;
    private static final int FUNCIONARIO = 1;
    private static final int DATA_INICIAL = 2;
    private static final int DATA_FINAL = 3;
    private final List<Reserva> reservas;


    public ReservaTableModel(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int getRowCount() {
        return reservas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case EQUIPAMENTO:
                return String.class;
            case FUNCIONARIO:
                return String.class;
            case DATA_INICIAL:
                return LocalDate.class;
            case DATA_FINAL:
                return LocalDate.class;
            default:
                throw  new NoSuchElementException("Coluna não encontrada");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case EQUIPAMENTO:
                return reservas.get(rowIndex).getEquipamento().getDescricao();
            case FUNCIONARIO:
                return reservas.get(rowIndex).getFuncionario().getNome();
            case DATA_INICIAL:
                return reservas.get(rowIndex).getDataInicial();
            case DATA_FINAL:
                return reservas.get(rowIndex).getDataFinal();
            default:
                throw  new NoSuchElementException("Coluna não encontrada");
        }
    }

    public void add(Reserva reserva) {
        reservas.add(reserva);
        super.fireTableDataChanged();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
