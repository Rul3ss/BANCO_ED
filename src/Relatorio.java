public class Relatorio {
    private Banco banco;

    // Construtor
    public Relatorio(Banco banco) {
        this.banco = banco;
    }

    // Método para gerar um relatório de clientes
    public void gerarRelatorioClientes() {
        System.out.println("Relatório de Clientes do Banco " + banco.getNomeBanco() + ":");
        banco.getClientes().exibirEmOrdem(); // Exibe os clientes em ordem de CPF
    }

    // Método para gerar um relatório de contas
    public void gerarRelatorioContas() {
        System.out.println("Relatório de Contas do Banco " + banco.getNomeBanco() + ":");
        banco.getContas().listar(); // Exibe todas as contas cadastradas
    }
}
