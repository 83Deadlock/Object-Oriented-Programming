import java.util.*;
import java.util.stream.Collectors;

public class CasaInteligente {
    private String morada;
    private Map<String,SmartDevice> devices;
    private Map<String, List<String>> divisoes;

    public CasaInteligente(){
        this.morada = "";
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public CasaInteligente(String morada){
        this.morada = morada;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public CasaInteligente(CasaInteligente ci){
        setMorada(ci.getMorada());
        setDevices(ci.getDevices());
        setDivisoes(ci.getDivisoes());
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Map<String,SmartDevice> getDevices() {
        return this.devices.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().clone()));
    }

    public void setDevices(Map<String,SmartDevice> devices) {
        this.devices = new HashMap<>();
        devices.entrySet().forEach(e -> this.devices.put(e.getKey(),e.getValue().clone()));

    }

    public Map<String, List<String>> getDivisoes() {

        return this.divisoes.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().stream().collect(Collectors.toList())));
    }

    public void setDivisoes(Map<String, List<String>> divisoes) {
        this.divisoes = new HashMap<>();
        divisoes.entrySet().forEach(e -> this.divisoes.put(e.getKey(),e.getValue().stream().collect(Collectors.toList())));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CasaInteligente that = (CasaInteligente) o;
        return Objects.equals(morada, that.morada) && Objects.equals(devices, that.devices) && Objects.equals(divisoes, that.divisoes);
    }

    public String toString() {
        return "CasaInteligente{" +
                "morada='" + morada + '\'' +
                ", devices=" + devices +
                ", divisoes=" + divisoes +
                '}';
    }

    public CasaInteligente clone(){
        return new CasaInteligente(this);
    }

    public boolean existsDevice(String code){
        return this.devices.containsKey(code);
    }

    public void addDevice(SmartDevice sd){
        this.devices.put(sd.getID(),sd.clone());
    }

    public SmartDevice getDevice(String code){
        if(existsDevice(code)){
            return this.devices.get(code).clone();
        } else {
            return null;
        }
    }

    public void setAllOn(boolean estado){
        this.devices.values().stream().forEach(k -> k.setOn(estado));
    }

    public boolean hasRoom (String room){
        return this.divisoes.keySet().contains(room);
    }

    public void addRoom (String room){
        this.divisoes.putIfAbsent(room,new ArrayList<>());
    }

    public void addToRoom (String room, String code){
        addRoom(room);
        this.divisoes.get(room).add(code);
    }

    public boolean roomHasDevice (String room, String code){
        return (this.divisoes.get(room).contains(code));
    }
}
