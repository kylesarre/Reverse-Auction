package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ProfileController implements Initializable
{

	private Main application;
	private User loggedUser;

	@FXML
	private Label profileNameLabel;
	@FXML
	private Label profileBox;
	@FXML
	private TextArea profileEditBox;
	@FXML
	private Button editButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelEditButton;
	@FXML
	private Button backButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
	}

	public void setApp(Main application)
	{
		this.application = application;
		loggedUser = application.getLoggedUser();
		profileNameLabel.setText("User Profile: " + loggedUser.getCompany());
		if (!loggedUser.getProfile().equals("")) // If profile is not empty,
													// replace label text with
													// profile text
			profileBox.setText(loggedUser.getProfile());
	}

	/**
	 * Changes the profile elements to allow the user to edit profile text
	 *
	 * @see toggleEditMode
	 */
	@FXML
	protected void editProfile()
	{
		toggleEditMode(true);
		if (!loggedUser.getProfile().equals(""))
			profileEditBox.setText(loggedUser.getProfile());
	}

	/**
	 * commits
	 *
	 * @throws FileNotFoundException
	 * @see toggleEditMode
	 */
	@FXML
	protected void saveEdit() throws FileNotFoundException
	{
		loggedUser.editProfile(profileEditBox.getText());
		profileBox.setText(profileEditBox.getText());
		toggleEditMode(false);
	}

	@FXML
	protected void cancelEdit()
	{
		toggleEditMode(false);
	}

	/**
	 * Changes visible profile elements for viewing profile and for editing
	 * profile
	 *
	 * @param state
	 *            true to edit profile, false to view profile
	 * @see editProfile, saveEdit
	 */
	private void toggleEditMode(boolean state)
	{
		profileBox.setVisible(!state);
		editButton.setVisible(!state);
		backButton.setVisible(!state);
		profileEditBox.setVisible(state);
		saveButton.setVisible(state);
		cancelEditButton.setVisible(state);
	}

	@FXML
	protected void goBack() throws IOException
	{
		application.gotoHomeMenu();
	}

}
