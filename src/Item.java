/**
 * Object reference to grand exchange api
 * 
 */

public class Item {
	
	public static final String STRING_ID 			= "id";
	public static final String STRING_NAME 			= "name";
	public static final String STRING_PRICE 		= "price";
	public static final String STRING_ICON_SMALL 	= "icon";
	public static final String STRING_ICON_LARGE 	= "icon_large";
	public static final String STRING_TYPE 			= "type";
	public static final String STRING_DESCRIPTION 	= "description";
	public static final String STRING_MEMBER 		= "members";
	
    private String id;
    private String name;
    private String price;
    private String iconSmall;
    private String iconLarge;
    private String type;
    private String description;
    private String member;

    
	public Item() {
	}

	public Item(String id, String name, String price, String iconSmall, String iconLarge, String type,
			String description, String member) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.iconSmall = iconSmall;
		this.iconLarge = iconLarge;
		this.type = type;
		this.description = description;
		this.member = member;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIconSmall() {
		return iconSmall;
	}
	public void setIconSmall(String iconSmall) {
		this.iconSmall = iconSmall;
	}
	public String getIconLarge() {
		return iconLarge;
	}
	public void setIconLarge(String iconLarge) {
		this.iconLarge = iconLarge;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
    
	public void displayItem()
	{	
		System.out.println("id: " + this.id); 
		System.out.println("name: " + this.name); 
		System.out.println("price: " + this.price); 
		System.out.println("iconSmall: " + this.iconSmall); 
		System.out.println("iconLarge: " + this.iconLarge); 
		System.out.println("type: " + this.type); 
		System.out.println("description: " + this.description); 
		System.out.println("member: " + this.member); 
	}
    
}
