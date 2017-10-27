import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class SQLcheckerTest {
    SQLchecker checker;
    final static Logger logger = Logger.getLogger(SQLchecker.class.getName());

    @Before
    public void setUp(){
        logger.info("SetUp");
        checker = new SQLchecker();
    }

    @Test
    public void goodTest() {
        logger.info("goodTest qeury: \"select * from prac where id=1 order by id\"");
        String result = checker.checkQuery("select * from prac where id=1 order by id");
        logger.info(Integer.toString(result.length()));
        Assert.assertEquals(result.length(),0);
    }

    @Test
    public void badTest() {
        logger.info("badTest qeury: \"from * select prac order by id where\"");
        String result = checker.checkQuery("from * select prac order by id where");
        logger.info(result);
        Assert.assertEquals("Zła pozycja dla SELECT lub jej brak !\n" +
                "Zła pozycja dla FROM lub jej brak !\n" +
                "Zła pozycja dla WHERE !\n" +
                "Zła pozycja dla ORDER BY !\n",result);
    }

    @Test
    public void badTestRevertOrderWhere() {
        logger.info("badTestRevertOrderWhere qeury: \"select * from prac order by id where\"");
        String result = checker.checkQuery("select * from prac order by id where");
        logger.info(result);
        Assert.assertEquals("Zła pozycja dla WHERE !\n" +
                "Zła pozycja dla ORDER BY !\n",result);
    }

    @Test
    public void badTestRevertByOrder() {
        logger.info("badTestRevertByOrder qeury: \"select * from prac where id=1 by order id\"");
        String result = checker.checkQuery("select * from prac where id=1 by order id");
        logger.info(result);
        Assert.assertEquals("Zła pozycja dla ORDER BY !\n",result);
    }
    //final static Logger logger = Logger.getLogger(SQLchecker.class);

}