package com.example.demo.dao;

import com.example.demo.entry.PrimaryStageEntry;
import com.example.demo.entry.ZhuangBeiXinxi;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ZhuangBeiXinxiDao {

    public List<ZhuangBeiXinxi> selectAll() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ZhuangBeiXinxi> users = sqlSession.selectList("ZhuangBeiXinxiMapper.selectAll");
        sqlSession.close();
        return users;
    }

    public ZhuangBeiXinxi selectObjectByName(String newValue) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ZhuangBeiXinxi users = sqlSession.selectOne("ZhuangBeiXinxiMapper.selectObjectByName", newValue);
        sqlSession.close();
        return users;
    }

    public void save(ZhuangBeiXinxi zhuangBeiXinxi) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("ZhuangBeiXinxiMapper.save", zhuangBeiXinxi);
        sqlSession.commit(true);
        sqlSession.close();
    }

    public void update(String username, ZhuangBeiXinxi zhuangBeiXinxi) {
        ZhuangBeiXinxi zhuangBeiXinxi1 = selectObjectByName(username);
        zhuangBeiXinxi.setId(zhuangBeiXinxi1.getId());

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("ZhuangBeiXinxiMapper.update", zhuangBeiXinxi);
        sqlSession.commit(true);
        sqlSession.close();
    }

    public void delete(ZhuangBeiXinxi zhuangBeiXinxi) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("ZhuangBeiXinxiMapper.delete", zhuangBeiXinxi);
        sqlSession.commit(true);
        sqlSession.close();
    }
}
