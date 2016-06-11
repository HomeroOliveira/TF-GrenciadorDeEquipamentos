package gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;
import dao.FuncionarioDao;
import gui.action.CancelAction;
import gui.tablemodel.FuncionarioTableModel;
import modelo.Funcionario;
import utils.LocalDateUtils;
import javax.swing.SwingConstants;

public class JFuncionarioDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private FuncionarioTableModel tableModel;
    private FuncionarioDao funcionarioDao;
    private JPanel panel;
    private JTextField txtCod;
    private JPasswordField txtSenha;
    private JTextField txtEndereco;
    private JTextField txtNome;
    private JComboBox<String> comboBoxSexo;
    private JDateChooser dateChooseAdmi;
    private JFormattedTextField txtSalario;
    private JDateChooser dateChooserNasc;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            JFuncionarioDialog dialog = new JFuncionarioDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public JFuncionarioDialog() {
        initialize();
    }

    public JFuncionarioDialog(FuncionarioTableModel tableModel, FuncionarioDao funcionarioDao) {
        initialize();
        this.tableModel = tableModel;
        this.funcionarioDao = funcionarioDao;
    }


    private void initialize() {
        setBounds(100, 100, 460, 333);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("Cadastrar Funcion\u00E1rio");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        contentPanel.add(lblNewLabel_3, BorderLayout.NORTH);
        {
            panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
        }
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 359, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("Cod Matricula:");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        txtCod = new JTextField();
        GridBagConstraints gbc_txtCod = new GridBagConstraints();
        gbc_txtCod.insets = new Insets(0, 0, 5, 0);
        gbc_txtCod.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCod.gridx = 1;
        gbc_txtCod.gridy = 0;
        panel.add(txtCod, gbc_txtCod);
        txtCod.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        txtNome = new JTextField();
        GridBagConstraints gbc_txtNome = new GridBagConstraints();
        gbc_txtNome.insets = new Insets(0, 0, 5, 0);
        gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNome.gridx = 1;
        gbc_txtNome.gridy = 1;
        panel.add(txtNome, gbc_txtNome);
        txtNome.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Senha:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

        txtSenha = new JPasswordField();
        GridBagConstraints gbc_txtSenha = new GridBagConstraints();
        gbc_txtSenha.insets = new Insets(0, 0, 5, 0);
        gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSenha.gridx = 1;
        gbc_txtSenha.gridy = 2;
        panel.add(txtSenha, gbc_txtSenha);

        JLabel lblEndereo = new JLabel("Endere\u00E7o");
        GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
        gbc_lblEndereo.anchor = GridBagConstraints.WEST;
        gbc_lblEndereo.insets = new Insets(0, 0, 5, 5);
        gbc_lblEndereo.gridx = 0;
        gbc_lblEndereo.gridy = 3;
        panel.add(lblEndereo, gbc_lblEndereo);

        txtEndereco = new JTextField();
        GridBagConstraints gbc_txtEndereco = new GridBagConstraints();
        gbc_txtEndereco.insets = new Insets(0, 0, 5, 0);
        gbc_txtEndereco.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEndereco.gridx = 1;
        gbc_txtEndereco.gridy = 3;
        panel.add(txtEndereco, gbc_txtEndereco);
        txtEndereco.setColumns(10);

        JLabel lblSexo = new JLabel("Sexo:");
        GridBagConstraints gbc_lblSexo = new GridBagConstraints();
        gbc_lblSexo.anchor = GridBagConstraints.WEST;
        gbc_lblSexo.insets = new Insets(0, 0, 5, 5);
        gbc_lblSexo.gridx = 0;
        gbc_lblSexo.gridy = 4;
        panel.add(lblSexo, gbc_lblSexo);

        comboBoxSexo = new JComboBox<>();
        comboBoxSexo.addItem("Masculino");
        comboBoxSexo.addItem("Feminino");
        GridBagConstraints gbc_comboBoxSexo = new GridBagConstraints();
        gbc_comboBoxSexo.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxSexo.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxSexo.gridx = 1;
        gbc_comboBoxSexo.gridy = 4;
        panel.add(comboBoxSexo, gbc_comboBoxSexo);

        JLabel lblDataNascimento = new JLabel("Data Nascimento:");
        GridBagConstraints gbc_lblDataNascimento = new GridBagConstraints();
        gbc_lblDataNascimento.anchor = GridBagConstraints.WEST;
        gbc_lblDataNascimento.insets = new Insets(0, 0, 5, 5);
        gbc_lblDataNascimento.gridx = 0;
        gbc_lblDataNascimento.gridy = 5;
        panel.add(lblDataNascimento, gbc_lblDataNascimento);

        dateChooserNasc = new JDateChooser();
        GridBagConstraints gbc_dateChooserNasc = new GridBagConstraints();
        gbc_dateChooserNasc.insets = new Insets(0, 0, 5, 0);
        gbc_dateChooserNasc.fill = GridBagConstraints.BOTH;
        gbc_dateChooserNasc.gridx = 1;
        gbc_dateChooserNasc.gridy = 5;
        panel.add(dateChooserNasc, gbc_dateChooserNasc);

        JLabel lblDataAdmisso = new JLabel("Data Admiss\u00E3o:");
        GridBagConstraints gbc_lblDataAdmisso = new GridBagConstraints();
        gbc_lblDataAdmisso.anchor = GridBagConstraints.WEST;
        gbc_lblDataAdmisso.insets = new Insets(0, 0, 5, 5);
        gbc_lblDataAdmisso.gridx = 0;
        gbc_lblDataAdmisso.gridy = 6;
        panel.add(lblDataAdmisso, gbc_lblDataAdmisso);

        dateChooseAdmi = new JDateChooser();
        GridBagConstraints gbc_dateChooseAdmi = new GridBagConstraints();
        gbc_dateChooseAdmi.insets = new Insets(0, 0, 5, 0);
        gbc_dateChooseAdmi.fill = GridBagConstraints.BOTH;
        gbc_dateChooseAdmi.gridx = 1;
        gbc_dateChooseAdmi.gridy = 6;
        panel.add(dateChooseAdmi, gbc_dateChooseAdmi);

        JLabel lblSalario = new JLabel("Salario:");
        GridBagConstraints gbc_lblSalario = new GridBagConstraints();
        gbc_lblSalario.anchor = GridBagConstraints.WEST;
        gbc_lblSalario.insets = new Insets(0, 0, 0, 5);
        gbc_lblSalario.gridx = 0;
        gbc_lblSalario.gridy = 7;
        panel.add(lblSalario, gbc_lblSalario);

        NumberFormat salarioDisplayFormat = NumberFormat.getCurrencyInstance();
        salarioDisplayFormat.setMinimumFractionDigits(2);
        NumberFormat salarioEditFormatter = NumberFormat.getNumberInstance();
        txtSalario = new JFormattedTextField(
                new DefaultFormatterFactory(
                        new NumberFormatter(salarioDisplayFormat),
                        new NumberFormatter(salarioDisplayFormat),
                        new NumberFormatter(salarioEditFormatter)));
        txtSalario.setColumns(10);
        txtSalario.setValue(0);
        GridBagConstraints gbc_txtSalario = new GridBagConstraints();
        gbc_txtSalario.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSalario.gridx = 1;
        gbc_txtSalario.gridy = 7;
        panel.add(txtSalario, gbc_txtSalario);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnSalvar = new JButton("Salvar");
                btnSalvar.setActionCommand("Salvar");
                btnSalvar.addActionListener(e -> {
                   Funcionario funcionario  = criarFuncionario();
                    cadastrar(funcionario);
                });

                buttonPane.add(btnSalvar);
                getRootPane().setDefaultButton(btnSalvar);


            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.setAction(new CancelAction(this));
                buttonPane.add(cancelButton);
            }
        }
    }

    public Funcionario criarFuncionario() {
        int cod = Integer.parseInt(txtCod.getText());
        String nome = txtNome.getText();
        String senha = new String(txtSenha.getPassword());
        BigDecimal salario = new BigDecimal(txtSalario.getValue().toString());
        String endereco = txtEndereco.getText();
        String sexo = String.valueOf(comboBoxSexo.getSelectedItem().toString().charAt(0));
        LocalDate dataNasc = LocalDateUtils.toLocalDate(dateChooserNasc.getDate());
        LocalDate dataAdmin = LocalDateUtils.toLocalDate(dateChooseAdmi.getDate());

        return new Funcionario(cod, nome, senha,
                dataNasc, dataAdmin, sexo, endereco, salario);
    }

    public void cadastrar(Funcionario funcionario) {
        funcionarioDao.inserir(funcionario);
        tableModel.add(funcionario);
    }
}
