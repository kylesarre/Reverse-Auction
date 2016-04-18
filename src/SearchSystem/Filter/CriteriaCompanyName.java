package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import SearchSystem.Name;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaCompanyName implements Criteria
{
    private String companyName;
    
    public CriteriaCompanyName(String name)
    {
        this.companyName = name;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> locationAuction = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getSeller().getCompany().equals(companyName))
            {
                locationAuction.add(auction);
            }
        }
        return locationAuction;
    }  
}
