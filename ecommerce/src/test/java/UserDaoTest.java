

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import sergey.library_db.SqlQuery;

public class UserDaoTest {
    
    @Before
    public void setUp() throws Exception {
	SqlQuery.qryBool("DROP TABLE members");
	SqlQuery.qryBool("CREATE TABLE members("
		+ "id INT(5) PRIMARY KEY,"
		+ "first_name VARCHAR(30),"
		+ "last_name VARCHAR(30)"
		+ ")");
	SqlQuery.qryBool("INSERT INTO members VALUES("
		+ "'1',"
		+ "'John',"
		+ "'Smith'"
		+ ")");
    }
    
    @Test
    public void testQryLst() {
	assertEquals("[ID: 1 | FIRST_NAME: John | LAST_NAME: Smith]",SqlQuery.qryLst("SELECT * FROM members WHERE id=1").toString());
    }
    
    @Test
    public void testQryRows() {
	assertEquals(1,SqlQuery.qryRows("UPDATE members SET first_name='Johnny' WHERE id='1'"));
    }
    
    @Test
    public void testQryBool() {
	assertEquals(true,SqlQuery.qryBool("DELETE FROM members WHERE id='1'"));
    }

}
