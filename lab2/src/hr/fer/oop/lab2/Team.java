package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;
import hr.fer.oop.lab2.welcomepack.ManageableTeam;

public abstract class Team implements ManageableTeam {
    private String _name;
    private SimpleFootballPlayerCollectionImpl _registeredPlayers;
    private SimpleFootballPlayerCollectionImpl _startingEleven;
    private Formation _formation;

    protected Team(int maxRegistered) {
        _name = Constants.DEFAULT_TEAM_NAME;
        _registeredPlayers = new SimpleFootballPlayerCollectionImpl(maxRegistered);
        _startingEleven = new SimpleFootballPlayerCollectionImpl(Constants.STARTING_ELEVEN_SIZE);
        _formation = Constants.DEFAULT_FORMATION;
    }
    protected Team(int maxRegistered, String name, Formation formation) {
        this(maxRegistered);
        if (name != null)
            _name = name;
        if (formation != null)
            _formation = formation;
    }

    public SimpleFootballPlayerCollectionImpl getRegisteredPlayers() {
        return _registeredPlayers;
    }
    public String getName() {
        return _name;
    }
    public SimpleFootballPlayerCollectionImpl getStartingEleven() {
        return _startingEleven;
    }
    public Formation getFormation() {
        return _formation;
    }

    @Override
    public boolean addPlayerToStartingEleven(FootballPlayer player) {
        return _startingEleven.add(player);
    }
    @Override
    public boolean isPlayerRegistered(FootballPlayer player) {
        return _registeredPlayers.contains(player);
    }
    @Override
    public boolean registerPlayer(FootballPlayer player) {
        return _registeredPlayers.add(player);
    }
    @Override
    public void setFormation(Formation formation) {
        if (formation != null) {
            _formation = formation;
        } else {
            System.out.printf("can't set formation to null");
        }
    }
    @Override
    public void clearStartingEleven() {
        _startingEleven.clear();
    }
}