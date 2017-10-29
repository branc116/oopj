package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;

public class FootballPlayer extends Person {
    private Integer _playingSkill;
    private PlayingPosition _playingPosition;

    public FootballPlayer(){
        super();
        setPlayingSkill(Constants.DEFAULT_PLAYING_SKILL);
        setPlayingPosition(Constants.DEFAULT_PLAYING_POSITION);
    }
    public FootballPlayer(Integer playingSkill, PlayingPosition playingPosition) {
        super();
        setPlayingSkill(playingSkill);
        setPlayingPosition(playingPosition);
    }
    public FootballPlayer(String name, String country, Integer emotion, Integer playingSkill, PlayingPosition playingPosition) {
        super(name, country, emotion);
        setPlayingSkill(Constants.DEFAULT_PLAYING_SKILL);
        setPlayingPosition(Constants.DEFAULT_PLAYING_POSITION);
        setPlayingSkill(playingSkill);
        setPlayingPosition(playingPosition);
    }

    public Integer getPlayingSkill() {
        return _playingSkill;
    }
    public void setPlayingSkill(Integer playingSkill){
        if (playingSkill == null || playingSkill > 100 || playingSkill < 0){
            System.out.printf("Error while setting plaing skill. Valid range is %d to %d. Got value %d.%n", Constants.MIN_PLAYING_SKILL, Constants.MAX_PLAYING_SKILL, playingSkill);
            _playingSkill = Constants.DEFAULT_PLAYING_SKILL;
        } else
            _playingSkill = playingSkill;
    }

    public PlayingPosition getPlayingPosition() {
        return _playingPosition;
    }
    public void setPlayingPosition(PlayingPosition playingPosition){
        if (playingPosition != null)
            _playingPosition =  playingPosition;    
    }
}