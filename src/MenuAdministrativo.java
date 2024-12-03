import java.util.Scanner;

public class MenuAdministrativo {
    private Banco banco;
    private static final String SENHA_ADMINISTRADOR = "999888"; // Senha fixa para acesso administrativo

    // Construtor
    public MenuAdministrativo(Banco banco) {
        this.banco = banco;
    }

    // Exibir menu administrativo com autenticação
    public void exibirMenuAdministrativo(Scanner scanner) {
        System.out.print("Digite a senha de administrador: ");
        String senha = scanner.nextLine();

        if (!senha.equals(SENHA_ADMINISTRADOR)) {
            System.out.println("Senha incorreta. Acesso negado.");
            return;
        }

        boolean executando = true;
        while (executando) {
            System.out.println("\n===== Menu Administrativo =====");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Exibir Relatório de Clientes");
            System.out.println("4. Exibir Relatório de Contas");
            System.out.println("5. Sair para o Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    criarCliente(scanner);
                    break;
                case 2:
                    criarConta(scanner);
                    break;
                case 3:
                    new Relatorio(banco).gerarRelatorioClientes();
                    break;
                case 4:
                    new Relatorio(banco).gerarRelatorioContas();
                    break;
                case 5:
                    System.out.println("Retornando ao Menu Principal...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void criarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        banco.criarCliente(nome, cpf);
    }

    private void criarConta(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        System.out.print("Digite o tipo da conta (Corrente ou Poupança): ");
        String tipoConta = scanner.nextLine();

        System.out.print("Digite o saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        Conta_Bancaria novaConta = new Conta_Bancaria(numeroConta, cliente.getNome(), saldo, tipoConta);
        cliente.adicionarConta(novaConta);
        banco.adicionarConta(novaConta);

        System.out.println("Conta criada com sucesso!");
    }
}
