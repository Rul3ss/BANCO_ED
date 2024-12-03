import java.time.LocalDateTime;
public class Transacao {
    private String tipo_de_transacao;
    private double valor;
    private LocalDateTime data_da_transacao;
    private String conta_de_origem;
    private String conta_de_destino;

    public Transacao(String tipo_de_transacao, double valor, String conta_de_origem, String conta_de_destino) {
        this.tipo_de_transacao = tipo_de_transacao;
        this.valor = valor;
        this.data_da_transacao = LocalDateTime.now();
        this.conta_de_origem = conta_de_origem;
        this.conta_de_destino = conta_de_destino;
    }

    public String getTipo_de_transacao() {
        return tipo_de_transacao;
    }

    public void setTipo_de_transacao(String tipo_de_transacao) {
        this.tipo_de_transacao = tipo_de_transacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData_da_transacao() {
        return data_da_transacao;
    }

    public void setData_da_transacao(LocalDateTime data_da_transacao) {
        this.data_da_transacao = data_da_transacao;
    }

    public String getConta_de_origem() {
        return conta_de_origem;
    }

    public void setConta_de_origem(String conta_de_origem) {
        this.conta_de_origem = conta_de_origem;
    }

    public String getConta_de_destino() {
        return conta_de_destino;
    }

    public void setConta_de_destino(String conta_de_destino) {
        this.conta_de_destino = conta_de_destino;
    }

    public String toString() {
        return "Tipo: " + tipo_de_transacao + ", Valor: R$ " + valor + ", Data/Hora: " + data_da_transacao +
                ", Conta Origem: " + (conta_de_origem != null ? conta_de_origem : "N/A") +
                ", Conta Destino: " + (conta_de_destino != null ? conta_de_destino : "N/A");
    }

    // existing fields and
}
