package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CriteriaRelevancy implements Criteria
{
    private double relevancy;
    
    public CriteriaRelevancy(double relevance)
    {
        relevancy = relevance;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> filteredAuctions = new ArrayList<>();
        for(Auction auction: auctions)
        {
            if(auction.getRelevance() >= relevancy)
            {
                filteredAuctions.add(auction);
            }
        }
        return filteredAuctions;
    }    
    public double getRelevancy()
    {
        return relevancy;
    }
    
}
