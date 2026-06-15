package view;

import controller.VeiculoController;
import model.Carro;
import model.Moto;
import model.Veiculo;
import java.util.Scanner;
import java.util.List;

public class VeiculoView {
    private Scanner scanner;
    private VeiculoController veiculoController;

    public VeiculoView(Scanner scanner, VeiculoController veiculoController) {
        this.scanner = scanner;
        this.veiculoController = veiculoController;
    }

    public void exibirMenuCadastro() {
        System.out.println("\n--- CADASTRO DE VEÍCULO ---");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.print("Tipo de veículo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Valor Base da Diária (Ex: 150.0): ");
        double valor = Double.parseDouble(scanner.nextLine());

        if (tipo == 1) {
            System.out.print("Quantidade de portas: ");
            int portas = Integer.parseInt(scanner.nextLine());
            System.out.print("Tem ar condicionado? (S/N): ");
            boolean ar = scanner.nextLine().equalsIgnoreCase("S");
            
            Carro carro = new Carro(placa, marca, modelo, valor, portas, ar);
            veiculoController.cadastrarVeiculo(carro);
            System.out.println("Carro cadastrado com sucesso e salvo no arquivo .dat!");
            
        } else if (tipo == 2) {
            System.out.print("Cilindradas: ");
            int cilindradas = Integer.parseInt(scanner.nextLine());
            
            Moto moto = new Moto(placa, marca, modelo, valor, cilindradas);
            veiculoController.cadastrarVeiculo(moto);
            System.out.println("Moto cadastrada com sucesso e salva no arquivo .dat!");
        }
    }

    public void listarVeiculos() {
        System.out.println("\n--- LISTA DE VEÍCULOS ---");
        List<Veiculo> veiculos = veiculoController.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo v : veiculos) {
                System.out.println(v.toString());
            }
        }
    }

    // ==========================================
    // TELAS DE EDIÇÃO E EXCLUSÃO
    // ==========================================

    public void editarVeiculo() {
        System.out.println("\n--- ALTERAÇÃO DE VEÍCULO ---");
        System.out.print("Digite a placa do veículo que deseja editar: ");
        String placa = scanner.nextLine();

        Veiculo v = veiculoController.buscarPorPlaca(placa);
        
        if (v == null) {
            System.out.println("ERRO: Veículo com a placa " + placa + " não foi encontrado.");
            return;
        }

        System.out.println("Veículo encontrado: " + v.getMarca() + " " + v.getModelo());
        System.out.println("Valor atual da diária: R$ " + v.getValorBaseDiaria());
        System.out.print("Digite o novo valor da diária (Ex: 180.0): ");
        
        try {
            double novoValor = Double.parseDouble(scanner.nextLine());
            veiculoController.editarVeiculo(placa, novoValor);
            System.out.println("Veículo atualizado com sucesso e salvo no arquivo!");
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Valor inválido. A edição foi cancelada.");
        }
    }

    public void excluirVeiculo() {
        System.out.println("\n--- EXCLUSÃO DE VEÍCULO ---");
        System.out.print("Digite a placa do veículo que deseja excluir: ");
        String placa = scanner.nextLine();

        // confirmação de segurança
        System.out.print("Tem certeza que deseja excluir o veículo placa " + placa + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            boolean removido = veiculoController.excluirVeiculo(placa);
            if (removido) {
                System.out.println("Veículo excluído com sucesso e arquivo atualizado!");
            } else {
                System.out.println("ERRO: Não foi possível excluir. Verifique se a placa está correta ou se o veículo está atualmente alugado.");
            }
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }
}