package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.view.IndexStageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.codehaus.plexus.util.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;


@FXMLController
public class ZhuangBeiStageController implements Initializable {

    @FXML
    private TextField ziyuan;
    @FXML
    private TextField xiangmu;
    @FXML
    private Label tishi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tishi.setText(null);
    }

    //查看
    @FXML
    public void ListButtonClick(ActionEvent actionEvent) {
        tishi.setText(null);
        danxuan(ziyuan.getText(), xiangmu.getText());
    }

    //上传
    @FXML
    public void PutButtonClick(ActionEvent actionEvent) {
        tishi.setText(null);
        duanxuan_save(ziyuan.getText());
    }

    //返回
    @FXML
    public void BackButtonClick(ActionEvent actionEvent) {
        tishi.setText(null);
        DemoApplication.showView(IndexStageView.class);
    }

    //1.单选文件
    private void danxuan(String zy, String xm) {
        if (zy == null || zy.trim().equals("") || xm == null || xm.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入相关路径");
            alert.show();
        } else {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();
            //1.设置窗口名称
            fc.setTitle("装备文件");
            //2.打开的默认路径
            fc.setInitialDirectory(new File(zy));
            //3.过滤要打开的文件类型
            fc.getExtensionFilters().addAll(
                    new ExtensionFilter("文件类型", "*.fbx"),
                    new ExtensionFilter("所有类型", "*.*")
            );
            //单选：返回文件
            File file = null;
            try {
                file = fc.showOpenDialog(stage);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("未输入正确的资源路径");
                alert.show();
            }

            if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
                return;
            } else {
                try {
                    FileUtils.copyFile(file.getAbsoluteFile(), new File(xm + File.separator + file.getName()));
                    tishi.setText("导入成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("输入异常");
                    alert.setHeaderText("未输入正确的项目路径");
                    alert.show();
                    tishi.setText("导入失败");
                }
            }
        }
    }

    //3.单选文件并保存文字到文本文件
    private void duanxuan_save(String zy) {
        if (zy == null || zy.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入资源路径");
            alert.show();
        } else {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();
            //1.设置窗口名称
            fc.setTitle("上传装备");
            //2.打开的默认路径  C:\Users\pc\Desktop
            fc.setInitialDirectory(new File(zy));
            //3.过滤要打开的文件类型
            fc.getExtensionFilters().addAll(
                    new ExtensionFilter("文件类型", "*.fbx"),
                    new ExtensionFilter("所有类型", "*.*")
            );
            //单选：保存文件
            File file = null;
            try {
                file = fc.showSaveDialog(stage);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入异常");
                alert.setHeaderText("未输入正确的资源路径");
                alert.show();
            }

            if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
                return;
            } else {
                System.out.println(file.getAbsolutePath()); //双击或者点击打开，会返回文件路径
            }
            //存文字到文本文件里面(要保存的文本路径，及文件名，写入等等操作)
        }
    }

    //4.多选文件
    private void duoxuan() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //1，2，3.多选的设置与单选一样

        //多选：返回文件
        List<File> files = fc.showOpenMultipleDialog(stage);
        if (files == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
            return;
        } else {

            files.forEach(new Consumer<File>() {
                @Override
                public void accept(File f) {
                    System.out.println(f.getAbsolutePath());
                }
            }); //双击或者点击打开，会返回文件路径:遍历list

        }
    }

    //5.选择文件夹,
    private void wenjianjia() {
        Stage stage = new Stage();
        DirectoryChooser dc = new DirectoryChooser();
        //1.设置窗口名称
        dc.setTitle("装备文件");
        //2.打开的默认路径
        dc.setInitialDirectory(new File("F:" + File.separator + "app"));

        //单选：返回文件
        File file = dc.showDialog(stage);
        if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
            return;
        } else {
            System.out.println(file.getAbsolutePath()); //输出该文件夹的路径
            //可以输出该文件夹内部文件的路径
            File[] files = file.listFiles();  //遍历
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
            }

        }
    }
}

