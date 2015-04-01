package simpleGa;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String... args) throws InterruptedException, IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("genetic.properties")) {
            properties.load(reader);
        }
        int populationSize = Integer.parseInt(properties.getProperty("populationSize", "100"));
        int maxIter = Integer.parseInt(properties.getProperty("maxIter", "100000"));
        double uniformRate = Double.parseDouble(properties.getProperty("uniformRate", "0.1"));
        double mutationRate = Double.parseDouble(properties.getProperty("mutationRate", "0.015"));
        int tournamentSize = Integer.parseInt(properties.getProperty("tournamentSize", "5"));
        boolean elitism = Boolean.parseBoolean(properties.getProperty("elitism", "true"));

        System.out.println(properties);

        Algorithm algo = new Algorithm(uniformRate, mutationRate, tournamentSize, elitism);
        Population myPop = new Population(populationSize, true);
        int iter = 0;
        while (iter < maxIter) {
            iter += 1;
            myPop = algo.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + iter);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
        System.out.println(myPop.getFittest().getA());
        System.out.println(myPop.getFittest().getB());
        System.out.println(myPop.getFittest().getC());
    }
}
