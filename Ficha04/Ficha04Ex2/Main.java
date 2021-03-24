import java.util.ArrayList;
import java.time.LocalDate;

public class Main {
    public static void main (String[] args) {

        LinhaEncomenda l1 = new LinhaEncomenda("Abcde", "Carro", 30000, 1, 1.23, 0.10);
        LinhaEncomenda l2 = new LinhaEncomenda("Fghij", "Oleo", 4.99, 10, 1.23, 0.05);
        LinhaEncomenda l3 = new LinhaEncomenda("Klmno", "Gasoleo", 1.158, 30, 1.23, 0.15);

        ArrayList<LinhaEncomenda> enc = new ArrayList<>();
        enc.add(l1);
        enc.add(l2);

        LocalDate hoje = LocalDate.now();

        Encomenda encTeste = new Encomenda("Jose", 123456789, "Braga", 453, hoje, enc);

        System.out.println("O valor total da encomenda: " + encTeste.calculaValorTotal() + "\n" +
                "O valor total dos descontos: " + encTeste.calculaValorDesconto() + "\n" +
                "O produto Abcde vai ser encomendado: " + encTeste.existeProdutoEncomenda("Abcde") + "\n" +
                "Vão ser encomendados " + encTeste.numeroTotalProdutos() + "\n");

        encTeste.removeProduto("Abcde");
        System.out.println("O produto Abcde vais er encomendado: "  + encTeste.existeProdutoEncomenda("Abcde"));
        encTeste.adicionaLinha(l3);
        System.out.println("O produto Klmno vais er encomendado: "  + encTeste.existeProdutoEncomenda("Klmno"));

        System.out.println(encTeste.toString() + "\n");
    }
}