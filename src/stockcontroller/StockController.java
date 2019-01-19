package stockcontroller;

import java.util.Calendar;

import stockmodel.StockModel;
import stockmodel.Canvas;
import stockview.StockView;

/**
 * this is the StockController class which implements all method in the IActionListener interface.
 */
public class StockController implements IActionListener {

  private StockModel model;
  private StockView view;
  private Canvas s2 = new Canvas();

  /**
   * This is the constructor of the StockController class with parameter m and v.
   *
   * @param m the model in the controller
   * @param v the view in the controller
   */
  public StockController(StockModel m, StockView v) {
    this.model = m;
    this.view = v;
    v.addJ(s2);
    view.setListener(this);
    view.show();
  }

  @Override
  public void action(String x) {
    switch (x) {
      case "Add Stock Button":
        String stockName = view.getStockName();
        try {
          StockApplication.getData(stockName);
        } catch (Exception e1) {
          //The program  will create a error file.
        }
        try {
          model.addStock(stockName, StockApplication.getTXTData(stockName));
        } catch (Exception e2) {
          view.setOperationOutput("Invalid input! Can not find the Stock.");
          break;
        }
        view.setOperationOutput("The Stock is already added!");
        break;

      case "Get trend":

        String stockName3 = view.getStockName();
        String startDate2 = view.getStartDate();
        String endDate2 = view.getEndDate();


        s2 = new Canvas(stockName3, startDate2, endDate2, this.model);
        view.addJ(s2);
        break;

      case "Get opportunity days":
        s2.repaint();
        //s2 = new Canvas();
        view.setOperationOutput("cleared all");
        view.addJ(s2);
        break;

      case "Exit Button":
        System.exit(0);
        break;
      default:
        System.exit(0);
    }
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











