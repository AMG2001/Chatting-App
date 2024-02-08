package gov.iti.jets.AdminPanel.Controllers;

import gov.iti.jets.AdminPanel.Models.OnlineUsersPieChartModel;
import gov.iti.jets.AdminPanel.Models.UserMapper;
import gov.iti.jets.AdminPanel.Models.UserModel;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Number> ageDistributionGraph;
    @FXML
    private BarChart<String, Number> statisticsGraph;
    @FXML
    private ToggleButton serverToggle;
    @FXML
    private ListView<String> announcementLog;
    @FXML
    private ListView<String> serverLog;
    @FXML
    private TableView<UserModel> userTable;
    @FXML
    private TableColumn<UserModel, String> countryTc;
    @FXML
    private TableColumn<UserModel, LocalDate> dobTc;
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
    private final ObservableList<String> announcementList = FXCollections.observableArrayList();
    private final ObservableList<String> processLogList = FXCollections.observableArrayList();
    private ObservableList<UserModel> userList;
    @FXML
    private PieChart onlineUsersPie;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ObservableList<XYChart.Data<String, Number>> ageGroup1Data = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Number>> ageGroup2Data = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data<String, Number>> ageGroup3Data = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> ageGroup1Series = new XYChart.Series<>("18 & Lower", ageGroup1Data);
    private XYChart.Series<String, Number> ageGroup2Series = new XYChart.Series<>("19 to 50 ", ageGroup2Data);
    private XYChart.Series<String, Number> ageGroup3Series = new XYChart.Series<>("50+", ageGroup3Data);
    @FXML
    private CategoryAxis AgeGraphXAxis;

    @FXML
    private NumberAxis AgeYAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList = getUsersFromDatabase();

        onlineUsersPie.setData(getPieChartData(userList));
        initializeBarChart();
        //updateBarChartData();
        // Bind the ListView to the observable list
        serverLog.setItems(processLogList);

        executorService.scheduleAtFixedRate(this::updatePieChart, 0, 10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(this::fetchUsersFromDatabase, 0, 10, TimeUnit.SECONDS);
    }
//------------------------------------DATABASE ACCESS -----------------------------------

    //Updates the Observable user list from the database
    private void fetchUsersFromDatabase() {
        // Simulate fetching users from a database
        List<UserModel> newUsers = getUsersFromDatabase();
        // Update the userList on the JavaFX Application Thread
        Platform.runLater(() -> userList.setAll(newUsers));
    }

    private ObservableList<UserModel> getUsersFromDatabase() {
        UserDao userDao = new UserDoaImpl();
        List<User> allUsers = userDao.getAll();
        List<UserModel> allUserModels = UserMapper.mapToUserModels(allUsers);
        return FXCollections.observableArrayList(allUserModels);
    }

    //-----------------------------------------AGEBARCHART------------------------------------
    //Initializes the Barchart Data and series
    //Binds the Series with the Userlist Age
    //Adds a listener to Userlist to call UpdateBarchart whenever the userlist is changed

    private void initializeBarChart()
    {
        // Bind the observable lists to the series
        ageGroup1Series.setData(ageGroup1Data);
        ageGroup2Series.setData(ageGroup2Data);
        ageGroup3Series.setData(ageGroup3Data);

        ageGroup1Series.getData().forEach(data -> data.YValueProperty().bind(
                Bindings.createIntegerBinding(() ->
                        (int) userList.stream().filter(user -> user.getAge() < 18).count(), userList)));

        ageGroup2Series.getData().forEach(data -> data.YValueProperty().bind(
                Bindings.createIntegerBinding(() ->
                        (int) userList.stream().filter(user -> user.getAge() >= 19 && user.getAge() < 50).count(), userList)));

        ageGroup3Series.getData().forEach(data -> data.YValueProperty().bind(
                Bindings.createIntegerBinding(() ->
                        (int) userList.stream().filter(user -> user.getAge() >= 50).count(), userList)));

        // Initialize the BarChart
        AgeGraphXAxis.setCategories(FXCollections.observableArrayList("18 & Lower", "19-50", "50+"));
        AgeYAxis.setLabel("Number of Users");
        ageDistributionGraph.getData().addAll(ageGroup1Series, ageGroup2Series, ageGroup3Series);
        userList.addListener((ListChangeListener<UserModel>) change -> updateBarChartData());
    }

    //Clears Old barChart data and creates new data from the usermodel
    private void updateBarChartData() {
        ageGroup1Data.clear();
        ageGroup2Data.clear();
        ageGroup3Data.clear();

        for (UserModel user : userList) {
            int age = user.getAge();

            if (age < 30) {
                ageGroup1Data.add(new XYChart.Data<>("18 & Lower", ageGroup1Data.size() + 1));
            } else if (age < 50) {
                ageGroup2Data.add(new XYChart.Data<>("19-50", ageGroup2Data.size() + 1));
            } else {
                ageGroup3Data.add(new XYChart.Data<>("50+", ageGroup3Data.size() + 1));
            }
        }
    }

    //-------------------------------------ONLINEUSERPIECHART----------------------------------------------
    private void updatePieChart() {
        executorService.submit(() -> {
            try {
                Platform.runLater(() -> {
                    ObservableList<PieChart.Data> newData = getPieChartData(userList);
                    onlineUsersPie.setData(newData);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private ObservableList<PieChart.Data> getPieChartData(ObservableList<UserModel> userModels) {
        long onlineCount = userModels.stream().filter(user -> UserStatus.ONLINE.equals(user.getStatus())).count();
        long offlineCount = userModels.stream().filter(user -> UserStatus.OFFLINE.equals(user.getStatus())).count();
        long busyCount = userModels.stream().filter(user -> UserStatus.BUSY.equals(user.getStatus())).count();
        long awayCount = userModels.stream().filter(user -> UserStatus.AWAY.equals(user.getStatus())).count();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(UserStatus.ONLINE.name() + " (" + onlineCount + ")", onlineCount),
                new PieChart.Data(UserStatus.OFFLINE.name() + " (" + offlineCount + ")", offlineCount),
                new PieChart.Data(UserStatus.BUSY.name() + " (" + busyCount + ")", busyCount),
                new PieChart.Data(UserStatus.AWAY.name() + " (" + awayCount + ")", awayCount)
        );
        return pieChartData;
    }

    @FXML
    void addNewUser(ActionEvent event) {

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


    //Get Announcements from database
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
    @FXML
    void changeServerStatus(ActionEvent event) {
    }
    @FXML
    void deleteSelectedUser(ActionEvent event) {
    }

    @FXML
    void sendAnnouncement(ActionEvent event) {
    }

    @FXML
    void editSelectedUser(ActionEvent event) {

    }
}
