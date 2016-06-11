package gui.panel;

import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ReservaDao;
import gui.cellrender.LocalDateCellRender;
import gui.tablemodel.ReservaTableModel;

public class JReservaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton btnCadastrar;
    private ReservaDao reservaDao;
	private ReservaTableModel tableModel;
    
    /**
	 * Create the panel.
	 */
	public JReservaPanel() {
        initialize();

	}
	private void initialize() {
		
		JScrollPane scrollPane = new JScrollPane();

        btnCadastrar = new JButton("Reservar");
		
		JButton btnReservasFuturas = new JButton("Reservas Futuras");
		
		JButton btnQuantidadeDeReservas = new JButton("Quantidade de Reservas");

        GroupLayout groupLayout = createGroupLayout(scrollPane, btnReservasFuturas, btnQuantidadeDeReservas);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

        reservaDao = new ReservaDao();
        tableModel = new ReservaTableModel(reservaDao.buscarTodos());

        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(LocalDate.class, new LocalDateCellRender());
	}

    private GroupLayout createGroupLayout(JScrollPane scrollPane,
                                          JButton btnReservasFuturas,
                                          JButton btnQuantidadeDeReservas) {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                            .addComponent(btnQuantidadeDeReservas)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnReservasFuturas)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnCadastrar)))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(7)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnQuantidadeDeReservas)
                        .addComponent(btnReservasFuturas)
                        .addComponent(btnCadastrar))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addContainerGap())
        );
        return groupLayout;
    }


}
