package stockmodel;

import java.util.Calendar;

/**
 * This interface contains all methods that a basket should have.
 */
interface Basket {

  /**
   * Add stocks to a basket with its given shares.
   *
   * @param stock  the given stock
   * @param shares the shares of the given stock
   */
  void addStocks(Stock stock, int shares);

  /**
   * Get the stock price of a basket on a given date.
   *
   * @param date the given date
   * @return the stock price of a basket
   * @throws IllegalArgumentException if the given date is not a business day
   */
  double getBasketPrice(Calendar date) throws IllegalArgumentException;

  /**
   * Determine if the price of the basket trends up during a certain date range.
   *
   * @param startDate the start date of the date range
   * @param endDate   the end date of the date range
   * @return true if the price of the basket trends up during a certain date range,
   *         otherwise false
   * @throws IllegalArgumentException if the start date is after the end date
   */
  boolean ifTrendUp(Calendar startDate, Calendar endDate) throws IllegalArgumentException;
}
