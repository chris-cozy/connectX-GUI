package cpsc2150.extendedConnectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Project 4
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */
public class ConnectXController {
    //hardcoded token array
    private final char[] tokens = new char[]{'X','Y','Z','O','A','C','S','T','V','B'};
    //current token tracker
    private int currentToken = 0;
    //boolean for if the game's over
    private boolean over = false;
    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private ConnectXView screen;
    //Max players
    public static final int MAX_PLAYERS = 10;
    //number of players
    private int numPlayers;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np) {
        //sets the model
        this.curGame = model;
        //sets the view
        this.screen = view;
        //sets the numPlayers
        numPlayers = np;
        screen.registerObserver(this);
    }

    /**
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        //if game is registered as over call newGame method
        if(over){
            newGame();
        }
        //set the column choice to variable c
        int c = col;
        //if column selection not in bounds, button click does nothing and asks for new column
        if ((c > curGame.getNumColumns()) || (c < 0)){
            screen.setMessage("Column selected is not valid. Try again");
        } else{
            //check if selected column is free, if so, place token
            if (curGame.checkIfFree(c)){
                curGame.placeToken(tokens[currentToken], c);
                //loop from the top of the column to find the row the token was placed and update the gui gameboard to reflect the placement
                for(int i = curGame.getNumRows(); i >= 0; i--){
                    BoardPosition temp = new BoardPosition(i,c);
                    if(curGame.whatsAtPos(temp) == tokens[currentToken]){
                        screen.setMarker(temp.getRow(), temp.getColumn(), tokens[currentToken]);
                        break;
                    }
                }
                //check for win or tie, if so, end restart game with next button click
                if(curGame.checkForWin(c)){
                    screen.setMessage("Player " + tokens[currentToken] + " has won! Press any button to start a new game");
                    over = true;
                } else if(curGame.checkTie()){
                    screen.setMessage("Tie Game! Press any button to start a new game");
                    over = true;
                }
                //increment token selection
                currentToken++;
                //if last player reached, reset to first player
                if(currentToken == numPlayers){
                    currentToken = 0;
                }
                //reflect whose turn it is
                if(!over){
                    screen.setMessage("It is " + tokens[currentToken] + "'s turn.");
                }
            } else{
                screen.setMessage("Column is full, try again.");
            }
        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
        over = false;
    }
}