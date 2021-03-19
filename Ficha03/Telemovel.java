import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

public class Telemovel {
    private String marca;
    private String modelo;
    private int resolucaoX;
    private int resolucaoY;
    private int armazenamentoMensagens;
    private String[] msgs;
    private int armazenamento;
    private int armazenamentoFotos;
    private int armazenamentoApps;
    private int espacoOcupado;
    private int fotosGuardadas;
    private int appsInstaladas;
    private String[] apps;

    public Telemovel(){
        this.marca = "";
        this.modelo = "";
        this.resolucaoX = 0;
        this.resolucaoY = 0;
        this.armazenamentoMensagens = 0;
        this.msgs = new String[this.armazenamentoMensagens];
        this.armazenamento = 0;
        this.armazenamentoFotos = 0;
        this.armazenamentoApps = 0;
        this.espacoOcupado = 0;
        this.fotosGuardadas = 0;
        this.appsInstaladas = 0;
        this.apps = new String[this.appsInstaladas];
    }

    public Telemovel(String marca, String modelo, int rX, int rY, int am, String[] msgs, int a, int af, int aa, int eo, int fg, int ai, String[] apps){
        this.marca = marca;
        this.modelo = modelo;
        this.resolucaoX = rX;
        this.resolucaoY = rY;
        this.armazenamentoMensagens = am;
        this.msgs = msgs;
        this.armazenamento = a;
        this.armazenamentoFotos = af;
        this.armazenamentoApps = aa;
        this.espacoOcupado = eo;
        this.fotosGuardadas = fg;
        this.appsInstaladas = ai;
        this.apps = apps;
    }

    public Telemovel (Telemovel t){
        this.marca = t.getMarca();
        this.modelo = t.getModelo();
        this.resolucaoX = t.getResolucaoX();
        this.resolucaoY = t.getResolucaoY();
        this.armazenamentoMensagens = t.getArmazenamentoMensagens();
        this.msgs = t.getMsgs();
        this.armazenamento = t.getArmazenamento();
        this.armazenamentoFotos = t.getArmazenamentoFotos();
        this.armazenamentoApps = t.getArmazenamentoApps();
        this.espacoOcupado = t.getEspacoOcupado();
        this.fotosGuardadas = t.getFotosGuardadas();
        this.appsInstaladas = t.getAppsInstaladas();
        this.apps = t.getApps();
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getResolucaoX() {
        return resolucaoX;
    }

    public int getResolucaoY() {
        return resolucaoY;
    }

    public int getArmazenamentoMensagens() {
        return armazenamentoMensagens;
    }

    public String[] getMsgs() {
        return msgs;
    }

    public int getArmazenamento(){
        return armazenamento;
    }

    public int getArmazenamentoFotos() {
        return armazenamentoFotos;
    }

    public int getArmazenamentoApps() {
        return armazenamentoApps;
    }

    public int getEspacoOcupado() {
        return espacoOcupado;
    }

    public int getFotosGuardadas() {
        return fotosGuardadas;
    }

    public int getAppsInstaladas() {
        return appsInstaladas;
    }

    public String[] getApps() {
        return apps;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setResolucaoX(int resolucaoX) {
        this.resolucaoX = resolucaoX;
    }

    public void setResolucaoY(int resolucaoY) {
        this.resolucaoY = resolucaoY;
    }

    public void setArmazenamentoMensagens(int armazenamentoMensagens) {
        this.armazenamentoMensagens = armazenamentoMensagens;
    }

    public void setMsgs(String[] msgs) {
        this.msgs = msgs;
    }

    public void setArmazenamento(int armazenamento){
        this.armazenamento = armazenamento;
    }

    public void setArmazenamentoFotos(int armazenamentoFotos) {
        this.armazenamentoFotos = armazenamentoFotos;
    }

    public void setArmazenamentoApps(int armazenamentoApps) {
        this.armazenamentoApps = armazenamentoApps;
    }

    public void setEspacoOcupado(int espacoOcupado) {
        this.espacoOcupado = espacoOcupado;
    }

    public void setFotosGuardadas(int fotosGuardadas) {
        this.fotosGuardadas = fotosGuardadas;
    }

    public void setAppsInstaladas(int appsInstaladas) {
        this.appsInstaladas = appsInstaladas;
    }

    public void setApps(String[] apps) {
        this.apps = apps;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telemovel telemovel = (Telemovel) o;
        return resolucaoX == telemovel.resolucaoX && resolucaoY == telemovel.resolucaoY && armazenamentoMensagens == telemovel.armazenamentoMensagens && armazenamento == telemovel.armazenamento && armazenamentoFotos == telemovel.armazenamentoFotos && armazenamentoApps == telemovel.armazenamentoApps && espacoOcupado == telemovel.espacoOcupado && fotosGuardadas == telemovel.fotosGuardadas && appsInstaladas == telemovel.appsInstaladas && Objects.equals(marca, telemovel.marca) && Objects.equals(modelo, telemovel.modelo) && Arrays.equals(msgs, telemovel.msgs) && Arrays.equals(apps, telemovel.apps);
    }

    public String toString() {
        return "Telemovel{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", resolucaoX=" + resolucaoX +
                ", resolucaoY=" + resolucaoY +
                ", armazenamentoMensagens=" + armazenamentoMensagens +
                ", msgs=" + Arrays.toString(msgs) +
                ", armazenamento=" + armazenamento +
                ", armazenamentoFotos=" + armazenamentoFotos +
                ", armazenamentoApps=" + armazenamentoApps +
                ", espacoOcupado=" + espacoOcupado +
                ", fotosGuardadas=" + fotosGuardadas +
                ", appsInstaladas=" + appsInstaladas +
                ", apps=" + Arrays.toString(apps) +
                '}';
    }

    public Telemovel clone(){
        return new Telemovel(this);
    }

    public boolean existeEspaco(int nrBytes){
        return (nrBytes <= ((this.armazenamento + this.armazenamentoMensagens) - this.espacoOcupado));
    }

    public int containsArr(String[] arr, String s){
        int i;
        int found = 0;
        for(i = 0; i < arr.length && (found == 0); i++){
            if(arr[i].equals(s)) found = 1;
        }
        if(found == 0) i = -1;
        return i;
    }

    public void instalaApp(String nome, int tamanho) {
        if (containsArr(this.apps, nome) == -1) {
            return;
        } else {
            if (this.apps.length == this.appsInstaladas) {
                String[] aux = new String[this.apps.length * 2];
                System.arraycopy(this.apps, 0, aux, 0, this.apps.length);
                this.setApps(aux);
            }
            String[] appsAux = this.getApps();
            appsAux[this.appsInstaladas++] = nome;
            this.setApps(appsAux);
            setEspacoOcupado(this.espacoOcupado + tamanho);
            setArmazenamentoApps(this.armazenamentoApps + tamanho);
        }
    }

    public void recebeMsg(String msg){
        int i;
        String[] aux = getMsgs();
        for(i = aux.length-1; i > 0; i--){
            aux[i] = aux[i-1];
        }
        aux[0] = msg;
        this.setMsgs(aux);
        setEspacoOcupado(this.espacoOcupado - 1);
        setArmazenamentoMensagens(this.armazenamentoMensagens + 1);
    }

    public double tamMedioApps(){
        return ((double) this.armazenamentoApps / this.appsInstaladas);
    }

    public String maiorMsg(){
        int max = Integer.MIN_VALUE;
        String r = "";
        for(String s : this.msgs){
            if(s.length() > max){
                max = s.length();
                r = s;
            }
        }
        if(max <= 0) r = null;
        return r;
    }

    // Function to remove the element
    public static String[] removeTheElement(String[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }
        String[] anotherArray = new String[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }

        return anotherArray;
    }

    public void removeApp(String nome, int tamanho){
        int teste = containsArr(this.apps, nome);
        if (teste == -1) {
            return;
        }
        else {
            setApps(removeTheElement(this.apps,teste));
            setEspacoOcupado(this.espacoOcupado + tamanho);
            setArmazenamentoApps(this.armazenamentoApps - tamanho);
        }
    }
}
