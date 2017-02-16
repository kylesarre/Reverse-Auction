package SearchSystem.Sorter;

import ReverseAuctionSystem.Auction;
import java.util.Comparator;

/**
 * a custom defined comparator for sorting auctions by relevance
 * @author Kyle
 */
public class RelevancyComparator implements Comparator<Auction>
{
    /**
     * compares two auctions based on relevance
     * @param auction1 the first Auction to be compared
     * @param auction2 the second auction to be compared
     * @return a negative number if o1 is bigger than o2, 0 if o1 and o2 are the same
     * and a positive number if o2 is bigger than o1
     */
    @Override
    public int compare(Auction auction1, Auction auction2) 
    {
        if(auction1.getRelevancy() - auction2.getRelevancy() < 0)
            return 1;
        else if(auction1.getRelevancy() - auction2.getRelevancy() > 0)
            return -1;
        else
            return 0;
    } 
}
