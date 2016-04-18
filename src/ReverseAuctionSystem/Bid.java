package ReverseAuctionSystem;

/**
 *
 * @author Alex
 */
public class Bid 
{

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
	public boolean equals (Object o) 
        {
            if(o == null)
                return false;
            else if(this.getClass() != o.getClass())
                return false;
            else
            {
                Bid bid = (Bid)o;
                return user.equals(this.user) 
                && (bid.price == this.price);
            }
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return user.hashCode() ^ (int) this.price;
	}
}