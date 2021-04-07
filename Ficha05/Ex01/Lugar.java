package Ex1;

import java.util.Objects;

public class Lugar {
    private String matricula;
    private String nome;
    private int minutos;
    private boolean permanente;

    public Lugar(){
        this.matricula = "";
        this.nome = "";
        this.minutos = 0;
        this.permanente = true;
    }

    public Lugar(String m, String n, int min, boolean p){
        this.matricula = m;
        this.nome = n;
        this.minutos = min;
        this.permanente = p;
    }

    public Lugar (Lugar l){
        this.matricula = l.getMatricula();
        this.nome = l.getNome();
        this.minutos = l.getMinutos();
        this.permanente = l.isPermanente();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public boolean isPermanente() {
        return permanente;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lugar lugar = (Lugar) o;
        return minutos == lugar.minutos && permanente == lugar.permanente && Objects.equals(matricula, lugar.matricula) && Objects.equals(nome, lugar.nome);
    }

    public String toString() {
        return "Ex1.Lugar{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", minutos=" + minutos +
                ", permanente=" + permanente +
                '}';
    }

    public Lugar clone(){
        return (new Lugar (this));
    }
}
