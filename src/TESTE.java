public class TESTE {
    public static void main(String[] args) {
        // Criando o banco
        Banco banco = new Banco("Banco Exemplo");

        // Criando clientes
        Cliente cliente1 = banco.criarCliente("João Silva", "12345678900");
        Cliente cliente2 = banco.criarCliente("Maria Oliveira", "98765432100");

        // Criando contas
        Conta_Bancaria conta1 = new Conta_Bancaria("12345", "João Silva", 1500.0, "Corrente");
        Conta_Bancaria conta2 = new Conta_Bancaria("67890", "Maria Oliveira", 2000.0, "Poupança");

        // Associando contas aos clientes
        cliente1.adicionarConta(conta1);
        cliente2.adicionarConta(conta2);

        // Adicionando contas ao banco
        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);

        // Criando relatório
        Relatorio relatorio = new Relatorio(banco);

        // Gerar relatório de clientes
        relatorio.gerarRelatorioClientes();

        // Gerar relatório de contas
        relatorio.gerarRelatorioContas();
    }
}
