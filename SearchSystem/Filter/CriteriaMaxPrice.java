package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaMaxPrice implements Criteria
{
    private MaxPrice maxPrice;
    public CriteriaMaxPrice(MaxPrice maxPrice)
    {
        this.maxPrice = maxPrice;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> filteredAuction = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getMinBid().getPrice() <= maxPrice.getMaxPrice())
            {
                filteredAuction.add(auction);
            }
        }
        return filteredAuction;
    }
    
}
