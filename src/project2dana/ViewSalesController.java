package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class ViewSalesController implements Initializable {

    FinishedOrderProperty op = null;
    ObservableList<FinishedOrderProperty> ObList = FXCollections.observableArrayList();
    DbConnector db = new DbConnector();

    @FXML
    private Button returnButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<FinishedOrderProperty> orderTable;

    @FXML
    private TableColumn<FinishedOrderProperty, String> drinkColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> drinkSizeColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> appetizerColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> mainCourseColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> dessertColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, Integer> tableColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, Integer> idColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> dateColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, Double> priceColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, Integer> saleIdColumn;

    @FXML
    private TableColumn<FinishedOrderProperty, String> extraColumn;

    @FXML
    private TextField itemField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField idField;

    @FXML
    private TextField tableField;

    @FXML
    private void handleaddReturnButtonAction(ActionEvent event) {
        try {
            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTable();
    }

    public void handleEditButtonAction(ActionEvent event) {

    }

    public void showTable() {
        try {

            ObList = db.getSales();

            drinkColumn.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drinkSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getDrinkSize());
            appetizerColumn.setCellValueFactory(cellData -> cellData.getValue().getAppetizer());
            mainCourseColumn.setCellValueFactory(cellData -> cellData.getValue().getMainCourse());
            dessertColumn.setCellValueFactory(cellData -> cellData.getValue().getDessert());
            extraColumn.setCellValueFactory(cellData -> cellData.getValue().getExtra());
            tableColumn.setCellValueFactory(cellData -> cellData.getValue().getTableNumber().asObject());
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
            dateColumn.setCellValueFactory(celldData -> celldData.getValue().getDate());
            priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
            saleIdColumn.setCellValueFactory(cellData -> cellData.getValue().getSalesId().asObject());
            orderTable.setItems(ObList);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleRefreshButtonAction(ActionEvent event) {
        ObList.clear();
        showTable();
    }

    public void handleDeleteButtonAction(ActionEvent event) {
        try {
            FinishedOrderProperty opf = ObList.get(orderTable.getSelectionModel().getSelectedIndex());
            int tmpInt = opf.getSalesId().intValue();
            db.deleteSale(tmpInt);

            ObList.clear();
            showTable();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void handleSearchButtonAction(ActionEvent event) {
        try {

            ObservableList<FinishedOrderProperty> tableItems = FXCollections.observableArrayList();

            ObservableList<TableColumn<FinishedOrderProperty, ?>> cols = orderTable.getColumns();

            for (int i = 0; i < ObList.size(); i++) {
                System.out.println(1);
                TableColumn drinkCol = cols.get(0);
                TableColumn appetizerCol = cols.get(2);
                TableColumn mainCourseCol = cols.get(3);
                TableColumn dessertCol = cols.get(4);
                TableColumn extraCol = cols.get(5);
                TableColumn tableCol = cols.get(6);
                TableColumn idCol = cols.get(7);
                TableColumn dateCol = cols.get(8);

                String cellValueDrink = drinkCol.getCellData(ObList.get(i)).toString();
                String cellValueAppetizer = appetizerCol.getCellData(ObList.get(i)).toString();
                String cellValueMainCourse = mainCourseCol.getCellData(ObList.get(i)).toString();
                String cellValueDessert = dessertCol.getCellData(ObList.get(i)).toString();
                String cellValueExtra = extraCol.getCellData(ObList.get(i)).toString();
                String cellValueTable = tableCol.getCellData(ObList.get(i)).toString();
                String cellValueId = idCol.getCellData(ObList.get(i)).toString();
                String cellValueDate = dateCol.getCellData(ObList.get(i)).toString();

                cellValueDrink = cellValueDrink.toLowerCase();
                cellValueAppetizer = cellValueAppetizer.toLowerCase();
                cellValueMainCourse = cellValueMainCourse.toLowerCase();
                cellValueDessert = cellValueDessert.toLowerCase();
                cellValueExtra = cellValueExtra.toLowerCase();
                cellValueTable = cellValueTable.toLowerCase();
                cellValueId = cellValueId.toLowerCase();
                cellValueDate = cellValueDate.toLowerCase();
                System.out.println(cellValueDrink);

                String itemString = null;
                String tableString = null;
                String dateString = null;
                String idString = null;

                if (itemField.textProperty().get().isEmpty()) {
                    itemString = "asdakj1232131kjasdka".toLowerCase();
                } else {
                    itemString = itemField.textProperty().get().toLowerCase();
                }
                if (tableField.textProperty().get().isEmpty()) {
                    tableString = "asdakj1232131kjasdka".toLowerCase();
                } else {
                    tableString = tableField.textProperty().get().toLowerCase();
                }
                if (idField.textProperty().get().isEmpty()) {
                    idString = "asdakj1232131kjasdka".toLowerCase();
                } else {
                    idString = idField.textProperty().get().toLowerCase();
                }
                if (dateField.textProperty().get().isEmpty()) {
                    dateString = "asdakj1232131kjasdka".toLowerCase();
                } else {
                    dateString = dateField.textProperty().get().toLowerCase();
                }

                if (cellValueDrink.contains(itemString)
                        || cellValueAppetizer.contains(itemString)
                        || cellValueMainCourse.contains(itemString)
                        || cellValueDessert.contains(itemString)
                        || cellValueExtra.contains(itemString)
                        || cellValueTable.contains(tableString)
                        || cellValueId.contains(idString)
                        || cellValueDate.contains(dateString)) {
                    tableItems.add(ObList.get(i));
                }
            }
            orderTable.setItems(tableItems);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setTotalPrice() {

    }

}
