package Model;

public class Rule {
    String part;
    String sign;
    int value;
    String destination;

    public Rule(String part, String sign, String value, String destination) {
        this.part = part;
        this.sign = sign;
        this.value = Integer.parseInt(value);
        this.destination = destination;
    }

    public Rule(String destination) {
        this.destination = destination;
        this.part = null;
        this.sign = null;
        this.value = 0;
    }

    public String getPart() {
        return part;
    }

    public String getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }

    public String getDestination() {
        return destination;
    }

    public String getNextRule(int x, int m, int a, int s){
        if(part==null)
            return destination;
        int compareValue;
        switch (part){
            case "x" -> compareValue = x;
            case "m" -> compareValue = m;
            case "a" -> compareValue = a;
            case "s" -> compareValue = s;
            default -> compareValue = 0;
        }
        if(sign.equals("<") && compareValue < value)
            return destination;
        else if(sign.equals(">") && compareValue> value)
            return destination;
        else
            return null;
    }

    public boolean isFinalDestination(){
        return part==null;
    }
}
