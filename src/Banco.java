public class Banco {
    private String nomeBanco;
    private Arvore_clientes clientes; // Árvore binária para armazenar os clientes
    private Lista_contas contas;      // Lista encadeada para armazenar contas

    // Construtor
    public Banco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.clientes = new Arvore_clientes();
        this.contas = new Lista_contas();
    }

    // Método para criar um novo cliente
    public Cliente criarCliente(String nome, String cpf) {
        Cliente novoCliente = new Cliente(nome, cpf);
        clientes.adicionar(novoCliente); // Adiciona o cliente à árvore
        System.out.println("Cliente criado: " + nome + " (CPF: " + cpf + ")");
        return novoCliente;
    }

    public Cliente buscarCliente(String cpf) {
        Cliente cliente = clientes.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente com CPF " + cpf + " não encontrado.");
        }
        return cliente;
    }

    public void adicionarConta(Conta_Bancaria conta) {
        contas.adicionar_conta(conta);
    }

    public Conta_Bancaria buscarConta(String numeroConta) {
        No_conta atual = contas.getComecoLista(); // Percorre a lista encadeada de contas
        while (atual != null) {
            Conta_Bancaria conta = atual.getConta();
            if (conta.getNumero_da_conta().equals(numeroConta)) {
                return conta; // Retorna a conta encontrada
            }
            atual = atual.getProximo_no();
        }
        System.out.println("Conta " + numeroConta + " não encontrada.");
        return null;
    }

    public Arvore_clientes getClientes() {
        return clientes;
    }

    public Lista_contas getContas() {
        return contas;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
}
