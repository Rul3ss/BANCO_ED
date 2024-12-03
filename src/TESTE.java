public class TESTE {
        public static void main(String[] args) {
            // Criar banco
            Banco banco = new Banco("Banco Exemplo");
    
            // Criar menu
            Menu_banco menu = new Menu_banco(banco);
    
            // Exibir menu
            menu.exibirMenu();
        }
    }
