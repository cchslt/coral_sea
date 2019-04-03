package com.stone.spider.BO;

public class BookInfoBO {
    private String bookName;
    private String oriPrice;
    private String promotionPrice;
    private String author;
    private String transAuthor;
    private String type;
    private String stars;
    private double rating;
    private String reviewCount;
    private String articleDesc;


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(String oriPrice) {
        this.oriPrice = oriPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTransAuthor() {
        return transAuthor;
    }

    public void setTransAuthor(String transAuthor) {
        this.transAuthor = transAuthor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }


    @Override
    public String toString() {
        return "BookInfoBO{" +
                "bookName='" + bookName + '\'' +
                ", oriPrice='" + oriPrice + '\'' +
                ", promotionPrice='" + promotionPrice + '\'' +
                ", author='" + author + '\'' +
                ", transAuthor='" + transAuthor + '\'' +
                ", type='" + type + '\'' +
                ", stars='" + stars + '\'' +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", articleDesc='" + articleDesc + '\'' +
                '}';
    }
}
