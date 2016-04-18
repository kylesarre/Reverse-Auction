package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 * a criteria for an upper bound in terms of a minimal bid price
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
            if(auction.getPriceMin() <= maxPrice.getMaxPrice())
            {
                filteredAuction.add(auction);
            }
        }
        return filteredAuction;
    }
    
}
