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

    public VeiculoView(Scanner scanner) {
        this.scanner = scanner;
        this.veiculoController = new VeiculoController();
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
}