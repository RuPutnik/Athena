package ru.putnik.athena.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.putnik.athena.model.MainModel;
import ru.putnik.athena.pojo.GroupData;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


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
            addEditGroupController.showWindow(AddEditGroupController.TypeOperation.ADDING);
        });
        editGroupMenuItem.setOnAction(event -> {
            addEditGroupController.showWindow(AddEditGroupController.TypeOperation.EDITING);
        });

 /*       addWordMenuItem.setOnAction(event -> {
            addEditController.addWord();
            if(mainModel.getWordList().size()>0){
                countWordsLabel.setText(String.valueOf(mainModel.getWordList().size()));
                deleteWord.setDisable(false);
                editWord.setDisable(false);
            }
        });
        editWordMenuItem.setOnAction(event -> {addEditController.editWord(wordTable.getSelectionModel().getSelectedIndex());});
        deleteWordMenuItem.setOnAction(event -> {
            int index=wordTable.getSelectionModel().getSelectedIndex();
            if(index!=-1) {
                mainModel.deleteWord(index);
                countWordsLabel.setText(String.valueOf(mainModel.getWordList().size()));
            }else{
                callAlert(Alert.AlertType.WARNING,"Невозможно удалить слово",null,"Слово не выбрано");
            }
            if(mainModel.getWordList().size()==0){
                deleteWord.setDisable(true);
                editWord.setDisable(true);
            }
        });
        settingsMenuItem.setOnAction(event -> {settingController.createWindow();});
        addWord.setOnAction(event -> {addWordMenuItem.fire();});
        editWord.setOnAction(event -> {editWordMenuItem.fire();});
        deleteWord.setOnAction(event -> {
            deleteWordMenuItem.fire();
        });*/

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
/*        if(mainModel.getWordList().size()>0){
            deleteWord.setDisable(false);
            editWord.setDisable(false);
            addWord.setDisable(false);
        }
        createWordbook.setOnAction(event -> {
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
        });
        findWordMenuItem.setOnAction(event -> {
            if (pathOpenWordFile != null && !pathOpenWordFile.equals("") && new File(pathOpenWordFile).exists()) {
                int numberWord = -1;
                String valueCategory="";
                String typeCategory="";
                boolean resumeFind=true;
                double positionAlertX=-1;
                double positionAlertY=-1;
                while (true) {
                    Alert findAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    findAlert.setTitle("Поиск слова");
                    findAlert.setHeaderText("Выберите критерий поиска и введите искомое значение:");
                    findAlert.initModality(Modality.NONE);
                    findAlert.getDialogPane().toBack();
                    if(positionAlertX!=-1){
                        findAlert.setX(positionAlertX);
                    }
                    if(positionAlertY!=-1){
                        findAlert.setY(positionAlertY);
                    }
                    ((Stage) findAlert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("icon/mainIcon.png"));
                    ((Stage) findAlert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                    HBox box = new HBox();
                    box.setSpacing(5);
                    ComboBox<String> parameterWord = new ComboBox<>(FXCollections.observableArrayList("Свободно", "Слово", "Перевод", "Группа"));
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
                            for (int n = 0; n < wordTable.getItems().size(); n++) {
                                if(n>numberWord&&resumeFind) {
                                    String category="";
                                    if(parameterWord.getValue()!=null){
                                        category=parameterWord.getValue();
                                    }
                                    switch (category) {
                                        case "Слово": {
                                            if (wordTable.getItems().get(n).getWord().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                wordTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Перевод": {
                                            if (wordTable.getItems().get(n).getTranslate().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                wordTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Группа": {
                                            if (wordTable.getItems().get(n).getGroup().toLowerCase().equals(textField.getText().toLowerCase())) {
                                                wordTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "": {
                                            String word = wordTable.getItems().get(n).getWord();
                                            String translate = wordTable.getItems().get(n).getTranslate();
                                            String group = wordTable.getItems().get(n).getGroup();
                                            if (word.toLowerCase().equals(textField.getText().toLowerCase()) || translate.toLowerCase().equals(textField.getText().toLowerCase()) ||
                                                    group.toLowerCase().equals(textField.getText().toLowerCase())) {
                                                wordTable.getSelectionModel().select(n);
                                                numberWord = n;
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                        case "Свободно": {
                                            String word = wordTable.getItems().get(n).getWord();
                                            String translate = wordTable.getItems().get(n).getTranslate();
                                            String group = wordTable.getItems().get(n).getGroup();
                                            if (word.toLowerCase().equals(textField.getText().toLowerCase()) || translate.toLowerCase().equals(textField.getText().toLowerCase()) ||
                                                    group.toLowerCase().equals(textField.getText().toLowerCase())) {
                                                numberWord = n;
                                                wordTable.getSelectionModel().select(n);
                                                resumeFind=false;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            if(callWaitAlert(Alert.AlertType.WARNING, "Поиск слова", null, "Строка поиска пуста").get()==ButtonType.OK) {
                                continue;
                            }
                        }
                        if (numberWord == -1) {
                            if(callWaitAlert(Alert.AlertType.INFORMATION, "Поиск слова", null, "Слово по данному запросу не обнаружено").get()==ButtonType.OK) {
                                continue;
                            }
                        }
                        resumeFind=true;
                        if(numberWord==wordTable.getItems().size()-1) {
                            numberWord = -1;//Что бы бегать по кругу списка
                        }
                    } else {
                        findAlert.close();
                        break;
                    }
                }
            } else {
                callAlert(Alert.AlertType.WARNING, "Невозможно найти слово", null, "Словарь не выбран или файл не существует");
            }

        });*/
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

  //  public GroupManagerController getGroupManagerController() {
 //       return groupManagerController;
  //  }

    public void setPathToGroupFile(String pathToGroupFile) {
        this.pathToGroupFile = pathToGroupFile;
    }*/
}
