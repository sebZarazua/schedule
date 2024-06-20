
public class week {
    
    String name;
    String[][] activities;
    boolean isEmpty;

    public week(String name) {
        this.name = name;
        this.activities = new String[7][24];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity(int day, int hour){
        return activities[day][hour];
    } 
    
    public void setActivity(String activity, int day, int hour){
        this.activities[day][hour] = activity;
    }
    
    public String[][] getActivities() {
        return activities;
    }
    
    public String getLinearActivities() {
        
        String set = "";
        String line = "";
        
        for(int i = 0; i < 7; i++){
            
            for(int j= 0; j < 24; j++){
                
                if(this.activities[i][j]!= null){
                    line = i + "" + j + "\t"+ this.activities[i][j] + "\n";
                    set += line;
                }
                
            }
            
        }
        
        return set;
        
    }

    public void setActivities(String[][] activities) {
        this.activities = activities;
    }
    
    public void setActivityByRange(int firstDay, int lastDay, int firstHour, int lastHour, String activity){
        
        for(int i = firstDay; i <= lastDay; i++){
            
            for(int j= firstHour; j <= lastHour; j++){
            
                this.activities[i][j] = activity;
                
            }
            
        }
        
    }
    
    public int countActivities(){
        int activities = 0;
        
        for(int i = 0; i < 7; i++){
            
            for(int j= 0; j < 24; j++){
                
                if(this.activities[i][j] != null)
                    activities++; 
                
            }
            
        }
        
        if(activities==0)
            isEmpty = true;
        
        return activities;
    }
    
    public void addActivities(String[][] activities) {
                
        for(int i = 0; i < 7; i++){
            
            for(int j= 0; j < 24; j++){
            
                
                if(activities[i][j] != null){
                    this.activities[i][j] = activities[i][j];
                }
                
            }            
        }
        
        
    }
    
    public void show(){
        
        System.out.println("\n" + this.name + "\n");
        System.out.println("[0 sunday] [1 monday] [2 tuesday] [3 wednesday] [4 thursday] [5 friday] [6 saturday]");
        System.out.println("[dd]\t[hh]\t[activity]");
        System.out.println("[0-6]\t[0-23]");
        for(int i = 0; i < 7; i++){
            
            for(int j= 0; j < 24; j++){
                
                if(this.activities[i][j]!= null)
                    System.out.print("[" + i + ":" + j + " - " + this.activities[i][j] + "] ");
                
            }
            
            System.out.println("");
            
        }
        
        System.out.println("");
        
    }
    
    /*
    public void showSubstitutedActivities(int firstDay, int lastDay, int firstHour, int lastHour) {//busyActivities suggestion
        
        System.out.println("substituted activities\n"); 
        
        for(int i = firstDay; i <= lastDay; i++){
            
            for(int j= firstHour; j <= lastHour; j++){
            
                
                if(this.activities[i][j] != null){
                    System.out.print(i + "," + j + this.activities[i][j] + " ");
                }
                
            }
            
            System.out.println("");
            
        }
        
        System.out.println("");
        
    }
*/
    

    
    
}
