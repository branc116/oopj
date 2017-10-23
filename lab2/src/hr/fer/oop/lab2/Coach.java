package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;

public class Coach extends Person {
    private Integer _coachingSkill;
    private Formation _formation;

    public Coach(){
        super();
        setCoachingSkill(Constants.DEFAULT_COACHING_SKILL);
        setFormation(Constants.DEFAULT_FORMATION);
    }
    public Coach(Integer coachingSkill, Formation formation) {
        super();
        setCoachingSkill(coachingSkill);
        setFormation(formation);
    }
    public Coach(String name, String country, Integer emotion, Integer coachingSkill, Formation formation) {
        super(name, country, emotion);
        setCoachingSkill(coachingSkill);
        setFormation(formation);
    }

    public Integer getCoachingSkill() {
        return _coachingSkill;
    }
    public void setCoachingSkill(Integer coachingSkill){
        if (coachingSkill > 100 || coachingSkill < 0) {
            System.err.printf("Error while setting coaching skill. Valid range is %d to %d. Got value %d.", Constants.MIN_COACHING_SKILL, Constants.MAX_COACHING_SKILL, coachingSkill);
            coachingSkill = Constants.DEFAULT_COACHING_SKILL;
        }
        else
            _coachingSkill = coachingSkill;
    }

    public Formation getFormation() {
        return _formation;
    }
    public void setFormation(Formation formation){
        // if (coachingSkill > 100 || coachingSkill < 0)
        //     System.err.printf("Error while setting coaching skill. Valid range is %d to %d. Got value %d.", Constants.MIN_COACHING_SKILL, Constants.MAX_COACHING_SKILL, coachingSkill);
        // else
        _formation =  formation;    
    }
}