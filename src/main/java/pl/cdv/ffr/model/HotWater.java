package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class HotWater extends BaseMedia {

    public HotWater() {
    }

    public HotWater(String rate, String used, String unit, String date, String amount, String currency) {
        super(rate, used, unit, date, amount, currency);
    }
}
