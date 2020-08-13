package co.apt.repository.impl;

import co.apt.entity.Match;
import co.apt.repository.MatchDao;

public class MatchDaoImpl implements MatchDao {

    private Match match;

    @Override
    public void saveMatch(Match match) {
        this.match = match;
    }

    @Override
    public Match getMatch() {
        return match;
    }


}
