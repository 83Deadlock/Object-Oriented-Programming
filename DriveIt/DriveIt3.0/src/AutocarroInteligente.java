import java.util.ArrayList;
import java.util.Objects;

public class AutocarroInteligente extends Veiculo implements BonificaKms{
    private double ocupacao;
    private int pontosPorKm;
    private int pontosAcumulados;

    public AutocarroInteligente(){
        super();
        this.ocupacao = 0;
        this.pontosPorKm = 0;
        this.pontosAcumulados = 0;
    }

    public AutocarroInteligente(String marca, String modelo, String matricula,
                                int ano, double velociademedia, double precokm,
                                ArrayList<Integer> classificacao,
                                int kms, int kmsUltimo, double ocupacao,
                                int pontosPorKm, int pontosAcumulados){
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
        this.ocupacao = ocupacao;
        this.pontosPorKm = pontosPorKm;
        this.pontosAcumulados = pontosAcumulados;
    }

    public AutocarroInteligente (AutocarroInteligente v){
        super(v);
        this.ocupacao = v.getOcupacao();
        this.pontosPorKm = v.getPontosPorKm();
        this.pontosAcumulados = v.getPontosAcumulados();
    }

    public double getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(double ocupacao){
        this.ocupacao = ocupacao;
    }

    public int getPontosPorKm() {
        return pontosPorKm;
    }

    public void setPontosPorKm(int pontosPorKm) {
        this.pontosPorKm = pontosPorKm;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }

    public String toString() {
        return "AutocarroInteligente{" +
                super.toString() +
                "ocupacao=" + ocupacao +
                "pontosPorKm=" + pontosPorKm +
                "pontosAcumulados=" + pontosAcumulados +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AutocarroInteligente that = (AutocarroInteligente) o;
        return Double.compare(that.ocupacao, ocupacao) == 0 &&
                that.pontosPorKm == pontosPorKm &&
                that.pontosAcumulados == pontosAcumulados;
    }

    public AutocarroInteligente clone(){
        return new AutocarroInteligente(this);
    }

    public double custoRealKM() {
        double valor = super.getPrecokm()*(2-1/Math.exp(super.getKms()));
        if(0 <= this.ocupacao && this.ocupacao <= 0.6){
            valor += 0.5;
        } else if(this.ocupacao >= 0.61){
            valor *= 0.25;
        }
        return valor;
    }

    public int calculaPontosAAtribuir(int km) {
        return this.getPontosPorKm() * km;
    }

    public int pontosPorKm() {
        return this.getPontosPorKm();
    }

    public int pontosAcumuladosPorVeiculo() {
        return super.getKms() * pontosPorKm;
    }

    public void addViagem(int nkms){
        super.addViagem(nkms);
        this.pontosAcumulados += nkms*this.pontosPorKm;
    }
}
