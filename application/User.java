package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//important UI comments on editProfile(): line 54

public class User 
{
    private String username;
    private String password;
    private String company;
    private String companyType;
    private String profile;
    
    public User(String username) throws FileNotFoundException
    {
        try (Scanner readUser = new Scanner(new File("./docs/userfiles/"+username+".txt"))) 
        {
            this.username = username;
            password = readUser.next();
            readUser.nextLine();
            company = readUser.nextLine();
            companyType = readUser.next();
            while(readUser.hasNextLine())
            {
                profile = profile + readUser.nextLine() + "\n";
            }
            readUser.close();
        }
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getCompany()
    {
        return company;
    }
    
    public String getType()
    {
        return companyType;
    }
    
    public String getProfile()
    {
        return profile;
    }
    
    //for this method, the UI should pull up a text box containing the results of getProfile()
    //the user should be able to edit the text in the box and click 'submit'
    //when the submit button is pressed, activate this method
    public void editProfile(String newProfile) throws FileNotFoundException
    {
        PrintWriter editProfile = new PrintWriter(new File("./docs/userfiles/"+username+".txt"));
        editProfile.print(username + "\n" + password + "\n" + company + "\n" + companyType + "\n" + newProfile);
        editProfile.close();
    }
    
}
