package com.example.demo.controller;

import com.example.demo.DemoApplication;
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
public class Data2StageController implements Initializable {

    @FXML
    TextField u1Field;
    @FXML
    TextField aField;
    @FXML
    TextField bField;

    @FXML
    TextField jsField;
    @FXML
    TextField addField;
    @FXML
    ListView<String> sumList;
    @FXML
    TextField deleteField;

    @FXML
    TextField pjField;

    //a,b得到Za，Zb
    private double getZp(double p) {
        double zp = 0;

        if (p == 0.01) {
            p = -2.33;
        }
        if (p == 0.05) {
            p = -1.65;
        }
        if (p == 0.1) {
            p = -1.28;
        }
        if (p == 0.15) {
            p = -1.04;
        }
        if (p == 0.2) {
            p = -0.84;
        }
        if (p == 0.3) {
            p = -0.52;
        }
        if (p == 0.4) {
            p = -0.25;
        }
        if (p == 0.5) {
            p = 0;
        }
        if (p == 0.6) {
            p = 0.25;
        }
        if (p == 0.7) {
            p = 0.52;
        }
        if (p == 0.8) {
            p = 0.84;
        }
        if (p == 0.85) {
            p = 1.04;
        }
        if (p == 0.9) {
            p = 1.28;
        }
        if (p == 0.95) {
            p = 1.65;
        }
        if (p == 0.99) {
            p = 2.33;
        }
        return p;
    }

    //求平均值
    private double getPjz(double[] ints) {
        double sumx = 0;
        double X = 0;
        int len = ints.length;
        //2.平均值
        for (int i = 0; i < len; i++) {
            sumx = sumx + Math.log(ints[i]);
        }
        X = sumx / len;
        return X;
    }

    //求方差
    private double getFc(double[] ints) {
        double sumd = 0;
        double dd = 0;
        int len = ints.length;
        double X = getPjz(ints);

        //3.方差
        for (int j = 0; j < len; j++) {
            double ss = Math.log(ints[j]) * Math.log(ints[j]) / len;
            sumd = sumd + ss;
        }
        dd = (sumd - X * X) / (len - 1);
        return dd;
    }

    private String ovg(int u1, double a, double b, double[] ints) {
//输入的参数
        double X2 = 0;
        int len = ints.length;

//2.平均值
        double X = getPjz(ints);
//3.方差
        double dd = getFc(ints);
//4.标准值
        X2 = Math.log(0.37 * u1) + 0.387 * Math.sqrt(dd);
//5.结果
        if (X < X2) {
            return "修复时间中值符合要求";
        } else {
            return "修复时间中值不符合要求";
        }
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

    //评价
    public void pjButtonClick(ActionEvent actionEvent) {
        int n = 0;
        try {
            n = Integer.parseInt(jsField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("样本量异常");
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

            int u1 = Integer.parseInt(u1Field.getText());
            double a = Double.parseDouble(aField.getText());
            double b = Double.parseDouble(bField.getText());
            pjField.setText(ovg(u1, a, b, ints));
        }
    }

    public void backButtonClick(ActionEvent actionEvent) {
        DemoApplication.showView(PingJiaStageView.class);
    }
}

