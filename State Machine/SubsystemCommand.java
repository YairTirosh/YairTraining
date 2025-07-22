public class SubsystemCommand{
    private final StateSubsystem actSystem;
    private System log;

    public SubsystemCommand(StateSubsystem actSystem, System log) {
        this.actSystem = actSystem;
        this.log = log;
    }
    public StateSubsystem getSubsystem() {
        return actSystem;
    }
    public void runSystem() {
        if (StateSubsystem.actionState == SystemMode.ENABLE) {
            State.getStateAction();
            System.out.println(State.getName());
        }
        System.logSystem();
    }

}