package SearchSystem.Sorter;

import SearchSystem.Auction;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * a custom defined comparator for sorting auctions by date
 * @author Kyle
 */
public class DateComparator implements Comparator<Auction>
{
    /**
     * compares the dates of auctions
     * @param auction1 the first auction to be compared
     * @param auction2 the second auction to be compared
     * @return -1 if auction1's date comes after auction2's date, 1 if
     * auction 1's date comes before auction2's date, and 0 if auction 1's date
     * equals auction2's date 
     */
    @Override
    public int compare(Auction auction1, Auction auction2) 
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = sdf.parse(auction1.getDate());
            Date date2 = sdf.parse(auction2.getDate());
            if(date1.after(date2))
            {
                return -1;
            }
            else if(date1.before(date2))
            {
                return 1;
            }
            else
                return 0;
        }
        catch(ParseException e)
        {
            System.err.println(e);
            System.err.println("Warning: a date parsing error has occurred. Dates cannot be compared.");
            System.err.println("Dates must fit the following format: dd-MM-yyyy");
            return 0;
        }
    }
    
}
