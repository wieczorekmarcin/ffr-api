package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class ColdWater extends BaseMedia {

    public ColdWater() {
    }

    public ColdWater(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
