import java.util.Arrays;

public class AmateurCompetitor extends Competitor {
    private String amateurLevel;

    public AmateurCompetitor(int competitorNumber, String name, String level, int age, String country, int[] scores, String amateurLevel) {
        super(competitorNumber, name, level, age, country, scores);
        this.amateurLevel = amateurLevel;
    }

    public String getAmateurLevel() {
        return amateurLevel;
    }

    @Override
    public int getOverallScore() {
        // Implementation for AmateurCompetitor
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.length;
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