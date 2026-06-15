package view;

import controller.VeiculoController;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private VeiculoView veiculoView;
    private LocacaoView locacaoView;
    private VeiculoController veiculoController;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
        this.veiculoController = new VeiculoController();

        this.veiculoView = new VeiculoView(scanner, veiculoController);
        this.locacaoView = new LocacaoView(scanner, veiculoController);
    }

    public void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Consultar Veículos");
            System.out.println("3 - Alterar Dados de Veículo");
            System.out.println("4 - Excluir Veículo");
            System.out.println("5 - Cadastrar Nova Locação");
            System.out.println("6 - Relatório de Locações");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        veiculoView.exibirMenuCadastro();
                        break;
                    case 2:
                        veiculoView.listarVeiculos();
                        break;
                    case 3:
                        veiculoView.editarVeiculo();
                        break;
                    case 4:
                        veiculoView.excluirVeiculo();
                        break;
                    case 5:
                        locacaoView.exibirMenuLocacao();
                        break;
                    case 6:
                        locacaoView.listarLocacoes();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema... Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
}