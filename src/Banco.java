import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeBanco;
    private Arvore_clientes clientes; // Árvore binária para armazenar os clientes
    private Lista_contas contas;      // Lista encadeada para armazenar contas
    private transient AtomicInteger contadorContas;

    // Construtor
    public Banco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.clientes = new Arvore_clientes();
        this.contas = new Lista_contas();
        this.contadorContas = new AtomicInteger(1000);
    }
    public Conta_Bancaria buscarContaPorCPF(String cpf) {
        for (Conta_Bancaria conta : converterParaLista()) {
            if (cpf.equals(conta.getCpf_do_titular())) {
                return conta;
            }
        }
        return null; // Retorna null se nenhuma conta for encontrada para o CPF fornecido
    }
    public String gerarNumeroConta() {
        return String.valueOf(contadorContas.getAndIncrement());
    }
    public void salvarDados(String caminhoArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(this);
        }
    }
    public static Banco carregarDados(String caminhoArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (Banco) ois.readObject();
        }
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.contadorContas = new AtomicInteger(1000); // Re-inicializar o contador de contas após a desserialização
    }
    // Método para criar um novo cliente
    public Cliente criarCliente(String nome, String cpf) {
        Cliente novoCliente = new Cliente(nome, cpf);
        clientes.adicionar(novoCliente); // Adiciona o cliente à árvore       
        return novoCliente;
    }

    public Cliente buscarCliente(String cpf) {
        Cliente cliente = clientes.buscar(cpf);      
        return cliente;
    }

    public void adicionarConta(Conta_Bancaria conta) {
        contas.adicionar_conta(conta, false);
    }

public ArrayList<Conta_Bancaria> converterParaLista() {
    ArrayList<Conta_Bancaria> lista = new ArrayList<>();
    No_conta atual = contas.getComecoLista();
    while (atual != null) {
        lista.add(atual.getConta());
        atual = atual.getProximo_no();
    }
    return lista;
}

public void ordenarContasPorNumero(ArrayList<Conta_Bancaria> lista) {
    Collections.sort(lista, new Comparator<Conta_Bancaria>() {
        @Override
        public int compare(Conta_Bancaria c1, Conta_Bancaria c2) {
            return c1.getNumero_da_conta().compareTo(c2.getNumero_da_conta());
        }
    });
}

public Conta_Bancaria buscarConta(String numeroConta) {
    ArrayList<Conta_Bancaria> lista = converterParaLista();
    ordenarContasPorNumero(lista);

    int esquerda = 0;
    int direita = lista.size() - 1;

    while (esquerda <= direita) {
        int meio = (esquerda + direita) / 2;
        Conta_Bancaria contaMeio = lista.get(meio);
        int comparacao = contaMeio.getNumero_da_conta().compareTo(numeroConta);

        if (comparacao == 0) {
            return contaMeio; // Conta encontrada
        } else if (comparacao < 0) {
            esquerda = meio + 1;
        } else {
            direita = meio - 1;
        }
    }

    System.out.println("Conta " + numeroConta + " não encontrada.");
    return null;
}

public boolean buscarContaBoolean(String numeroConta) {
    ArrayList<Conta_Bancaria> lista = converterParaLista();
    ordenarContasPorNumero(lista);

    int esquerda = 0;
    int direita = lista.size() - 1;

    while (esquerda <= direita) {
        int meio = (esquerda + direita) / 2;
        Conta_Bancaria contaMeio = lista.get(meio);
        int comparacao = contaMeio.getNumero_da_conta().compareTo(numeroConta);

        if (comparacao == 0) {
            return true; // Conta encontrada
        } else if (comparacao < 0) {
            esquerda = meio + 1;
        } else {
            direita = meio - 1;
        }
    }
    return false;
}

public ArrayList<Conta_Bancaria> buscarContasPorTitular(String nomeTitular) {
    ArrayList<Conta_Bancaria> contasDoTitular = new ArrayList<>();
    No_conta atual = contas.getComecoLista();
    while (atual != null) {
        Conta_Bancaria conta = atual.getConta();
        if (conta.getNome_do_titular().equalsIgnoreCase(nomeTitular)) {
            contasDoTitular.add(conta);
        }
        atual = atual.getProximo_no();
    }
    return contasDoTitular;
}

public boolean clienteJaPossuiConta(String cpf) {
    No_conta atual = contas.getComecoLista();
    while (atual != null) {
        Conta_Bancaria conta = atual.getConta();
        if (conta.getCpf_do_titular().equalsIgnoreCase(cpf)) {
            return true;
        }
        atual = atual.getProximo_no();
    }
    return false;
}
    public Arvore_clientes getClientes() {
        return clientes;
    }

    public Lista_contas getContas() {
        return contas;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
}
