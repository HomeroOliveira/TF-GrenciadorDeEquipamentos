package gui.cellrender;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Junior on 25/05/2016.
 */
public class BigDecimalCellRender implements TableCellRenderer{
    private static final DefaultTableCellRenderer CELL_RENDERER = new DefaultTableCellRenderer();
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        return CELL_RENDERER.getTableCellRendererComponent(table, CURRENCY.format(value), isSelected, hasFocus, row, column);
    }
}
