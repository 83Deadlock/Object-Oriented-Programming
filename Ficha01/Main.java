import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;

class ExerciciosI {
    private Scanner input = new Scanner(System.in);
    /** Given a date, returns the correspondent day of the week
     *
     * @return String with the day of the week
     */
    public String diaDaSemana(){

        System.out.print("Day: ");
        int d = input.nextInt();
        System.out.print("Month: ");
        int m = input.nextInt();
        System.out.print("Year: ");
        int y = input.nextInt();

        int r = y - 1900;

        r *= 365;

        r += (y-1900)/4;

        if((((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) && (y == 1 || y == 2)){
            r--;
        }


        //Adicionar os dias já passados no corrente ano
        for(; m > 0; m--){
            if(d == 0){
                if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12){
                    d = 31;
                }
                else if (m == 4 || m == 6 || m == 9 || m == 11){
                    d = 30;
                }
                else if (m == 2) d = 28;
            }
            r += d;
            d = 0;
        }



        r = r % 7;
        String dayOfTheWeek = null;

        switch(r){
            case 0: dayOfTheWeek = "Domingo";
                break;
            case 1: dayOfTheWeek = "Segunda-feira";
                break;
            case 2: dayOfTheWeek = "Terça-feira";
                break;
            case 3: dayOfTheWeek = "Quarta-feira";
                break;
            case 4: dayOfTheWeek = "Quinta-feira";
                break;
            case 5: dayOfTheWeek = "Sexta-feira";
                break;
            case 6: dayOfTheWeek = "Sábado";
                break;
        }
        return dayOfTheWeek;

    }

    public String somaDatas(){
        LocalDateTime date1 = LocalDateTime.now();

        int d,h,min,s;

        System.out.print("Day: ");
        d = input.nextInt();
        System.out.print("Hour: ");
        h = input.nextInt();
        System.out.print("Minute: ");
        min = input.nextInt();
        System.out.print("Second: ");
        s = input.nextInt();


        s += date1.getSecond();
        while(s >= 60){
            min++;
            s = s - 60;
        }
        min += date1.getMinute();
        while(min >= 60){
            h++;
            min = min - 60;
        }
        h += date1.getHour();
        while(h >= 24){
            d++;
            h = h - 24;
        }
        d += date1.getDayOfMonth();

        String out = d + "D " + h + "H " + min + "m " + s + "s";
        return out;
    }

    public void classificacoes(){
        System.out.println("Quando quiser terminar de inserir classificações, insira -1");

        int zerocinco = 0;
        int cincodez = 0;
        int dezquinze = 0;
        int quinzevinte = 0;
        System.out.print("Nota: ");
        int nota = input.nextInt();
        while(nota != -1){
            if(0 <= nota && nota < 5) zerocinco++;
            else if(5 <= nota && nota < 10) cincodez++;
            else if(10 <= nota && nota < 15) dezquinze++;
            else if(15 <= nota && nota <= 20) quinzevinte++;

            System.out.print("Nota: ");
            nota = input.nextInt();
        }
        System.out.println("[0,5[ -> " + zerocinco);
        System.out.println("[5,10[ -> " + cincodez);
        System.out.println("[10,15[ -> " + dezquinze);
        System.out.println("[15,20] -> " + quinzevinte);
    }

    public void temperaturas(){
        System.out.print("Quantas temperaturas quer inserir?");
        int n = input.nextInt();
        int i;
        int max = 0;
        int daymax = 0;
        int tempAnt = 0;
        int temp;
        float media = 0;
        for(i = 0; i < n; i++){
            System.out.print("Temperatura: ");
            temp = input.nextInt();
            media += temp;
            if(i == 0){
                tempAnt = temp;
            }
            else{
                if(Math.abs(temp - tempAnt) > max){
                    max = Math.abs(temp-tempAnt);
                    daymax = i;
                }
            }
        }
        media = media / n;

        System.out.println("A média das " + n + " temperaturs foi de " + media + ".");
        System.out.println("A maior vairação registou-se entre os dias ");
    }

    public void triangulos(){
        float base = (float) 01.00;
        float altura = (float) 00.00;
        float area = (float) 00.00;

        while(true){
            System.out.print("base: ");
            base = input.nextFloat();
            if(base == (float) 00.00) break;
            System.out.print("altura: ");
            altura = input.nextFloat();
            area = (base * altura) / 2;
            System.out.println("area = " + area);
        }
    }

    public boolean ePrimo(int n){
        if(n <= 3) return n > 1;
        if(n % 2 == 0 || n % 3 == 0){
            return false;
        }
        int i = 5;
        while(Math.pow(i,2) <= n){
            if(n % i == 0 || n % (i+2) == 0) return false;
            i += 6;
        }
        return true;
    }

    public void primos(){
        int n = 0;
        int stay = 1;

        while(stay == 1){
            System.out.print("Insira um número: ");
            n = input.nextInt();

            while(n > 0){
                if(ePrimo(n)) System.out.println(n + " ");
                n--;
            }

            System.out.print("Deseja continuar a jogar? (1 se sim): ");
            stay = input.nextInt();
        }
    }

    public void idadeEmHoras() {
        System.out.print("Day: ");
        int d = input.nextInt();
        System.out.print("Month: ");
        int m = input.nextInt();
        System.out.print("Year: ");
        int y = input.nextInt();

        LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.of(y,m,d);

        Period diff = Period.between(birth,today);
        int dS = diff.getDays();
        int mS = diff.getMonths();
        int yS = diff.getYears();

        System.out.println(diff.getYears() + " anos");
        System.out.println(diff.getMonths() + " Meses");
        System.out.println(diff.getDays() + " Dias");

        int r = dS;
        int mC = 0;
        boolean flag = false;
        while(m + mS != m){
            mC = m + mS;
            mC = mC % 12;
            if(mC == 0 || mC == 1 || mC == 3 || mC == 5 || mC == 7 || mC == 8 || mC == 10){
                r += 31;
            } else if (mC == 4 || mC == 6 || mC == 9 || mC == 11){
                r += 30;
            }
            else if(mC == 2){
                r += 28;
            }
            mS--;
        }
        int yC = 0;
        while(y + yS != y){
            yC = y + yS;
            if(((((yC % 4 == 0) && (yC % 100 != 0)) || (yC % 400 == 0)) && (yC == 1 || yC == 2))){
                r += 366;
            } else {
                r += 365;
            }
            yS--;
        }

        r *= 24;

        System.out.println(r + " Horas no total");
    }
}

class ExerciciosII{
    public double celsiusParaFarenheit (double graus){
        return ((graus*1.8) + 32);
    }

    public int maximoNumeros(int a, int b){
        if(a > b) return a;
        return b;
    }

    public String criaDescricaoConta (String nome, double saldo){
        String s = "nome: " + nome + "; saldo: " + saldo;
        return s;
    }

    public double eurosParaLibras (double valor, double taxa){
        return (valor * taxa);
    }

    public String p5 (int a, int b){
        StringBuilder sb = new StringBuilder();
        if(a > b){
            sb.append(a + " " + b);
        } else sb.append(b + " " + a);

        double media = (a + b) / 2;
        sb.append(" media -> " + media);
        return sb.toString();
    }

    public long fatorial (int a){
        long fat = 1;
        while(a > 1){
            fat *= a;
            a--;
        }
        return fat;
    }

    public long tempoGasto(){

        LocalDateTime now1 = LocalDateTime.now();

        System.out.println(now1.toString());

        long x = Timestamp.valueOf(now1).getTime();

        fatorial(5000);

        LocalDateTime now2 =  LocalDateTime.now();

        System.out.println(now2.toString());

        long y = Timestamp.valueOf(now2).getTime();

        return (y-x);
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int e = -1;
        while(e != 0){
            System.out.println("Exercícios 1 ou 2 (0 para sair): " );
            e = in.nextInt();
            switch(e){
                case 1: exercicios1();
                        break;
                case 2: exercicios2();
                        break;
                case 0: break;
            }
        }
        System.out.println("Terminando");
    }

    public static void exercicios1 () {
        Scanner input = new Scanner(System.in);
        ExerciciosI f = new ExerciciosI();
        int q = -1;
        String out = null;
        while (q != 0) {
            System.out.print("Pergunta a executar (0 para sair): ");
            q = input.nextInt();
            switch (q) {
                case 0:
                    System.out.println("Voltando ao menu inicial");
                    break;
                case 1:
                    out = f.diaDaSemana();
                    System.out.println(out);
                    break;
                case 2:
                    out = f.somaDatas();
                    System.out.println(out);
                    break;
                case 3:
                    f.classificacoes();
                    break;
                case 4:
                    f.temperaturas();
                    break;
                case 5:
                    f.triangulos();
                    break;
                case 6:
                    f.primos();
                    break;
                case 7:
                    f.idadeEmHoras();
                    break;
                default:
                    break;
            }

        }
    }

    public static void exercicios2 () {
        Scanner input = new Scanner(System.in);
        ExerciciosII f = new ExerciciosII();
        int q = -1;
        String out = null;
        while (q != 0) {
            System.out.print("Pergunta a executar (0 para sair): ");
            q = input.nextInt();
            switch (q) {
                case 0:
                    System.out.println("Voltando ao menu inicial");
                    break;
                case 1:
                    System.out.print("Temperatura em Celsius: ");
                    double graus = input.nextDouble();
                    double farenheit = f.celsiusParaFarenheit(graus);
                    System.out.println(farenheit + "ºF");
                    break;
                case 2:
                    System.out.print("Insira o valor a: ");
                    int a = input.nextInt();
                    System.out.print("Insira o valor b: ");
                    int b = input.nextInt();
                    int r = f.maximoNumeros(a,b);
                    System.out.println(r);
                    break;
                case 3:
                    System.out.println("Nome da conta: ");
                    String nome = (String) input.next();
                    System.out.println("Saldo da conta: ");
                    double saldo = input.nextDouble();
                    out = f.criaDescricaoConta(nome, saldo);
                    System.out.println(out);
                    break;
                case 4:
                    System.out.print("Valor em euros: ");
                    double euros = input.nextDouble();
                    System.out.print("Taxa de Cnversão: ");
                    double taxa = input.nextDouble();
                    double libras = f.eurosParaLibras(euros,taxa);
                    System.out.println(libras + "£");
                    break;
                case 5:
                    System.out.print("Insira o valor a: ");
                    a = input.nextInt();
                    System.out.print("Insira o valor b: ");
                    b = input.nextInt();
                    out = f.p5(a,b);
                    System.out.println(out);
                    break;
                case 6:
                    System.out.print("Insira o número para fatorizar: ");
                    int x = input.nextInt();
                    long fact = f.fatorial(x);
                    System.out.println(x + "! = " + fact);
                    break;
                case 7:
                    long ms = f.tempoGasto();
                    System.out.println(ms + "ms");
                    break;
                default:
                    break;
            }

        }
    }
}
