import java.util.*;
import java.util.stream.Collectors;

class CompetitorList {
    private ArrayList<Competitor> competitors;
    
    public CompetitorList() {
        competitors = new ArrayList<>();
    }
    
    public void addCompetitor(Competitor c) {
        competitors.add(c);
    }
    
    public Competitor getCompetitor(int number) {
        for (Competitor c : competitors) {
            if (c.getCompetitorNumber() == number) {
                return c;
            }
        }
        return null;
    }
    
    public String getFullDetailsTable() {
        StringBuilder sb = new StringBuilder();
        for (Competitor c : competitors) {
            sb.append(c.getFullDetails()).append("\n");
        }
        return sb.toString();
    }
    
    public Competitor getHighestScoringCompetitor() {
        return competitors.stream().max(Comparator.comparingInt(Competitor::getOverallScore)).orElse(null);
    }

    public double getAverageScore() {
        return competitors.stream().mapToInt(Competitor::getOverallScore).average().orElse(0.0);
    }

    public Map<Integer, Long> getScoreFrequency() {
        return competitors.stream()
            .flatMapToInt(c -> Arrays.stream(c.getScores()))
            .boxed()
            .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    }

    public boolean competitorNumberExists(int competitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public void removeCompetitor (Competitor competitor) {
        competitors.remove(competitor);
    }

    public void removeCompetitorByNumber(int number) {
        Competitor competitorToRemove = getCompetitorByNumber(number);
        if (competitorToRemove != null) {
            competitors.remove(competitorToRemove);
        }
    }

    public Competitor getCompetitorByNumber(int number) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber() == number) {
                return competitor;
            }
        }
        return null;
    }

    public String calculateAverageOverallScoreReport() {
        if (competitors.isEmpty()) {
            return "Average Overall Score: N/A\n";
        }

        double totalScore = 0;
        for (Competitor competitor : competitors) {
            totalScore += competitor.getOverallScore();
        }
        double averageScore = totalScore / competitors.size();

        StringBuilder report = new StringBuilder();
        report.append("\n-----------------------------------\n");

        // Add average overall score
        report.append(String.format("Average Overall Score: %.2f%n", averageScore));

        return report.toString();
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Competitor Report\n");
        report.append("=================\n\n");

        // Header
        report.append(String.format("%-20s %-15s %-10s %-10s %-10s %s%n", "Name", "Level", "Age", "Country", "Overall Score", "Scores"));

        // Competitor details
        for (Competitor competitor : competitors) {
            report.append(String.format("%-20s %-15s %-10d %-10s %-10d %s%n", 
                competitor.getName(), 
                competitor.getLevel(), 
                competitor.getAge(), 
                competitor.getCountry(), 
                competitor.getOverallScore(), 
                Arrays.toString(competitor.getScores())
            ));
        }

        // Footer
        report.append("\n=================\n");
        report.append("End of Report\n");

        return report.toString();
    }

}