package Model.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.module.Pulse.HIGH;
import static Model.module.Pulse.LOW;

public class ConjunctionModule extends Module {
    private final Map<String, Pulse> inputMap;

    public ConjunctionModule(String name) {
        super(name);
        inputMap = new HashMap<>();
    }

    public void setInputs(List<Module> inputs) {
        for (Module s : inputs) {
            inputMap.put(s.getName(), LOW);
        }
    }

    @Override
    public void sendPulse() {
//        if high pulses for all input - send low else high
        if (inputMap.values().stream().allMatch(p -> p == HIGH)) {
            destinationModules.forEach(d -> d.setInput(new PulseInput(name, LOW)));
            nrLowPulsesSend += destinationModules.size();
        } else {
            destinationModules.forEach(d -> d.setInput(new PulseInput(name, HIGH)));
            nrHighPulsesSend += destinationModules.size();
        }
    }

    @Override
    public void setInput(PulseInput input) {
        inputMap.put(input.name(), input.pulse());
    }

    @Override
    public boolean hasSendPulse() {
        return true;
    }
}
