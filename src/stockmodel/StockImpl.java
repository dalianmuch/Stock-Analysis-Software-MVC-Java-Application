package stockmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * This class represents a stock with the given name and a collection of historical prices
 * based on dates.
 */
class StockImpl implements Stock {

  private String name;
  private Map<Calendar, Double> prices;


  /**
   * Construct an object of this class with the given name and collection of prices.
   *
   * @param name   the given name of a stock
   * @param prices the given prices of a stock
   */
  StockImpl(String name, Map<Calendar, Double> prices) {
    this.name = name;
    this.prices = prices;
  }

  @Override
  public double getStockPrice(Calendar date) throws IllegalArgumentException {
    if (prices.get(date) == null) {
      throw new IllegalArgumentException("The given date must be a business day.");
    } else {
      return prices.get(date);
    }
  }

  @Override
  public boolean isOpportunity(Calendar date) throws IllegalArgumentException {
    int xDay = 50;
    int yDay = 200;
    SortedMap<Calendar, Double> sortDatePrices = new TreeMap<>(prices);
    Calendar firstDate = sortDatePrices.firstKey();
    if (prices.get(date) == null) {
      throw new IllegalArgumentException("The given date must be a business day.");
    } else {
      double sumXDays = 0;
      double sumYDays = 0;
      int flag = 0;

      while (flag < yDay) {
        if (flag < xDay) {
          if (prices.get(date) != null) {
            sumXDays = sumXDays + prices.get(date);
            sumYDays = sumYDays + prices.get(date);
            flag++;
          }
        }
        if (flag >= xDay) {
          if (prices.get(date) != null) {
            sumYDays = sumYDays + prices.get(date);
            flag++;
          }
        }
        date.add(Calendar.DAY_OF_MONTH, -1);
        if (date.compareTo(firstDate) < 0) {
          throw new IllegalArgumentException("Do not have enough reference data.");
        }
      }
      return sumXDays / xDay > sumYDays / yDay;
    }
  }

  @Override
  public Map<String, Double> getHistoricalPrices(Calendar startDate, Calendar endDate)
          throws IllegalArgumentException {
    Map<String, Double> result = new TreeMap<>();
    Double priceData;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    if (startDate.compareTo(endDate) > 0) {
      throw new IllegalArgumentException("The start date cannot be after the end date.");
    } else {
      while (startDate.compareTo(endDate) <= 0) {
        priceData = prices.get(startDate);
        result.put(format.format(startDate.getTime()), priceData);
        startDate.add(Calendar.DAY_OF_MONTH, 1);
      }
    }
    return result;
  }

  @Override
  public boolean ifTrendUp(Calendar startDate, Calendar endDate) throws IllegalArgumentException {
    Double startPrice = null;
    Double endPrice = null;
    if (startDate.compareTo(endDate) > 0) {
      throw new IllegalArgumentException("The start date cannot be after the end date.");
    }
    while (startDate.compareTo(endDate) <= 0) {
      if (prices.get(startDate) != null) {
        startPrice = prices.get(startDate);
        break;
      } else {
        startDate.add(Calendar.DAY_OF_MONTH, 1);
      }
    }
    while (endDate.compareTo(startDate) >= 0) {
      if (prices.get(endDate) != null) {
        endPrice = prices.get(endDate);
        break;
      } else {
        endDate.add(Calendar.DAY_OF_MONTH, -1);
      }
    }

    return startPrice != null && endPrice != null && startPrice - endPrice < 0;

  }

  @Override
  public String getName() {
    return name;
  }
}
