package ReverseAuctionSystem;
import application.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Spring
 */
public class MainThing 
{

    public static void main(String[] args) 
    {
        String description = "description%nmore description%nThat's all";
        description = String.format(description);
        Item item = new Item(description, "location");
        Item item2 = new Item(description, "location");
        try 
        {
            User user = new User("bob@yahoo.com");
            User user2 = new User("test@test.com");
            Date date = new Date();
            //Auction auction = new Auction(date, user, item, 20.00, 20.00);
            //Auction auction2 = new Auction(date, user, item2, 18.00, 18.00);
            Auction auction = new Auction(1, date, user, item, 20.00, 20.00);
            //Bid bid = new Bid(1,18.00, user);
            //Bid bid2 = new Bid(1,17.00, user2);
            //Bid bid3 = new Bid(1,21.00, user);
            Bid bid = new Bid(1,15.00, user);
            AuctionList listObject = new AuctionList();
            List<Auction> list1 = listObject.getAuctionList();
            Auction auctionQ = list1.get(0); //gets first auction
            System.out.println(auctionQ.getPriceMin()); //
            BidList listObject2 = new BidList(1); //searches auction 1
            List<Bid> list2 = listObject2.getBidList();
            Bid bidQ = list2.get(0); //gets first bid
            System.out.println(bidQ.getPrice()); //
            
            
            
            
            
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(MainThing.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
    }
    
}
