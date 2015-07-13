package thoughtworks.academy.letsgoandroid.bean;

public class Good {

    public int price;
    public String name;
    public int imgSrcId;

    public Good(String name, int price, int imgSrcId) {
        this.name = name;
        this.price = price;
        this.imgSrcId = imgSrcId;
    }
}
