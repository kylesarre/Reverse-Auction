package ReverseAuctionSystem;

import application.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class Bid
{
	private int auctionId;
	private double price;
	private User user;
	private int id;

	// creates a new bid and writes it to a file
	public Bid(int auctionId, double price, User user) throws FileNotFoundException, IOException
	{
		if (price < getAuction(auctionId).getPriceMin())
		{
			// setPriceMin(auctionId, price);
			Auction auction = getAuction(auctionId);
			this.id = generateId();
			this.auctionId = auctionId;
			this.price = price;
			this.user = user;
			File ListFile = new File("./docs/BidList.txt");
			Scanner readListFile = new Scanner(ListFile);
			boolean BidIsOnFile = false;
			while (readListFile.hasNextInt())
			{
				if (readListFile.nextInt() == id)
				{
					BidIsOnFile = true;
				}
			}
			if (!BidIsOnFile)
			{
				// write to BidList
				FileWriter fileWriter = new FileWriter(new File("./docs/BidList.txt"), true);
				BufferedWriter buffWriter = new BufferedWriter(fileWriter);
				PrintWriter writeToList = new PrintWriter(buffWriter);
				writeToList.println(id);
				writeToList.close();
				// write the bid
				PrintWriter writeToFile = new PrintWriter(new File("./docs/Bids/" + id + ".txt"));
				writeToFile.println(auctionId);
				writeToFile.println(price);
				writeToFile.println(user.getUsername());
				writeToFile.close();
				// update User bids
				FileWriter fileWriter2 = new FileWriter(
						new File("./docs/userfiles/" + user.getUsername() + ".bids.txt"), true);
				BufferedWriter buffWriter2 = new BufferedWriter(fileWriter2);
				PrintWriter writeToUser = new PrintWriter(buffWriter2);
				writeToUser.println(auctionId + " " + price);
				writeToUser.close();
				// update auction file
				FileWriter fileWriter3 = new FileWriter(new File("./docs/Auctions/" + auctionId + ".txt"), true);
				BufferedWriter buffWriter3 = new BufferedWriter(fileWriter3);
				PrintWriter writeToAuction = new PrintWriter(buffWriter3);
				writeToAuction.println(String.format("%8d  %.2f", id, price));
				writeToAuction.close();
				setPriceMin(auctionId, price);

			}
		}
	}

	// creates a Bid that is already initialized
	public Bid(int id, int auctionId, double price, User user) throws FileNotFoundException, IOException
	{
		this.id = id;
		this.auctionId = auctionId;
		this.price = price;
		this.user = user;
	}

	// creates a new Id for a new Bid
	public int generateId() throws FileNotFoundException
	{
		File ListFile = new File("./docs/BidList.txt");
		Scanner readListFile = new Scanner(ListFile);
		int lastId = 0;
		while (readListFile.hasNextInt())
		{
			lastId = readListFile.nextInt();
		}
		return lastId + 1;
	}

	public void setPriceMin(int auctionId, double priceMin) throws FileNotFoundException, IOException
	{
		Auction auction = getAuction(auctionId);
		String replacementAuctionData = "";
		Scanner auctionScanner = new Scanner(new File("./docs/Auctions/" + auctionId + ".txt"));
		boolean priceMinSet = false;
		while (auctionScanner.hasNextLine())
		{
			if (auctionScanner.hasNextDouble())
			{
				double newDouble = auctionScanner.nextDouble();
				if (newDouble == auction.getPriceMin() && !priceMinSet)
				{
					replacementAuctionData = replacementAuctionData + priceMin + "%n";
					auctionScanner.nextLine();
					priceMinSet = true;
				} else
				{
					replacementAuctionData = replacementAuctionData + (int) newDouble + auctionScanner.nextLine()
							+ "%n";
				}
			} else
				replacementAuctionData = replacementAuctionData + auctionScanner.nextLine() + "%n";
		}
		System.out.println(String.format(replacementAuctionData));
		PrintWriter overrideOldAuction = new PrintWriter(new File("./docs/Auctions/" + auctionId + ".txt"));
		overrideOldAuction.print(String.format(replacementAuctionData));
		overrideOldAuction.close();
	}

	public Auction getAuction(int auctionId) throws FileNotFoundException, IOException
	{
		return new Auction(auctionId);
	}

	public double getPrice()
	{
		return price;
	}

	public int getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	// equality test
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		else if (this.getClass() != o.getClass())
			return false;
		else
		{
			Bid bid = (Bid) o;
			return user.equals(this.user) && (bid.price == this.price);
		}
	}

	// need to override hashcode as well for equality test
	@Override
	public int hashCode()
	{
		return user.hashCode() ^ (int) this.price;
	}
}