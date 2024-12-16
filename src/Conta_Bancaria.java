import java.io.Serializable;

public class Conta_Bancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numero_da_conta;
    private String nome_do_titular;
    private String cpf_do_titular;
    private double saldo;
    private String tipo_de_conta;
    private Lista_de_transacoes historicoTransacoes;
    
    public Conta_Bancaria(String nome_do_titular, String cpf_do_titular, double saldo, String tipo_de_conta) {
        
        this.nome_do_titular = nome_do_titular;
        this.saldo = saldo;
        this.cpf_do_titular = cpf_do_titular;
        this.tipo_de_conta = tipo_de_conta;
        this.historicoTransacoes = new Lista_de_transacoes();
    }

    public String getNumero_da_conta() {
        return numero_da_conta;
    }

    public String getCpf_do_titular() {
        return cpf_do_titular;
    }
    public void setNumero_da_conta(String numero_da_conta) {
        this.numero_da_conta = numero_da_conta;
    }

    public String getNome_do_titular() {
        return nome_do_titular;
    }

    public void setNome_do_titular(String nome_do_titular) {
        this.nome_do_titular = nome_do_titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) { // Validação para garantir que o saldo não seja negativo
            this.saldo = saldo;
        } else {
            System.out.println("Saldo não pode ser negativo!");
        }
    }
    public String getTipo_de_conta() {
        return tipo_de_conta;
    }

    public void setTipo_de_conta(String tipo_de_conta) {
        this.tipo_de_conta = tipo_de_conta;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso! Novo saldo: " + saldo);
            historicoTransacoes.adicionar(new Transacao("Depósito", valor, null, numero_da_conta)); // Adiciona ao histórico
        } else {
            System.out.println("Valor inválido para depósito. Insira um valor maior que zero.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0) {
            if (valor <= saldo) {
                saldo -= valor;
                System.out.println("Saque realizado com sucesso! Novo saldo: " + saldo);
                // Passa null para contaDestino
                historicoTransacoes.adicionar(new Transacao("Saque", valor, numero_da_conta, null));
            } else {
                System.out.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.out.println("Valor inválido para saque. Insira um valor maior que zero.");
        }
    }

public double consultarSaldo() {
    System.out.println("Saldo atual: " + saldo);
    return saldo;
}

public void transferir(double valor, Conta_Bancaria contaDestino) {
    if (contaDestino == null) {
        System.out.println("Conta de destino inválida.");
        return;
    }

    if (this.numero_da_conta.equals(contaDestino.getNumero_da_conta())) {
        System.out.println("Transferência não permitida para a mesma conta.");
        return;
    }

    if (valor > 0) {
        if (valor <= saldo) {
            saldo -= valor; // Deduz o valor da conta de origem
            contaDestino.saldo += valor; // Adiciona o valor diretamente à conta de destino

            System.out.println("Transferência de R$ " + valor + " realizada com sucesso para a conta " + contaDestino.getNumero_da_conta());
            System.out.println("Novo saldo da conta de origem: " + saldo);

            // Registra a transação no histórico da conta de origem
            historicoTransacoes.adicionar(new Transacao("Transferência Enviada", valor, numero_da_conta, contaDestino.getNumero_da_conta()));

            // Registra a transação no histórico da conta de destino
            contaDestino.historicoTransacoes.adicionar(new Transacao("Transferência Recebida", valor, numero_da_conta, contaDestino.getNumero_da_conta()));
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência.");
        }
    } else {
        System.out.println("Valor inválido para transferência. Insira um valor maior que zero.");
    }
}

public void exibirHistoricoTransacoes() {
    historicoTransacoes.exibir();
}


}
