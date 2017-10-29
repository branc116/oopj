package hr.fer.oop.lab2;

import java.util.ArrayList;
import java.util.List;

import hr.fer.oop.lab2.welcomepack.SimpleFootballPlayerCollection;

public class SimpleFootballPlayerCollectionImpl implements SimpleFootballPlayerCollection {
    private List<FootballPlayer> players;
    private Integer _maxSize;
    public SimpleFootballPlayerCollectionImpl(Integer maxSize) {
        players = new ArrayList<FootballPlayer>();
        _maxSize = maxSize;
    }
    public SimpleFootballPlayerCollectionImpl() {
        players = new ArrayList<FootballPlayer>();
    }
    @Override
    public int size() {
        return players.size();
    }

    @Override
    public boolean contains(FootballPlayer player) {
        return players.contains(player);
    }

    @Override
    public boolean add(FootballPlayer player) {
        if (!this.contains(player) && (this.size() < _maxSize)) {
            return players.add(player);
        }
        return false;
    }

    @Override
    public void clear() {
        players.clear();
    }

    @Override
    public int getMaxSize() {
        return _maxSize;
    }

    @Override
    public int calculateEmotionSum() {
        Integer sum = 0;
        for (Person person : players){
            sum += person.getEmotion();
        }
        return sum;
    }

    @Override
    public int calculateSkillSum() {
        Integer sum = 0;
        for (FootballPlayer player : players){
            sum += player.getPlayingSkill();
        }
        return sum;
    }

    @Override
    public FootballPlayer[] getPlayers() {
        return players.toArray(new FootballPlayer[0]);
    }

}