package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.Setting;

public class SettingDAO {
    
    // Get all the settings
    public List<Setting> getAllSettings() {
	List<Setting> settings = new ArrayList<Setting>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    settings = session.createCriteria(Setting.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return settings;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Get setting by id (Long)
    public Setting getSettingById(Long settingId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.get(Setting.class, settingId);
	    session.flush();
	    session.getTransaction().commit();
	    return setting;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get setting by id (integer)
    public Setting getSettingById (Integer settingId) {
	return this.getSettingById(settingId.longValue());
    }
    
    // Get setting by name
    public Setting getSettingByName(String name) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.createCriteria(Setting.class).add(Restrictions.eq("setting", name)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return setting;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get setting value by name
    public String getSettingValueByName(String name) {
	return this.getSettingByName(name).getValue();
//	Setting setting = this.getSettingByName(name);
//	if (setting != null) {
//	    return setting.getValue();
//	}
//	return null;
    }
    
    // Get setting value by setting_id
    public String getSettingValueById(Long settingId) {
	return this.getSettingById(settingId).getValue();
//	Setting setting = this.getSettingById(settingId);
//	if (setting != null) {
//	    return setting.getValue();
//	}
//	return null;
    }
    
    // Get setting value by setting_id (Integer)
    public String getSettingValueById(Integer settingId) {
	return getSettingValueById(settingId.longValue());
    }
    
    // Get setting ID by name
    public Long getSettingIdByName(String name) {
	return getSettingByName(name).getSetting_id();
    }

    // Update setting value by name
    public boolean updateSettingByName(String name, String newValue) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.createCriteria(Setting.class).add(Restrictions.eq("setting", name)).uniqueResult();
	    setting.setValue(newValue);
	    session.update(setting);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }

    // Update setting by id (Long)
    public boolean updateSettingById (Long settingId, String newValue) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.load(Setting.class, settingId);
	    setting.setValue(newValue);
	    session.update(setting);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }
    
    // Update setting by id (Integer)
    public boolean updateSettingById (Integer settingId, String newValue) {
	return updateSettingById(settingId.longValue(), newValue);
    }

    // Delete setting by name
    public boolean deleteSettingByName(String name) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.createCriteria(Setting.class).add(Restrictions.eq("setting", name)).uniqueResult();
	    session.delete(setting);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }

    // Delete setting by id (Long)
    public boolean deleteSettingById (Long settingId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Setting setting = (Setting) session.load(Setting.class, settingId);
	    session.delete(setting);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }
    
    // Delete setting by id (Integer)
    public boolean deleteSettingById (Integer settingId) {
	return deleteSettingById(settingId.longValue());
    }
    
}
