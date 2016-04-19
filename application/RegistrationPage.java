package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RegistrationPage implements Initializable
{
	@FXML
	private Button register;
	@FXML
	private TextField emailBox;
	@FXML
	private TextField compNameBox;
	@FXML
	private PasswordField pwBox;
	@FXML
	private PasswordField pwConfirm;
	@FXML
	private Label regWarningLabel;
	@FXML
	private final ToggleGroup group = new ToggleGroup();
	@FXML
	private RadioButton servCoButton;
	@FXML
	private RadioButton explCoButton;


	private String companySelection;
	private Stage stage;
	private Main application;

    public void setApp(Main application){
        this.application = application;
        servCoButton.setToggleGroup(group);
        explCoButton.setToggleGroup(group);
    }

	@FXML
	protected void regAttempt(ActionEvent event)
	{
		if(emailBox.getText().equals(null))
			regWarningLabel.setText("You must enter an email address.");
		else if(compNameBox.getText().equals(null))
			regWarningLabel.setText("You must enter a company name.");
		else if(pwBox.getText().equals(null))
			regWarningLabel.setText("You must enter a password.");
		else if(pwConfirm.getText().equals(null))
			regWarningLabel.setText("Re-enter your password to confirm.");
		else if(companySelection.equals(null))
			regWarningLabel.setText("Please select company type.");
		else
		{
			Register newCompany = new Register(emailBox.getText(), compNameBox.getText(), companySelection, pwBox.getText(), pwConfirm.getText());
			String regResult = newCompany.register();
			regWarningLabel.setText(regResult);
		}
	}

	@FXML
	protected void setServCo(ActionEvent event)
	{
		companySelection = "Service";
	}

	@FXML
	protected void setExplCo(ActionEvent event)
	{
		companySelection = "Exploration";
	}

	@FXML
	protected void goBack(ActionEvent event) throws IOException
	{
		application.gotoLogin();
	}

	protected Parent changeScene(String fxml) throws IOException
	{
		Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 500, 300);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        return page;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub

	}
}
