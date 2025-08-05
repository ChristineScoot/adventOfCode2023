package Solutions;

import Model.module.Module;
import Model.module.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day20 {
    private static final Map<String, Module> allModules = new HashMap<>();
    private static final Map<String, Long> clicks = new HashMap<>();

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 20: Part 1~~~~~~~~~~~~");
        Module.resetPulsesSend();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Map<Module, List<String>> moduleToDestinations = new HashMap<>();

        for (String line : fileContentString) {
            String name;
            Module module;
            name = line.substring(0, line.indexOf(" -> "));
            if (name.contains("%")) { //"flip-flop"
                name = name.replace("%", "");
                module = new FlipFlopModule(name);
            } else if (name.contains("&")) { //"conjunction"
                name = name.replace("&", "");
                module = new ConjunctionModule(name);
            } else { //broadcast
                module = new BroadcasterModule(name);
            }
            String[] destinationModules = line.substring(line.indexOf(" -> ") + 4).split(", ");
            allModules.put(name, module);
            moduleToDestinations.put(module, List.of(destinationModules));
        }

        //String destinationModules to Module destinationModules
        Map<Module, List<Module>> conjModuleToInputs = createDestinationModulesObjects(moduleToDestinations);

        //add inputs to conjunction modules
        for (Map.Entry<Module, List<Module>> entry : conjModuleToInputs.entrySet()) {
            ConjunctionModule cModule = (ConjunctionModule) entry.getKey();
            cModule.setInputs(entry.getValue());
        }

        handleButton();

        for (int click = 0; click < 1000; click++) {
            pushButton(allModules.get("button"), new LinkedList<>());
        }
        return Module.nrHighPulsesSend * Module.nrLowPulsesSend;
    }

    private static Map<Module, List<Module>> createDestinationModulesObjects(Map<Module, List<String>> moduleToDestinations) {
        Map<Module, List<Module>> conjModuleToInputs = new HashMap<>();
        for (Map.Entry<Module, List<String>> entry : moduleToDestinations.entrySet()) {
            Module module = entry.getKey();
            List<String> outputs = entry.getValue();
            List<Module> outputList = new ArrayList<>();
            for (String o : outputs) {
                if (!allModules.containsKey(o)) {
                    allModules.put(o, new OutputModule(o));
                }
                Module dest = allModules.get(o);
                outputList.add(dest);
                if (dest instanceof ConjunctionModule) {
                    conjModuleToInputs.putIfAbsent(dest, new ArrayList<>());
                    List<Module> inputs = conjModuleToInputs.get(dest);
                    inputs.add(module);
                    conjModuleToInputs.put(dest, inputs);
                }
            }
            module.setDestinationModules(outputList);
        }
        return conjModuleToInputs;
    }

    private static void handleButton() {
        //add a button module - broadcaster is a destination
        Module buttonModule = new ButtonModule("button");
        List<Module> buttonDest = new ArrayList<>();
        buttonDest.add(allModules.get("broadcaster"));
        buttonModule.setDestinationModules(buttonDest);
        allModules.put(buttonModule.getName(), buttonModule);
    }

    private static void pushButton(Module module, LinkedList<Module> queue) {
        queue.add(module);
        while (!queue.isEmpty()) {
            Module current = queue.pop();
            current.sendPulse();
            if (!(current instanceof OutputModule) && current.hasSendPulse()) {
                queue.addAll(current.getDestinationModules());
            }
        }
    }

    private static void pushButton2(Module module, LinkedList<Module> queue, long click) {
        queue.add(module);
        while (!queue.isEmpty()) {
            Module current = queue.pop();

            long before = Module.nrHighPulsesSend;
            current.sendPulse();
            long diff = Module.nrHighPulsesSend - before;
            if (diff > 0 && !clicks.containsKey(current.getName()))
                clicks.put(current.getName(), click);

            if (!(current instanceof OutputModule) && current.hasSendPulse()) {
                queue.addAll(current.getDestinationModules());
            }
        }
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 20: Part 2~~~~~~~~~~~~");

        Module.resetPulsesSend();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Map<Module, List<String>> moduleToDestinations = new HashMap<>();

        for (String line : fileContentString) {
            String name;
            Module module;
            name = line.substring(0, line.indexOf(" -> "));
            if (name.contains("%")) { //"flip-flop"
                name = name.replace("%", "");
                module = new FlipFlopModule(name);
            } else if (name.contains("&")) { //"conjunction"
                name = name.replace("&", "");
                module = new ConjunctionModule(name);
            } else { //broadcast
                module = new BroadcasterModule(name);
            }
            String[] destinationModules = line.substring(line.indexOf(" -> ") + 4).split(", ");
            allModules.put(name, module);
            moduleToDestinations.put(module, List.of(destinationModules));
        }

        //String destinationModules to Module destinationModules
        Map<Module, List<Module>> conjModuleToInputs = createDestinationModulesObjects(moduleToDestinations);

        //add inputs to conjunction modules
        for (Map.Entry<Module, List<Module>> entry : conjModuleToInputs.entrySet()) {
            ConjunctionModule cModule = (ConjunctionModule) entry.getKey();
            cModule.setInputs(entry.getValue());
        }

        handleButton();
        for (int click = 1; true; click++) {
            if (click % 100000 == 0) System.out.println(click);
            pushButton2(allModules.get("button"), new LinkedList<>(), click);
            if (clicks.containsKey("ks") && clicks.containsKey("jf") && clicks.containsKey("qs") && clicks.containsKey("zk"))
                break;
        }
        return clicks.get("ks") * clicks.get("jf") * clicks.get("qs") * clicks.get("zk"); //ks jf qs zk , those 4 have to receive a high pulse for my input
    }
}
