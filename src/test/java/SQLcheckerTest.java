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
        logger.info(checker.checkQuery("select * from prac where id=1 order by id"));
    }

    @Test
    public void badTest() {
        logger.info("badTest qeury: \"from * select prac order by id where\"");
        logger.info(checker.checkQuery("from * select prac order by id where"));
    }

    @Test
    public void badTestRevertOrderWhere() {
        logger.info("badTestRevertOrderWhere qeury: \"select * from prac order by id where\"");
        logger.info(checker.checkQuery("select * from prac order by id where"));
    }

    @Test
    public void badTestRevertByOrder() {
        logger.info("badTestRevertByOrder qeury: \"select * from prac where id=1 by order id\"");
        logger.info(checker.checkQuery("select * from prac where id=1 by order id"));
    }
    //final static Logger logger = Logger.getLogger(SQLchecker.class);

}