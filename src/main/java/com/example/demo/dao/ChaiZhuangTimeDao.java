package com.example.demo.dao;

import com.example.demo.entry.ChaiZhuangTime;
import com.example.demo.entry.ZhuangBeiXinxi;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChaiZhuangTimeDao {

    public List<ChaiZhuangTime> selectAll(String zhuangbeiname) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ChaiZhuangTime> users = sqlSession.selectList("ChaiZhuangTimeMapper.selectAll", zhuangbeiname);
        sqlSession.close();
        return users;
    }

    public ChaiZhuangTime selectObjectByName(ChaiZhuangTime chaiZhuangTime) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ChaiZhuangTime users = sqlSession.selectOne("ChaiZhuangTimeMapper.selectObjectByName", chaiZhuangTime);
        sqlSession.close();
        return users;
    }

    public void save(ChaiZhuangTime chaiZhuangTime) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("ChaiZhuangTimeMapper.save", chaiZhuangTime);
        sqlSession.commit(true);
        sqlSession.close();
    }

    public void update(String username, ChaiZhuangTime chaiZhuangTime) {
        ChaiZhuangTime chaiZhuangTime1 = new ChaiZhuangTime();
        chaiZhuangTime1.setZbname(chaiZhuangTime.getZbname());
        chaiZhuangTime1.setLjname(username);
        ChaiZhuangTime chaiZhuangTime2 = selectObjectByName(chaiZhuangTime1);
        chaiZhuangTime.setId(chaiZhuangTime2.getId());

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("ChaiZhuangTimeMapper.update", chaiZhuangTime);
        sqlSession.commit(true);
        sqlSession.close();
    }

    public void delete(ChaiZhuangTime chaiZhuangTime) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("ChaiZhuangTimeMapper.delete", chaiZhuangTime);
        sqlSession.commit(true);
        sqlSession.close();
    }

    public void updateAllByZbName(String username, ChaiZhuangTime chaiZhuangTime) {
        List<ChaiZhuangTime> chaiZhuangTimes = selectAll(username);

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        for (ChaiZhuangTime cztime : chaiZhuangTimes) {
            chaiZhuangTime.setId(cztime.getId());
            sqlSession.update("ChaiZhuangTimeMapper.updateAllByZbName", chaiZhuangTime);
            sqlSession.commit(true);
        }
        sqlSession.close();
    }

    public void deleteAllByZbName(ChaiZhuangTime chaiZhuangTime) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("ChaiZhuangTimeMapper.deleteAllByZbName", chaiZhuangTime);
        sqlSession.commit(true);
        sqlSession.close();
    }
}
