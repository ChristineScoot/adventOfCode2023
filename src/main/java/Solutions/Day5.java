package Solutions;

import Model.SeedToSoil;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Day5 {
    public static Map<BigInteger, SeedToSoil> seedToSoil = new LinkedHashMap<>(); //<lowest seed from the range, entire line(dest, source, length)>, this probably could just be a list
    public static Map<BigInteger, SeedToSoil> soilToFertilizer = new LinkedHashMap<>(); //this probably could just be a list
    public static Map<BigInteger, SeedToSoil> fertilizerToWater = new LinkedHashMap<>(); //this probably could just be a list
    public static Map<BigInteger, SeedToSoil> waterToLight = new LinkedHashMap<>(); //this probably could just be a list
    public static Map<BigInteger, SeedToSoil> lightToTemperature = new LinkedHashMap<>(); //this probably could just be a list
    public static Map<BigInteger, SeedToSoil> temperatureToHumidity = new LinkedHashMap<>(); //this probably could just be a list
    public static Map<BigInteger, SeedToSoil> humidityToLocation = new LinkedHashMap<>(); //this probably could just be a list

    public static BigInteger part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 5: Part 1~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        BigInteger location;
        BigInteger minLocation = new BigInteger("9937863290");

        String[] seedsString = scanner.nextLine().split(": ")[1].split(" ");
        List<BigInteger> seeds = new ArrayList<>();
        for (String s : seedsString) {
            seeds.add(new BigInteger(s));
        }
        scanner.nextLine(); //""
        scanner.nextLine(); //"seed-to-soil map:"

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("soil-to-fertilizer map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            seedToSoil.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("fertilizer-to-water map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            soilToFertilizer.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("water-to-light map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(l[0]), new BigInteger(l[1]), new BigInteger(l[2]));
            fertilizerToWater.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("light-to-temperature map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            waterToLight.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("temperature-to-humidity map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            lightToTemperature.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("humidity-to-location map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            temperatureToHumidity.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            humidityToLocation.put(seed.getSource(), seed);
        }

        for (BigInteger seed : seeds) {
            BigInteger soil = seed; //find corresponding soil
            for (SeedToSoil s : seedToSoil.values()) {
                if (s.isWithinRange(seed))
                    soil = s.getDestination(seed);
            }
            BigInteger fertilizer = soil; //find corresponding fertilizer
            for (SeedToSoil s : soilToFertilizer.values()) {
                if (s.isWithinRange(soil))
                    fertilizer = s.getDestination(soil);
            }
            BigInteger water = fertilizer; //find corresponding water
            for (SeedToSoil s : fertilizerToWater.values()) {
                if (s.isWithinRange(fertilizer))
                    water = s.getDestination(fertilizer);
            }

            BigInteger light = water; //find corresponding light
            for (SeedToSoil s : waterToLight.values()) {
                if (s.isWithinRange(water))
                    light = s.getDestination(water);
            }
            BigInteger temperature = light; //find corresponding temperature
            for (SeedToSoil s : lightToTemperature.values()) {
                if (s.isWithinRange(light))
                    temperature = s.getDestination(light);
            }
            BigInteger humidity = temperature; //find corresponding light
            for (SeedToSoil s : temperatureToHumidity.values()) {
                if (s.isWithinRange(temperature))
                    humidity = s.getDestination(temperature);
            }
            location = humidity; //find corresponding light
            for (SeedToSoil s : humidityToLocation.values()) {
                if (s.isWithinRange(humidity))
                    location = s.getDestination(humidity);
            }

            if (location.compareTo(minLocation) < 0)
                minLocation = location;
        }

        return minLocation;
    }

    public static BigInteger part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 5: Part 2~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        BigInteger location;
        BigInteger minLocation = new BigInteger("9937863290");

        String[] seedsString = scanner.nextLine().split(": ")[1].split(" ");
        List<BigInteger> seeds = new ArrayList<>();
        for (String s : seedsString) {
            seeds.add(new BigInteger(s));
        }
        scanner.nextLine(); //""
        scanner.nextLine(); //"seed-to-soil map:"

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("soil-to-fertilizer map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            seedToSoil.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("fertilizer-to-water map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            soilToFertilizer.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("water-to-light map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(l[0]), new BigInteger(l[1]), new BigInteger(l[2]));
            fertilizerToWater.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("light-to-temperature map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            waterToLight.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("temperature-to-humidity map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            lightToTemperature.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals("humidity-to-location map:"))
                break;
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            temperatureToHumidity.put(seed.getSource(), seed);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] l = line.split(" ");
            SeedToSoil seed = new SeedToSoil(new BigInteger(
                    l[0]), new BigInteger(
                    l[1]), new BigInteger(
                    l[2]));
            humidityToLocation.put(seed.getSource(), seed);
        }

        for (int i = 0, j = 1; j < seeds.size(); i = i + 2, j = j + 2) {
            System.out.println("seeds[" + i + "][" + j + "]");
            for (BigInteger k = BigInteger.ZERO; k.compareTo(seeds.get(j)) < 0; k = k.add(BigInteger.ONE)) {
                BigInteger seed = seeds.get(i).add(k);
                BigInteger soil = seed; //find corresponding soil
                for (SeedToSoil s : seedToSoil.values()) {
                    if (s.isWithinRange(seed))
                        soil = s.getDestination(seed);
                }
                BigInteger fertilizer = soil; //find corresponding fertilizer
                for (SeedToSoil s : soilToFertilizer.values()) {
                    if (s.isWithinRange(soil))
                        fertilizer = s.getDestination(soil);
                }
                BigInteger water = fertilizer; //find corresponding water
                for (SeedToSoil s : fertilizerToWater.values()) {
                    if (s.isWithinRange(fertilizer))
                        water = s.getDestination(fertilizer);
                }

                BigInteger light = water; //find corresponding light
                for (SeedToSoil s : waterToLight.values()) {
                    if (s.isWithinRange(water))
                        light = s.getDestination(water);
                }
                BigInteger temperature = light; //find corresponding temperature
                for (SeedToSoil s : lightToTemperature.values()) {
                    if (s.isWithinRange(light))
                        temperature = s.getDestination(light);
                }
                BigInteger humidity = temperature; //find corresponding light
                for (SeedToSoil s : temperatureToHumidity.values()) {
                    if (s.isWithinRange(temperature))
                        humidity = s.getDestination(temperature);
                }
                location = humidity; //find corresponding light
                for (SeedToSoil s : humidityToLocation.values()) {
                    if (s.isWithinRange(humidity))
                        location = s.getDestination(humidity);
                }

                if (location.compareTo(minLocation) < 0)
                    minLocation = location;
            }
        }
        return minLocation;
    }
}