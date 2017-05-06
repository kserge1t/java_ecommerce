package sergey.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import sergey.data.User;

public class UserDAO {
    
    // Save user
    public void addUser(User user) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.save(user);
	    session.getTransaction().commit();
	    session.flush();
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
    }
    
    // Update user
    public void updateUser(User user) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.saveOrUpdate(user);
	    session.getTransaction().commit();
	    session.flush();
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
    }

    // Get all the users
    public List<User> getAllUsers() {
	List<User> users = new ArrayList<User>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    users = session.createCriteria(User.class).list();
	    session.getTransaction().commit();
	    session.flush();
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return users;
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
	    Criteria filter = session.createCriteria(User.class).add(Restrictions.idEq(userId));
	    session.getTransaction().commit();
	    session.flush();
	    return (User) filter;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Delete user by id (integer)
    public boolean deleteUser (int userId) {
	if (this.deleteUser((long) userId)) return true;
	return false;
    }

    // Delete user by id (Long)
    public boolean deleteUser (Long userId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.load(User.class, new Long(userId));
	    session.delete(user);
	    session.getTransaction().commit();
	    session.flush();
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
