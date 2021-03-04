import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;

class Ficha02{
    private int[][] notasTurma;
    private LocalDate[] datas;
    private int nDatas;
    private String[] stringArr;

    public Ficha02(){
        notasTurma = new int[5][5];
        datas = new LocalDate[3];
        nDatas = 0;
    }

    public int minimoDoArray (int[] valores){
        int min = Integer.MAX_VALUE;
        for(int i : valores){
            if(i < min) min = i;
        }
        return min;
    }

    public int maximoDoArray(int[] valores){
        int max = Integer.MIN_VALUE;
        for(int i : valores){
            if(i > max) max = i;
        }
        return max;
    }

    public int[] subArray(int[] valores, int inf, int sup){
        int size = sup - inf + 1;
        int[] r = new int[size];
        System.arraycopy(valores,inf,r,0,size);
        return r;
    }

    public int[] arraysComum(int[] v1, int[] v2){
        int[] r = new int[(Math.min(v1.length,v2.length))];
        int i = 0;
        for(int k : v1){
            for(int j : v2){
                if(k == j) r[i++] = k;
            }
        }
        return r;
    }

    public void updatePauta(int[][] p){
        for(int i = 0; i < 5; i++){
            System.arraycopy(p[i],0,this.notasTurma[i],0,5);
        }
    }

    public String printPauta(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i++){
            sb.append("---Aluno "+i+"---\n");
            for(int j = 0; j < 5; j++){
                sb.append("UC " + j +": "+this.notasTurma[i][j]+" ||| ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int somaNotasUC(int uc){
        int sum = 0;
        for(int a = 0; a < 5; a++){
            sum += notasTurma[a][uc];
        }
        return sum;
    }

    public float calculaMediaAluno (int aluno){
        int sum = 0;
        for(int u = 0; u < 5; u++){
            sum += notasTurma[aluno][u];
        }
        return ((float) sum/5);
    }

    public float calculaMediaUC (int uc){
        int sum = 0;
        for(int aluno = 0; aluno < 5; aluno++){
            sum += notasTurma[aluno][uc];
        }
        return ((float) sum/5);
    }

    public int[] notasMaisAltasUC(){
        int[] r = new int[5];
        int[] aux = new int[5];
        for(int uc = 0; uc < 5; uc++){
            for(int a = 0; a < 5; a++){
                aux[a] = notasTurma[a][uc];
            }
            r[uc] = maximoDoArray(aux);
        }
        return r;
    }

    public int[] notasMaisBaixasUC(){
        int[] r = new int[5];
        int[] aux = new int[5];
        for(int uc = 0; uc < 5; uc++){
            for(int a = 0; a < 5; a++){
                aux[a] = notasTurma[a][uc];
            }
            r[uc] = minimoDoArray(aux);
        }
        return r;
    }

    public int[] notasMinimas(int nm){
        int[] aux = new int[25];
        int k = 0;
        for(int a = 0; a < 5; a++){
            for(int u = 0; u < 5; u++){
                if(notasTurma[a][u] >= nm) aux[k++] = notasTurma[a][u];
            }
        }
        int[] r = new int[k];
        System.arraycopy(aux,0,r,0,k);
        return r;
    }

    public int UCMediaMaisAlta(){
        int uc = -1;
        float max = Float.MIN_VALUE;

        float[] medias = new float[5];
        for(int i = 0; i < 5; i++){
            medias[i] = calculaMediaUC(i);
            if(medias[i] > max){
                uc = i;
                max = medias[i];
            }
        }

        return uc;
    }

    public void insereData(LocalDate data){
        if(nDatas == this.datas.length){
            LocalDate[] aux = new LocalDate[datas.length * 2];
            System.arraycopy(datas,0,aux,0,nDatas);
            this.datas = aux;
        }
        this.datas[nDatas++] = data;
    }

    public LocalDate dataMaisProxima(LocalDate date){
        LocalDate closest;
        Period diffMin;
        Period diffCurr;
        if(nDatas == 0) return null;
        else{
            closest = datas[0];
            diffMin = Period.between(date,closest);
            for(int i = 1; i < nDatas; i++){
                diffCurr = Period.between(date,datas[i]);
                if(Math.abs(diffCurr.getDays()) < Math.abs(diffMin.getDays())){
                    diffMin = diffCurr;
                    closest = datas[i];
                }
            }
        }
        return closest;
    }

    public String datasToString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nDatas; i++){
            sb.append(datas[i].toString()+"\n");
        }
        return sb.toString();
    }

    public int[] orderCresc(int[] v){
        Arrays.sort(v,0,(v.length-1));
        return v;
    }

    int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    void setStringArr(String[] arr){
        this.stringArr = arr;
    }

    int elemArrString(String[] v, String elem){
        int found = -1;
        int size = v.length;

        for(int i = 0; i < size && (found == -1); i++){
            if(elem.equals(v[i])) found = i;
        }

        return found;
    }

    String getArrSemReps(){
        StringBuilder sb = new StringBuilder();
        String[] aux = new String[this.stringArr.length];
        int iAux = 0;
        for(String s : this.stringArr){
            if(elemArrString(aux,s) == -1){
                aux[iAux++] = s;
                sb.append(iAux + " | " + s + "\n");
            }
        }
        return sb.toString();
    }

    String printReps(){
        StringBuilder sb = new StringBuilder();
        String[] aux = new String[this.stringArr.length];
        int iAux = 0;
        for(String s : this.stringArr){
            int f = elemArrString(aux,s);
            if(f == -1){
                aux[iAux++] = s;
            } else {
                sb.append(s + "\n");
                aux[f] = "";
            }
        }
        return sb.toString();
    }

    String maiorString(){
        int biggest = Integer.MIN_VALUE;
        String s = "";
        int size = this.stringArr.length;
        for(int i = 0; i < size; i++){
            if(stringArr[i].length() > biggest){
                biggest = stringArr[i].length();
                s = stringArr[i];
            }
        }
        return s;
    }

    int contaReps(String s){
        int size = this.stringArr.length;
        int count = 0;
        for(int i = 0; i < size; i++){
            if(s.equals(stringArr[i])) count++;
        }
        return count;
    }

    int[][] somaMatriz(int[][] m1, int[][] m2, int l, int c){
        int[][] m = new int[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                m[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return m;
    }

    boolean matrizesIguais(int[][] m1, int[][] m2, int l, int c){
        boolean equal = true;
        for(int i = 0; i < l && equal; i++){
            for(int j = 0; j < c && equal; j++){
                if(m1[i][j] != m2[i][j]) equal = false;
            }
        }
        return equal;
    }

    int[][] matrizOposta(int [][] m, int l, int c){
        int[][] mf = new int[l][c];

        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                mf[i][j] = -(m[i][j]);
            }
        }

        return mf;
    }

    int[] geraChaveVencedora(){
        int[] r = new int[7];

        int i = 0;
        while(i != 5){
            r[i] = (int) ((Math.random() * (50 - 1)) + 1);
            i++;
        }
        while(i != 7){
            r[i] = (int) ((Math.random() * (9 - 1)) + 1);
            i++;
        }

        return r;
    }

    boolean verificaChave (int[] w, int[] c){
        boolean venceu = true;
        for(int i = 0; i < 7 && venceu; i++){
            if(w[i] != c[i]) venceu = false;
        }
        return venceu;
    }
}

public class Main {
    public static void e1(Ficha02 f){
        Scanner input = new Scanner(System.in);

        int a = -1;
        while(a != 0){
            System.out.println("1.- Ler inteiros para um array e determinar o valor mínimo desse array");
            System.out.println("2.- Ler um array e dois índices e determinar o array com os valores entre esses índices");
            System.out.println("3.- Ler dois arrays de ints e determinar o array com os elements comuns aos dois arrays");
            System.out.println("0.- Sair");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch(a){
                case 1: System.out.print("Quantos elementos quer adicionar? ");
                        int n = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int k;
                        int[] valores = new int[n];
                        for(int i = 0; i < n; i++){
                            System.out.print("Valor a inserir ("+(n-i)+" restantes): ");
                            k = input.nextInt();
                            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                            valores[i] = k;
                        }
                        System.out.println(f.minimoDoArray(valores) + " é o menor elemento do array");
                        break;
                case 2: System.out.print("Quantos elementos quer adicionar? ");
                        n = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        valores = new int[n];
                        for(int i = 0; i < n; i++){
                            System.out.print("Valor a inserir ("+(n-i)+" restantes): ");
                            k = input.nextInt();
                            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                            valores[i] = k;
                        }
                        System.out.print("Limite inferior: ");
                        int inf = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        System.out.print("Limite superior: ");
                        int sup = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above

                        int[] newA = f.subArray(valores,inf,sup);
                        for(int c = 0; c < newA.length; c++){
                            System.out.println(c +": "+newA[c]);
                        }
                        break;
                case 3: System.out.print("Quantos elementos quer adicionar? ");
                        n = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int[] valores1 = new int[n];
                        for(int i = 0; i < n; i++){
                            System.out.print("Valor a inserir ("+(n-i)+" restantes): ");
                            k = input.nextInt();
                            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                            valores1[i] = k;
                        }
                        System.out.print("Quantos elementos quer adicionar? ");
                        n = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int[] valores2 = new int[n];
                        for(int i = 0; i < n; i++){
                            System.out.print("Valor a inserir ("+(n-i)+" restantes): ");
                            k = input.nextInt();
                            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                            valores2[i] = k;
                        }
                        int[] comum = f.arraysComum(valores1,valores2);
                        for(int c = 0; c < comum.length; c++){
                            System.out.println(c +": "+comum[c]);
                        }
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção inválida");
                         break;
            }
        }
    }

    public static void e2(Ficha02 f){
        Scanner input = new Scanner(System.in);
        int[][] notasTurma = new int[5][5];
        int a = -1;
        while(a != 0){
            System.out.println("-----------------------------------MENU------------------------------------");
            System.out.println("1.- Ler as notas dos alunos e atualizar o array da pauta");
            System.out.println("2.- calcular a soma das notas a uma determinada UC");
            System.out.println("3.- Calcular a média das notas de um aluno");
            System.out.println("4.- Média das notas de uma UC");
            System.out.println("5.- Nota mais alta a todas as UCs de todos os alunos");
            System.out.println("6.- Nota mais baixa a todas as UCs de todos os alunos");
            System.out.println("7.- Array com as notas acima de um determinado valor");
            System.out.println("8.- String com as notas de todos os alinos a todas as UCs");
            System.out.println("9.- Índice da UC com a média mais elevada");
            System.out.println("0.- Sair");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch(a){
                case 1: for(int i = 0; i < 5; i++){
                            System.out.println("---Notas do aluno "+i+"---");
                            for(int j = 0; j < 5; j++){
                                System.out.print("Na UC "+j+": ");
                                notasTurma[i][j] = input.nextInt();
                                input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                            }
                        }
                        f.updatePauta(notasTurma);
                        break;
                case 2: System.out.print("UC: ");
                        int uc = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int sum = f.somaNotasUC(uc);
                        System.out.println("A soma das notas da UC "+uc+" é "+sum);
                        break;
                case 3: System.out.print("Aluno: ");
                        int aluno = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        float media = f.calculaMediaAluno(aluno);
                        System.out.printf("A média do aluno %d é %f.2\n",aluno,media);
                        break;
                case 4: System.out.print("UC: ");
                        uc = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        media = f.calculaMediaUC(uc);
                        System.out.printf("A média da UC %d é %f\n",uc,media);
                        break;
                case 5: int[] notasMaisAltas;
                        notasMaisAltas = f.notasMaisAltasUC();
                        for(int t = 0; t < 5; t++){
                            System.out.println("A nota mais alta da UC "+t+" é "+notasMaisAltas[t]);
                        }
                        break;
                case 6: int[] notasMaisBaixas;
                        notasMaisBaixas = f.notasMaisBaixasUC();
                        for(int t = 0; t < 5; t++){
                            System.out.println("A nota mais baixa da UC "+t+" é "+notasMaisBaixas[t]);
                        }
                        break;
                case 7: System.out.print("Valor de nota mínimo: ");
                        int nm = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int[] passou = f.notasMinimas(nm);
                        for(int t = 0; t < passou.length; t++){
                            System.out.println(passou[t]);
                        }
                        break;
                case 8: System.out.println("-----------------PAUTA-----------------");
                         System.out.print(f.printPauta());
                         System.out.println("---------------------------------------");
                         break;
                case 9: uc = f.UCMediaMaisAlta();
                        System.out.println("A UC com a média mais alta é "+uc);
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção inválida");
                         break;
            }
        }
    }

    public static void e3(Ficha02 f){
        Scanner input = new Scanner(System.in);

        int a = -1;
        while(a != 0){
            System.out.println("-----------------------------------MENU------------------------------------");
            System.out.println("1.- Inserir uma nova data");
            System.out.println("2.- Dada uma data, determinar a data mais próxima");
            System.out.println("3.- Devolver uma String com todas as datas");
            System.out.println("0.- Sair");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch(a){
                case 1: System.out.print("Insira o ano: ");
                        int y = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        System.out.print("Insira o mes: ");
                        int m = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        System.out.print("Insira o dia: ");
                        int d = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        LocalDate date = LocalDate.of(y,m,d);
                        f.insereData(date);
                        System.out.println("Data inserida!");
                        break;
                case 2: System.out.print("Insira o ano: ");
                        y = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        System.out.print("Insira o mes: ");
                        m = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        System.out.print("Insira o dia: ");
                        d = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above

                        date = LocalDate.of(y,m,d);
                        LocalDate closest = f.dataMaisProxima(date);
                        System.out.println("A data mais pŕoxima é "+closest.toString());
                        break;
                case 3: System.out.print(f.datasToString());
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção inválida");
                         break;
            }
        }
    }

    public static void e4(Ficha02 f){
        Scanner input = new Scanner(System.in);
        int[] arr = {111,120,121,80,83,85,86,137,139,141,150,152,154,90,95,101,108,109,124,131,133,136,158,165,4,5,6,9,13,14,24,29,30,35,43,44,46,50,51,53,55,56,61,64,66,71,76};
        int a = -1;
        while(a != 0){
            System.out.println("-----------------------------------MENU------------------------------------");
            System.out.print("Array inicial -> ");
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i] + ", ");
            }
            System.out.print("\n");
            System.out.println("1.- Ordena o array por ordem crescente");
            System.out.println("2.- Prócura binária por um elemento do array");
            System.out.println("0.- Sair");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch(a){
                case 1: arr = f.orderCresc(arr);
                        for(int i = 0; i < arr.length; i++){
                            System.out.print(arr[i] +", ");
                        }
                        System.out.print("\n total = " + arr.length+"\n");
                        break;
                case 2: System.out.print("Elemento a procurar: ");
                        int elem = input.nextInt();
                        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
                        int n = arr.length;
                        int pos = f.binarySearch(arr,0,n - 1, elem);
                        if(pos == -1){
                            System.out.println("Elemento não pertence ao array");
                        } else {
                            System.out.println("O elemento " + elem +" encontra-se na posição "+ pos+" do array.");
                        }
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção Inválida");
                         break;
            }
        }
    }

    public static void e5(Ficha02 f){
        Scanner input = new Scanner(System.in);

        int a = -1;
        while(a != 0){
            System.out.println("-----------------------------------MENU------------------------------------");
            System.out.println("1.- Inserir strings");
            System.out.println("2.- Determinar o array com as Strings existentes (sem repetições)");
            System.out.println("3.- Determinar a maior String inserida");
            System.out.println("4.- Determinar um array com as Strings que aparecem mais de uma vez");
            System.out.println("5.- Determinar quantas vezes uma determinada String ocorre no array");
            System.out.println("0.- Sair");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch (a){
                case 1: System.out.print("Quantas strings pretende inserir? ");
                        int n = input.nextInt();
                        input.nextLine();                   //Consumes the '\n' which is ignored by nextInt() on line above
                        String[] arr = new String[n];
                        for(int i = 0; i < n; i++){
                            System.out.println("Insira a string (" + (n-i) + " restantes!)");
                            arr[i] = input.nextLine();
                        }
                        f.setStringArr(arr);
                        break;
                case 2: System.out.println(f.getArrSemReps());
                        break;
                case 3: System.out.println(f.maiorString());
                        break;
                case 4: System.out.println(f.printReps());
                        break;
                case 5: System.out.println("String a procurar:");
                        String s = input.nextLine();
                        int count = f.contaReps(s);
                        if(count == 0) System.out.println("A string " + s + " não ocorre no array!");
                        else if (count == 1){
                            System.out.println("A string " + s + " ocorre apenas uma vez no array!");
                        } else if (count > 1){
                            System.out.println("A string " + s + " ocorre " + count + " vezes no array!");
                        }
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção Inválida");
                         break;
            }
        }
    }

    public static int[][] readMatrix(Scanner input){
        System.out.print("Número de linhas: ");
        int l = input.nextInt();
        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
        System.out.print("Número de colunas: ");
        int c = input.nextInt();
        input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
        int m1 [][] = new int[l][c];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                System.out.print("("+i+","+j+") -> ");
                m1[i][j] = input.nextInt();
                input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            }
        }
        return m1;
    }

    public static void e6(Ficha02 f){
        Scanner input = new Scanner(System.in);

        int a = -1;
        while(a != 0){
            System.out.println("-----------------------------------MENU------------------------------------");
            System.out.println("1.- Ler uma matriz");
            System.out.println("2.- Soma de duas matrizes");
            System.out.println("3.- Determinar se duas matrizes são iguais");
            System.out.println("4.- Determinar a matriz oposta de uma matriz");
            System.out.println("0.- Sair");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Alínea a executar: ");
            a = input.nextInt();
            input.nextLine(); //Consumes the '\n' which is ignored by nextInt() on line above
            switch(a){
                case 1: int[][] m = readMatrix(input);
                        break;
                case 2: int[][] m1 = readMatrix(input);
                        int l1 = m1.length;
                        int c1 = m1[0].length;
                        int[][] m2 = readMatrix(input);
                        int l2 = m2.length;
                        int c2 = m2[0].length;
                        int[][] mf = new int[l1][c1];
                        if(l1 == l2 && c1 == c2){
                            mf = f.somaMatriz(m1,m2,l1,c1);
                            for(int i = 0; i < l1; i++){
                                System.out.print("| ");
                                for(int j = 0; j < c1; j++){
                                    System.out.print(mf[i][j]+" | ");
                                }
                                System.out.print("\n");
                            }
                        }else{
                            System.out.println("Não se pode somar matrizes de dimensões diferentes!");
                        }
                        break;
                case 3: m1 = readMatrix(input);
                        l1 = m1.length;
                        c1 = m1[0].length;
                        m2 = readMatrix(input);
                        l2 = m2.length;
                        c2 = m2[0].length;
                        mf = new int[l1][c1];
                        if(l1 == l2 && c1 == c2 && f.matrizesIguais(m1,m2,l1,c1)){
                            System.out.println("As matrizes são iguais");
                        } else {
                            System.out.println("As matrizes não são iguais");
                        }
                        break;
                case 4: m1 = readMatrix(input);
                        l1 = m1.length;
                        c1 = m1[0].length;
                        mf = f.matrizOposta(m1,l1,c1);
                        for(int i = 0; i < l1; i++){
                            System.out.print("| ");
                            for(int j = 0; j < c1; j++){
                                System.out.print(mf[i][j]+" | ");
                            }
                            System.out.print("\n");
                        }
                        break;
                case 0: System.out.println("Voltando ao menu inicial");
                        break;
                default: System.out.println("Opção Inválida");
                         break;
            }
        }
    }

    public static void e7(Ficha02 f){
        Scanner input = new Scanner(System.in);
        int[] chave = new int[7];
        int i = 0;
        int[] winner = f.geraChaveVencedora();
        System.out.print("winner = ");
        for(int o = 0; o < 7; o++){
            System.out.print(winner[o] + " | ");
        }
        System.out.print("\n");
        System.out.print("Insira os 5 números: ");
        while(i != 5){
            chave[i] = input.nextInt();
            input.nextLine();
            i++;
        }
        System.out.print("Insira as 2 estrelas: ");
        while(i != 7){
            chave[i] = input.nextInt();
            input.nextLine();
            i++;
        }

        if(f.verificaChave(winner,chave)){
            int spaces = 0;
            int o = 0;
            for(int j = 0; j < 50; j++){
                for(int k = 0; k < spaces; k++){
                    System.out.print(" ");
                }
                System.out.print("| Números: ");
                for(o = 0; o < 5; o++){
                    System.out.print(chave[o] + " ");
                }
                System.out.print("| Estrelas: ");
                for(o = 5; o < 7; o++){
                    System.out.print(chave[o] + " ");
                }
                spaces += 2;
                System.out.print("\n");
            }
        } else {
            System.out.println("bad luck :(");
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Ficha02 f = new Ficha02();

        int e = -1;
        while(e != 0){
            System.out.print("Pergunta a executar: ");
            e = in.nextInt();
            switch(e){
                case 1: e1(f);
                        break;
                case 2: e2(f);
                        break;
                case 3: e3(f);
                        break;
                case 4: e4(f);
                        break;
                case 5: e5(f);
                        break;
                case 6: e6(f);
                        break;
                case 7: e7(f);
                        break;
                case 0: System.out.print("Terminando");
                        break;
                default: System.out.println("opção inválida, tente novamente!");
                         break;
            }
        }
    }
}
