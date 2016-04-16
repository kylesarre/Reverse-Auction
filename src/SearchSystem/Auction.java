package SearchSystem;

import SearchSystem.Sorter.ListSorter;

/**
 * Pseudo Auction Class for testing; needs to be replaced
 * @author Kyle
 */
public class Auction 
{
    protected String companyName;
    protected String title;
    protected String location;
    protected String description;
    protected String date;
    protected Bid minBid;
    protected double relevance;
    
    public Auction(String name, String location, String title, String description)
    {
        this.companyName = name;
        this.title = title;
        this.location = location;
        this.description = description;
        this.relevance = 0;
        this.date = date;
    }
    
    public String getName()
    {
        return companyName;
    }
    public String getTitle()
    {
        return title;
    }
    public String getLocation()
    {
        return location;
    }
    public String getDescription()
    {
        return description;
    }
    public Bid getMinBid()
    {
        return minBid;
    }
    public void setRelevance(String userInput)
    {
        relevance = ListSorter.similarity(userInput, companyName+title);
    }
    public double getRelevance()
    {
        return relevance;
    }
    public String getDate()
    {
        return date;
    }
}
