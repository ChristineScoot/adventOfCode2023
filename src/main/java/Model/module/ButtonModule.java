package Model.module;

import static Model.module.Pulse.LOW;

public class ButtonModule extends Module {
    public ButtonModule(String name) {
        super(name);
    }

    @Override
    public void sendPulse() {
        destinationModules.forEach(d -> d.setInput(new PulseInput(name, LOW)));
        nrLowPulsesSend += destinationModules.size();
    }

    @Override
    public void setInput(PulseInput input) {
    }

    @Override
    public boolean hasSendPulse() {
        return true;
    }
}
