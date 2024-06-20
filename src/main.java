
import java.util.Scanner;


//

public class main {

    //
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        manager whoami = new manager();
        
        whoami.loadProcess( whoami.getCurrentFiles() );
        whoami.loadAttributes( whoami.getCurrentFiles() );
        
        
        Scanner input = new Scanner(System.in);
        String op;
        char d;
        //menu
        do{
            
            whoami.schedule();
            
            System.out.println("[choose a process or some action below]\n");
            whoami.showProcesses();
             
            System.out.println("\n+] new process");
            System.out.println("%] swap proccess");
            System.out.println(".] exit\n");
            
            

            op = input.nextLine();
            d = op.charAt(0);
            
            if(d > 47 && d < 58){
                whoami.processMenu( Integer.valueOf( op ) );
                d = 'r';
            }
            
            switch(d){
                case '+': 
                        whoami.saveProcess( whoami.newProcess() - 1 );
                    break;
                case '%':
                        whoami.swapProcess();
                        whoami = new manager();
                        whoami.loadProcess( whoami.getCurrentFiles() );
                        whoami.loadAttributes( whoami.getCurrentFiles() );
                    break;
                case 'r':
                    break;
                case '.':
                    break;
                default:
                    System.out.println("try again");
            }
            
        }while(d != '.');
        
    }
    
}

