package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Electricity extends BaseMedia {

    public Electricity() {
    }

    public Electricity(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
