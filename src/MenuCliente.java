import java.util.Scanner;
import java.io.Serializable;

public class MenuCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private Banco banco;
    private String cpf_atual;

    // Construtor
    public MenuCliente(Banco banco) {
        this.banco = banco;
    }

    // Exibir menu do cliente
    public void exibirMenuCliente(Scanner scanner) {
        System.out.println("======================================");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("======================================");
            System.out.println("Cliente não encontrado.");
            return;
        }
        cpf_atual = cpf;

        boolean executando = true;
        while (executando) {
            System.out.println("\n======================================");
            System.out.println("||          Menu Cliente             ||");
            System.out.println("======================================");
            System.out.println("||  1  ||  Transferir                ||");
            System.out.println("||  2  ||  Sacar                     ||");
            System.out.println("||  3  ||  Depositar                 ||");
            System.out.println("||  4  ||  Ver Extrato               ||");
            System.out.println("||  5  ||  Consultar saldo           ||");
            System.out.println("||  6  ||  Sair para o Menu Principal||");
            System.out.println("======================================");
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
                    consultarSaldo(cliente);
                    break;
                case 6:
                    System.out.println("Retornando ao Menu Principal...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void consultarSaldo(Cliente cliente) {
        Conta_Bancaria contaOrigem = banco.buscarContaPorCPF(cpf_atual);
        String origem = contaOrigem.getNumero_da_conta();
        System.out.println("======================================");
        System.out.println("Saldo atual da conta " + origem + ": " + contaOrigem.getSaldo());
        System.out.println("======================================");
    }

    private void transferir(Scanner scanner, Cliente cliente) {
        Conta_Bancaria contaOrigem = banco.buscarContaPorCPF(cpf_atual);
        String origem = contaOrigem.getNumero_da_conta(); // Obtém o número da conta a partir da conta encontrada
        System.out.println("======================================");
        System.out.println("Conta de origem: " + origem);
        System.out.println("======================================");
        
        System.out.print("Digite o número da conta de destino: ");
        String destino = scanner.nextLine();

        Conta_Bancaria contaDestino = banco.buscarConta(destino);
        if (contaDestino == null) {
            System.out.println("======================================");
            System.out.println("A conta de Destino está incorreta ou não existe.");
            return;
        }
        System.out.println("======================================");
        System.out.print("Digite o valor a transferir: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("======================================");
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); // descarta a entrada inválida
            System.out.println("======================================");
            System.out.print("Digite o valor a transferir: ");
        }
        
        double valor = scanner.nextDouble();
        scanner.nextLine();
        
        while (valor <= 0) {
            System.out.println("======================================");
            System.out.println("Valor inválido. Por favor, digite um valor positivo.");
            System.out.println("======================================");
            System.out.print("Digite o valor a transferir: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("======================================");
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); // descarta a entrada inválida
                System.out.println("======================================");
                System.out.print("Digite o valor a transferir: ");
            }
            valor = scanner.nextDouble();
            scanner.nextLine();
        }       

        contaOrigem.transferir(valor, contaDestino);
    }

    private void sacar(Scanner scanner, Cliente cliente) {
        Conta_Bancaria contaOrigem = banco.buscarContaPorCPF(cpf_atual);       
        System.out.println("======================================");
        System.out.print("Digite o valor a sacar: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        contaOrigem.sacar(valor);
    }

    private void depositar(Scanner scanner, Cliente cliente) {

        boolean executando = true;
        while (executando){
        System.out.println("======================================");
        System.out.println("1 - Depositar na propria conta");
        System.out.println("2 - Depositar em outra conta");
        System.out.println("3 - Rotornar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();
        if(opcao == 3){return;}

        else if (opcao == 2){    
        System.out.println("======================================");   
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        Conta_Bancaria conta = banco.buscarConta(numeroConta);
        if (conta == null) {
            System.out.println("======================================");
            System.out.println("Conta não encontrada.");
            return;
        }
        System.out.println("======================================");
        System.out.print("Digite o valor a depositar na conta " + numeroConta + ": ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        conta.depositar(valor);
        return;
    }else {
        Conta_Bancaria contaOrigem = banco.buscarContaPorCPF(cpf_atual); 
        System.out.println("======================================");
        System.out.print("Digite o valor a depositar na sua conta: " + contaOrigem.getNumero_da_conta() + ": ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        contaOrigem.depositar(valor);
        return;
    }
    }}
    private void consultarExtrato(Scanner scanner, Cliente cliente) {
        Conta_Bancaria contaOrigem = banco.buscarContaPorCPF(cpf_atual);
        String origem = contaOrigem.getNumero_da_conta();

        Conta_Bancaria conta = banco.buscarConta(origem);
        if (conta == null) {           
            return;
        }

        conta.exibirHistoricoTransacoes();
    }
}
