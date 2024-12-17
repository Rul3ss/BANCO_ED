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
            System.out.println("\n===================================================");
            System.out.println("||          Menu Administrativo                  ||");
            System.out.println("===================================================");
            System.out.println("||  1  ||  Criar Cliente                         ||");
            System.out.println("||  2  ||  Criar Conta                           ||");
            System.out.println("||  3  ||  Exibir Relatório de Clientes          ||");
            System.out.println("||  4  ||  Exibir Relatório de Contas            ||");
            System.out.println("||  5  ||  Buscar Cliente e Mostrar Informações  ||");
            System.out.println("||  6  ||  Sair para o Menu Principal            ||");
            System.out.println("||  7  ||  Apagar Banco de Dados                 ||");
            System.out.println("||  8  ||  Criar 1000 contas para testes         ||");
            System.out.println("===================================================");
            System.out.print("Escolha uma opção: ");



            int opcao = scanner.nextInt();
            System.out.println("=====================================");
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
                    banco.contarNos();                    
                    break;
                case 4:
                    new Relatorio(banco).gerarRelatorioContas();
                    banco.contarNos();
                    break;
                case 5:
                    buscarClienteEExibirInformacoes(scanner);
                    break;
                case 6:
                    System.out.println("Retornando ao Menu Principal...");
                    executando = false;
                    break;
                case 7:
                    Menu_banco.apagarBancoDeDados("banco_dados.ser");
                    executando = false;
                    break;
                case 8:
                    criar1000Contas();
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
        // if (cliente == null) {
        //     System.out.println("Cliente não encontrado.");
        //     return;
        // }    
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
        cliente.adicionarConta(novaConta);     
        
    }

    private void criar1000Contas(){
        String tipoConta = "Corrente";
        int i = 0;
        while (i < 1000) {
            try {
                Integer cpfRandom = (int) (Math.random() * 1000000000);
                String cpfString = cpfRandom.toString();
    
                Integer numeroConta = (int) (Math.random() * 10000000);
                String numeroContaString = numeroConta.toString();
    
                Double saldo = (double) (Math.random() * 100);
                String nome = "Cliente " + i;
    
                if (banco.clienteJaPossuiConta(cpfString)) {
                    return;
                }
    
                if(banco.buscarConta(numeroContaString) != null){
                    return;
                }
    
                Cliente cliente = banco.criarCliente(nome, cpfString);
    
                Conta_Bancaria novaConta = new Conta_Bancaria(nome, numeroContaString, saldo, tipoConta);
                banco.adicionarConta(novaConta);
                cliente.adicionarConta(novaConta);  
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally{
                i++;
            }
        }
        System.out.println("+-10000 contas criadas com sucesso!");
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
