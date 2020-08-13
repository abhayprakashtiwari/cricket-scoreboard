package co.apt.entity;

import co.apt.constant.BallType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ball {

    private BallType type;
    private int runs;

}
