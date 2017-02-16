package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaLocation implements Criteria
{
    private final String location;
    public CriteriaLocation(String location)
    {
        this.location = location;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> auctionByLoc = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getItem().getLocation().equals(location))
            {
                auctionByLoc.add(auction);
            }
        }
        return auctionByLoc;
    }
    
}
