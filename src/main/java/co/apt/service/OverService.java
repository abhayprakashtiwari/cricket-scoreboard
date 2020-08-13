package co.apt.service;

import co.apt.entity.Match;
import co.apt.entity.Team;

public interface OverService extends ListenerService {

    void playOver(Team team);

    void play(Match match);
}
