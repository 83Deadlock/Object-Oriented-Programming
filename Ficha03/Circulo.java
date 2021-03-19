import java.util.Objects;

public class Circulo {
    private Ponto p;
    private double raio;

    public Circulo(){
        this.p = new Ponto();
        this.raio = 0;
    }

    public Circulo(Ponto p, double ra){
        this.p = p;
        this.raio = ra;
    }

    public Circulo (Circulo c){
        this.p = c.getP();
        this.raio = c.getRaio();
    }

    public Ponto getP() {
        return p;
    }

    public double getRaio(){
        return this.raio;
    }

    public void setP(Ponto p) {
        this.p = p;
    }

    public void setRaio(double rn){
        this.raio = rn;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circulo circulo = (Circulo) o;
        return p.equals(circulo.p) && Double.compare(circulo.raio, raio) == 0;
    }

    public String toString() {
        return "Circulo{" +
                "p = " + p.toString() +
                ", raio=" + raio +
                '}';
    }

    public Circulo clone(){
        return new Circulo(this);
    }

    public void alteraCentro(int xn, int yn){
        this.p = new Ponto(xn,yn);
    }

    public double calculaArea(){
        return (Math.PI * Math.pow(this.raio,2));
    }

    public double perimetro(){
        return (Math.PI * 2 * (this.raio));
    }
}
