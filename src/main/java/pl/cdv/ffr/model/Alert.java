package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DynamicUpdate
public class Alert extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    private String description;
    private String createdDate;
    private boolean visible = true;
    private String property;
    private String tenat;

    public Alert() {
    }

    public Alert(AlertType alertType, String description, String createdDate, boolean visible, String property, String tenat) {
        this.alertType = alertType;
        this.description = description;
        this.createdDate = createdDate;
        this.visible = visible;
        this.property = property;
        this.tenat = tenat;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTenat() {
        return tenat;
    }

    public void setTenat(String tenat) {
        this.tenat = tenat;
    }
}
