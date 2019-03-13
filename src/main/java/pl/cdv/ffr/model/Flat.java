package pl.cdv.ffr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FLAT")
public class Flat {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flat_seq")
    @SequenceGenerator(name = "flat_seq", sequenceName = "flat_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NUMBER")
    @NotNull
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
