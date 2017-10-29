package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Constants;
import hr.fer.oop.lab2.welcomepack.Formation;

public class ClubTeam extends Team {
    private Integer _reputation;

    public ClubTeam() {
        super(Constants.MAX_NO_PLAYERS_NATIONAL);
        _reputation = Constants.DEFAULT_REPUTATION;
    }
    public ClubTeam(String name, Formation formation) {
        super(Constants.MAX_NO_PLAYERS_NATIONAL, name, formation);
        _reputation = Constants.DEFAULT_REPUTATION;
    }
    public ClubTeam(String name, Formation formation, Integer reputation) {
        super(Constants.MAX_NO_PLAYERS_NATIONAL, name, formation);
        _reputation = Constants.DEFAULT_REPUTATION;
        this.setReputation(reputation);
    }

    public Integer getReputation() {
        return _reputation;
    }

    public void setReputation(Integer reputation) {
        if (reputation == null || reputation < Constants.MIN_REPUTATION || reputation > Constants.MAX_REPUTATION) {
            System.out.println("Reputation value is invalid");

            return;
        }
        this._reputation = reputation;
    }

    @Override
    public boolean addPlayerToStartingEleven(FootballPlayer player) {
        if (player == null || player.getPlayingSkill() < this.calculateRating())
            return false;
        return super.addPlayerToStartingEleven(player);
    }

    @Override
    public boolean registerPlayer(FootballPlayer player) {
        if (player == null || player.getPlayingSkill() < this.calculateRating()/getRegisteredPlayers().size())
            return false;
        return super.registerPlayer(player);
    }

    @Override
    public double calculateRating() {
        return Constants.SEVENTY_PERCENT * getRegisteredPlayers().calculateSkillSum() + Constants.THIRTY_PERCENT * getRegisteredPlayers().calculateEmotionSum();
    }
}