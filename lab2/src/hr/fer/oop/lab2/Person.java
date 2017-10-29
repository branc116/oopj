package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;

public abstract class Person {
    private String _name;
    private String _country;
    private Integer _emotion;

    public Person(String name, String country, Integer emotion) {
        this();
        setName(name);
        setCountry(country);
        setEmotion(emotion);
    }
    public Person() {
        _name = Constants.DEFAULT_PLAYER_NAME;
        _country = Constants.DEFAULT_COUNTRY;
        _emotion = Constants.DEFAULT_EMOTION;
    }

    public Integer getEmotion() {
        return _emotion;
    }
    public void setEmotion(Integer emotion) {
        if (emotion > 100 || emotion < 0)
        {
            System.out.printf("Error while setting emotion. Valid range is %d to %d. Got value %d.%n", Constants.MIN_EMOTION, Constants.MAX_EMOTION, emotion);
            // emotion = Constants.DEFAULT_EMOTION;
        }
        else
            _emotion = emotion;
    }
    public String getName() {
        return _name;
    }
    private void setName(String name) {
        if (name != null) {
            _name = name;
        }
    }
    public String getCountry() {
        return _country;
    }
    private void setCountry(String country) {
        if (country != null) {
            _country = country;
        }
    }
    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null)
            return false;
        if (arg0.getClass() == this.getClass()) {
            return ((Person)(arg0)).getName() == this.getName() &&
                   ((Person)(arg0)).getCountry() == this.getCountry();
        }
        return false;
    }
}