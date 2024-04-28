package Model.module;

import java.util.LinkedList;
import java.util.List;

public abstract class Module {
    public static long nrLowPulsesSend = 0;
    public static long nrHighPulsesSend = 0;
    protected List<Module> destinationModules;
    protected String name;
    protected LinkedList<PulseInput> inputQueue;

    public Module(String name) {
        this.name = name;
        this.inputQueue = new LinkedList<>();
    }

    public abstract void sendPulse();

    public abstract void setInput(PulseInput input);

    protected PulseInput getNextInput() {
        return inputQueue.pop();
    }

    public abstract boolean hasSendPulse();

    public void setDestinationModules(List<Module> destinations) {
        this.destinationModules = destinations;
    }

    public String getName() {
        return name;
    }

    public List<Module> getDestinationModules() {
        return destinationModules;
    }

    public static void resetPulsesSend() {
        nrLowPulsesSend = 0;
        nrHighPulsesSend = 0;
    }
}
