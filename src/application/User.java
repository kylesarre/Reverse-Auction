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
        	readUser.nextLine();
            this.username = username;
            password = readUser.nextLine();
            company = readUser.nextLine();
            companyType = readUser.nextLine();
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
    	if(profile != null)
    		return profile;
    	else
    		return "";
    	}

    //for this method, the UI should pull up a text box containing the results of getProfile()
    //the user should be able to edit the text in the box and click 'submit'
    //when the submit button is pressed, activate this method
    public void editProfile(String newProfile) throws FileNotFoundException
    {
        PrintWriter editProfile = new PrintWriter(new File("./docs/userfiles/"+username+".txt"));
        editProfile.printf("%s%n%s%n%s%n%s%n%s%n", username, password, company, companyType, newProfile);
        editProfile.close();
        profile = newProfile;
    }

}
