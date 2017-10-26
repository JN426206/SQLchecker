import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyModules {
    public List<MyModule> getMyModuleList() {
        return myModuleList;
    }

    public void setMyModuleList(List<MyModule> myModuleList) {
        this.myModuleList = myModuleList;
    }

    private List<MyModule> myModuleList;
    public MyModules(){
        this.myModuleList = new ArrayList<MyModule>();
        this.myModuleList.add(new MyModule("8:15","9:45",MyModule.Type.LESSON));
        this.myModuleList.add(new MyModule("9:45","10:00",MyModule.Type.BREAK));
        this.myModuleList.add(new MyModule("10:00","11:30",MyModule.Type.LESSON));
        this.myModuleList.add(new MyModule("11:30","11:45",MyModule.Type.BREAK));
        this.myModuleList.add(new MyModule("11:45","13:15",MyModule.Type.LESSON));
        this.myModuleList.add(new MyModule("13:15","13:45",MyModule.Type.BREAK));
        this.myModuleList.add(new MyModule("13:45","15:15",MyModule.Type.LESSON));
        this.myModuleList.add(new MyModule("15:15","15:30",MyModule.Type.BREAK));
        this.myModuleList.add(new MyModule("15:30","16:45",MyModule.Type.LESSON));
        this.myModuleList.add(new MyModule("16:45","17:00",MyModule.Type.BREAK));
        this.myModuleList.add(new MyModule("17:00","18:30",MyModule.Type.LESSON));
        //this.myModuleList.add(new MyModule("23:00","23:30",MyModule.Type.BREAK));
    }
    public MyModules(List<MyModule> myModuleList){
        this.myModuleList = myModuleList;
    }

    public String timeToTheEndString(Date dNow){
        String left="";
        for(MyModule myModule:this.myModuleList){
            Integer minutesLeft=myModule.toTheEnd(dNow);
            if(minutesLeft!=-1){
                String moduleName;
//                    if(myModule.getType()==MyModule.Type.LESSON) moduleName="Lekcji";
//                    else moduleName="Przerwy";
                moduleName = (myModule.getType()==MyModule.Type.LESSON) ? "lekcji" : "przerwy";
                left="Do końca "+ moduleName+" pozostało "+minutesLeft.toString()+" minut";
            }
        }
        return left;
    }
}
