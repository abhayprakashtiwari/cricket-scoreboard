package co.apt.output.impl;

import co.apt.constant.BattingStatus;
import co.apt.entity.Match;
import co.apt.entity.Over;
import co.apt.entity.Player;
import co.apt.entity.Team;
import co.apt.output.OutputService;
import co.apt.repository.MatchDao;
import co.apt.service.TeamService;

import javax.inject.Inject;
import java.util.List;

public class PrintServiceImpl implements OutputService {

    @Inject private TeamService teamService;
    @Inject private MatchDao matchDao;

    @Override
    public void outputScore() {
        Match match = matchDao.getMatch();
        Team team = teamService.getCurrentPlayingTeam(match);
        System.out.println("Scorecard for " + team.getIdentifier());
        System.out.println("Player Name\t Score \t 4s \t 6s \t Balls");
        printPlayersScore(team);
        System.out.println("Total : " + team.getScore() + "/" + (match.getNoOfPlayers()-team.getPlayersLeft()-2));
        System.out.printf("Overs : %s \n", getCurrentOvers(team));
        System.out.println();
    }

    private String getCurrentOvers(Team team){
        List<Over> overs = team.getOvers();
        Over lastOver = overs.get(overs.size()-1);
        StringBuilder sb = new StringBuilder();
        int lastOverBalls = lastOver.getBalls().size();
        if(lastOverBalls <6){
            sb.append(team.getCurrOVer()-1).append(".").append(lastOverBalls);
        } else {
            sb.append(team.getCurrOVer());
        }
        return sb.toString();
    }

    @Override
    public void outputVerdict() {
        Match match = matchDao.getMatch();
        String winningTeamName = match.getWinningTeamName();
        System.out.printf("%s won by %d runs", winningTeamName, (Math.abs(match.getTeam1().getScore() - match.getTeam2().getScore())));
    }

    private void printPlayersScore(Team team) {
        List<Player> players = team.getPlayers();
        for (Player player: players) {
            StringBuilder sb = new StringBuilder(player.getIdentifier());
            if(player.getStatus().equals(BattingStatus.PLAYING)) sb.append("*");
            int runsScored = player.getRunsScored();
            int fourCount = player.getFourCount();
            int sixCount = player.getSixCount();
            int ballsPlayed = player.getBallsPlayed();
            System.out.printf("%s\t%d\t%d\t%d\t%d\n", sb.toString(),runsScored,fourCount,sixCount,ballsPlayed);
        }
    }
}
