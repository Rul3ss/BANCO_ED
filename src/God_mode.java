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
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                ordenarContasPorNumero();;                    
                    break;
                case 2:
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
        System.out.println("Contas ordenadas por número da conta:");
        for (Conta_Bancaria conta : contas) {
            System.out.println("- Conta: " + conta.getNumero_da_conta() + ", Saldo: R$ " + conta.getSaldo());
        }

        // Imprime o tempo de execução
        System.out.println("Tempo de execução: " + executionTime + " ms");
    }
}