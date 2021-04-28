import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TesteDriveIt {
    public static final int N = 5;

    public static void main(String[] args){
        DriveIt d = new DriveIt();
        List<Integer> classif = new ArrayList<>();

        Comparator<Veiculo> cv = (v1,v2) -> (v1.getModelo().compareTo(v2.getModelo()));
        DriveIt.addComparator("ordemCrescenteModelo",cv);

        Comparator<Veiculo> cv1 = (v1,v2) -> v1.getKms() != v2.getKms() ?
                (v2.getKms() - v1.getKms()): v2.getMatricula().compareTo(v1.getMatricula());
        DriveIt.addComparator("ordemDecrescenteKilometrosMatricula",cv1);

        try{
            Veiculo v = d.getVeiculo("matricula");
        } catch (VeiculoNaoExistenteException vnee){
            System.out.println("A matricula "+ vnee.getMessage()+" não existe");
        }

    }
}
