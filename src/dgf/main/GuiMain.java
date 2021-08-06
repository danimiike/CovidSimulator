package dgf.main;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiMain extends JFrame {
    private JSlider sldrPopulation, sldrNotVaccinated, sldrOneShot, sldrTwoShot, sldrImmunity;
    private JLabel lblPopulation, lblNotVaccinated, lblOneShot, lblTwoShot, lblImmunity;
    private JLabel lblStatusPopulation, lblStatusNotVaccinated, lblStatusOneShot, lblStatusTwoShot, lblStatusImmunity;
    private JLabel lblReportPop_T, lblReportPop_N_I, lblReportPop_O_S, lblReportPop_T_S, lblReportPop_I, lblReport_Felipe;
    private JPanel pnlMain, pnlInput, pnlInputPopulation, pnlInputImmunity, pnlInputBtn, pnlReport, pnlSimulation;
    private JButton btnStartSimulation;

    private final int WINDOW_WIDTH_SIMULATION = 600;
    private final int WINDOW_HEIGHT_SIMULATION = 580;
    private final int IMG_DIAMETER = 10;
    private final Font FONT_TITLE = new Font("Times New Roman", Font.PLAIN, 20);
    private final Font sliderFont = new Font("Times New Roman", Font.PLAIN, 10);

    private int pop, popNotVaccinated, popOneShot, popTwoShot, popImmunity;

    public GuiMain() {
        super("Covid-19 Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
//        this.setLocationRelativeTo(null);

        this.setResizable(false);
        this.setLayout(new GridLayout(1, 2, 10, 0));

        // initialize panels
        this.pnlMain = new JPanel();
        this.pnlMain.setLayout(new GridLayout(2, 1, 1, 0));

        this.pnlInput = new JPanel();
        this.pnlInput.setLayout(new BorderLayout(0, 10));
        this.pnlInput.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.pnlInput.setBorder(new TitledBorder("Choose your simulation scenario"));
        this.pnlInput.setSize(new Dimension(600, 400));
        this.pnlInputPopulation = new JPanel();
        this.pnlInputPopulation.setLayout(new GridLayout(1, 3, 0, 0));
        this.pnlInputImmunity = new JPanel();
        this.pnlInputImmunity.setLayout(new GridLayout(4, 3, 0, 0));
        this.pnlInputImmunity.setBorder(new TitledBorder("Levels of Immunity - The total should be 100%"));
        this.pnlInputBtn = new JPanel();
        this.pnlReport = new JPanel();
        this.pnlReport.setLayout(new GridLayout(5, 1));
        this.pnlReport.setBorder(new TitledBorder("Report panel"));
        this.pnlReport.setSize(new Dimension(600, 400));
        this.pnlSimulation = new JPanel();
        this.pnlSimulation.setLayout(new BorderLayout());
        this.pnlSimulation.setSize(new Dimension(600, 800));
        this.pnlSimulation.setBorder(new TitledBorder("Simulation panel"));

        // Initialize labels
        this.lblReportPop_T = new JLabel("Total of population: ");
        this.lblReportPop_N_I = new JLabel("Total of non vaccinated: ");
        this.lblReportPop_O_S = new JLabel("Total of one-shot: ");
        this.lblReportPop_T_S = new JLabel("Total of two-shot: ");
        this.lblReportPop_I = new JLabel("Total of immune: ");


        this.lblPopulation = new JLabel("Size of the population");
        this.lblNotVaccinated = new JLabel("People not Vaccinated");
        this.lblOneShot = new JLabel("People with one shot");
        this.lblTwoShot = new JLabel("People with two shots");
        this.lblImmunity = new JLabel("People with natural immunity");
        this.lblStatusPopulation = new JLabel("Choose a value", JLabel.CENTER);
        this.lblStatusNotVaccinated = new JLabel("Choose a value", JLabel.CENTER);
        this.lblStatusOneShot = new JLabel("Choose a value", JLabel.CENTER);
        this.lblStatusTwoShot = new JLabel("Choose a value", JLabel.CENTER);
        this.lblStatusImmunity = new JLabel("Choose a value", JLabel.CENTER);

        // initialize sliders
        this.sldrPopulation = this.setSliderConfig(this.lblStatusPopulation, this.lblPopulation.getText(), 5000, 500, 1000);
        this.sldrNotVaccinated = this.setSliderConfig(this.lblStatusNotVaccinated, this.lblNotVaccinated.getText(), 100, 5, 10);
        this.sldrOneShot = this.setSliderConfig(this.lblStatusOneShot, this.lblOneShot.getText(), 100, 5, 10);
        this.sldrTwoShot = this.setSliderConfig(this.lblStatusTwoShot, this.lblTwoShot.getText(), 100, 5, 10);
        this.sldrImmunity = this.setSliderConfig(this.lblStatusImmunity, this.lblImmunity.getText(), 100, 5, 10);


        // Initialize buttons
        this.btnStartSimulation = new JButton("Start Simulation");
        this.btnStartSimulation.setFont(FONT_TITLE);
        this.btnStartSimulation.addActionListener(new ButtonListener());

        // populate the panels
        this.pnlInputPopulation.add(this.lblPopulation);
        this.pnlInputPopulation.add(this.sldrPopulation);
        this.pnlInputPopulation.add(this.lblStatusPopulation);
        // immunity status panel
        // not vaccinated slider
        this.pnlInputImmunity.add(this.lblNotVaccinated);
        this.pnlInputImmunity.add(this.sldrNotVaccinated);
        this.pnlInputImmunity.add(this.lblStatusNotVaccinated);
        // one shot slider
        this.pnlInputImmunity.add(this.lblOneShot);
        this.pnlInputImmunity.add(this.sldrOneShot);
        this.pnlInputImmunity.add(this.lblStatusOneShot);
        // two shot slider
        this.pnlInputImmunity.add(this.lblTwoShot);
        this.pnlInputImmunity.add(this.sldrTwoShot);
        this.pnlInputImmunity.add(this.lblStatusTwoShot);
        // natural immunity slider
        this.pnlInputImmunity.add(this.lblImmunity);
        this.pnlInputImmunity.add(this.sldrImmunity);
        this.pnlInputImmunity.add(this.lblStatusImmunity);
        // populate btn panel
        this.pnlInputBtn.add(this.btnStartSimulation);
        // populate the input panel
        this.pnlInput.add(this.pnlInputPopulation, BorderLayout.NORTH);
        this.pnlInput.add(this.pnlInputImmunity, BorderLayout.CENTER);
        this.pnlInput.add(this.pnlInputBtn, BorderLayout.SOUTH);

        //populate teh report panel
        this.pnlReport.add(this.lblReportPop_T);
        this.pnlReport.add(this.lblReportPop_N_I);
        this.pnlReport.add(this.lblReportPop_O_S);
        this.pnlReport.add(this.lblReportPop_T_S);
        this.pnlReport.add(this.lblReportPop_I);
        // populate main panel with the input panel and the report panel
        this.pnlMain.add(pnlInput);
        this.pnlMain.add(pnlReport);

        // populate the frame
        this.add(this.pnlMain);

        this.pnlReport.setVisible(true);

        this.pack();
        this.setVisible(true);
    } // end GuiMain ctor

    private JSlider setSliderConfig(JLabel statusLabel, String statusString, int max, int minorSpace, int majorSpace) {
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, max, 0);
        slider.setMinorTickSpacing(minorSpace);
        slider.setMajorTickSpacing(majorSpace);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setFont(sliderFont);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (!statusString.contains("population"))
                    statusLabel.setText(((JSlider) e.getSource()).getValue() + "%");
                else
                    statusLabel.setText("Population: " + ((JSlider) e.getSource()).getValue());
//                System.out.println(e.getSource().toString());
//                reportLabel.setText(reportLabel.getText() + ((JSlider) e.getSource()).getValue() / 100.0);
            }
        });
        return slider;
    } // end setSliderConfig method

    private void callSimulation(ArrayList<Person> arr) {
        this.pnlSimulation.add(new Simulator(arr));
        this.sldrPopulation.setEnabled(false);
        this.sldrNotVaccinated.setEnabled(false);
        this.sldrOneShot.setEnabled(false);
        this.sldrTwoShot.setEnabled(false);
        this.sldrImmunity.setEnabled(false);
        this.btnStartSimulation.setEnabled(false);

        this.add(this.pnlSimulation, BorderLayout.CENTER);
        this.pack();
    } // end callSimulation method

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Person> personList = new ArrayList<>();

            // TODO: Check the total value for the count. their sum need to be equals
            pop = sldrPopulation.getValue();
            popNotVaccinated = sldrNotVaccinated.getValue() * pop / 100;
            popOneShot = sldrOneShot.getValue() * pop / 100;
            popTwoShot = sldrTwoShot.getValue() * pop / 100;
            popImmunity = sldrImmunity.getValue() * pop / 100;
            int countPop = pop;


            lblReportPop_T.setText(lblReportPop_T.getText() + pop);
            lblReportPop_N_I.setText(lblReportPop_N_I.getText() + popNotVaccinated);
            lblReportPop_O_S.setText(lblReportPop_O_S.getText() + popOneShot);
            lblReportPop_T_S.setText(lblReportPop_T_S.getText() + popTwoShot);
            lblReportPop_I.setText(lblReportPop_I.getText() + popImmunity);

//            int width = pnlSimulation.getWidth();
//            int height = pnlSimulation.getHeight();

            Person infected = new Person(true, true, ImmunityStatus.Status.NO_IMMUNITY,
                    new Ball(IMG_DIAMETER, Color.RED, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION));
            personList.add(infected);

            for (int i = 0; i < pop; i++) {
                if (popNotVaccinated-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_IMMUNITY, ballPerson);
                    personList.add(person);
                } else if (popOneShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.CYAN, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popTwoShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.YELLOW, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popImmunity-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.GREEN, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (countPop-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH_SIMULATION, WINDOW_HEIGHT_SIMULATION);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_IMMUNITY, ballPerson);
                    personList.add(person);
                } // end
                countPop--;
            } // end for loop

            callSimulation(personList);
        } // end actionPerformed
    } // end inner listener class

    private class ReportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            lblReportPop_T.setText((lblStatusPopulation.getText()));
        }
    }

    public static void main(String[] args) {
        new GuiMain();
    }
} // end GuiMain class

