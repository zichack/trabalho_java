package controller;

import model.Locacao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocacaoController {
    private List<Locacao> listaLocacoes;
    private final String CAMINHO_ARQUIVO = "arquivos/locacoes.dat";

    public LocacaoController() {
        this.listaLocacoes = new ArrayList<>();
        // carrega a lista de locações
        carregarDados(); 
    }

    public void registrarLocacao(Locacao locacao) {
        listaLocacoes.add(locacao);
        // salva a lista em um arquivo para persistencia
        salvarDados(); 
    }

    public List<Locacao> listarLocacoes() {
        return listaLocacoes;
    }

    // ==========================================
    // PERSISTÊNCIA DE DADOS (SERIALIZAÇÃO)
    // ==========================================
    
    private void salvarDados() {
        File diretorio = new File("arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {
            oos.writeObject(listaLocacoes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar locações no arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                listaLocacoes = (List<Locacao>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar locações: " + e.getMessage());
            }
        }
    }
}