package ReverseAuctionSystem;

import ReverseAuctionSystem.Utilities.AlertType;
import ReverseAuctionSystem.Utilities.Alert;
import SearchSystem.Sorter.ListSorter;
import application.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Auction 
{
	private Date auctionEnd;
	private ArrayList<Bid> allBids;
	private User seller;
	private Item item;
	private double priceMin, priceGuard;
	private AuctionState state;
	private boolean reservePriceReached;
        private double relevancy;
        private int id;

        //creates a new auction and writes it to a file
	public Auction (Date auctionEnd, User user, Item item, double priceMin, double priceGuard) throws FileNotFoundException, IOException
        {
		initDefault();
                this.id = generateId();
		this.auctionEnd = auctionEnd;
		seller = user;
		this.item = item;
		this.priceMin = priceMin;
		this.priceGuard = priceGuard;
                File ListFile = new File("./docs/AuctionList.txt");
                Scanner readListFile = new Scanner(ListFile);
                boolean auctionIsOnFile = false;
                while(readListFile.hasNextInt())
                {
                    if(readListFile.nextInt() == getId())
                    {
                        auctionIsOnFile = true;
                    }
                    
                }
                if(!auctionIsOnFile)
                {
                    //write to auction list
                    FileWriter fileWriter = new FileWriter(new File("./docs/AuctionList.txt"), true);
                    BufferedWriter buffWriter = new BufferedWriter(fileWriter);
                    PrintWriter writeToList = new PrintWriter(buffWriter);
                    writeToList.println(getId());
                    writeToList.close();
                    //
                    PrintWriter writeToFile = new PrintWriter(new File("./docs/Auctions/" + getId() + ".txt"));
                    int auctionYear = auctionEnd.getYear() + 1900;
                    writeToFile.println(auctionEnd.getMonth() + " " + auctionEnd.getDay() + " " + auctionYear);
                    writeToFile.println(user.getUsername());
                    writeToFile.println(item.getLocation());
                    writeToFile.println(priceMin);
                    writeToFile.println(priceGuard);
                    writeToFile.println(getId());
                    writeToFile.println();
                    writeToFile.println(item.getDescription());
                    writeToFile.println();
                    writeToFile.println("Bids");
                    writeToFile.close();
                    //
                    FileWriter fileWriter2 = new FileWriter(new File("./docs/userfiles/"+user.getUsername()+".auctions.txt"), true);
                    BufferedWriter buffWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter writeToUser = new PrintWriter(buffWriter2);
                    writeToUser.println(getId());
                    writeToUser.close();
                }
	}
        
        public Auction (int id, Date auctionEnd, User user, Item item, double priceMin, double priceGuard) throws FileNotFoundException, IOException
        {

		initDefault();
                this.id=id;
		this.auctionEnd = auctionEnd;
		seller = user;
		this.item = item;
		this.priceMin = priceMin;
		this.priceGuard = priceGuard;
	}
        
        public Auction(int auctionId) throws FileNotFoundException
        {
            File AuctionFile = new File("./docs/Auctions/"+auctionId+".txt");
            Scanner readAuctionFile = new Scanner(AuctionFile);
            int month = readAuctionFile.nextInt();
            int day = readAuctionFile.nextInt();
            int year = readAuctionFile.nextInt();
            this.auctionEnd = new Date(year-1900, month-1, day);
            this.seller = new User(readAuctionFile.next());
            readAuctionFile.nextLine();
            String location = readAuctionFile.nextLine();
            this.priceMin = readAuctionFile.nextDouble();
            this.priceGuard = readAuctionFile.nextDouble();
            String description = "";
            readAuctionFile.nextLine();
            while(readAuctionFile.hasNextLine() && !readAuctionFile.next().equals("Bids:"))
            {
                description = description + readAuctionFile.nextLine() + "\n";
            }
            this.item = new Item(String.format(description), location);
        }
	
        public int generateId() throws FileNotFoundException
        {
            File ListFile = new File("./docs/AuctionList.txt");
            Scanner readListFile = new Scanner(ListFile);
            int lastId = 0;
            while(readListFile.hasNextInt())
            {
                lastId = readListFile.nextInt();
            }
            return lastId + 1;
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

	public User getUser() {
		return seller;
	}


	public ArrayList<Bid> getAllBids() {
		return allBids;
	}
 
	public Item getItem() 
        {
		return item;
	}

	public double getPriceMin() 
        {
		return priceMin;
	}

	public double getPriceGuard() 
        {
		return priceGuard;
	}

	public AuctionState getState() {
		return state;
	}
        
        public void setPriceMin(double priceMin)
        {
            this.priceMin = priceMin;
        }

        public double getRelevancy()
        {
            return relevancy;
        }
        public void setRelevancy(String input)
        {
            this.relevancy = ListSorter.similarity(input, this.seller.getCompany()+" "+this.item.getDescription());
        }
	public boolean setState(AuctionState state) {

		if (state == AuctionState.AUCTION_CANCELED) {

			if(reservePriceReached)
				return false;
			
			// get all users
			ArrayList<User> users = this.findUsers();
			Alert alert = new Alert(this, AlertType.AUCTION_CANCELLED);
			
			//for (User u : users)
				//u.onAlert (alert);
		}
		
		this.state = state;
		
		return true;

	}
        
        //checks if the auction has ended
        public boolean isOver()
        {
            Date date = new Date();
            if(date.after(auctionEnd))
            {
                setState(AuctionState.AUCTION_ENDED);
                return true;
            }
            else
            {
                return false;
            }
        }
	
	public boolean addBid (Bid bid) 
        {
		Alert alert;

		// if auction is invalid
		// we can't bid on it
		if (!(state.equals(AuctionState.AUCTION_PUBLISHED)))
			return false;

		// it is not possible to bid over the minimum price
		if(bid.getPrice() > this.getPriceMin())
			return false;
		
		// seller cannot bid on his own auction
	//*     if (bid.getUser().equals(this.seller))
	//*         return false;

		// disable the auction if the end date is reached
		if(isOver())
                {
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
			//allBids.get(allBids.size()-i).getUser().onAlert(alert);
		}

		// send an alert to the seller when the reserve price is reached
		if (
			!reservePriceReached && 
			bid.getPrice() <= this.getPriceGuard() &&
			this.getPriceGuard() != -1
		) {
			this.reservePriceReached = true;
			alert = new Alert(this, AlertType.RESERVE_PRICE_REACHED);
			//this.seller.onAlert(alert);
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
		
		//for (Bid b : allBids) {
		//	if (Users.contains(b.getUser()) == false) {
		//		Users.add(b.getUser());
		//	}
		//}
		
		return Users;
	}
	
	// equality test
        @Override
	public boolean equals (Object obj) 
        {
            //checking if obj exists
            if(obj == null)
                return false;
            //checking if the parameter and obj are of the same class
            else if(obj.getClass() != this.getClass() )
                return false;
            //testing for equality once existence and class similarity are confirmed
            else
            {
                Auction auction = (Auction)obj;
                return (auction.auctionEnd.equals(this.auctionEnd)
			&& auction.seller.equals(this.seller)
			&& auction.item.equals(this.item)
			&& (Double.compare(auction.priceMin, this.priceMin) == 0)
			&& (Double.compare(auction.priceGuard, this.priceGuard) == 0));
            }
	}
        
        public int getId()
        {
            return id;
        }
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return this.seller.hashCode() ^ this.item.hashCode() ^ this.auctionEnd.hashCode();
	}
    
    public double getLowestBid() throws FileNotFoundException
    {
        Scanner readAuctionFile = new Scanner(new File("./docs/Auctions/" + id + ".txt"));
        boolean onBids = false;
        double lowestBid = 0;
        while(readAuctionFile.hasNext())
        {
            if(readAuctionFile.next().equals("Bids"))
            {
                onBids = true;
            }
            if(onBids == true && readAuctionFile.hasNextDouble())
            {
                while(readAuctionFile.hasNextDouble())
                {
                    lowestBid = readAuctionFile.nextDouble();
                }
            }
            else
                readAuctionFile.nextLine();
        }
        return lowestBid;
    }
    
    //one way of printing
    public String toString1() throws FileNotFoundException
    {
        String lowestBid;
        if(getLowestBid() != 0)
        {
            lowestBid = String.format("$%.2f",getLowestBid());
        }
        else
        {
            lowestBid = "no bids";
        }
        String priceGuardString = String.format("%.2f",priceGuard);
        String auctionOutput = "Auction ID: " + id + "%nEnd Time: " + this.auctionEnd + 
        "%nHosted By: " + seller.getCompany() + "%nLocation: " + item.getLocation() + 
        "%nMaximum Starting Bid: " + priceGuardString + "%n%nAuction Description:" + 
        item.getDescription() + "%nCurrent Lowest Bid: " + lowestBid + "%n";
        return String.format(auctionOutput);
    }
    
    public String toString2() throws FileNotFoundException
    {
        return String.format("%8d %12s %20s %12.2f %20s",id, getItem().getLocation(), getUser().getCompany(), getLowestBid(), auctionEnd.toString());
    }
}
