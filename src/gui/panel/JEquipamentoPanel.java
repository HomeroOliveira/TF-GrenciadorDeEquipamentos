package gui.panel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.EquipamentoDao;
import gui.cellrender.BigDecimalCellRender;
import gui.cellrender.LocalDateCellRender;
import gui.dialog.JEquipamentoDialog;
import gui.tablemodel.EquipamentoTableModel;
import modelo.Equipamento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEquipamentoPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton btnCadastrar;
    private JTextField textField;
    private EquipamentoDao equipamentoDao;
    private EquipamentoTableModel tableModel;

    /**
     * Create the panel.
     */
    public JEquipamentoPanel() {
        initialize();
    }

    private void initialize() {

        JScrollPane scrollPane = new JScrollPane();

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		do_btnCadastrar_actionPerformed(arg0);
        	}
        });

        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		do_btnBuscar_actionPerformed(arg0);
        	}
        });

        GroupLayout groupLayout = createGroupLayout(scrollPane, btnBuscar);

        table = new JTable();
        scrollPane.setViewportView(table);
        setLayout(groupLayout);

        equipamentoDao = new EquipamentoDao();
        tableModel = new EquipamentoTableModel(equipamentoDao.buscarTodos());
        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);

        table.setDefaultRenderer(BigDecimal.class, new BigDecimalCellRender());
        table.setDefaultRenderer(LocalDate.class, new LocalDateCellRender());

//        jpanelEquipamento.getBtnCadastrar().addActionListener(e -> {
//            JEquipamentoDialog jEquipamentoDialog = new JEquipamentoDialog(tableModel, equipamentoDao);
//            jEquipamentoDialog.setVisible(true);
//        });
    }

    private GroupLayout createGroupLayout(JScrollPane scrollPane, JButton btnBuscar) {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBuscar)
        					.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
        					.addComponent(btnCadastrar)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(7)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCadastrar)
        				.addComponent(btnBuscar))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        			.addContainerGap())
        );
        return groupLayout;
    }
    protected void do_btnBuscar_actionPerformed(ActionEvent arg0) {
    	String descricao = textField.getText();
        List<Equipamento> funcionarios = equipamentoDao.buscarPorDescricao(descricao);
        tableModel.setEquipamentos(funcionarios);
        table.setModel(tableModel);    	
    }
	protected void do_btnCadastrar_actionPerformed(ActionEvent arg0) {
		JEquipamentoDialog dialog = new JEquipamentoDialog(tableModel, equipamentoDao);
		dialog.setVisible(true);	
	}
}
