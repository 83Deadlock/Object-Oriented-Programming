import java.util.ArrayList;
import java.util.Objects;

public class VeiculoOcasiao extends Veiculo{
    private boolean desconto;

    public VeiculoOcasiao(){
        super();
        this.desconto = false;
    }

    public VeiculoOcasiao(String marca, String modelo, String matricula,
                          int ano, double velociademedia, double precokm,
                          ArrayList<Integer> classificacao,
                          int kms, int kmsUltimo, boolean desconto){
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
        this.desconto = desconto;
    }

    public VeiculoOcasiao(VeiculoOcasiao v){
        super(v);
        this.desconto = v.isDesconto();
    }

    public boolean isDesconto() {
        return desconto;
    }

    public void setDesconto(boolean desconto) {
        this.desconto = desconto;
    }

    public double getPrecokm() {
        double d = 0.25;
        if(!this.isDesconto()) d = 0.00;
        return (super.getPrecokm() - super.getPrecokm()*d);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VeiculoOcasiao that = (VeiculoOcasiao) o;
        return desconto == that.desconto;
    }

    public String toString() {
        return "VeiculoOcasiao{" +
                super.toString() +
                "desconto=" + desconto +
                '}';
    }

    public VeiculoOcasiao clone(){
        return new VeiculoOcasiao(this);
    }
}
