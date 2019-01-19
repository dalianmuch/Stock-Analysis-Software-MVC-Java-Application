package stockmodel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * This class represents a basket of stocks with the given stocks and shares of each stock.
 */
class BasketImpl implements Basket {
  private Map<String, Stock> stockPrices;
  private Map<String, Integer> stockShares;

  BasketImpl() {
    stockPrices = new HashMap<>();
    stockShares = new HashMap<>();
  }


  @Override
  public void addStocks(Stock stock, int shares) {
    stockPrices.put(stock.getName(), stock);
    stockShares.put(stock.getName(), shares);
  }

  @Override
  public double getBasketPrice(Calendar date) throws IllegalArgumentException {
    int sumShares = 0;
    double sumPrice = 0;
    for (Map.Entry<String, Stock> entry : stockPrices.entrySet()) {
      try {
        sumPrice = sumPrice + entry.getValue().getStockPrice(date)
                * stockShares.get(entry.getKey());
        sumShares = sumShares + stockShares.get(entry.getKey());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("The given date is not a business day");
      }
    }
    return sumPrice / sumShares;
  }

  @Override
  public boolean ifTrendUp(Calendar startDate, Calendar endDate) throws IllegalArgumentException {
    Double startPrice = null;
    Double endPrice = null;
    if (startDate.compareTo(endDate) > 0) {
      throw new IllegalArgumentException("The start date cannot be after the end date.");
    }
    while (startDate.compareTo(endDate) <= 0) {
      try {
        startPrice = getBasketPrice(startDate);
        break;
      } catch (IllegalArgumentException e) {
        startDate.add(Calendar.DAY_OF_MONTH, 1);
      }
    }

    while (endDate.compareTo(startDate) >= 0) {
      try {
        endPrice = getBasketPrice(endDate);
        break;
      } catch (IllegalArgumentException e) {
        endDate.add(Calendar.DAY_OF_MONTH, -1);
      }
    }
    return startPrice != null && endPrice != null && startPrice - endPrice < 0;
  }
}
