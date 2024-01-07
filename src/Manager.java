import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Manager {
    private CompetitorList competitorList;
    private CompetitorController controller;
    private CompetitorGUI gui;

    public Manager() {
        competitorList = new CompetitorList();
        this.controller = new CompetitorController(competitorList, gui);
        this.gui = new CompetitorGUI(competitorList, controller);
    }

    public void ImportDataGUI() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV and TXT files", "csv", "txt");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadCompetitors(selectedFile.getAbsolutePath());
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "You clicked cancel. The program will now exit.");
            System.exit(0);
        }
    }

    public void loadCompetitors(String filename) {
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                try {
                    int number = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String level = parts[2].trim();
                    int age = Integer.parseInt(parts[3].trim());
                    String country = parts[4].trim();
                    int[] scores = new int[parts.length - 5];
                    for (int i = 5; i < parts.length; i++) {
                        scores[i - 5] = Integer.parseInt(parts[i].trim());
                    }
                    Competitor competitor;
                    if (level.equalsIgnoreCase("AMATEUR")) {
                        competitor = new AmateurCompetitor(number, name, level, age, country, scores, "Amateur Level");
                    } else if (level.equalsIgnoreCase("PROFESSIONAL")) {
                        competitor = new ProfessionalCompetitor(number, name, level, age, country, scores,
                                "Professional Level");
                    } else {
                        throw new IllegalArgumentException("Invalid level: " + level);
                    }
                    competitorList.addCompetitor(competitor);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid data format in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error reading file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayCompetitorDetails(int competitorNumber) {
        Competitor competitor = competitorList.getCompetitor(competitorNumber);
        if (competitor != null) {
            System.out.println(competitor.getShortDetails());
        } else {
            System.out.println("Invalid competitor number");
        }
    }

    public void generateReport(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println("FULL DETAILS TABLE");
            out.print(competitorList.getFullDetailsTable());

            out.println("HIGHEST SCORING COMPETITOR");
            out.print(competitorList.getHighestScoringCompetitor().getFullDetails());

            out.println("AVERAGE SCORE");
            out.print(competitorList.getAverageScore());

            out.println("SCORE FREQUENCY");
            out.print(competitorList.getScoreFrequency());
        } catch (FileNotFoundException e) {
            System.out.println("Error writing file");
        }
    }

    public void displayCompetitorDetails() {
        String competitorNumberStr = JOptionPane.showInputDialog("Enter competitor number:");
        try {
            int competitorNumber = Integer.parseInt(competitorNumberStr);
            Competitor competitor = competitorList.getCompetitor(competitorNumber);
            if (competitor != null) {
                JOptionPane.showMessageDialog(null, competitor.getShortDetails());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid competitor number");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid competitor number");
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.ImportDataGUI();

        manager.displayCompetitorDetails();

        manager.gui.setVisible(true);

        // manager.generateReport("report.txt");
    }
}