package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.view.PrimaryStageView1;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;


@FXMLController
public class PrimaryStageController implements Initializable {

    @FXML
    private TextArea ta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onHelloButtonClick(ActionEvent actionEvent) {
        danxuan_load();
    }

    @FXML
    public void onHelloButtonClick1(ActionEvent actionEvent) {
        ta.clear();
        //DemoApplication.showView(PrimaryStageView1.class);
    }

    //1.单选文件
    private void danxuan() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //1.设置窗口名称
        fc.setTitle("装备文件");
        //2.打开的默认路径
        fc.setInitialDirectory(new File("F:" + File.separator + "app"));
        //3.过滤要打开的文件类型
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("图片类型", "*.jpg", "*.png"),
                new ExtensionFilter("文本类型", "*.txt"),
                new ExtensionFilter("所有类型", "*.*")
        );
        //单选：返回文件
        File file = fc.showOpenDialog(stage);
        if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
            return;
        } else {
            System.out.println(file.getAbsolutePath()); //双击或者点击打开，会返回文件路径
        }
    }

    //2.单选文件并打开文本，保存到textarea里面
    private void danxuan_load() {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        //1.设置窗口名称
        fc.setTitle("装备文件");
        //2.打开的默认路径
        fc.setInitialDirectory(new File("F:" + File.separator + "app"));
        //3.过滤要打开的文件类型
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("图片类型", "*.jpg", "*.png"),
                new ExtensionFilter("文本类型", "*.txt"),
                new ExtensionFilter("所有类型", "*.*")
        );
        //单选：返回文件
        File file = fc.showOpenDialog(stage);
        if (file == null) {  //如果不选文件直接关闭窗口，file为空，后面会报错
            return;
        } else {
            System.out.println(file.getAbsolutePath()); //双击或者点击打开，会返回文件路径
        }
        //读取文本文件内容到app里面(就是根据文件的路径，打开读取，写入等等操作)
        try {

            //1.读取方式
            //FileReader fr = new FileReader(file); //（如果文本的格式为GBK，直接读，乱码），文本格式是UTF-8可以直接读
            //2.读取方式
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "GBK");  //GBK ， UTF-8：文本的格式

            BufferedReader br = new BufferedReader(isr);
            String str = null;
            //开启自动换行
            ta.setWrapText(true);

            while ((str = br.readLine()) != null) {
                ta.appendText(str);
            }
            fis.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
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

            osw.write(ta.getText());

            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
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

