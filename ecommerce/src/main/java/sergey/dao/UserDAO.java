package sergey.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sergey.entities.Role;
import sergey.entities.User;

public class UserDAO {
    
    // Save user
    public boolean addUser(User user) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.save(user);
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
    
    // Update user
    public boolean updateUser(User user) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.saveOrUpdate(user);
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

    // Get all the users
    public List<User> getAllUsers() {
	List<User> users = new ArrayList<User>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    users = session.createCriteria(User.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return users;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get user by id (integer)
    public User getUserById (int userId) {
	return this.getUserById((long) userId);
    }

    // Get user by id (Long)
    public User getUserById(Long userId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    //Criteria filter = session.createCriteria(User.class).add(Restrictions.idEq(userId));
	    User user = (User) session.get(User.class, userId);
	    session.flush();
	    session.getTransaction().commit();
	    return user;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Get user by email
    public User getUserByEmail(String email) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    //Criteria filter = session.createCriteria(User.class).add(Restrictions.idEq(userId));
	    User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return user;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Delete user by email
    public boolean deleteUserByEmail(String email) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
	    session.delete(user);
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

    // Delete user by User
    public boolean deleteUser(User user) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.delete(user);
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
    
    // Delete user by id (integer)
    public boolean deleteUserById (int userId) {
	if (this.deleteUserById((long) userId)) return true;
	return false;
    }

    // Delete user by id (Long)
    public boolean deleteUserById (Long userId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.load(User.class, new Long(userId));
	    session.delete(user);
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
}
