package SearchSystem.Filter;

import ReverseAuctionSystem.Auction;
import ReverseAuctionSystem.AuctionList;
import ReverseAuctionSystem.Item;
import application.User;
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

public class FilterDemo 
{
    public static void main(String[] args) 
    {
         try
        {
            List<Auction> auctions = new AuctionList().getAuctionList();
            
            Criteria newCriteria1 = new CriteriaLocation("LA");
            Criteria newCriteria2 = new CriteriaCompanyName("Yahoo");
            
            Criteria andCriteria1 = new AndCriteria(newCriteria1, newCriteria2);
            Criteria orCriteria1 = new OrCriteria(newCriteria1, newCriteria2);
                        
            List<Auction> filteredAuctions = andCriteria1.meetCriteria(auctions);
            List<Auction> filteredAuctions2 = orCriteria1.meetCriteria(auctions);
            
            System.out.println("Before filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            for(Auction auctionA: auctions)
            {
                System.out.printf("%s%n%s%n%s\n%s%n%n", 
                auctionA.getUser().getCompany(),auctionA.getAuctionEnd(),
                auctionA.getItem().getLocation(),auctionA.getItem().getDescription());
            }
            
            System.out.println("After AND filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: filteredAuctions)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getUser().getCompany(),auctionB.getAuctionEnd(),
                auctionB.getItem().getLocation(),auctionB.getItem().getDescription());
            }
            
            System.out.println("After OR filtering method:");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println();
            for(Auction auctionB: filteredAuctions2)
            {
                System.out.printf("%s%n%s%n%s%n%s%n%n", 
                auctionB.getUser().getCompany(),auctionB.getAuctionEnd(),
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
