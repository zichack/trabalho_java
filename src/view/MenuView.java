package view;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private VeiculoView veiculoView;
    private LocacaoView locacaoView;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
        this.veiculoView = new VeiculoView(scanner);
        this.locacaoView = new LocacaoView(scanner);
    }

    public void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Consultar Veículos");
            System.out.println("3 - Cadastrar Nova Locação");
            System.out.println("4 - Relatório de Locações");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1: veiculoView.exibirMenuCadastro(); break;
                    case 2: veiculoView.listarVeiculos(); break;
                    case 3: locacaoView.exibirMenuLocacao(); break;
                    case 4: locacaoView.listarLocacoes(); break;
                    case 0: System.out.println("Encerrando o sistema... Até logo!"); break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
}