import java.util.Scanner;

class Audience extends User {
    String name;
    long phonenumber;
    int age;

    //used to store username of theater when audience selected theater to book movie.
    //this is used to set "to" parameter while creating a transaction
    static int selectedtheater=0;

    static Scanner sc=new Scanner(System.in);
    Audience(String username,String name,long phonenumber,String emailid,String password,int age,int balance) {
        super(username, password,emailid,balance);
        this.name=name;
        this.phonenumber=phonenumber;
        this.age=age;
    }
    Audience(){

    }
    public void forgotPassword(){
        System.out.print("Enter your username: ");
        String username=sc.next();
        System.out.print("Enter your Email Id: ");
        String email=sc.next();
        int c=0;
        int index=0;
        for (int i = 0; i < Database.audiencelist.size(); i++) {
            Audience obj=Database.audiencelist.get(i);
            if(obj.username.equals(username)&&obj.email.equals(email)){
                c++;
                index=i;
                break;
            }
        }
        try {
            User.loadingAnimation();
        } catch (Exception e) {
            System.out.println("Animation exception");
            new Welcome().welcome();
        }
        if (c!=0) {
            System.out.println(Main.clear);
            System.out.print("Enter New Password: ");
            Database.audiencelist.get(index).setPassword(sc.next());
            try {
                User.loadingAnimation();
            } catch (Exception e) {
                System.out.println("Animation exception");
                new Welcome().welcome();
            }
            System.out.println(Main.clear);
            System.out.println(Main.green+"Password Updated Succesfully"+Main.reset);
            new Welcome().welcome();
        }
        else{
            System.out.println(Main.clear);
            System.out.println(Main.red+"Details not found"+Main.reset);
            new Welcome().welcome();
        }

    }
    public void register(){
        System.out.println(Main.purple+"-------Audience Registration-------"+Main.reset);
        try {
                System.out.print("Enter username: ");
                String username=sc.next();
                sc.nextLine();
                System.out.print("Enter password: ");  
                String password=sc.next();
                sc.nextLine();

                System.out.print("Enter your name: ");  
                String name=sc.nextLine(); 

                System.out.print("Enter your mobile number: ");
                long phonenumber=User.phonenumberinput();

                System.out.print("Enter your email id: ");
                String email=User.emailInput();

                System.out.print("Enter age: ");  
                int age=sc.nextInt();

                User.loadingAnimation();

                //creating Audience object
                Audience obj=new Audience(username,name,phonenumber,email, password,age,0);

                //adding audience object to audience list
                Database.audiencelist.add(obj);
                System.out.println(Main.clear);
                System.out.println(Main.green+"Audience Registration successful"+Main.reset);
                new Welcome().welcome();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println(Main.clear);
            System.out.println(Main.red+"Invalid Input"+Main.reset);
            register();
        }
    }

    void login(){
        System.out.println(Main.purple+"-------Audience Login-------"+Main.reset);
        try {
            System.out.print("Enter your username: ");
            String username=sc.next();
            System.out.print("Enter your password: ");
            String password=sc.next();

            User.loadingAnimation();

            int count=0;
            for (int i = 0; i < Database.audiencelist.size(); i++) {
                if(Database.audiencelist.get(i).username.equals(username)&&Database.audiencelist.get(i).getPassword().equals(password)){
                    count+=1; 
                    if(count==1){
                        //checking if input matches with data in database
                        Main.user=Database.audiencelist.get(i);
                        Main.audienceno=i;
                        break;
                    }
                }

            }  
            if(count>0){
                System.out.println("--------------------------------");
                System.out.println(Main.clear);
                System.out.println(Main.green+"Audience Login successful"+Main.reset);
                //System.out.println("index "+Main.audienceno);

                loggedIn();

            }
            else{
                System.out.println(Main.clear);
                System.out.println(Main.red+"Account not found"+Main.reset);
                new Welcome().welcome();
            }
        }
        catch (Exception e) {
            sc.nextLine();
            new Audience().login();
        }
    }


    public void changePassword(){
        System.out.print("Enter your old password: ");
        String oldpass=sc.next();
        System.out.print("Enter your Email id: ");
        String email=sc.next();
        int c=0;
        int index=0;
        for (int i = 0; i < Database.audiencelist.size(); i++) {
            Audience obj=Database.audiencelist.get(i);
            if(obj.getPassword().equals(oldpass)&&obj.email.equals(email)){
                c++;
                index=i;
                break;
            }
        }
        try {
            User.loadingAnimation();
        } catch (Exception e) {
            System.out.println("Animation exception");
        }
        if (c!=0) {
            System.out.println(Main.clear);
            System.out.print("Enter New Password: ");
            Database.audiencelist.get(index).setPassword(sc.next());
            try {
                User.loadingAnimation();
            } catch (Exception e) {
                System.out.println("Animation exception");
            }
            System.out.println(Main.clear);
            System.out.println(Main.green+"Password Updated Succesfully"+Main.reset);
        }
        else{
            System.out.println(Main.clear);
            System.out.println(Main.red+"Details Not Matched"+Main.reset);
        }
    }

    void loggedIn(){
        // Audience options after login
        System.out.println();
        System.out.println(Main.red+"  ***AUDIENCE DASHBOARD***");
        System.out.println("  ------------------------"+Main.reset);
        System.out.println();
        System.out.println(Main.Cyan+"Welcome "+Main.user.name+"!"+Main.reset);
        try {
            System.out.println("1.Book Tickets");
            System.out.println("2.View Profile");
            System.out.println("3.Update Profile");
            System.out.println("4.View Booked History");
            System.out.println("5.Wallet");
            System.out.println("6.Change Password");
            System.out.println("7.View Transaction Details");
            System.out.println("8.Logout");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    bookTickets();
                    break;
                case 2:
                    //view profile
                    Database.audiencelist.get(Main.audienceno).viewProfile();;
                    loggedIn();
                    break;
                case 3:
                    //update profile
                    updateProfile();
                    new Audience().loggedIn();
                    break;
                case 4:
                    viewBookedHistory();
                    new Audience().loggedIn();
                    break;
                case 5:
                    System.out.println(Main.clear);
                    new Wallet().walletMenu();
                    break;
                case 6:
                    new Audience().changePassword();
                    new Audience().loggedIn();
                    break;
                case 7:
                    System.out.println(Main.clear);
                    new Transaction().viewTransactionDetails();
                    new Audience().loggedIn();
                    break;
                case 8:
                    System.out.println(Main.clear);
                    logout();
                    break;
                default:
                    System.out.println(Main.red+"Please Enter correct Input"+Main.reset);
                    loggedIn();
                    break;
            }
        } 
        catch (Exception e) {
            System.out.println(Main.clear);
            System.out.println(Main.red+"Invalid Input..Please try again"+Main.reset);
            //System.out.println(e);
            sc.nextLine();
            loggedIn();
        }
    }
    static void bookTickets(){
        if(Database.theaterlist.size()!=0){
           // System.out.println(Main.Cyan+"**********************"+Main.reset);
           System.out.println(Main.clear);
            System.out.println(Main.purple+"Theaters List"+Main.reset);
            System.out.println(Main.Cyan+"**********************"+Main.reset);            
            int[] booking_details=new int[4];

            for (int i = 0; i < Database.theaterlist.size(); i++) {
                    System.out.println((i+1)+". "+Database.theaterlist.get(i).theater_name);                   
            }
            System.out.print("Select theater number: ");
            booking_details[0]=sc.nextInt()-1;
            Audience.selectedtheater=booking_details[0];
 
            Theaters selected_theater=Database.theaterlist.get(booking_details[0]);

            System.out.println(Audience.selectedtheater);

            System.out.println(Main.clear);

            //System.out.println(Main.Cyan+"**********************"+Main.reset);
            System.out.println(Main.purple+"Movies List"+Main.reset);
            System.out.println(Main.Cyan+"**********************"+Main.reset);

            if(selected_theater.movieslist.size()!=0){
                for (int j = 0; j < selected_theater.movieslist.size(); j++) {
                    System.out.println((j+1)+". "+selected_theater.movieslist.get(j).Title);
                }
                System.out.print("Enter movie number: ");
                booking_details[1]=sc.nextInt()-1;
                Movies selected_movie=selected_theater.movieslist.get(booking_details[1]);

                //display movie details

                Movies.printMovieDetails(selected_movie.Title, selected_movie.description, selected_movie.Language);
                // System.out.println(Main.yellow+"---Movie Details---"+Main.reset);
                // System.out.println("Title: "+Main.Cyan+ selected_movie.Title+Main.reset);
                // System.out.println("Description: "+Main.Cyan+ selected_movie.description+Main.reset);
                // System.out.println("Language: "+Main.Cyan+ selected_movie.Language+Main.reset);
                // System.out.println("-----------------------------");

                System.out.println("1.continue booking");
                System.out.println("2.back");
                System.out.print("Enter a option: ");
                String opt=sc.next();
                if(opt.equals("1")){
                    System.out.println(Main.clear);
                    //System.out.println(Main.Cyan+"**********************"+Main.reset);
                    System.out.println(Main.purple+"Movie Running Dates"+Main.reset);
                    System.out.println(Main.Cyan+"**********************"+Main.reset);

                    for (int j = 0; j < selected_movie.noofdays.size(); j++) {
                        System.out.println((j+1)+". "+selected_movie.noofdays.get(j).date);
                    }
                    System.out.print("Enter Date: ");
                    booking_details[2]=sc.nextInt()-1;
                    System.out.println(Main.clear);
                    //System.out.println(Main.Cyan+"**********************"+Main.reset);
                    System.out.println(Main.purple+"List of Shows"+Main.reset);
                    System.out.println(Main.Cyan+"**********************"+Main.reset);

                    System.out.println("1. 9:00 AM");
                    System.out.println("2. 1:00 PM");
                    System.out.println("3. 5:00 PM");
                    System.out.println("4. 9:00 PM");
                    System.out.print("select Show: ");
                    int show=sc.nextInt();
                    booking_details[3]=show;

                    //stage after selecting Theater,movie,date and show
                    new Shows().bookTickets(booking_details);
                }
                else if(opt.equals("2")){
                    Audience.selectedtheater=0;
                    System.out.println(Main.clear);
                    new Audience().loggedIn();
                }
                else{
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Something went wrong!!"+Main.reset);
                    new Audience().loggedIn();

                }
            }
            else{
                System.out.println(Main.clear);
                System.out.println(Main.red+"Movies not available"+Main.reset);
                Audience.selectedtheater=0;
                new Audience().loggedIn();
            }
        }
        else{
            System.out.println(Main.clear);
            System.out.println(Main.red+"Theaters not available"+Main.reset);
            new Audience().loggedIn();
        }
    }

    static void viewBookedHistory(){
        System.out.println(Main.clear);
        int cc=0;
        System.out.println(Main.yellow+"    BOOKED TICKETS    "+Main.reset);
        System.out.println();
        int ticketcount=0;
        for (int i = 0; i < Database.bookedtickets.size(); i++) {
            Bookedtickets ticket=Database.bookedtickets.get(i);
            if(ticket.audience_username.equals(Main.user.username)){
                cc++;
                ticketcount++;
                System.out.println(Main.green+"Ticket "+ticketcount+Main.reset);
                System.out.println();
                System.out.println("Theater Name   : "+Main.purple+ticket.theater+Main.reset);
                System.out.println("Movie Name     : "+Main.purple+ticket.movie+Main.reset);
                System.out.println("Date           : "+Main.purple+ticket.date+Main.reset);
                System.out.println("Show Timings   : "+Main.purple+ticket.show+Main.reset);

                int c=0;
                String seats="";
                for (int j = 0; j < ticket.seat_numbers.size(); j++) {
                    c++;
                    if(c!=1)
                        seats+=", ";
                    seats+=ticket.seat_numbers.get(j);
                }
                System.out.println("Seat Numbers   : "+Main.purple+seats+Main.reset);
                System.out.println("Transaction ID : "+Main.purple+ticket.trans_id+Main.reset);

            }
            System.out.println();
        }
        if(cc==0)
            System.out.println(Main.red+"Movies not yet booked!"+Main.reset);
        //System.out.println();
    }

    void viewProfile(){
        System.out.println(Main.clear);
        System.out.println(Main.yellow+"       ---PROFILE---"+Main.reset);
        System.out.println();
        System.out.println("Name           : "+Main.purple+this.name+Main.reset);
        System.out.println("Username       : "+Main.purple+this.username+Main.reset);
        System.out.println("Email-id       : "+Main.purple+this.email+Main.reset);
        System.out.println("Mobile         : "+Main.purple+this.phonenumber+Main.reset);
        System.out.println("Age            : "+Main.purple+this.age+Main.reset);
        System.out.println("Wallet balance : "+Main.purple+this.getBal()+Main.reset);
    }

    void updateProfile(){
        System.out.println(Main.clear);

        System.out.println("update your profile "+Main.Cyan+Main.user.name+"!"+Main.reset);
        {
            System.out.println("1.Change Email");
            System.out.println("2.Change Mobile Number");
            System.out.println("3.Back to Login Menu");
            System.out.print("Enter a option: ");
            String opt=sc.next();
            switch (opt) {
                case "1":
                    changeEmail();
                  break;
                case "2":
                    changePhoneNumber();
                    break;
                case "3":
                    System.out.println(Main.clear);
                    break;
                default:
                    System.out.println(Main.red+"Invalid Option"+Main.reset);
                    updateProfile();
                    break;
            }
        }
    }
    void changeEmail()
    { 
        System.out.println(Main.clear);
        System.out.print("Enter your new email id number: ");
        String email=User.emailInput();
        String old_email=Database.audiencelist.get(Main.audienceno).email;
        Database.audiencelist.get(Main.audienceno).email=email;
        System.out.println(Main.clear);
        System.out.println("Your email-id is succesfully updated from "+old_email+" to "+Main.green+email+". "+Main.reset);
    }
    void changePhoneNumber()
            { 
            System.out.println(Main.clear);
            System.out.print("Enter your new Mobile number: ");
            long phonenumber=User.phonenumberinput();
            long old_phno=Database.audiencelist.get(Main.audienceno).phonenumber;
            Database.audiencelist.get(Main.audienceno).phonenumber=phonenumber;
            System.out.println(Main.clear);
            System.out.println("Your Mobile number is succesfully updated from "+old_phno+" to "+Main.green+phonenumber+". "+Main.reset);
    }
}
