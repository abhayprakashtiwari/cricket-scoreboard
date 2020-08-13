package co.apt.service;

import co.apt.entity.Match;

public interface MatchService {

    Match init();

    void startMatch(Match match);

     void concludeMatch(Match match);

     int getPlayersLeft(Match match);

}
