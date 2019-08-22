package ru.putnik.athena.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.putnik.athena.model.MainModel;
import ru.putnik.athena.pojo.GroupData;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Создано 20.08.2019 в 13:59
 */
public class AddEditGroupController implements Initializable {
    private static Stage stage;
    private static MainController mainController;
    private static TypeOperation operation;
    private boolean added=false;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField commentTextField;
    @FXML
    private Button addEditGroupButton;
    @FXML
    private Button exitButton;
    public AddEditGroupController(MainController controller){
        mainController=controller;
    }
    public AddEditGroupController(){}

    public boolean showWindow(TypeOperation operation){
        stage=new Stage();
        AddEditGroupController.operation=operation;

        try {
            Parent parent=FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/addEditGroupView.fxml")));
            stage.setScene(new Scene(parent));
            stage.setWidth(430);
            stage.setHeight(330);
            if(operation==TypeOperation.ADDING){
                stage.setTitle("Athena - Добавить новую группу данных");
            }else{
                stage.setTitle("Athena - Редактировать группу данных");
            }
            stage.showAndWait();

            return added;
        } catch (IOException e) {
            e.printStackTrace();
            return added;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(operation==TypeOperation.ADDING){
            addEditGroupButton.setText("Добавить");
        }else{
            addEditGroupButton.setText("Сохранить");
            //TODO: Добавить вывод существующих данных для редактирования. mainController.groupTable.getSelectionModel().getSelectedIndex()
        }
        addEditGroupButton.setOnAction(event -> {
           // if(){  TODO: добавить проверки
            GroupData data=new GroupData();
            data.setName(nameTextField.getText());
            data.setAddress(addressTextField.getText());
            data.setLogin(loginTextField.getText());
            data.setPassword(passwordTextField.getText());
            data.setEmail(emailTextField.getText());
            data.setComment(commentTextField.getText());

            MainModel.getListData().add(data);
            added=true;
           // }
        });
        exitButton.setOnAction(event -> {
            stage.close();
        });
    }
    public enum TypeOperation{
        ADDING,EDITING
    }
}
