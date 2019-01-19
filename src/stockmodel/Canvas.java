package stockmodel;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JPanel;

/**
 * This is a class represent of a canvas.
 */
public class Canvas extends JPanel {

  private String stockName;
  private String startDate;
  private String endDate;
  private StockModel model;
  private Map<String, Double> prices;

  public Canvas() {
    setSize(1400, 400);
    setLocation(100, 100);
  }

  /**
   * The constructor of the Canvas class.
   *
   * @param stockName the name of the stock
   * @param startDate the start date
   * @param endDate   the end date
   * @param model     the model we needed
   */
  public Canvas(String stockName, String startDate, String endDate, StockModel model) {
    this.stockName = stockName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.model = model;
    prices = model.getHistoricalPrices(stockName, startDate, endDate);

    setSize(1400, 400);
    setLocation(100, 100);
  }

  /**
   * Make the components of the graphics.
   *
   * @param g the graphic
   */
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    if (this.dateCount() == -1) {
      return;
    }
    int divide = 1400 / this.dateCount();
    int i = 0;
    double largest = 0;
    double min = 1000000;
    for (String key : prices.keySet()) {
      if (prices.get(key) != null) {
        if (prices.get(key) > largest) {
          largest = prices.get(key);
        }
        if (prices.get(key) < min) {
          min = prices.get(key);
        }
      }
      i++;
    }

    int j = 0;
    double trigger = 0;
    for (String key : prices.keySet()) {
      if (prices.get(key) != null) {
        double s = 400 * ((prices.get(key) - min) / (largest - min));
        if (trigger == 0) {
          trigger = s;
        }
        if (model.isOpportunity(stockName, key)) {
          g.setColor(Color.GREEN);
          g.drawOval(divide * j, (400 - (int) s), 2, 2);
        } else {
          g.setColor(Color.RED);
          g.drawOval(divide * j, (400 - (int) s), 2, 2);
        }
      }
      j++;
    }


    String tempe = this.endDate;
    Calendar e = StockModelImpl.stringToCalendar(this.endDate);

    while (prices.get(tempe) == null) {
      e.add(Calendar.DAY_OF_MONTH, -1);
      tempe = this.calendarToString(e);
    }

    double price = 400 * ((prices.get(tempe) - min) / (largest - min));
    g.setColor(Color.BLACK);
    g.drawLine(0
            , (400 - (int) trigger)
            , divide * this.dateCount()
            , (400 - (int) price));

  }

  /**
   * Count the date we spend.
   *
   * @return the number of days
   */
  private int dateCount(){
    int date = 0;
    Calendar s;
    Calendar e;
    try {
      s = StockModelImpl.stringToCalendar(this.startDate);
      e = StockModelImpl.stringToCalendar(this.endDate);
    }
    catch (Exception ex) {
      return -1;
    }
    while (s.compareTo(e) <= 0) {
      date++;
      s.add(Calendar.DAY_OF_MONTH, 1);
    }
    return date;
  }

  /**
   * This method could help us to convert the Calendar type to the the String type.
   *
   * @param date the input of Calender data type
   * @return the string of the date
   */
  private String calendarToString(Calendar date) {
    String sDate = "";
    sDate = sDate + date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1)
            + "-" + date.get(Calendar.DAY_OF_MONTH);
    return sDate;
  }


}
