package Model.module;

import static Model.module.Pulse.HIGH;
import static Model.module.Pulse.LOW;

public class FlipFlopModule extends Module {
    private boolean isOn = false;
    private boolean sendPulse = false;

    public FlipFlopModule(String name) {
        super(name);
    }

    @Override
    public void sendPulse() {
        //if high - nothing happens
        PulseInput input = getNextInput();
        sendPulse = false;
        if (input.pulse() == LOW) {
            isOn = !isOn;
            destinationModules.forEach(d -> d.setInput(new PulseInput(this.name, isOn ? HIGH : LOW)));
            sendPulse = true;
            if (isOn) {
                nrHighPulsesSend += destinationModules.size();
            } else {
                nrLowPulsesSend += destinationModules.size();
            }
        }
    }

    @Override
    public void setInput(PulseInput input) {
        this.inputQueue.add(input);
    }

    @Override
    public boolean hasSendPulse() {
        return sendPulse;
    }
}
