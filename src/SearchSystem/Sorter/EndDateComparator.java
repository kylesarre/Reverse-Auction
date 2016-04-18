package SearchSystem.Sorter;

import ReverseAuctionSystem.Auction;
import java.util.Comparator;
import java.util.Date;
/**
 * a custom defined comparator for sorting auctions by date
 * @author Kyle
 */
public class EndDateComparator implements Comparator<Auction>
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
            Date date1 = auction1.getAuctionEnd();
            Date date2 = auction2.getAuctionEnd();
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
    
}
