package ReverseAuctionSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import application.User;
import java.io.IOException;
import java.util.ArrayList;

public class AuctionList 
{
    private List<Auction> auctions = new ArrayList<Auction>();
    
    public AuctionList() throws FileNotFoundException, IOException
    {
        File ListFile = new File("./docs/AuctionList.txt");
        Scanner readListFile = new Scanner(ListFile);
        int auctionID;
        while(readListFile.hasNextInt())
        {
            auctionID = readListFile.nextInt();
            File AuctionFile = new File("./docs/Auctions/"+auctionID+".txt");
            Scanner readAuctionFile = new Scanner(AuctionFile);
            int month = readAuctionFile.nextInt();
            int day = readAuctionFile.nextInt();
            int year = readAuctionFile.nextInt();
            Date date = new Date(year, month, day);
            User user = new User(readAuctionFile.next());
            readAuctionFile.nextLine();
            String location = readAuctionFile.nextLine();
            double priceMin = readAuctionFile.nextDouble();
            double priceGuard = readAuctionFile.nextDouble();
            String description = "";
            while(readAuctionFile.hasNextLine() && !readAuctionFile.next().equals("Bids:"))
            {
                description = description + readAuctionFile.nextLine() + "\n";
            }
            //while(readAuctionFile.hasNextLine())
            //{
                //create Bid
                //auction.addBid();
            //}
            Item item = new Item(auctionID, description, location);
            Auction auction = new Auction(date, user, item, priceMin, priceGuard);
            auctions.add(auction);
        }   
    }
    
    public List getAuctionList()
    {
        return auctions;
    }
}