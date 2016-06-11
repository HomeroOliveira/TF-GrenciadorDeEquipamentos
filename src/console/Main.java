package console;

import java.util.Scanner;

import dao.EquipamentoDao;
import dao.FuncionarioDao;
import dao.ReservaDao;

/**
 * Created by Junior on 07/06/2016.
 */
public class Main {

    private static EquipamentoDao equipamentoDao = new EquipamentoDao();
    private static ReservaDao reservaDao = new ReservaDao();
    private static FuncionarioDao funcionarioDao = new FuncionarioDao();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        int escolha = 10;
        while (escolha != 0) {
            System.out.println("1-Listar funcionarios");
            System.out.println("2-Buscar funcionario por nome:");
            System.out.println("3-Buscar equipamento por descricao:");
            System.out.println("4-Inserir reserva:");
            System.out.println("5-Relatorio de reservas futuras:");
            System.out.println("6-Visualizar a quantidade de reservas de um equipamento e o total do custo:");
            System.out.println("7-Listar os funcionarios, o numero de reservas de cada um, e o custo total de uso de " +
                    "equipamentos de cada funcionario:");
            System.out.println("8-Subconsulta");
            escolha = scanner.nextInt();
            menu(escolha);
        }


    }

    private static void menu(int escolha) {
        Scanner scanner = new Scanner(System.in);
        switch (escolha) {
            case 0:
                System.exit(0);
                break;
            case 1:
                funcionarioDao
                        .buscarTodos()
                        .forEach(System.out::println);
                break;
            case 2:
                System.out.println("Dígite o nome do funcionario:");
                String nome = scanner.next();
                funcionarioDao
                        .buscarPorNome(nome)
                        .forEach(System.out::println);
                break;
            case 3:
                System.out.println("Dígite a descrição do equipamento:");
                String descricao = scanner.next();
                equipamentoDao
                        .buscarPorDescricao(descricao)
                        .forEach(System.out::println);
                break;
            case 4:
                break;
            case 5:
                reservaDao
                        .buscarReservasFuturas()
                        .forEach(System.out::println);
                break;
            case 6:
                reservaDao
                        .buscarQuantidadeDeReservasComEquipamentos()
                        .forEach(System.out::println);
                break;
            case 7:
                reservaDao
                        .buscarQuantidadeDeReservasComFuncionario()
                        .forEach(System.out::println);
                break;

        }
    }

}
