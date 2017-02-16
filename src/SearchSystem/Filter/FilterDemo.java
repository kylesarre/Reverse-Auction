package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import ReverseAuctionSystem.Item;
import ReverseAuctionSystem.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * tests the implementation of our filters
 * @author Kyle Sarre
 * @version 0
 */
public class FilterDemo {
/*
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {/*
         try
        {
            
            Scanner sc = new Scanner(new File(args[0]));
            List<Auction> auctions = new ArrayList<>();
            
            while(sc.hasNextLine())
            {                
                String date = sc.nextLine();
                String companyName = sc.nextLine();
                String jobTitle = sc.nextLine();
                String priceGuard = sc.nextLine();
                String minPrice = sc.nextLine();
                String location = sc.nextLine();
                sc.nextLine();
                auctions.add( new Auction(new Date(date), new User("asdf", "asdf", companyName, location), new Item(1, jobTitle), Double.parseDouble(minPrice), Double.parseDouble(priceGuard) ));               
            }
            
            Criteria newCriteria1 = new CriteriaLocation("TX");
            Criteria newCriteria2 = new CriteriaCompanyName("Occidental Petroleum");
            
            Criteria andCriteria1 = new AndCriteria(newCriteria1, newCriteria2);
            Criteria orCriteria1 = new OrCriteria(newCriteria1, newCriteria2);
                        
            List<Auction> filteredAuctions = andCriteria1.meetCriteria(auctions);
            List<Auction> filteredAuctions2 = orCriteria1.meetCriteria(auctions);
            
            System.out.println("Before filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            for(Auction auctionA: auctions)
            {
                System.out.printf("%s%n%s%n%s\n%s%n%n", 
                auctionA.getSeller().getCompany(),auctionA.getAuctionEnd(),
                auctionA.getSeller().getLocation(),auctionA.getItem().getDescription());
            }
            
            System.out.println("After AND filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: filteredAuctions)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getSeller().getCompany(),auctionB.getAuctionEnd(),
                auctionB.getSeller().getLocation(),auctionB.getItem().getDescription());
            }
            
            System.out.println("After OR filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: filteredAuctions2)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getSeller().getCompany(),auctionB.getAuctionEnd(),
                auctionB.getSeller().getLocation(),auctionB.getItem().getDescription());
            }
        }
        catch(RuntimeException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            System.err.println(e);
        }*/
    }
}
