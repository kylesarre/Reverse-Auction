
package reverseauction;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
	private Date auctionEnd;
	private ArrayList<Bid> allBids;
	private User seller;
	private Item item;
	private double priceMin, priceGuard;
	private AuctionState state;
	private boolean reservePriceReached;

	public Auction (Date auctionEnd, User user, Item item) {

		initDefault();
		this.auctionEnd = auctionEnd;
		seller = user;
		this.item = item;

	}

	public Auction (Date auctionEnd, User user, Item item, double priceMin, double priceGuard) {

		initDefault();
		this.auctionEnd = auctionEnd;
		seller = user;
		this.item = item;
		this.priceMin = priceMin;
		this.priceGuard = priceGuard;

	}
	
	private void initDefault(){

		allBids = new ArrayList<Bid>();
		priceMin = 0;
		priceGuard = -1;
		state = AuctionState.AUCTION_CREATED;
		reservePriceReached = false;

	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public User getSeller() {
		return seller;
	}

	public ArrayList<Bid> getAllBids() {
		return allBids;
	}

	public Item getItem() {
		return item;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public double getPriceGuard() {
		return priceGuard;
	}

	public AuctionState getState() {
		return state;
	}

	public boolean setState(AuctionState state) {

		if (state == AuctionState.AUCTION_CANCELED) {

			if(reservePriceReached)
				return false;
			
			// get all users
			ArrayList<User> users = this.findUsers();
			Alert alert = new Alert(this, AlertType.AUCTION_CANCELLED);
			
			for (User u : users)
				u.onAlert (alert);
		}
		
		this.state = state;
		
		return true;

	}

	//
	public boolean addBid (Bid bid) {
		Alert alert;

		// if auction is invalid
		// we can't bid on it
		if (!(state.equals(AuctionState.AUCTION_PUBLISHED)))
			return false;

		// it is not possible to bid over the minimum price
		if(bid.getPrice() < this.getPriceMin())
			return false;
		
		// seller cannot bid on his own auction
		if (bid.getUser().equals(this.seller))
			return false;

		// disable the auction if the end date is reached
		Date date = new Date();
		if(date.after(auctionEnd)){
			setState(AuctionState.AUCTION_ENDED);
			return false;
		}
		
		// if bid to add is higher than the last bid
		// we refuse it
		if (allBids.size() != 0)
			if (allBids.get(allBids.size() - 1).getPrice() <= bid.getPrice())
				return false;

		// check if bid already exist
		for (Bid b : allBids) {
			if (b.equals(bid)) {
				return false;
			}
		}

		// add bid
		allBids.add(bid);

		// send an alert to the previous best bidder
		int i = 1;
		while(
			allBids.size() > i &&
			allBids.get(allBids.size()-i).getUser().equals(bid.getUser())
		)
			i++;

		if(!allBids.get(allBids.size()-i).getUser().equals(bid.getUser())){
			alert = new Alert(this, AlertType.LOWER_OFFER_HAPPENED);
			allBids.get(allBids.size()-i).getUser().onAlert(alert);
		}

		// send an alert to the seller when the reserve price is reached
		if (
			!reservePriceReached && 
			bid.getPrice() <= this.getPriceGuard() &&
			this.getPriceGuard() != -1
		) {
			this.reservePriceReached = true;
			alert = new Alert(this, AlertType.RESERVE_PRICE_REACHED);
			this.seller.onAlert(alert);
		}

		// bid has been correctly added and alerts sent
		return true;
	}
	
	public void delBid (Bid bid) {
		for (Bid b : allBids) {
			if (b.equals(bid))
				allBids.remove(bid);
		}
	}
	
	public Bid findUserBid (User user) {
		Bid bid = null;

		for (Bid b : allBids) {
			if (b.getUser().equals(user)) {
				bid = b;
				break;
			}
		}

		return bid;
	}

	// find all users of an auction
	public ArrayList<User> findUsers () {
		ArrayList<User> Users = new ArrayList<User>();
		
		for (Bid b : allBids) {
			if (Users.contains(b.getUser()) == false) {
				Users.add(b.getUser());
			}
		}
		
		return Users;
	}
	
	// equality test
	@Override
	public boolean equals (Object o) {
		Auction auction = (Auction) o;
		
		if (auction.auctionEnd.equals(this.auctionEnd)
				&& auction.seller.equals(this.seller)
				&& auction.item.equals(this.item)
				&& (Double.compare(auction.priceMin, this.priceMin) == 0)
				&& (Double.compare(auction.priceGuard, this.priceGuard) == 0))
			return true;
		
		return false;
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return this.seller.hashCode() ^ this.item.hashCode() ^ this.auctionEnd.hashCode();
	}
}
