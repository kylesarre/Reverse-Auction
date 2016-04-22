package application;

import ReverseAuctionSystem.Auction;
import ReverseAuctionSystem.AuctionList;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Spring
 */
class AuctionListUI implements Initializable
{
    private Main application;
    private User loggedUser;

	//@FXML
	//private Label welcomeText;

        //@FXML
	//private Button exitButton;
        
        @FXML
	private Text auctionList;
    
    
	public void setApp(Main application) throws IOException 
        {
        this.application = application;
        loggedUser = application.getLoggedUser();
        //auctionList.setText(list());
        }
/**
        public String list() throws IOException
        {
            AuctionList aulist = new AuctionList();
            List<Auction> l1 = aulist.getAuctionList();
            String aucText = "";
            for(int i = 0; i < l1.size(); i++)
            {
                aucText = aucText + l1.get(i).toString2() + "%n";
            }
            return String.format(aucText);
        }
        */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	//@FXML
	//protected void exitApplication(ActionEvent event)
	//{
	//	Stage stage = (Stage) exitButton.getScene().getWindow();
	//	stage.close();
	//}
}
