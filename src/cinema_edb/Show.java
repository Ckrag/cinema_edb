package cinema_edb;
 
import java.util.ArrayList;
import java.util.List;

public class Show implements IShow{
	
	private Seat[][] seatCollection;

	public Show(int rows, int seats)
	{
		seatCollection = new Seat[rows][seats];
		NumerateSeats();
	}
	
	
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
	  											seatCollection[i][j].available == true;););	
  	  @
  	  @ (* antallet af pladser hvor available = true * );
	  @	post \result.reservedSeats == 	\(forall int i; 0 <= i && i < rowNr;
	  										\(num_of int j; 0 <= j && j < rowLength; 
	  											seatCollection[i][j].available != true;););																
	  @
	  @ post \result.consoleGraph != null;
	  @
	  @ */
	public /*@ pure @*/ CountedSeats CountSeats()
	{
		CountedSeats data = new CountedSeats();
		
		Seat[][] collection = seatCollection;
		int rowNr = collection.length;
		int rowLength = collection[0].length;
		int i = 0;
		
		String overview = "";
		
		/*@ 
		@ loop_invariant (\forall int ii; (0 <= ii & ii < collection.length) ==> collection[ii] != null);
		@ loop_invariant collection != null;
		*/
		while(i < rowNr)
		{
			int j = 0;
			
			/*@ 
			@ loop_invariant (\forall int jj; (0 <= jj & jj < collection[i].length) ==> collection[i][jj] != null);
			@ loop_invariant collection[i] != null;
			*/
			while(j < rowLength)
			{
				if(seatCollection[i][j].available)
				{
					data.freeSeats++;
					overview += "[-]";
				}
				else
				{
					data.reservedSeats++;
					overview += "[+]";
				}
				j++;
			}
			overview += "\n";
			i++;
		}
		data.consoleGraph = overview;
		
		return data;
	}
	
	/* @ (* Creates a list of ints sorted from smallest to largest with unevens on the left side and evens on the right *);
	  @ 
	  @ pre _size > 0
	  @
	  @ (* Listen er opbygget med ulige tal først og lige tal til sidst *);
	  @ post \result == (\forall int i; 0 < i && i < size;  i % 2 != 0 ==> sortedList.get(i) == i);
	  @ post \result ==	(\forall int i; 0 < i && i < size;  i % 2 == 0 ==> sortedList.get(i) == i);
	  @ 
	  @ */
	private /*@ pure @*/ List<Integer> SortSeatnumbers(int _size)
	{
		int size = _size + 1;
		List<Integer> sortedList = new ArrayList<Integer>();
		int i = 1;
		
		/*@
		@ loop_invariant sortedList != null;
		@ loop_invariant (\forall int ii; 0 < ii && ii < size; ii % 2 != 0 ==> sortedList.get(ii) == ii);
		*/
		while(i < size)
		{
			if(i % 2 != 0)
			{
				sortedList.add(i);
			}
			i += 1;
		}
		
		
		i = 1;
		/*@ 
		@ loop_invariant sortedList != null;
		@ loop_invariant (\forall int ii; 0 < ii && ii < size; ii % 2 == 0 ==> sortedList.get(ii) == ii);
		*/
		while(i < size)
		{
			if(i % 2 == 0)
			{
				sortedList.add(i);
			}
			i += 1;
		}

		return sortedList;
	}
	
	/* @ (* Numerates the seats in the seatCollection based on the SortSeatNumbers() function *);
	  @ 
	  @ pre seatCollection != null;
	  @ pre seatCollection.size() > 0;
	  @ pre seatCollection[0].size() > 0;
	  @
	  @ assignable seatCollection;
	  @
	  @	post \result == 	\(forall int i; 0 <= i && i < numberOfRows;
									\(forall int j; 0 <= j && j < numberOfSeatsOnRow; 
											seatCollection[i][j] != null;
											seatCollection[i][j].rowNr = i + 1;
											seatCollection[i][j].searNr = seatNumbers.get(j););		
											
	  @ 
	  @ */
	private void NumerateSeats()
	{
		int numberOfRows = seatCollection.length;
		int numberOfSeatsOnRow = seatCollection[0].length;
		int i = 0;
		
		List<Integer> seatNumbers = SortSeatnumbers(numberOfSeatsOnRow); //{1,3,5,7,9,2,4,6,8,10};
		
		/*@
		@ loop_invariant seatCollection != null;
		@ loop_invariant (\forall int ii; 0 <= ii && ii < numberOfRows);
		*/
		while(i < numberOfRows)
		{
			int j = 0;
			
			/*@
			@ loop_invariant seatCollection != null;
			@ loop_invariant (\forall int jj; 0 <= jj && jj < numberOfSeatsOnRow; 
											seatCollection[i][j] != null && 
											seatCollection[i][j].rowNr == i + 1 &&
											seatCollection[i][j].seatNr == seatNumbers.get(j));
			*/
			while(j < numberOfSeatsOnRow)
			{
				seatCollection[i][j] = new Seat();
				seatCollection[i][j].rowNr = i + 1;
				seatCollection[i][j].seatNr = seatNumbers.get(j);
				j += 1;
			}
			i += 1;
		}
	}
	
	/* @ (* Reserves seats found in a given list. Seats are guaranteed to be free from the FindSeats() function *);
	  @ 
	  @ pre _wantedSeats != null;
	  @ pre seatCollection != null;
	  @ 
	  @ post (\forall int ii; 0 <= ii && ii < nrOfWantedSeats; 
	  							seatCollection[wantedSeats.get(i).rowNr - 1][seatCount].available == false &&  
	  							seatCount == \old(seatCount) + 1;
	  @ 
	  @*/
	public void ReserveSeats(List<Seat> _wantedSeats)
	{
		List<Seat> wantedSeats = _wantedSeats;
		int nrOfWantedSeats = wantedSeats.size();
		
		int i = 0;
		
		/*@
		@ loop_invariant (\forall int ii; 0 <= ii && ii < nrOfWantedSeats; 
							seatCollection[wantedSeats.get(ii).rowNr - 1][GetIndexFromSeatNr(wantedSeats.get(ii).rowNr - 1, wantedSeats.get(ii).seatNr)].available == false);
		*/
		while(i < nrOfWantedSeats)
		{
			/*
			 * for each seat, it finds the rowNr and seatNr of the
			 * seat and then goes directly into the seatCollection to set availability
			 */
			int rowNr = wantedSeats.get(i).rowNr - 1; //-1 da rowNr starter fra 1 men listen starter fra 0 (indexering)
			int seatNr = GetIndexFromSeatNr(rowNr, wantedSeats.get(i).seatNr);
			seatCollection[rowNr][seatNr].available = false;
			
			i += 1;
		}
	}
	
	/* @ (* Returns the index-value of a Seatnumber based on the row of the seat *);
	  @ 
	  @ pre seatCollection != null;
	  @ pre wantedRow > 0 && wantedRow < seatCollection.length;
	  @
	  @ post \result == seatCollection[wantedRow][i].seatNr == wantedSeatNr
	  @
	  @ */
	private /*@ pure @*/ int GetIndexFromSeatNr(int wantedRow, int wantedSeatNr)
	{
		int index = 0;
		int rowLength = seatCollection[wantedRow].length;
		int i = 0;
		
		/*@
		@ loop_invariant (\forall int ii; 0 <= ii && ii < seatCollection[wantedRow].length);
		@*/
		while(i < rowLength)
		{
			if(seatCollection[wantedRow][i].seatNr == wantedSeatNr)
			{
				index = i;
			}
			i++;
		}
		return index;
	}
	
	
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
	public /*@ pure @*/ List<Seat> FindSeats(int _seatsNeeded)
	{		
		List<Seat> foundSeats = new ArrayList<Seat>();
		int seatsNeeded = _seatsNeeded;

		if (FindConnectedSeats(seatsNeeded).size() > 0)
		{
			return FindConnectedSeats(seatsNeeded);		}
		else
		{	
			return FindAnySeats(seatsNeeded);
		}
	}
	
	/* @ (* Checks that the amount of wanted seats are available NEXT TO EACHOTHER and returns a list containing a reference to those seats.
	 *  if unable to find the wanted amount of seats in succession it will return a list with a length of 0. *);
	  @ 
	  @ pre _seatsNeeded > 0;
	  @ pre seatCollection != null;
	  @ pre seatCollection.length > 0;
	  @ 
	  @ post 	(foundSeats.size() == seatsNeeded ==> \result == (\forall int ii; 0 <= ii && ii < rowNr;
	  						 										(\num_of int jj; 0 <= jj && jj < rowLength;
	  																		seatCollection[i][j].available == true && seatCollection[i][j+1].available == true || \old(seatCollection[i][j].available == true))))
	  																		||
	  			(foundSeats.size() != seatsNeeded ==> \result == foundSeats.size() == 0);
	  @
	  @*/
	private /*@ pure @*/ List<Seat>FindConnectedSeats(int _seatsNeeded)
	{
		List<Seat> foundSeats = new ArrayList<Seat>();
		
		int seatsNeeded = _seatsNeeded;
		int rowNr = seatCollection.length;
		int rowLength = seatCollection[0].length;
		int i = 0;
		
		/*@
		@ loop_invariant (\forall int ii; 0 <= ii && ii < rowNr);
		*/
		while(i < rowNr)
		{
			int j = 0;
			
			/*@
			@ loop_invariant foundSeats.size() >= 0;
			@ loop_invariant (\forall int jj; 0 <= jj && jj < rowLength);
			@ 
			*/
			while(j < rowLength)
			{
				if (seatCollection[i][j].available)
				{
					foundSeats.add(seatCollection[i][j]);
					
					if (foundSeats.size() == seatsNeeded)
					{
						return foundSeats;
					}
				} 
				else
				{
					foundSeats.clear();
				}
				j += 1;
			}
			i += 1;
			foundSeats.clear();
		}
		foundSeats.clear();
		
		return foundSeats;
	}
	
	/* @ (* Checks that the amount of wanted seats are available ANYWHERE and returns a list containing a reference to those seats.
	 *  if unable to find the wanted amount of seats it will return a list with a length of 0. *);
	  @ 
	  @ pre _seatsNeeded > 0;
	  @ pre seatCollection != null;
	  @ pre seatCollection.length > 0;
	  @ 
	  @ post 	(foundSeats.size() == seatsNeeded ==> \result == (\forall int ii; 0 <= ii && ii < rowNr;
	  						 										(\num_of int jj; 0 <= jj && jj < rowLength;
	  																		seatCollection[i][j].available)))
	  																		||
	  			(foundSeats.size() != seatsNeeded ==> \result == foundSeats.size() == 0);
	  @
	  @*/
	private /*@ pure @*/ List<Seat>FindAnySeats(int _seatsNeeded)
	{
		List<Seat> foundSeats = new ArrayList<Seat>();
		
		int seatsNeeded = _seatsNeeded;
		int rowNr = seatCollection.length;
		int rowLength = seatCollection[0].length;
		int i = 0;
		
		/*@
		@ loop_invariant (\forall int ii; 0 <= ii && ii < rowNr);
		*/
		while(i < rowNr)
		{
			int j = 0;
			
			/*@
			@ loop_invariant foundSeats.size() >= 0;
			@ loop_invariant (\forall int jj; 0 <= jj && jj < rowLength);
			@ 
			*/
			while(j < rowLength)
			{
				if (seatCollection[i][j].available)
				{
					foundSeats.add(seatCollection[i][j]);
					
					if (foundSeats.size() == seatsNeeded)
					{
						return foundSeats;
					}
				} 
				j += 1;
			}
		i += 1;
		}
	foundSeats.clear();
	return foundSeats;
	}
}
