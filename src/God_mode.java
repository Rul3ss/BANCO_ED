import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class God_mode {
    private Banco banco;

    public God_mode(Banco banco) {
        this.banco = banco;
    }

    public void exibirMenuSecreto() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("===== Menu Secreto =====");
            System.out.println("1. Ordenar contas por saldo (Bubble Sort)");
            System.out.println("2. Ordenar contas por saldo (Merge Sort)");
            System.out.println("3. Ordenar contas por saldo (Quick Sort)");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                ordenarContasPorNumero();;                    
                    break;
                case 2:
                ordenarContasPorNumeroMergeSort();;
                    break;
                case 3:
                ordenarContasPorNumeroQuickSort();;
                    break;
                case 4:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    private void ordenarContasPorNumero() {
        List<Conta_Bancaria> contas = banco.converterParaLista();
        int n = contas.size();

        long startTime = System.currentTimeMillis(); // Captura o tempo de início

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                int numeroConta1 = Integer.parseInt(contas.get(j).getNumero_da_conta());
                int numeroConta2 = Integer.parseInt(contas.get(j + 1).getNumero_da_conta());
                if (numeroConta1 > numeroConta2) {
                    // Troca as contas
                    Conta_Bancaria temp = contas.get(j);
                    contas.set(j, contas.get(j + 1));
                    contas.set(j + 1, temp);
                }
            }
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo de término
        long executionTime = endTime - startTime; // Calcula o tempo de execução

        // Imprime as contas ordenadas
        System.out.println("Contas ordenadas pelo Bubble Sort por número da conta:");
        for (Conta_Bancaria conta : contas) {
            System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Saldo: R$ " + conta.getSaldo());
        }

        // Imprime o tempo de execução
        System.out.println("Tempo de execução: " + executionTime + " ms");
    }
    private void ordenarContasPorNumeroMergeSort() {
    List<Conta_Bancaria> contas = banco.converterParaLista();
    int n = contas.size();

    long startTime = System.currentTimeMillis(); // Captura o tempo de início

    contas = mergeSort(contas);

    long endTime = System.currentTimeMillis(); // Captura o tempo de término
    long executionTime = endTime - startTime; // Calcula o tempo de execução

    // Imprime as contas ordenadas
    System.out.println("Contas ordenadas pelo Merge Sort por número da conta:");
    for (Conta_Bancaria conta : contas) {
        System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Saldo: R$ " + conta.getSaldo());
    }

    // Imprime o tempo de execução
    System.out.println("Tempo de execução: " + executionTime + " ms");
}

private List<Conta_Bancaria> mergeSort(List<Conta_Bancaria> contas) {
    if (contas.size() <= 1) {
        return contas;
    }

    int mid = contas.size() / 2;
    List<Conta_Bancaria> left = new ArrayList<>(contas.subList(0, mid));
    List<Conta_Bancaria> right = new ArrayList<>(contas.subList(mid, contas.size()));

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left, right);
}

private List<Conta_Bancaria> merge(List<Conta_Bancaria> left, List<Conta_Bancaria> right) {
    List<Conta_Bancaria> merged = new ArrayList<>();
    int leftIndex = 0, rightIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
        int numeroContaLeft = Integer.parseInt(left.get(leftIndex).getNumero_da_conta());
        int numeroContaRight = Integer.parseInt(right.get(rightIndex).getNumero_da_conta());

        if (numeroContaLeft <= numeroContaRight) {
            merged.add(left.get(leftIndex++));
        } else {
            merged.add(right.get(rightIndex++));
        }
    }

    while (leftIndex < left.size()) {
        merged.add(left.get(leftIndex++));
    }

    while (rightIndex < right.size()) {
        merged.add(right.get(rightIndex++));
    }

    return merged;
}
private void ordenarContasPorNumeroQuickSort() {
    List<Conta_Bancaria> contas = banco.converterParaLista();
    int n = contas.size();

    long startTime = System.currentTimeMillis(); // Captura o tempo de início

    quickSort(contas, 0, n - 1);

    long endTime = System.currentTimeMillis(); // Captura o tempo de término
    long executionTime = endTime - startTime; // Calcula o tempo de execução

    // Imprime as contas ordenadas
    System.out.println("Contas ordenadas pelo Quick Sort por número da conta:");
    for (Conta_Bancaria conta : contas) {
        System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Saldo: R$ " + conta.getSaldo());
    }

    // Imprime o tempo de execução
    System.out.println("Tempo de execução: " + executionTime + " ms");
}

private void quickSort(List<Conta_Bancaria> contas, int low, int high) {
    if (low < high) {
        int pi = partition(contas, low, high);

        quickSort(contas, low, pi - 1);
        quickSort(contas, pi + 1, high);
    }
}

private int partition(List<Conta_Bancaria> contas, int low, int high) {
    int pivot = Integer.parseInt(contas.get(high).getNumero_da_conta());
    int i = (low - 1);
    for (int j = low; j < high; j++) {
        int numeroConta = Integer.parseInt(contas.get(j).getNumero_da_conta());
        if (numeroConta <= pivot) {
            i++;

            // Troca as contas
            Conta_Bancaria temp = contas.get(i);
            contas.set(i, contas.get(j));
            contas.set(j, temp);
        }
    }

    // Troca a conta do pivot
    Conta_Bancaria temp = contas.get(i + 1);
    contas.set(i + 1, contas.get(high));
    contas.set(high, temp);

    return i + 1;
}
    
}