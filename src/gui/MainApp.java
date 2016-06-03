package gui;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import dao.EquipamentoDao;
import dao.FuncionarioDao;
import dao.ReservaDao;
import gui.cellrender.BigDecimalCellRender;
import gui.cellrender.LocalDateCellRender;
import gui.dialog.JEquipamentoDialog;
import gui.dialog.JFuncionarioDialog;
import gui.tablemodel.EquipamentoTableModel;
import gui.tablemodel.FuncionarioTableModel;
import gui.tablemodel.ReservaTableModel;
import modelo.Equipamento;
import modelo.Funcionario;
import modelo.Reserva;

public class MainApp {

    private JFrame frame;
    private EquipamentoDao equipamentoDao = new EquipamentoDao();
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private ReservaDao reservaDao =new ReservaDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainApp window = new MainApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 560, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        List<Equipamento> equipamentos = equipamentoDao.buscarTodos();
        EquipamentoTableModel tableModel = new EquipamentoTableModel(equipamentos);
        JTablePanel jpanelEquipamento = new JTablePanel(tableModel)
                .setDefaultRenderer(BigDecimal.class, new BigDecimalCellRender())
                .setDefaultRenderer(LocalDate.class, new LocalDateCellRender());

        jpanelEquipamento.getBtnCadastrar().addActionListener(e -> {
            JEquipamentoDialog jEquipamentoDialog = new JEquipamentoDialog(tableModel, equipamentoDao);
            jEquipamentoDialog.setVisible(true);
        });
        Icon iconEquipamento = new ImageIcon(ClassLoader.getSystemResource("gui/images/tool.jpg"));

        List<Funcionario> funcionarios = funcionarioDao.buscarTodos();
        FuncionarioTableModel funcionarioTableModel = new FuncionarioTableModel(funcionarios);
        JTablePanel jpanelFuncionario = new JTablePanel(funcionarioTableModel)
                .setDefaultRenderer(LocalDate.class, new LocalDateCellRender())
                .setDefaultRenderer(BigDecimal.class, new BigDecimalCellRender());
        Icon iconFuncionario = new ImageIcon(ClassLoader.getSystemResource("gui/images/employee.jpg"));

        jpanelFuncionario.getBtnCadastrar().addActionListener(e -> {
            JFuncionarioDialog jFuncionarioDialog = new JFuncionarioDialog(funcionarioTableModel, funcionarioDao);
            jFuncionarioDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            jFuncionarioDialog.setVisible(true);
        });

        
        List<Reserva> reservas = reservaDao.buscarTodos();
        ReservaTableModel reservaTableModel = new ReservaTableModel(reservas);
        JTablePanel jpanelReservas = new JTablePanel(reservaTableModel)
                .setDefaultRenderer(LocalDate.class, new LocalDateCellRender());
        jpanelReservas.getBtnCadastrar().setText("Fazer Reserva");

        Icon iconReserva = new ImageIcon(ClassLoader.getSystemResource("gui/images/reserva.jpg"));

        String title = "Funcionario";
        tabbedPane.addTab(title, iconFuncionario, jpanelFuncionario);
        title = "Equipamneto";
        tabbedPane.addTab(title, iconEquipamento, jpanelEquipamento);
        title = "Reservas";
        tabbedPane.addTab(title, iconReserva, jpanelReservas);

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout
                .setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addContainerGap()
                                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                .addContainerGap()));
        groupLayout
                .setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addContainerGap()
                                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                .addContainerGap()));
        frame.getContentPane().setLayout(groupLayout);
    }
}
