package co.apt;

import co.apt.config.ScorecardModule;
import co.apt.entity.Match;
import co.apt.service.MatchService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class App
{

    public static void main( String[] args )
    {
        System.out.println( "Cricket scorecard App" );
        Injector injector = Guice.createInjector(new ScorecardModule());
        MatchService matchService = injector.getInstance(MatchService.class);
        Match match = matchService.init();
        matchService.startMatch(match);
        matchService.concludeMatch(match);
    }
}
