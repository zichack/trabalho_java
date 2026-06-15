package view;

import controller.LocacaoController;
import controller.VeiculoController;
import model.Cliente;
import model.Funcionario;
import model.Locacao;
import model.Veiculo;
import java.util.Scanner;
import java.util.List;

public class LocacaoView {
    private Scanner scanner;
    private LocacaoController locacaoController;
    private VeiculoController veiculoController;

    public LocacaoView(Scanner scanner) {
        this.scanner = scanner;
        this.locacaoController = new LocacaoController();
        this.veiculoController = new VeiculoController();
    }

    public void exibirMenuLocacao() {
        System.out.println("\n--- CADASTRO DE LOCAÇÃO ---");

        // simulação de dados
        Cliente clienteTeste = new Cliente("João Silva", "111.222.333-44", "joao", "123", "987654321", "B");
        Funcionario funcTeste = new Funcionario("Admin", "000.000.000-00", "admin", "123", "MAT001");

        List<Veiculo> veiculos = veiculoController.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Não existem veículos cadastrados no arquivo para realizar a locação.");
            return;
        }

        System.out.println("Veículos Cadastrados:");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(i + " - " + veiculos.get(i).getMarca() + " " + veiculos.get(i).getModelo()
                    + " | Status: " + (veiculos.get(i).isDisponivel() ? "Livre" : "Alugado"));
        }

        System.out.print("Escolha o índice numérico do veículo desejado: ");
        int indice = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade de dias da locação: ");
        int dias = Integer.parseInt(scanner.nextLine());

        Veiculo veiculoEscolhido = veiculos.get(indice);

        if (!veiculoEscolhido.isDisponivel()) {
            System.out.println("ERRO: Este veículo já se encontra alugado no momento!");
            return;
        }

        // instancia a classe composta
        int novoId = locacaoController.listarLocacoes().size() + 1;
        Locacao locacao = new Locacao(novoId, clienteTeste, funcTeste, veiculoEscolhido, dias);

        locacaoController.registrarLocacao(locacao);

        System.out.println("Locação cadastrada com sucesso! Valor Total a pagar: R$ " + locacao.getValorTotal());
    }

    public void listarLocacoes() {
        System.out.println("\n--- RELATÓRIO ENVOLVENDO MÚLTIPLAS CLASSES ---");
        List<Locacao> locacoes = locacaoController.listarLocacoes();
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação cadastrada.");
        } else {
            for (Locacao l : locacoes) {
                System.out.println(l.toString());
                System.out.println("-----------------------------------");
            }
        }
    }
}