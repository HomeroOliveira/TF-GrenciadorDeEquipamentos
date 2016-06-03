package gui.tablemodel;

import modelo.Equipamento;
import modelo.Funcionario;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Junior on 24/05/2016.
 */
public class FuncionarioTableModel extends AbstractTableModel implements TableModel {
    private static final String[] COLUNAS = {"Nome", "Data Nascimento",
             "Sexo", "Endereço","Data Admissão", "Sálario"};
    private final List<Funcionario> funcionarios;

    public FuncionarioTableModel(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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
        return funcionarios.size();
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
                return String.class;
            case 3:
                return String.class;
            case 4:
                return LocalDate.class;
            case 5:
                return BigDecimal.class;

            default:
                throw new NoSuchElementException("Coluna não encontrada");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return funcionarios.get(rowIndex).getNome();
            case 1:
                return funcionarios.get(rowIndex).getDataNascimento();
            case 2:
                return funcionarios.get(rowIndex).getSexo();
            case 3:
                return funcionarios.get(rowIndex).getEndereco();
            case 4:
                return funcionarios.get(rowIndex).getDataAdmissao();
            case 5:
                return funcionarios.get(rowIndex).getSalario();
            default:
                throw  new NoSuchElementException("Coluna não encontrada");
        }
    }

    public void add(Funcionario funcionario) {
        funcionarios.add(funcionario);
        super.fireTableDataChanged();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
