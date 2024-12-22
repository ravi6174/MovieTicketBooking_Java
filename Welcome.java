import java.util.*;
public class Welcome {
    static Scanner sc=new Scanner(System.in);
     static String RESET = "\033[0m";      // Reset color
        static String BLINK = "\033[5m"; 
    void welcome(){ 
        try {
            System.out.println();

            System.out.println(Main.yellow+"                             Welcome to");
            System.out.println("\r\n" + //
                                "██████╗░░█████╗░░█████╗░██╗░░██╗  ░██████╗██╗░░██╗░█████╗░░██╗░░░░░░░██╗\r\n" + //
                                "██╔══██╗██╔══██╗██╔══██╗██║░██╔╝  ██╔════╝██║░░██║██╔══██╗░██║░░██╗░░██║\r\n" + //
                                "██████╦╝██║░░██║██║░░██║█████═╝░  ╚█████╗░███████║██║░░██║░╚██╗████╗██╔╝\r\n" + //
                                "██╔══██╗██║░░██║██║░░██║██╔═██╗░  ░╚═══██╗██╔══██║██║░░██║░░████╔═████║░\r\n" + //
                                "██████╦╝╚█████╔╝╚█████╔╝██║░╚██╗  ██████╔╝██║░░██║╚█████╔╝░░╚██╔╝░╚██╔╝░\r\n" + //
                                "╚═════╝░░╚════╝░░╚════╝░╚═╝░░╚═╝  ╚═════╝░╚═╝░░╚═╝░╚════╝░░░░╚═╝░░░╚═╝░░");
            System.out.println(Main.yellow+"========================================================================"+Main.reset);
            System.out.println();
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Forgot Password?");
            System.out.println("4.Exit Application");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    register();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    login();
                    break;
                case 3:
                    System.out.println(Main.clear);
                    forgot_Password();
                    break;
                case 4:
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Developed by Team RB"+Main.reset);

                    System.out.println(Main.yellow+"Thankyou for using BOOK SHOW"+Main.reset);
                    //System.exit(0);
                    break;
                default:
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Invalid Input"+Main.reset);
                    welcome();
                    break;
            }
        } 
        catch (Exception e) {
                System.out.println(Main.clear);
                System.out.println(Main.red+"Not a valid option"+Main.reset);
                sc.nextLine();
                welcome();
        }
	}

    void register(){
        System.out.println(Main.purple+"-------REGISTRATION-------"+Main.reset);
        try {
            System.out.println("1.Register as Theater Owner");
            System.out.println("2.Register as Audience");
            System.out.println("3.Back");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    User obj=new Theaters();
                    obj.register();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    User obj1=new Audience();
                    obj1.register();
                    break;
                case 3:
                    System.out.println(Main.clear);
                    //System.out.println("-----------------------");
                    welcome();
                    break;    
                default:
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Invalid Input"+Main.reset);
                    register();
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.clear);
            sc.nextLine();
            System.out.println(Main.red+"Not a valid option"+Main.reset);
            register();
        }

        
    }

    
    void login(){
        System.out.println(Main.purple+"-------LOGIN-------"+Main.reset);
        try {
            System.out.println("1.Login as Theater");
            System.out.println("2.Login as Audience");
            System.out.println("3.Back");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    User obj=new Theaters();
                    obj.login();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    User obj2=new Audience();
                    obj2.login();
                    break;
                case 3:
                    //System.out.println("-----------------------");
                    System.out.println(Main.clear);
                    welcome();
                    break;
                default:
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Invalid Input"+Main.reset);
                    login();
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.clear);
            sc.nextLine();
            System.out.println(Main.red+"Not a valid option"+Main.reset);
            login();
        }
    }

    static void forgot_Password(){
        System.out.println(Main.purple+"-------LOGIN-------"+Main.reset);
        try {
            System.out.println("1.Forgot Password - Theater");
            System.out.println("2.Forgot Password - Audience");
            System.out.println("3.Back");
            System.out.print("Enter a option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    User obj=new Theaters();
                    obj.forgotPassword();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    User obj2=new Audience();
                    obj2.forgotPassword();
                    break;
                case 3:
                    //System.out.println("-----------------------");
                    System.out.println(Main.clear);
                    new Welcome().welcome();
                    break;
                default:
                    System.out.println(Main.clear);
                    System.out.println(Main.red+"Invalid Input"+Main.reset);
                    forgot_Password();
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.clear);
            sc.nextLine();
            System.out.println(Main.red+"Not a valid option"+Main.reset);
            forgot_Password();
        }
    }
}
