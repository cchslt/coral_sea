package com.stone.spider.cia;

import com.stone.spider.BO.BookInfoBO;
import com.stone.spider.util.JDBCConnectionFactory;
import com.stone.spider.util.URLUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiteratureCia {

    private static String bigCategory ="经济管理";
    private static String category ="企业经营";
    private final static String urlStr = "https://read.douban.com/kind/164?sort=hot&start=%s";

    public static void main(String[] args) {


        for(int i = 0; ; i = i+20) {
            System.out.println(String.format("读取第 %s 页数据", i/20));
            String urlTmp = String.format(urlStr, i);
            //2、取出url中的信息
            String htmlContext = URLUtils.getURLConnect(urlTmp);


            //3、解析url获取的网页信息
            List<BookInfoBO> bookInfoBOList = getParserHtml(htmlContext);
            if (bookInfoBOList.isEmpty()) {
                break;
            }

            //4、将数据格式化后存入文本或者数据库中
            saveToDb(bookInfoBOList);

        }

    }

    private static void saveToDb(List<BookInfoBO> bookInfoBOList) {
        if (bookInfoBOList.isEmpty()) {
            return;
        }


        String sql ="INSERT INTO `book_info`(`book_name`, `ori_price`, `promotion_price`, `author`, `trans_author`, `type`, `stars`, `rating`, `reviewCount`, `articleDesc`, `category`, `big_category`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            for (BookInfoBO bo : bookInfoBOList) {
                pst.setString(1, bo.getBookName());
                pst.setString(2, bo.getOriPrice());
                pst.setString(3, bo.getPromotionPrice());
                pst.setString(4, bo.getAuthor());
                pst.setString(5, bo.getTransAuthor());
                pst.setString(6, bo.getType());
                pst.setString(7, bo.getStars());
                pst.setDouble(8, bo.getRating());
                pst.setString(9, bo.getReviewCount());
                pst.setString(10, bo.getArticleDesc());
                pst.setString(11, category);
                pst.setString(12, bigCategory);
                pst.addBatch();
            }
            pst.executeBatch();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static List<BookInfoBO> getParserHtml(String htmlContext) {
        //3、解析url获取的网页信息
        Document document = Jsoup.parse(htmlContext);
        Elements itemElements = document.getElementsByClass("item store-item");

        List<BookInfoBO> bookInfoBOS = new ArrayList<>();
        for (Element e : itemElements) {
            BookInfoBO bookInfoBO = new BookInfoBO();
            String bookName = e.getElementsByClass("title").text();
            String oriPrice = e.getElementsByClass("original-tag").text();
            String promotionPrice = e.getElementsByClass("discount-price").text();
            promotionPrice = promotionPrice.trim().isEmpty() ? e.getElementsByClass("price-tag ").text() : promotionPrice;

            Elements labeled = e.getElementsByClass("labeled-text");
            String author = labeled.size() == 1 ? null : labeled.get(0).text();
            String transAuthor = labeled.size() == 3 ? labeled.get(1).text() : null;
            String type = labeled.size() == 3 ? labeled.get(2).text() : (labeled.size() == 1 ? labeled.get(0).text() :labeled.get(1).text());
            String stars = e.getElementsByClass("rating list-rating").text().isEmpty() ? null : e.getElementsByClass("rating list-rating").get(0).child(0).attr("title");
            String rating = e.getElementsByClass("rating-average").text();
            String reviewCount = e.getElementsByClass("ratings-link").text();
            String articleDesc = e.getElementsByClass("article-desc-brief").text();

            bookInfoBO.setBookName(bookName);
            bookInfoBO.setOriPrice(oriPrice);
            bookInfoBO.setPromotionPrice(promotionPrice);
            bookInfoBO.setAuthor(author);
            bookInfoBO.setTransAuthor(transAuthor);
            bookInfoBO.setType(type);
            bookInfoBO.setStars(stars);
            bookInfoBO.setRating(rating.isEmpty() ? 0 : Double.parseDouble(rating));
            bookInfoBO.setReviewCount(reviewCount);
            bookInfoBO.setArticleDesc(articleDesc);
            bookInfoBOS.add(bookInfoBO);
        }

        return bookInfoBOS;
    }




}
