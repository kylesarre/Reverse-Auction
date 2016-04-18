package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class AndCriteria implements Criteria
{
    private final Criteria criteria1;
    private final Criteria criteria2;
    
    public AndCriteria(Criteria criteria1, Criteria criteria2)
    {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        List<Auction> firstCriteriaAuctions = criteria1.meetCriteria(auctions);
        return criteria2.meetCriteria(firstCriteriaAuctions);
    }  
}
