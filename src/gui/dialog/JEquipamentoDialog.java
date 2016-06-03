package gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import dao.EquipamentoDao;
import gui.action.CancelAction;
import gui.tablemodel.EquipamentoTableModel;

public class JEquipamentoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txtCusto;
	private JComboBox<String> comboBox;
	private JTextField txtDesc;
	private JTextField txtCod;
	private JDateChooser dateChooser;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private EquipamentoTableModel tableModel;
	private JTextFieldDateEditor dateEditor;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JEquipamentoDialog dialog = new JEquipamentoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public JEquipamentoDialog() {
		initialize();
	}

	/**
	 * Create the dialog.
	 * @param tableModel
	 */
	public JEquipamentoDialog(EquipamentoTableModel tableModel) {
		this.tableModel = tableModel;
		initialize();
	}

	public JEquipamentoDialog(EquipamentoTableModel tableModel, EquipamentoDao equipamentoDao) {
		initialize();

	}

	private void initialize() {
		setBounds(100, 100, 518, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblId = new JLabel("CodEquipamento");
		
		txtCod = new JTextField();
		txtCod.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrica\u00E7\u00E3o");
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		
		JLabel lblTipoEquip = new JLabel("Tipo Equipamento");
		
		comboBox = new JComboBox<>();
		comboBox.addItem("FIXO");
		comboBox.addItem("MOVEL");
		comboBox.addItem("USO EXTERNO");
		
		JLabel lblManuteno = new JLabel("Em manuten\u00E7\u00E3o");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		buttonGroup.add(rdbtnSim);

		JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setSelected(true);

		NumberFormat custoDisplayFormat = NumberFormat.getCurrencyInstance();
		custoDisplayFormat.setMinimumFractionDigits(2);
        NumberFormat custoEditFormatter = NumberFormat.getNumberInstance();
		
		txtCusto = new JFormattedTextField(
                new DefaultFormatterFactory(
                        new NumberFormatter(custoDisplayFormat),
                        new NumberFormatter(custoDisplayFormat),
                        new NumberFormatter(custoEditFormatter)));
		txtCusto.setColumns(10);
        txtCusto.setValue(0);

		dateEditor = new JTextFieldDateEditor();
		dateChooser = new JDateChooser(dateEditor);
		
		JLabel lblCustoDiario = new JLabel("Custo diario:");
		
		JLabel lblDataDeAquisio = new JLabel("Data de Aquisi\u00E7\u00E3o");
		
		JLabel lblCadastrarEquipamento = new JLabel("Cadastrar Equipamento");
		lblCadastrarEquipamento.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescricao)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblId)
								.addGap(22))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblTipoEquip)
								.addGap(18)))
						.addComponent(lblManuteno)
						.addComponent(lblCustoDiario, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeAquisio, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(rdbtnSim)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnNo))
						.addComponent(comboBox, 0, 154, Short.MAX_VALUE)
						.addComponent(txtDesc, 154, 154, Short.MAX_VALUE)
						.addComponent(txtCod, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(txtCusto, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
					.addGap(101))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastrarEquipamento, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(217, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastrarEquipamento, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescricao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoEquip))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnSim)
						.addComponent(rdbtnNo)
						.addComponent(lblManuteno))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustoDiario))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeAquisio))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		
		
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnSalvar = new JButton("Salvar");
			buttonPane.add(btnSalvar);
			btnSalvar.addActionListener(e ->
        {
            System.out.println(new BigDecimal(txtCusto.getValue().toString()));
//            EquipamentoDao dao = new EquipamentoDao();
//            Equipamento equipamento = null;
//            try{
//            int codEquipamento = Integer.parseInt(txtCod.getText());
//            String descricao = txtDesc.getText();
//            String tipo = comboBox.getSelectedItem().toString();
//            BigDecimal custodiario = new BigDecimal(txtCusto.getText());
//            LocalDate dataAquisicao = LocalDateUtils.toLocalDate(dateChooser.getDate());
//            boolean emMantencao = rdbtnSim.isSelected();
//			 equipamento = new Equipamento(codEquipamento, dataAquisicao, descricao,
//            			custodiario, tipo, emMantencao);
//			}catch (Exception ex){
//				JOptionPane.showMessageDialog(this, ex.getMessage());
//			}
//
//            try {
//                dao.inserir(equipamento);
//				tableModel.add(equipamento);
//				limpaCampos();
//				JOptionPane.showMessageDialog(this, "Cadastrado com sucesso.");
//            } catch (SQLException e1) {
//                JOptionPane.showMessageDialog(this, e1.getMessage());
//            }

        });
			{
				JButton cancelButton = new JButton(new CancelAction(this));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void limpaCampos(){
		txtCod.setText("");
		txtCusto.setText("");
		txtDesc.setText("");
		((JTextField) dateEditor.getUiComponent()).setText("");
	}
}
