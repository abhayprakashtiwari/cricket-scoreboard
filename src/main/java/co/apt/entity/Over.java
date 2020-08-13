package co.apt.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Over {

    private List<Ball> balls = new LinkedList<>();

}
