package Ex1;

import java.util.*;
import java.util.stream.Collectors;

public class Parque {
    private String nome;
    private Map<String,Lugar> lugares;

    public Parque (){
        this.nome = "";
        this.lugares = new HashMap<>();
    }

    public Parque(String n, Map<String,Lugar> v){
        this.nome = n;
        setLugares(v);
    }

    public Parque(Parque p){
        this.nome = p.getNome();
        setLugares(p.getLugares());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Lugar> getLugares() {
        return this.lugares.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public void setLugares(Map<String, Lugar> lugares) {
        this.lugares = new HashMap<>();
        lugares.entrySet().forEach(e -> this.lugares.put(e.getKey(),e.getValue().clone()));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parque parque = (Parque) o;
        return Objects.equals(nome, parque.nome) && Objects.equals(lugares, parque.lugares);
    }

    public String toString() {
        return "Parque{" +
                "nome='" + nome + '\'' +
                ", lugares=" + lugares +
                '}';
    }

    public Parque clone(){
        return (new Parque(this));
    }

    /** Método que devolve todas as matriculas dos lugares ocupados
     *
     */
    public Set<String> matriculasLugaresOcupados(){
        return this.lugares.keySet();
    }

    /** Método que regista uma nova ocupação de lugar
     *
     */
    public void registaOcupacaoLugar(Lugar l){
        this.lugares.put(l.getMatricula(),l);
    }

    /** Método que remove o ludar de dada matrícula
     *
     */
    public void removeLugarMatricula(String matricula){
        this.lugares.remove(matricula);
    }

    /** Método que altera o tempo disponível de um lugar, para uma dada matricula
     *
     */
    public void alteraTempoDisponivel(String matricula, int tempo){
        Lugar temp = this.lugares.get(matricula);
        temp.setMinutos(tempo);
        this.lugares.put(matricula,temp);
    }

    /** Método que devolve a quantidade total de minutos atribuídos
     *  ITERADOR INTERNO
     */
    public int tempoTotalInt(){
        return this.lugares.values().stream().mapToInt(Lugar::getMinutos).sum();
    }

    /** Método que devolve a quantidade total de minutos atribuídos
     *  ITERADOR EXTERNO
     */
    public int tempoTotalExt(){
        int total = 0;
        Collection<Lugar> c = this.lugares.values();
        for(Lugar l : c){
            total += l.getMinutos();
        }
        return total;
    }
}
