

public abstract class Competitor {
    protected int competitorNumber;
    protected String name;
    protected String level;
    protected int age;
    protected String country;
    protected int[] scores;

    public Competitor(int competitorNumber, String name, String level, int age, String country, int[] scores) {
        this.competitorNumber = competitorNumber;
        this.name = name;
        this.level = level;
        this.age = age;
        this.country = country;
        this.scores = scores;
    }
    

    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int[] getScores() {
        return scores;
    }

    public int[] getScoreArray() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public abstract int getOverallScore();

    public String getFullDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Competitor number ").append(competitorNumber)
                .append(", name ").append(name)
                .append(", country ").append(country)
                .append(".\n")
                .append(name).append(" is a ").append(level)
                .append(" aged ").append(age)
                .append(" and received these scores: ");
        for (int score : scores) {
            details.append(score).append(",");
        }
        details.deleteCharAt(details.length() - 1);
        details.append(".\n")
                .append("This gives him an overall score of ").append(getOverallScore()).append(".");
        return details.toString();
    }

    public String getShortDetails() {
        StringBuilder details = new StringBuilder();
        details.append("CN ").append(competitorNumber)
                .append(" (").append(getInitials()).append(") has overall score ").append(getOverallScore());
        return details.toString();
    }

    private String getInitials() {
        String[] names = name.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String n : names) {
            initials.append(n.charAt(0));
        }
        return initials.toString();
    }

    // public static void main(String[] args) {
    //     // Create some scores
    //     int[] scores1 = {5, 4, 3, 2, 1};
    //     int[] scores2 = {2, 3, 4, 5, 4};
    //     int[] scores3 = {3, 2, 5, 4, 3};

    //     // Create some competitors
    //     Competitor competitor1 = new Competitor(100, "John Doe", Level.PROFESSIONAL, 20, "USA", scores1);
    //     Competitor competitor2 = new Competitor(101, "Jane Doe", Level.PROFESSIONAL, 25, "UK", scores2);
    //     Competitor competitor3 = new Competitor(102, "Jim Doe", Level.AMATEUR, 30, "Canada", scores3);

    //     // Test the methods
    //     System.out.println(competitor1.getFullDetails());
    //     System.out.println(competitor1.getShortDetails());

    //     System.out.println(competitor2.getFullDetails());
    //     System.out.println(competitor2.getShortDetails());

    //     System.out.println(competitor3.getFullDetails());
    //     System.out.println(competitor3.getShortDetails());
    // }

}
