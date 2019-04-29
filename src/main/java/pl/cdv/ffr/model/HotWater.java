package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class HotWater extends BaseMedia {

    public HotWater() {
    }

    public HotWater(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
