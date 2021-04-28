import java.util.ArrayList;
import java.util.Objects;

public class AutocarroInteligente extends Veiculo{
    private double ocupacao;

    public AutocarroInteligente(){
        super();
        this.ocupacao = 0;
    }

    public AutocarroInteligente(String marca, String modelo, String matricula,
                                int ano, double velociademedia, double precokm,
                                ArrayList<Integer> classificacao,
                                int kms, int kmsUltimo, double ocupacao){
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
        this.ocupacao = ocupacao;
    }

    public AutocarroInteligente (AutocarroInteligente v){
        super(v);
    }

    public double getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(double ocupacao){
        this.ocupacao = ocupacao;
    }

    public String toString() {
        return "AutocarroInteligente{" +
                super.toString() +
                "ocupacao=" + ocupacao +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AutocarroInteligente that = (AutocarroInteligente) o;
        return Double.compare(that.ocupacao, ocupacao) == 0;
    }

    public AutocarroInteligente clone(){
        return new AutocarroInteligente(this);
    }

    @Override
    public double custoRealKM() {
        double valor = super.getPrecokm()*(2-1/Math.exp(super.getKms()));
        if(0 <= this.ocupacao && this.ocupacao <= 0.6){
            valor += 0.5;
        } else if(this.ocupacao >= 0.61){
            valor *= 0.25;
        }
        return valor;
    }
}
