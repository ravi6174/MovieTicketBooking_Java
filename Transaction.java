import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Transaction {
    String trans_id;
    String from;
    String to;
    int amount;
    String time;
    String date;
    static Scanner sc=new Scanner(System.in);
    Transaction(String trans_id,String from,String to,int amount, String time,String date){
        this.trans_id=trans_id;
        this.from=from;
        this.to=to;
        this.amount=amount;
        this.date=date;
        this.time=time;
    }

    Transaction(){
    }
    static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        String date=now.toString();
        return date.substring(0, 10);
    }
    static String getCurrentDate(){
        LocalDateTime now = LocalDateTime.now();
        String date=now.toString();
        return date.substring(12, 19);
    }
    static String getTransactionID(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return "T"+now.format(formatter)+(int)Math.random()*100;
    }

    void getTransactionDetails(){
        System.out.println(Main.clear);
        System.out.println(Main.yellow+"       ---Transaction Details---"+Main.reset);
        System.out.println();
        System.out.println("Transaction ID   : "+Main.purple+this.trans_id+Main.reset);
        System.out.println("From             : "+Main.purple+this.from+Main.reset);
        System.out.println("To               : "+Main.purple+this.to+Main.reset);
        System.out.println("Amount           : "+Main.purple+this.amount+Main.reset);
        System.out.println("Transaction Time : "+Main.purple+this.time+Main.reset);
        System.out.println("Transaction Date : "+Main.purple+this.date+Main.reset);

    }

    void viewTransactionDetails(){
        System.out.print("Enter Transaction Id: ");
        String t_id=sc.next();
        int c=0;
        //int index=0;
        Transaction obj=null;
        for (int i = 0; i < Database.transactionslist.size(); i++) {
            Transaction obj1=Database.transactionslist.get(i);
            if(obj1.trans_id.equals(t_id)){
                c++;
                obj=obj1;
                // index=i;
                break;
            }
        }
        if(c==0){  
            System.out.println(Main.clear);
            System.out.println(Main.red+"Transaction Not Found"+Main.reset);
        }
        else{
            obj.getTransactionDetails();
        }
    }
}

