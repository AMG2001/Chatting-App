package gov.iti.jets.AdminPanel.Controllers;

import DTO.NotificationDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.AdminPanel.Models.UserMapper;
import gov.iti.jets.AdminPanel.Models.UserModel;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.ServerControlCallbackHandler;
import gov.iti.jets.Service.Utilities.FileSystemUtil;
import gov.iti.jets.Service.Utilities.FileType;
import gov.iti.jets.Service.Utilities.OnlineUserManager;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.time.LocalDate;
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
    private ListView<String> announcementLog;
    @FXML
    private TextField announcementTextfield;
    @FXML
    private TextField phoneInput;

    @FXML
    private ListView<String> serverLog;
    @FXML
    private TableView<UserModel> userTable;
    @FXML
    private TableColumn<UserModel, String> countryTc;
    @FXML
    private TableColumn<UserModel, String> dobTc;
    @FXML
    private TableColumn<UserModel, String> emailTc;
    @FXML
    private TableColumn<UserModel, String> genderTc;
    @FXML
    private TableColumn<UserModel, String> phoneNumberTc;
    @FXML
    private TableColumn<UserModel, String> usernameTc;
    @FXML
    private TableColumn<UserModel, String> statusTc;
    private final ObservableList<String> announcementList = FXCollections.observableArrayList();
    private final ObservableList<String> processLogList = FXCollections.observableArrayList();
    private ObservableList<UserModel> userList;
    private ObservableList<UserModel> userTableList = FXCollections.observableArrayList();
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
    @FXML
    private Label statusLabel;
    @FXML
    private Button toggleButton;
    private boolean serverOnline = true;
    UserDao userDao = new UserDoaImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList = getUsersFromDatabase();

        onlineUsersPie.setData(getPieChartData(userList));
        initializeBarChart();
        initializeAnnouncementLog();
        initializeProcessLog();
        //updateBarChartData();

        executorService.scheduleAtFixedRate(this::updatePieChart, 0, 10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(this::fetchUsersFromDatabase, 0, 10, TimeUnit.SECONDS);
        initializeTableView();
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
        List<User> allUsers = userDao.getAll();
        List<UserModel> allUserModels = UserMapper.mapToUserModels(allUsers);
        return FXCollections.observableArrayList(allUserModels);
    }

    //-----------------------------------------AGEBARCHART------------------------------------
    //Initializes the Barchart Data and series
    //Binds the Series with the Userlist Age
    //Adds a listener to Userlist to call UpdateBarchart whenever the userlist is changed

    private void initializeBarChart() {
        // Bind the observable lists to the series
        ageGroup1Series.setData(ageGroup1Data);
        ageGroup2Series.setData(ageGroup2Data);
        ageGroup3Series.setData(ageGroup3Data);

        ageGroup1Series.getData().forEach(data -> data.YValueProperty().bind(Bindings.createIntegerBinding(() -> (int) userList.stream().filter(user -> user.getAge() < 18).count(), userList)));

        ageGroup2Series.getData().forEach(data -> data.YValueProperty().bind(Bindings.createIntegerBinding(() -> (int) userList.stream().filter(user -> user.getAge() >= 19 && user.getAge() < 50).count(), userList)));

        ageGroup3Series.getData().forEach(data -> data.YValueProperty().bind(Bindings.createIntegerBinding(() -> (int) userList.stream().filter(user -> user.getAge() >= 50).count(), userList)));

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

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data(UserStatus.ONLINE.name() + " (" + onlineCount + ")", onlineCount), new PieChart.Data(UserStatus.OFFLINE.name() + " (" + offlineCount + ")", offlineCount), new PieChart.Data(UserStatus.BUSY.name() + " (" + busyCount + ")", busyCount), new PieChart.Data(UserStatus.AWAY.name() + " (" + awayCount + ")", awayCount));
        return pieChartData;
    }

    //----------------------------------------USERTABLEVIEW---------------------------------------
    private void initializeTableView() {
        phoneNumberTc.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        usernameTc.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailTc.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        genderTc.setCellValueFactory(cellData -> cellData.getValue().genderProperty().asString());
        countryTc.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        statusTc.setCellValueFactory(cellData -> cellData.getValue().statusProperty().asString());
        dobTc.setCellValueFactory(cellData -> cellData.getValue().dobProperty().asString());

//        // Add listener for TableView row editing
//        usernameTc.setCellFactory(TextFieldTableCell.forTableColumn());
//        usernameTc.setOnEditCommit(this::onNameEditCommit);
//
//        emailTc.setCellFactory(TextFieldTableCell.forTableColumn());
//        emailTc.setOnEditCommit(this::onEmailEditCommit);
//
//        countryTc.setCellFactory(TextFieldTableCell.forTableColumn());
//        countryTc.setOnEditCommit(this::onCountryEditCommit);
//
//        genderTc.setCellFactory(TextFieldTableCell.forTableColumn());
//        genderTc.setOnEditCommit(this::onGenderEditCommit);
//
//        userTable.getSelectionModel().setCellSelectionEnabled(true);
        // Add your listener for the onEdit event of each row here

        //Bind the user List to the Table
        userTable.setItems(userTableList);
    }

//    private void onNameEditCommit(TableColumn.CellEditEvent<UserModel, String> event) {
//        UserModel editedEmployee = event.getRowValue();
//        editedEmployee.setName(event.getNewValue());
//        userDao.updateUserName(editedEmployee.getName(),editedEmployee.getPhoneNumber());
//
//        //Notify All online contacts
//        ContactCallbackHandler handler  = new ContactCallbackHandler();
//        List<User> users = userDao.getAllContactsByPhone(editedEmployee.getPhoneNumber());
//
//        List<RemoteCallbackInterface> onlineFriends = OnlineUserManager.
//                getFriendsFromOnlineList(users.stream()
//                        .map(User::getPhoneNumber)
//                        .toList());
//
//        handler.updateContactName(editedEmployee.getPhoneNumber(),editedEmployee.getName(),onlineFriends);
//
//
//    }

//    private void onGenderEditCommit(TableColumn.CellEditEvent<UserModel, String> event) {
//        UserModel editedEmployee = event.getRowValue();
//        try {
//            editedEmployee.setGender(Gender.valueOf(event.getNewValue().toUpperCase()));
//            userDao.updateUserGender(editedEmployee.getGender(),editedEmployee.getPhoneNumber());
//        } catch (IllegalArgumentException e) {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Illegal Argument");
//            alert.setHeaderText(null); // No header text
//            alert.setContentText("Please enter a correct Gender");
//            // Show the alert box
//            alert.showAndWait();
//        }
//    }

//    private void onEmailEditCommit(TableColumn.CellEditEvent<UserModel, String> event) {
//        UserModel editedEmployee = event.getRowValue();
//        editedEmployee.setEmail(event.getNewValue());
//        userDao.updateUserEmail(editedEmployee.getEmail(),editedEmployee.getPhoneNumber());
//    }
//
//    private void onCountryEditCommit(TableColumn.CellEditEvent<UserModel, String> event) {
//        UserModel editedEmployee = event.getRowValue();
//        editedEmployee.setCountry(event.getNewValue());
//        userDao.updateUserCountry(editedEmployee.getCountry(),editedEmployee.getPhoneNumber());
//    }

    @FXML
    void searchByPhone(ActionEvent event) {
        if (!phoneInput.getText().isEmpty()) {
            String phoneNo = phoneInput.getText();
            phoneInput.clear();
            User userDomain = userDao.getById(phoneNo);
            if (userDomain != null) {
                UserModel user = UserMapper.mapToUserModel(userDomain);
                // Replace all table data with the selected employee
                userTableList.setAll(user);
                phoneInput.clear();
            } else {
                // Employee not found, show a modal alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The employee with the specified details was not found.");

                // Customize the alert dialog pane
                alert.getDialogPane().setPrefWidth(300);
                Label label = new Label("Employee Not Found");
                label.setStyle("-fx-font-weight: bold;");
                alert.getDialogPane().setHeader(label);

                // Add OK button
                alert.getButtonTypes().setAll(ButtonType.OK);

                // Show the alert and wait for the user to acknowledge
                alert.showAndWait();
            }
        }

    }
    @FXML
    void addNewUser(ActionEvent event) {
        // Create a Dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Enter User Details");
        // Set the header text
        dialog.setHeaderText(null);

        // Create Labels and TextFields for the new items
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();

        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();

        Label genderLabel = new Label("Gender:");
        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getItems().addAll("MALE", "FEMALE");

        Label countryLabel = new Label("Country:");
        TextField countryTextField = new TextField();

        Label userProfileLabel = new Label("User Profile Picture:");

        // File selection
        FileChooser fileChooser = new FileChooser();
        Button selectFileButton = new Button("Select File");
        ImageView previewImageView = new ImageView();
        previewImageView.setPreserveRatio(true);
        previewImageView.setFitWidth(150);

        final byte[][] fileBytes = new byte[1][1];

        selectFileButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(dialog.getOwner());
            if (file != null) {
                try {
                    fileBytes[0] = Files.readAllBytes(file.toPath());
                } catch (IOException ex) {
                    System.out.println("Error reading image as bytes ");
                }
                Image image = new Image(file.toURI().toString());
                previewImageView.setImage(image);
            }
        });

        Label dateOfBirthLabel = new Label("Date of Birth:");
        DatePicker datePicker = new DatePicker();

        // Create a GridPane for the input fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add the components to the GridPane
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTextField, 1, 0);

        gridPane.add(phoneNumberLabel, 0, 1);
        gridPane.add(phoneNumberTextField, 1, 1);

        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);

        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailTextField, 1, 3);

        gridPane.add(genderLabel, 0, 4);
        gridPane.add(genderChoiceBox, 1, 4);

        gridPane.add(countryLabel, 0, 5);
        gridPane.add(countryTextField, 1, 5);

        gridPane.add(userProfileLabel, 0, 6);
        gridPane.add(selectFileButton, 1, 6);

        // Set column span for the image preview
        GridPane.setColumnSpan(previewImageView, 1);
        GridPane.setRowSpan(previewImageView, 1); // Adjust the row span as needed
        GridPane.setHalignment(previewImageView, HPos.LEFT); // Align to the left

        gridPane.add(previewImageView, 2, 6);

        gridPane.add(dateOfBirthLabel, 0, 7);
        gridPane.add(datePicker, 1, 7);

        // Set the GridPane as the content of the Dialog
        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().setPrefSize(600, 600);

        // Add buttons to the dialog
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        // Show the dialog and wait for the user's response
        dialog.showAndWait().ifPresent(result -> {
            if (result.equals(okButton)) {
                // User clicked OK, handle the entered values
                // Must add verification here
                String username = usernameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String password = passwordField.getText();
                String email = emailTextField.getText();
                String gender = genderChoiceBox.getValue();
                String country = countryTextField.getText();
                LocalDate dateOfBirth = datePicker.getValue();

                //Save Image on file system
                String imagePath = FileSystemUtil.storeByteArrayAsFile(fileBytes[0], phoneNumber, FileType.PROFILE_PIC);

                // Handle the values as needed
                User user = new User(phoneNumber, username, email, password, country, UserStatus.OFFLINE, Gender.valueOf(gender), "Hello", imagePath, dateOfBirth);


                //Add the new user to the DB
                userDao.add(user);
                fetchUsersFromDatabase();

                System.out.println("Employee details Successfully sent to ServiceLayer");
            }
        });

    }

    @FXML
    void getAllUsers(ActionEvent event) {
        userTableList.setAll(userList);
    }

    //-------------------------------ANNOUNCEMENTLOG--------------------------------------

    //Get Announcements from database
    private void initializeAnnouncementLog() {
        announcementLog.setItems(announcementList);
    }

    public void appendToAnnouncementLog(String message) {
        announcementList.add(message);
    }
    @FXML
    void sendAnnouncement(ActionEvent event) {
        String notificationBody = announcementTextfield.getText();
        String type = NotificationType.SYSTEM.toString();
        NotificationDTO announcement = new NotificationDTO("1", type, LocalDateTime.now(), notificationBody);
        if (!OnlineUserManager.getOnlineUsersCallbackInterfaces().isEmpty()) {

            OnlineUserManager.getOnlineUsersCallbackInterfaces().stream().forEach((e) -> {
                try {
                    e.recieveNotification(announcement);
                } catch (RemoteException ex) {
                    System.out.println("Failed to send server announcement " + announcement.getBody());
                }
            });
            Platform.runLater(() -> appendToAnnouncementLog(notificationBody));
        }
        announcementTextfield.clear();
    }
    //--------------------------------PROCESSLOG--------------------------------------------------
    private void initializeProcessLog() {
        serverLog.setItems(processLogList);
    }

    public void appendToProcessLog(String message) {
        processLogList.add(message);
    }

    //---------------------------------SERVERSTATUS-------------------------------------------------
    @FXML
    private void toggleServer(ActionEvent event) {
        List<RemoteCallbackInterface> allUsers = OnlineUserManager.getOnlineUsersCallbackInterfaces();
        ServerControlCallbackHandler handler = new ServerControlCallbackHandler();

        if(serverOnline)
            handler.serverShutdown(allUsers);
        else
            handler.serverRestart(allUsers);

        serverOnline = !serverOnline;
        updateStatus();
    }
    private void updateStatus() {
        if (serverOnline) {
            statusLabel.setText("Server is online");
            statusLabel.getStyleClass().setAll("online-label");
            toggleButton.setText("Turn Server Off");
            toggleButton.setStyle("    -fx-background-color: #cc0000;\n" +
                    "    -fx-text-fill: white;");
        } else {
            statusLabel.setText("Server is offline");
            statusLabel.getStyleClass().setAll("offline-label");
            toggleButton.setText("Turn Server On");
            toggleButton.setStyle("    -fx-background-color: #3b943b;\n" +
                    "    -fx-text-fill: white;");
        }
    }
}
