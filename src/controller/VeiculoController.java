package controller;

import model.Veiculo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoController {
    private List<Veiculo> listaVeiculos;
    private final String CAMINHO_FICHEIRO = "arquivos/veiculos.dat";

    public VeiculoController() {
        this.listaVeiculos = new ArrayList<>();
        carregarDados(); // sempre que iniciar, carrega os dados
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        listaVeiculos.add(veiculo);
        salvarDados();
    }

    public List<Veiculo> listarVeiculos() {
        return listaVeiculos;
    }

    // ==========================================
    // PERSISTÊNCIA DE DADOS (SERIALIZAÇÃO)
    // ==========================================
    
    private void salvarDados() {
        // verifica se a pasta 'arquivos' existe, se nao, cria
        File diretorio = new File("arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }

        // grava a lista de veiculos no arquivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_FICHEIRO))) {
            oos.writeObject(listaVeiculos);
        } catch (IOException e) {
            System.out.println("Erro ao guardar os veículos no ficheiro: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File ficheiro = new File(CAMINHO_FICHEIRO);
        if (ficheiro.exists()) {
            // le o arquivo e carrega a lista de veiculos
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiro))) {
                listaVeiculos = (List<Veiculo>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar os veículos: " + e.getMessage());
            }
        }
    }
}