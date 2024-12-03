public class Conta_Bancaria {

    private String numero_da_conta;
    private String nome_do_titular;
    private double saldo;
    private String tipo_de_conta;
    
    public Conta_Bancaria(String numero_da_conta, String nome_do_titular, double saldo, String tipo_de_conta) {
        this.numero_da_conta = numero_da_conta;
        this.nome_do_titular = nome_do_titular;
        this.saldo = saldo;
        this.tipo_de_conta = tipo_de_conta;
    }

    public String getNumero_da_conta() {
        return numero_da_conta;
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
        saldo += valor; // Adiciona o valor ao saldo atual
        System.out.println("Depósito realizado com sucesso! Novo saldo: " + saldo);
    } else {
        System.out.println("Valor inválido para depósito. Insira um valor maior que zero.");
    }
}

public void sacar(double valor) {
    if (valor > 0) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso! Novo saldo: " + saldo);
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



}
