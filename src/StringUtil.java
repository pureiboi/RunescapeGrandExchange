/**
 * Class to handle String returning related
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	/**
	 * Process object list and convert into string list of one of the object attribute.
	 * @param listObject the list containing objects
	 * @param methodName the object attribute name
	 * @return
	 */
	public static List<String> objectListToStringList (List<?> listObject, String methodName) throws Exception
	{
		//put object list into string list
		List<String> resultStringList = new ArrayList<String>();
		//get all method names
		Method[] objectMethods = null;
		if(listObject.size()>0 && !listObject.isEmpty())
			objectMethods = listObject.get(0).getClass().getMethods();
		for (int i = 0; i < objectMethods.length; i++) {
			Method currMethod = objectMethods[i];
			if (!currMethod.getName().startsWith("get")
					|| currMethod.getParameterTypes().length > 0)
				continue;
			if(listObject.size()>0 && !listObject.isEmpty() && currMethod.getName().toLowerCase().contains(methodName.toLowerCase()))
			{
				for(Object obj : listObject)
				{
					Object string = currMethod.invoke(obj, new Object[] {});
					resultStringList.add(string.toString());
				}
			}
		}
		return resultStringList;
	}
	
	//module testing
	public static void main(String args[]) throws Exception  {
		/*
		String s = "hello";
		String t = "worldHello";
		if(t.contains(s))
		{
			System.out.println("ASDASDAS");
		}*/
		List<Item> itemlist = DataBase.createSearchObject("pie");
		System.out.println("ASD");
	}
}
