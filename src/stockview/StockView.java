package stockview;

import stockcontroller.IActionListener;
import stockmodel.Canvas;

/**
 * The interface for view class, which contain all methods we need.
 */
public interface StockView {

  /**
   * The output of the operations we give to the function.
   *
   * @param s the output
   */
  void setOperationOutput(String s);

  /**
   * Get the stock name and return it.
   *
   * @return the stock name
   */
  String getStockName();

  /**
   * Get the start date and return it.
   *
   * @return the start date
   */
  String getStartDate();

  /**
   * Get the start date and return it.
   *
   * @return the start date
   */
  String getEndDate();

  /**
   * clear user's input.
   */
  void clearInput();

  /**
   * Set the listener for any actions.
   */
  void setListener(IActionListener listener);

  /**
   * Display this view.
   */
  void show();

  /**
   * Add a Canvas to a JFrame.
   *
   * @param g a graphic we wanted
   */
  void addJ(Canvas g);

}
