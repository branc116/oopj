package hr.fer.oop.lab2;

import hr.fer.oop.lab2.welcomepack.Formation;
import hr.fer.oop.lab2.welcomepack.ManageableTeam;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;

public class Manager extends Coach implements hr.fer.oop.lab2.welcomepack.Manager {

    private ManageableTeam _team;
    public Manager() {
        super();
    }

    @Override
    public void pickStartingEleven() {
        FootballPlayer[] players = _team.getRegisteredPlayers().getPlayers();
        for (Integer i = 0;i<players.length;i++) {
            for (Integer j = i + 1;j < players.length;j++) {
                if (players[i].getPlayingSkill() < players[j].getPlayingSkill()) {
                    FootballPlayer temp = players[i];
                    players[i] = players[j];
                    players[j] = temp;
                }
            }
        }
        Formation form = _team.getFormation();
        int noDF = form.getNoDF();
        int noMF = form.getNoMF();
        int noFW = form.getNoFW();
        int noGK = 1;
        _team.clearStartingEleven();
        for (int i = 0;i<players.length;i++) {
            PlayingPosition poz = players[i].getPlayingPosition();
            if (poz == PlayingPosition.GK && noGK > 0) {
                _team.addPlayerToStartingEleven(players[i]);
                noGK--;
            } else if (poz == PlayingPosition.DF && noDF > 0) {
                _team.addPlayerToStartingEleven(players[i]);
                noDF--;
            } else if (poz == PlayingPosition.FW && noFW > 0) {
                _team.addPlayerToStartingEleven(players[i]);
                noFW--;
            } else if (poz == PlayingPosition.MF && noMF > 0) {
                _team.addPlayerToStartingEleven(players[i]);
                noMF--;
            }
        }
    }

    @Override
    public void forceMyFormation() {
        _team.setFormation(super.getFormation());
    }

    @Override
    public void setManagingTeam(ManageableTeam team) {
        if (team != null)
            _team = team;
    }

}