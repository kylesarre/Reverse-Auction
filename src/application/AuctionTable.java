package application;
/**
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ReverseAuctionSystem.TableAuction;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AuctionTable implements Initializable
{
    private Main application;
    private User loggedUser;
    
    

    private TableView table;
    private ObservableList data = getInitialTableData();
    //private Text actionStatus;
    private Text actionStatus;
    
    @FXML 
    private TableView<TableAuction> tableView;
    @FXML
    private TableColumn<TableAuction, String> idCol;
    @FXML
    private TableColumn<TableAuction, String> locationCol;
    @FXML
    private TableColumn<TableAuction, String> companyCol;
    @FXML
    private TableColumn<TableAuction, String> bidCol;
    @FXML
    private TableColumn<TableAuction, String> dateCol;
    @FXML
    private TableColumn<TableAuction, String> endCol;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("id"));
        locationCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("location"));
        companyCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("company"));
        bidCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("bid"));
        dateCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("date"));
        endCol.setCellValueFactory(new PropertyValueFactory<TableAuction, String>("end"));

        tableView.getItems().setAll(getInitialTableData());
    }
    
    public void setApp(Main application)
    {
        this.application = application;
        loggedUser = application.getLoggedUser();
        // Auctions label
        Label label = new Label("Auctions");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);

        // Table view, data, columns and properties

        table = new TableView();
        data = getInitialTableData();
        table.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        companyCol.setCellValueFactory(new PropertyValueFactory("company"));
        locationCol.setCellValueFactory(new PropertyValueFactory("location"));
        endCol.setCellValueFactory(new PropertyValueFactory("auctionEnd"));

        table.getColumns().setAll(idCol, companyCol, locationCol, endCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //table.getSelectionModel().selectedIndexProperty().addListener(
               // new RowSelectChangeListener());
		
        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        //Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));;
        vbox.getChildren().addAll(hb, table, actionStatus);
       

    }
    
    private ObservableList getInitialTableData() 
    {

        List list = new ArrayList();
        list.add(new TableAuction(1, "Arizona", "C1", 10.00, new Date(), new Date()));
        list.add(new TableAuction(2, "Texas", "C2", 15.00, new Date(), new Date()));
        list.add(new TableAuction(3, "Louisiana", "C3", 12.00, new Date(), new Date()));
        list.add(new TableAuction(4, "Arkansas", "C4", 135.67, new Date(), new Date()));
        list.add(new TableAuction(1, "Gulf of Mexico", "C5", 12.50, new Date(), new Date()));
        list.add(new TableAuction(1, "California", "C6", 100.00, new Date(), new Date()));
        

        ObservableList data = FXCollections.observableList(list);

        return data;
    }
}
/**
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ReverseAuctionSystem.TableAuction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class AuctionTable implements Initializable
{
    private Main application;
    private User loggedUser;
    
    public void setApp(Main application)
    {
        this.application = application;
        loggedUser = application.getLoggedUser();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<TableAuction, String> idCol;

    @FXML
    private TableColumn<TableAuction, String> companyCol;

    @FXML
    private TableColumn<TableAuction, String> dateCol;

    @FXML
    private TableColumn<TableAuction, String> endCol;

    @FXML
    private TableColumn<TableAuction, String> bidCol;

    @FXML
    private TableColumn<TableAuction, String> locCol;

    @FXML
    private TableView<TableAuction> tableView;


    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
            //idCol.setCellValueFactory(new PropertyValueFactory("id"));
		// TODO Auto-generated method stub

	}
        
    
    private ObservableList getInitialTableData() 
    {
        List list = new ArrayList();
        list.add( new TableAuction(1, "Arizona", "C1", 10.00, new Date(), new Date()));
        list.add( new TableAuction(2, "Texas", "C2", 10.01, new Date(), new Date()));
        list.add( new TableAuction(3, "La", "C3", 100.00, new Date(), new Date()));
        list.add( new TableAuction(4, "Dominican Republic", "C4", 50.00, new Date(), new Date()));
        
        ObservableList data = FXCollections.observableList(list);

        return data;
    }
 
}
*/
/**
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ReverseAuctionSystem.TableAuction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuctionTable implements Initializable 
{

    public Stage stage = new Stage();

    private ObservableList<AuctionTable> data = FXCollections.observableArrayList();

    @FXML
    private TableView<AuctionTable> tableData;

    @FXML
    private TableColumn<AuctionTable, String> idCol;
    @FXML
    private TableColumn<AuctionTable, String> locationCol;
    @FXML
    private TableColumn<AuctionTable, String> companyCol;
    @FXML
    private TableColumn<AuctionTable, String> bidCol;
    @FXML
    private TableColumn<AuctionTable, String> dateCol;
    @FXML
    private TableColumn<AuctionTable, String> endCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {

        idCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Nombre"));
        locationCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Matrícula"));
        companyCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Carrera"));
        bidCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Ingreso"));
        dateCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Promedio"));
        endCol.setCellValueFactory(new PropertyValueFactory<AuctionTable,String>("Estatus"));
        tableData.setEditable(true);
        tableData.setItems(getInitialTableData());
    }
    
    private ObservableList getInitialTableData() 
    {

        List list = new ArrayList();
        list.add( new TableAuction(1, "Arizona", "C1", 10.00, new Date(), new Date()));
        list.add( new TableAuction(2, "Texas", "C2", 10.01, new Date(), new Date()));
        list.add( new TableAuction(3, "La", "C3", 100.00, new Date(), new Date()));
        list.add( new TableAuction(4, "Dominican Republic", "C4", 50.00, new Date(), new Date()));
        

        ObservableList data = FXCollections.observableList(list);

        return data;
    }
    
    
    
    private Main application;
    private User loggedUser;
    public void setApp(Main application)
    {
        this.application = application;
        loggedUser = application.getLoggedUser();
    }
}

/**
    @FXML
    public void volverAMain(ActionEvent e) throws IOException{
        stage = (Stage)volverButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
***/
/**
public class AuctionTable implements Initializable 
{
private final static String MONTHS[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
   
        
  private XYChart.Series createSeries(String name) {
    XYChart.Series series = new XYChart.Series();
    series.setName(name);
    final ObservableList data = series.getData();
    final DataGenerator generator = new DataGenerator();
    for (String month: MONTHS) {
      data.add(generator.createDataItem(month));
    }
    
    return series;
  }
    private Main application;
    private User loggedUser;
    public void setApp(Main application)
    {
        this.application = application;
        loggedUser = application.getLoggedUser();
    }
    
  
  @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    }
  
  private TableView createTableView(final LineChart<String, Number> chart) {
    TableView<XYChart.Series<String,Number>> table = new TableView();
    
    if (!chart.getData().isEmpty()) {
      TableColumn legendCol = new TableColumn("Legend");
      legendCol.setResizable(false);
      legendCol.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<XYChart.Series<String,Number>, String>, ObservableValue<XYChart.Series<String,Number>>>() {
          @Override public ObservableValue<XYChart.Series<String, Number>> call(TableColumn.CellDataFeatures<XYChart.Series<String, Number>, String> param) {
            return new SimpleObjectProperty(param.getValue());
          }
        }  
      );
      legendCol.setCellFactory(new Callback<TableColumn<XYChart.Series<String,Number>,XYChart.Series<String, Number>>,TableCell<XYChart.Series<String,Number>,XYChart.Series<String, Number>>>() {
        @Override public TableCell<XYChart.Series<String, Number>, XYChart.Series<String, Number>> call(TableColumn<XYChart.Series<String, Number>, XYChart.Series<String, Number>> param) {
          return new TableCell<XYChart.Series<String, Number>, XYChart.Series<String, Number>>() {
            @Override protected void updateItem(XYChart.Series<String, Number> series, boolean empty) {
              super.updateItem(series, empty);
              if (series != null) {
                setText(series.getName());
                setGraphic(createSymbol(series, chart.getData().indexOf(series)));
              }  
            }
          };
        }
      });
      table.getColumns().add(legendCol);

      final ObservableList<XYChart.Data<String, Number>> firstSeriesData = chart.getData().get(0).getData();
      for (final XYChart.Data<String, Number> item: firstSeriesData) {
        TableColumn col = new TableColumn(item.getXValue());
        col.setSortable(false);
        col.setResizable(false);
        col.prefWidthProperty().bind(chart.getXAxis().widthProperty().divide(firstSeriesData.size()));
        col.setCellValueFactory(
          new Callback<TableColumn.CellDataFeatures<XYChart.Series<String,Number>, String>, ObservableValue<Number>>() {
            @Override public ObservableValue<Number> call(TableColumn.CellDataFeatures<XYChart.Series<String,Number>, String> param) {
              for (XYChart.Data<String, Number> curItem: param.getValue().getData()) {
                if (curItem.getXValue().equals(item.getXValue())) {
                  return curItem.YValueProperty();
                }
              }
              
              return null;
            }
          }  
        );
        table.getColumns().add(col);
      }
      
      for (XYChart.Series<String,Number> series: chart.getData()) {
        table.getItems().add(series);
      }  

      table.setEditable(false);
      table.setFocusTraversable(false);
    }  
    
    table.setTranslateY(-30);
    table.setPrefHeight(88);
    table.setStyle("-fx-box-border: transparent; -fx-focus-color: transparent; -fx-padding: 0 9 0 9;");
    
    return table;
  }
  
  private Node createSymbol(XYChart.Series<String, Number> series, int seriesIndex) {
    Node symbol = new StackPane();
    symbol.getStyleClass().setAll(
      "chart-line-symbol", 
      "series" + seriesIndex,
      "default-color" + (seriesIndex % 8)
    );
    return symbol;
  }  

// generates random chart data.
class DataGenerator {
  private Random random = new Random();
  private int delta = 0;
  private int trend = random.nextInt(4) - 1;
  public XYChart.Data createDataItem(String month) {
    return new XYChart.Data(month, genDataVal());
  }
  private int genDataVal() {
    delta += trend;
    return random.nextInt(20) + 15 + delta;
  }}}*/

import ReverseAuctionSystem.AuctionList;
import ReverseAuctionSystem.Auction;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ReverseAuctionSystem.TableAuction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AuctionTable implements Initializable {

    private Main application;
    public void setApp(Main application)
    {
        this.application = application;
    }
    
    @FXML
    private Label label;

    @FXML
    private TableView<TableAuction> table;// = new TableView<Person>();

    @FXML private TableColumn idCol ;
    @FXML private TableColumn locationCol ;
    @FXML private TableColumn companyCol ;
    @FXML private TableColumn bidCol ;
    @FXML private TableColumn endCol ;

    @FXML TextField addFirstName;
    @FXML TextField addLastName;
    @FXML TextField addEmail;
    
    private ObservableList getInitialTableData() throws IOException 
    {
        AuctionList auList = new AuctionList();
        List<Auction> list = auList.getAuctionList();
        List<TableAuction> list2 = new ArrayList();
        for(int i = 0; i < list.size(); i++)
        {
            list2.add(list.get(i).toTableAuction());
        }
        ObservableList data = FXCollections.observableList(list);

        return data;
    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException
    {
            application.gotoLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        idCol.setMinWidth(100);
        locationCol.setMinWidth(100);
        companyCol.setMinWidth(200);
        bidCol.setMinWidth(100);
        endCol.setMinWidth(200);
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        locationCol.setCellValueFactory(new PropertyValueFactory("location"));
        companyCol.setCellValueFactory(new PropertyValueFactory("company"));
        bidCol.setCellValueFactory(new PropertyValueFactory("lowestBid"));
        endCol.setCellValueFactory(new PropertyValueFactory("auctionEnd"));
        try {
            table.getItems().setAll(getInitialTableData());
        } catch (IOException ex) {
            Logger.getLogger(AuctionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
/**
    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }*/
}