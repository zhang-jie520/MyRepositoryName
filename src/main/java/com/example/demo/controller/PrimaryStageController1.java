package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.dao.ChaiZhuangTimeDao;
import com.example.demo.dao.ZhuangBeiXinxiDao;
import com.example.demo.entry.ChaiZhuangTime;
import com.example.demo.entry.ZhuangBeiXinxi;
import com.example.demo.view.PrimaryStageView;
import com.example.demo.view.PrimaryStageView1;
import com.example.demo.view.lingjianStageView;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class PrimaryStageController1 implements Initializable {
    @FXML
    private ListView<String> userList;
    //  修改
    @FXML
    private TextField updatediynameField;
    private String username;
    @FXML
    private TextField updatesumlingjianField;
    @FXML
    private TextArea updateshunxuhzField;
    //  保存
    @FXML
    private TextField savediynameField;
    @FXML
    private TextField savesumlingjianField;
    @FXML
    private TextArea saveshunxuhzField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList.getItems().add("请点击下方装备名字");
        listshow();
        updateshunxuhzField.setWrapText(true);
        saveshunxuhzField.setWrapText(true);
    }

    public void removelist() {
        userList.getSelectionModel().selectedItemProperty().removeListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userList.getSelectionModel().clearSelection();
            }
        });
        for (int i = userList.getItems().size() - 1; i > 0; i--) {
            userList.getItems().remove(i);
        }
    }

    public void listshow() {
        ZhuangBeiXinxiDao zhuangBeiXinxiDao = new ZhuangBeiXinxiDao();
        List<ZhuangBeiXinxi> zbxxList = zhuangBeiXinxiDao.selectAll();
        for (int i = 0; i < zbxxList.size(); i++) {
            userList.getItems().add(zbxxList.get(i).getDiyname());
        }

        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                username = newValue;
                if (newValue == "请点击下方装备名字") {
                    updatediynameField.setText(null);
                    updatesumlingjianField.setText(null);
                    updateshunxuhzField.setText(null);
                } else {
                    ZhuangBeiXinxi zbxx = zhuangBeiXinxiDao.selectObjectByName(newValue);
                    updatediynameField.setText(zbxx.getDiyname());
                    updatesumlingjianField.setText(String.valueOf(zbxx.getSumlingjian()));
                    updateshunxuhzField.setText(zbxx.getShunxuhz());
                }
            }
        });
    }

    @FXML
    public void updateButtonClick(ActionEvent actionEvent) {
        if (updatediynameField.getText() == null || updatediynameField.getText().trim().equals("") || updatesumlingjianField.getText() == null || updatesumlingjianField.getText().trim().equals("") || updateshunxuhzField.getText() == null || updateshunxuhzField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入相关参数");
            alert.show();
        } else {
            ZhuangBeiXinxiDao zhuangBeiXinxiDao = new ZhuangBeiXinxiDao();
            ZhuangBeiXinxi zhuangBeiXinxi = new ZhuangBeiXinxi();
            zhuangBeiXinxi.setDiyname(updatediynameField.getText());
            try {
                zhuangBeiXinxi.setSumlingjian(Integer.parseInt(updatesumlingjianField.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("零件总数值为数字");
                alert.show();
                return;
            }
            zhuangBeiXinxi.setShunxuhz(updateshunxuhzField.getText());
            zhuangBeiXinxiDao.update(username, zhuangBeiXinxi);

            ChaiZhuangTimeDao chaiZhuangTimeDao = new ChaiZhuangTimeDao();
            ChaiZhuangTime chaiZhuangTime = new ChaiZhuangTime();
            chaiZhuangTime.setZbname(updatediynameField.getText());
            chaiZhuangTimeDao.updateAllByZbName(username, chaiZhuangTime);

            removelist();
            listshow();
            updatediynameField.setText(null);
            updatesumlingjianField.setText(null);
            updateshunxuhzField.setText(null);
        }
    }

    @FXML
    public void saveButtonClick(ActionEvent actionEvent) {
        if (savediynameField.getText() == null || savediynameField.getText().trim().equals("") || savesumlingjianField.getText() == null || savesumlingjianField.getText().trim().equals("") || saveshunxuhzField.getText() == null || saveshunxuhzField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入相关参数");
            alert.show();
        } else {
            ZhuangBeiXinxiDao zhuangBeiXinxiDao = new ZhuangBeiXinxiDao();
            ZhuangBeiXinxi zhuangBeiXinxi = new ZhuangBeiXinxi();
            zhuangBeiXinxi.setDiyname(savediynameField.getText());
            try {
                zhuangBeiXinxi.setSumlingjian(Integer.parseInt(savesumlingjianField.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("零件总数值为数字");
                alert.show();
                return;
            }
            zhuangBeiXinxi.setShunxuhz(saveshunxuhzField.getText());
            zhuangBeiXinxiDao.save(zhuangBeiXinxi);
            removelist();
            listshow();
            savediynameField.setText(null);
            savesumlingjianField.setText(null);
            saveshunxuhzField.setText(null);
        }
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
        if (updatediynameField.getText() == null || updatediynameField.getText().trim().equals("") || updatesumlingjianField.getText() == null || updatesumlingjianField.getText().trim().equals("") || updateshunxuhzField.getText() == null || updateshunxuhzField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入相关参数");
            alert.show();
        } else {
            ZhuangBeiXinxiDao zhuangBeiXinxiDao = new ZhuangBeiXinxiDao();
            ZhuangBeiXinxi zhuangBeiXinxi = new ZhuangBeiXinxi();
            zhuangBeiXinxi.setDiyname(updatediynameField.getText());
            try {
                zhuangBeiXinxi.setSumlingjian(Integer.parseInt(updatesumlingjianField.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("零件总数值为数字");
                alert.show();
                return;
            }
            zhuangBeiXinxi.setShunxuhz(updateshunxuhzField.getText());
            zhuangBeiXinxiDao.delete(zhuangBeiXinxi);

            ChaiZhuangTimeDao chaiZhuangTimeDao = new ChaiZhuangTimeDao();
            ChaiZhuangTime chaiZhuangTime = new ChaiZhuangTime();
            chaiZhuangTime.setZbname(updatediynameField.getText());
            chaiZhuangTimeDao.deleteAllByZbName(chaiZhuangTime);

            removelist();
            listshow();
            updatediynameField.setText(null);
            updatesumlingjianField.setText(null);
            updateshunxuhzField.setText(null);
        }
    }

    public void lingjianButtonClick(ActionEvent actionEvent) throws IOException {
        if (updatediynameField.getText() == null || updatediynameField.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("选择异常");
            alert.setHeaderText("未选择装备");
            alert.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL resource = fxmlLoader.getClassLoader().getResource("view/lingjianStage.fxml");
            fxmlLoader.setLocation(resource);
            BorderPane bp = (BorderPane) fxmlLoader.load();
            lingjianStageController ljController = (lingjianStageController) fxmlLoader.getController();
            ljController.setzbname(updatediynameField.getText());
            savediynameField.setText(null);
            savesumlingjianField.setText(null);
            saveshunxuhzField.setText(null);
            DemoApplication.showView(lingjianStageView.class);
        }
    }
}

