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
 * filters a list of auctions to find what auctions a user has participated in
 * @author Kyle
 */
public class CriteriaUserBids implements Criteria
{
    private User user;
    public CriteriaUserBids(User user)
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
