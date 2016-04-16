package SearchSystem.Filter;

import SearchSystem.Auction;
import SearchSystem.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaLocation implements Criteria
{
    private final Location location;
    public CriteriaLocation(Location location)
    {
        this.location = location;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> auctionByLoc = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getLocation().equals(location.getLocation()))
            {;
                auctionByLoc.add(auction);
            }
        }
        return auctionByLoc;
    }
    
}
