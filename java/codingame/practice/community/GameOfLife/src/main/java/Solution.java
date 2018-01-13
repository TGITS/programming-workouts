import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

enum State {
    DEAD(0, false),
    LIVE(1, true);

    private int encoding;
    private boolean live;

    State(int encoding, boolean live) {
        this.encoding = encoding;
        this.live = live;
    }

    public String encoding() {
        return (Integer.toString(this.encoding));
    }

    public boolean isLive() {
        return this.live;
    }

    private static State[] states = new State[2];

    static {
        states[0] = DEAD;
        states[1] = LIVE;
    }

    public static State stateByEncoding(int encoding) {
        if (encoding != 0 && encoding != 1) {
            throw new IllegalArgumentException("The provided value of " + encoding + " is not possible");
        }
        return states[encoding];
    }
}

class Cell {
    private int x, y;
    private State state;
    private int liveNeighbors = -1;

    public Cell(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = State.stateByEncoding(state);
    }

    public State getState() {
        return state;
    }

    public void updateLiveNeighbors(int liveNeighbors) {
        this.liveNeighbors = liveNeighbors;
    }

    /*
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * 2. Any live cell with two or three live neighbors lives on to the next generation.
     * 3. Any live cell with more than three live neighbors dies, as if by over-population..
     * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * */
    public void updateState() {
        //System.err.println("State " + this.state.toString() + " - Number of live neighbors : " + this.liveNeighbors);
        if (state.isLive() && this.liveNeighbors < 2) {
            this.state = State.DEAD;
        } else if (state.isLive() && (this.liveNeighbors == 2 || this.liveNeighbors == 3)) {
            this.state = State.LIVE;
        } else if (state.isLive() && this.liveNeighbors > 3) {
            this.state = State.DEAD;
        } else if (!state.isLive() && this.liveNeighbors == 3) {
            this.state = State.LIVE;
        }
    }
}

class Board {
    private Cell[][] grid;
    private int width;
    private int height;

    public Board(int w, int h) {
        this.grid = new Cell[w][h];
        this.width = w;
        this.height = h;
    }

    public void addCell(Cell cell, int x, int y) {
        this.grid[x][y] = cell;
    }

    public void update() {
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                this.updateNeighborsCount(w, h);
            }
        }
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                this.updateCellsState(w, h);
            }
        }
    }

    private void updateNeighborsCount(int x, int y) {
        int count = 0;

        if (y > 0) {
            count += (grid[x][y - 1].getState().isLive() ? 1 : 0);
        }

        if (y < height - 1) {
            count += (grid[x][y + 1].getState().isLive() ? 1 : 0);
        }

        if (x > 0) {
            count += (grid[x - 1][y].getState().isLive() ? 1 : 0);
            if (y > 0) {
                count += (grid[x - 1][y - 1].getState().isLive() ? 1 : 0);
            }
            if (y < height - 1) {
                count += (grid[x - 1][y + 1].getState().isLive() ? 1 : 0);
            }
        }

        if (x < width - 1) {
            count += (grid[x + 1][y].getState().isLive() ? 1 : 0);
            if (y > 0) {
                count += (grid[x + 1][y - 1].getState().isLive() ? 1 : 0);
            }
            if (y < height - 1) {
                count += (grid[x + 1][y + 1].getState().isLive() ? 1 : 0);
            }
        }

        grid[x][y].updateLiveNeighbors(count);
    }

    private void updateCellsState(int x, int y) {
        //System.err.println("For cell at : " + x + "," + y);
        grid[x][y].updateState();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                sb.append(grid[w][h].getState().encoding());
            }
            sb.append("\n");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        Board board = new Board(width, height);
        System.err.println("Initial grid");
        for (int h = 0; h < height; h++) {
            String line = in.next();
            System.err.println(line);
            for (int w = 0; w < width; w++) {
                board.addCell(new Cell(w, h, Integer.parseInt(Character.toString(line.charAt(w)))), w, h);
            }
        }

        board.update();
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.err.println("Updated grid");
        System.out.println(board.toString());
    }
}