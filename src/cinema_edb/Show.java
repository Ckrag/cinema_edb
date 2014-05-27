package cinema_edb;

import java.util.ArrayList;
import java.util.List;

public class Show {
	
	private Seat[][] seatCollection;

	public Show(int rows, int seats)
	{
		seatCollection = new Seat[rows][seats];
		NumerateSeats();
	}
	
	public CountedSeats CountSeats()
	{
		CountedSeats data = new CountedSeats();
		
		int rowNr = seatCollection.length;
		int rowLength = seatCollection[0].length;
		int i = 0;
		
		String overview = "";
		
		while(i < rowNr)
		{
			int j = 0;
			
			while(j < rowLength)
			{
				if(seatCollection[i][j].availible)
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
	
	private List<Integer> SortSeatnumbers(int _size)
	{
		int size = _size;
		List<Integer> sortedList = new ArrayList<Integer>();
		int i = 0;
		
		while(i < size)
		{
			if(i % 2 == 0)
			{
				sortedList.add(i);
			}
			i += 1;
		}
		
		i = 0;
		while(i < size)
		{
			if(i % 2 != 0)
			{
				sortedList.add(i);
			}
			i += 1;
		}
		
		return sortedList;
	}
	
	private void NumerateSeats()
	{
		int numberOfRows = seatCollection.length;
		int numberOfSeatsOnRow = seatCollection[0].length;
		int i = 0;
		
		List<Integer> seatNumbers = SortSeatnumbers(numberOfSeatsOnRow); //{1,3,5,7,9,2,4,6,8,10};
		
		while(i < numberOfRows)
		{
			int j = 0;
			
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
	
	public Boolean ReserveSeats(List<Seat> _wantedSeats)
	{
		boolean reservationSuccessful = false;
		List<Seat> wantedSeats = _wantedSeats;
		int nrOfWantedSeats = wantedSeats.size();
		
		int seatCount = 0;
		int i = 0;
		
		while(i < nrOfWantedSeats)
		{
			int rowNr = wantedSeats.get(i).rowNr - 1;
			seatCollection[rowNr][seatCount].availible = false;
	
			seatCount += 1;
			i += 1;
			
			if(seatCount==seatCollection[0].length)
			{
				seatCount = 0;
			}
		}
		
		return reservationSuccessful;
	}
	
	public List<Seat> FindSeats(int _seatsNeeded)
	{		
		List<Seat> foundSeats = new ArrayList<>();
		
		int seatsNeeded = _seatsNeeded;
		int rowNr = seatCollection.length;
		int rowLength = seatCollection[0].length;
		int i = 0;
		int seatsFound = 0;
		
		while(i < rowNr)
		{
			int j = 0;
			
			while(j < rowLength)
			{
				if (seatCollection[i][j].availible)
				{
					foundSeats.add(seatCollection[i][j]);
					seatsFound++;
					
					if (seatsFound == seatsNeeded)
					{
						return foundSeats;
					}
				} 
				else
				{
					seatsFound = 0;
					foundSeats.clear();
				}
				j += 1;
			}
			i += 1;
		}
		
		i = 0;
		
		if (seatsFound == 0)
		{
			while(i < rowNr)
			{
				int j = 0;
				
				while(j < rowLength)
				{
					if (seatCollection[i][j].availible)
					{
						foundSeats.add(seatCollection[i][j]);
						seatsFound++;
						
						if (seatsFound == seatsNeeded)
						{
							return foundSeats;
						}
					} 
					j += 1;
				}
			}
			i += 1;
		}
		foundSeats.clear();
		return foundSeats;
	}
}
