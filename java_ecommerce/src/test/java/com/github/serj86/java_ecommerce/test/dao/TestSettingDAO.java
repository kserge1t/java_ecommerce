package com.github.serj86.java_ecommerce.test.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.entities.Setting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSettingDAO {
    public static String testSetting1 = "Test";
    public static String testValue = "Test Value";
    
    public static String testSetting2 = "Testing";
    
    @BeforeClass
    public static void setUp() {
	SettingDAO settingDao = new SettingDAO();
	// Delete existing test Setting, if any.
	if (settingDao.getSettingByName(testSetting1) != null) {
	    settingDao.deleteSettingByName(testSetting1);
	}
	if (settingDao.getSettingByName(testSetting2) != null) {
	    settingDao.deleteSettingByName(testSetting2);
	}
    }

    @Before
    public void preTestCheck() {
	SettingDAO settingDao = new SettingDAO();
	GenericDAO genericDAO = new GenericDAO();
	if (settingDao.getSettingByName(testSetting1) == null) {
	    Setting setting = new Setting();
	    setting.setSetting(testSetting1);
	    setting.setValue(testValue);
	    genericDAO.add(setting);
	}
    }

    
    @Test
    public void testAddSetting() {
	SettingDAO settingDao = new SettingDAO();
	settingDao.deleteSettingByName(testSetting1);
	Setting setting = new Setting();
	setting.setSetting(testSetting1);
	setting.setValue(testValue);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.add(setting));
    }

    @Test
    public void testGetSettingById() {
	SettingDAO settingDao = new SettingDAO();
	Integer id = settingDao.getSettingIdByName(testSetting1).intValue();  // int method calls for long method so both get tested
	assertEquals(testSetting1, settingDao.getSettingById(id).getSetting());
    }

    @Test
    public void testGetSettingValueByName() {
	SettingDAO settingDao = new SettingDAO();
	assertEquals(testValue, settingDao.getSettingValueByName(testSetting1));
    }

    @Test
    public void testGetSettingByValue() {
	SettingDAO settingDao = new SettingDAO();
	assertEquals(testSetting1, settingDao.getSettingByName(testSetting1).getSetting());
    }

    @Test
    public void testGetAllSettings() {
	SettingDAO settingDao = new SettingDAO();
	List<Setting> settings = settingDao.getAllSettings();
	assertTrue(settings.size()>0);
    }

    @Test
    public void testUpdateSetting() {
	GenericDAO genericDAO = new GenericDAO();
	SettingDAO settingDao = new SettingDAO();
	Setting setting = settingDao.getSettingByName(testSetting1);
	setting.setSetting(testSetting2);
	assertTrue(genericDAO.update(setting));
	assertTrue(settingDao.deleteSettingByName(testSetting2));
    }

    @Test
    public void testDeleteSetting() {
	SettingDAO settingDao = new SettingDAO();
	Setting setting = settingDao.getSettingByName(testSetting1);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.delete(setting));
    }

    @Test
    public void testDeleteSettingById() {
	SettingDAO settingDao = new SettingDAO();
	Integer id = settingDao.getSettingIdByName(testSetting1).intValue();  // int method calls for long method so both get tested
	assertTrue(settingDao.deleteSettingById(id));
    }

    @Test
    public void testDeleteSettingByValue() {
	SettingDAO settingDao = new SettingDAO();
	assertTrue(settingDao.deleteSettingByName(testSetting1));
    }
    
    
    @AfterClass
    public static void clean() {
	SettingDAO settingDao = new SettingDAO();
	if (settingDao.getSettingByName(testSetting1) != null) {
	    settingDao.deleteSettingByName(testSetting1);
	}
	if (settingDao.getSettingByName(testSetting2) != null) {
	    settingDao.deleteSettingByName(testSetting2);
	}
    }

}
