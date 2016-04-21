/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReverseAuctionSystem;
import SearchSystem.Filter.*;
import java.util.ArrayList;
import java.util.List;
import SearchSystem.Sorter.*;
/**
 * bridges the interface with the auction list
 * @author ksarre2
 */
public class AuctionListController 
{
    private List<Auction> auctionList;
    //private UI uiElement; not implemented
    
    public AuctionListController(List auctionList)
    {
        this.auctionList = auctionList;
    }
    public void addToList(Auction auction)
    {
        auctionList.add(auction);
    }
    public void removeFromList(Auction auction)
    {
        auctionList.remove(auction);
    }
    public List grabAuctionList()
    {
        return new ArrayList(); // to implement
    }
    public void sortListByEndDate()
    {
        ListSorter.sortByEndDate(auctionList);
    }
    public void sortListByRelevancy(String input)
    {
        ListSorter.sortByRelevance(input, auctionList);
    }
    public void reverseList()
    {
        ListSorter.reverse(auctionList);
    }
    public List buildFilteredList(List<Auction> auctionList, Criteria inputCriteria)
    {
        return inputCriteria.meetCriteria(auctionList);
    }
    public void setAuctionList()
    {
        // to implement
    }
    public void updateUI()
    {
        //not implemented
    }
}
