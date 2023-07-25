package parameterized;

public enum WebTechnology {
    EMBERJS("Ember.js"),
    KNOCKOUTJS("KnockoutJS"),
    DOJO("Dojo");
    private String technologyName;

    WebTechnology(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getTechnologyName() {
        return technologyName;
    }
}
