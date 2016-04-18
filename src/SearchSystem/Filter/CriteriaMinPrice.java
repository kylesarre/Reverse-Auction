package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 * a criteria for a lower bound in terms of the minimal bid price 
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
            if(auction.getPriceMin() >= minPrice.getMinPrice())
            {
                filteredAuction.add(auction);
            }
        }
        return filteredAuction;   
    }
}
