package ru.putnik.athena.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.putnik.athena.pojo.GroupData;

/**
 * Создано 20.08.2019 в 14:25
 */
public class MainModel {
    private static ObservableList<GroupData> listData=FXCollections.observableArrayList();

    public static ObservableList<GroupData> getListData() {
        return listData;
    }
}
