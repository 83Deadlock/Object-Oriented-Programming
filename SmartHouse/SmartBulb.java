import java.util.Objects;

public class SmartBulb extends SmartDevice{
    private int tone; //0 cold, 1 neutral, 2 warm
    public static final int COLD = 0;
    public static int NEUTRAL = 1;
    public static int WARM = 2;



    public SmartBulb(){
        super();
        this.tone = 1;
    }

    public SmartBulb(String id){
        super(id);
        this.tone = 1;
    }

    public SmartBulb(String id, boolean on){
        super(id,on);
        this.tone = 1;
    }

    public SmartBulb(String id, int tone){
        super(id);
        this.tone = tone;
    }


    public SmartBulb(String id, boolean on, int tone){
        super(id,on);
        if(tone > 2){
            this.tone = 2;
        } else if(tone < 0){
            this.tone = 0;
        } else {
            this.tone = tone;
        }
    }

    public SmartBulb(SmartBulb sb){
        setID(sb.getID());
        setOn(sb.getOn());
        setTone(sb.getTone());
    }

    public int getTone() {
        return tone;
    }

    public void setTone(int tone) {
        if(tone > 2){
            this.tone = 2;
        } else if(tone < 0){
            this.tone = 0;
        } else {
            this.tone = tone;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartBulb smartBulb = (SmartBulb) o;
        return tone == smartBulb.tone && super.getOn() == smartBulb.getOn() && super.getID().equals(smartBulb.getID());
    }

    public String toString() {
        return "SmartBulb{" + '\n' +
                "\tid="+ super.getID() + '\n' +
                "\tton="+ super.getOn() + '\n' +
                "\ttone=" + tone + '\n' +
                '}';
    }

    public SmartBulb clone(){
        return new SmartBulb(this);
    }
}

