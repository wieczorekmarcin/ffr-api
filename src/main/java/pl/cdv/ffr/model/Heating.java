package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Heating extends BaseMedia {

    public Heating() {
    }

    public Heating(String rate, String used, String unit, String date, String amount, String currency) {
        super(rate, used, unit, date, amount, currency);
    }
}
