package com.github.serj86.java_ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "settings")
public class Setting {

    @Id
    @GeneratedValue
    @Column(name="setting_id", unique = true, nullable = false, length = 20)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String setting;

    @Column(unique = false, nullable = false, length = 255)
    private String value;
    
    public Long getId() {
        return id;
    }

    public void setId(Long setting_id) {
        this.id = setting_id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
