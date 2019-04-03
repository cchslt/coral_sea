package com.stone.spider.cia;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stone.spider.BO.AnHuiHosBO;
import com.stone.spider.util.ObjectToMapUtil;
import com.stone.spider.util.URLUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


public class AnHuiHosCia {

    private final static String urlStr = "https://m.hsyuntai.com/hp/hospitals/100176/common/departments?branchId=&hasShift=1&isExpert=";
//    private final static String urlStr = "https://m.hsyuntai.com/hp/hospitals/100176/registration/departmentDetails?date=&departmentId=&distId=&isLoading=true&mode=2&outpatientId=1067197&pageNum=1&pageSize=10&timeout=30000&title=";

    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, IOException {
        String htmlContext = URLUtils.getURLConnect(urlStr);


        getParserHtml(htmlContext);
    }

    private static void getParserHtml(String htmlContext) throws ParserConfigurationException, XPathExpressionException {
        JSONObject jsonObject = JSONObject.parseObject(htmlContext);
        JSONObject propsArray = jsonObject.getJSONObject("categories");
        JSONArray dataList = (JSONArray) propsArray.get("data");

        List<AnHuiHosBO> anHuiHosBOList = new ArrayList<>();
        List<Object> list = Arrays.asList(dataList.toArray());
        for (Object obj : list) {
            Map<String, Object> map = ObjectToMapUtil.objectToMap(obj);
            HashMap<String, String> keShiMap= (HashMap<String, String>)map.get("map");
            AnHuiHosBO anHuiHosBO = new AnHuiHosBO();
            anHuiHosBO.setKeShi(keShiMap.get("cateTxt"));
            anHuiHosBOList.add(anHuiHosBO);
        }

        if (anHuiHosBOList.isEmpty()) {
            return;
        }

        getParseratogeryListHtml(anHuiHosBOList);
    }

    private static void getParseratogeryListHtml(List<AnHuiHosBO> anHuiHosBOList) {
        String categoryUrl = "https://m.hsyuntai.com/hp/hospitals/100176/common/departmentsByCategoryName?hasShift=1&branchId=&categoryName=";
        List<AnHuiHosBO> resultList = new ArrayList<>();
        for (AnHuiHosBO bo : anHuiHosBOList) {
            String keShi = bo.getKeShi();
            String categoryTmpUrl = null;
            try {
                categoryTmpUrl = categoryUrl + URLEncoder.encode(keShi,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSONObject.parseObject(URLUtils.getURLConnect(categoryTmpUrl));
            JSONArray dataArray = jsonObject.getJSONArray("data");

            List<Object> list = Arrays.asList(dataArray.toArray());
            for (Object obj : list) {
                Map<String, Object> map = ObjectToMapUtil.objectToMap(obj);
                HashMap<String, String> categoryMap = (HashMap<String, String>) map.get("map");
                String name = categoryMap.get("name");
//                System.out.println(categoryMap.get("deptId"));
                AnHuiHosBO resultBO = new AnHuiHosBO();
                resultBO.setKeShi(keShi);
                resultBO.setCatogeryList(name);
                resultBO.setFlag("1");
                resultList.add(resultBO);
            }
        }

        for (AnHuiHosBO bo : resultList) {
            System.out.println(bo);
        }
    }
}
