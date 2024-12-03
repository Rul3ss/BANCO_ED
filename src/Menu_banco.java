import java.util.Scanner;

public class Menu_banco {
    private Banco banco; // O banco que gerencia os clientes e contas

    // Construtor
    public Menu_banco(Banco banco) {
        this.banco = banco;
    }

    // Método para exibir o menu principal
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== Menu Banco =====");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar Contas");
            System.out.println("5. Sair");
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
                    transferir(scanner);
                    break;
                case 4:
                    consultarContas(scanner);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // Método para criar um cliente
    private void criarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        banco.criarCliente(nome, cpf);
    }

    // Método para criar uma conta para um cliente existente
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

    // Método para realizar transferência entre contas
    private void transferir(Scanner scanner) {
        System.out.print("Digite o número da conta de origem: ");
        String origem = scanner.nextLine();

        System.out.print("Digite o número da conta de destino: ");
        String destino = scanner.nextLine();

        System.out.print("Digite o valor a transferir: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        Conta_Bancaria contaOrigem = banco.buscarConta(origem);
        Conta_Bancaria contaDestino = banco.buscarConta(destino);

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Uma ou ambas as contas não foram encontradas.");
            return;
        }

        contaOrigem.transferir(valor, contaDestino);
    }

    // Método para consultar contas de um cliente
    private void consultarContas(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        cliente.consultarContas();
    }
}
