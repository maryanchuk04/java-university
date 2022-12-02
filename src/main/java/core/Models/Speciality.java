package core.Models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;


public class Speciality extends BaseModel {
    @NotEmpty(message = "Name must be valid string")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Speciality(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "\n\t\tid=" + getId() +
                "\n\t\tname='" + name + '\'' +
                "\n\t}";
    }
}
