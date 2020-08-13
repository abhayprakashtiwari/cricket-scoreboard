package co.apt.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Team {

    private String identifier;
    private List<Player> players;
    private List<Over> overs;
    private Player strike;
    private Player nonStrike;
    int playersLeft;
    int currOVer = 0;
    int score = 0;

}
