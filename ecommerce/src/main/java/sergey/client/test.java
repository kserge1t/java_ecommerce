package sergey.client;

import sergey.util.PasswordHashing;
import sergey.util.PasswordHashing.CannotPerformOperationException;
import sergey.util.PasswordHashing.InvalidHashException;

public class test {

    public static void main(String[] args) {

	PasswordHashing hash = new PasswordHashing();
	String hashedPassword = null;
	boolean verified = false;
	try {
	    hashedPassword = hash.createHash("password");
	} catch (CannotPerformOperationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	try {
	    verified = hash.verifyPassword("passwordd", hashedPassword);
	} catch (CannotPerformOperationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidHashException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	System.out.println(hashedPassword);
	System.out.println(verified);

    }

}
