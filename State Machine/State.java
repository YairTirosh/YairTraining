<<<<<<< HEAD
public class State{
    private final String name;
    private final StateSubsystem system;
    private static Runnable stateAction;

    public State(String name, StateSubsystem system, Runnable stateAction){
        this.name = name;
        this.system = system;
        this.stateAction = stateAction;
    }
    public String getName() {
        return name;
    }
    public StateSubsystem getSystem() {
        return system;
    }
    public static Runnable getStateAction() {
        return stateAction;
    }
    

=======
public class State{
    private final String name;
    private final StateSubsystem system;
    private static Runnable stateAction;

    public State(String name, StateSubsystem system, Runnable stateAction){
        this.name = name;
        this.system = system;
        this.stateAction = stateAction;
    }
    public String getName() {
        return name;
    }
    public StateSubsystem getSystem() {
        return system;
    }
    public static Runnable getStateAction() {
        return stateAction;
    }
    

>>>>>>> de7f643f584afbca54c0f372ae76c447c5cad8a2
}