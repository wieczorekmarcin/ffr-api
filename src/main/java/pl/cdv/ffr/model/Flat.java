package pl.cdv.ffr.model;

public class Flat {

    private Long id;
    private String number;

    public Flat() {
    }

    public Flat(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
