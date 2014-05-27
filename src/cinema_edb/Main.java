package cinema_edb;

public class Main {

	public static void main(String[] args) {
		
		Cinema myCinema = new Cinema();
		
		myCinema.CreateHall();
		myCinema.CreateShow(0);
		myCinema.CreateShow(0);
		myCinema.ReserveSeats(0, 0, 38);
		myCinema.ReserveSeats(0, 1, 12);
		myCinema.ReserveSeats(0, 1, 40);
		myCinema.CreateHall();
		myCinema.CreateShow(1);
		myCinema.ReserveSeats(1, 0, 3);
		myCinema.PrintSeatStatus();
		
	}
}
