import java.util.*;

class Main{
    static Scanner sc=new Scanner(System.in);

    //These Variables store theater or audience object and their index number in theater or audience list when any one of them logged in
    static Theaters theater;
    static Audience user;
    static int theaterno=0;
    static int audienceno=0;

    public static String reset  = "\u001B[0m";
    public static String red    = "\u001B[31m";
    public static String green  = "\u001B[32m";
    public static String yellow = "\u001B[33m";
    public static String clear  = "\033\143";
    public static String purple = "\u001B[95m";
    public static String Cyan   = "\u001B[96m";
    public static String magenta= "\u001B[35m";
    public static String blue   = "\u001B[34m";
	public static void main(String[]args){
        System.out.println(Main.clear); 
        new Welcome().welcome();
    }
 } 