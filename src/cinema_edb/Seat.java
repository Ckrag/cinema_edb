package cinema_edb;

public class Seat implements ISeat{
	private int rowNr = 0;
	private int seatNr = 0;
	private boolean available = true;
	
	/* @ (* Returns the rowNr *);
	  @ 
	  @ pre rowNr != null;
	  @
	  @ post \result == rowNr;
	  @*/
	public /*@ pure @*/ int getRowNr() {
		return rowNr;
	}
	
	/* @ (* Sets the rowNr *);
	  @ 
	  @ pre rowNr != null;
	  @
	  @ post this.rowNr == rowNr;
	  @*/
	public void setRowNr(int rowNr) {
		this.rowNr = rowNr;
	}
	
	/* @ (* Returns the seatNr *);
	  @ 
	  @ pre seatNr != null;
	  @
	  @ post \result == seatNr;
	  @*/
	public /*@ pure @*/ int getSeatNr() {
		return seatNr;
	}
	
	/* @ (* Sets the seatNr *);
	  @ 
	  @ pre seatNr != null;
	  @
	  @ post this.seatNr == seatNr;
	  @*/
	public void setSeatNr(int seatNr) {
		this.seatNr = seatNr;
	}
	
	/* @ (* Returns the boolean value of available *);
	  @ 
	  @ pre available != null;
	  @
	  @ post \result == available;
	  @*/
	public /*@ pure @*/ boolean isAvailable() {
		return available;
	}
	
	/* @ (* Sets the boolean value of available *);
	  @ 
	  @ pre available != null;
	  @
	  @ post this.available == available;
	  @*/
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
