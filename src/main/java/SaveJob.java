import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;

public class SaveJob implements org.quartz.Job {
    public SaveJob(){

    }

    public void execute(JobExecutionContext context) throws JobExecutionException{
        try {
            PrintWriter zapis = new PrintWriter("odp.txt");
            Collections.sort(SQLchecker.myQueryList);
            for(MyQuery query:SQLchecker.myQueryList){
                zapis.println(query.toString());
            }
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //System.err.println(SQLchecker.myQueryList.size());
    }
}
