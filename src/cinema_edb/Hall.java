package cinema_edb;

import java.util.ArrayList;
import java.util.List;

public class Hall {
	private List<Show> showList = new ArrayList<Show>();
	
	public int GetShowListLenght()
	{
		return showList.size();
	}
	
	public Show GetShow(int _showListNr)
	{
		int showListnr = _showListNr;
		return showList.get(showListnr);
	}
	
	public void CreateShow()
	{
		Show aShow = new Show();
		showList.add(aShow);
	}
	
	public void RemoveShow(int index)
	{
		showList.remove(index);
	}
	
	public void RemoveAllShows()
	{
		showList.clear();
	}
}