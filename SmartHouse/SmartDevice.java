import java.util.Objects;

public class SmartDevice {
    private String id;
    private boolean on;

    public SmartDevice(){
        this.id = "";
        this.on = false;
    }

    public SmartDevice(String id){
        this.id = id;
        this.on = false;
    }

    public SmartDevice(String id, boolean ligado){
        this.id = id;
        this.on = ligado;
    }

    public SmartDevice (SmartDevice sd){
        setID(sd.getID());
        setOn(sd.getOn());
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public boolean getOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice that = (SmartDevice) o;
        return on == that.on && Objects.equals(id, that.id);
    }

    public String toString() {
        return "SmartDevice{" +
                "id='" + id + '\'' +
                ", on=" + on +
                '}';
    }
    @Override
    public SmartDevice clone(){
        return new SmartDevice(this);
    }
}

