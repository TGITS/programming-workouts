import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        Board board = new Board(W,H);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            board.updateGrid(i, LINE);
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        board.setExitCoordinate(EX);

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(board.computeOutputPosition(XI,YI,POS));
        }
    }
}

interface RoomType {
    public String computeOutputPositionFromInputPosition(String position);
}

class RoomType0 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        return "";
    }
}

class RoomType1 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {

        if("LEFT".equals(position) || "RIGHT".equals(position) || "TOP".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType2 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("RIGHT".equals(position)) {
            return "LEFT";
        }

        if ("LEFT".equals(position)) {
            return "RIGHT";
        }

        return "";
    }
}

class RoomType3 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position)) {
            return "BOTTOM";
        }
        return "";
    }
}

class RoomType4 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position)) {
            return "LEFT";
        }

        if ("RIGHT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType5 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position)) {
            return "RIGHT";
        }

        if ("LEFT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType6 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("RIGHT".equals(position)) {
            return "LEFT";
        }

        if ("LEFT".equals(position)) {
            return "RIGHT";
        }

        return "";
    }
}

class RoomType7 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position) || "LEFT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType8 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("RIGHT".equals(position) || "LEFT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType9 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position) || "RIGHT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType10 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position)) {
            return "LEFT";
        }

        return "";
    }
}

class RoomType11 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("TOP".equals(position)) {
            return "RIGHT";
        }

        return "";
    }
}

class RoomType12 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("RIGHT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomType13 implements RoomType {

    @Override
    public String computeOutputPositionFromInputPosition(String position) {
        if ("LEFT".equals(position)) {
            return "BOTTOM";
        }

        return "";
    }
}

class RoomTypeFactory {

    private static HashMap<String,RoomType> roomTypeMap = new HashMap<>();
    static {
        roomTypeMap.put("0",new RoomType0());
        roomTypeMap.put("1",new RoomType1());
        roomTypeMap.put("2",new RoomType2());
        roomTypeMap.put("3",new RoomType3());
        roomTypeMap.put("4",new RoomType4());
        roomTypeMap.put("5",new RoomType5());
        roomTypeMap.put("6",new RoomType6());
        roomTypeMap.put("7",new RoomType7());
        roomTypeMap.put("8",new RoomType8());
        roomTypeMap.put("9",new RoomType9());
        roomTypeMap.put("10",new RoomType10());
        roomTypeMap.put("11",new RoomType11());
        roomTypeMap.put("12",new RoomType12());
        roomTypeMap.put("13",new RoomType13());
    }

    private RoomTypeFactory() {
        super();
    }

    public static RoomType createRoomType(String type){
        return roomTypeMap.get(type);
    }
}

final class Coordinate {
    private final int x,y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if (o instanceof Coordinate) {
            Coordinate c = (Coordinate)o;
            return this.x == c.getX() && this.y == c.getY() ;
        }
        return false;
    }

    public String toString(){
        return Integer.toString(x) + " " + Integer.toString(y);
    }
}

class Board {

    private final RoomType [][] grid;
    private final int width;
    private final int height;
    private Coordinate exitCoordinate;

    public Board(int width,int height){
        this.width = width;
        this.height = height;
        grid = new RoomType[height][width];
    }

    public void setExitCoordinate(int exitXCoordinate){
        this.exitCoordinate = new Coordinate(exitXCoordinate, this.height+1);
    }

    public void updateGrid(int rowNumber, String content){
        String[] line = content.split(" ");
        int i = 0;
        for(String item:line) {
            grid[rowNumber][i] = RoomTypeFactory.createRoomType(item);
            i++;
        }
    }

    public String computeOutputPosition(int x, int y, String position){
        StringBuilder sb = new StringBuilder();
        RoomType type = grid[y][x];
        String outputPosition = type.computeOutputPositionFromInputPosition(position);
        Coordinate coordinate = computeOutputCoordinate(x,y, outputPosition);
        if(coordinate.equals(exitCoordinate)){
           return "EX";
        }
        return coordinate.toString();
    }

    private Coordinate computeOutputCoordinate(int x, int y, String position){
        final Coordinate coordinate ;

        switch(position){
            case "BOTTOM":
                return new Coordinate(x, y + 1);
            case "LEFT":
                return new Coordinate(x-1, y);
            case "RIGHT":
                return new Coordinate(x+1, y);
        }

        return null;
    }
}