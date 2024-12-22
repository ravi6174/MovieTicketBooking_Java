import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    static Scanner sc=new Scanner(System.in);
    String username;
    private String password;
    String email;
    private int balance;

    User(String username,String password,String email,int balance){
        this.username=username;
        this.password=password;
        this.email=email;
        this.balance=balance;
    }
    User(){

    }
    abstract void register();
    abstract void login();   
    abstract void forgotPassword(); 

    int getBal(){
        return balance;
    }
    void setBal(int balance){
            this.balance=balance;
    }
    void logout(){
        Main.theater=null;
        Main.user=null;
        Main.audienceno=0;
        Main.theaterno=0;
        System.out.println(Main.green+"Logged out Succesfully"+Main.reset);
        new Welcome().welcome();
    }
    String getPassword(){
        return this.password; 
    }
    void setPassword(String password){
        this.password=password;
    }

    static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,}$";

    public static String emailInput() {

        
        //System.out.print("Enter your emailid: ");
        String emailid=sc.next();
        if(emailid.length()>=5 && emailid.contains("@") && emailid.contains(".") && emailid.indexOf("@")>=1 && emailid.indexOf(".")>(emailid.indexOf("@")+1)){
            return emailid;
        }
        else{
            System.out.println(Main.red+"Invalid Email."+Main.reset);
            System.out.print("Please enter your email again: ");

            return emailInput();
        }
    }

    public static long phonenumberinput(){
        Scanner sc=new Scanner(System.in);
        String phno=sc.next();
        if(phno.charAt(0)<='9'&&phno.charAt(0)>='6'&&phno.length()==10){
            try {
                return Long.parseLong(phno);
            } catch (Exception e) {
                System.out.println(Main.red+"Invalid Mobile Number"+Main.reset);
                return phonenumberinput();
            }
        }
        else{
            System.out.println(Main.red+"Invalid Mobile Number"+Main.reset);
            return phonenumberinput();
        }
    }
    public static void loadingAnimation() throws InterruptedException {
        //String[] loadingChars = {"|", "/", "-", "\\"};
        String dots="...";
        System.out.println();
        System.out.print(Main.green+"Loading");
        for (int i = 0; i <dots.length(); i++) {
            System.out.print(dots.charAt(i));
            Thread.sleep(400);
        }
        System.out.println(Main.reset);
    }
    
    static void paymentAnimation(){
        System.out.println(Main.yellow);
        String message = "Processing payment";
        System.out.print(message);
        int width = 5;  // Width of the bouncing area
        boolean forward = true;
        int position = 0;
        for (int i = 0; i < 10; i++) {
            System.out.print("\r" + message + " ".repeat(position) + ".");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            if (forward) {
                position++;
                if (position >= width) forward = false;  // Change direction
            } else {
                position--;
                if (position <= 0) forward = true;  // Change direction
            }
        }
        System.out.println(Main.reset);
    }
}
