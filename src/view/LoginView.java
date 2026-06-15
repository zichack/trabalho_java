package view;

import controller.LoginController;
import model.Funcionario;
import java.util.Scanner;

public class LoginView {
    private LoginController loginController;
    private Scanner scanner;
    private Funcionario usuarioPadrao; // usuario teste

    public LoginView() {
        this.loginController = new LoginController();
        this.scanner = new Scanner(System.in);
        // criando usuario padrao para teste
        this.usuarioPadrao = new Funcionario("Admin", "000.000.000-00", "admin", "1234", "MAT001");
    }

    public void exibirTelaLogin() {
        boolean autenticado = false;

        System.out.println("=================================");
        System.out.println("=SISTEMA DE LOCADORA DE VEÍCULOS=");
        System.out.println("=================================");

        while (!autenticado) {
            try {
                System.out.print("Login: ");
                String login = scanner.nextLine();
                
                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                // controller valida o login e senha
                autenticado = loginController.autenticar(usuarioPadrao, login, senha);
                
                if (autenticado) {
                    System.out.println("Autenticação realizada com sucesso!\n");
                    // exibe menu principal
                    MenuView menu = new MenuView(scanner);
                    menu.exibirMenuPrincipal();
                }

            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
                // se erro for bloqueio ou limite de tentativas, inicia metodo de redefinição de senha
                if (e.getMessage().contains("bloqueada") || e.getMessage().contains("limite")) {
                    redefinirSenha();
                }
            }
        }
    }

    private void redefinirSenha() {
        boolean senhaRedefinida = false;
        while (!senhaRedefinida) {
            System.out.println("\n--- REDEFINIÇÃO DE SENHA ---");
            System.out.print("Digite a nova senha: ");
            String novaSenha = scanner.nextLine();

            try {
                loginController.redefinirSenha(usuarioPadrao, novaSenha);
                System.out.println("Senha redefinida com sucesso! Você pode entrar novamente.\n");
                senhaRedefinida = true;
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }
}