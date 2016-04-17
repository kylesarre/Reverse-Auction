package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	private Button exitButton;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;

	@FXML
	protected void exitApplication(ActionEvent event)
	{
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	protected void login(ActionEvent event) throws IOException
	{
            LogIn loggingIn = new LogIn(usernameField.getText(),passwordField.getText());
            String loginToken = loggingIn.login();
            //seperate home pages necessary for each company type
            //stage testing below-feel free to edit/correct
            if(loginToken.equals("successful Service login") || loginToken.equals("successful Exploration login")) 
            {
                Parent home = FXMLLoader.load(getClass().getResource("HomeMenu.fxml"));
                Scene homeScene = new Scene(home,500,300);
                Stage homeStage = new Stage();
                homeStage.setScene(homeScene);
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
                homeStage.show();
                //Please configure Home Menu to include username and menu options
            }
            else if(loginToken.equals("Incorrect Password"))
            {
                System.out.println("The password you entered is incorrect.");
                //Please replace with dialog in stage
            }
            else if(loginToken.equals("invalid Company type"))
            {
                System.out.print("company type error");
            }
            else
            {
                System.out.println("The email address you entered has not registered an account.");
                //Please replace with dialog in stage
            }
	}
	
	@FXML
	protected void createAccount(ActionEvent event)
	{
            //please redirect to new stage with fields for username(email), company, company type, password, and confirmPassword
	}
}
