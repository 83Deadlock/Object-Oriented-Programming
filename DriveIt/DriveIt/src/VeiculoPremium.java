import java.util.ArrayList;
import java.util.Objects;

public class VeiculoPremium extends Veiculo{
    private double taxa;

    public VeiculoPremium(){
        super();
        this.taxa = 0.10;
    }

    public VeiculoPremium(String marca, String modelo, String matricula,
                          int ano, double velociademedia, double precokm,
                          ArrayList<Integer> classificacao,
                          int kms, int kmsUltimo, double taxa){
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
        this.taxa = taxa;
    }

    public VeiculoPremium(VeiculoPremium v){
        super(v);
        this.taxa = v.getTaxa();
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getPrecokm() {
        return (super.getPrecokm() + super.getPrecokm()*this.taxa);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VeiculoPremium that = (VeiculoPremium) o;
        return Double.compare(that.taxa, taxa) == 0;
    }


    public String toString() {
        return "VeiculoPremium{" +
                super.toString() +
                "taxa=" + taxa +
                '}';
    }
}
