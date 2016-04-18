package SearchSystem.Sorter;

import ReverseAuctionSystem.Auction;
import java.util.List;
import java.util.Collections;

/**
 * Holds an algorithm to compute the similarity between two strings as a percentage.
 * @author Kyle
 * @version 0
 */
public class ListSorter
{
    /**
     * An algorithm to compute the similarity between two strings as a decimal.
     * @param s1 string provided by the user
     * @param s2 string representing the company name and job title
     * @return percentage similarity between the two strings
     */
    public static double similarity(String s1, String s2) 
    {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) 
        {
            longer = s2; 
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) 
        {
            return 1.0; 
        }
        return (longerLength - Levenshtein.distance(longer, shorter)) / (double) longerLength;
    }
    /**
     * Sorts a list of auctions in order of descending relevance
     * @param input string provided by the user
     * @param auctions list of auctions to be sorted
     */
    public static void sortByRelevance(String input, List<Auction> auctions)
    {
        for(Auction auction : auctions)
        {
            auction.setRelevancy(input);
        }
        Collections.sort(auctions, new RelevancyComparator());
    }
    /**
     * Sorts a list of auctions in order of descending date
     * @param auctions the list of auctions to be sorted
     */
    public static void sortByEndDate(List<Auction> auctions)
    {
        Collections.sort(auctions, new EndDateComparator());
    }
    /**
     * reverses the current order of the list
     * @param auctions the list of auctions to be reversed
     */
    public static void reverse(List<Auction> auctions)
    {
        Collections.reverse(auctions);
    }
}
