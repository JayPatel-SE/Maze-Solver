
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 
 */
public class Search {

    /*
    0 = path
    1 = wall
    2 = destination
    3 = starting point
     */
    private GridObj[][] grid;
    private int[][] maze;

    private ArrayList<GridObj> frontier;
    private ArrayList<GridObj> exploredSet;
    private Maze m;

    /* The coordinate move is currently on */
    private GridObj currObj;
    /* The coordinate to get to  */
    private GridObj goalObj;
    /* The starting coordinate */
    private GridObj startObj;

    private int stepsTaken;
    private boolean finished = false;

    public Search(int[][] maze) {

        this.maze = maze;
        frontier = new ArrayList<GridObj>();
        exploredSet = new ArrayList<GridObj>();
        createGrid();
        stepsTaken = 0;

//        currObj = grid[5][0];
//        frontier.add(currObj);
//        System.out.println("Set initial currObj and added to the frontier. X : " + currObj.getXCoord() + " Y: " + currObj.getYCoord() + "frontier size : " + frontier.size());

        while (finished == false) {
            algorithm();
            System.out.println();
        }//end while

//        System.out.println("index: " + ((grid[goalObj.getXCoord()].length * goalObj.getXCoord()) + goalObj.getYCoord()));
        m.setPanelColor((grid[goalObj.getXCoord()].length * goalObj.getXCoord()) + goalObj.getYCoord(), Color.ORANGE);
        m.setPanelColor((grid[startObj.getXCoord()].length * startObj.getXCoord()) + startObj.getYCoord(), Color.YELLOW);
    }//end class()

    /**
     * creates the maze and mazeUI
     */
    private void createGrid() {

        grid = new GridObj[maze.length][maze[0].length];
//        maze = new int[8][8];

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                grid[row][col] = new GridObj(row, col, maze[row][col]);
//                System.out.println("maze: " + maze[row][col] + " gridObj: " + grid[row][col]);
                if (maze[row][col] == 2) {
                    goalObj = grid[row][col];
//                    System.out.println("==========================");
//                    System.out.println("goalObj: " + goalObj);
//                    System.out.println("==========================");
                } else if(maze[row][col]==3){
                    currObj = grid[row][col];
                    startObj = grid[row][col];
                    frontier.add(currObj);
                }//end if
//                maze[row][col] = 0;
            }//end col()
        }//end row()

        //set the walls
//        grid[1][1].setState(1);
//        grid[1][3].setState(1);
//        grid[1][4].setState(1);
//        grid[1][5].setState(1);
//        grid[1][6].setState(1);
//        grid[1][7].setState(1);
//        grid[2][1].setState(1);
//        grid[3][1].setState(1);
//        grid[3][2].setState(1);
//        grid[3][4].setState(1);
//        grid[3][6].setState(1);
//        grid[3][7].setState(1);
//        grid[4][1].setState(1);
//        grid[4][4].setState(1);
//        grid[4][6].setState(1);
//        grid[5][1].setState(1);
//        grid[6][1].setState(1);
//        grid[6][2].setState(1);
//        grid[6][3].setState(1);
//        grid[6][5].setState(1);
//        grid[6][6].setState(1);
//        grid[6][7].setState(1);
//        grid[7][1].setState(1);
//        maze[1][1] = 1;
//        maze[1][3] = 1;
//        maze[1][4] = 1;
//        maze[1][5] = 1;
//        maze[1][6] = 1;
//        maze[1][7] = 1;
//        maze[2][1] = 1;
//        maze[3][1] = 1;
//        maze[3][2] = 1;
//        maze[3][4] = 1;
//        maze[3][6] = 1;
//        maze[3][7] = 1;
//        maze[4][1] = 1;
//        maze[4][4] = 1;
//        maze[4][6] = 1;
//        maze[5][1] = 1;
//        maze[6][1] = 1;
//        maze[6][2] = 1;
//        maze[6][3] = 1;
//        maze[6][5] = 1;
//        maze[6][6] = 1;
//        maze[6][7] = 1;
//        maze[7][1] = 1;
//        maze[0][1] = 1;
//        maze[0][3] = 1;
//        maze[1][1] = 1;
//        maze[1][3] = 1;
//        maze[3][0] = 1;
//        maze[3][2] = 1;
//        maze[3][3] = 1;
//        maze[3][4] = 1;
//        maze[4][2] = 1;
        //set the destination
//        grid[7][7].setState(2);
//        goalObj = grid[4][8];
//        maze[7][7] = 2;
        m = new Maze(grid);

        //ouput the text maze for testing purposes
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(maze[row][col] + " ");
            }//end col()
            System.out.println();
        }//end row()

        
        
//        System.out.println("==========================");
//        System.out.println("goalObj: " + goalObj);
//        System.out.println("==========================");

    }//end createGrid()

    /**
     * The main algorithm which does the work
     */
    private void algorithm() {

        /*
        1.  initially add the starting state to the frontier
        2. if the frontier is empty, there is no solution
        3. remove the node
            -if single node, just remove it,
            -if multiple nodes, then remove the one with the lowest path cost, and then set the currObj
        4.if currObj is the goal then return the solution
        5. add currObj to the explored set.
        5.explore the neighbors, and add the resulting node(s) to the frontier is they're not in the expored set
         */
        //add the initial state to the frontier
//        currObj = grid[grid.length - 1][0];
//        frontier.add(currObj);
//        System.out.println("Set initial currObj and added to the frontier. X : " + currObj.getXCoord() + " Y: " + currObj.getYCoord() + "frontier size : " + frontier.size());
        //checking if frontier is empty
        if (frontier.isEmpty()) {
            System.out.println("No solution");
            finished = true;
            return;
        }//end if

        //remove the best move node and make it the current node
        if (frontier.size() > 1) { //if there is more than 1 node in the frontier

            int bestMoveIndex = 0;

            for (int obj = 0; obj < frontier.size(); obj++) {
                //the moveCost for the current and previous "best" move
                int currMoveCost = manhattanDistance(frontier.get(obj), goalObj) + stepsTaken;
                int bestMoveCost = manhattanDistance(frontier.get(bestMoveIndex), goalObj) + stepsTaken;

                System.out.println(frontier.get(bestMoveIndex) + " best : " + bestMoveCost);
                System.out.println(frontier.get(obj) + " curr : " + currMoveCost);

                //check if the currenti
                if (currMoveCost <= bestMoveCost) {
                    bestMoveIndex = obj;
                }//end if
            }//end forloop()

            //remove the best move and make it the current node
//            System.out.println("best move taken : " + manhattanDistance(frontier.get(bestMoveIndex), goalObj) + stepsTaken);
            currObj = frontier.get(bestMoveIndex);
            frontier.remove(bestMoveIndex);
            stepsTaken++;
//            System.out.println("here " + currObj);
            System.out.println(currObj);

        } else { //only 1 node in the frontier

            //remove the only node and make it the current node
            currObj = frontier.get(0);
            frontier.remove(0);
            stepsTaken++;
//            System.out.println("here " + currObj);
            System.out.println(currObj);

        }//end if

        //check if current position is the goal state
        if (currObj.getState() == 2) {
            System.out.println("Found the destination");
            finished = true;
            return;
        }//end if
        System.out.println("Checked for if current state was goal or not");

        m.setPanelColor((grid[currObj.getXCoord()].length * currObj.getXCoord()) + currObj.getYCoord(), Color.GREEN);

        //add current node to the explored set
        exploredSet.add(currObj);
//        System.out.println("Added obj to the explored set size : " + exploredSet.size());

        //expand node, and add the resulting nodes into the frontier if they aren't already in the explored set
        expandNode(currObj);

        System.out.println("Taken Steps : " + stepsTaken);
        for (int i = 0; i < frontier.size(); i++) {
//            System.out.print(manhattanDistance(frontier.get(i), goalObj) + stepsTaken + "\t");
            System.out.println(frontier.get(i) + " " + (manhattanDistance(frontier.get(i), goalObj) + stepsTaken));
        }
        System.out.println();
    }//end algorithm()

    /**
     * Check all of the neighboring node
     *
     * @param node
     */
    private void expandNode(GridObj node) {

        int x = node.getXCoord();
        int y = node.getYCoord();

        if (x == 0 && y == 0) {//topLeftCorner
            System.out.println("topleft");
            checkRight(x, y);
            checkBottom(x, y);
        } else if (x == 0 && y == grid[x].length - 1) { //topRightCorner
            System.out.println("topRight");
            checkLeft(x, y);
            checkBottom(x, y);
        } else if (x == grid.length - 1 && y == 0) { //bottomLeftCorner
            System.out.println("bottomLeft");
            checkRight(x, y);
            checkTop(x, y);
        } else if (x == grid.length - 1 && y == grid[x].length - 1) { //bottomRightCorner
            System.out.println("bottomRight");
            checkLeft(x, y);
            checkTop(x, y);
        } else if (y == 0) { //left edge
            System.out.println("left edge");
            checkTop(x, y);
            checkRight(x, y);
            checkBottom(x, y);
        } else if (y == grid[x].length - 1) { //right edge
            System.out.println("right edge");
            checkTop(x, y);
            checkLeft(x, y);
            checkBottom(x, y);
        } else if (x == 0) { //top edge
            System.out.println("top edge");
            checkLeft(x, y);
            checkRight(x, y);
            checkBottom(x, y);
        } else if (x == grid.length - 1) { //bottom edges
            System.out.println("bottomedge");
            checkLeft(x, y);
            checkRight(x, y);
            checkTop(x, y);
        } else {
            System.out.println("middle");
            checkLeft(x, y);
            checkRight(x, y);
            checkTop(x, y);
            checkBottom(x, y);
        }//end if

    }//end expandNode()

    /**
     * Check the node up above on the grid
     *
     * @param x, x coord of the current node
     * @param y, y coord of the current node
     */
    private void checkTop(int x, int y) {

        //the node above
        GridObj aboveObj = grid[x - 1][y];

        //check if the node has already been explored or not
        if (inExploredSet(aboveObj.getXCoord(), aboveObj.getYCoord()) == false
                && aboveObj.getState() != 1) {
            frontier.add(aboveObj);
            m.setPanelColor((grid[x-1].length * (x - 1)) + y, Color.BLUE);
        }//end if

    }//end checkTop()

    /**
     * Check the node to the left on the grid
     *
     * @param x, x coord of the current node
     * @param y, y coord of the current node
     */
    private void checkLeft(int x, int y) {

        //node to the left
        GridObj leftObj = grid[x][y - 1];

        //check if the node has already been explored or not
        if (inExploredSet(leftObj.getXCoord(), leftObj.getYCoord()) == false
                && leftObj.getState() != 1) {
            frontier.add(leftObj);
            m.setPanelColor((grid[x].length * x) + (y - 1), Color.BLUE);
        }//end if

    }//end checkLeft()

    /**
     * Check the node to the right on the grid
     *
     * @param x, x coord of the current node
     * @param y, y coord of the current node
     */
    private void checkRight(int x, int y) {

        //node to the right
        GridObj rightObj = grid[x][y + 1];

        //check if the node has already been explored or not
        if (inExploredSet(rightObj.getXCoord(), rightObj.getYCoord()) == false
                && rightObj.getState() != 1) {
            frontier.add(rightObj);
            m.setPanelColor((grid[x].length * x) + (y + 1), Color.BLUE);
        }//end if

    }//end checkRight()

    /**
     * Check the node below on the grid
     *
     * @param x, x coord of the current node
     * @param y, y coord of the current node
     */
    private void checkBottom(int x, int y) {

        //node to the bottom
        GridObj bottomObj = grid[x + 1][y];

        //check if the node has already been explored or not
        if (inExploredSet(bottomObj.getXCoord(), bottomObj.getYCoord()) == false
                && bottomObj.getState() != 1) {
            frontier.add(bottomObj);
            m.setPanelColor((grid[x+1].length * (x + 1)) + y, Color.BLUE);
        }//end if

    }//end checkBottom()

    /**
     * Calculate the manhattan distance from the current node to the goal node
     *
     * @param curr
     * @param goal
     * @return the distance represented as an integer
     */
    private int manhattanDistance(GridObj curr, GridObj goal) {

        return (Math.abs(curr.getXCoord() - goal.getXCoord())) + (Math.abs(curr.getYCoord() - goal.getYCoord()));

    }//end manhattanDistance()    

    /**
     * Check if node is in the explored set
     *
     * @param x
     * @param y
     * @return true if in the set, false is not in the set
     */
    private boolean inExploredSet(int x, int y) {

        for (int i = 0; i < exploredSet.size(); i++) {

            if (exploredSet.get(i).getXCoord() == x
                    && exploredSet.get(i).getYCoord() == y) { //if the node is in the explored set
                return true;
            }//end if

        }//end forloop()

        //node is not in the explored sets
        return false;

    }//end inExploredSet()

    public static void main(String[] args) {

        int[][] maze = {{0, 1, 0, 1, 0, 1, 0, 0, 0, 3, 0},
                                {0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
                                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                                {0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                                {0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0},
                                {0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0},
                                {0, 1, 2, 1, 1, 1, 0, 1, 0, 1, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}

        };
        int[][] maze2 = {{1, 1, 0, 0, 0, 0, 0, 0},
                                    {3, 1, 0, 1, 1, 1, 0, 0},
                                    {0, 1, 0, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 1, 1, 1, 1, 1},
                                    {1, 1, 0, 1, 0, 2, 0, 0},
                                    {1, 1, 0, 1, 0, 1, 1, 0},
                                    {0, 1, 0, 0, 0, 1, 1, 1},
                                    {0, 0, 0, 1, 0, 0, 1, 1}};

        int[][] maze3 = {{1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
                                    {1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
                                    {1, 1, 0, 1, 3, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                                    {0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                                    {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
                                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1},
                                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                                    {1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1},
                                    {1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
                                    {1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                                    {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1},
                                    {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1},
                                    {0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                                    {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1},
                                    {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0},
                                    {1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                                    {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                                    {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1}};
        
        Search s = new Search(maze3);

    }//end main()

}//end class()
