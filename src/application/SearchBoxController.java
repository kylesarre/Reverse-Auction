package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Spring
 */
public class SearchBoxController implements Initializable 
{
    private Main application;
    private User loggedUser;
    
    @FXML
    private Text searchSystem;
    
    @FXML
    private Button searchButton;
    
    @FXML
	private Button homeButton;
    
    @FXML
	private Button logoutButton;
    
    @FXML
	private Button exitButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(Main application)
    {
        this.application = application;
        loggedUser = application.getLoggedUser();
    }
    
    //home
    @FXML
    protected void goBack() throws IOException
    {
    	application.gotoHomeMenu();
    }
    
    //exit
    @FXML
	protected void exitApplication(ActionEvent event)
	{
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
    
}
