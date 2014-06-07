package cinema_edb;

public class Main {

	public static void main(String[] args) {
		
		Cinema myCinema = new Cinema();
		
		myCinema.CreateHall(4,6);
		myCinema.CreateShow(0);
		myCinema.ReserveSeats(0, 0, 4);
		myCinema.ReserveSeats(0, 0, 7);
		myCinema.PrintSeatStatus();
		
	}
}
