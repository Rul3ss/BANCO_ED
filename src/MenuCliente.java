import java.util.Scanner;

public class MenuCliente {
    private Banco banco;

    // Construtor
    public MenuCliente(Banco banco) {
        this.banco = banco;
    }

    // Exibir menu do cliente
    public void exibirMenuCliente(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        boolean executando = true;
        while (executando) {
            System.out.println("\n===== Menu Cliente =====");
            System.out.println("1. Transferir");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Ver Extrato");
            System.out.println("5. Sair para o Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    transferir(scanner, cliente);
                    break;
                case 2:
                    sacar(scanner, cliente);
                    break;
                case 3:
                    depositar(scanner, cliente);
                    break;
                case 4:
                    consultarExtrato(scanner, cliente);
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

    private void transferir(Scanner scanner, Cliente cliente) {
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

    private void sacar(Scanner scanner, Cliente cliente) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        Conta_Bancaria conta = banco.buscarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Digite o valor a sacar: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        conta.sacar(valor);
    }

    private void depositar(Scanner scanner, Cliente cliente) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        Conta_Bancaria conta = banco.buscarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Digite o valor a depositar: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        conta.depositar(valor);
    }

    private void consultarExtrato(Scanner scanner, Cliente cliente) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        Conta_Bancaria conta = banco.buscarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        conta.exibirHistoricoTransacoes();
    }
}
