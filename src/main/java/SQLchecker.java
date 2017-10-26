import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SQLchecker {

    public static List<MyQuery> myQueryList = new ArrayList<MyQuery>();

    private static  void updateaddToMyQueryList(MyQuery query){
            Boolean was=false;
            for(MyQuery queryBuf : myQueryList){
                if(query.getNumer()==queryBuf.getNumer()){
                    queryBuf.setZapytanie(query.getZapytanie());
                    was=true;
                }
            }
            if(!was) myQueryList.add(query);
    }

    public static String checkQuery(String query){
        query = query.toUpperCase();
        String[] keys = query.split(" ");
        Integer selectIndex=-1,fromIndex=-1,whereIndex=-1,orderIndex=-1,byIndex=-1;
//        selectIndex = query.indexOf("SELECT");
//        fromIndex = query.indexOf("FROM");
//        whereIndex = query.indexOf("WHERE");
//        orderByIndex = query.indexOf("ORDER BY");
//        if(selectIndex!=-1 && ((fromIndex!=-1 && selectIndex>fromIndex) || selectIndex<whereIndex && selectIndex<orderByIndex))
//        System.out.println(selectIndex);
//        System.out.println(fromIndex);
//        System.out.println(whereIndex);
//        System.out.println(orderByIndex);
        Integer index=0;
        for (String key : keys) {
            if(key.equals("SELECT")){ selectIndex=index; ++index;}
            if(key.equals("FROM")){ fromIndex=index; ++index;}
            if(key.equals("WHERE")){ whereIndex=index; ++index;}
            if(key.equals("ORDER")){ orderIndex=index; ++index;}
            if(key.equals("BY")){ byIndex=index; ++index;}
        }
        String error="";
        if(selectIndex==-1 || (selectIndex!=-1 && selectIndex!=0)) error+="Zła pozycja dla SELECT lub jej brak !\n";
        if(fromIndex==-1 || (fromIndex!=-1 && fromIndex!=1)) error+="Zła pozycja dla FROM lub jej brak !\n";
        if(whereIndex!=-1 && whereIndex!=2) error+="Zła pozycja dla WHERE !\n";
        if(orderIndex!=-1 && ((orderIndex!=2 && byIndex!=3 && whereIndex==-1) || (orderIndex!=3  && byIndex!=4 && whereIndex!=-1) )) error+="Zła pozycja dla ORDER BY !\n";

        return error;
    }

    public static void main(String[] args){

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //scheduler.start();
            JobDetail endJob = newJob(EndJob.class)
                    .withIdentity("endJob")
                    .build();

            JobDetail saveJob = newJob(SaveJob.class)
                    .withIdentity("saveJob")
                    .build();

            CronTrigger saveJobTrigger = newTrigger()
                    .withIdentity("saveJobTrigger","group1")
                    .withSchedule(cronSchedule("0/30 * * * * ?"))
                    .build();

            CronTrigger endJobTrigger = newTrigger()
                    .withIdentity("endJobTrigger","group1")
                    .withSchedule(cronSchedule("0 0/1 8-18 ? * MON-FRI"))
                    .build();

            scheduler.scheduleJob(saveJob,saveJobTrigger);
            scheduler.scheduleJob(endJob,endJobTrigger);

            scheduler.start();


            Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            System.out.println("Aby zakończyć wpisz !q");
            String wpisane="";
            while(wpisane!="!q") {
                Integer nrZapytania;
                String zapytanie;

                System.out.println("Wpisz numer zapytania");
                wpisane = odczyt.nextLine();
                if (wpisane.equals("!q")) break;
                nrZapytania = Integer.parseInt(wpisane);

                System.out.println("Wpisz treść zapytania " + nrZapytania.toString());
                wpisane = odczyt.nextLine();
                if (wpisane.equals("!q")) break;
                zapytanie = wpisane;

                String wynikQuery = checkQuery(zapytanie);
                if (wynikQuery.length() > 0) {
                    System.out.println(checkQuery(zapytanie));
                    System.out.println("Nie dodano zapytania !");
                }
                else {
                    updateaddToMyQueryList(new MyQuery(nrZapytania, zapytanie));
                    System.out.println("Pomyśłnie dodano zapytanie nr " + nrZapytania.toString());
                }
            }

            scheduler.triggerJob(saveJob.getKey());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //            for(MyQuery query : myQueryList){
//                System.out.println(query.toString());
//            }

            scheduler.shutdown();

        } catch (SchedulerException se){
            se.printStackTrace();
        }

    }
}
