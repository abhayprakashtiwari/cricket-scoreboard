package co.apt.service.impl;

import co.apt.constant.BallType;
import co.apt.constant.BattingStatus;
import co.apt.entity.*;
import co.apt.repository.MatchDao;
import co.apt.service.ScoreCardService;
import co.apt.service.TeamService;

import javax.inject.Inject;
import java.util.List;

public class ScoreCardServiceImpl implements ScoreCardService {

    @Inject MatchDao matchDao;
    @Inject TeamService teamService;

    @Override
    public void notify(Ball ball) {
        modifyScore(ball);
    }

    @Override
    public void modifyScore(Ball ball) {
        Match match = matchDao.getMatch();
        Team currentPlayingTeam = teamService.getCurrentPlayingTeam(match);
        Player currPlayer = currentPlayingTeam.getStrike();


        handleRuns( ball.getRuns(),currentPlayingTeam, currPlayer);
        handleBallType(ball.getType(), currentPlayingTeam, currPlayer);
    }

    private void handleBallType(BallType type, Team team, Player player) {
        switch (type){
            case WICKET:
                incrementBallsPlayed(player);
                player.setStatus(BattingStatus.OUT);
                replaceStrikerWithNewPlayer(team);
                break;
            case WIDE:
                increaseScore(team,1);
                break;
            case NOBALL:
                increaseScore(team,1);
                increasePlayerScore(player,1);
                break;
            default:
                break;
        }
    }

    private void handleRuns(int runs, Team team, Player player) {
        if (runs%2==0){
            handleEvenRunBall(team, player,runs);
        } else {
            handleOddRunBall(team, player, runs);
        }
    }



    private void handleEvenRunBall(Team currentPlayingTeam, Player currPlayer, int i) {
        increaseScore(currentPlayingTeam, i);
        increasePlayerScore(currPlayer, i);
        incrementBallsPlayed(currPlayer);
    }

    private void handleOddRunBall(Team currentPlayingTeam, Player currPlayer, int i) {
        handleEvenRunBall(currentPlayingTeam, currPlayer, i);
        changeStrike(currentPlayingTeam);
    }

    private void replaceStrikerWithNewPlayer(Team currentPlayingTeam) {
        List<Player> players = currentPlayingTeam.getPlayers();
        int playersLeft = currentPlayingTeam.getPlayersLeft();
        currentPlayingTeam.setPlayersLeft(playersLeft-1);
        if (playersLeft==0) return;
        Player newStriker = players.get(players.size() - playersLeft);
        newStriker.setStatus(BattingStatus.PLAYING);
        currentPlayingTeam.setStrike(newStriker);

    }

    private void changeStrike(Team currentPlayingTeam) {
        Player nonStrike = currentPlayingTeam.getNonStrike();
        Player strike = currentPlayingTeam.getStrike();
        currentPlayingTeam.setStrike(nonStrike);
        currentPlayingTeam.setNonStrike(strike);
    }


    private void increaseScore(Team currentPlayingTeam, int runs) {
        currentPlayingTeam.setScore(currentPlayingTeam.getScore()+runs);
    }

    private void incrementBallsPlayed(Player currPlayer) {
        int ballsPlayed = currPlayer.getBallsPlayed();
        currPlayer.setBallsPlayed(ballsPlayed+1);
    }

    private void increasePlayerScore(Player currPlayer, int runs){
        currPlayer.setRunsScored(currPlayer.getRunsScored()+runs);
        switch (runs){
            case 4:
                currPlayer.setFourCount(currPlayer.getFourCount()+1);
                break;
            case 6:
                currPlayer.setSixCount(currPlayer.getSixCount()+1);
                break;
        }
    }

}
