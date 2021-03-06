package cinema_edb;

import java.util.ArrayList;
import java.util.List;

public class Hall implements IHall {
	
	private int rows;
	private int seats;
	
	public Hall(int _rows, int _seats)
	{
		rows = _rows;
		seats = _seats;
	}
	
	private List<Show> showList = new ArrayList<Show>();
	
	/* @ (* Returns the number of seats at a hall *);
	  @ pre rows != null;
	  @ pre seats != null;
	  @ 
	  @ post \result == rows*seats;
	  @
	  @ */
	public /*@ pure @*/ int GetSeatAmount()
	{
		int numberOfSeats = rows*seats;
		return numberOfSeats;
	}
	
	
	/* @ (* Returns the length of showlist *);
	  @ pre showList != null;
	  @ 
	  @ post \result == showList.size();
	  @
	  @ */
	public /*@ pure @*/ int GetShowListLenght()
	{
		return showList.size();
	}
	
	/* @ (* Returns a specific show from showList based on index *);
	  @ pre showList != null;
	  @ pre _showListNr > 0 && _showListNr < showList.Size(); 
	  @
	  @ post \result == showList.get(showListnr);
	  @
	  @ */
	public /*@ pure @*/ Show GetShow(int _showListNr)
	{
		int showListnr = _showListNr;
		return showList.get(showListnr);
	}
	
	/* @ (* Creates a new show object and adds it to the showList *);
	  @ pre showList != null;
	  @
	  @ post showList.size() == \old(showList.size() + 1);
	  @
	  @ */
	public void CreateShow()
	{
		Show aShow = new Show(rows,seats);
		showList.add(aShow);
	}
	
	/* @ (* Removes a specific show from showList based on index *);
	  @ pre showList != null;
	  @ pre index >= 0 && showList.Size() >= index + 1; 
	  @
	  @ post showList.size() == \old(showList.Size() - 1);
	  @
	  @ */
	public void RemoveShow(int index)
	{
		showList.remove(index);
	}
	
	/* @ (* Removes all shows from showList *);
	  @ pre showList != null;
	  @
	  @ post \result == (showList.size() == 0);
	  @
	  @ */
	public void RemoveAllShows()
	{
		showList.clear();
	}
}
