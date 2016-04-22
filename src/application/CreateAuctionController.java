package application;

import ReverseAuctionSystem.Auction;
import ReverseAuctionSystem.Item;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Spring
 */
public class CreateAuctionController implements Initializable 
{
    @FXML
    private TextField locationField;
    @FXML
    private TextField priceGuardField;
    @FXML
    private TextField monthField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField descriptionField;

    private Main application;
    private User loggedUser;

    public void setApp(Main application)
    {
    this.application = application;
    loggedUser = application.getLoggedUser();
    }
    
    public void createAuction() throws IOException
    {
        int yyyy = Integer.parseInt(yearField.getText());
        int mm = Integer.parseInt(monthField.getText());
        int dd = Integer.parseInt(dayField.getText());
        double pG = Double.parseDouble(priceGuardField.getText());
        Date date = new Date(yyyy-1900,mm+1,dd);
        Item newItem = new Item(descriptionField.getText(), locationField.getText());
        Auction newAuction = new Auction(date, loggedUser, newItem, pG, pG);
    }
    
    @FXML
    protected void goBack() throws IOException
    {
    	application.gotoHomeMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }    
}
