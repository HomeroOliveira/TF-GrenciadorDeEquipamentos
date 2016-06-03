package console;

import dao.EquipamentoDao;
import dao.FuncionarioDao;
import dao.ReservaDao;
import modelo.Equipamento;
import utils.Tuple;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static final FuncionarioDao FUNCIONARIO_DAO = new FuncionarioDao();
    private static final EquipamentoDao EQUIPAMENTO_DAO = new EquipamentoDao();
    private static final ReservaDao RESERVA_DAO = new ReservaDao();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = -1;
//        while (escolha != 0) {
            System.out.println("Escolha um opção:");
          //  escolha = in.nextInt();
//            menuEquipamento(escolha);
        List<Tuple<String, LocalDate, LocalDate>> tuples = RESERVA_DAO.buscarReservasFuturas();
        tuples.forEach(System.out::println);
//        }


    }

    public static void menuEquipamento(int numero) {
        switch (numero) {
            case 1:
                List<Equipamento> equipamentos = EQUIPAMENTO_DAO.buscarTodos();
                equipamentos.forEach(System.out::print);
                break;
            case 2:
                Equipamento equipamento = criarEquipamento();
                try {
                    EQUIPAMENTO_DAO.inserir(equipamento);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Dígite a descrição do equipamento:");
                String descricao = in.next();
                Optional<Equipamento> equipamentoOptional = EQUIPAMENTO_DAO.buscarPor(descricao);
                if (equipamentoOptional.isPresent()){
                    System.out.println(equipamentoOptional.get());
                }else {
                    System.out.println("Não foi encontrado.");
                }
                break;
            default:
                System.out.println("Erro menu escolhido não existe.");
                break;
        }
    }

    private static Equipamento criarEquipamento() {
        System.out.println("CodEquipamento:");
        int codEquipamento = in.nextInt();
        System.out.println("Data de aquisição(dia/mês/ano):");
        LocalDate dataAquisicao = null;
        try {
            String data = in.next();
            dataAquisicao = LocalDate.parse(data,
                    DateTimeFormatter.ofPattern("dd/MM/YYYY"));

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        String descricao = in.next();
        BigDecimal custoDiario = new BigDecimal(in.next());
        String tipoEquipamento = in.next();
        boolean emManutencao = Boolean.valueOf(in.next());
        return new Equipamento(codEquipamento, dataAquisicao, descricao, custoDiario, tipoEquipamento, emManutencao);

    }

    public static void menuFuncionario(int escolha){

    }
}
