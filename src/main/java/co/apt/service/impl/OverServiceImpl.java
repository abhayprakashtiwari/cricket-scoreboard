package co.apt.service.impl;

import co.apt.constant.BallType;
import co.apt.constant.BattingStatus;
import co.apt.entity.*;
import co.apt.input.ReaderUtil;
import co.apt.output.OutputService;
import co.apt.service.MatchService;
import co.apt.service.OverService;
import co.apt.service.ScoreCardService;
import co.apt.service.TeamService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OverServiceImpl implements OverService {

    @Inject private ReaderUtil readerUtil;
    @Inject private MatchService matchService;
    @Inject private TeamService teamService;
    @Inject private ScoreCardService scoreCardService;
    @Inject private OutputService outputService;

    @Override
    public void playOver(Team team) {
        int balls = 6;
        Over over = initOver(team);
        while(balls>0){
            String[] strings = readerUtil.readCustom();
            int currBallRuns = Integer.parseInt(strings[0]);
            Ball customBall = constructBall(strings[1], currBallRuns);
            customBall.setRuns(currBallRuns);
            over.getBalls().add(customBall);
            notify(customBall);
            if (team.getPlayersLeft()<0) break;
            if (!customBall.getType().equals(BallType.WIDE) && !customBall.getType().equals(BallType.NOBALL))balls-=1;
        }
        outputService.outputScore();
    }

    private Over initOver(Team team) {
        List<Over> overs = team.getOvers();
        Over over = new Over();
        if(overs==null || overs.size()==0){
            List<Player> players = team.getPlayers();
            Player strikePlayer = players.get(0);
            strikePlayer.setStatus(BattingStatus.PLAYING);
            team.setStrike(strikePlayer);
            Player nonStrikePlayer = players.get(1);
            nonStrikePlayer.setStatus(BattingStatus.PLAYING);
            team.setNonStrike(nonStrikePlayer);
            List<Over> newOvers = new ArrayList<>();
            team.setOvers(newOvers);
        } else {
            teamService.changeStrike(team);
        }
        team.getOvers().add(over);
        return over;
    }

    private Ball constructBall(String currBall, int runs) {
        return new Ball(BallType.fromString(currBall), runs);
    }

    @Override
    public void play(Match match) {
        Team currentPlayingTeam = teamService.getCurrentPlayingTeam(match);
        while(matchService.getPlayersLeft(match)>=0 && currentPlayingTeam.getCurrOVer()<match.getNoOfOvers()){
            currentPlayingTeam.setCurrOVer(currentPlayingTeam.getCurrOVer()+1);
            System.out.println("Over " + currentPlayingTeam.getCurrOVer());
            playOver(currentPlayingTeam);
        }
    }

    @Override
    public void notify(Ball ball) {
        scoreCardService.notify(ball);
    }
}
