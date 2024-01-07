import java.util.Arrays;

public class ProfessionalCompetitor extends Competitor {
    private String professionalLevel;

    public ProfessionalCompetitor(int competitorNumber, String name, String level, int age, String country, int[] scores, String professionalLevel) {
        super(competitorNumber, name, level, age, country, scores);
        this.professionalLevel = professionalLevel;
    }

    public String getProfessionalLevel() {
        return professionalLevel;
    }

    @Override
    public int getOverallScore() {
        // Implementation for ProfessionalCompetitor
        int maxScore = 0;
        for (int score : scores) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    @Override
    public String toString() {
        return "AmateurCompetitor{" +
               "competitorNumber=" + competitorNumber +
               ", name='" + name + '\'' +
               ", level='" + level + '\'' +
               ", age=" + age +
               ", country='" + country + '\'' +
               ", scores=" + Arrays.toString(scores) +
               '}';
    }
}