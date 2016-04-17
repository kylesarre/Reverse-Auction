
package reverseauction;

import java.util.ArrayList;


public class AuctionModel {
	ArrayList<Auction> auctions;

	public AuctionModel() {
	}
	
	// get all user auctions
	ArrayList<Auction> searchUserAuctions (User user) {
		ArrayList<Auction> UserAuctions = new ArrayList<Auction>();
		
		for (Auction a : auctions) {
			if (a.findUserBid(user) != null)
				UserAuctions.add(a);
		}
		
		return UserAuctions;
	}
}