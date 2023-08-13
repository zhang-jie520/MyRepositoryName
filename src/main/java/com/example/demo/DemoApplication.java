package com.example.demo;

import com.example.demo.entry.PrimaryStageEntry;
import com.example.demo.view.IndexStageView;
import com.example.demo.view.PrimaryStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(DemoApplication.class, IndexStageView.class, args);
        SpringApplication.run(DemoApplication.class, args);
        //launch(DemoApplication.class, PrimaryStageView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("维修性试验评价");
        super.start(stage);
    }
}

