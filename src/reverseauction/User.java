
package reverseauction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class User implements AlertListener{

	private String login;
	private String name;
	private String company;
	private boolean isBuyer;
	private boolean isSeller;
	private ArrayList<Bid> bidList;
	private ArrayList<Auction> auctionList;
	private Map<AlertType, Boolean> activeAlerts;
	private ArrayList<Alert> alertList;

	public User(String login, String name, String company) {

		this.login = login;
		this.name = name;
		this.company = company;
		this.isBuyer = true;
		this.isSeller = true;
		this.bidList = new ArrayList<Bid>();
		this.auctionList = new ArrayList<Auction>();
		this.activeAlerts = new HashMap<AlertType, Boolean>();
		this.alertList = new ArrayList<Alert>();

		for(AlertType at : AlertType.values())
			activeAlerts.put(at, true);

	}

	public void onAlert(Alert alert){
		if(activeAlerts.get(alert.getType()) == true)
			alertList.add(alert);
	}

	public ArrayList<Bid> getBidList() {
		return bidList;
	}

	public ArrayList<Auction> getAuctionList() {
		return auctionList;
	}

	public Map<AlertType, Boolean> getActiveAlerts() {
		return activeAlerts;
	}

	public ArrayList<Alert> getAlertList() {
		return alertList;
	}
	
	public void sell(Date auctionEnd, Item item){

		if(isSeller)
			auctionList.add(new Auction(auctionEnd, this, item));

	}
	
	public void sell(Date auctionEnd, Item item, double priceMin, double priceGuard){

		if(isSeller)
			auctionList.add(new Auction(auctionEnd, this, item, priceMin, priceGuard));

	}

	public boolean cancelAuction(Auction auction){

		if(auctionList.contains(auction))
			return auction.setState(AuctionState.AUCTION_CANCELED);
		return false;
	}
	
	public void bid(Auction auction, double amount) {

		if(isBuyer){

			Bid bid = new Bid(amount, this);
			if(auction.addBid(bid))
				bidList.add(bid);
		
		}

	}

	public void startAuction(Date auctionEnd, Item item){
		
		if(isSeller)
			auctionList.add(new Auction(auctionEnd, this, item));

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isBuyer() {
		return isBuyer;
	}

	public void setBuyer(boolean isBuyer) {
		this.isBuyer = isBuyer;
	}

	public boolean isSeller() {
		return isSeller;
	}

	public void setSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}

	public void setActiveAlert(AlertType type, Boolean state){
		activeAlerts.put(type, state);
	}
	
	// equality test
	@Override
	public boolean equals (Object o) {
		User u = (User) o;

		if (u.getLogin() == this.getLogin()
				&& u.getName() == this.getName()
				&& u.getCompany() == this.getCompany())
			return true;

		return false;
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		String str;
		
		str = this.login + this.name + this.company;
		
		return str.hashCode();
	}
}