package ReverseAuctionSystem;

import application.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	public Bid(int auctionId, double price, User user) throws FileNotFoundException, IOException
        {
                this.auctionId = auctionId;
		this.price = price;
		this.user = user;
                File ListFile = new File("./docs/BidList.txt");
                Scanner readListFile = new Scanner(ListFile);
                int bidID;
                boolean BidIsOnFile = false;
                while(readListFile.hasNextInt())
                {
                    if(readListFile.nextInt() == id)
                    {
                        BidIsOnFile = true;
                    }
                }
                if(!BidIsOnFile)
                {
                    //write to BidList
                    FileWriter fileWriter = new FileWriter(new File("./docs/BidList.txt"), true);
                    BufferedWriter buffWriter = new BufferedWriter(fileWriter);
                    PrintWriter writeToList = new PrintWriter(buffWriter);
                    writeToList.println(id);
                    //write the bid
                    PrintWriter writeToFile = new PrintWriter(new File("./docs/Bids/" + id + ".txt"));
                    writeToFile.println(auctionId);
                    writeToFile.println(price);
                    writeToFile.println(user.getUsername());
                    //update User bids
                    FileWriter fileWriter2 = new FileWriter(new File("./docs/userfiles/"+user.getUsername()+".bids.txt"), true);
                    BufferedWriter buffWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter writeToUser = new PrintWriter(buffWriter2);
                    writeToUser.println(auctionId +  " " +price);
                    //update auction file
                    
                }
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
        public int hashCode() 
        {
		return user.hashCode() ^ (int) this.price;
        }
}