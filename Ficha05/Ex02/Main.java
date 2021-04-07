package Ex2;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Aluno a1 = new Aluno("a00000",16,"Marcelo Sousa","MiEI");
        Aluno a2 = new Aluno("a00001",9,"Antionio Costa","MiEI");
        Aluno a3 = new Aluno("a00002",20,"Nelito","Gota");

        Turma t = new Turma("PL8","POO",new HashMap<>());
        t.insereAluno(a1);
        t.insereAluno(a2);
        t.insereAluno(a3);


        System.out.println(t.toString());
        System.out.println("\n\n\n\n\nAlunos Por Ordem Alfabética");
        System.out.println(t.alunosOrdemAlfabetica().toString());
        System.out.println("\n\n\n\n\nAlunos Por Ordem Decrescente do Número");
        System.out.println(t.alunosOrdemDescrescenteNumero().toString());

        t.removeAluno("a00000");
        System.out.println("\n\n\n\n\nTURMA");
        System.out.println(t.toString());
        System.out.println("\n\n\n\n\nQuantos alunos inscritos?");
        System.out.println("Inscritos = " + t.qtsAlunos());
        System.out.println("\n\n\n\n\nCodigos dos Alunos");
        System.out.println(t.todosOsCodigos().toString());
    }
}
