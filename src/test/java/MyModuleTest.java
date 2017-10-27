import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class MyModuleTest {
    MyModule myModule;
    final static Logger logger = Logger.getLogger(MyModule.class.getName());

    @Before
    public void setUp(){
        logger.info("SetUp");
        myModule = new MyModule("8:15","9:45",MyModule.Type.LESSON);
    }

    @Test
    public void Test1() {
        try {
            logger.info("Lesson at 9:00");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("9:00");
            String result = myModule.toTheEnd(candidate).toString();
            logger.info("Left minutes to the end: "+result);
            Assert.assertEquals("45",result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}