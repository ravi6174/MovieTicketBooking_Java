import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Day {
    String date;
    Shows show1=new Shows("10:00 AM");
    Shows show2=new Shows("02:00 PM");
    Shows show3=new Shows("06:00 PM");
    Shows show4=new Shows("10:00 PM");
    
    Day(String date){
        this.date=date;
    }
    public static Day createDay(String date){
        return new Day(date);
    }
    static void displayShows(Day day){
        Scanner sc=new Scanner(System.in);
        //System.out.println(Main.Cyan+"**********************"+Main.reset); 
        System.out.println(Main.clear);
        System.out.println(Main.purple+"List of shows"+Main.reset);
        System.out.println(Main.Cyan+"**********************"+Main.reset);        
        System.out.println("1. "+day.show1.time);
        System.out.println("2. "+day.show2.time);
        System.out.println("3. "+day.show3.time);
        System.out.println("4. "+day.show4.time);
        int seats_booked=0;
        try {
            System.out.print("select show: ");
            int showopt=sc.nextInt();
            System.out.println(Main.clear);       
            if(showopt>0&&showopt<=4){
                switch (showopt) {
                    case 1:
                        seats_booked=Shows.viewSeats(day.show1.seating);
                        System.out.println(Main.red+"Booked: "+Main.reset+seats_booked+Main.green+"   Available: "+Main.reset+(70-seats_booked));
                        break;
                    case 2:
                        seats_booked=Shows.viewSeats(day.show2.seating);
                        System.out.println(Main.red+"Booked: "+Main.reset+seats_booked+Main.green+"   Available: "+Main.reset+(70-seats_booked));
                        break;
                    case 3:
                        seats_booked=Shows.viewSeats(day.show3.seating);
                        System.out.println(Main.red+"Booked: "+Main.reset+seats_booked+Main.green+"   Available: "+Main.reset+(70-seats_booked));
                        break;
                    default:
                        seats_booked=Shows.viewSeats(day.show4.seating);
                        System.out.println(Main.red+"Booked: "+Main.reset+seats_booked+Main.green+"   Available: "+Main.reset+(70-seats_booked));
                        break;                
                }
                //new Theaters().loggedIn();
            }
            else{
                displayShows(day);
            }
            
        } catch (Exception e) {
            System.out.println(Main.red+"Invalid Input"+Main.reset);
            sc.nextLine();
            displayShows(day);
        }
    }


    static String[]  takeDatesList(int noofdays){
        //array to store dates
        String[] dateslist=new String[noofdays];
        Scanner sc=new Scanner(System.in);

        //setting date format in what way we want
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 

        System.out.print("Enter First Day date in (DD-MM-YYYY) Format: ");
        String inputdate = sc.next();

        try {
            LocalDate firstday_date = LocalDate.parse(inputdate, formatter);
            String fdateString = firstday_date.format(formatter); // Convert LocalDate to String
            dateslist[0]=fdateString;
            for (int i = 1; i < noofdays; i++) {
                LocalDate date=firstday_date.plusDays(i);
                String dateString = date.format(formatter); // Convert LocalDate to String
                dateslist[i]=dateString;
            }
            return dateslist;

        } 
        catch (DateTimeParseException e) {
            System.out.println(Main.red+"Invalid date format.Please try again"+Main.clear);
             return takeDatesList(noofdays);
        }
        catch(Exception e){
            System.out.println("Something went wrong");
             return takeDatesList(noofdays);
        }
        //return dateslist;
    }
}
