package co.apt.repository;

import co.apt.entity.Match;

public interface MatchDao {

    void saveMatch(Match match);

    Match getMatch();



}
