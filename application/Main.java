package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application
{
	private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 500;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

	@Override
	public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Reverse Auction System");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean login(String username, String password) throws IOException
	{
        LogIn loggingIn = new LogIn(username, password);
        String loginToken = loggingIn.login();
        //seperate home pages necessary for each company type
        //stage testing below-feel free to edit/correct
        if(loginToken.equals("successful Service login") || loginToken.equals("successful Exploration login"))
        {
        	loginSuccessful();
        	return true;
        } else
        	return false;
        /*
        else if(loginToken.equals("Incorrect Password"))
        {
            loginAlertLabel.setText("The password you entered is incorrect.");
        }
        else if(loginToken.equals("invalid Company type"))
        {
            loginAlertLabel.setText("company type error");
        }
        else
        {
            loginAlertLabel.setText("There is no account associated with the given email address.");
        }*/
	}

    protected void gotoLogin()
    {
        try {
            RootController login = (RootController) changeScene("Root.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loginSuccessful()
    {
        try {
            HomeMenu login = (HomeMenu) changeScene("HomeMenu.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void createAccount()
	{
		try {
            RegistrationPage reg = (RegistrationPage) changeScene("RegistrationPage.fxml");
            reg.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	protected Initializable changeScene(String fxml) throws IOException
	{
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 500, 300);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
	}
}
