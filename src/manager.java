//gestiona las acciones del horario

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class manager {
    week schedule = new week("Schedule");
    week[] process = new week[168];
    int n = 0;
    
    manager(){
        week schedule = new week("Schedule");
        week[] process = new week[168];
        int n = 0;
    }
    //OBJECT METHOD
    public int newProcess(){
        
        Scanner input = new Scanner(System.in);
        System.out.print("type process name ");
        String processName = input.nextLine();
        process[this.n] = new week(processName);
        this.n++;
        
        return this.n;
    }
    
    //OBJECT METHOD
    public void newProcess(String processName, int processNumber){
        
        Scanner input = new Scanner(System.in);
        process[processNumber] = new week(processName);
        this.n++;
        
    }
    
    //OBJECT METHOD
    public void showProcesses(){
        
        if (this.n == 0){
            System.out.println("[no process]");
        }
        else
        if (this.n > 0){
            
            System.out.println("[process priority]");
            
            for(int i = 0; i < this.n; i++){
                System.out.println(i + "]" + process[i].getName());
            }
        }
        
    }
    
    //OBJECT METHOD
    public void swapProcess(){
        
        if (this.n == 0){
            System.out.println("[no process]");
        }
        else if(this.n < 2){
            System.out.println("[add more process]");
        }
        else{
            Scanner input = new Scanner(System.in);
            int processN = this.n - 1;
            
            System.out.println("[0 - " + processN + "]");
            System.out.print("choose one process ");
            String firstProcess = input.nextLine();
            int fp = Integer.valueOf(firstProcess);
            
            System.out.println("[0 - " + processN + "]");
            System.out.print("choose other process ");
            String secondProcess = input.nextLine();
            int sp = Integer.valueOf( secondProcess );
           
            String fFile = searchFileBy_n(fp);
            String sFile = searchFileBy_n(sp);
            String fShortName, sShortName,  fProcessName, sProcessName;
        
            fShortName = cutLength(fFile, 4);
            sShortName = cutLength(sFile, 4);
            
            fProcessName = onlyAlpha(fShortName);
            sProcessName = onlyAlpha(sShortName);
            
            renameFile(fp + fProcessName, sp + fProcessName);
            renameFile(sp + sProcessName, fp + sProcessName);
            
        }
    }
    
    //OBJECT METHOD:
    public void loadAttributes(String[] currentFiles){
        
        int nCurrentFiles = countCurrentFiles();
        String[] paragraph;
        

        int processNumber;
        
        String activityName, date;
        int day, hour = 0;
        
        for (int i = 0; i < nCurrentFiles; i++) {
            
            processNumber = Integer.valueOf(onlyInt(currentFiles[i]));
            
            paragraph = readFile(currentFiles[i]);
            for (String line : paragraph) {
                
                activityName = onlyAlpha(line);
                
                date = onlyInt(line);
                day = Integer.valueOf(line.substring(0, 1));
                if(date.length() == 2){
                    hour = Integer.valueOf(date.substring(1, 2));
                } else if(date.length() == 3){
                    hour = Integer.valueOf(date.substring(1, 3));
                } else {
                    hour = 0;
                }
                
                process[processNumber].setActivity(activityName, day, hour);
            }
          
            
        }
        
 
        
    }
    
    //OBJECT METHOD
    public void loadProcess(String[] currentFiles){
        
        int nCurrentFiles = countCurrentFiles();
        
        String longName, shortName, processName;
        int processNumber;
        
        for (int i = 0; i < nCurrentFiles; i++) {
            longName = currentFiles[i];
            shortName = cutLength(longName, 4);
            processName = onlyAlpha(shortName);
            processNumber = Integer.valueOf(onlyInt(shortName));
            newProcess(processName, processNumber);
        }
        
        

    }
    
    //OBJECT METHOD
    public void schedule(){
        
        
         if (this.n == 0){
            System.out.println("[no process]");
        }
        else
        if (this.n > 0){
        
            for(int i = (this.n-1); i >= 0; i--){
                schedule.addActivities(process[i].getActivities());
            }
        }
         
         schedule.show();
    }
    
    //FILE METHOD
    public void createFile(String name){
        
        try {
            File myFile = new File(name + ".txt");
            if (myFile.createNewFile()) {
                System.out.print("[file created " + myFile.getName() + "]");
            } else {
                System.out.println("file already exists]");
            }
        } catch (IOException e) {
            System.out.println("[an error occurred]");
            e.printStackTrace();
        }
    
    }
    
    //FILE METHOD
    public void writeFile(String name, String text){
        
        try {
            FileWriter myWriter = new FileWriter(name + ".txt");
            myWriter.write(text);
            myWriter.close();
            System.out.println("[successfully wrote to the file]");
          } catch (IOException e) {
            System.out.println("[an error occurred]");
            e.printStackTrace();
          }
        
    }
    
    //FILE METHOD
    public void renameFile(String oldName, String newName){
        
        oldName += ".txt";
        newName += ".txt";
        
        System.out.print(oldName + " to " + newName);
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        
        if (oldFile.renameTo(newFile)) {
            System.out.println("[file renamed successfully]");
        } else {
            System.out.println("[failed to rename file]");
        }

    }
    
    //FILE METHOD
    public int countFileLines(String name){
        
        int c = 0;
        
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                myReader.nextLine();
                c++;
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("[an error occurred]");
            e.printStackTrace();
          }
        
        return c;
        
    }
    
    //FILE METHOD
    public String[] readFile(String name){
        
        int fileLines = countFileLines(name);
        String line;
        String[] paragraph = new String[fileLines];
        
        int l = 0;
        
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              line = myReader.nextLine();
              paragraph[l] = line;
              l++;
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("[an error occurred]");
            e.printStackTrace();
          }
        
        return paragraph;
    }
    
    //FILE METHOD:
    public void saveProcess(int n){
        
        if (this.n == 0){
            System.out.print("[nothing to save]");
        }
        else if (this.n > 0){
            
            System.out.print("\n[");
            String name  = n + process[n].getName();
            createFile(name);
            System.out.print(" saved]\n");
        }
        
    }
    
    //FILE METHOD
    public void saveChanges(int n){
        //save process changes    
        System.out.print("\n[");

        String name  = n + process[n].getName();
        System.out.print(name);
        writeFile(name,process[n].getLinearActivities());
        
        System.out.print(" saved]");        
    }
    
    //FILE METHOD
    public int countCurrentFiles(){
        
        int c = 0;
        File currentDirectory = new File(".");
        File[] files = currentDirectory.listFiles();
        
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                c++;
            }
        }
        
        return c;
        
    }
    
    //FILE METHOD
    public String[] getCurrentFiles(){
        //get the name of the current files
        
        String name;
        int nCurrentFiles = countCurrentFiles();
        String[] currentFiles = new String[nCurrentFiles];
                
        File currentDirectory = new File(".");
        File[] files = currentDirectory.listFiles();
        int f = 0;
        
        for (File file : files) {
            
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                name = file.getName();
                currentFiles[f] = name;
                f++;
            }
            
            
        }
        
        return currentFiles;
        
    }
    
    //STRING METHOD
    public String searchFileBy_n(int f){
        
        String[] nameFiles = getCurrentFiles();
        String processName = "";
        int processNumber;
        
        for (String nameFile : nameFiles) {

            processNumber = Integer.valueOf(onlyInt(nameFile));
            if(processNumber == f){
                processName = nameFile;
            }
        
        }
        
        return processName;
    }
    
    //STRING METHOD
    public String onlyAlpha(String name){
        
        StringBuilder alphabeticPart = new StringBuilder();

        for (char c : name.toCharArray()) {
            if (Character.isLetter(c)) {
                alphabeticPart.append(c);
            } 
        }
        
        return alphabeticPart.toString();
        
    }
    
    //STRING METHOD
    public String onlyInt(String name){
        
        StringBuilder numericPart = new StringBuilder();

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                numericPart.append(c);
            }
        }
 
        return numericPart.toString();
        
    }
    
    //STRING METHOD
    public String cutLength(String originalString, int cut){
        
         if (originalString.length() >= cut) {
            originalString = originalString.substring(0, originalString.length() - cut);
        } else {
            System.out.println("[string is too short to delete]");
        }
        return originalString;
    
    }
    
    //MENU
    public void processMenu(int n){
        
        Scanner input = new Scanner(System.in);
        String op, activityName, firstDay, lastDay, firstHour, lastHour;
        char d;
        
        if (this.n == 0){
            
            System.out.println("[no process]");
        }
        else if (n > this.n){
            System.out.println("[process " + n + " doesn´t exist]");
        } else if (n <= this.n){
        
            process[n].show();

            do{

                System.out.println("\n+] new activity");
                System.out.println("*] new range of activities");
                System.out.println("-] errase activity");
                System.out.println("/] errase range of activities ");
                System.out.println("<] back\n");


                op = input.nextLine();
                d = op.charAt(0);

                switch(d){
                    case '+': 
                            System.out.print("\nactivity name ");
                            activityName = input.nextLine();

                            System.out.print("\nset day [0-6 day format]");
                            System.out.println("[0 sunday] [1 monday] [2 tuesday] [3 wednesday] [4 thursday] [5 friday] [6 saturday]");
                            firstDay = input.nextLine();

                            System.out.print("\nset hour [0-23 hour format] ");
                            firstHour = input.nextLine();

                            process[n].setActivity(activityName, Integer.valueOf(firstDay), Integer.valueOf(firstHour));
                            process[n].show();
                            
                            saveChanges(n);
                        break;

                    case '*':
                            System.out.print("\nactivity name ");
                            activityName = input.nextLine();

                            System.out.println("\nset day range [0-6 day format]");
                            System.out.println("[0 sunday] [1 monday] [2 tuesday] [3 wednesday] [4 thursday] [5 friday] [6 saturday]");
                            System.out.print("first day ");
                            firstDay = input.nextLine();
                            System.out.print("last day ");
                            lastDay = input.nextLine();

                            System.out.println("\nset hour range [0-23 hour format]");
                            System.out.print("first hour ");
                            firstHour = input.nextLine();
                            System.out.print("last hour ");
                            lastHour = input.nextLine();

                            process[n].setActivityByRange(Integer.valueOf(firstDay), Integer.valueOf(lastDay), Integer.valueOf(firstHour), Integer.valueOf(lastHour), activityName);
                            process[n].show();
                            
                            saveChanges(n);
                        break;

                    case '-':
                            System.out.print("\nset day [0-6 day format]");
                            System.out.println("[0 sunday] [1 monday] [2 tuesday] [3 wednesday] [4 thursday] [5 friday] [6 saturday]");
                            firstDay = input.nextLine();

                            System.out.print("\nset hour [0-23 hour format] ");
                            firstHour = input.nextLine();

                            process[n].setActivity(null, Integer.valueOf(firstDay), Integer.valueOf(firstHour));
                            process[n].show();
                            
                            saveChanges(n);
                        break;

                    case '/':
                            System.out.println("\nset day range [0-6 day format]");
                            System.out.println("[0 sunday] [1 monday] [2 tuesday] [3 wednesday] [4 thursday] [5 friday] [6 saturday]");
                            System.out.print("first day ");
                            firstDay = input.nextLine();
                            System.out.print("last day ");
                            lastDay = input.nextLine();

                            System.out.println("\nset hour range [0-23 hour format]");
                            System.out.print("first hour ");
                            firstHour = input.nextLine();
                            System.out.print("last hour ");
                            lastHour = input.nextLine();

                            process[n].setActivityByRange(Integer.valueOf(firstDay), Integer.valueOf(lastDay), Integer.valueOf(firstHour), Integer.valueOf(lastHour), null);
                            process[n].show();
                            
                            saveChanges(n);
                        break;
                    case '<':
                        break;
                    default:
                        System.out.println("[try again]");
                }

            }while(d != '<');
        
        }
    }
    
 
}
