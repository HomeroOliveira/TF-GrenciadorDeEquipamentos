package gui.tablemodel;

import modelo.Equipamento;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Created by Junior on 24/05/2016.
 */
public class EquipamentoTableModel extends AbstractTableModel implements TableModel {
    private static final String[] COLUNAS = {"Descricao", "Data Aquisição",
            "Custo Diário", "Tipo Equipamento", "Em Manutenção"};
    private final List<Equipamento> equipamentos;

    public EquipamentoTableModel(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return COLUNAS[column];
    }

    @Override
    public int getRowCount() {
        return equipamentos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return LocalDate.class;
            case 2:
                return BigDecimal.class;
            case 3:
                return String.class;
            case 4:
                return Boolean.class;
            default:
                throw new NoSuchElementException("Coluna não encontrada");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return equipamentos.get(rowIndex).getDescricao();
            case 1:
                return equipamentos.get(rowIndex).getDataAquisao();
            case 2:
                return equipamentos.get(rowIndex).getCustoDiario();
            case 3:
                return equipamentos.get(rowIndex).getTipoEquipamento();
            case 4:
                return equipamentos.get(rowIndex).isEmManutencao();
            default:
                throw  new NoSuchElementException("Coluna não encontrada");
        }
    }

    public void add(Equipamento equipamento) {
        equipamentos.add(equipamento);
        int ultimo = equipamentos.size() - 1;
        super.fireTableRowsInserted(ultimo, ultimo);
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
}
