
package Tests;

import static org.junit.Assert.*;
import reverseauction.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;


public class UserTest {

	private Date alreadyDone;
	private Date neverEnds;

	@Before
	public void setupDates() throws ParseException {
		this.alreadyDone = neverEnds;
		this.neverEnds = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse("2017-07-16");
	}

	//Users can sell items.
	@Test
	public void testUserSellStandard() {

		User seller = new User("JohnS", "John Sellers", "Exxon");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item);

		assertEquals(1, seller.getAuctionList().size());

	}
	
	//Users cannot sell items if they are not set as sellers.
	@Test
	public void testUserSellNotSeller() {

		User seller = new User("JohnS", "John Sellers", "Exxon");
		Item item = new Item(0, "description");
		seller.setSeller(false);
		seller.sell(neverEnds, item);

		assertEquals(0, seller.getAuctionList().size());

	}

	//Users can bid on an offer.
	@Test
	public void testUserBidStandard() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		System.out.println(neverEnds.toString());
		seller.sell(neverEnds,item);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);

		buyer.bid(seller.getAuctionList().get(0), 10);
		assertEquals(1, buyer.getBidList().size());

	}

	//Users cannot bid on an unpublished auction.
	@Test
	public void testUserBidUnpublished() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item);

		buyer.bid(seller.getAuctionList().get(0), 10);
		assertEquals(0, buyer.getBidList().size());

	}
	
	//Users cannot bid on a cancelled auction.
	@Test
	public void testUserBidCancelled() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_CANCELED);

		buyer.bid(seller.getAuctionList().get(0), 10);
		assertEquals(0, buyer.getBidList().size());

	}
	
	//Users cannot bid on an finished auction.
	@Test
	public void testUserBidEnded() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(alreadyDone, item);

		buyer.bid(seller.getAuctionList().get(0), 10);
		assertEquals(0, buyer.getBidList().size());

	}

	//Users cannot bid under the minimum price.
	@Test
	public void testUserBidBelowMinimum() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item, 10, 20);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);

		buyer.bid(seller.getAuctionList().get(0), 9.99);
		assertEquals(0, buyer.getBidList().size());
		
	}

	//Users cannot bid if they are not set as buyers.
	@Test
	public void testUserBidNotBuyer() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);
		buyer.setBuyer(false);

		buyer.bid(seller.getAuctionList().get(0), 10);
		assertEquals(0, buyer.getBidList().size());
		
	}

	//Sellers receive an alerts when the guard price is reached.
	@Test
	public void testUserAlertReached() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item, 0, 10.01);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);
		buyer.bid(seller.getAuctionList().get(0), 10);

		assertEquals(1, seller.getAlertList().size());
		assertEquals(AlertType.RESERVE_PRICE_REACHED, seller.getAlertList().get(0).getType());

	}

	//Buyers receive an alert when an offer they bid on is cancelled.
	@Test
	public void testUserAlertCancelled() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);
		buyer.bid(seller.getAuctionList().get(0), 10);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_CANCELED);

		assertEquals(1, buyer.getAlertList().size());
		assertEquals(AlertType.AUCTION_CANCELLED, buyer.getAlertList().get(0).getType());

	}

	//Highest bidder is alerted when another buyer made a lower better offer.
	@Test
	public void testUserAlertLowerOffer() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		User buyer2 = new User("buyer2", "Bob Serviceman2", "STS");
		User buyer3 = new User("buyer3", "Jack Meoff", "S3");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item, 0, 100);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);

		buyer.bid(seller.getAuctionList().get(0), 10.01);
		buyer2.bid(seller.getAuctionList().get(0), 10);

		assertEquals(1, buyer.getAlertList().size());
		assertEquals(AlertType.LOWER_OFFER_HAPPENED, buyer.getAlertList().get(0).getType());

		buyer3.bid(seller.getAuctionList().get(0), 9.99);
		
		assertEquals(1, buyer.getAlertList().size());
		assertEquals(AlertType.LOWER_OFFER_HAPPENED, buyer.getAlertList().get(0).getType());
		assertEquals(1, buyer2.getAlertList().size());
		assertEquals(AlertType.LOWER_OFFER_HAPPENED, buyer2.getAlertList().get(0).getType());

	}

	//A user can disable some alerts.
	@Test
	public void testUserAlertLowerOfferDisabled() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		buyer.setActiveAlert(AlertType.LOWER_OFFER_HAPPENED, false);
		User buyer2 = new User("buyer2", "Bob Serviceman2", "STS");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item, 0, 100);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);

		buyer.bid(seller.getAuctionList().get(0), 10.02);
		buyer2.bid(seller.getAuctionList().get(0), 10.01);

		assertEquals(0, buyer.getAlertList().size());

	}

	//Multiple alerts at once.
	@Test
	public void testUserMultiAlert() {

		User seller = new User("seller", "John Sellers", "Exxon");
		User buyer = new User("buyer", "Jeff Serviceman", "Spartan");
		User buyer2 = new User("buyer2", "Bob Serviceman2", "STS");
		Item item = new Item(0, "description");
		seller.sell(neverEnds, item, 0, 10.01);
		seller.getAuctionList().get(0).setState(AuctionState.AUCTION_PUBLISHED);

		buyer.bid(seller.getAuctionList().get(0), 10.01);
		buyer2.bid(seller.getAuctionList().get(0), 10.00);

		assertEquals(1, seller.getAlertList().size());
		assertEquals(AlertType.RESERVE_PRICE_REACHED, seller.getAlertList().get(0).getType());

		assertEquals(1, buyer.getAlertList().size());
		assertEquals(AlertType.LOWER_OFFER_HAPPENED, buyer.getAlertList().get(0).getType());

	}

}