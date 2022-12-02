package core.DTOs;

public class MarkDto {
    private int value;

    private String date;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MarkDto(int value, String date) {
        this.value = value;
        this.date = date;
    }

    public MarkDto(){}
}
