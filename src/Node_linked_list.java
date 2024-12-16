import java.io.Serializable;
public class Node_linked_list implements Serializable {
    private static final long serialVersionUID = 1L;
    private Transacao transacao;
    private Node_linked_list proximo_no;
    
    public Node_linked_list(Transacao transacao) {
        this.transacao = transacao;
        this.proximo_no = null;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Node_linked_list getProximo_no() {
        return proximo_no;
    }

    public void setProximo_no(Node_linked_list proximo_no) {
        this.proximo_no = proximo_no;
    }

    

}
