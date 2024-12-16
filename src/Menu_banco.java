import java.util.Scanner;
import java.io.Serializable;

public class Menu_banco implements Serializable{
    private static final long serialVersionUID = 1L;
    private Banco banco;

    // Construtor
    public Menu_banco(Banco banco) {
        this.banco = banco;
    }

    // Exibir menu principal
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== Menu Banco =====");
            System.out.println("1. Menu Administrativo");
            System.out.println("2. Menu do Cliente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    new MenuAdministrativo(banco).exibirMenuAdministrativo(scanner);
                    break;
                case 2:
                    new MenuCliente(banco).exibirMenuCliente(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}

