package SearchSystem;

import SearchSystem.Sorter.*;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the implementation of the ListSorter class.
 * @author Kyle
 * @version 0
 */
public class SearchImplementationTest {

    /**
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
                
                String companyName = sc.nextLine();
                String jobTitle = sc.nextLine();
                String location = sc.nextLine();
                sc.nextLine();
                auctions.add(new Auction(companyName, jobTitle,location,"Looking for new recruits") );               
            }
            System.out.println("Before sorting method:");
            System.out.println("//////////////////////////////////////////////////////////");
            for(Auction auctionA: auctions)
            {
                System.out.printf("%s\n%s\n%s\n%s\n\n", 
                auctionA.getName(),auctionA.getTitle(),
                auctionA.getLocation(),auctionA.getDescription());
            }
            ListSorter.sortByRelevance("Occidental Petroleum", auctions);
            System.out.println("After relevancy sorting method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: auctions)
            {
                System.out.printf("%s\n%s\n%s\n%s\n\n", 
                auctionB.getName(),auctionB.getTitle(),
                auctionB.getLocation(),auctionB.getDescription());
            }
            ListSorter.reverse(auctions);
            System.out.println("After reverse method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: auctions)
            {
                System.out.printf("%s\n%s\n%s\n%s\n\n", 
                auctionB.getName(),auctionB.getTitle(),
                auctionB.getLocation(),auctionB.getDescription());
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}
