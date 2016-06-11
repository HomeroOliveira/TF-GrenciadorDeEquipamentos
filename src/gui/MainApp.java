package gui;

import gui.panel.JEquipamentoPanel;
import gui.panel.JFuncionarioPanel;
import gui.panel.JReservaPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainApp {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                MainApp window = new MainApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JEquipamentoPanel jpanelEquipamento = new JEquipamentoPanel();
        JFuncionarioPanel jpanelFuncionario = new JFuncionarioPanel();
        JReservaPanel jpanelReservas = new JReservaPanel();

        Icon iconEquipamento = new ImageIcon(ClassLoader.getSystemResource("gui/images/tool.jpg"));
        Icon iconFuncionario = new ImageIcon(ClassLoader.getSystemResource("gui/images/employee.jpg"));
        Icon iconReserva = new ImageIcon(ClassLoader.getSystemResource("gui/images/reserva.jpg"));

        tabbedPane.addTab("Funcionario", iconFuncionario, jpanelFuncionario);
        tabbedPane.addTab("Equipamneto", iconEquipamento, jpanelEquipamento);
        tabbedPane.addTab("Reservas", iconReserva, jpanelReservas);

        createGroupLayout(tabbedPane);
    }

    private void createGroupLayout(JTabbedPane tabbedPane) {
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
