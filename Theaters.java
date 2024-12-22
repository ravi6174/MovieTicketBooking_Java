import java.util.*;
public class Theaters extends User {
    // String username;
    // private String password;
    // String email;
    String theater_name;
    ArrayList<Movies> movieslist=new ArrayList<Movies>();

    Theaters(){
        super();
    }
    Theaters(String username,String password,String email,String theater_name){
        super(username, password, email,0);
        // this.username=username;
        // this.password=password;
        // this.email=email;
        this.theater_name=theater_name;
    }
    static Scanner sc=new Scanner(System.in);

    public void register(){
        System.out.println(Main.clear);
        Scanner sc=new Scanner(System.in);
        System.out.println(Main.purple+"-------THEATER REGISTRATION-------"+Main.reset);
        try {
                System.out.print("Enter username: ");
                String username=sc.next();

                System.out.print("Enter password: ");  
                sc.nextLine();
                String password=sc.next();

                System.out.print("Enter Theater name: ");  
                sc.nextLine();

                String theatername=sc.nextLine();

                System.out.print("Enter your email id: ");
                String email=User.emailInput();

                User.loadingAnimation();

                //creating theater object based on inputs
                Theaters obj=new Theaters(username, password,email,theatername);
                //adding that object to theater list in Database
                Database.theaterlist.add(obj);
                System.out.println(Main.clear);
                System.out.println(Main.green+"Theater Registration Successful"+Main.reset);
                new Welcome().welcome();
        } catch (Exception e) {
            sc.nextLine();
            register();
        }
    }

    void login(){
        System.out.println(Main.clear);
        System.out.println(Main.purple+"-------THEATER LOGIN-------"+Main.reset);
        try {
                int count=0;
                System.out.print("Enter your username: ");
                String username=sc.next();
                System.out.print("Enter your password: ");
                String password=sc.next();

                User.loadingAnimation();

                for (int i = 0; i < Database.theaterlist.size(); i++) {
                    if(Database.theaterlist.get(i).username.equals(username)&&Database.theaterlist.get(i).getPassword().equals(password)){
                        //checking if details entered are matching or not from theater list
                        count+=1;
                        if(count==1){
                            Main.theater=Database.theaterlist.get(i);
                            Main.theaterno=i;
                        }
                        break;
                    }
                }
                if(count!=0){
                    System.out.println(Main.clear);
                    System.out.println(Main.green+"Login successful"+Main.reset);
                    loggedIn();
                }
                else{
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Account not found"+Main.reset);
                    new Welcome().welcome();
                }
            }
        catch (Exception e) {
            //System.out.println(e);
            sc.nextLine();
            login();
        }
    }

    public void changePassword(){
        System.out.println(Main.clear);
        System.out.print("Enter your old password: ");
        String oldpass=sc.next();
        System.out.print("Enter your Email id: ");
        String email=sc.next();
        int c=0;
        int index=0;
        for (int i = 0; i < Database.theaterlist.size(); i++) {
            Theaters obj=Database.theaterlist.get(i);
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
            Database.theaterlist.get(index).setPassword(sc.next());
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

    public void forgotPassword(){
        System.out.println(Main.clear);
        System.out.print("Enter your username: ");
        String username=sc.next();
        System.out.print("Enter your Email Id: ");
        String email=sc.next();
        int c=0;
        int index=0;
        for (int i = 0; i < Database.theaterlist.size(); i++) {
            Theaters obj=Database.theaterlist.get(i);
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
            Database.theaterlist.get(index).setPassword(sc.next());
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

    void loggedIn(){
        // Theater options after login
        System.out.println();
        System.out.println(Main.red+"  ***THEATER DASHBOARD***");
        System.out.println("  -----------------------"+Main.reset);
        System.out.println();
        System.out.println(Main.yellow+"Welcome "+Main.theater.theater_name+"!"+Main.reset);
        try {
            System.out.println("1.Add movie");
            System.out.println("2.View Booked tickets in my theater");
            System.out.println("3.View Transactions");
            System.out.println("4.Change Password");
            System.out.println("5.Wallet");
            System.out.println("6.Logout");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    addmovie();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    viewbookedtickets();
                    break;
                case 3:
                     viewTransaction();
                     new Theaters().loggedIn();
                     break;
                case 4:
                    new Theaters().changePassword();
                    new Theaters().loggedIn();
                    break;
                case 5:
                    System.out.println(Main.clear);
                    new Wallet().walletMenu(true);
                    break;
                case 6:
                    System.out.println(Main.clear);
                    logout();
                    break;
                default:
                    System.out.println(Main.red+"Please Enter correct Input"+Main.reset);
                    loggedIn();
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.red+"Invalid Input..Please try aginn"+Main.reset);
            //System.out.println(e);
            sc.nextLine();
            loggedIn();
        }
    }
    
    private static void addmovie(){
        //System.out.println("------------------------");
        System.out.println(Main.clear);
        System.out.println(Main.Cyan+"Enter all details to add MOVIE"+Main.reset);
        System.out.println();
        sc.nextLine();

        //creating movie obj
        Movies obj=new Movies().returnMovies();
        Database.theaterlist.get(Main.theaterno).movieslist.add(obj);
        System.out.println(Main.clear);
        System.out.println(Main.green+"Movie added successfully"+Main.reset);
        new Theaters().loggedIn();
    }

    private void viewbookedtickets(){
        if(Database.theaterlist.get(Main.theaterno).movieslist.size()!=0){
            //System.out.println(Main.Cyan+"**********************"+Main.reset);
            System.out.println(Main.clear);       
            System.out.println(Main.purple+"Movies List"+Main.reset);
            System.out.println(Main.Cyan+"**********************"+Main.reset);
            Theaters own_theater=Database.theaterlist.get(Main.theaterno);
            for (int i = 0; i < own_theater.movieslist.size(); i++) {
                //if(own_theater.movieslist.get(i)!=null)
                    System.out.println((i+1)+". "+own_theater.movieslist.get(i).Title);
            }
            try {
                System.out.print("select movie: ");
                int opt=sc.nextInt();            
                if(opt<=own_theater.movieslist.size()&&opt>0){
                    Movies mv=own_theater.movieslist.get(opt-1);
                    Movies.showDayList(mv);
                    loggedIn();
                }
                else{
                    System.out.println(Main.red+"Not a valid input"+Main.reset);
                    viewbookedtickets();
                    loggedIn();
                }
                
            } catch (Exception e) {
                System.out.println(Main.red+"Something went wrong!!"+Main.reset);
                sc.nextLine();
                viewbookedtickets();
            }
        }
        else{
            System.out.println(Main.red+"Movies not available. Please add"+Main.reset);
            new Theaters().loggedIn();
        }
    }

    static void viewTransaction(){
        System.out.println(Main.clear);
        int sno=0;
        System.out.println(Main.purple+"    ---TRANSACTIONS---"+Main.reset);
        System.out.println(Main.green+"    Transaction ID    Paid by"+Main.reset);
        System.out.println();
        for (int i = 0; i < Database.transactionslist.size(); i++) {
            Transaction obj=Database.transactionslist.get(i);
            if(obj.to.equals(Database.theaterlist.get(Main.theaterno).username)){
                //System.out.println();
                System.out.println((sno+1)+". "+obj.trans_id+"     "+obj.from);
                sno++;
            }
        }
        if(sno==0){
            System.out.println(Main.red+"No transactions found"+Main.reset);
        }
        else{
            System.out.println();
            System.out.println("1.View Transaction details");
            System.out.println("2.Back to login menu");
            System.out.print("Enter a option: ");
            String opt=sc.next();
            if(opt.equals("1")){
                new Transaction().viewTransactionDetails(); 
                System.out.println();
                System.out.println("1.View another transaction details");  
                System.out.println("2.Back to login menu");  
                System.out.print("Enter a option: ");  
                String opt2=sc.next();
                if(opt2.equals("1")){
                    viewTransaction();
                }
                else{
                    System.out.println(Main.clear);
                }    
            }
            else if(opt.equals("2")){
                System.out.println(Main.clear);
            }
            else{
                System.out.println(Main.red+"Something Went Wrong!!"+Main.reset);
                System.out.println();
                viewTransaction();
            }
        }
    }
}
