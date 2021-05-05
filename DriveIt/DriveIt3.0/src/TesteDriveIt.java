import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TesteDriveIt {
    public static final int N = 5;

    public static void main(String[] args){
        DriveIt d = new DriveIt();
        d.setNome("Deadlock");
        List<Integer> classif = new ArrayList<>();

        Comparator<Veiculo> cv = (v1,v2) -> (v1.getModelo().compareTo(v2.getModelo()));
        DriveIt.addComparator("ordemCrescenteModelo",cv);

        Comparator<Veiculo> cv1 = (v1,v2) -> v1.getKms() != v2.getKms() ?
                (v2.getKms() - v1.getKms()): v2.getMatricula().compareTo(v1.getMatricula());
        DriveIt.addComparator("ordemDecrescenteKilometrosMatricula",cv1);

        try{
            Veiculo v = d.getVeiculo("matricula");
        } catch (VeiculoNaoExistenteException vnee){
            System.out.println("A matricula \""+ vnee.getMessage()+"\" não existe");
        }

        try{
            d.adicionaVeiculo(new VeiculoNormal("Hyundai","Accent","29-31-TI",2004,120,2.5,new ArrayList<>(),70000,300));
            d.adicionaVeiculo(new AutocarroInteligente("Mercebes","Bus","04-BI-20",1998,45,1.2,new ArrayList<>(),430256,150,0.3,0,0));
            d.adicionaVeiculo(new VeiculoPremium("Toyota","Auris","76-GQ-48",2008,85,3.8,new ArrayList<>(),65000,10,0.50,0,0));
            d.adicionaVeiculo(new VeiculoOcasiao("Renault","Captur","39-SX-17",2017,90,4.6,new ArrayList<>(), 30000, 679,true));
            d.adicionaVeiculo(new VeiculoNormal("Hyundai","Accent","29-31-TI",2004,120,2.5,new ArrayList<>(),70000,300));
        } catch (VeiculoExistenteException vee){
            System.out.println("O veiculo de matricula "+ vee.getMessage() + " já existe no sistema");
        }

        System.out.println("Testar os pontos");
        List<BonificaKms> todosOsQueDaoPontos = d.daoPontos();

        for(BonificaKms v: todosOsQueDaoPontos){
            System.out.println(v.toString());
        }

        /*
        gravar em ficheiro de texto
         */

        try {
            d.escreveFicheiro("DriveItTXT.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        guardar em modo objeto
         */
        try {
            d.guardaEstado("DriveItOBJ.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
