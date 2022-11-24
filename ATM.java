import java.util.*;
class Main{
    static Scanner scan = new Scanner(System.in);
    static int atm_total=0;
    static String u_name[]= {"Sudharsan","Surendhran"};
    static int u_pass[]={1234,12345};
    static int balance[]={10000,20000};
    static String mini_statement[][] = new String[u_name.length][7];
    static HashMap<Integer,Integer>atm_amount = new HashMap<Integer,Integer>();
    static HashMap<Integer,Integer>user_deno = new HashMap<Integer,Integer>();
    public static void withdraw(int i){
        System.out.println("Enter the amount to be withdrawn : ");
        int w_amount=scan.nextInt();
        if(w_amount<=balance[i]){
            if(w_amount<=atm_total){
                int temp=w_amount;
                user_deno.put(2000,0);
                user_deno.put(500,0);
                user_deno.put(200,0);
                user_deno.put(100,0);
                while(temp!=0){
                    if(temp>=2000 && atm_amount.get(2000)!=0){
                        temp-=2000;
                        balance[i]-=2000;
                        atm_total-=2000;
                        user_deno.replace(2000,user_deno.get(2000)+1);
                        atm_amount.replace(2000,atm_amount.get(2000)-1);
                    }
                    else if(temp>=500 && atm_amount.get(500)!=0){
                        temp-=500;
                        balance[i]-=500;
                        atm_total-=500;
                        user_deno.replace(500,user_deno.get(500)+1);
                        atm_amount.replace(500,atm_amount.get(500)-1);
                    }
                    else if(temp>=200 && atm_amount.get(200)!=0){
                        temp-=200;
                        balance[i]-=200;
                        atm_total-=200;
                        user_deno.replace(200,user_deno.get(200)+1);
                        atm_amount.replace(200,atm_amount.get(200)-1);
                    }
                    else if(temp>=100 && atm_amount.get(100)!=0){
                        temp-=100;
                        balance[i]-=100;
                        atm_total-=100;
                        user_deno.replace(100,user_deno.get(100)+1);
                        atm_amount.replace(100,atm_amount.get(100)-1);
                    }
                    else{
                        atm_amount.replace(2000,atm_amount.get(2000)+user_deno.get(2000));
                        atm_amount.replace(500,atm_amount.get(500)+user_deno.get(500));
                        atm_amount.replace(200,atm_amount.get(200)+user_deno.get(200));
                        atm_amount.replace(100,atm_amount.get(100)+user_deno.get(100));
                        System.out.println("Insufficient balance in ATM \n Please try nearby ATM ");
                        break;
                        
                    }
                }
                for(int j=0;j<7;j++){
                    if(mini_statement[i][j]==null){
                        mini_statement[i][j]="Your withdrawn amount is "+String.valueOf(w_amount)+" Your balance is "+String.valueOf(balance[i]);
                        break;
                    }
                }
                if(mini_statement[i][6]!=null){
                    mini_statement[i][0]=mini_statement[i][1];
                    mini_statement[i][1]=mini_statement[i][2];
                    mini_statement[i][2]=mini_statement[i][3];
                    mini_statement[i][3]=mini_statement[i][4];
                    mini_statement[i][4]=mini_statement[i][5];
                    mini_statement[i][5]=mini_statement[i][6];
                    mini_statement[i][6]=null;
                }
                System.out.println("Please collect the amount :"+ w_amount);
            }
            else{
                System.out.println("Insufficient balance in ATM \n Please try nearby ATM ");
            }
        }
        else{
            System.out.println("Insufficient balance\nYou can only take :"+balance[i]);
        }
    }
    public static void pin_change(int i){
        while(true){
             System.out.println("Pin Change");
             System.out.println(); 
            System.out.println("Enter the new pin");
            int new_pin=scan.nextInt();
            if(new_pin==u_pass[i]){
                System.out.println(" Pin already exist ");
            }
            else{
                u_pass[i]=new_pin;
                System.out.println("password changed successfully");
                break;
            }
        }
    }
    public static void user_work(int i){
        while(true){
            System.out.println("Withdraw      ->1\nBalance       ->2\nchange_pin    ->3\nmini statement->4\nExit         ->5");
            int x = scan.nextInt();
            if(x==1){
                System.out.print("\033[H\033[2J");
                System.out.flush();      
                withdraw(i);
            }
            else if(x==2){
                System.out.print("\033[H\033[2J");
                System.out.flush();       
                System.out.println("Your balance is : "+balance[i]);
            }
            else if(x==3){
                System.out.print("\033[H\033[2J");
                System.out.flush();      
                pin_change(i);
                break;
            }
            else if(x==4){
                System.out.print("\033[H\033[2J");
                 System.out.flush();      
                for(int j=0;j<7;j++){
                    if(mini_statement[i][j]!=null)
                    System.out.println(mini_statement[i][j]);
                }
            }
            else{
                break;
            }
        }
    }
    public static void user(){
        int trail=3;
        while(trail!=0){
            scan.nextLine();
            System.out.println("Enter your user_name :");
            String user_name = scan.nextLine();
            System.out.println("Enter your password :");
            int pass = scan.nextInt();
            int flg=0;
            for(int i=0;i<u_name.length;i++){
            if(user_name.equals(u_name[i]) && pass==u_pass[i]){
                System.out.print("\033[H\033[2J");
                  System.out.flush();      
                System.out.println("Welcome "+user_name);
                user_work(i);
                flg=1;
                break;
            }
            }
            if(flg==0){
                trail-=1;
                if(trail==0){
                    System.out.println("You have no trails");
                }
                else{
                    System.out.println("You have only "+trail +" trails");
                }
            }
            break;
        }
    }
    public static void deposit(){
        while(true){
            System.out.println("Deposit");
            System.out.println();
            System.out.println("Enter no of 2000 to be deposited");
            int n=scan.nextInt();
            atm_total+=2000*n;
            atm_amount.put(2000,atm_amount.get(2000)+n);
        
            System.out.println("Enter no of 500 to be deposited");
            int m = scan.nextInt();
            atm_total+=500*m;
            atm_amount.put(500,atm_amount.get(500)+m);
        
            System.out.println("Enter no of 200 to be deposited");
            int o = scan.nextInt();
            atm_total+=200*o;
            atm_amount.put(200,atm_amount.get(200)+o);
        
            System.out.println("Enter no of 100 to be deposited");
            int p = scan.nextInt();
            atm_total+=100*p;
            atm_amount.put(100,atm_amount.get(100)+p);
            System.out.print("\033[H\033[2J");
            System.out.println("Amount added Successfully");
            System.out.println();
            break;
        }
    }
    public static void admin(){
        while(true){
            System.out.println("Deposit->1 \nBalance->2 \nExit   ->3");
            int n = scan.nextInt();
            if(n==1){
                deposit();
            }
            else if(n==2){
                System.out.print("\033[H\033[2J");
                System.out.flush();      
                for(Map.Entry i : atm_amount.entrySet()){
                    System.out.println(i.getKey()+":"+i.getValue());
                }
                System.out.println("Total : "+atm_total);
            }
            else if(n==3){
                break;
            }
            else{
                System.out.println("Enter no froom 1-3 to proceed");
            }
            
        }
    }
    public static void main(String[] args){
        atm_amount.put(2000,0);
        atm_amount.put(500,0);
        atm_amount.put(200,0);
        atm_amount.put(100,0);
        while(true){
            System.out.println("-----Welcome to canara bank-----");
            System.out.println("Admin ->1 \nUser  ->2 \nExit  ->3");
            int n = scan.nextInt();
            if(n==1){
                System.out.println("Enter your id : ");
                scan.nextLine();
                String user_name = scan.nextLine();
                System.out.println("Enter your password : ");
                int password = scan.nextInt();
                if(user_name.equals("admin") && password==4321){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();      
                    System.out.println("Welcome admin");
                    admin();
                }
                else{
                    System.out.print("\033[H\033[2J");
                     System.out.flush();      
                    System.out.println("The entered id or password is wrong");
                }
            }
            else if(n==2){
                user();
            }
            else if(n==3){
                System.out.print("\033[H\033[2J");
                System.out.flush();      
                System.out.println("Thank You.....Visit Again");
                break;
            }
            else{
                System.out.println("Please enter the number between 1 - 3");
            }
        }
    }
}
