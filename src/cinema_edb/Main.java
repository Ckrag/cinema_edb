package cinema_edb;

public class Main {

	public static void main(String[] args) {
		
		Cinema myCinema = new Cinema();
		
		myCinema.CreateHall(6,28);
		myCinema.CreateShow(0);
		myCinema.ReserveSeats(0, 0, 55);
		myCinema.PrintSeatStatus();
		
	}
}
