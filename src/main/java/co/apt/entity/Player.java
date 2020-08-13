package co.apt.entity;

import co.apt.constant.BattingStatus;
import lombok.Data;

@Data
public class Player {

    final private String identifier;
    private int runsScored;
    private int fourCount;
    private int sixCount;
    private int ballsPlayed;
    private BattingStatus status = BattingStatus.YET_TO_PLAY;
}
