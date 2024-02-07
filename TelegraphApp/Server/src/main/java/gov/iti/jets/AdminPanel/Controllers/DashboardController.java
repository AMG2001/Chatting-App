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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
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

    private final ObservableList<String> announcementList = FXCollections.observableArrayList();
    private final ObservableList<String> processLogList = FXCollections.observableArrayList();
    private final OnlineUsersPieChartModel pieChartModel = new OnlineUsersPieChartModel();
    private ObservableList<UserModel> observableUserModel;
    @FXML
    private PieChart onlineUsersPie;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableUserModel = getUsersFromDatabase();
        onlineUsersPie.setData(getChartData(observableUserModel));

        executorService.scheduleAtFixedRate(this::updatePieChart, 0, 5, TimeUnit.SECONDS);


  //      initializeCharts();
//        initializeTableView();
//        initializeAnnouncementLog();
//        initializeProcessLog();
    }

    @FXML
    void addNewUser(ActionEvent event) {

    }

    private void updatePieChart() {
        executorService.submit(() -> {
            try {
                // Fetch users from the database on a background thread
                List<UserModel> users = getUsersFromDatabase();

                // Update the PieChart with the new data on the JavaFX Application Thread
                Platform.runLater(() -> {
                    ObservableList<PieChart.Data> newData = getChartData(FXCollections.observableArrayList(users));
                    onlineUsersPie.setData(newData);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void initializeCharts() {
        observableUserModel.addAll(getUsersFromDatabase());
        onlineUsersPie.setData(getChartData(observableUserModel));
    }

    private ObservableList<UserModel> getUsersFromDatabase() {
        UserDao userDao = new UserDoaImpl();
        List<User> allUsers = userDao.getAll();
        List<UserModel> allUserModels = UserMapper.mapToUserModels(allUsers);
        return FXCollections.observableArrayList(allUserModels);
    }

    private ObservableList<PieChart.Data> getChartData(ObservableList<UserModel> userModels) {
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
