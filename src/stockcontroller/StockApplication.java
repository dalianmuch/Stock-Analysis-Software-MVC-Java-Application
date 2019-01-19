package stockcontroller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is for getting the information from the internet.
 */
public class StockApplication {
  /**
   * This method is for get txt file from the internet.
   *
   * @param stock The stock we want
   * @throws Exception when we meet some problems dragging the information from the internet
   */
  public static void getData(String stock) throws Exception {
    URL url;

    String spec = ("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stock
            + "&outputsize=full&apikey=QKP9VXMABTB3Z254&datatype=csv");


    //historical data from vantage API
    url = new URL(spec);


    InputStream in;

    in = url.openStream();

    String filename = stock + ".txt";
    PrintWriter writer = new PrintWriter(filename, "UTF-8");


    Scanner sc = new Scanner(in);
    writer.println(sc.next());

    while (sc.hasNext()) {
      String date = sc.next();
      writer.println(date);
      writer.flush();
    }
  }

  /**
   * This method will convert the txt file to a Map with the stock's data.
   *
   * @param stock the stock we want to convert
   * @return a map with the information about the price
   * @throws Exception when we meet some problems to read a file
   */
  public static Map<String, Double> getTXTData(String stock) throws Exception {

    File file = new File(stock + ".txt");
    Map<String, Double> data = new HashMap<>();


    Scanner sc = new Scanner(file);
    sc.next();

    while (sc.hasNext()) {
      String date = sc.next();
      String[] temp = date.split(",");
      data.put(temp[0], Double.parseDouble(temp[4]));

    }
    return data;
  }
}
