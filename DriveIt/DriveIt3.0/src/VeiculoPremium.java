import java.util.ArrayList;
import java.util.Objects;

public class VeiculoPremium extends Veiculo implements BonificaKms{
    private double taxa;
    private int pontosPorKm;
    private int pontosAcumulados;

    public VeiculoPremium(){
        super();
        this.taxa = 0.10;
        this.pontosPorKm = 0;
        this.pontosAcumulados = 0;
    }

    public VeiculoPremium(String marca, String modelo, String matricula,
                          int ano, double velociademedia, double precokm,
                          ArrayList<Integer> classificacao,
                          int kms, int kmsUltimo, double taxa,
                          int pontosPorKm, int pontosAcumulados){
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
        this.taxa = taxa;
        this.pontosPorKm = pontosPorKm;
        this.pontosAcumulados = pontosAcumulados;
    }

    public VeiculoPremium(VeiculoPremium v){
        super(v);
        this.taxa = v.getTaxa();
        this.pontosPorKm = v.getPontosPorKm();
        this.pontosAcumulados = v.getPontosAcumulados();
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
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

    public double getPrecokm() {
        return (super.getPrecokm() + super.getPrecokm()*this.taxa);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VeiculoPremium that = (VeiculoPremium) o;
        return Double.compare(that.taxa, taxa) == 0 &&
                pontosPorKm == that.pontosPorKm &&
                pontosAcumulados == that.pontosAcumulados;
    }


    public String toString() {
        return "VeiculoPremium{" +
                super.toString() +
                "taxa=" + taxa +
                "pontosPorKm=" + pontosPorKm +
                "pontosAcumulados=" + pontosAcumulados +
                '}';
    }

    public VeiculoPremium clone(){
        return new VeiculoPremium(this);
    }

    public double custoRealKM(){
        double valor = super.getPrecokm()*(2-1/Math.exp(super.getKms()));
        return valor * (1-this.taxa);
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
