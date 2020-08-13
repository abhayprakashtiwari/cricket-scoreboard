package co.apt.service.impl;

import co.apt.constant.Inning;
import co.apt.entity.Match;
import co.apt.entity.Player;
import co.apt.entity.Team;
import co.apt.input.ReaderUtil;
import co.apt.service.OverService;
import co.apt.service.TeamService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    @Inject private ReaderUtil reader;
    @Inject private OverService overService;

    @Override
    public Team getBattingOrder(Match match) {
        String currentTeamName = getCurrentTeamName(match);
        System.out.println("Batting Order for "  + currentTeamName);
        int playerCount = match.getNoOfPlayers();
        List<Player> players = new ArrayList<>();
        while (playerCount>0){
            String playerId = reader.readString();
            Player player = new Player(playerId);
            players.add(player);
            playerCount-=1;
        }
        Team team = Team.builder()
                .players(players)
                .playersLeft(players.size()-2)
                .identifier(currentTeamName)
                .build();
        return team;
    }

    @Override
    public Team getCurrentPlayingTeam(Match match) {
        return match.getInning().equals(Inning.FIRST) ? match.getTeam1() : match.getTeam2();
    }

    @Override
    public void changeStrike(Team team) {
        Player nonStrike = team.getNonStrike();
        Player strike = team.getStrike();
        team.setNonStrike(strike);
        team.setStrike(nonStrike);
    }

    private String getCurrentTeamName(Match match) {
        return match.getInning().equals(Inning.FIRST) ? "Team 1" : "Team 2";
    }
}
