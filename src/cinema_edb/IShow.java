package cinema_edb;

import java.util.List;

public interface IShow {

	/* @ (* Returns a CountedSeats object containing information about seats status of a show *);
	  @ 
	  @ pre seatCollection != null;
	  @ pre seatCollection.size() >= 1;
	  @ pre seatCollection[0].size() >= 1;
	  @
	  @ 
	  @ (* antallet af pladser hvor available = false * );
	  @ POST \result.freeSeats ==	\(forall int i; 0 <= i && i < rowNr;
	  										\(num_of int j; 0 <= j && j < rowLength; 
	  											seatCollection[i][j].isAvailable() == true;););	
	  @
	  @ (* antallet af pladser hvor available = true * );
	  @	post \result.reservedSeats == 	\(forall int i; 0 <= i && i < rowNr;
	  										\(num_of int j; 0 <= j && j < rowLength; 
	  											seatCollection[i][j].isAvailable() != true;););																
	  @
	  @ post \result.consoleGraph != null;
	  @
	  @ */
	public /*@ pure @*/ CountedSeats CountSeats();
	
	/* @ (* Reserves seats found in a given list. Seats are guaranteed to be free from the FindSeats() function *);
	  @ 
	  @ pre _wantedSeats != null;
	  @ pre seatCollection != null;
	  @ 
	  @ post (\forall int ii; 0 <= ii && ii < nrOfWantedSeats; 
					seatCollection[wantedSeats.get(i).getRowNr() - 1][GetIndexFromSeatNr(wantedSeats.get(i).getRowNr() - 1, wantedSeats.get(i).getSeatNr())].isAvailable() == false &&
					seatCount == \old(seatCount) + 1;
	  @ 
	  @*/
	public void ReserveSeats(List<Seat> _wantedSeats);
	
	/* @ (* Checks if the amount of wanted seats are available. Returns either (according to priority: 
	 * 	- a list of seats with everyone next to eachother.
	 *  - a list of seats with everyone seated where space was found.
	 *  - en empty list, signalring that no seats are availible. *);
	  @ 
	  @ pre _seatsNeeded > 0;
	  @ 
	  @ post \results == FindConnectedSeats(seatsNeeded).size() > 0 ==> FindConnectedSeats(seatsNeeded) || FindAnySeats(seatsNeeded);
	  @
	  @*/
	public /*@ pure @*/ List<Seat> FindSeats(int _seatsNeeded);

}
