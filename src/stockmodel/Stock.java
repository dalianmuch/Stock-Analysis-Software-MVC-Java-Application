package stockmodel;

import java.util.Calendar;
import java.util.Map;

/**
 * This interface contains all methods that a stock should have.
 */
interface Stock {

  /**
   * Look up the price of a stock on a certain day.
   *
   * @param date the given certain date
   * @return the price on that certain date
   * @throws IllegalArgumentException if the given date cannot be found
   */
  double getStockPrice(Calendar date) throws IllegalArgumentException;

  /**
   * Determine if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param date the given certain date
   * @return true if if there is a buying opportunity for a certain stock on a certain day,
   *         otherwise false
   * @throws IllegalArgumentException if the given date is not a business day
   *                                  or there is no enough reference data
   */
  boolean isOpportunity(Calendar date) throws IllegalArgumentException;

  /**
   * Get historical (closing) prices for a stock for a certain date range.
   *
   * @param startDate the start date of that date range
   * @param endDate   the end date of that range
   * @return a collection of prices based on dates
   * @throws IllegalArgumentException if the start date is after the end date
   */
  Map<String, Double> getHistoricalPrices(Calendar startDate, Calendar endDate)
          throws IllegalArgumentException;

  /**
   * Determine if a stock trends up during a certain date range.
   *
   * @param startDate the start date of the date range
   * @param endDate   the end date of that date range
   * @return true if a stock trends up during a certain date range, otherwise false
   * @throws IllegalArgumentException if the start date is after the end date
   */
  boolean ifTrendUp(Calendar startDate, Calendar endDate) throws IllegalArgumentException;

  /**
   * Get the name of this stock.
   *
   * @return the name of this stock
   */
  String getName();

}
