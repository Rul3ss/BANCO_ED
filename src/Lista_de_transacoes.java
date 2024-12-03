public class Lista_de_transacoes {
    private Node_linked_list comeco_do_no;

    public Lista_de_transacoes() {
        this.comeco_do_no = null;
    }

    public void adicionar(Transacao transacao) {
        Node_linked_list novoNo = new Node_linked_list(transacao);
                if (comeco_do_no == null) {
                comeco_do_no = novoNo; // Se a lista estiver vazia, o novo nó é o primeiro
        } else {
            Node_linked_list atual = comeco_do_no;
            while (atual.getProximo_no() != null) {
                atual = atual.getProximo_no(); // Percorre até o último nó
            }
            atual.setProximo_no(novoNo); // Adiciona o novo nó ao final
        }
    }
    public void exibir() {
        if (comeco_do_no == null) {
            System.out.println("Nenhuma transação realizada.");
            return;
        }
        Node_linked_list atual = comeco_do_no;
        System.out.println("Histórico de Transações:");
        while (atual != null) {
            System.out.println(atual.getTransacao());
            atual = atual.getProximo_no(); // Vai para o próximo nó
        }
    }
}
