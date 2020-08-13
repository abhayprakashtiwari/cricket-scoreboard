package co.apt.entity;


import co.apt.constant.Inning;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Match {

    private int noOfPlayers;
    private int noOfOvers;
    private Team team1;
    private Team team2;
    private Inning inning;
    private String winningTeamName;

}
