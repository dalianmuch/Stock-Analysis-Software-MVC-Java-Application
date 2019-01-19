import stockcontroller.StockController;
import stockmodel.StockModel;
import stockmodel.StockModelImpl;
import stockview.StockView;
import stockview.StockViewImpl;


/**
 * This the main class.
 */
public class Main {
  /**
   * This this the main method for initializing the program.
   *
   * @param args the user's input
   */
  public static void main(String []args) {
    StockView view = new StockViewImpl("Stock Program");
    StockModel model = new StockModelImpl();
    StockController controller = new StockController(model, view);
  }
}
