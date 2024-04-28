package Model.module;

import static Model.module.Pulse.LOW;

public class BroadcasterModule extends Module {

    public BroadcasterModule(String name) {
        super(name);
    }

    @Override
    public void sendPulse() {
        PulseInput input = getNextInput();
        destinationModules.forEach(d -> d.setInput(new PulseInput(name, input.pulse())));
        if (input.pulse() == LOW) {
            nrLowPulsesSend += destinationModules.size();
        } else {
            nrHighPulsesSend += destinationModules.size();
        }
    }

    @Override
    public void setInput(PulseInput input) {
        this.inputQueue.add(input);
    }


    @Override
    public boolean hasSendPulse() {
        return true;
    }
}
