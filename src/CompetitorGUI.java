import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CompetitorGUI extends JFrame {

    private CompetitorList model;
    private CompetitorController controller;

    private JTextArea displayArea;
    private JTextField competitorNumberField;
    private JButton addCompetitorButton;
    private JButton listCompetitorsButton;
    private JButton viewDetailsButton;
    private JButton editDetailsButton;
    private JButton removeCompetitorButton;
    private JButton closeProgramButton;

    public CompetitorGUI(CompetitorList model, CompetitorController controller) {
        this.model = model;
        this.controller = controller;

        initializeComponents();
        buildGUI();
    }

    private void initializeComponents() {
        displayArea = new JTextArea(20, 50);
        competitorNumberField = new JTextField(10);
        listCompetitorsButton = new JButton("List Competitors");
        addCompetitorButton = new JButton("Add Competitor");
        viewDetailsButton = new JButton("View Details");
        editDetailsButton = new JButton("Edit Details");
        removeCompetitorButton = new JButton("Remove Competitor");
        closeProgramButton = new JButton("Close Program");

        listCompetitorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllCompetitors();
            }
        });

        addCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competitorNumberText = JOptionPane.showInputDialog("Enter competitor number:");
                if (competitorNumberText == null) {
                    return; // User cancelled, stop addition
                }
                int competitorNumber = Integer.parseInt(competitorNumberText);

                // Check if competitor number already exists
                if (model.competitorNumberExists(competitorNumber)) {
                    JOptionPane.showMessageDialog(null, "Competitor number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = JOptionPane.showInputDialog("Enter name:");
                if (name == null) {
                    return; // User cancelled, stop addition
                }
                // Make level selection a dropdown
                Object[] possibleLevels = { "AMATEUR", "PROFESSIONAL" };
                String level = (String) JOptionPane.showInputDialog(null, "Select level:", "Level Selection",
                        JOptionPane.QUESTION_MESSAGE, null, possibleLevels, possibleLevels[0]);
                if (level == null) {
                    return; // User cancelled, stop addition
                }

                String ageText = JOptionPane.showInputDialog("Enter age:");
                if (ageText == null) {
                    return; // User cancelled, stop addition
                }
                int age = Integer.parseInt(ageText);

                String country = JOptionPane.showInputDialog("Enter country:");
                if (country == null) {
                    return; // User cancelled, stop addition
                }

                String scoresText = JOptionPane.showInputDialog("Enter scores (comma separated):");
                if (scoresText == null) {
                    return; // User cancelled, stop addition
                }
                int[] scores = Arrays.stream(scoresText.split(",")).mapToInt(Integer::parseInt).toArray();

                Competitor newCompetitor;
                if (level.equalsIgnoreCase("Amateur")) {
                    newCompetitor = new AmateurCompetitor(competitorNumber, name, level, age, country, scores, "");
                } else {
                    newCompetitor = new ProfessionalCompetitor(competitorNumber, name, level, age, country, scores, "");
                }

                model.addCompetitor(newCompetitor);
                displayArea.setText("Competitor added successfully.");
            }
        });

        editDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competitorNumberText = competitorNumberField.getText();
                if (competitorNumberText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a competitor number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int competitorNumber = Integer.parseInt(competitorNumberText);
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    if (newName == null) {
                        return; // User cancelled, stop editing
                    }
                    String newLevel = JOptionPane.showInputDialog("Enter new level:");
                    if (newLevel == null) {
                        return; // User cancelled, stop editing
                    }
                    String newAgeText = JOptionPane.showInputDialog("Enter new age:");
                    if (newAgeText == null) {
                        return; // User cancelled, stop editing
                    }
                    int newAge = Integer.parseInt(newAgeText);
                    String newCountry = JOptionPane.showInputDialog("Enter new country:");
                    if (newCountry == null) {
                        return; // User cancelled, stop editing
                    }
                    String message = controller.editCompetitor(competitorNumber, newName, newLevel, newAge,
                            newCountry);
                    displayArea.setText(message);
                }
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competitorNumberText = competitorNumberField.getText();
                if (competitorNumberText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a competitor number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int competitorNumber = Integer.parseInt(competitorNumberText);
                    displayArea.setText(controller.viewDetails(competitorNumber));
                }
            }
        });

        removeCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competitorNumberText = competitorNumberField.getText();
                if (competitorNumberText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a competitor number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int competitorNumber = Integer.parseInt(competitorNumberText);
                    controller.removeCompetitor(competitorNumber);
                    displayArea.setText("Competitor removed successfully.");
                }
            }
        });

        closeProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.writeReportToFile("report.txt");
                System.exit(0);
            }
        });
    }

    private void listAllCompetitors() {
        StringBuilder competitors = new StringBuilder();
        for (Competitor competitor : model.getCompetitors()) {
            competitors.append(competitor.toString()).append("\n");
        }
        displayArea.setText(competitors.toString());
    }

    private void buildGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Competitor Number:"));
        controlPanel.add(listCompetitorsButton);
        controlPanel.add(addCompetitorButton);
        controlPanel.add(competitorNumberField);
        controlPanel.add(viewDetailsButton);
        controlPanel.add(editDetailsButton);
        controlPanel.add(removeCompetitorButton);
        controlPanel.add(closeProgramButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setTitle("Competitor Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateDisplay(String data) {
        displayArea.setText(data);
    }
}
