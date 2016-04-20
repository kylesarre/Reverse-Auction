package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// To use this class, create a Login object using the appropriate fields
// then use login() and if successful, generateUser() and
// send the User object and the user to the homepage for their specific companyType()

public class LogIn
{
    private String username;
    private String password;

    public LogIn(String username, String password)
    {
        this.username=username;
        this.password=password;
    }
    public String login()
    {
        if(findUsername(username)==true)
        {
            if(findPassword(username,password)==true)
            {
                if(companyType().equalsIgnoreCase("Service"))
                        {
                            return "successful Service login";
                            //send to Service Company Homepage
                        }
                        else if(companyType().equalsIgnoreCase("Exploration"))
                        {
                            return "successful Exploration login";
                            //send to Exploration Company Homepage
                        }
                        else
                        {
                            return "invalid Company type";
                            //invalid company type
                        }
            }
            else
            {
                return "Incorrect Password";
            }
        }
        else
        {
            return "No Account";
        }
    }

    public User generateUser() throws FileNotFoundException
    {
        return new User(username);
    }

    /* Used for the Main class to set the logged-in user*/
    public static User generateUser(String username) throws FileNotFoundException
    {
        return new User(username);
    }

    private boolean findUsername(String username)
    {
        boolean userExists = false;
        try(Scanner readUsernames = new Scanner(new File("./docs/Usernames.txt"));)
        {
            while(readUsernames.hasNext())
            {
                if(readUsernames.next().equals(username))
                {
                    userExists = true;
                }
            }
            readUsernames.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.print(e);
        }
        return userExists;
    }

    private boolean findPassword(String username, String password)
    {
        boolean passwordVerified=false;
        try (Scanner readUser = new Scanner(new File("./docs/userfiles/"+username+".txt")))
                {
                    String confirmPassword;
                    readUser.next();
                    confirmPassword = readUser.next();
                    if(confirmPassword.equals(password))
                    {
                        passwordVerified=true;
                    }
                    readUser.close();
                }
        catch(FileNotFoundException e)
        {
            System.out.print(e);
        }
        return passwordVerified;
    }

    private String companyType()
    {
        try (Scanner readUser = new Scanner(new File("./docs/userfiles/"+username+".txt")))
                {
                    readUser.next();
                    readUser.next();
                    readUser.nextLine();
                    readUser.nextLine();
                    String type = readUser.next();
                    readUser.close();
                    return type;
                }
        catch(FileNotFoundException e)
        {
            System.out.print(e);
        }
        return "invalid type";
    }

}
