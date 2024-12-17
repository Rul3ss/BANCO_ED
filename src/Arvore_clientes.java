import java.io.Serializable;
public class Arvore_clientes implements Serializable {
    private static final long serialVersionUID = 1L;

        private No_arvore_cliente raiz; // Raiz da árvore
    
        // Construtor
        public Arvore_clientes() {
            this.raiz = null; // Inicialmente, a árvore está vazia
        }
    
        // Método para adicionar um cliente
        public void adicionar(Cliente cliente) {
            raiz = adicionarRecursivo(raiz, cliente);
        }
    
        private No_arvore_cliente adicionarRecursivo(No_arvore_cliente atual, Cliente cliente) {
            if (atual == null) {
                return new No_arvore_cliente(cliente); // Insere o cliente em um novo nó
            }
    
            if (cliente.getCpf().compareTo(atual.getCliente().getCpf()) < 0) {
                atual.setEsquerdo(adicionarRecursivo(atual.getEsquerdo(), cliente)); // Adiciona à esquerda
            } else if (cliente.getCpf().compareTo(atual.getCliente().getCpf()) > 0) {
                atual.setDireito(adicionarRecursivo(atual.getDireito(), cliente)); // Adiciona à direita
            }
            return atual;
        }
            // Método para buscar um cliente pelo CPF
        public Cliente buscar(String cpf) {
            return buscarRecursivo(raiz, cpf);
        }
    
        private Cliente buscarRecursivo(No_arvore_cliente atual, String cpf) {
            if (atual == null) {
                return null; // Cliente não encontrado
            }
    
            if (cpf.equals(atual.getCliente().getCpf())) {
                return atual.getCliente();
            }
    
            if (cpf.compareTo(atual.getCliente().getCpf()) < 0) {
                return buscarRecursivo(atual.getEsquerdo(), cpf); // Busca à esquerda
            } else {
                return buscarRecursivo(atual.getDireito(), cpf); // Busca à direita
            }
        }
    
        // Método para exibir os clientes em ordem (ordem crescente de CPF)
        public void exibirEmOrdem() {
            exibirEmOrdemRecursivo(raiz);
        }
    
        private void exibirEmOrdemRecursivo(No_arvore_cliente atual) {
            if (atual != null) {
                exibirEmOrdemRecursivo(atual.getEsquerdo());
                Cliente cliente = atual.getCliente();
                System.out.println("------- Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
                cliente.consultarContas(); // Exibe as contas associadas ao cliente
                exibirEmOrdemRecursivo(atual.getDireito());
            }
        }
    }
