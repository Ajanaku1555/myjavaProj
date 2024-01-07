import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CompetitorController {

    private CompetitorList model;
    private CompetitorGUI view;

    public CompetitorController(CompetitorList model, CompetitorGUI view) {
        this.model = model;
        this.view = view;
    }

    public void setView(CompetitorGUI view) {
        this.view = view;
    }
    
    public String generateReport() {
        return model.generateReport();
    }

    public void updateView(String data) {
        view.updateDisplay(data);
    }

    public String viewDetails(int competitorNumber) {
        Competitor competitor = model.getCompetitorByNumber(competitorNumber);
        if (competitor != null) {
            return competitor.getFullDetails();
        } else {
            return "Competitor not found.";
        }
    }

    public String editCompetitor(int competitorNumber, String newName, String newLevel, int newAge, String newCountry) {
        Competitor competitor = model.getCompetitorByNumber(competitorNumber);
        if (competitor != null) {
            competitor.setName(newName);
            competitor.setLevel(newLevel);
            competitor.setAge(newAge);
            competitor.setCountry(newCountry);
            return "Competitor details updated successfully:\n" + competitor.getFullDetails();
        } else {
            return "Invalid competitor number.";
        }
    }
    

    public void removeCompetitor(int competitorNumber) {
        model.removeCompetitorByNumber(competitorNumber);
    }

    public void writeReportToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(model.generateReport());
            writer.println(model.getScoreFrequency());
            Competitor highestScoringCompetitor = model.getHighestScoringCompetitor();
            if (highestScoringCompetitor != null) {
                writer.println("Highest Scoring Competitor is " + highestScoringCompetitor.getFullDetails());
            } else {
                writer.println("No competitors in the list.");
            }
            writer.println(model.calculateAverageOverallScoreReport());

            // Print full details for all competitors
            for (Competitor competitor : model.getCompetitors()) {
                writer.println(competitor.getFullDetails());
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
