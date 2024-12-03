public class TESTE {
   public static void main(String[] args) {
    Conta_Bancaria conta1 = new Conta_Bancaria("12345", "João Silva", 1000.0, "Corrente");
    Conta_Bancaria conta2 = new Conta_Bancaria("67890", "Maria Oliveira", 500.0, "Poupança");

        // Operações
        conta1.transferir(300.0, conta2);

    // Exibir históricos
    System.out.println("\nHistórico da conta 1:");
    conta1.exibirHistoricoTransacoes();

    System.out.println("\nHistórico da conta 2:");
    conta2.exibirHistoricoTransacoes();


        
   }
    
    }
    
