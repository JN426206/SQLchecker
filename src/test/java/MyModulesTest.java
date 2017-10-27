import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class MyModulesTest {
    MyModules myModules;
    final static Logger logger = Logger.getLogger(MyModule.class.getName());

    @Before
    public void setUp(){
        logger.info("SetUp");
        myModules = new MyModules();
    }


    @Test
    public void TestGoodStartLesson() {
        try {
            logger.info("Lesson at 8:15");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("8:15");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(90,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoodEndLesson() {
        try {
            logger.info("Lesson at 16:44:59");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            Date candidate = parser.parse("16:44:59");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(1,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoodStartBreak() {
        try {
            logger.info("Break at 13:15");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("13:15");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(30,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoodEndBreak() {
        try {
            logger.info("Break at 16:59:59");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            Date candidate = parser.parse("16:59:59");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(1,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestLesson() {
        try {
            logger.info("Lesson at 9:00");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("9:00");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(45,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestBreak() {
        try {
            logger.info("Break at 13:25");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("13:25");
            String moduleName="";
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString()+moduleName);
            Assert.assertEquals(20,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestAfterTime() {
        try {
            logger.info("AfterTime 20:25");
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            Date candidate = parser.parse("20:25");
            Integer result = myModules.timeToTheEnd(candidate);
            logger.info(result.toString());
            Assert.assertEquals(-1,result.intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}