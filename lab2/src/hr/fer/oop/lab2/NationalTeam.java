package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;

public class NationalTeam extends Team {
    private String _country;


    public NationalTeam() {
        super(Constants.MAX_NO_PLAYERS_NATIONAL);
        _country = Constants.DEFAULT_COUNTRY;
    }
    public NationalTeam(String name, Formation formation) {
        super(Constants.MAX_NO_PLAYERS_NATIONAL, name, formation);
        _country = Constants.DEFAULT_COUNTRY;
    }
    public NationalTeam(String name, Formation formation, String country) {
        super(Constants.MAX_NO_PLAYERS_NATIONAL, name, formation);
        if (country != null) 
            _country = country;
        else
            _country = Constants.DEFAULT_COUNTRY;
    }
    public String getCountry() {
        return _country;
    }

    @Override
    public boolean addPlayerToStartingEleven(FootballPlayer player) {
        if (player == null || player.getCountry() != _country)
            return false;
        return super.addPlayerToStartingEleven(player);
    }

    @Override
    public boolean registerPlayer(FootballPlayer player) {
        if (player == null || player.getCountry() != _country)
            return false;
        return super.registerPlayer(player);
    }


    @Override
    public double calculateRating() {
        return Constants.THIRTY_PERCENT * getRegisteredPlayers().calculateSkillSum() +  Constants.SEVENTY_PERCENT * getRegisteredPlayers().calculateEmotionSum();
    }
}