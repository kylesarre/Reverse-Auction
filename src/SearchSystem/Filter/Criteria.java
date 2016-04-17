package SearchSystem.Filter;

import SearchSystem.Auction;
import java.util.List;

/**
 * a contract for given criteria
 * @author Kyle
 */
public interface Criteria
{
    /**
     * returns a filtered list of auctions that meet a specified criteria
     * @param auctions
     * @return a list of filtered auctions
     */
    public List<Auction> meetCriteria(List<Auction> auctions);
}
