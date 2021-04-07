package Ex2;

import java.util.*;
import java.util.stream.Collectors;

public class Turma {
    private String nome;
    private String codigoUC;
    private Map<String,Aluno> alunos;

    public Turma(){
        this.nome = "";
        this.codigoUC = "";
        this.alunos = new HashMap<>();
    }

    public Turma(String n, String c, Map<String,Aluno> alunos){
        this.nome = n;
        this.codigoUC = c;
        setAlunos(alunos);
    }

    public Turma(Turma t){
        setNome(t.getNome());
        setCodigoUC(t.getCodigoUC());
        setAlunos(t.getAlunos());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoUC() {
        return codigoUC;
    }

    public void setCodigoUC(String codigoUC) {
        this.codigoUC = codigoUC;
    }

    public Map<String, Aluno> getAlunos() {
        return this.alunos.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().clone()));
    }

    public void setAlunos(Map<String, Aluno> alunos) {
        this.alunos = new HashMap<>();
        alunos.entrySet().forEach(e -> this.alunos.put(e.getKey(),e.getValue().clone()));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(nome, turma.nome) && Objects.equals(codigoUC, turma.codigoUC) && Objects.equals(alunos, turma.alunos);
    }

    public String toString() {
        return "Turma{" +
                "nome='" + nome + '\'' +
                ", codigoUC='" + codigoUC + '\'' +
                ", alunos=" + alunos +
                '}';
    }

    public Turma clone(){
        return new Turma(this);
    }

    public void insereAluno(Aluno a){
        this.alunos.put(a.getNumero(),a.clone());
    }

    public Aluno getAluno(String codAluno){
        return this.alunos.get(codAluno).clone();
    }

    public void removeAluno(String codAluno){
        if(this.alunos.containsKey(codAluno))
            this.alunos.remove(codAluno);
    }

    public Set<String> todosOsCodigos(){
        return new HashSet<>(this.alunos.keySet());
    }

    public int qtsAlunos(){
        return this.alunos.keySet().size();
    }

    public Collection<Aluno> alunosOrdemAlfabetica(){
        Comparator<Aluno> comp1 = (a1,a2) -> a1.getNome().compareTo(a2.getNome());

        return this.alunos.values().stream().map(Aluno::clone).sorted(comp1).collect(Collectors.toList());
    }

    public Set<Aluno> alunosOrdemDescrescenteNumero(){
        Comparator<Aluno> comp2 = (a1,a2) -> -(a1.getNumero().compareTo(a2.getNumero()));
        Set<Aluno> ret = new TreeSet<>(comp2);
        for(Aluno a: this.alunos.values()){
            ret.add(a.clone());
        }
        return ret;
    }
}
