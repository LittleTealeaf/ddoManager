package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enhancement {

    private String name = "";
    private String description = "";
    private List<Attribute> bonuses = new ArrayList<>();

    public Enhancement(String name) {
        this.name = name;
    }

    public Enhancement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Enhancement(String name, String description, Attribute... bonuses) {
        this.name = name;
        this.description = description;
        this.bonuses = Arrays.asList(bonuses);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attribute> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Attribute> bonuses) {
        this.bonuses = bonuses;
    }
}
