/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchSystem.Filter;

import java.util.ArrayList;
import java.util.List;
import ReverseAuctionSystem.Auction;
import application.User;

/**
 *
 * @author Kyle
 */
public class FilterByUserBids implements Criteria
{
    private User user;
    public FilterByUserBids(User user)
    {
        this.user = user;
    }
    @Override
    public List<Auction> meetCriteria(List<Auction> auctions)
    {
        {
		ArrayList<Auction> UserAuctions = new ArrayList<>();

		for (Auction a : auctions)
                {
                    if (a.findUserBid(user) != null)
                        UserAuctions.add(a);
		}

		return UserAuctions;
        }
    }
}
