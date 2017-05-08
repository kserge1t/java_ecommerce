

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sergey.dao.RoleDAO;
import sergey.dao.UserDAO;
import sergey.entities.Role;
import sergey.entities.User;
import sergey.util.PasswordHashing.CannotPerformOperationException;

public class TestUserDAO {
    
    @BeforeClass
    public static void setUp() throws CannotPerformOperationException {

	RoleDAO roleDao = new RoleDAO();
	UserDAO userDao = new UserDAO();
	User user = new User();
	
	user.setFirstName("Test");
	user.setLastName("Tset");
	user.setEmail("test@test.com");
	user.setBalance(100.00);
	user.setRole(roleDao.getRoleById(User.defaultRoleId));
	user.setHashedPassword("Secret");
	
	if (userDao.addUser(user)) {
	    System.out.println("setUp Success");
	} else {
	    System.out.println("setUp Failed");
	}
    }      
    
    @AfterClass
    public static void cleanUp() {
	UserDAO userDao = new UserDAO();
	if (userDao.deleteUserByEmail("test@test.com") || userDao.getUserByEmail("test@test.com") == null) {
	    System.out.println("cleanUp Success");
	} else {
	    System.out.println("cleanUp Failed");
	}
    }
    
    @Test
    public void testAddUser() throws CannotPerformOperationException {
	RoleDAO roleDao = new RoleDAO();
	UserDAO userDao = new UserDAO();
	User user = new User();
	
	user.setFirstName("Test");
	user.setLastName("Tset");
	user.setEmail("test@test.com");
	user.setBalance(100.00);
	user.setRole(roleDao.getRoleById(User.defaultRoleId));
	user.setHashedPassword("Secret");
	
	userDao.deleteUserByEmail(user.getEmail());
	assertEquals(true,userDao.addUser(user));
    }
    
    @Before
    public void preTest() throws CannotPerformOperationException {
	UserDAO userDao = new UserDAO();
	if (userDao.getUserByEmail("test@test.com") == null) {
	    TestUserDAO.setUp();
	}
    }
    
    @Test
    public void testSetRole() {
	RoleDAO roleDao = new RoleDAO();
	Role role = new Role();
	role.setRole("Test");
	assertEquals(true,roleDao.addRole(role));
	
    }
    
    @Test
    public void testGetRole() {
	RoleDAO roleDao = new RoleDAO();
	assertEquals(Role.class,roleDao.getRoleById(User.defaultRoleId).getClass());
    }
    
    @Test
    public void testGetUserById() {
	UserDAO userDao = new UserDAO();
	User user = userDao.getUserByEmail("test@test.com");
	assertEquals(User.class,userDao.getUserById(user.getId()).getClass());
    }
    
    @Test
    public void testGetUserByEmail() {
	UserDAO userDao = new UserDAO();
	assertEquals(User.class,userDao.getUserByEmail("test@test.com").getClass());
    }
    
    @Test
    public void testDeleteUserById() {
	UserDAO userDao = new UserDAO();
	User user = userDao.getUserByEmail("test@test.com");
	assertEquals(true,userDao.deleteUserById(user.getId()));
    }
    
    @Test
    public void testDeleteUserByEmail() {
	UserDAO userDao = new UserDAO();
	assertEquals(true,userDao.deleteUserByEmail("test@test.com"));
    }

}
