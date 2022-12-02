package core.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Mark {
    private int value;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Mark(int value) {
        this.value = value;
        this.date = LocalDate.now();
    }

    public Mark() {}


    @Override
    public String toString() {
        return "Mark{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
