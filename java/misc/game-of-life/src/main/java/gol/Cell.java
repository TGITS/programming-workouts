package gol;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private Position position;
    private boolean state;
    private Board board;

    public Cell(Position position, boolean state, Board board) {
        this.position = position;
        this.state = state;
        this.board = board;
    }

    public void evolve() {

    }

    public List<Cell> computeNeighbourhood() {
        return List.of();
    }
}
