/**
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class DataBase {

	private static final Logger logger = LoggerFactory.getLogger(DataBase.class.getName());
	private static final String dataBaseRootPath = System.getProperty("user.dir") + "\\";
	private static final String dataBaseFolder = "localData";
	private static final String dataBaseFileName = "items.txt";
	private static final String dataBaseFullPath = dataBaseRootPath + dataBaseFolder + "\\";
	private static final String dataBaseFullFilePath = dataBaseRootPath + dataBaseFolder + "\\" +dataBaseFileName;
	private static final String urlByItemId = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";
	private static final String urlByItemCat = "http://services.runescape.com/m=itemdb_rs/api/catalogue/items.json?category=";
	private static final String urlCategory = "http://services.runescape.com/m=itemdb_rs/api/catalogue/category.json?category=";
	private static final String alphaNumber = "&alpha=%";
	private static final String alpha = "&alpha=";
	private static final String paging = "&page=1";
	private static final int categroySize = 38;
	private static final int itemSearchSize = 10;
	
	//testing line
	private static final String fullURL = "http://services.runescape.com/m=itemdb_rs/api/catalogue/items.json?category=1&alpha=a&page=1";
	
    public DataBase() {

    }
    
    public static void loadAllItemFromApi()
    {

    }
    
    /**
     * Found total can be loaded from api
     * @return
     * @throws Exception
     */
    private static int findTotalItemCountInApi() throws Exception
    {
    	int itemTotalCount =0;
    	for(int x = 0; x <categroySize; x++)
    	{
    		JSONObject json = loadJsonObjectFromURL(urlCategory+x);
    		JSONArray jsonArray = null;
    		int categoryCount =0;
    		if(json.getJSONArray("alpha") == null)
    			continue;
    		else
    		{
    			jsonArray = json.getJSONArray("alpha");
	    		logger.info("load category ::" + x);
	    		System.out.print("load item :: ");
	    		for(int y = 0; y<27; y++)
	    		{
	    			JSONObject jsonLetter = jsonArray.getJSONObject(y);
	    			System.out.print(jsonLetter.getString("letter") + " ");
	    			itemTotalCount += jsonLetter.getInt("items");
	    			categoryCount += jsonLetter.getInt("items");;
	    			//System.out.println(jsonLetter.getInt("items"));
	    		}
	    		System.out.println("\n total category ::: " + categoryCount);
    		}
    	}
    	return itemTotalCount;
    }
    
    private static List<Item> loadAllItemFromApiIntoList() throws Exception
    {
    	logger.info("loadAllItemFromApiIntoList -- Entry");
    	List<Item> allItems = new ArrayList<Item>();
    	for(char alphabet='a'; alphabet <='z'; alphabet++  )
    	{
    		logger.info("load letter :::" + alphabet);
    		List<Item> result = createSearchObject(String.valueOf(alphabet));
    		allItems.addAll(result);
    	}
    	logger.info("loadAllItemFromApiIntoList -- Exit");
    	return allItems;
    }
    
    public static void loadFromFile(List<Item> itemList)
    {
    	Scanner reader = null;
		try {
			reader = new Scanner(new FileReader(DataBase.getDatabasefullfilepath()));
			reader.useDelimiter(",");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		}
        while (reader.hasNext()){
            System.out.println(reader.next());
        }
    }
    
    public static void saveToFile()
    {
		List<String> lines = Arrays.asList("The first line", "The second line");
		Path file = Paths.get("pathFile.txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public static List<Item> createSearchObject(String itemName) throws Exception
    {
    	logger.info("createSearchObject -- Entry");
    	List<Item> itemList = new ArrayList<>();
    	char letter = itemName.charAt(0);
    	int catNo = 0;
    	while (itemList.size()<DataBase.getItemsearchsize() && catNo<DataBase.getCategroysize())
    	{
    		String url = "";
    		if(Character.isDigit(letter))
    			url = DataBase.getUrlbyitemcat() + String.valueOf(catNo) + DataBase.getAlphanumber() + itemName + DataBase.paging;
    		else
  			url = DataBase.getUrlbyitemcat() + String.valueOf(catNo) + DataBase.getAlpha() + itemName + DataBase.paging;
    		itemList.addAll(DataBase.processJsonIntoItemList(DataBase.loadJsonObjectFromURL(url)));
    		catNo ++;
    	}
    	logger.info("createSearchObject -- Exit");
    	return itemList;
    }
    
    /**
     * process json object and map into item 
     * @param jsonObject
     * @return 
     */
    private static List<Item> processJsonIntoItemList(JSONObject jsonObject)
    {
    	List<Item> itemList = new ArrayList<>();
    	if(jsonObject.getJSONArray("items")!=null)
    	{
    		JSONArray jsonArray = jsonObject.getJSONArray("items");	
	    	if(jsonArray.length()>0)
	    	{
	    		for(int i =0; i<jsonArray.length(); i++)
	    		{
	    			JSONObject jsonItem = jsonArray.getJSONObject(i);
	    			JSONObject priceJson = jsonItem.getJSONObject("current");
	    			itemList.add(
	    					new Item(
	    					jsonItem.get(Item.STRING_ID).toString(), 
	    					jsonItem.get(Item.STRING_NAME).toString(),
	    					priceJson.get(Item.STRING_PRICE).toString(),
	    					jsonItem.get(Item.STRING_ICON_SMALL).toString(),
	    					jsonItem.get(Item.STRING_ICON_LARGE).toString(),
	    					jsonItem.get(Item.STRING_TYPE).toString(),
	    					jsonItem.get(Item.STRING_DESCRIPTION).toString(),
	    					jsonItem.get(Item.STRING_MEMBER).toString()
	    					));
	    		}
	    	}
    	}
    	return itemList;
    }
    
    /**
     * Parse url into JSON object
     * @param URL
     * @return
     * @throws Exception
     */
    private static JSONObject loadJsonObjectFromURL(String URL) throws Exception
    {
		JSONObject jsonObject = null;
			URL url = new URL(URL);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			String jsonText = null;
			while ((jsonText = bufferedReader.readLine())!=null)
				jsonObject = new JSONObject(jsonText);
			bufferedReader.close();
		return jsonObject;
    }
    
	public static String getDatabaserootpath() {
		return dataBaseRootPath;
	}

	public static String getDatabasefolder() {
		return dataBaseFolder;
	}

	public static String getDatabasefilename() {
		return dataBaseFileName;
	}

	public static String getDatabasefullpath() {
		return dataBaseFullPath;
	}

	public static String getDatabasefullfilepath() {
		return dataBaseFullFilePath;
	}

	public static String getUrlbyitemid() {
		return urlByItemId;
	}

	public static String getUrlbyitemcat() {
		return urlByItemCat;
	}

	public static String getAlphanumber() {
		return alphaNumber;
	}

	public static String getAlpha() {
		return alpha;
	}

	public static String getPaging() {
		return paging;
	}

	public static String getFullurl() {
		return fullURL;
	}

	public static int getCategroysize() {
		return categroySize;
	}

	public static int getItemsearchsize() {
		return itemSearchSize;
	}
	
	public static void main (String[] args) throws Exception
	{
		//System.out.println(findTotalItemCountInApi());
		
		List<Item> item = loadAllItemFromApiIntoList();
		
		//System.out.println(loadJsonObjectFromURL(DataBase.fullURL));
	}
}
