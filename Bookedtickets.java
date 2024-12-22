import java.util.ArrayList;

public class Bookedtickets {
    //This object stores the booked ticket details
    String audience_username;
    String theater;
    String movie;
    String date;
    String show;
    String trans_id;
    ArrayList<String> seat_numbers;

    //creating bookedticket object
    static Bookedtickets createBookedTicket(String username,int theater,int movie,int date,int show,ArrayList<String> seat_numbers,String trans_id){

        Theaters selected_theater=Database.theaterlist.get(theater);
        String theater_name=selected_theater.theater_name;

        Movies selected_movie=selected_theater.movieslist.get(movie);
        String movie_name=selected_movie.Title;

        Day selected_day=selected_movie.noofdays.get(date);
        String selected_date=selected_day.date;

        String showtime="";
        if(show==1)
            showtime=selected_day.show1.time;
        else if(show==2)
            showtime=selected_day.show2.time;
        else if(show==2)
            showtime=selected_day.show3.time;
        else
            showtime=selected_day.show4.time;

        return new Bookedtickets(username, theater_name, movie_name, selected_date, showtime, seat_numbers,trans_id);


    }
    public Bookedtickets(String username,String theater,String movie,String date,String show,ArrayList<String> seat_numbers,String trans_id){
        this.audience_username=username;
        this.theater=theater;
        this.movie=movie;
        this.date=date;
        this.show=show;
        this.seat_numbers=seat_numbers;
        this.trans_id=trans_id;
    }
}
