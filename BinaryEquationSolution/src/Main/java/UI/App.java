package UI;

import GA.Individual;
import GA.PopulationSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class App extends AbstractApp{
    private static Logger log = Logger.getLogger(App.class.getName());

    protected static JPanel mainPanel = null;
    protected JPanel northPanel = null;
    protected JPanel rightPannel = null;
    protected JButton startBtn;
    protected JButton stopBtn;
    protected JButton pauseBtn;
    protected JButton resetBtn;
    private BGCanvas bgPanel;
    private JComboBox<String> faceCombo;
    private TextField stopPec,maxStp;
    private TextField x0,x1,x2,x3,x4,x5,x6,x7,x8,x9;

    PopulationSet sim = null;
    Thread thread = null;

    /**
     * Sample app constructor
     */
    public App() {
        frame.setSize(1200, 900); // initial Frame size
        frame.setTitle("App");

        showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }

    @Override
    public JPanel getMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, getNorthPanel());
        mainPanel.add(BorderLayout.EAST, getRightPanel());

        // set canvas
        bgPanel = new BGCanvas();
        mainPanel.add(BorderLayout.CENTER, bgPanel);
        return mainPanel;
    }

    /**
     * Create a top panel that will hold control buttons
     *
     * @return
     */
    public JPanel getNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.RED);

        startBtn = new JButton("Start");
        northPanel.add(startBtn);
        startBtn.addActionListener(this); // Allow the app to hear about button pushes

        stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        pauseBtn = new JButton("Pause"); // Allow the app to hear about button pushes
        pauseBtn.addActionListener(this);
//        northPanel.add(pauseBtn);
//
        resetBtn = new JButton("Reset"); // Allow the app to hear about button pushes
        resetBtn.addActionListener(this);
        northPanel.add(resetBtn);

        // set rule comboBox
        faceCombo = new JComboBox<String>();
        faceCombo.addItemListener(this);
        faceCombo.setEnabled(true);
        faceCombo.addItem("How many decimal");
        faceCombo.addItem("0");
        faceCombo.addItem("1");
        faceCombo.addItem("2");
        faceCombo.addItem("3");
        faceCombo.addItem("4");
        //actionListener
        faceCombo.addActionListener(this);
        northPanel.add(faceCombo, BorderLayout.SOUTH);

        //set textfield
        Label maxStplb=new Label("Max generation");
        Label lineSzlb=new Label("Stop when change less then");
        Label per = new Label("%");
        stopPec =new TextField(1);
        stopPec.setText("5");
        maxStp=new TextField(5);
        maxStp.setText("10000");

        northPanel.add(maxStplb, BorderLayout.SOUTH);
        northPanel.add(maxStp, BorderLayout.SOUTH);
        northPanel.add(lineSzlb, BorderLayout.SOUTH);
        northPanel.add(stopPec, BorderLayout.SOUTH);
        northPanel.add(per, BorderLayout.SOUTH);

        return northPanel;
    }

    /**
     * Create a right panel that will hold control buttons
     *
     * @return
     */
    public JPanel getRightPanel() {
        rightPannel = new JPanel();
        rightPannel.setLayout(new FlowLayout());
        rightPannel.setPreferredSize(new Dimension(90, rightPannel.getHeight()));
        rightPannel.setBackground(Color.RED);

        //set textfield
        Label l0 = new Label("x0");
        Label l1 = new Label("x1");
        Label l2 = new Label("x2");
        Label l3 = new Label("x3");
        Label l4 = new Label("x4");
        Label l5 = new Label("x5");
        Label l6 = new Label("x6");
        Label l7 = new Label("x7");
        Label l8 = new Label("x8");
        Label l9 = new Label("x9");

        x0=new TextField(4);
        x0.setText("2000");
        x1=new TextField(4);
        x1.setText("2000");
        x2=new TextField(4);
        x2.setText("2000");
        x3=new TextField(4);
        x3.setText("2000");
        x4=new TextField(4);
        x4.setText("2000");
        x5=new TextField(4);
        x5.setText("2000");
        x6=new TextField(4);
        x6.setText("2000");
        x7=new TextField(4);
        x7.setText("2000");
        x8=new TextField(4);
        x8.setText("2000");
        x9=new TextField(4);
        x9.setText("2000");

        rightPannel.add(l0, BorderLayout.SOUTH);
        rightPannel.add(x0, BorderLayout.SOUTH);
        rightPannel.add(l1, BorderLayout.SOUTH);
        rightPannel.add(x1, BorderLayout.SOUTH);
        rightPannel.add(l2, BorderLayout.SOUTH);
        rightPannel.add(x2, BorderLayout.SOUTH);
        rightPannel.add(l3, BorderLayout.SOUTH);
        rightPannel.add(x3, BorderLayout.SOUTH);
        rightPannel.add(l4, BorderLayout.SOUTH);
        rightPannel.add(x4, BorderLayout.SOUTH);
        rightPannel.add(l5, BorderLayout.SOUTH);
        rightPannel.add(x5, BorderLayout.SOUTH);
        rightPannel.add(l6, BorderLayout.SOUTH);
        rightPannel.add(x6, BorderLayout.SOUTH);
        rightPannel.add(l7, BorderLayout.SOUTH);
        rightPannel.add(x7, BorderLayout.SOUTH);
        rightPannel.add(l8, BorderLayout.SOUTH);
        rightPannel.add(x8, BorderLayout.SOUTH);
        rightPannel.add(l9, BorderLayout.SOUTH);
        rightPannel.add(x9, BorderLayout.SOUTH);

        return rightPannel;
    }

    private void startSim() {
        //System.out.println("wolfApp startsim");
        TextField[] textFields = {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9};
        pauseBtn.setEnabled(true);
        if (true) {
            int[] array1 = new int[10];
            for (int i = 0; i < textFields.length; i++) {
                System.out.println("add x " + i);
                array1[i] = Integer.parseInt(textFields[i].getText());
            }
            Individual.range = array1;
            Individual.calculateCutIndex();
            if (sim == null) {
                createSim();
            }
            sim.MAX_GENERATION = Integer.parseInt(maxStp.getText());
            sim.stopPec = Integer.parseInt(stopPec.getText());
            maxStp.setEnabled(false);
            stopPec.setEnabled(false);
            sim.setPaused(false);
            if (sim.isRunning()) {
                return;
            }
            if (thread == null) {
                thread = new Thread(sim);
                thread.start();
            }
            resetButtons(true);
        }
    }


    private void resetButtons(boolean bl) {
        //startBtn.setEnabled(bl);
        stopBtn.setEnabled(true);
        pauseBtn.setEnabled(true);
    }

    private void pauseSim() {
        if (sim == null) return;
        if (!sim.isRunning())
            return;
        while (!sim.isPaused()) {
            startBtn.setEnabled(true);//enable start
            sim.setPaused(true);
            pauseBtn.setEnabled(false);//变灰
			/*try {
				thread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        }
    }

    private void stopSim() {
        if (sim == null) return;
        sim.setDone(true);
        sim = null;
        thread = null;
        stopBtn.setEnabled(true);
        pauseBtn.setEnabled(true);
        startBtn.setEnabled(true);
        maxStp.setEnabled(true);
        stopPec.setEnabled(true);
    }

    private void resetSim() {
        if (sim == null) return;
        sim.setDone(true);
        sim = null;
        thread = null;
        stopBtn.setEnabled(true);
        pauseBtn.setEnabled(true);
        startBtn.setEnabled(true);
        maxStp.setEnabled(true);
        stopPec.setEnabled(true);
    }

    private void createSim() {
        System.out.println("wolfApp createSim");
        sim = new PopulationSet();
        sim.addObserver(bgPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("We received an ActionEvent " + e);
        //System.out.println(ae.getSource());
        //System.out.println(startBtn);
        if (e.getSource() == startBtn) {
            System.out.println("Start pressed");
            log.info("We received an ActionEvent " + e);
            startSim();
        } else if (e.getSource() == stopBtn) {
            System.out.println("stopbtn");
            stopSim();
            log.info("We received an ActionEvent " + e);
        } else if (e.getSource() == pauseBtn) {
            pauseSim();
            log.info("We received an ActionEvent " + e);
        } else if (e.getSource() == resetBtn) {
            resetSim();
            log.info("We received an ActionEvent " + e);
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == faceCombo){
            if (e.getItem().toString() != "How many decimal") {
                Individual.decimal = Integer.parseInt(e.getItem().toString());
                bgPanel.rule = 1;
            }

        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


    /**
     * Sample Wolf application starting point
     *
     * @param args
     */
    public static void main(String[] args) {
        App wapp = new App();
        log.info("WolfApp started");
    }
}
