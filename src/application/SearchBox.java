package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchBox implements Initializable
{
	private Main application;
	private User loggedUser;

	@FXML
	private Text searchSystem;
	@FXML
	private Button exitButton;

	public void setApp(Main application)
        {
        this.application = application;
        loggedUser = application.getLoggedUser();
        }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	@FXML
	protected void logout(ActionEvent event)
	{
		application.logout();
	}

	@FXML
	protected void exitApplication(ActionEvent event)
	{
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	protected void gotoActiveProfile() throws IOException
	{
		application.gotoProfilePage();
	}
}
