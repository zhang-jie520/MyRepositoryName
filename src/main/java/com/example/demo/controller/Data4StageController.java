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
import org.apache.commons.math3.distribution.NormalDistribution;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class Data4StageController implements Initializable {

    @FXML
    TextField I_p0Field;
    @FXML
    TextField I_p1Field;
    @FXML
    TextField aField;
    @FXML
    TextField bField;
    @FXML
    TextField limitTime;

    @FXML
    TextField jsFieldn;
    @FXML
    TextField addField;
    @FXML
    ListView<String> sumList;
    @FXML
    TextField deleteField;
    @FXML
    TextField jsFieldc;

    @FXML
    TextField bzField;
    @FXML
    TextField syField;
    @FXML
    TextField pjField;

    @FXML
    TextField ccField;
    @FXML
    TextField ccjgField;

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

    //求平均值2
    private double getPjz2(double[] ints) {
        double sumx = 0;
        double X = 0;
        int len = ints.length;
        //2.平均值
        for (int i = 0; i < len; i++) {
            sumx = sumx + Math.log(ints[i]) * Math.log(ints[i]);
        }
        return sumx;
    }

    //求平均值3
    private double getPjz3(double[] ints) {
        double sumx = 0;
        int len = ints.length;
        double sumx1 = getPjz2(ints);
        sumx = sumx1 / len;
        return sumx;
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

    private String ovg(int u1, double b, double[] ints) {
//输入的参数
        int len = ints.length;
        double zb = getZp(1 - b);
//2.平均值
        double X1 = getPjz(ints);
        double sumx1 = getPjz2(ints);
        double sumx2 = getPjz3(ints);
        double X2 = zb * Math.sqrt((sumx1 - sumx2) / (len - 1));
//4.标准值
        double X3 = Math.pow(Math.E, X1 + X2);
//5.结果
        if (X3 <= u1) {
            return "最大修复时间符合要求";
        } else {
            return "最大修复时间不符合要求";
        }
    }

    //p0<0.2时获得n,c
//    private int[] getNc(double p0, double p1, double a, double b) {
//        int n=0;
//        int c=0;
//        double k = (double) p1/p0;
//        int[] nc = new int[2];
//
//        switch (k){
//            case 1.5:
//                System.out.println(k);
//                break;
//            case 2.0:
//                System.out.println(k);
//                break;
//            default:
//                System.out.println(k);
//        }
//
//        return nc;
//    }

    //获得试验值r
    private int getR(double T, double[] ints) {
        int len = ints.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (ints[i] > T) {
                sum++;
            }
        }
        return sum;
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

    //样本量
    public void jsnButtonClick(ActionEvent actionEvent) {
        double p0 = 1 - Double.parseDouble(I_p0Field.getText());
        double p1 = 1 - Double.parseDouble(I_p1Field.getText());
        double a = Double.parseDouble(aField.getText());
        double b = Double.parseDouble(bField.getText());

        if (p0 < 0.2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("参数异常");
            alert.setHeaderText("请参考表格填充样本量");
            alert.show();
            return;
        }

        double q0 = 1 - p0; //可接受值
        double q1 = 1 - p1;
        double za = getZp(1 - a);
        double zb = getZp(1 - b);
        double shang = za * Math.sqrt(p0 * q0) + zb * Math.sqrt(p1 * q1);
        double xia = p1 - p0;
        int n = (int) Math.ceil(Math.pow(shang / xia, 2));
        jsFieldn.setText(String.valueOf(n));
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

    //接收数
    public void jscButtonClick(ActionEvent actionEvent) {
        double p0 = 1 - Double.parseDouble(I_p0Field.getText());
        double p1 = 1 - Double.parseDouble(I_p1Field.getText());
        double a = Double.parseDouble(aField.getText());
        double b = Double.parseDouble(bField.getText());

        if (p0 < 0.2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("参数异常");
            alert.setHeaderText("请参考表格填充接收数");
            alert.show();
            return;
        }

        double q0 = 1 - p0;
        double q1 = 1 - p1;
        double za = getZp(1 - a);
        double zb = getZp(1 - b);
        double shang = za * Math.sqrt(p0 * q0) + zb * Math.sqrt(p1 * q1);
        double xia = p1 - p0;
        int n = (int) Math.ceil(Math.pow(shang / xia, 2));
        int c = (int) Math.floor(n * p0 + za * Math.sqrt(n * p0 * q0));
        jsFieldc.setText(String.valueOf(c));
    }

    //评价
    public void pjButtonClick(ActionEvent actionEvent) {
        int n = 0;
        int c = 0;
        double T = 0;
        try {
            n = Integer.parseInt(jsFieldn.getText());
            c = Integer.parseInt(jsFieldc.getText());
            T = Double.parseDouble(limitTime.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("样本量/接收值/临界维修时间异常");
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

            int r = getR(T, ints);
            bzField.setText(String.valueOf(c));
            syField.setText(String.valueOf(r));
            if (r <= c) {
                pjField.setText("装备符合维修性要求而接受");
            } else {
                pjField.setText("装备不符合维修性要求而拒绝");
            }
        }
    }

    public void backButtonClick(ActionEvent actionEvent) {
        DemoApplication.showView(PingJiaStageView.class);
    }

    //计算抽查值
    public void jsccButtonClick(ActionEvent actionEvent) {
        double p0 = 0;
        double p1 = 0;
        double cc = 0; //待抽查值
        double n = 0; //样本量n
        double c = 0; //接收值c

        try {
            p0 = 1 - Double.parseDouble(I_p0Field.getText());
            p1 = 1 - Double.parseDouble(I_p1Field.getText());
            cc = Double.parseDouble(ccField.getText()); //待抽查值
            n = Double.parseDouble(jsFieldn.getText()); //样本量n
            c = Double.parseDouble(jsFieldc.getText()); //接收值c
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("样本量/接收值/可接受值/不可接收值/待抽查值");
            alert.show();
            return;
        }

        double q0 = 1 - p0;
        double q1 = 1 - p1;

        if (p0 < 0.2) {
            double sum = 0;
            for (int d = 0; d <= c; d++) {
                double shang = Math.pow(n * cc, d) * Math.pow(Math.E, -n * cc);
                double xia = 1;
                for (int i = 1; i <= d; i++) {
                    xia = xia * i;
                }
                sum = sum + shang / xia;
            }
            ccjgField.setText(String.valueOf(sum));
        } else {
            double X = (c - n * cc) / Math.sqrt(n * cc * (1 - cc));
            NormalDistribution normalDistributioin = new NormalDistribution(0, 1);
            double S1 = normalDistributioin.cumulativeProbability(X);
            ccjgField.setText(String.valueOf(S1));
        }
    }
}

