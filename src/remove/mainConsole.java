/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */

package remove;

//import org.json.simple.JSONObject;
import org.json.*;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class mainConsole {

    //static DataBase data = new DataBase();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            System.out.println(jsonText);
            JSONObject json = new JSONObject(jsonText);
            //System.out.println(json.toString(3));
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray readArrayFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            //System.out.println(jsonText);
            JSONArray json = new JSONArray(jsonText);
            //System.out.println(json.toString(3));
            return json;
        } finally {
            is.close();
        }
    }

    public static JsonItem fetchData(String url) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(url);
        String price = json.getJSONObject("item").getJSONObject("current").get("price").toString();
        String name = json.getJSONObject("item").get("name").toString();
        String icon = json.getJSONObject("item").get("icon").toString();
        return new JsonItem(name, price, icon);
    }

    public static void main(String args[])  {

        // System.out.println(data.searchName("coal"));
        //System.out.println(data.binarySearch(data.getDB(), "32272", 0, data.getDB().size()-1));
        //String url = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=453";
        //JSONObject json = readJsonFromUrl("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=453");
//        System.out.println(json.toString(5));
//        System.out.println(json.getJSONObject("item").getJSONObject("current").get("price"));
//        System.out.println(json.getJSONObject("item").get("icon"));
        //System.out.println(fetchObject(url, "item"));
        //fetchData(url, url);
        
                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
    }
}
