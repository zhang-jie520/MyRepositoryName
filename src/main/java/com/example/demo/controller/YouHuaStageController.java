package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.view.IndexStageView;
import com.example.demo.view.PingJiaStageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class YouHuaStageController implements Initializable {

    @FXML
    TextField startField;
    @FXML
    TextField endField;
    @FXML
    TextField xgField;
    @FXML
    TextField nField;

    @FXML
    TextField addField;
    @FXML
    ListView<String> sumList;
    @FXML
    TextField deleteField;

    @FXML
    TextField pjzField;

    //求平均值
    private double getPjz(double[] ints) {
        double sumx = 0;
        double X = 0;
        int len = ints.length;
        //2.平均值
        for (int i = 0; i < len; i++) {
            sumx = sumx + ints[i];
        }
        X = sumx / len;
        return X;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sumList.getItems().add("请点击下面的值");

        sumList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == "请点击下面的值") {
                    deleteField.setText(null);
                } else {
                    deleteField.setText(newValue);
                }
            }
        });
    }

    //添加
    public void addButtonClick(ActionEvent actionEvent) {
        if (addField.getText() == null || addField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入相关参数");
            alert.show();
        } else {
            int i;
            try {
                i = Integer.parseInt(addField.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("请输入数字");
                alert.show();
                return;
            }
            sumList.getItems().add(addField.getText());
            addField.setText(null);
        }
    }

    //    getSelectionModel().getSelectedIndex()
//        –返回在单选（single-selection）模式下当前被选中的列表项索引号。
//
//            · getSelectionModel().getSelectedItem()
//        – 返回当前被选中的列表项。
    //删除
    public void deleteButtonClick(ActionEvent actionEvent) {
        if (sumList.getSelectionModel().getSelectedIndex() != 0) {
            sumList.getItems().remove(sumList.getSelectionModel().getSelectedIndex());
            deleteField.setText(null);
        }
    }

    //计算优化效果
    public void jsxgButtonClick(ActionEvent actionEvent) {
        double start = 0;
        double end = 0;
        double xg = 0;
        try {
            start = Double.parseDouble(startField.getText());
            end = Double.parseDouble(endField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("初始值/优化值");
            alert.show();
            return;
        }
        xg = (start - end) / start * 100;
        xgField.setText(String.valueOf(xg));
    }

    //求平均值
    public void pjzButtonClick(ActionEvent actionEvent) {
        int n = 0;
        try {
            n = Integer.parseInt(nField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("样本量");
            alert.show();
            return;
        }
        if (sumList.getItems().size() - 1 < n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("样本量不足");
            alert.show();
            return;
        } else {
            double[] ints = new double[n];

            for (int i = 0; i < n; i++) {
                ints[i] = Double.parseDouble(sumList.getItems().get(i + 1));
            }
            double pjz = getPjz(ints);
            pjzField.setText(String.valueOf(pjz));
        }
    }

    public void backButtonClick(ActionEvent actionEvent) {
        DemoApplication.showView(IndexStageView.class);
    }
}

