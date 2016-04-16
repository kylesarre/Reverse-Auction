package SearchSystem.Filter;

import SearchSystem.Auction;
import SearchSystem.Name;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaName implements Criteria
{
    private String name;
    
    public CriteriaName(String name)
    {
        this.name = name;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> locationAuction = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getName().equals(name))
            {
                locationAuction.add(auction);
            }
        }
        return locationAuction;
    }  
}
