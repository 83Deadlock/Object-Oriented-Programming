import java.util.Objects;

public class SmartSpeaker extends SmartDevice {

    private String channel;
    private int volume;

    public static final int MAX = 20;
    public static final int MIN = 0;

    public SmartSpeaker() {
        super();
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String id) {
        super(id);
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String id, String canal, int volume) {
        super(id);
        this.channel = canal;
        setVolume(volume);
    }

    public SmartSpeaker(SmartSpeaker ss) {
        setID(ss.getID());
        setOn(ss.getOn());
        setChannel(ss.getChannel());
        setVolume(ss.getVolume());
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String canal) {
        this.channel = canal;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (volume > MAX) {
            this.volume = 20;
        } else if (volume < MIN) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    public String toString() {
        return "SmartSpeaker{" + '\n' +
                "\tid="+ super.getID() + '\n' +
                "\ton="+ super.getOn() + '\n' +
                "\tchannel='" + channel + '\n' +
                "\tvolume=" + volume + '\n' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return volume == that.volume && Objects.equals(channel, that.channel) && super.getOn() == that.getOn() && super.getID().equals(that.getID());
    }

    public void volumeUp(){
        this.setVolume(this.getVolume() + 1);
    }

    public void volumeDown(){
        this.setVolume(this.getVolume() - 1);
    }

}
