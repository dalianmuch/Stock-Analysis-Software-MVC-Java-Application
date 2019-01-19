package stockview;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import stockcontroller.IActionListener;
import stockmodel.Canvas;

/**
 * This is the StockViewImpl class which implements all method in the StockView interface.
 */
public class StockViewImpl extends JFrame implements StockView {

  private JLabel display;
  private JButton addButton;
  private JButton getTrend;
  private JButton getOpportunityDays;
  private JButton exitButton;
  private JTextField stockName;
  private JTextField startDate;
  private JTextField endDate;

  /**
   * This is the constructor of the StockImpl class which contain the parameter cap.
   *
   * @param cap name of the program
   */
  public StockViewImpl(String cap) {
    super(cap);

    setSize(1400, 700);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    JLabel name;
    JLabel start;
    JLabel end;

    name = new JLabel("stock name");
    add(name);
    stockName = new JTextField(10);
    this.add(stockName);

    start = new JLabel("start date");
    this.add(start);
    startDate = new JTextField(10);
    this.add(startDate);

    end = new JLabel("end date");
    this.add(end);
    endDate = new JTextField(10);
    this.add(endDate);

    addButton = new JButton("add Stock");
    addButton.setActionCommand("Add Stock Button");
    this.add(addButton);

    getTrend = new JButton("get trend");
    getTrend.setActionCommand("Get trend");
    this.add(getTrend);

    getOpportunityDays = new JButton("clear all");
    getOpportunityDays.setActionCommand("Get opportunity days");
    this.add(getOpportunityDays);

    //exit Button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    this.display = new JLabel("Welcome to the stock world!");
    this.add(display);
  }

  @Override
  public void setOperationOutput(String s) {
    this.display.setText(s);
  }

  @Override
  public String getStockName() {
    return stockName.getText();
  }

  @Override
  public String getStartDate() {
    return startDate.getText();
  }

  @Override
  public String getEndDate() {
    return endDate.getText();
  }

  @Override
  public void clearInput() {
    this.display.setText("");
  }

  @Override
  public void setListener(IActionListener listener) {
    ActionListener listen = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.action(e.getActionCommand());
      }
    };
    addButton.addActionListener(listen);
    getTrend.addActionListener(listen);
    getOpportunityDays.addActionListener(listen);
    exitButton.addActionListener(listen);
  }

  public void addJ(Canvas g) {
    this.add(g);
    repaint();
  }
}
