import java.io.Serializable;

public class No_conta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Conta_Bancaria conta;
    private No_conta proximo_no;

    public No_conta(Conta_Bancaria conta) {
        this.conta = conta;
        this.proximo_no = null;
    }
    public Conta_Bancaria getConta() {
            return conta;
        }
    
    public No_conta getProximo_no() {
            return proximo_no;
        }
    
    public void setProximo_no(No_conta proximo_no) {
            this.proximo_no = proximo_no;
        }



}
