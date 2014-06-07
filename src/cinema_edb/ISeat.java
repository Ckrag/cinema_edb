package cinema_edb;

public interface ISeat {

	/* @ (* Returns the rowNr *);
	  @ 
	  @ pre rowNr != null;
	  @
	  @ post \result == rowNr;
	  @*/
	public /*@ pure @*/ int getRowNr();
	
	/* @ (* Sets the rowNr *);
	  @ 
	  @ pre rowNr != null;
	  @
	  @ post this.rowNr == rowNr;
	  @*/
	public void setRowNr(int rowNr);
	
	/* @ (* Returns the seatNr *);
	  @ 
	  @ pre seatNr != null;
	  @
	  @ post \result == seatNr;
	  @*/
	public /*@ pure @*/ int getSeatNr();
	
	/* @ (* Sets the seatNr *);
	  @ 
	  @ pre seatNr != null;
	  @
	  @ post this.seatNr == seatNr;
	  @*/
	public void setSeatNr(int seatNr);
	
	/* @ (* Returns the boolean value of available *);
	  @ 
	  @ pre available != null;
	  @
	  @ post \result == available;
	  @*/
	public /*@ pure @*/ boolean isAvailable();
	
	/* @ (* Sets the boolean value of available *);
	  @ 
	  @ pre available != null;
	  @
	  @ post this.available == available;
	  @*/
	public void setAvailable(boolean available);
}
