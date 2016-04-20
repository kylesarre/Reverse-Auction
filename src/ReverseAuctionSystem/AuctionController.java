/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReverseAuctionSystem;
import java.util.List;
import java.util.Date;
/**
 * bridges the interface with the auction class
 * @author ksarre2
 */

public class AuctionController 
{
    private Auction auction;
    //private UI uiElement; not implemented
    
    /**
     * constructs the controller with a specified auction
     * @param auction 
     */
    public AuctionController(Auction auction)
    {
        this.auction = auction;
    }
    /**
     * sets a new auction in the Controller
     * @param auction 
     */
    public void setAuction(Auction auction)
    {
        this.auction = auction;
    }
    /**
     * grabs the complete list of bids in the given auction
     * @return the complete list of bids
     */
    public List<Bid> grabBidList()
    {
        return auction.getAllBids();
    }
    /**
     * grabs the date the auction is scheduled to end
     * @return the ending date of the auction
     */
    public Date grabEndDate()
    {
        return auction.getAuctionEnd();
    }
    /**
     * grabs the company name of the auction owner
     * @return the company name of the auction owner
     */
    public String grabCompanyName()
    {
        return auction.getSeller().getCompany();
    }
    /**
     * grabs the profile information of the auction owner
     * @return the profile info of the auction owner
     */
    public String grabProfileDesc()
    {
        return auction.getSeller().getCompany();
    }
    public String grabJobDesc()
    {
        return auction.getItem().getDescription();
    }
    public String grabLocation()
    {
        return auction.getItem().getLocation();
    }
    public void setPriceMin(double input)
    {
        auction.setMinPrice(input);
    }
    public void setCompanyName(String input)
    {
        auction.getSeller().setCompany(input);
    }
    /**
     * updates the user interface element in the Controller
     */
    public void updateUI()
    {
        // not implemented 
    }
    
}
