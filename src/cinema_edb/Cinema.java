package cinema_edb;

import java.util.ArrayList;
import java.util.List;

public class Cinema implements ICinema {
	
	private List<Hall> hallList = new ArrayList<Hall>();
	
	/* @ (* Creates a new hall object and adds it to hallList *);
	  @ pre hallList != null;
	  @
	  @ post hallList.size() == \old(hallList.size() + 1);
	  @
	  @ */
	public void CreateHall(int _rows, int _seats)
	{
		Hall aHall = new Hall(_rows, _seats);
		hallList.add(aHall);
		System.out.println("Creation: " + "Hall " + (hallList.size()-1) + " Created");
	}
	
	/* @ (* Creates a new show in the hall in hallList based on index *);
	  @ pre _selectedHall >= 0 && _selectedHall =<hallList.size();
	  @
	  @ post hallList.get(selectedHall).CreateShow();
	  @ 
	  @ */
	public void CreateShow(int _selectedHall)
	{
		int selectedHall = _selectedHall;
		hallList.get(selectedHall).CreateShow();
		System.out.println("Creation: " + "Show " + (hallList.get(selectedHall).GetShowListLenght()-1) + " Created in hall " + selectedHall);
	}
	
	/* @ (* Reserves seats at a given show in a given hall. If 0 are found the reservation function failed due to lag of space *);
	  @ pre _selectedHall >= 0 && _selectedHall =< hallList.size();
	  @ pre _selectedShow >= 0 && _selectedShow =< hallList.get(selectedHall).GetShowListLenght();
	  @ pre _seatsNeeded > 0 && _seatsNeeded <= hallList.get(selectedHall).GetSeatAmount();
	  @
	  @ post hallList.get(selectedHall).GetShow(selectedShow).FindSeats(seatsNeeded) >= 0;
	  @ post hallList.get(selectedHall).GetShow(selectedShow).ReserveSeats(foundSeats) <== foundSeats.size() >= 0;
	  @ 
	  @ */
	public void ReserveSeats(int _selectedHall, int _selectedShow, int _seatsNeeded)
	{
		int seatsNeeded = _seatsNeeded;
		int selectedShow = _selectedShow;
		int selectedHall = _selectedHall;

		List<Seat> foundSeats = hallList.get(selectedHall).GetShow(selectedShow).FindSeats(seatsNeeded);
		
		if(foundSeats.size() > 0)
		{
			hallList.get(selectedHall).GetShow(selectedShow).ReserveSeats(foundSeats);
			System.out.println("Reservation: " + "Reserved " + seatsNeeded + " in Hall " + selectedHall + " for show " + selectedShow);
		}
		else
		{
			System.out.println("Reservation: " + "Not room for " + seatsNeeded + " more in Hall " + selectedHall + " for show " + selectedShow);
		}
	}
	
	
	/* @ (* Prints information about the status of all halls and all their shows into the console *);
	  @ 
	  @ pre hallList != null;
	  @ 
	  @ (* antallet af pladser hvor available = false * );
  	  @ post	\(forall int i; 0 <= i && i < hallList.size();
						\(forall int j; 0 <= j && j < hallList.get(i).GetShowListLenght(); 
								CountedSeats data = hallList.get(i).GetShow(j).CountSeats();););	
	  @
	  @ */
	public /*@ pure @*/ void PrintSeatStatus()
	{
		int numberOfHalls = hallList.size();
		int i = 0;
		System.out.println("");
		System.out.println("************** Cinema Status *****************");
		/*@
		@ loop_invariant hallList != null;
		@ loop_invariant (\forall int ii; 0 <= ii && ii < numberOfHalls);
		*/
		while(i < numberOfHalls)
		{
			int j = 0;
			int numberOfShows = hallList.get(i).GetShowListLenght();
			
			System.out.println("Hall " + i + ":");
			
			/*@
			@ loop_invariant j < numberOfHalls; 
			@ loop_invariant (\forall int jj; 0 <= jj && jj < numberOfShows);
			*/
			while(j < numberOfShows)
			{
				CountedSeats data = hallList.get(i).GetShow(j).CountSeats();
				
				System.out.println(" - Show " + j + " | " + data.reservedSeats + " seats reserved" + " | " + data.freeSeats + " seats free");
				System.out.print(data.consoleGraph);
				j++;
			}
			i++;
		}
	}
}
