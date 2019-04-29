package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class RepairFund extends BaseMedia {

    public RepairFund() {
    }

    public RepairFund(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
