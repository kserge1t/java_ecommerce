package sergey.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sergey.entities.Role;

public class RoleDAO {
    
    // Save role
    public boolean addRole(Role role) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.save(role);
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
    
    // Update role
    public boolean updateRole(Role role) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.saveOrUpdate(role);
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

    // Get all the roles
    public List<Role> getAllRoles() {
	List<Role> roles = new ArrayList<Role>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    roles = session.createCriteria(Role.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return roles;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get role by id (integer)
    public Role getRoleById (int roleId) {
	return this.getRoleById((long) roleId);
    }

    // Get role by id (Long)
    public Role getRoleById(Long roleId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Role role = (Role) session.get(Role.class, roleId);
	    session.flush();
	    session.getTransaction().commit();
	    return role;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get role by value
    public Role getRoleByValue(String value) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Role role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("role", value)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return role;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get role ID by value
    public Long getRoleIdByValue(String value) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Role role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("role", value)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return role.getId();
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Delete role by id (integer)
    public boolean deleteRoleById (int roleId) {
	if (this.deleteRoleById((long) roleId)) return true;
	return false;
    }

    // Delete role by id (Long)
    public boolean deleteRoleById (Long roleId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Role role = (Role) session.load(Role.class, roleId);
	    session.delete(role);
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

    // Delete role by value
    public boolean deleteRoleByValue(String value) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Role role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("role", value)).uniqueResult();
	    session.delete(role);
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

    // Delete role by Role
    public boolean deleteRole(Role role) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    session.delete(role);
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
