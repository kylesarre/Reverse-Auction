package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

//to use this class in the registration page, create a Register object with the appropriate fields then use the method register()

public class Register
{
	private String username;
	private String company;
	private String companyType;
	private String password;
	private String confirmPassword;

	public Register(String username, String company, String companyType, String password, String confirmPassword)
	{
		this.username = username;
		this.password = password;
		this.company = company;
		this.companyType = companyType;
		this.confirmPassword = confirmPassword;
	}

	public String register()
	{
		if (findUser() == true)
		{
			return "Account already exists.";
		} else
		{
			if (password.equals(confirmPassword))
			{
				if (companyType.equalsIgnoreCase("Service") || companyType.equalsIgnoreCase("Exploration"))
				{
					writeUserFile();
					return "Account successfully created.";
				} else
					return "Invalid company type.";
			} else
				return "Passwords do not match.";
		}
	}

	private void writeUserFile()
	{
		try (PrintWriter writeUser = new PrintWriter(new File("./docs/userfiles/" + username + ".txt")))
		{
			writeUser.println(username);
			writeUser.println(password);
			writeUser.println(company);
			writeUser.println(companyType);
			writeUser.close();
		} catch (FileNotFoundException e)
		{
			System.out.println(e);
		}
		try (FileWriter fw = new FileWriter(new File("./docs/Usernames.txt"), true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter writeMasterUserList = new PrintWriter(bw))
		{
			writeMasterUserList.println(username);
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private boolean findUser()
	{
		boolean userExists = false;
		try (Scanner readUsernames = new Scanner(new File("./docs/Usernames.txt"));)
		{
			while (readUsernames.hasNext())
			{
				if (readUsernames.next().equals(username))
				{
					userExists = true;
				}
			}
			readUsernames.close();
		} catch (FileNotFoundException e)
		{
			System.out.println(e);
		}
		return userExists;
	}

}
