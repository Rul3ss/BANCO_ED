import java.util.ArrayList;
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
            System.out.println("5. Buscar Cliente e Mostrar Informações");
            System.out.println("6. Sair para o Menu Principal");
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
                    buscarClienteEExibirInformacoes(scanner);
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


    private void criarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
    
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
    
        if (banco.buscarCliente(cpf) != null) {
            System.out.println("Já existe um cliente com este CPF.");
            return;
        }    
        banco.criarCliente(nome, cpf);
        System.out.println("Cliente criado com sucesso!");
    }

    private void criarConta(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
    
        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }    
        if (banco.clienteJaPossuiConta(cpf)) {
            System.out.println("O cliente já possui uma conta.");
            return;
        }
            
        System.out.print("Digite o tipo da conta (Corrente ou Poupança): ");
        String tipoConta = scanner.nextLine();
    
        System.out.print("Digite o saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha
    
        Conta_Bancaria novaConta = new Conta_Bancaria(cliente.getNome(), cliente.getCpf(), saldo, tipoConta);
        banco.adicionarConta(novaConta);
    
        
    }

    private void buscarClienteEExibirInformacoes(Scanner scanner) {
    System.out.print("Digite o nome do titular: ");
    String nomeTitular = scanner.nextLine();

    ArrayList<Conta_Bancaria> contas = banco.buscarContasPorTitular(nomeTitular);
    if (contas.isEmpty()) {
        System.out.println("Nenhuma conta associada ao titular " + nomeTitular + ".");
    } else {
        System.out.println("Informações do Titular: " + nomeTitular);
        System.out.println("Contas associadas:");
        for (Conta_Bancaria conta : contas) {
            System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Tipo: " + conta.getTipo_de_conta() + ", Saldo: R$ " + conta.getSaldo());
        }
    }
}
}
