import java.io.IOException;
// import java.io.File;

public class TESTE {
    public static void main(String[] args) {
        Banco banco = null;

        String caminhoArquivo = "banco_dados.ser";

        // Apagar o banco de dados atual
        // apagarBancoDeDados(caminhoArquivo);
        
        // Tentar carregar dados do arquivo
        try {
            banco = Banco.carregarDados(caminhoArquivo);
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum dado encontrado, criando novo banco.");
            banco = new Banco("Banco Exemplo");
        }          
               
        //Salvar dados ao sair
        try {
            banco.salvarDados(caminhoArquivo);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }


         // Criar menu administrativo
         Menu_banco menu = new Menu_banco(banco);
    
         // Exibir menu
         menu.exibirMenu();
 
    }
    // public static void apagarBancoDeDados(String caminhoArquivo) {
    //     File arquivo = new File(caminhoArquivo);
    //     if (arquivo.exists()) {
    //         if (arquivo.delete()) {
    //             System.out.println("Banco de dados apagado com sucesso.");
    //         } else {
    //             System.out.println("Erro ao apagar o banco de dados.");
    //         }
    //     } else {
    //         System.out.println("Banco de dados não encontrado.");
    //     }
    // }
}