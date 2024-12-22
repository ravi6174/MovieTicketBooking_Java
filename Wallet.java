import java.util.*;
public class Wallet {
    static Scanner sc=new Scanner(System.in);

    //for audience
    void walletMenu(){
        System.out.println(Main.yellow+"   ---Wallet---"+Main.reset);
        System.out.println("1.check balance");
        System.out.println("2.Add amount to wallet");
        System.out.println("3.back to login menu");
        System.out.print("Enter a option: ");
        try {
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    System.out.println("Balance: "+getBalance());
                    walletMenu();
                    break;
                case 2:
                    System.out.println(Main.clear);
                    System.out.print("Enter amount to deposit: ");
                    addAmount(sc.nextInt());
                    break;
                case 3:
                    System.out.println(Main.clear);
                    new Audience().loggedIn();
                    break;
                default:
                    new Wallet().walletMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.red+"Something Went Wrong"+Main.reset);
            walletMenu();
        }
    }


    //for theaters

    void walletMenu(boolean b){
        System.out.println(Main.yellow+"   ---Wallet---"+Main.reset);
        System.out.println();
        System.out.println("1.check balance");
        System.out.println("2.Add amount to wallet");
        System.out.println("3.back to login menu");
        System.out.print("Enter a option: ");
        try {
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(Main.clear);
                    System.out.println("Balance: "+getBalance(true));
                    walletMenu(true);
                    break;
                case 2:
                    System.out.println(Main.clear);
                    System.out.print("Enter amount to deposit: ");
                    addAmount(sc.nextInt(),true);
                    break;
                case 3:
                    System.out.println(Main.clear);
                    new Theaters().loggedIn();
                    break;
                default:
                    new Wallet().walletMenu(true);
                    break;
            }
        } catch (Exception e) {
            System.out.println(Main.red+"Something Went Wrong"+Main.reset);
            walletMenu(true);
        }
    }

    //for audiennce
    int getBalance(){
        return Database.audiencelist.get(Main.audienceno).getBal();
    }

    //for theaters
    int getBalance(boolean b){
        return Database.theaterlist.get(Main.theaterno).getBal();
    }

    //for audience
    void addAmount(int amount){
        System.out.print("Your OTP is:");
        int otp=(int)(Math.random()*10000);
        System.out.println(otp);
        System.out.print("Please enter your OTP: ");
        if(Main.sc.nextInt()==otp){
            int current_balance=Database.audiencelist.get(Main.audienceno).getBal();
            Database.audiencelist.get(Main.audienceno).setBal(current_balance+amount);
            System.out.println(Main.clear);
            System.out.println();
            System.out.println(Main.green+"Amount Deposited Succesfully"+Main.reset);
            new Wallet().walletMenu();
        }
        else{
            System.out.println(Main.clear);
            System.out.println(Main.red+"Transaction Failed"+Main.reset);
            System.out.println("1.Try again");
            System.out.println("2.Back");
            int opt=Main.sc.nextInt();
            if(opt==1){
                addAmount(amount);
            }
            else{
                System.out.println(Main.clear);
                new Audience().loggedIn();
            }
        }
    }


    //for Theaters
    void addAmount(int amount,boolean b){
        try {
            System.out.print("Your OTP is:");
            int otp=(int)(Math.random()*10000);
            System.out.println(otp);
            System.out.print("Please enter your OTP: ");
            if(Main.sc.nextInt()==otp){
                int current_balance=Database.theaterlist.get(Main.theaterno).getBal();
                Database.theaterlist.get(Main.theaterno).setBal(current_balance+amount);
                System.out.println(Main.clear);
                System.out.println();
                System.out.println(Main.green+"Amount Deposited Succesfully"+Main.reset);
                new Wallet().walletMenu(true);
            }
            else{
                System.out.println(Main.clear);
                System.out.println(Main.red+"Transaction Failed"+Main.reset);
                System.out.println("1.Try again");
                System.out.println("2.Back");
                int opt=Main.sc.nextInt();
                if(opt==1){
                    addAmount(amount);
                }
                else{
                    System.out.println(Main.clear);
                    new Theaters().loggedIn();
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Theater exception");
            new Wallet().walletMenu(true);
        }
        
    }

    static int totalamount(int nooftickets){
        System.out.println();
        System.out.println(Main.purple+"   ---Payment details---"+Main.reset);
        System.out.println();
        System.out.println(Main.yellow+"No.of tickets          Amount"+Main.reset);
        int basic=nooftickets*149;
        System.out.println("     "+nooftickets+"         "+nooftickets+" X "+149+" = "+basic);
        int tax=(basic*18)/100;
        System.out.println("18% GST:                 "+tax);
        System.out.println("------------------------------");
        System.out.println(Main.red+"Total:"+Main.reset+"                   "+(basic+tax));
        System.out.println("------------------------------");
        return basic+tax;   
    }

}
