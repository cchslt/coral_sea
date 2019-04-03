package com.stone.spider.cia;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.stone.spider.BO.KgPinganBo;
import com.stone.spider.util.JDBCConnectionFactory;
import com.stone.spider.util.ListToStringUtils;
import com.stone.spider.util.URLUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KgPinganCia {

    private final static String urlStr = "http://14.116.177.61:1259/api/detail/disease/";


    public static void main(String[] args) {


        //2、取出url中的信息
        String htmlContext = URLUtils.getURLConnect(urlStr);


        //3、解析url获取的网页信息
        //4、将数据格式化后存入文本或者数据库中
        getParserHtml(htmlContext);

    }

    private static void saveToDb(List<KgPinganBo> bookInfoBOList) {
        if (bookInfoBOList.isEmpty()) {
            return;
        }

        System.out.println("正在执行插入操作");
        String sql ="INSERT INTO `kg_pingan`(concept_term, uuid, symptom, test) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            for (KgPinganBo bo : bookInfoBOList) {
                pst.setString(1, bo.getConceptTerm());
                pst.setString(2, bo.getUuid());
                pst.setString(3, bo.getSymptom());
                pst.setString(4, bo.getTest());

                pst.addBatch();
            }
            pst.executeBatch();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("完成插入操作,PONG!!!PONG!!!");
    }


    public static void getParserHtml(String htmlContext) {
        List<KgPinganBo> kgPinganBoList = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(htmlContext);
        for(String key : jsonObject.keySet()) {
            JSONArray jsonArray = (JSONArray) jsonObject.get(key);
            for (int i = 0; i<jsonArray.size(); i++) {
                KgPinganBo bo = jsonArray.getObject(i, KgPinganBo.class);
                getKgDetail(bo);
                kgPinganBoList.add(bo);
                if (kgPinganBoList.size() % 500 == 0) {
                    saveToDb(kgPinganBoList);
                    kgPinganBoList.clear();
                }
            }
        }

        saveToDb(kgPinganBoList);
    }

    private static void getKgDetail(KgPinganBo bo) {
        String htmlContext = URLUtils.getURLConnect(urlStr + bo.getUuid());
        JSONObject jsonObject = JSONObject.parseObject(htmlContext);
        JSONObject propsArray = jsonObject.getJSONObject("props");
        JSONArray symptomJson = (JSONArray) propsArray.get("symptom");
        JSONArray testJson = (JSONArray) propsArray.get("test");
        String symptom = ListToStringUtils.listToString(Arrays.asList(symptomJson.toArray()), ',');
        String test = ListToStringUtils.listToString(Arrays.asList(testJson.toArray()), ',');

        bo.setSymptom(symptom);
        bo.setTest(test);
    }


}
