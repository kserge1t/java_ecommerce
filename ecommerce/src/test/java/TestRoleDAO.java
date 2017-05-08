
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import sergey.dao.RoleDAO;
import sergey.entities.Role;

public class TestRoleDAO {

    @Before
    public void preTestCheck() {
	RoleDAO roleDao = new RoleDAO();
	if (roleDao.getRoleByValue("Test") == null) {
	    Role role = new Role();
	    role.setRole("Test");
	    roleDao.addRole(role);
	}
    }

    
    @Test
    public void testAddRole() {
	RoleDAO roleDao = new RoleDAO();
	roleDao.deleteRoleByValue("Test");
	Role role = new Role();
	role.setRole("Test");
	assertTrue(roleDao.addRole(role));
    }

    @Test
    public void testGetRoleById() {
	RoleDAO roleDao = new RoleDAO();
	Integer id = roleDao.getRoleIdByValue("Test").intValue();  // int method calls for long method so both get tested
	assertEquals("Test", roleDao.getRoleById(id).getRole());
    }

    @Test
    public void testGetRoleIdByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertEquals(Long.class, roleDao.getRoleIdByValue("Test").getClass());
    }

    @Test
    public void testGetRoleByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertEquals("Test", roleDao.getRoleByValue("Test").getRole());
    }

    @Test
    public void testGetAllRoles() {
	RoleDAO roleDao = new RoleDAO();
	List<Role> roles = roleDao.getAllRoles();
	assertTrue(roles.size()>0);
    }

    @Test
    public void testUpdateRole() {
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue("Test");
	role.setRole("Testing");
	assertTrue(roleDao.updateRole(role));
	assertTrue(roleDao.deleteRoleByValue("Testing"));
    }

    @Test
    public void testDeleteRole() {
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue("Test");
	assertTrue(roleDao.deleteRole(role));
    }

    @Test
    public void testDeleteRoleById() {
	RoleDAO roleDao = new RoleDAO();
	Integer id = roleDao.getRoleIdByValue("Test").intValue();  // int method calls for long method so both get tested
	assertTrue(roleDao.deleteRoleById(id));
    }

    @Test
    public void testDeleteRoleByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertTrue(roleDao.deleteRoleByValue("Test"));
    }
    
    
    @AfterClass
    public static void clean() {
	RoleDAO roleDao = new RoleDAO();
	if (roleDao.getRoleByValue("Test") != null) {
	    roleDao.deleteRoleByValue("Test");
	}
	
	// Info
	int nonTestMethods = 2; // Number of non @Test methods e.g.: @AfterClass, @BeforeClass, @After, @Before, etc.
	int combinedTests = 2; // Number of methods that are tested along with other methods (combined tests)
	System.out.println(RoleDAO.class.getDeclaredMethods().length+" declared methods in "+RoleDAO.class.getName());
	System.out.println((int)(TestRoleDAO.class.getDeclaredMethods().length-nonTestMethods+combinedTests)+" test methods in "+TestRoleDAO.class.getName());
    }

}
