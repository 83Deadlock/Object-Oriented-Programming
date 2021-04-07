import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encomenda composta por nome, NIF e morada do cliente, número, data e linhas da encomenda.
 *
 * @author Jose Pedro Ferreira a89572
 * @date 24/3/2021
 */
public class Encomenda {
    private String nome;
    private int nif;
    private String morada;
    private int id;
    private LocalDate data;
    private List<LinhaEncomenda> encomendas;


    /**
     * Construtores da classe Encomenda.
     * Declaração dos construtores por omissão (vazio),
     * parametrizado e de cópia.
     */

    /**
     * Construtor por omissão
     */
    public Encomenda() {
        this.nome = "";
        this.nif = 0;
        this.morada = "";
        this.id = 0;
        this.data = LocalDate.now();
        this.encomendas = new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     */
    public Encomenda (String nome, int nif, String morada, int id, LocalDate date, List<LinhaEncomenda> le){
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.id = id;
        this.data = date;
        this.encomendas = new ArrayList<>(le);
    }

    /**
     * Construtor de cópia
     */
    public Encomenda (Encomenda e) {
        this.nome = e.getNome();
        this.nif = e.getNIF();
        this.morada = e.getMorada();
        this.id = e.getID();
        this.data = e.getData();
        this.encomendas = e.getEncomendas();
    }


    /**
     * Métodos de instância
     */
    public String getNome(){
        return this.nome;
    }

    public int getNIF(){
        return this.nif;
    }

    public String getMorada(){
        return this.morada;
    }

    public int getID(){
        return this.id;
    }

    public LocalDate getData(){
        return this.data;
    }

    public List<LinhaEncomenda> getEncomendas(){
        //return new ArrayList<>(this.encomendas); //Seria correto se a estratégia fosse de agregação. Isto partilha os conteúdos

        return this.encomendas.stream().map(LinhaEncomenda::clone).collect(Collectors.toList());
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNIF(int nif){
        this.nif = nif;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public void setEncomendas(List<LinhaEncomenda> le){
        this.encomendas = new ArrayList<>();
        for(LinhaEncomenda l: le){
            this.encomendas.add(l.clone());
        }
    }


    public Encomenda clone(){
        return (new Encomenda(this));
    }

    public String toString() {
        return "Encomenda{" +
                "nome='" + nome + '\'' +
                ", nif=" + nif +
                ", morada='" + morada + '\'' +
                ", id=" + id +
                ", data=" + data +
                ", encomendas=" + encomendas.toString() +
                '}';
    }

    /** Calcula o valor total da Encomenda, calculando o calor de cada Linha que a compõe
     *
     * @return Valor Total da Encomenda
     */
    public double calculaValorTotal() {
        double total = 0;

        for(LinhaEncomenda le: this.encomendas){
            total += le.calculaValorLinhaEnc();
        }

        return total;
    }

    /** Calcula o valor total de descontos obtidos linha a linha
     *
     * @return Soma dos descontos obtidos
     */
    public double calculaValorDesconto() {
        double total = 0;

        for(LinhaEncomenda le: this.encomendas){
            total += le.calculaValorDesconto();
        }

        return total;
    }

    /** Calcula o numero total de produtos (quantidade total) encomendada
     *
     * @return Quantidade total de produtos a ser encomendados
     */
    public int numeroTotalProdutos(){
        int total = 0;

        for(LinhaEncomenda le: this.encomendas){
            total += le.getQuantidade();
        }

        return total;
    }

    public boolean existeProdutoEncomenda(String refProduto){

        return this.encomendas.stream().anyMatch(le -> le.getReferencia().equals(refProduto));
    }

    public void adicionaLinha (LinhaEncomenda linha){
        this.encomendas.add(linha.clone());
    }

    public void removeProduto(String codProd){
        LinhaEncomenda le;
        Iterator<LinhaEncomenda> it = this.encomendas.iterator();
        boolean flag = false;
        while(it.hasNext() && !flag){
            le = it.next();
            if(le.getReferencia().equals(codProd)){
                it.remove();
                flag = true;
            }
        }
    }
}
