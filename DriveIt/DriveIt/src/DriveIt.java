import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class DriveIt {
    private String nome;
    private Map<String,Veiculo> carros;
    private boolean descontoOcasiao;
    private double desconto;

    public DriveIt (){
        this.nome = "";
        this.carros = new HashMap<>();
        this.descontoOcasiao = false;
        this.desconto = 1.25;
    }

    public DriveIt(String nome, Map<String, Veiculo> carros, boolean descontoOcasiao, double desconto){
        this.nome = nome;
        this.carros = new HashMap<>();
        this.carros = carros.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(),par -> par.getValue().clone()));
        this.descontoOcasiao = descontoOcasiao;
        this.desconto = desconto;
    }

    public DriveIt (DriveIt d){
        this.nome = d.getNome();
        setCarros(d.getCarros());
        this.descontoOcasiao = d.isDescontoOcasiao();
        this.desconto = d.getDesconto();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String,Veiculo> getCarros(){
        return this.carros.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().clone()));
    }

    public void setCarros(Map<String,Veiculo> carros){
        this.carros = new HashMap<>();
        carros.entrySet().forEach(e -> this.carros.put(e.getKey(),e.getValue().clone()));
    }

    public boolean isDescontoOcasiao() {
        return descontoOcasiao;
    }

    public void setDescontoOcasiao(boolean descontoOcasiao) {
        this.descontoOcasiao = descontoOcasiao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriveIt driveIt = (DriveIt) o;
        return Objects.equals(carros, driveIt.carros) && driveIt.getNome().equals(this.nome) &&
                driveIt.isDescontoOcasiao() == this.isDescontoOcasiao() &&
                driveIt.getDesconto() == (this.getDesconto());
    }

    public String toString() {
        return "DriveIt{" +
                "nome=" + nome +
                "carros=" + carros +
                "descontoOcasiao=" + descontoOcasiao +
                "desconto=" + desconto +
                '}';
    }

    public DriveIt clone(){
        return new DriveIt(this);
    }

    public boolean existeVeiculo(String cod){
        return this.carros.containsKey(cod);
    }

    public int quantos(){
        return this.carros.keySet().size();
    }

    public int quantosMarca(String marca){
        return (int) this.carros.values().stream().filter(e -> e.getMarca().equals(marca)).count();
    }

    public Veiculo getVeiculo(String cod){
        if(existeVeiculo(cod)){
            return this.carros.get(cod).clone();
        } else {
            return null; //Depois vai ser uma Exception
        }
    }

    public void adicionaVeiculo(Veiculo v){
        this.carros.put(v.getMatricula(),v.clone());
    }

    public List<Veiculo> getVeiculos(){
        return this.carros.values().stream().collect(Collectors.toList());
    }

    public void adiciona(Set<Veiculo> v){
        v.forEach(veic -> adicionaVeiculo(veic));
    }

    public void registarAluguer(String cod, int numKms){
        this.carros.get(cod).addViagem(numKms);
    }

    public void classificarVeiculo(String cod, int classificacao){
        this.carros.get(cod).addClassificacao(classificacao);
    }

    public int custoRealKm (String cod){
        if(existeVeiculo(cod)){
            return (int) this.carros.get(cod).getPrecokm();
        } else {
            return 0;
        }
    }

    public Veiculo getCarroMaisBarato(){
        Comparator<Veiculo> cv = (v1,v2) -> v1.getPrecokm() != v2.getPrecokm() ?
                (int) (v1.custoRealKM()-v2.custoRealKM()): v1.getMatricula().compareTo(v2.getMatricula());

        return this.carros.values().stream().sorted(cv).findFirst().get().clone();
    }

    public void colocaEmpresaEmPromocao(){
        for(Veiculo v: this.carros.values()){
            if(v instanceof VeiculoOcasiao){
                ((VeiculoOcasiao) v).setDesconto(true);
            }
        }
    }

    public int quantosT(String tipo){
        int count = 0;
        if(tipo.equals("Ocasião")){
            for(Veiculo v : this.carros.values()){
                if(v instanceof VeiculoOcasiao){
                    count++;
                }
            }
        } else if (tipo.equals("Premium")) {
            for(Veiculo v : this.carros.values()){
                if(v instanceof VeiculoPremium){
                    count++;
                }
            }
        }
        return count;
    }

    /** Calcular o veículo mais utilizado, isto é, com mais kms
     * Se tiverem o mesmo numero de kms, entao deve devolver o alfabeticamente "maior".
     *
     * @return Veiculo mais utilizado do catálogo
     */

    public Veiculo getCarroMaisUsado(){
        Comparator<Veiculo> cv = (v1,v2) -> v1.getKms() != v2.getKms() ?
                (v2.getKms() - v1.getKms()): v2.getMatricula().compareTo(v1.getMatricula());

        return this.carros.values().stream().sorted(cv).findFirst().get().clone();
    }

}
