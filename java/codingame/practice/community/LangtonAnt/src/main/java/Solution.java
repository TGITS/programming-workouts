import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        String direction = in.next();
        Ant ant = new Ant(new Position(x, y), Direction.fromLabel(direction));
        int T = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Color[][] colorGrid = new Color[W][H];
        for (int r = 0; r < H; r++) {
            String C = in.nextLine();
            for (int c = 0; c < W; c++) {
                colorGrid[r][c] = Color.fromSymbol(Character.toString(C.charAt(c)));
            }
        }
        Grid grid = new Grid(W, H, colorGrid);
        System.err.println("Initial grid : ");
        System.err.println(grid.toString());
        System.err.println("----------");
        System.err.println();
        ant.move(grid, T);
        System.out.println(grid.toString());
    }
}

class Grid {
    private int width;
    private int height;
    private Color[][] grid;

    public Grid(int width, int height, Color[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public boolean isCellWhite(Position position) {
        return grid[position.getY()][position.getX()] == Color.WHITE;
    }

    public void toogleCellColor(Position position) {
        if (this.isCellWhite(position)) {
            grid[position.getY()][position.getX()] = Color.BLACK;
        } else {
            grid[position.getY()][position.getX()] = Color.WHITE;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(this.grid[y][x].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class Position {
    private int x;//Column
    private int y;//Row

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incX() {
        x++;
    }

    public void incY() {
        y++;
    }

    public void decX() {
        x--;
    }

    public void decY() {
        y--;
    }
}

enum Direction {
    N("N", 0), E("E", 1), S("S", 2), W("W", 3);

    private String label;
    private int value;

    Direction(String label, int value) {
        this.label = label;
        this.value = value;
    }

    String label() {
        return this.label;
    }

    int value() {
        return this.value;
    }

    private static final Map<String, Direction> labelToDirection = new HashMap<>();
    private static final Direction[] valueToDirection = new Direction[4];

    static {
        for (Direction direction : values()) {
            labelToDirection.put(direction.label(), direction);
            valueToDirection[direction.value] = direction;
        }
    }

    public static Direction fromLabel(String label) {
        final Direction direction = labelToDirection.get(label);
        if (direction != null) {
            return direction;
        }
        throw new IllegalArgumentException("Unknown label : " + label);
    }

    public static Direction fromValue(int value) {
        if (value >= values().length) {
            throw new IllegalArgumentException("Impossible value : " + value);
        }
        final Direction direction = valueToDirection[value];

        return direction;
    }
}

enum Color {
    BLACK("#"), WHITE(".");

    private String symbol;

    Color(String symbol) {
        this.symbol = symbol;
    }

    String symbol() {
        return this.symbol;
    }

    private static final Map<String, Color> symbolToColor = new HashMap<>();

    static {
        for (Color color : values()) {
            symbolToColor.put(color.symbol(), color);
        }
    }

    public static Color fromSymbol(String symbol) {
        final Color color = symbolToColor.get(symbol);
        if (color != null) {
            return color;
        }
        throw new IllegalArgumentException("Unknown symbol" + symbol);
    }

    public String toString() {
        return this.symbol();
    }
}

class Ant {
    private Position position;
    private Direction direction;

    public Ant(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void move(Grid grid, int turns) {
        for (int i = 0; i < turns; i++) {
            this.step(grid);
        }
    }

    private void step(Grid grid) {
        //the ant rotates 90 degrees left if it is on a white square, 90 degrees right otherwise.
        if (grid.isCellWhite(this.position)) {
            this.turnLeft();
        } else {
            this.turnRight();
        }
        //the ant inverts the color of the square it is located on.
        grid.toogleCellColor(this.position);
        //it moves 1 square forward in its current direction.
        this.updatePosition();
    }

    private void turnLeft() {
        int value = this.direction.value();
        value -= 1;
        if (value < 0) {
            value += 4;
        }
        this.direction = Direction.fromValue(value);
    }

    private void turnRight() {
        int value = this.direction.value();
        value = (value + 1) % 4;
        this.direction = Direction.fromValue(value);
    }

    private void updatePosition() {
        switch (this.direction) {
            case N:
                this.position.decY();
                break;
            case E:
                this.position.incX();
                break;
            case S:
                this.position.incY();
                break;
            case W:
                this.position.decX();
                break;
        }
    }
}