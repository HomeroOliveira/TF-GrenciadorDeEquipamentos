package gui.cellrender;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Junior on 20/05/2016.
 */
public class LocalDateCellRender implements TableCellRenderer {

    private final DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Object valorFormatado = ((LocalDate)value).format(dateTimeFormatter);
            return cellRenderer.getTableCellRendererComponent(table,valorFormatado, isSelected,
                    hasFocus, row, column);
    }
}
