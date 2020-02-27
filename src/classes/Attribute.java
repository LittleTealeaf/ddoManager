package classes;

public class Attribute {

    private String attribute = "";
    private String type = "Enhancement";
    private double value = 0.0;

    public Attribute() {}

    public Attribute(String attribute) {
        this.attribute = attribute;
    }

    public Attribute(String attribute, double value) {
        this.attribute = attribute;
        this.value = value;
    }

    public Attribute(String attribute, String type, double value) {
        this.attribute = attribute;
        this.type = type;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return (value >= 0 ? "+" : "-") + value + " " + type;
    }
}
