    class No_arvore_cliente {
        private Cliente cliente;       // Cliente armazenado no nó
        private No_arvore_cliente esquerdo;    // Referência para o nó esquerdo
        private No_arvore_cliente direito;     // Referência para o nó direito
    
        // Construtor
        public No_arvore_cliente(Cliente cliente) {
            this.cliente = cliente;
            this.esquerdo = null;
            this.direito = null;
        }
    
        public Cliente getCliente() {
            return cliente;
        }
    
        public No_arvore_cliente getEsquerdo() {
            return esquerdo;
        }
    
        public void setEsquerdo(No_arvore_cliente esquerdo) {
            this.esquerdo = esquerdo;
        }
    
        public No_arvore_cliente getDireito() {
            return direito;
        }
    
        public void setDireito(No_arvore_cliente direito) {
            this.direito = direito;
        }
    }

