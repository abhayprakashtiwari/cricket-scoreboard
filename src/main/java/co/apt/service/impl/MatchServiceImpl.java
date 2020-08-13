package co.apt.service.impl;

import co.apt.constant.Inning;
import co.apt.entity.Match;
import co.apt.entity.Team;
import co.apt.input.ReaderUtil;
import co.apt.output.OutputService;
import co.apt.repository.MatchDao;
import co.apt.service.MatchService;
import co.apt.service.OverService;
import co.apt.service.TeamService;

import javax.inject.Inject;

public class MatchServiceImpl implements MatchService {

    @Inject private ReaderUtil reader;
    @Inject private TeamService teamService;
    @Inject private OverService overService;
    @Inject private MatchDao matchDao;
    @Inject private OutputService outputService;


    @Override
    public Match init() {
        System.out.println("No. of players for each team: ");
        int noOfPlayers = reader.readInt();
        System.out.println("No. of overs: ");
        int noOfOvers = reader.readInt();
        Match match = Match.builder().
                noOfOvers(noOfOvers).
                noOfPlayers(noOfPlayers).
                build();
        matchDao.saveMatch(match);
        return match;
    }

    @Override
    public void startMatch(Match match) {
        match.setInning(Inning.FIRST);
        Team team1 = teamService.getBattingOrder(match);
        match.setTeam1(team1);
        overService.play(match);
        match.setInning(Inning.SECOND);
        Team team2 = teamService.getBattingOrder(match);
        match.setTeam2(team2);
        overService.play(match);
    }


    @Override
    public void concludeMatch(Match match) {
        int team1Score = match.getTeam1().getScore();
        int team2Score = match.getTeam2().getScore();
        match.setWinningTeamName(team1Score>team2Score ? match.getTeam1().getIdentifier() : match.getTeam2().getIdentifier());
        outputService.outputVerdict();
    }

    @Override
    public int getPlayersLeft(Match match) {
        return match.getInning().equals(Inning.FIRST) ?
                match.getTeam1().getPlayersLeft() :
                match.getTeam2().getPlayersLeft();
    }
}
