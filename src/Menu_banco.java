import java.util.Scanner;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;

public class Menu_banco implements Serializable {
    private static final long serialVersionUID = 1L;
    private Banco banco;
    String caminhoArquivo = "banco_dados.ser";
    // Construtor
    public Menu_banco(Banco banco) {
        this.banco = banco;
    }
        public static void apagarBancoDeDados(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Banco de dados apagado com sucesso.");
            } else {
                System.out.println("Erro ao apagar o banco de dados.");
            }
        } else {
            System.out.println("Banco de dados não encontrado.");
        }
    }

    // Exibir menu principal
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        try {
            banco = Banco.carregarDados(caminhoArquivo);
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum dado encontrado, criando novo banco.");
            banco = new Banco("Banco Exemplo");
        }

        while (executando) {
            System.out.println("=================================");
            System.out.println("|         Menu Banco            |");
            System.out.println("=================================");
            System.out.println("| 1. Menu Administrativo        |");
            System.out.println("| 2. Menu do Cliente            |");
            System.out.println("| 3. Sair                       |");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");
            


            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                System.out.println("======================================");
                    new MenuAdministrativo(banco).exibirMenuAdministrativo(scanner);
                    break;
                case 2:
                System.out.println("======================================");
                    new MenuCliente(banco).exibirMenuCliente(scanner);
                    break;
                case 3:
                //Salvar dados ao sair
                    try {
                        banco.salvarDados(caminhoArquivo);
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                    System.out.println("Saindo do sistema...");

                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
