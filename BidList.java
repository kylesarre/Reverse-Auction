package ReverseAuctionSystem;

import application.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BidList 
{
    private List<Bid> bids = new ArrayList<Bid>();
    
    public BidList(int auctionId) throws FileNotFoundException, IOException
    {
        Scanner readAuctionFile = new Scanner(new File("./docs/Auctions/"+auctionId+".txt"));
        while(!readAuctionFile.next().equals("Bids"))
        {
            readAuctionFile.nextLine();
        }   
        readAuctionFile.nextLine();
        int id;
        double price;
        while(readAuctionFile.hasNextInt())
        {
            id = readAuctionFile.nextInt();
            price = readAuctionFile.nextDouble();
            Scanner readBidFile = new Scanner(new File("./docs/Bids/"+id+".txt"));
            readBidFile.nextInt();
            readBidFile.nextDouble();
            String userid = readBidFile.next();
            Bid bid = new Bid(id, auctionId, price, new User(userid));
            bids.add(bid);
        }
    }
    
    public List getBidList()
    {
        return bids;
    }
}
