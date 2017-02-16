package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import ReverseAuctionSystem.Auction;
import ReverseAuctionSystem.AuctionList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class AuctionListController implements Initializable
{
	private Main application;
	private AuctionList aucts;
	private ObservableList<Auction> data;

	@FXML
	private TableView<Auction> table;
	@FXML
	private TableColumn<Auction, String> titleColumn;
	@FXML
	private TableColumn<Auction, String> locColumn;
	@FXML
	private TableColumn<Auction, String> creatorColumn;
	@FXML
	private TableColumn<Auction, Double> currentColumn;
	@FXML
	private TableColumn<Auction, String> dateColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}

	public void setApp(Main application) throws FileNotFoundException, IOException
	{
		this.application = application;
		aucts = new AuctionList();
		/*data = FXCollections.observableArrayList(aucts.getAuctionList());
		populateList();
		table.setItems(data);*/
	}

	@FXML
	protected void goBack()
	{
		application.gotoLogin();
	}

//This code is incomplete, placeholder image used due to time constraints
/*
	public void populateList()
    {
    	titleColumn.setCellValueFactory(new Callback<CellDataFeatures<Auction, String>, ObservableValue<String>>() {
    		public ObservableValue<String> call(CellDataFeatures<Auction, String> p) {
    	         return new ReadOnlyObjectWrapper<>(p.getValue().getItem().getDescription());
    	     }
    	});
    	locColumn.setCellValueFactory(new Callback<CellDataFeatures<Auction, String>, ObservableValue<String>>() {
    		public ObservableValue<String> call(CellDataFeatures<Auction, String> p) {
    	         return new ReadOnlyObjectWrapper<>(p.getValue().getItem().getLocation());
    	     }
    	});
    	creatorColumn.setCellValueFactory(new Callback<CellDataFeatures<Auction, String>, ObservableValue<String>>() {
    		public ObservableValue<String> call(CellDataFeatures<Auction, String> p) {
    	         return new ReadOnlyObjectWrapper<>(p.getValue().getSeller().getCompany());
    	     }
    	});
    	currentColumn.setCellValueFactory(new Callback<CellDataFeatures<Auction, Double>, ObservableValue<Double>>() {
    		public ObservableValue<Double> call(CellDataFeatures<Auction, Double> p) {
    	         return new ReadOnlyObjectWrapper<>(p.getValue().getLowBid().getPrice());
    	     }
    	});
    	dateColumn.setCellValueFactory(new Callback<CellDataFeatures<Auction, String>, ObservableValue<String>>() {
    		public ObservableValue<String> call(CellDataFeatures<Auction, String> p) {
    	         return new ReadOnlyObjectWrapper<>(p.getValue().getAuctionEnd().toString());
    	     }
    	});
    }
*/
}
