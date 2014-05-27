package cinema_edb;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
	
	private List<Hall> halls = new ArrayList<Hall>();
	
	public void CreateHall(int _rows, int _seats)
	{
		Hall aHall = new Hall(_rows, _seats);
		halls.add(aHall);
		System.out.println("Creation: " + "Hall " + (halls.size()-1) + " Created");
	}
	
	public void CreateShow(int _selectedHall)
	{
		int selectedHall = _selectedHall;
		halls.get(selectedHall).CreateShow();
		System.out.println("Creation: " + "Show " + (halls.get(selectedHall).GetShowListLenght()-1) + " Created in hall " + selectedHall);
	}
	
	public void ReserveSeats(int _selectedHall, int _selectedShow, int _seatsNeeded)
	{
		int seatsNeeded = _seatsNeeded;
		int selectedShow = _selectedShow;
		int selectedHall = _selectedHall;

		List<Seat> foundSeats = halls.get(selectedHall).GetShow(selectedShow).FindSeats(seatsNeeded);
		
		if(foundSeats.size() > 0)
		{
			halls.get(selectedHall).GetShow(selectedShow).ReserveSeats(foundSeats);
			System.out.println("Reservation: " + "Reserved " + seatsNeeded + " in Hall " + selectedHall + " for show " + selectedShow);
		}
		else
		{
			System.out.println("Reservation: " + "Not enough free seats available in Hall " + selectedHall + " for show " + selectedShow);
		}
	}
	
	
	public void PrintSeatStatus()
	{
		int numberOfHalls = halls.size();
		int i = 0;
		System.out.println("");
		System.out.println("************** Cinema Status *****************");
		while(i < numberOfHalls)
		{
			int j = 0;
			int numberOfShows = halls.get(i).GetShowListLenght();
			
			System.out.println("Hall " + i + ":");
			
			while(j < numberOfShows)
			{
				CountedSeats data = halls.get(i).GetShow(j).CountSeats();
				
				System.out.println(" - Show " + j + " | " + data.reservedSeats + " seats reserved" + " | " + data.freeSeats + " seats free");
				System.out.print(data.consoleGraph);
				j++;
			}
			i++;
		}
	}
}
