package cinema_edb;

public interface ICinema {

	/* @ (* Creates a new hall object and adds it to hallList *);
	  @ pre hallList != null;
	  @
	  @ post hallList.size() == \old(hallList.size() + 1);
	  @
	  @ */
	public void CreateHall(int _rows, int _seats);
	
	/* @ (* Creates a new show in the hall in hallList based on index *);
	  @ pre _selectedHall >= 0 && _selectedHall =<hallList.size();
	  @
	  @ post hallList.get(selectedHall).CreateShow();
	  @ 
	  @ */
	public void CreateShow(int _selectedHall);
	
	/* @ (* Reserves seats at a given show in a given hall. If 0 are found the reservation function failed due to lag of space *);
	  @ pre _selectedHall >= 0 && _selectedHall =< hallList.size();
	  @ pre _selectedShow >= 0 && _selectedShow =< hallList.get(selectedHall).GetShowListLenght();
	  @ pre _seatsNeeded > 0 && _seatsNeeded <= hallList.get(selectedHall).GetSeatAmount();
	  @
	  @ post hallList.get(selectedHall).GetShow(selectedShow).FindSeats(seatsNeeded) >= 0;
	  @ post hallList.get(selectedHall).GetShow(selectedShow).ReserveSeats(foundSeats) <== foundSeats.size() >= 0;
	  @ 
	  @ */
	public void ReserveSeats(int _selectedHall, int _selectedShow, int _seatsNeeded);
	
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
	public /*@ pure @*/ void PrintSeatStatus();

}
