package sergey.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import sergey.util.PasswordHashing;
import sergey.util.PasswordHashing.CannotPerformOperationException;
import sergey.util.PasswordHashing.InvalidHashException;

@Entity
public class User {
    
    public static final Long defaultRoleId = (long) 2; // default role id

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String hashedPassword;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Double balance;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="role")
    private Role role;

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getHashedPassword() {
	return hashedPassword;
    }

    public void setHashedPassword(String password) throws CannotPerformOperationException {
	hashedPassword = password; // Plain text for testing
	//hashedPassword = PasswordHashing.createHash("password"); // Need fix
	
    }
    
    public boolean validate (String password) throws CannotPerformOperationException, InvalidHashException {
	return (hashedPassword.equals(password)); // Compare plain text password for testing
	//return PasswordHashing.verifyPassword(password, getHashedPassword()); // Need fix
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
