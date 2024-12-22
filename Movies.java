import java.util.*;;
public class Movies {
    static Scanner sc=new Scanner(System.in);
    String Title;
    String description;
    String Language;
    ArrayList<Day> noofdays=new ArrayList<Day>();
    Movies(String Title,String description,String Language,ArrayList<Day> noofdays){
        this.Title=Title;
        this.description=description;
        this.Language=Language;  
        this.noofdays=noofdays; 
    }
    Movies(){
    }
    public Movies returnMovies(){
        Scanner sc=new Scanner(System.in);
        try {
            Movies movie=new Movies();
            //sc.nextLine();
            System.out.print("Enter movie title: ");
            String movietitle=sc.nextLine();
            //System.out.println(movietitle);
            movie.Title=movietitle;

            System.out.print("Enter movie description: ");
            String moviedesc=sc.nextLine();
            movie.description=moviedesc;

            System.out.print("Enter movie Language: ");
            String movielang=sc.nextLine();
            movie.Language=movielang;

            System.out.print("Enter no. of days movie running: ");
            int nodays=sc.nextInt();

            String[] days=new String[nodays];
            days=Day.takeDatesList(nodays);

            ArrayList<Day> dayslist=new ArrayList<Day>();

            //creating Day object by sending date parameter for each day object
            for(int i=0;i<days.length;i++){
                dayslist.add(Day.createDay(days[i]));
            }

            movie.noofdays=dayslist; //adding day list to movie object

            return movie; //returning movie object
        } 
        catch (Exception e) {
            System.out.println(Main.red+"Something went wrong!!"+Main.reset);
            //sc.nextLine();
            return returnMovies();
        }
       // return new Movies();
    }
    static void showDayList(Movies movie){
        Scanner sc=new Scanner(System.in);
        //System.out.println(Main.Cyan+"**********************"+Main.reset);
        if(movie.noofdays.size()==0){
            System.out.println(Main.red+"Not Available"+Main.reset);
        }
        else{
            System.out.println(Main.clear);       
            System.out.println(Main.purple+"Movies Running dates"+Main.reset);
            System.out.println(Main.Cyan+"**********************"+Main.reset);        
            for (int i = 0; i < movie.noofdays.size(); i++) {
                System.out.println((i+1)+". "+movie.noofdays.get(i).date);
            }
            try {
                System.out.print("Select date number: ");
                int dayopt=sc.nextInt();
                if(dayopt>0&&dayopt<=movie.noofdays.size()){
                        Day day=movie.noofdays.get(dayopt-1);
                        System.out.println(Main.clear);       
                        Day.displayShows(day);
                }
                else{
                    System.out.println(Main.red+"Invalid"+Main.reset);
                    showDayList(movie);
                }
            } 
            catch (Exception e) {
                System.out.println(Main.red+"Invalid Input"+Main.reset);
                sc.nextLine();
                showDayList(movie);
            }
        }
    }

    public static void printMovieDetails(String title, String description, String language) {
        String border = "----------------------------------------";
        String header = "|          "+Main.blue+"---Movie Details---"+Main.reset+"         |";
        
        // Calculate spaces
        String titleLine = "| Title: " + Main.yellow+ title + Main.reset+" ".repeat(30 - title.length()) + "|";
        String descriptionLine = "| Description: " +Main.yellow+ description + Main.reset+" ".repeat(24 - description.length()) + "|";
        String languageLine = "| Language: " + Main.yellow+language +Main.reset+ " ".repeat(27 - language.length()) + "|";

        System.out.println(border);
        System.out.println(header);
        System.out.println("|                                      |");
        System.out.println(titleLine);
        System.out.println(descriptionLine);
        System.out.println(languageLine);
        System.out.println(border);
    }
}
