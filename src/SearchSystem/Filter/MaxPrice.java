package SearchSystem.Filter;

/**
 *
 * @author Kyle
 */
public class MaxPrice 
{
    private double maxPrice;
    public MaxPrice(double price)
    {
        maxPrice = price;
    }
    public double getMaxPrice()
    {
        return maxPrice;
    }
    public void updateMaxPrice(double newMaxPrice)
    {
        this.maxPrice = newMaxPrice;
    }
}
