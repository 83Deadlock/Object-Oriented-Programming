import java.util.Objects;

public class Jogo {
    private int estado; //0 por começar, 1 decorrer, 2 terminado
    private String visitado;
    private int golosVisitado;
    private String visitante;
    private int golosVisitante;

    public Jogo(){
        this.estado = 0;
        this.visitado = "";
        this.golosVisitado = 0;
        this.visitante = "";
        this.golosVisitante = 0;
    }

    public Jogo(int estado, String visitado, int golosVisitado, String visitante, int golosVisitante) {
        this.estado = estado;
        this.visitado = visitado;
        this.golosVisitado = golosVisitado;
        this.visitante = visitante;
        this.golosVisitante = golosVisitante;
    }

    public Jogo(Jogo j) {
        this.estado = j.getEstado();
        this.visitado = j.getVisitado();
        this.golosVisitado = j.getGolosVisitado();
        this.visitante = j.getVisitante();
        this.golosVisitante = j.getGolosVisitante();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getVisitado() {
        return visitado;
    }

    public void setVisitado(String visitado) {
        this.visitado = visitado;
    }

    public int getGolosVisitado() {
        return golosVisitado;
    }

    public void setGolosVisitado(int golosVisitado) {
        this.golosVisitado = golosVisitado;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getGolosVisitante() {
        return golosVisitante;
    }

    public void setGolosVisitante(int golosVisitante) {
        this.golosVisitante = golosVisitante;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return estado == jogo.estado && golosVisitado == jogo.golosVisitado && golosVisitante == jogo.golosVisitante && Objects.equals(visitado, jogo.visitado) && Objects.equals(visitante, jogo.visitante);
    }

    public String toString() {
        return "Jogo{" +
                "estado=" + estado +
                ", visitado='" + visitado + '\'' +
                ", golosVisitado=" + golosVisitado +
                ", visitante='" + visitante + '\'' +
                ", golosVisitante=" + golosVisitante +
                '}';
    }

    public Jogo clone(){
        return new Jogo(this);
    }

    public void startGame(){
        if(this.estado == 0){
            setEstado(1);
        }
    }

    public void endGame(){
        if(this.estado == 1){
            setEstado(2);
        }
    }

    public void goloVisitado() {
        if (this.estado == 1) {
            setGolosVisitado(this.golosVisitado + 1);
        }
    }

    public void goloVisitante(){
        if(this.estado == 1){
            setGolosVisitante(this.golosVisitante + 1);
        }
    }

    public String resultadoAtual(){
        String res;
        if(this.estado == 1 || this.estado == 2){
            res = this.visitado + " " + this.golosVisitado + ":" + this.golosVisitante + " " + this.visitante;
        } else {
            res = "O jogo ainda não começou. Resultado disponível assim que iniciar.";
        }
        return res;
    }
}
