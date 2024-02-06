package gov.iti.jets.AdminPanel.Controllers;

import gov.iti.jets.AdminPanel.Models.UserModel;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Number> ageDistributionGraph;
    @FXML
    private BarChart<String, Number> statisticsGraph;
    @FXML
    private PieChart onlineUsersPie;
    @FXML
    private ListView<String> announcementLog;
    @FXML
    private ListView<String> serverLog;
    @FXML
    private ToggleButton serverToggle;
    @FXML
    private TableView<UserModel> userTable;
    @FXML
    private TableColumn<UserModel,String> countryTc;
    @FXML
    private TableColumn<UserModel, LocalDateTime> dobTc;
    @FXML
    private TableColumn<UserModel, String> emailTc;
    @FXML
    private TableColumn<UserModel, Gender> genderTc;
    @FXML
    private TableColumn<UserModel, String> phoneNumberTc;
    @FXML
    private TableColumn<UserModel, String> usernameTc;
    @FXML
    private TableColumn<UserModel, UserStatus> statusTc;

    private final ObservableList<UserModel> usersList = FXCollections.observableArrayList();
    private final ObservableList<String> announcementList = FXCollections.observableArrayList();
    private final ObservableList<String> processLogList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCharts();
        initializeTableView();
        initializeAnnouncementLog();
        initializeProcessLog();
    }

    @FXML
    void addNewUser(ActionEvent event) {

    }

    public void initializeCharts() {
        // Initialize ageBarChart
        XYChart.Series<String, Number> ageDataSeries = new XYChart.Series<>();
        ageDataSeries.getData().add(new XYChart.Data<>("18 and lower", 5)); // Example data
        ageDataSeries.getData().add(new XYChart.Data<>("19 to 40", 15));  // Example data
        ageDataSeries.getData().add(new XYChart.Data<>("41+", 8));        // Example data
        ageDistributionGraph.getData().add(ageDataSeries);

        // Initialize itemsBarChart
        XYChart.Series<String, Number> itemsDataSeries = new XYChart.Series<>();
        itemsDataSeries.getData().add(new XYChart.Data<>("Item 1", 10));  // Example data
        itemsDataSeries.getData().add(new XYChart.Data<>("Item 2", 20));  // Example data
        itemsDataSeries.getData().add(new XYChart.Data<>("Item 3", 5));   // Example data
        statisticsGraph.getData().add(itemsDataSeries);

        // Initialize onlineOfflinePieChart
        ObservableList<PieChart.Data> onlineOfflineData = FXCollections.observableArrayList(
                new PieChart.Data("Online", 25),   // Example data
                new PieChart.Data("Offline", 15)   // Example data
        );
        onlineUsersPie.setData(onlineOfflineData);
    }

    private void initializeTableView() {
        phoneNumberTc.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        usernameTc.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailTc.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderTc.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryTc.setCellValueFactory(new PropertyValueFactory<>("country"));
        statusTc.setCellValueFactory(new PropertyValueFactory<>("status"));
        dobTc.setCellValueFactory(new PropertyValueFactory<>("dob"));

        // Add listener for TableView row editing
        userTable.setEditable(true);
        userTable.getSelectionModel().setCellSelectionEnabled(true);

        // Add your listener for the onEdit event of each row here
    }

    private void initializeAnnouncementLog() {
        announcementLog.setItems(announcementList);
    }

    private void initializeProcessLog() {
        serverLog.setItems(processLogList);
    }
    // Method to append a message to the server log

    public void appendToServerLog(String message) {
        announcementList.add(message);
    }
    // Method to append a message to the process log and perform processing

    public void appendToProcessLog(String message) {
        processLogList.add(message);

        // Perform processing here
    }
    // Method to handle adding a user

    public void addUser() {
        // Implement the logic to show a pop-up pane for adding a user
        // Retrieve input and create a new UserModel
        // Add the new user to the usersList
        UserModel newUser = new UserModel();
        usersList.add(newUser);
    }
    // Method to handle deleting a user

    public void deleteUser() {
        // Implement the logic to delete the selected user from usersList and database
        UserModel selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            usersList.remove(selectedUser);
            // Implement deletion from the database
        }
    }

    @FXML
    void changeServerStatus(ActionEvent event) {
    }

    @FXML
    void deleteSelectedUser(ActionEvent event) {
    }

    @FXML
    void editCountry(ActionEvent event) {
    }

    @FXML
    void editDOB(ActionEvent event) {
    }

    @FXML
    void editEmail(ActionEvent event) {
    }

    @FXML
    void editGender(ActionEvent event) {
    }

    @FXML
    void editSelectedUser(ActionEvent event) {
    }

    @FXML
    void editUserName(ActionEvent event) {
    }

    @FXML
    void sendAnnouncement(ActionEvent event) {
    }
}
