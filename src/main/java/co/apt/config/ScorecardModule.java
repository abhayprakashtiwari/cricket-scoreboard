package co.apt.config;

import co.apt.output.OutputService;
import co.apt.output.impl.PrintServiceImpl;
import co.apt.repository.MatchDao;
import co.apt.repository.impl.MatchDaoImpl;
import co.apt.service.MatchService;
import co.apt.service.OverService;
import co.apt.service.ScoreCardService;
import co.apt.service.TeamService;
import co.apt.service.impl.MatchServiceImpl;
import co.apt.service.impl.OverServiceImpl;
import co.apt.service.impl.ScoreCardServiceImpl;
import co.apt.service.impl.TeamServiceImpl;
import com.google.inject.AbstractModule;

public class ScorecardModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MatchDao.class).to(MatchDaoImpl.class).asEagerSingleton();
        bind(MatchService.class).to(MatchServiceImpl.class);
        bind(OverService.class).to(OverServiceImpl.class);
        bind(ScoreCardService.class).to(ScoreCardServiceImpl.class);
        bind(TeamService.class).to(TeamServiceImpl.class);
        bind(ScoreCardService.class).to(ScoreCardServiceImpl.class);
        bind(OutputService.class).to(PrintServiceImpl.class);
    }
}
