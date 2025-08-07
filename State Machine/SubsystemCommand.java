<<<<<<< HEAD
public class SubsystemCommand{
    private final StateSubsystem actSystem;
    private iSystem log;

    public SubsystemCommand(StateSubsystem actSystem, iSystem log) {
        this.actSystem = actSystem;
        this.log = log;
    }
    public StateSubsystem getSubsystem() {
        return actSystem;
    }
    public void runSystem() {
        if (actSystem.getSystemMode() == SystemMode.ENABLE) {
            State.getStateAction();
            System.out.println(actSystem.currentState.getName());
        }
        log.logSystem();
    }

=======
public class SubsystemCommand{
    private final StateSubsystem actSystem;
    private iSystem log;

    public SubsystemCommand(StateSubsystem actSystem, iSystem log) {
        this.actSystem = actSystem;
        this.log = log;
    }
    public StateSubsystem getSubsystem() {
        return actSystem;
    }
    public void runSystem() {
        if (actSystem.getSystemMode() == SystemMode.ENABLE) {
            State.getStateAction();
            System.out.println(actSystem.currentState.getName());
        }
        log.logSystem();
    }

>>>>>>> de7f643f584afbca54c0f372ae76c447c5cad8a2
}