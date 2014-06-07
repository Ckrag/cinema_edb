package cinema_edb;

public interface IHall {

	/* @ (* Returns the number of seats at a hall *);
	  @ pre rows != null;
	  @ pre seats != null;
	  @ 
	  @ post \result == rows*seats;
	  @
	  @ */
	public /*@ pure @*/ int GetSeatAmount();
	
	/* @ (* Returns the length of showlist *);
	  @ pre showList != null;
	  @ 
	  @ post \result == showList.size();
	  @
	  @ */
	public /*@ pure @*/ int GetShowListLenght();
	
	/* @ (* Returns a specific show from showList based on index *);
	  @ pre showList != null;
	  @ pre _showListNr > 0 && _showListNr < showList.Size(); 
	  @
	  @ post \result == showList.get(showListnr);
	  @
	  @ */
	public /*@ pure @*/ Show GetShow(int _showListNr);
	
	/* @ (* Creates a new show object and adds it to the showList *);
	  @ pre showList != null;
	  @
	  @ post showList.size() == \old(showList.size() + 1);
	  @
	  @ */
	public void CreateShow();
	
	/* @ (* Removes a specific show from showList based on index *);
	  @ pre showList != null;
	  @ pre index >= 0 && showList.Size() >= index + 1; 
	  @
	  @ post showList.size() == \old(showList.Size() - 1);
	  @
	  @ */
	public void RemoveShow(int index);
	
	/* @ (* Removes all shows from showList *);
	  @ pre showList != null;
	  @
	  @ post \result == (showList.size() == 0);
	  @
	  @ */
	public void RemoveAllShows();
}
