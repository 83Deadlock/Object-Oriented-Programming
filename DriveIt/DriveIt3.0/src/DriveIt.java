import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class DriveIt implements Serializable{

    /**
     * Variáveis de classe
     */

    private static Map<String, Comparator<Veiculo>> comps = new HashMap<>();

    public static void addComparator(String nome, Comparator<Veiculo> cv){
        comps.put(nome,cv);
    }

    public static Comparator<Veiculo> getComparador(String nome){
        return comps.get(nome);
    }

    /**
     * Variáveis de instância
     */

    private String nome;
    private Map<String,Veiculo> carros;

    public DriveIt (){
        this.nome = "";
        this.carros = new HashMap<>();
    }

    public DriveIt(String nome, Map<String, Veiculo> carros){
        this.nome = nome;
        this.carros = new HashMap<>();
        this.carros = carros.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(),par -> par.getValue().clone()));
    }

    public DriveIt (DriveIt d){
        this.nome = d.getNome();
        setCarros(d.getCarros());
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriveIt driveIt = (DriveIt) o;
        return Objects.equals(carros, driveIt.carros) && driveIt.getNome().equals(this.nome);
    }

    public String toString() {
        return "DriveIt{" +
                "nome=" + nome +
                " carros=" + carros +
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

    /** Se cod não existir "devolve" uma excepção
     *
     * @param cod
     * @return
     */

    public Veiculo getVeiculo(String cod) throws VeiculoNaoExistenteException{

        if(existeVeiculo(cod)){
            return this.carros.get(cod).clone();
        } else {
            throw new VeiculoNaoExistenteException(cod);
        }
    }

    public void adicionaVeiculo(Veiculo v) throws VeiculoExistenteException{
        if(!this.carros.containsKey(v.getMatricula())){
            this.carros.put(v.getMatricula(),v.clone());
        } else {
            throw new VeiculoExistenteException(v.getMatricula());
        }

    }

    public List<Veiculo> getVeiculos(){
        return this.carros.values().stream().collect(Collectors.toList());
    }

    public void adiciona(Set<Veiculo> vs) throws VeiculoExistenteException{
        for(Veiculo v: vs)
            adicionaVeiculo(v);
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

    public Set<Veiculo> ordenarVeiculos(){
        Set<Veiculo> res = new TreeSet<>();
        for(Veiculo v: this.carros.values()){
            res.add(v.clone());
        }

        return res;
    }

    public List<Veiculo> ordenarVeiculosL(){
        return this.carros.values().stream().map(Veiculo::clone).sorted().collect(Collectors.toList());
    }

    public Set<Veiculo> ordenarVeiculosS(){
        Set<Veiculo> res = new TreeSet<>(Veiculo::compareTo);

        for(Veiculo v: this.carros.values()){
            res.add(v.clone());
        }

        return res;
    }

    /**
     *  Devolver um Map que associa a cada marca de Veiculo a lista dos veiculos dessa marca,
     *  ordenados por ordem alfabetica de modelo.
     */

    public Map<String,List<Veiculo>> porMarca_Modelo(){
        Comparator <Veiculo> cv = (v1,v2) -> (v1.getModelo().compareTo(v2.getModelo()));

        //Esta estratégia passa por ordenar os carros por modelo na stream, ao adicionar não há necessidade de re-ordenar

        return this.carros.values().stream()
                                   .map(Veiculo::clone)
                                   .sorted(cv)
                                   .collect(Collectors.groupingBy(Veiculo::getMarca));
    }

    public Map<String,List<Veiculo>> porMarca_Modelov2(){
        Comparator <Veiculo> cv = (v1,v2) -> (v1.getModelo().compareTo(v2.getModelo()));

        Map<String,List<Veiculo>> res = new HashMap<>();

        for(Veiculo v: this.carros.values()){
            String marca = v.getMarca();

            res.putIfAbsent(marca, new ArrayList<>());
            res.get(marca).add(v.clone());
        }

        for(List<Veiculo> l: res.values()){
            //l.sort(cv); é igual
            Collections.sort(l,cv);
        }

        return res;
    }

    /**
     *
     * @param nome - Identificador do comparador por parametro
     * @return
     */
    public Map<String,List<Veiculo>> porMarca_Modelov3(String nome){
        Map<String,List<Veiculo>> res = new HashMap<>();

        for(Veiculo v: this.carros.values()){
            String marca = v.getMarca();

            res.putIfAbsent(marca, new ArrayList<>());
            res.get(marca).add(v.clone());
        }

        for(List<Veiculo> l: res.values()){
            //l.sort(cv); é igual
            Collections.sort(l,getComparador(nome));
        }

        return res;
    }

    public Iterator<Veiculo> ordenarVeiculo(String crit){
        List<Veiculo> res = new ArrayList<>();

        res = this.carros.values().stream()
                                  .map(Veiculo::clone)
                                  .sorted(getComparador(crit))
                                  .collect(Collectors.toList());

        return res.iterator();
    }

    public List<BonificaKms> daoPontos(){
        return this.carros.values().stream().
                filter(v -> v instanceof BonificaKms).
                map(v -> (BonificaKms) v).
                collect(Collectors.toList());
    }

    /*
    Fase 4, gravar em ficheiro de texto
     */

    public void escreveFicheiro(String nomeFicheiro) throws FileNotFoundException {
        PrintWriter fich = new PrintWriter(nomeFicheiro);
        fich.print(this.toString());

        fich.flush();
        fich.close();
    }

    /*
    Gravar um ficheiro de objetos
     */
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);
        oos.flush();
        oos.close();
    }


}

