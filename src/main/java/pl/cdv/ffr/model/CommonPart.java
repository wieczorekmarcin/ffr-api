package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class CommonPart extends BaseMedia {

    public CommonPart() {
    }

    public CommonPart(String value, String rate, String unit) {
        super(value, rate, unit);
    }

}
