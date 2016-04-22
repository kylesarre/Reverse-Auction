package SearchSystem;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ReverseAuctionSystem.Item;
import ReverseAuctionSystem.Auction;
import SearchSystem.Sorter.ListSorter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Date;
import application.User;
/**
 * Tests the implementation of the ListSorter class.
 * @author Kyle
 * @version 0
 */
public class SearchImplementationTest {

    /**
     * run with input.txt in command line
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            Scanner sc = new Scanner(new File(args[0]));
            List<Auction> auctions = new ArrayList<>();
            while(sc.hasNextLine())
            {
                //Build the date from input.txt
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                String datestr = sc.nextLine();
                Date date = format.parse(datestr);
                //Setting a user (not reading from the file here)
                User user = new User("test@test.com");
                //Build the user from input.txt
                Item item = new Item(Integer.parseInt(sc.nextLine()),sc.nextLine(), sc.nextLine());
                //set pricemin and guard because auctions are dumb
                double priceMin = Double.parseDouble(sc.nextLine());
                double priceGuard = Double.parseDouble(sc.nextLine());
                sc.nextLine();
                //throw everything into that auction
                auctions.add(new Auction(date, user, item, priceMin, priceGuard));               
            }
            
            // THE TEST STARTS HERE
            // feel free to try other sorts in here
            // also, sortByRelevance() compares "input" to "CompanyName" + "Item Description". you can add more to the sortByRelevance() if you'd like.
            System.out.println("Before sorting method:");
            System.out.println("//////////////////////////////////////////////////////////");
            for(Auction auctionA: auctions)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionA.getAuctionEnd(),auctionA.getUser().getCompany(),
                auctionA.getItem().getLocation(),auctionA.getItem().getDescription());
            }
            // We call the sorting method
            ListSorter.sortByRelevance("Environmental guy", auctions);
            //Print the auctionList(type) auctions(var) after sorting
            System.out.println("After relevancy sorting method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: auctions)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getAuctionEnd(),auctionB.getUser().getCompany(),
                auctionB.getItem().getLocation(),auctionB.getItem().getDescription());
            }
            // Then we test to see if we can reverse the list
            ListSorter.reverse(auctions);
            System.out.println("After reverse method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: auctions)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getAuctionEnd(),auctionB.getUser().getCompany(),
                auctionB.getItem().getLocation(),auctionB.getItem().getDescription());
            }
        }
        catch(RuntimeException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}
