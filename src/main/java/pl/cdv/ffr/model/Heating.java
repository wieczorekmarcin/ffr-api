package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Heating extends BaseMedia {

    public Heating() {
    }

    public Heating(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
