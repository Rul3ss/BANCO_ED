public class No_conta {
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
