package ReverseAuctionSystem;

import java.io.IOException;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TableAuction {
    
    private SimpleStringProperty id;
    private SimpleStringProperty location;
    private SimpleStringProperty company;
    private SimpleStringProperty lowestBid;
    private SimpleStringProperty auctionEnd;

    public TableAuction (int id, String location, String company, String lowestBid, Date endDate) {

        this.id = new SimpleStringProperty(id + "");
        this.location = new SimpleStringProperty(location);
        this.company = new SimpleStringProperty(company);
        this.lowestBid = new SimpleStringProperty(lowestBid);
        this.auctionEnd = new SimpleStringProperty(endDate.toString());
    }

    public String getId() 
    {

        return id.get();
    }
    public void setId(int id) 
    {
	
        this.id.set(id + "");
    }
    public String getLocation() 
    {

        return location.get();
    }
    public void setLocation(String location) 
    {
	
       this.location.set(location);
    }
    public String getCompany() 
    {

        return location.get();
    }
    public void setCompany(String company) 
    {
	
        this.company.set(company);
    }
    public String getBid() 
    {

        return lowestBid.get();
    }
    public void setBid(int bidAmount) 
    {
	
        lowestBid.set(bidAmount + "");
    }
    public String getAuctionEnd() 
    {

        return auctionEnd.get();
    }
    public void setAuctionEnd(Date date) 
    {
	
        auctionEnd.set(date.toString());
    }
}