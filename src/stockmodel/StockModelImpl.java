package stockmodel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a model of stock with the given stocks and baskets.
 */
public class StockModelImpl implements StockModel {
  private Map<String, Stock> stockData;
  private Map<String, Basket> basketData;
  private String startDate;



  /**
   * Construct an object of this stock model class.
   */
  public StockModelImpl() {
    stockData = new HashMap<>();
    basketData = new HashMap<>();
  }

  /**
   * A helper function. Transform the string date to a Calendar date.
   *
   * @param date the given date in a string format
   * @return the result date in a Calendar format
   */
  public static Calendar stringToCalendar(String date) {
    return new GregorianCalendar(Integer.valueOf(date.split("[-]")[0]),
            Integer.valueOf(date.split("[-]")[1]) - 1
            , Integer.valueOf(date.split("[-]")[2]));
  }


  @Override
  public void addStock(String name, Map<String, Double> priceData) {
    Set<String> dayData = priceData.keySet();
    Map<Calendar, Double> newPriceData = new HashMap<>();
    for (String obj : dayData) {
      Calendar newDate = stringToCalendar(obj);
      newPriceData.put(newDate, priceData.get(obj));
    }
    Stock tempStock = new StockImpl(name, newPriceData);
    stockData.put(name, tempStock);
  }

  @Override
  public double getStockPrice(String stockName, String date) throws IllegalArgumentException {
    if (stockData.get(stockName) == null) {
      throw new IllegalArgumentException("The given stock cannot be found.");
    } else {
      Calendar newDate = stringToCalendar(date);
      try {
        return stockData.get(stockName).getStockPrice(newDate);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  @Override
  public boolean isOpportunity(String stockName, String date) throws IllegalArgumentException {
    if (stockData.get(stockName) == null) {
      throw new IllegalArgumentException("The given stock cannot be found.");
    } else {
      try {
        Calendar newDate = stringToCalendar(date);
        return stockData.get(stockName).isOpportunity(newDate);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  @Override
  public Map<String, Double> getHistoricalPrices(String stockName, String startDate,
                                                 String endDate)
          throws IllegalArgumentException {
    if (stockData.get(stockName) == null) {
      throw new IllegalArgumentException("The given stock cannot be found.");
    } else {
      try {
        Calendar newStartDate = stringToCalendar(startDate);
        Calendar newEndDate = stringToCalendar(endDate);
        return stockData.get(stockName).getHistoricalPrices(newStartDate, newEndDate);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  @Override
  public void addBasket(String nameOfBasket, Map<String,
          Integer> setOfStocks) throws IllegalArgumentException {
    Basket basket = new BasketImpl();
    Set<String> stockName = setOfStocks.keySet();

    // The given name of basket cannot be the same as the name of existed stock.
    if (stockData.containsKey(nameOfBasket)) {
      throw new IllegalArgumentException("The given name of basket cannot be the same as "
              + "the name of existed stock");
    }

    for (String name : stockName) {
      if (stockData.get(name) == null) {
        throw new IllegalArgumentException("The given stock cannot be found.");
      } else {
        basket.addStocks(stockData.get(name), setOfStocks.get(name));
      }
    }
    basketData.put(nameOfBasket, basket);
  }

  @Override
  public double getPriceBasketDate(String nameOfBasket,
                                   String date) throws IllegalArgumentException {
    if (basketData.get(nameOfBasket) == null) {
      throw new IllegalArgumentException("The given basket cannot be found.");
    } else {
      Calendar newDate = stringToCalendar(date);
      try {
        return basketData.get(nameOfBasket).getBasketPrice(newDate);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  @Override
  public boolean ifTrendUp(String name, String startDate,
                           String endDate) throws IllegalArgumentException {
    Calendar newStartDate = stringToCalendar(startDate);
    Calendar newEndDate = stringToCalendar(endDate);
    if (stockData.get(name) == null) {
      if (basketData.get(name) == null) {
        throw new IllegalArgumentException("The given name of stock or basket cannot be found.");
      } else {
        try {
          return basketData.get(name).ifTrendUp(newStartDate, newEndDate);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException(e.getMessage());
        }
      }
    } else {
      try {
        return stockData.get(name).ifTrendUp(newStartDate, newEndDate);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }


}

