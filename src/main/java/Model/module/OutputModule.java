package Model.module;

public class OutputModule extends Module {
    public OutputModule(String name) {
        super(name);
    }

    @Override
    public void sendPulse() {
    }

    @Override
    public void setInput(PulseInput input) {
    }

    @Override
    public boolean hasSendPulse() {
        return false;
    }
}
