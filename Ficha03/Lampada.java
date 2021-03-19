import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Lampada {
    private int estado; //-1 eco, 0 off, 1 ON
    private double consumoPorMS;
    private double consumoAtual;
    private double consumoTotal;
    private LocalDateTime reset;

    public Lampada(){
        this.estado = 0;
        this.consumoPorMS = 0;
        this.consumoAtual = 0;
        this.consumoTotal = 0;
        this.reset = LocalDateTime.now();
    }

    public Lampada(int e, double c, double a, double t, LocalDateTime date){
        this.estado = e;
        this.consumoPorMS = c;
        this.consumoAtual = a;
        this.consumoTotal = t;
        this.reset = date;
    }

    public Lampada(Lampada l){
        this.estado = l.getEstado();
        this.consumoPorMS = l.getConsumoPorMS();
        this.consumoAtual = l.getConsumoAtual();
        this.consumoTotal = l.getConsumoTotal();
        this.reset = l.getreset();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getConsumoPorMS() {
        return consumoPorMS;
    }

    public void setConsumoPorMS(double consumoPorMS) {
        this.consumoPorMS = consumoPorMS;
    }

    public double getConsumoAtual() {
        return consumoAtual;
    }

    public void setConsumoAtual(double consumoAtual) {
        this.consumoAtual = consumoAtual;
    }

    public double getConsumoTotal() {
        return consumoTotal;
    }

    public void setConsumoTotal(double consumoTotal) {
        this.consumoTotal = consumoTotal;
    }

    public LocalDateTime getreset() {
        return reset;
    }

    public void setreset(LocalDateTime reset) {
        this.reset = reset;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lampada lampada = (Lampada) o;
        return estado == lampada.estado && Double.compare(lampada.consumoPorMS, consumoPorMS) == 0 && Double.compare(lampada.consumoAtual, consumoAtual) == 0 && Double.compare(lampada.consumoTotal, consumoTotal) == 0 && Objects.equals(reset, lampada.reset);
    }

    public String toString() {
        return "Lampada{" +
                "estado=" + estado +
                ", consumoPorMS=" + consumoPorMS +
                ", consumoAtual=" + consumoAtual +
                ", consumoTotal=" + consumoTotal +
                ", reset=" + reset +
                '}';
    }

    public Lampada clone(){
        return new Lampada(this);
    }

    public void lampON(){
        setEstado(1);
        setConsumoPorMS(100);
        setreset(LocalDateTime.now());
    }

    public void lampOFF(){
        setEstado(0);
        setConsumoPorMS(0);
        setConsumoTotal(getConsumoAtual() + getConsumoTotal());
        setConsumoAtual(0);
        setreset(LocalDateTime.now());
    }

    public void lampECO(){
        setEstado(1);
        setConsumoPorMS(25);
        setConsumoTotal(getConsumoAtual() + getConsumoTotal());
        setreset(LocalDateTime.now());
    }

    public double totalConsumo(){
        return this.consumoTotal;
    }

    public double periodoConsumo(){
        LocalDateTime agora = LocalDateTime.now();
        long ms = Duration.between(agora,this.reset).toMillis();
        return (consumoAtual * ms);
    }
}
