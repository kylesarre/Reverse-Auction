package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RootController implements Initializable
{
	private Main application;

    public void setApp(Main application){
        this.application = application;
    }

    @FXML
	private Button exitButton;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label loginAlertLabel;

	@FXML
	protected void exitApplication(ActionEvent event)
	{
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	protected void login(ActionEvent event) throws IOException
	{
		//If login succeeds, Main class automatically changes scenes.
		if(!application.login(usernameField.getText(), passwordField.getText()))
			loginAlertLabel.setText("Invalid login. Check credentials, or create an account.");
	}

	@FXML
	protected void createAccount(ActionEvent event) throws IOException
	{
		application.createAccount();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		loginAlertLabel.setText("");
	}

}
