public class Lista_contas {
    private No_conta começo_lista;

    public Lista_contas() {
        this.começo_lista = null;
    }
    
    public No_conta getComecoLista() {

        return começo_lista;

    }

    public void setComeço_lista(No_conta começo_lista) {
        this.começo_lista = começo_lista;
    }

    public void adicionar_conta(Conta_Bancaria conta) {
        No_conta novoNo = new No_conta(conta);
        if (começo_lista == null) {
            começo_lista = novoNo; // Se a lista estiver vazia, o novo nó será o primeiro
        } else {
            No_conta atual = começo_lista;
            while (atual.getProximo_no() != null) {
                atual = atual.getProximo_no(); // Percorre até o final
            }
            atual.setProximo_no(novoNo); // Adiciona o novo nó ao final
        }
        System.out.println("Conta adicionada com sucesso: " + conta.getNumero_da_conta());
    }

    public boolean remover_conta(String numeroConta) {
        if (começo_lista == null) {
            return false; // Lista vazia
        }
        // Se a conta a remover estiver no primeiro nó
        if (começo_lista.getConta().getNumero_da_conta().equals(numeroConta)) {
            começo_lista = começo_lista.getProximo_no(); // Move o ponteiro para o próximo nó
            return true;
        }
        No_conta atual = começo_lista;
        while (atual.getProximo_no() != null) {
            if (atual.getProximo_no().getConta().getNumero_da_conta().equals(numeroConta)) {
                atual.setProximo_no(atual.getProximo_no().getProximo_no()); // Remove o nó da lista
                return true;
            }
            atual = atual.getProximo_no();
        }
        return false; // Conta não encontrada
    }
    public void listar() {
        if (começo_lista == null) {
            System.out.println("Nenhuma conta associada.");
            return;
        }
        No_conta atual = começo_lista;
        System.out.println("Contas associadas:" );
        while (atual != null) {
            Conta_Bancaria conta = atual.getConta();
            System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Tipo: " + conta.getTipo_de_conta() + ", Saldo: R$ " + conta.getSaldo());
            atual = atual.getProximo_no();
        }
    }
}
