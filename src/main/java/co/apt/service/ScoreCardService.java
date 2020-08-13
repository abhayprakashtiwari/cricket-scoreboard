package co.apt.service;

import co.apt.entity.Ball;

public interface ScoreCardService extends ListenerService {

    void modifyScore(Ball ball);

}
