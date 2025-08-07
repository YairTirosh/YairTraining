<<<<<<< HEAD
abstract class StateSubsystem implements iSystem{
    private final String name;
    private final State[] allStates;
    public State currentState;
    public SystemMode actionState;

    public StateSubsystem(String name, State[] allStates) {
        this.actionState = SystemMode.ENABLE;
        this.allStates = allStates;
        this.name = name;
        this.currentState = allStates[1];
    }
    public void setState(State newState) {
        for (int i = 0; i < allStates.length; i++ ){
            if (newState == allStates[i]){
                currentState = newState;
            }
        }
    }
    public State getCurrentState() {
        return currentState;
    }
    public String getName() {
        return name;
    }
    public SystemMode getSystemMode() {
        return actionState;
    }

    public void setSystemMode(SystemMode newSystemMode) {
        this.actionState = newSystemMode;
    }
    public abstract boolean canMove();


    public void logSystem() {

    }
=======
abstract class StateSubsystem implements iSystem{
    private final String name;
    private final State[] allStates;
    public State currentState;
    public SystemMode actionState;

    public StateSubsystem(String name, State[] allStates) {
        this.actionState = SystemMode.ENABLE;
        this.allStates = allStates;
        this.name = name;
        this.currentState = allStates[1];
    }
    public void setState(State newState) {
        for (int i = 0; i < allStates.length; i++ ){
            if (newState == allStates[i]){
                currentState = newState;
            }
        }
    }
    public State getCurrentState() {
        return currentState;
    }
    public String getName() {
        return name;
    }
    public SystemMode getSystemMode() {
        return actionState;
    }

    public void setSystemMode(SystemMode newSystemMode) {
        this.actionState = newSystemMode;
    }
    public abstract boolean canMove();


    public void logSystem() {

    }
>>>>>>> de7f643f584afbca54c0f372ae76c447c5cad8a2
}