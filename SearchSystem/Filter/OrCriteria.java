package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class OrCriteria implements Criteria
{
    private final Criteria criteria1;
    private final Criteria criteria2;
    
    public OrCriteria(Criteria criteria1, Criteria criteria2)
    {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }
    
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions) 
    {
        // construct collection A and collection B.
        List<Auction> firstCriteriaItems = criteria1.meetCriteria(auctions);
        List<Auction> otherCriteriaItems = criteria2.meetCriteria(auctions);
        // if an item b is in Collection B but is not in Collection A,
        // add item b to collection A. This yields unique elements b and a
        // in the new collection C.
        for (Auction auction : otherCriteriaItems) 
        {
            if(!firstCriteriaItems.contains(auction))
            {
                firstCriteriaItems.add(auction);
            }
        }	
        return firstCriteriaItems;
    }
}
