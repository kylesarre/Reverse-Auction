
package ReverseAuctionSystem.Utilities;

import ReverseAuctionSystem.Auction;

public class Alert {
	private Auction auction;
	private AlertType type;

	public Alert(Auction auction, AlertType type) {
		this.auction = auction;
		this.type = type;
	}

	public Auction getAuction() {
		return auction;
	}

	public AlertType getType() {
		return type;
	}
	
}
   
