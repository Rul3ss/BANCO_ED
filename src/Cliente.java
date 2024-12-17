import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    String nome;
    String cpf;
    private Lista_contas contas;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new Lista_contas();
}

public String getNome() {
    return nome;
}

public String getCpf() {
    return cpf;
}
public void adicionarConta(Conta_Bancaria conta) {
    contas.adicionar_conta(conta, true);
}
public void removerConta(String numeroConta) {
    boolean removido = contas.remover_conta(numeroConta);
    if (removido) {
        System.out.println("Conta " + numeroConta + " removida com sucesso.");
    } else {
        System.out.println("Conta " + numeroConta + " não encontrada.");
    }}

    public void consultarContas() {
        contas.listar(); // Exibe a lista de contas
    }

    


}
