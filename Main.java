
import java.util.ArrayList;
import java.util.List;

// 1. Classe Comprador
class Comprador {
    private String nome;
    private String documento;

    // Construtor: define o nome e o documento ao criar um comprador.
    public Comprador(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    // Retorna o nome do comprador.
    public String getNome() {
        return nome;
    }

    // Retorna o documento do comprador.
    public String getDocumento() {
        return documento;
    }
}

// 2. Classe Mercadoria
class Mercadoria {
    private String nome;
    private double valor;
    private int estoque;

    // Construtor: define o nome, o valor e inicia o estoque em zero.
    public Mercadoria(String nome, double valor) {
        this.nome = nome == null || nome.isBlank() ? "Mercadoria sem nome" : nome;
        this.valor = valor > 0 ? valor : 0.01;
        this.estoque = 0;
    }

    // Retorna o nome da mercadoria.
    public String getNome() {
        return nome;
    }

    // Retorna o valor da mercadoria.
    public double getValor() {
        return valor;
    }

    // Atualiza o valor se ele for positivo e informa se a alteração funcionou.
    public boolean atualizarValor(double novoValor) {
        if (novoValor <= 0) {
            return false;
        }
        this.valor = novoValor;
        return true;
    }
}

// 3. Classe ItemCompra
class ItemCompra {
    private Mercadoria mercadoria;
    private int quantidade;

    // Construtor: define qual mercadoria foi comprada e sua quantidade.
    public ItemCompra(Mercadoria mercadoria, int quantidade) {
        this.mercadoria = mercadoria;
        this.quantidade = quantidade > 0 ? quantidade : 1;
    }

    // Retorna a mercadoria deste item.
    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    // Retorna a quantidade comprada.
    public int getQuantidade() {
        return quantidade;
    }

    // Multiplica o valor da mercadoria pela quantidade e retorna o subtotal.
    public double calcularSubtotal() {
        return mercadoria.getValor() * quantidade;
    }
}

// 4. Classe Compra
class Compra {
    private Comprador comprador;
    private List<ItemCompra> itens;

    // Construtor: associa um comprador à compra e cria uma lista vazia de itens.
    public Compra(Comprador comprador) {
        this.comprador = comprador;
        this.itens = new ArrayList<>();
    }

    // Retorna o comprador associado à compra.
    public Comprador getComprador() {
        return comprador;
    }

    // Adiciona uma mercadoria à compra se ela existir e a quantidade for positiva.
    public void adicionarMercadoria(Mercadoria mercadoria, int quantidade) {
        if (mercadoria != null && quantidade > 0) {
            ItemCompra item = new ItemCompra(mercadoria, quantidade);
            itens.add(item);
        }
    }

    // Retorna uma cópia da lista de itens da compra.
    public List<ItemCompra> getItens() {
        return new ArrayList<>(itens);
    }

    // Percorre os itens e soma os subtotais para calcular o valor total.
    public double calcularTotal() {
        double total = 0;
        for (ItemCompra item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    // Exibe no console o comprador, os itens, os subtotais e o total da compra.
    public void exibirResumo() {
        System.out.println("=== RESUMO DA COMPRA ===");
        System.out.println("Comprador: " + comprador.getNome());

        for (ItemCompra item : itens) {
            System.out.println(
                "- " + item.getMercadoria().getNome()
                + " | Qtd: " + item.getQuantidade()
                + " | Subtotal: R$ " + item.calcularSubtotal()
            );
        }

        System.out.println("Total Geral: R$ " + calcularTotal());
        System.out.println("====================");
    }
}

// 5. Classe principal
public class Main {
    // Ponto de entrada: o Java começa a executar o programa por este método.
    public static void main(String[] args) {
        // Cria o comprador.
        Comprador comprador = new Comprador("Nicolas", "12345");

        // Cria as mercadorias com seus respectivos valores.
        Mercadoria teclado = new Mercadoria("Teclado Mecânico", 150.00);
        Mercadoria mouse = new Mercadoria("Mouse Gamer", 80);

        // Cria uma compra associada ao comprador.
        Compra compra = new Compra(comprador);

        // Adiciona as mercadorias e suas quantidades.
        compra.adicionarMercadoria(teclado, 2);
        compra.adicionarMercadoria(mouse, 3);

        // Exibe o resumo da compra no console.
        compra.exibirResumo();
    }
}