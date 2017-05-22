package com.github.serj86.java_ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "settings")
public class Setting {

    @Id
    @GeneratedValue
    private Long setting_id;

    @Column(unique = true)
    private String setting;

    private String value;
    
    public Long getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(Long setting_id) {
        this.setting_id = setting_id;
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
