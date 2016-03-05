package remove;
public class JsonItem {
    private String price;
    private String name;
    private String url;
    
    public JsonItem(String name, String price, String url)
    {
        this.price = price;
        this.name = name;
        this.url = url;
    }
    
    public String getPrice()
    {
        return price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getUrl()
    {
        return url;
    }

}
