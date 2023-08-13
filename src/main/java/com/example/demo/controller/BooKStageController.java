package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.view.IndexStageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;


@FXMLController
public class BooKStageController implements Initializable {

    @FXML
    private Text book;
    @FXML
    private TextField zy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void ShowButtonClick(ActionEvent actionEvent) {
        danxuan_load(zy.getText());
    }

    @FXML
    public void BackButtonClick(ActionEvent actionEvent) {
        book.setText("请选择打开说明书");
        DemoApplication.showView(IndexStageView.class);
    }


    //2.单选文件并打开文本，保存到textarea里面
    private void danxuan_load(String zy) {
        if (zy == null || zy.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入异常");
            alert.setHeaderText("未输入资源路径");
            alert.show();
        } else {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();
            //1.设置窗口名称
            fc.setTitle("装备导入说明书");
            //2.打开的默认路径
            fc.setInitialDirectory(new File(zy));
            //3.过滤要打开的文件类型
            fc.getExtensionFilters().addAll(
                    new ExtensionFilter("文本类型", "*.txt"),
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
                //读取文本文件内容到app里面(就是根据文件的路径，打开读取，写入等等操作)
                try {
                    //1.读取方式
                    //FileReader fr = new FileReader(file); //（如果文本的格式为GBK，直接读，乱码），文本格式是UTF-8可以直接读
                    //2.读取方式
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");  //GBK ， UTF-8：文本的格式

                    BufferedReader br = new BufferedReader(isr);
                    String str = "";
                    String str1 = "";
                    while ((str1 = br.readLine()) != null) {
                        str = str + str1 + "\n";
                    }
                    book.setText(str);
                    fis.close();
                    isr.close();
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //3.单选文件并保存文字到文本文件
    private void duanxuan_save() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //1.设置窗口名称
        fc.setTitle("装备文件");
        //1.1要保存的文本文件名称
        fc.setInitialFileName("test");  //文本文件名为test.txt
        //2.打开的默认路径
        fc.setInitialDirectory(new File("F:" + File.separator + "app"));
        //3.过滤要打开的文件类型
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("文本类型", "*.txt")
        );
        //单选：保存文件
        //File file = fc.showOpenDialog(stage);
        File file = fc.showSaveDialog(stage);

        if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
            return;
        } else {
            System.out.println(file.getAbsolutePath()); //双击或者点击打开，会返回文件路径
        }
        //存文字到文本文件里面(要保存的文本路径，及文件名，写入等等操作)
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            osw.write(book.getText());

            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

