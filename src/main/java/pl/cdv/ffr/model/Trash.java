package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Trash extends BaseMedia {

    public Trash() {
    }

    public Trash(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
