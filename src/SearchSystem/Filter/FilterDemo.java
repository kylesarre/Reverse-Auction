package SearchSystem.Filter;

import SearchSystem.Location;
import SearchSystem.Auction;
import SearchSystem.Name;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * tests the implementation of our filters
 * @author Kyle Sarre
 * @version 0
 */
public class FilterDemo {

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
            
            Criteria newCriteria1 = new CriteriaLocation(new Location("TX"));
            Criteria newCriteria2 = new CriteriaName("Occidental Petroleum");
            
            Criteria andCriteria1 = new OrCriteria(newCriteria1, newCriteria2);
                        
            List<Auction> filteredAuctions = andCriteria1.meetCriteria(auctions);
            //List<Auction> filteredAuctions = newCriteria2.meetCriteria(auctions);
            
            System.out.println("Before filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            for(Auction auctionA: auctions)
            {
                System.out.printf("%s\n%s\n%s\n%s\n\n", 
                auctionA.getName(),auctionA.getTitle(),
                auctionA.getLocation(),auctionA.getDescription());
            }
            
            System.out.println("After AND filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: filteredAuctions)
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
