
package reverseauction;

public class Bid {

	private double price;	
	private User user;

	public Bid(double price, User user) {
		this.price = price;
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public User getUser() {
		return user;
	}
	
	// equality test
	@Override
	public boolean equals (Object o) {
		Bid bid = (Bid) o;
		
		if (bid.user.equals(this.user) 
				&& (Double.compare(bid.getPrice(), this.price) == 0))
			return true;
		
		return false;
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return user.hashCode() ^ (int) this.price;
	}
}