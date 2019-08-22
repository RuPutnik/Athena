package ru.putnik.athena.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.putnik.athena.model.MainModel;
import ru.putnik.athena.pojo.GroupData;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ru.putnik.athena.AthenaAlerts.callAlert;
import static ru.putnik.athena.AthenaAlerts.callWaitAlert;


/**
 * Создано 14.08.2019
 */
public class MainController extends Application implements Initializable {
    private MainModel mainModel=new MainModel();
    private AddEditGroupController addEditGroupController =new AddEditGroupController();

    private static Stage stage;
   // private String pathToWordFile;
 //   private String pathToGroupFile;

 //   private String pathOpenWordFile;

    @FXML
    private MenuItem createList;
    @FXML
    private MenuItem openList;
    @FXML
    private MenuItem deleteList;
    @FXML
    private MenuItem saveList;
    @FXML
    private MenuItem addGroupContext;
    @FXML
    private MenuItem editGroupContext;
    @FXML
    private MenuItem deleteGroupContext;
    @FXML
    private MenuItem settingsMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem addGroupMenuItem;
    @FXML
    private MenuItem editGroupMenuItem;
    @FXML
    private MenuItem deleteGroupMenuItem;
    @FXML
    private MenuItem deleteAllGroupsMenuItem;
    @FXML
    private MenuItem findGroupMenuItem;
    @FXML
    private TableView<GroupData> groupTable;
    @FXML
    private TableColumn<GroupData,Integer> numberColumn;
    @FXML
    private TableColumn<GroupData,String> nameColumn;
    @FXML
    private TableColumn<GroupData,String> addressColumn;
    @FXML
    private TableColumn<GroupData,String> loginColumn;
    @FXML
    private TableColumn<GroupData,String> passwordColumn;
    @FXML
    private TableColumn<GroupData,String> emailColumn;
    @FXML
    private TableColumn<GroupData,String> commentColumn;
    @FXML
    private Label countGroupsLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        Parent parent=FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/mainView.fxml")));

        primaryStage.setScene(new Scene(parent));
        try {
            primaryStage.getIcons().add(new Image("icon/mainIcon.png"));
        }catch (Exception ex){
            System.out.println("Нет иконки главного окна");
        }
        primaryStage.setResizable(true);
        primaryStage.setWidth(780);
        primaryStage.setHeight(530);
        primaryStage.show();
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        pathToWordFile=settingController.getPathToWordBook();
        stage.setTitle("Athena");
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        addGroupMenuItem.setOnAction(event -> {
            if(addEditGroupController.showWindow(AddEditGroupController.TypeOperation.ADDING)) {
                countGroupsLabel.setText(String.valueOf(MainModel.getListData().size()));
                deleteGroupContext.setDisable(false);
                editGroupContext.setDisable(false);
            }
        });
        editGroupMenuItem.setOnAction(event -> {
            addEditGroupController.showWindow(AddEditGroupController.TypeOperation.EDITING);
        });
        deleteGroupMenuItem.setOnAction(event -> {
            int index=groupTable.getSelectionModel().getSelectedIndex();
            if(index!=-1) {
                MainModel.getListData().remove(index);
                countGroupsLabel.setText(String.valueOf(MainModel.getListData().size()));
            }else{
                callAlert(Alert.AlertType.WARNING,"Невозможно удалить группу данных",null,"Группа данных не выбрана");
            }
            if(MainModel.getListData().size()==0){
                deleteGroupContext.setDisable(true);
                editGroupContext.setDisable(true);
            }
        });
        deleteAllGroupsMenuItem.setOnAction(event -> {
            MainModel.getListData().clear();
        });


        /*  settingsMenuItem.setOnAction(event -> {settingController.createWindow();});*/
        addGroupContext.setOnAction(event -> {addGroupMenuItem.fire();});
        editGroupContext.setOnAction(event -> {editGroupMenuItem.fire();});
        deleteGroupContext.setOnAction(event -> {deleteGroupMenuItem.fire();});

        numberColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getNumber()));
        nameColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getName()));
        addressColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getAddress()));
        loginColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getLogin()));
        passwordColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getPassword()));
        emailColumn.setCellValueFactory(value-> new SimpleObjectProperty<>(value.getValue().getEmail()));
        commentColumn.setCellValueFactory(value->new SimpleObjectProperty<>(value.getValue().getComment()));
    /*    if(pathToWordFile!=null) {
            if(mainModel.openWordBook(pathToWordFile)){
                stage.setTitle(stage.getTitle() + " [" + pathToWordFile + "]");
                countWordsLabel.setText(String.valueOf(mainModel.getWordList().size()));
                pathOpenWordFile=pathToWordFile;
            }
        }*/
        groupTable.setItems(mainModel.getListData());
       if(MainModel.getListData().size()>0){
            deleteGroupContext.setDisable(false);
            editGroupContext.setDisable(false);
            addGroupContext.setDisable(false);
        }
    /*     createWordbook.setOnAction(event -> {
            String nameFile=createNewWordbook();

            String path=mainModel.createWordBook(nameFile);
            if(path!=null) {
                settingController.setPathToWordBook(path);
                addWord.setDisable(false);
            }

        });
        openWordbook.setOnAction(event -> {
            FileChooser chooser=new FileChooser();

            chooser.setTitle("Выберите файл со словарем");
            chooser.setInitialDirectory(new File((System.getenv("USERPROFILE") + "\\Desktop\\")));
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt","*.txt"));
            String path;
            File file=chooser.showOpenDialog(new Stage());
            if(file!=null) {
                path = file.getPath();
                    if (mainModel.openWordBook(path)) {
                        stage.setTitle("Word Note" + " [" + path + "]");
                        countWordsLabel.setText(String.valueOf(mainModel.getWordList().size()));
                        addWord.setDisable(false);
                        if (mainModel.getWordList().size() > 0) {
                            deleteWord.setDisable(false);
                            editWord.setDisable(false);
                        }else{
                            deleteWord.setDisable(true);
                            editWord.setDisable(true);
                        }
                        pathOpenWordFile=path;
                    }
            }
        });
        deleteWordbook.setOnAction(event -> {
            if(pathOpenWordFile!=null&&!pathOpenWordFile.equals("")&&new File(pathOpenWordFile).exists()){
                if(callConfirmationAlert("Удаление словаря",null,"Вы действительно хотите удалить файл словаря?").get()==ButtonType.OK) {
                    new File(pathOpenWordFile).delete();
                    stage.setTitle("Word Note");
                    pathOpenWordFile=null;
                }
            }else{
                callAlert(Alert.AlertType.WARNING,"Невозможно удалить файл словаря",null,"Словарь не выбран или файл не существует");
            }
        });
        deleteAllWordMenuItem.setOnAction(event -> {
            if(mainModel.getWordList().size()>0){
                mainModel.getWordList().clear();
                countWordsLabel.setText(String.valueOf(mainModel.getWordList().size()));
                deleteWord.setDisable(true);
                editWord.setDisable(true);
            }
        });
        saveWordbook.setOnAction(event -> {
                if (pathOpenWordFile != null && !pathOpenWordFile.equals("") && new File(pathOpenWordFile).exists()) {
                    new File(pathOpenWordFile).delete();
                    String path=createNewWordbook();
                    if(path!=null) {
                        mainModel.rewriteFile(path);
                    }
                }else {
                    String path=createNewWordbook();
                    if(path!=null) {
                        if(mainModel.rewriteFile(path)){
                            stage.setTitle("Word Note" + " [" + path + "]");
                            pathOpenWordFile=path;
                        }
                    }

                }
        });*/
        findGroupMenuItem.setOnAction(event -> {
            if(MainModel.getListData().size()>0){
                int numberWord = -1;
                String valueCategory="";
                String typeCategory="";
                boolean resumeFind=true;
                double positionAlertX=-1;
                double positionAlertY=-1;
                while (true) {
                    Alert findAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    findAlert.setTitle("Поиск группы данных");
                    findAlert.setHeaderText("Выберите критерий поиска и введите искомое значение:");
                    findAlert.initModality(Modality.NONE);
                    findAlert.getDialogPane().toBack();
                    if(positionAlertX!=-1){
                        findAlert.setX(positionAlertX);
                    }
                    if(positionAlertY!=-1){
                        findAlert.setY(positionAlertY);
                    }
                    try {
                    ((Stage) findAlert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("icon/mainIcon.png"));
                    }catch (Exception ex){
                        System.out.println("Нет иконки окна");
                    }
                    ((Stage) findAlert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                    HBox box = new HBox();
                    box.setSpacing(5);
                    ComboBox<String> parameterWord = new ComboBox<>(FXCollections.observableArrayList("Свободно", "Название", "Адрес", "Логин","Пароль","Email"));
                    parameterWord.setPromptText("Критерий поиска");
                    TextField textField = new TextField();
                    box.getChildren().addAll(parameterWord, textField);

                    findAlert.getDialogPane().setContent(box);
                    findAlert.getButtonTypes().clear();
                    findAlert.getButtonTypes().addAll(new ButtonType("Найти далее",ButtonBar.ButtonData.OK_DONE), new ButtonType("Выход",ButtonBar.ButtonData.CANCEL_CLOSE));
                    if(typeCategory!=null&&!typeCategory.equals("")){
                        parameterWord.setValue(typeCategory);
                    }
                    textField.setText(valueCategory);
                    ButtonType type = findAlert.showAndWait().get();

                    if (type.getText().equals("Найти далее")) {
                        positionAlertX=findAlert.getX();
                        positionAlertY=findAlert.getY();
                        typeCategory=parameterWord.getValue();
                        valueCategory=textField.getText();

                        if (!textField.getText().equals("")) {
                            for (int n = 0; n < groupTable.getItems().size(); n++) {
                                if(n>numberWord&&resumeFind) {
                                    String category="";
                                    if(parameterWord.getValue()!=null){
                                        category=parameterWord.getValue();
                                    }
                                    switch (category) {
                                        case "Название": {
                                            if (groupTable.getItems().get(n).getName().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Адрес": {
                                            if (groupTable.getItems().get(n).getAddress().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Логин": {
                                            if (groupTable.getItems().get(n).getLogin().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Пароль": {
                                            if (groupTable.getItems().get(n).getPassword().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Email": {
                                            if (groupTable.getItems().get(n).getEmail().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "": {
                                            String name = groupTable.getItems().get(n).getName();
                                            String address = groupTable.getItems().get(n).getAddress();
                                            String login = groupTable.getItems().get(n).getLogin();
                                            String password = groupTable.getItems().get(n).getPassword();
                                            String email = groupTable.getItems().get(n).getEmail();
                                            if (name.toLowerCase().equals(textField.getText().toLowerCase()) || address.toLowerCase().equals(textField.getText().toLowerCase()) ||
                                                    login.toLowerCase().equals(textField.getText().toLowerCase())||password.toLowerCase().equals(textField.getText().toLowerCase())||email.toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Свободно": {
                                            String name = groupTable.getItems().get(n).getName();
                                            String address = groupTable.getItems().get(n).getAddress();
                                            String login = groupTable.getItems().get(n).getLogin();
                                            String email = groupTable.getItems().get(n).getEmail();
                                            String password = groupTable.getItems().get(n).getPassword();
                                            if (name.toLowerCase().equals(textField.getText().toLowerCase()) || address.toLowerCase().equals(textField.getText().toLowerCase()) ||
                                                    login.toLowerCase().equals(textField.getText().toLowerCase())||password.toLowerCase().equals(textField.getText().toLowerCase())||email.toLowerCase().equals(textField.getText().toLowerCase())) {
                                                groupTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            if(callWaitAlert(Alert.AlertType.WARNING, "Поиск группы данных", null, "Строка поиска пуста").get()==ButtonType.OK) {
                                continue;
                            }
                        }
                        if (numberWord == -1) {
                            if(callWaitAlert(Alert.AlertType.INFORMATION, "Поиск группы данных", null, "Группа данных по данному запросу не обнаружена").get()==ButtonType.OK) {
                                continue;
                            }
                        }
                        resumeFind=true;
                        if(numberWord==groupTable.getItems().size()-1) {
                            numberWord = -1;//Что бы бегать по кругу списка
                        }
                    } else {
                        findAlert.close();
                        break;
                    }
                }
            } else {
                callAlert(Alert.AlertType.WARNING, "Невозможно найти группу данных", null, "Группы данных отсутствуют");
            }

        });
        exitMenuItem.setOnAction(event -> {
            stage.close();
        });
    }
 /*   private String createNewWordbook(){
        Alert newWordbook=new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)newWordbook.getDialogPane().getScene().getWindow()).getIcons().add(new Image("icon/mainIcon.png"));
        newWordbook.setTitle("Создание словаря");
        newWordbook.setHeaderText(null);
        VBox box=new VBox();
        HBox hBox=new HBox();
        Label infoLabel=new Label("Укажите путь до словаря");
        TextField nameField=new TextField(System.getenv("USERPROFILE") + "\\Desktop\\"+"wordbook.txt");
        Button pickFile=new Button("Выбрать папку");
        hBox.getChildren().add(nameField);
        hBox.getChildren().add(pickFile);
        hBox.setSpacing(5);

        box.getChildren().add(infoLabel);
        box.getChildren().add(hBox);

        nameField.setPrefWidth(250);

        pickFile.setOnAction(event -> {
            DirectoryChooser chooser=new DirectoryChooser();

            chooser.setTitle("Выберите директорию");
            chooser.setInitialDirectory(new File((System.getenv("USERPROFILE") + "\\Desktop\\")));

            nameField.setText(chooser.showDialog(new Stage())+"\\wordbook.txt");
        });

        newWordbook.getDialogPane().setContent(box);
        if(newWordbook.showAndWait().get()==ButtonType.OK) {
            if(!nameField.getText().equals("")) {
                if (!new File(nameField.getText()).exists()) {
                    return nameField.getText();
                }else{
                    callAlert(Alert.AlertType.WARNING,"Невозможно создать словарь",null,"Файл по данному адресу уже существует");
                    return null;
                }
            }else{
                callAlert(Alert.AlertType.WARNING,"Невозможно создать словарь",null,"Адрес файла не указан");
                return null;
            }
        }else{
            return null;
        }
    }*/

    public static void play(){
        launch();
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public Stage getStage() {
        return stage;
    }
  /*  public String getPathToWordFile() {
        return pathToWordFile;
    }

    public String getPathToGroupFile() {
        return pathToGroupFile;
    }

    public void setPathToGroupFile(String pathToGroupFile) {
        this.pathToGroupFile = pathToGroupFile;
    }*/
}
