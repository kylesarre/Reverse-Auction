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
        Item item = new Item(description, "place1");
        Item item2 = new Item(description, "place2");
        try 
        {
            User user = new User("bob@yahoo.com");
            User user2 = new User("test@test.com");
            Date date = new Date(116, 1, 3);
            Auction auction = new Auction(date, user, item, 20.00, 20.00);
            Auction auction2 = new Auction(date, user, item2, 18.00, 18.00);
            //Auction auction = new Auction(1, date, user, item, 20.00, 20.00);
            Bid bid = new Bid(1,18.00, user);
            Bid bid2 = new Bid(1,17.00, user2);
            Bid bid3 = new Bid(1,21.00, user);
            //Bid bid = new Bid(1,12.00, user);
            AuctionList listObject = new AuctionList();
            List<Auction> list1 = listObject.getAuctionList();
            Auction auctionQ = list1.get(0); //gets first auction
            BidList listObject2 = new BidList(1); //searches auction 1
            List<Bid> list2 = listObject2.getBidList();
            Bid bidQ = list2.get(0); //gets first bid
            //for(int i = 0; i < list1.size(); i++)
            //{
            //    System.out.println(list1.get(i).toString1());
            //}
            System.out.println(list1.get(0).toString1());
            System.out.println(list1.get(1).toString2());
            //System.out.println(auction.toString1());
            
            
            
            
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
