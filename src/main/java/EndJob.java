import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EndJob implements org.quartz.Job {
    public EndJob(){

    }
//    public static void main(String[] args) {
//        LocalTime time = LocalTime.parse("11:22");
//        System.out.println(isBetween(time, LocalTime.of(10, 0), LocalTime.of(18, 0)));
//    }
//
//    public static boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
//        return !candidate.isBefore(start) && !candidate.isAfter(end);  // Inclusive.
//    }

    private Boolean isBetween(Date candidate, Date start, Date end){
        try {
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            candidate = parser.parse(parser.format(candidate));
            start = parser.parse(parser.format(start));
            end = parser.parse(parser.format(end));

            return candidate.after(start) && candidate.before(end);

        }
        catch (ParseException a){
            return false;
        }
    }

    private Integer toMinutes(Date date){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String[] splited = parser.format(date).split(":");
        Integer hh = Integer.parseInt(splited[0]);
        Integer mm = Integer.parseInt(splited[1]);
        return mm+hh*60;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException{

//        String lekcja1 = "8:15-9:45";
//        String przerwa1 = "9:45-10:00";
//        String lekcja2 = "10:00-11:30";
//        String przerwa2 = "11:30-11:45";
//        String lekcja3 = "11:45-13:15";
//        String przerwa3 = "13:15-13:45";
//        String lekcja4 = "13:45-15:15";
//        String przerwa4 = "15:15-15:30";
//        String lekcja5 = "15:30-16:45";
//        String przerwa5 = "16:45-17:00";
//        String lekcja6 = "17:00-18:30";

        List<Date> lekcje = new ArrayList<Date>();
        Date dNow = new Date();
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

        try{
            Date lekcja1 = parser.parse("17:49");
            Date lekcja6 = parser.parse("17:50");
            Date nowTime = parser.parse(parser.format(dNow));

            System.out.println(toMinutes(dNow));
            System.out.println(toMinutes(lekcja6));
            System.out.println(toMinutes(lekcja6)-toMinutes(dNow));
            System.out.println(isBetween(dNow,lekcja1,lekcja6));
        } catch (ParseException a){}

    }
}
