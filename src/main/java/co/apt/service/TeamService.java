package co.apt.service;

import co.apt.entity.Match;
import co.apt.entity.Team;

public interface TeamService {

    Team getBattingOrder(Match match);

    Team getCurrentPlayingTeam(Match match);

    void changeStrike(Team team);
}
