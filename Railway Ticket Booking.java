import java.util.*;
class Main{
    
    public static Scanner scan = new Scanner(System.in);
    public static HashMap<String,Integer>users=new HashMap<>();
    
        // ****************** CHANGE PASSWORD *******************
    public static void change_pin(String username){
        System.out.println("Enter the new pin : ");
        int new_pin = scan.nextInt();
        if(new_pin!=users.get(username)){
            //users.get(username)=new_pin;
            System.out.println("Pin changed successfully...");
        }
        else{
            System.out.println("You have entered the same pin\n");
            change_pin(username);
        }
    }
    
    // ******************* LoginPage *************************
        public static void LoginPage(){
        scan.nextLine();
        while(true){
            System.out.println("Enter the user_name");
            String username = scan.nextLine();
            System.out.println("Enter the password");
            int pass = scan.nextInt();
            if(users.containsKey(username)){
                if(users.get(username).equals(pass)){
                    System.out.print("\033[H\033[2J");
                    System.out.println("Welcome "+username);
                    while(true){
                        User u = new User();
                        System.out.println("Book Ticket        - 1 \nCancel Ticket      - 2 \npin change         - 3 \nAvailable Seats    - 4 \nBooked seat        - 5\nExit               - 6" );
                        System.out.println("Please enter your choice : ");
                        int choi = scan.nextInt();
                        if(choi==1){
                            scan.nextLine();
                            System.out.println("Enter your name:");
                            String name = scan.nextLine();
                            System.out.println("Enter your age:");
                            int age = scan.nextInt();   
                            System.out.println("Enter your BerthPrefernce:");
                            scan.nextLine();
                            String berthPrefernce = scan.nextLine();                            
                            Passenger p =new Passenger(name,age,berthPrefernce);
                            u.bookPassenger(p);
                        }
                        else if(choi==2){
                            System.out.println("Enter id to cancel");
                            int Id=scan.nextInt();
                            if(u.PassengerData.containsKey(Id)){
                                u.cancelTicket(Id);
                            }
                            else{
                                System.out.println("No ID exists");
                            }
                        }
                        else if(choi==3){
                            change_pin(username);
                        }
                        else if(choi==4){
                            u.availableTickets();
                        }
                        else if(choi==5){
                            u.bookedTickets();
                        }
                        else if(choi==6){
                            break;
                        }
                        else{
                            System.out.println("Entered choice is not availavble\nPlease enter choice between 1 to 6");
                        }
                    }
                }
                else{
                    System.out.println("Entered password is wrong");
                }
            }
            else{
                System.out.println("user_name dosen't exist \nPlease register");
                break;
            }
        }
    }
    
    // ******************* CreateAccount *********************
    public static void CreateAccount(){
        scan.nextLine();
        while(true){
            System.out.println("Please enter Username");
            String new_user = scan.nextLine();
            if(users.containsKey(new_user)){
                System.out.println("user name already exists");
            }
            else{
                System.out.println("PLease Enter the password");
                int password = scan.nextInt();
                System.out.println("Re-Enter the password");
                int re_password =scan.nextInt();
                if(password==re_password){
                    users.put(new_user,password);
                    System.out.print("\033[H\033[2J");
                    System.out.println("Account created successfully....");
                    break;
                }
                else{
                   System.out.println("Password mismatch");
                }
            }
        }
    }
    
    
    
    //*********************************** Main Function *************************************
    
    public static void main(String[] args){
        System.out.println("********Railway Booking**********");
        while(true){
            System.out.println("Create Account  - 1");
            System.out.println("Login           - 2");
            System.out.println("Exit            - 3");
            System.out.println("Enter your choice:");
            int n = scan.nextInt();
            if(n==1){
                CreateAccount();
            }
            else if(n==2){
                LoginPage();
            }
            else if(n==3){
                break;
            }
            else{
                System.out.println("Entered choice is not availavble\nPlease enter choice between 1 to 3");
            }
        }
    }
}

import java.util.*;
public class User{
    static int availableUpperBerth=1;
    static int availableLowerBerth=1;
    static int availableMiddleBerth=1;
    static int availableWaitingticket=1;
    
    public static HashMap<Integer,Passenger>PassengerData = new HashMap<>();
    
    static Queue<Integer> waitingList = new LinkedList<>();
    static List<Integer>bookedList = new ArrayList<>();
    
    static List<Integer>UpperBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer>LowerBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer>MiddleBerthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer>WaitingListPosition = new ArrayList<>(Arrays.asList(1)); 
    
    //******************* BOOK TICKETS **********************
        public static void bookTicket(Passenger p , int position,String Berth){
            p.alloted=Berth;
            p.seatNo=position;
            PassengerData.put(p.passengerId,p);
            bookedList.add(p.passengerId);
            System.out.println("Ticket Booked Successfully");
        }
    //******************* ADD TO WAITING LIST ************************
        public static void addToWaitingList(Passenger p,int position,String Berth){
            p.alloted=Berth;
            p.seatNo=position;
            PassengerData.put(p.passengerId,p);
            waitingList.add(p.passengerId);
        }
    
    //******************** SEAT ALLOTING **********************
        public static void bookPassenger(Passenger p){
        if(availableWaitingticket==0){
            System.out.println("No Tickets Available");
        }
        else{
            if(p.berthPreference=="L" && availableLowerBerth>0 ||p.berthPreference=="M"&& availableMiddleBerth>0||p.berthPreference=="U"&&availableUpperBerth>0){
                System.out.println("Prefered berth available");
                if(p.berthPreference.equals("L")){
                    System.out.println("Lower Berth Given");
                    bookTicket(p,LowerBerthPosition.get(0),"L");
                    LowerBerthPosition.remove(0);
                    availableLowerBerth--;
                }
                else if(p.berthPreference.equals("U")){
                    System.out.println("Upper berth Given");
                    bookTicket(p,UpperBerthPosition.get(0),"U");
                    UpperBerthPosition.remove(0);
                    availableUpperBerth--;
                }
                else if(p.berthPreference.equals("M")){
                    System.out.println("Middle berth Given");
                    bookTicket(p,MiddleBerthPosition.get(0),"M");
                    MiddleBerthPosition.remove(0);
                    availableMiddleBerth--;
                }
            }
            else if(availableLowerBerth>0){
                 System.out.println("Lower berth Given");
                 bookTicket(p,LowerBerthPosition.get(0),"WL");
                 LowerBerthPosition.remove(0);
                 availableLowerBerth--;
            }
            else if(availableUpperBerth>0){
                 System.out.println("Upper berth Given");
                 bookTicket(p,UpperBerthPosition.get(0),"U");
                 UpperBerthPosition.remove(0);
                 availableUpperBerth--;
            }
            else if(availableMiddleBerth>0){
                 System.out.println("Middle berth Given");
                 bookTicket(p,MiddleBerthPosition.get(0),"M");
                 MiddleBerthPosition.remove(0);
                 availableMiddleBerth--;
            }
            else if(availableWaitingticket>0){                        
                System.out.println("Added to Waiting List");
                addToWaitingList(p,WaitingListPosition.get(0),"WL");
                WaitingListPosition.remove(0);
                availableWaitingticket--;
            }
        }
    }
    
    //******************** CANCEL TICKETS **********************
        public static void cancelTicket(int Id){
        Passenger p = PassengerData.get(Id);
        PassengerData.remove(Id);
        int freeposition=p.seatNo;
        System.out.println("Cancelled Successfully");
        if(p.alloted.equals("L")){
            availableLowerBerth++;
            LowerBerthPosition.add(freeposition);
        }
        else if(p.alloted.equals("M")){
            availableMiddleBerth++;
            MiddleBerthPosition.add(freeposition);
        }
        else if(p.alloted.equals("U")){
            availableUpperBerth++;
            UpperBerthPosition.add(freeposition);
        }
        if(waitingList.size()>0){
            Passenger passengerFromWL = PassengerData.get(waitingList.poll());
            int positionWL = passengerFromWL.seatNo;
            waitingList.remove(passengerFromWL.passengerId);
            availableWaitingticket++;
            WaitingListPosition.add(positionWL);
        }
    }
    
    //******************** AVAILABLE TICKETS **********************
    public static void availableTickets(){
        System.out.println("Available Lower Berths "  + availableLowerBerth);
        System.out.println("Available Middle Berths "  + availableMiddleBerth);
        System.out.println("Available Upper Berths "  + availableUpperBerth);
        System.out.println("Available Waiting List " + availableWaitingticket);
    }
    
    //******************** BOOKED TICKETS **********************
    public static void bookedTickets(){
        if(PassengerData.size() == 0){
            System.out.println("No details of passengers");
        }
        else{
            for(Passenger p : PassengerData.values()){
            System.out.println("PASSENGER ID " + p.passengerId );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.seatNo + p.alloted);
            }
        }
    }
}

public class Passenger
{
    static int id = 1;
    String name;
    int age;
    String berthPreference;
    int passengerId;
    String alloted;
    int seatNo;
    public Passenger(String name,int age,String berthPreference)
    {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.passengerId = id++;
        alloted = "";
        seatNo = -1;
    }
}
