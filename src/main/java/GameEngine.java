public class GameEngine {
    private static final int MAX_ATTEMPTS = 10;

    private final int min;
    private final int max;
    private int target;
    private int attempts;
    private boolean gameWon;

    private boolean userQuit;
    private boolean hintsEnabled;
    private boolean gameOver;

    public GameEngine(int min, int max) {
        this.min = min;
        this.max = max;
        this.hintsEnabled = true;
        reset();
    }

    public GuessResult makeGuess(int guess) {
        if (guess < 0) {
            userQuit = true;
            return new GuessResult(false, "Exiting game...", attempts);
        }

        if (gameOver || gameWon) {
            return new GuessResult(false, "Game is already over. Please reset to play again.", attempts);
        }

        attempts++;

        // win the game
        if (guess ==target) {
            gameWon = true;
            return new GuessResult(true,"Correct! You guessed it in " + attempts + " attempts.", attempts);
        }

        // Max attempts reached --- game over
        if (attempts >= MAX_ATTEMPTS) {
            gameOver = true;
            return new GuessResult(false, "Game Over! Max attempts reached", attempts);
        }

        String message;
        if (guess < target) {
            message = "Too low! Try a higher number.";
        } else {
            message = "Too high! Try a lower number.";
        }

        String hint = getHint(guess);
        return new GuessResult(false, message + hint, attempts);
    }

    public void reset() {
        target = Utils.randomInt(min, max);
        attempts = 0;
        gameWon = false;
        userQuit = false;
        gameOver = false;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean hasUserQuit() {
        return userQuit;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isHintsEnabled() {
        return hintsEnabled;
    }

    public void setHintsEnabled(boolean enabled) {
        this.hintsEnabled = enabled;
    }

    private String getHint(int guess) {
        if (!hintsEnabled) return "";

        int diff = Math.abs(target - guess);

        if (attempts >= 3 && diff <= 10) {
            return " HINT: You're very close!";
        } else if (attempts >= 5 && diff <= 20) {
            return " HINT: Getting warmer!";
        }
        return "";
    }

    protected void setTarget(int target) {
        this.target = target;
    }

    protected int getTarget() {
        return target;
    }
}
