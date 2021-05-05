/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/

import java.util.ArrayList;

/**
 * Classe Veículo, para utilização na Ficha 6.
 *
 * @author MaterialPOO
 * @version 20210420
 */

public class VeiculoNormal extends Veiculo{

    /**
     * Construtores
     */


    public VeiculoNormal() {
        super();
    }

    public VeiculoNormal(String marca, String modelo, String matricula,
                   int ano, double velociademedia, double precokm,
                   ArrayList<Integer> classificacao,
                   int kms, int kmsUltimo) {
        super(marca,modelo,matricula,ano,velociademedia,precokm,classificacao,kms,kmsUltimo);
    }



    public VeiculoNormal(VeiculoNormal v){
        super(v);
    }

    /**
     * Esta implementação poderá ser alterada.
     * Neste momento considera-se que o custo é 10% acima do custo
     * teórico. Poderia ser também função dos kms realizados.
     */

    public double custoRealKM(){
        double valor = super.getPrecokm()*(2-1/Math.exp(super.getKms()));
        return valor;
    }


    public int classificacao(){
        return (int) super.getClassificacao().stream().mapToInt(k->k.intValue()).average().getAsDouble();
    }

    public void addClassificacao(int v){
        super.getClassificacao().add(v);
    }

    public VeiculoNormal clone(){
        return new VeiculoNormal(this);
    }



    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || ! o.getClass().equals(this.getClass())) return false;
        VeiculoNormal v = (VeiculoNormal) o;
        return  super.getMarca().equals(v.getMarca()) &&
                super.getModelo().equals(v.getModelo())&&
                super.getMatricula().equals(v.getMatricula()) &&
                super.getAno() == v.getAno() &&
                super.getVelociademedia() == v.getVelociademedia() &&
                super.getPrecokm() == v.getPrecokm() &&
                super.getClassificacao().equals(v.getClassificacao()) &&
                super.getKms() == v.getKms() &&
                super.getKmsUltimo() == v.getKmsUltimo() ;
    }

    /**
     * Implementação da ordem natural dos veículos
     */
    public int compareTo(Veiculo v) {
        if(super.getMarca().compareTo(v.getMarca()) == 0)
            return (super.getModelo().compareTo(v.getModelo()));
        else
            return (super.getMarca().compareTo(v.getMarca()));
    }





}