package gui.panel;


import dao.FuncionarioDao;
import gui.cellrender.BigDecimalCellRender;
import gui.cellrender.LocalDateCellRender;
import gui.dialog.JFuncionarioDialog;
import gui.tablemodel.FuncionarioTableModel;
import modelo.Funcionario;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JFuncionarioPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField txtNome;
    private FuncionarioDao funcionarioDao;
    private FuncionarioTableModel tableModel;
    private JScrollPane scrollPane_1;
    private JButton btnBuscar_1;
    private JButton btnCadastrar_1;

    /**
     * Create the panel.
     */
    public JFuncionarioPanel() {
        initialize();
    }

    private void initialize() {

        scrollPane_1 = new JScrollPane();

        btnCadastrar_1 = new JButton("Cadastrar");
        btnCadastrar_1.addActionListener(this::do_btnCadastrar_actionPerformed);


        btnBuscar_1 = new JButton("Buscar");
        btnBuscar_1.addActionListener(this::do_btnBuscar_actionPerformed);

        txtNome = new JTextField();
        txtNome.setColumns(10);

        GroupLayout groupLayout = createGroupLayout(scrollPane_1, btnCadastrar_1, btnBuscar_1);

        table = new JTable();
        scrollPane_1.setViewportView(table);
        setLayout(groupLayout);

        funcionarioDao = new FuncionarioDao();
        tableModel = new FuncionarioTableModel(funcionarioDao.buscarTodos());

        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);

        table.setDefaultRenderer(BigDecimal.class, new BigDecimalCellRender());
        table.setDefaultRenderer(LocalDate.class, new LocalDateCellRender());

    }

    private GroupLayout createGroupLayout(JScrollPane scrollPane, JButton btnCadastrar, JButton btnBuscar) {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBuscar_1)
        					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
        					.addComponent(btnCadastrar_1)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(7)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCadastrar_1)
        				.addComponent(btnBuscar_1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        			.addContainerGap())
        );
        return groupLayout;
    }

    private void do_btnBuscar_actionPerformed(ActionEvent arg0) {
		String nome = txtNome.getText();
        List<Funcionario> funcionarios = funcionarioDao.buscarPorNome(nome);
        tableModel.setFuncionarios(funcionarios);
        table.setModel(tableModel);
    }
	private void do_btnCadastrar_actionPerformed(ActionEvent arg0) {
		JFuncionarioDialog dialog = new JFuncionarioDialog(tableModel, funcionarioDao);
		dialog.setVisible(true);
	}
}
