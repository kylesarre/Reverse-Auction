package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaMinPrice implements Criteria
{
    private MinPrice minPrice;
    public CriteriaMinPrice(MinPrice minPrice)
    {
        this.minPrice = minPrice;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> filteredAuction = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getMinBid().getPrice() >= minPrice.getMinPrice())
            {
                filteredAuction.add(auction);
            }
        }
        return filteredAuction;   
    }
}
