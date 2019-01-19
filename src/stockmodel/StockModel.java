package stockmodel;


import java.util.Map;

/**
 * This interface contains all methods that the model of stock should have.
 */
public interface StockModel {

  /**
   * Add a certain stock to the model with the given name and its price data.
   *
   * @param name      the name of the stock
   * @param priceData the price data of that stock
   */
  void addStock(String name, Map<String, Double> priceData);

  /**
   * Get the price of a stock on a certain day.
   *
   * @param stockName the name of the stock to be searched
   * @param date      the given certain date
   * @return the price of a stock on a certain day
   * @throws IllegalArgumentException if the given stock cannot be found in the model
   *                                  or the given date is not a business day
   */
  double getStockPrice(String stockName, String date) throws IllegalArgumentException;


  /**
   * Determine if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param stockName the name of a certain stock
   * @param date      the given certain date
   * @return true if there is a buying opportunity for a certain stock on a certain day,
   *         otherwise false
   * @throws IllegalArgumentException if the given stock cannot be found in the model
   *                                  or the given date is not a business day
   *                                  or there is no enough reference data in the model
   */
  boolean isOpportunity(String stockName, String date) throws IllegalArgumentException;

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   *
   * @param stockName the name of a certain stock
   * @param startDate the given start date
   * @param endDate   the given end date
   * @return a collection of closing prices with the corresponding dates
   * @throws IllegalArgumentException if the given stock cannot be found in the model
   *                                  or the start date is after the end date
   */
  Map<String, Double> getHistoricalPrices(String stockName, String startDate, String endDate)
          throws IllegalArgumentException;

  /**
   * Create a basket of stocks comprising of shares of one or more stocks.
   *
   * @param nameOfBasket the name of the created basket
   * @param setOfStocks  the group of stocks that this basket wants to contain
   * @throws IllegalArgumentException if the given stock cannot be found in the model
   *                                  or the name of the basket is the same as the name of
   *                                  one previous added stock
   */
  void addBasket(String nameOfBasket, Map<String, Integer> setOfStocks)
          throws IllegalArgumentException;

  /**
   * Create a basket of stocks comprising of shares of one or more stocks and
   * then determine its price on a certain date.
   *
   * @param nameOfBasket the name of different stocks with its corresponding shares
   * @param date         the given certain date
   * @return the price of the created basket on a certain date
   * @throws IllegalArgumentException if the given stock cannot be found in the model
   *                                  or the given date is not a business day
   */
  double getPriceBasketDate(String nameOfBasket, String date) throws IllegalArgumentException;

  /**
   * Determine if a stock or a basket trends up during a certain date range.
   *
   * @param name      the name of a stock or a basket
   * @param startDate the start date of the given date range
   * @param endDate   the end date of the given date range
   * @return true if it trends up, otherwise false
   * @throws IllegalArgumentException if the given stock or basket cannot be found in the model
   *                                  or the start date is after the end date.
   */
  boolean ifTrendUp(String name, String startDate, String endDate)
          throws IllegalArgumentException;

}
