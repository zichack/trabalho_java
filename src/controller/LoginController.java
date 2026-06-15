package controller;

import model.Usuario;

public class LoginController {
    private int tentativasErradas;
    private final int LIMITE_TENTATIVAS = 3;

    public LoginController() {
        this.tentativasErradas = 0;
    }

    // método que view chama para autenticar usuario
    public boolean autenticar(Usuario usuarioCandidato, String loginDigitado, String senhaDigitada) throws Exception {
        if (tentativasErradas >= LIMITE_TENTATIVAS) {
            throw new Exception("Conta bloqueada. Atingiu o limite de tentativas. Deve registar uma nova senha.");
        }

        if (usuarioCandidato.getLogin().equals(loginDigitado) && usuarioCandidato.getSenha().equals(senhaDigitada)) {
            tentativasErradas = 0; // reseta as tentativas erradas depois de um login bem sucedido
            return true;
        } else {
            tentativasErradas++;
            throw new Exception("Credenciais inválidas. Tentativas restantes: " + (LIMITE_TENTATIVAS - tentativasErradas));
        }
    }

    // metodo para redefinir senha, que não pode ser igual as ultimas 3 senhas
    public void redefinirSenha(Usuario usuario, String novaSenha) throws Exception {
        int tamanhoHistorico = usuario.getHistoricoSenhas().size();
        
        // verifica os ultimos 3 registros de senha
        int inicioBusca = Math.max(0, tamanhoHistorico - 3);

        for (int i = inicioBusca; i < tamanhoHistorico; i++) {
            if (usuario.getHistoricoSenhas().get(i).equals(novaSenha)) {
                throw new Exception("Erro: A nova senha não pode ser igual a nenhuma das últimas 3 senhas registadas.");
            }
        }

        // passando as validações, adiciona nova senha ao historico
        usuario.adicionarNovaSenha(novaSenha);
        tentativasErradas = 0; 
    }
}