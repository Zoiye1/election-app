import nl.hva.stack5.election.model.PartyResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ResearchExample {

    public static void main(String[] args) {
        int runs = 10;

        System.out.println("=== ADD Operation ===");
        calculateAverageAdd(runs);

        System.out.println("\n=== GET Operation ===");
        calculateAverageGet(runs);

        System.out.println("\n=== REMOVE Operation ===");
        calculateAverageRemove(runs);
    }

    // -------------------- ADD --------------------
    public static void calculateAverageAdd(int runs) {
        double totalArrayTime = 0;
        double totalLinkedTime = 0;

        for (int i = 0; i < runs; i++) {
            double arrayTime = timeDifferenceWAdd_ArrayList();
            double linkedTime = timeDifferenceWAdd_LinkedList();

            totalArrayTime += arrayTime;
            totalLinkedTime += linkedTime;
        }

        System.out.println("Average time to add 100,000 objects to ArrayList: " + (totalArrayTime / runs) + "s");
        System.out.println("Average time to add 100,000 objects to LinkedList: " + (totalLinkedTime / runs) + "s");
    }

    public static double timeDifferenceWAdd_ArrayList() {
        ArrayList<PartyResult> exampleArray = new ArrayList<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {
            exampleArray.add(new PartyResult());
        }

        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to add 100,000 objects to the ArrayList = " + seconds + "s");
        return seconds;
    }

    public static double timeDifferenceWAdd_LinkedList() {
        List<PartyResult> exampleLinkedList = new LinkedList<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {
            exampleLinkedList.add(new PartyResult());
        }

        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to add 100,000 objects to the LinkedList = " + seconds + "s");
        return seconds;
    }

    // -------------------- GET --------------------
    public static void calculateAverageGet(int runs) {
        double totalArrayTime = 0;
        double totalLinkedTime = 0;

        for (int i = 0; i < runs; i++) {
            double arrayTime = timeDifferenceWGet_ArrayList();
            double linkedTime = timeDifferenceWGet_LinkedList();

            totalArrayTime += arrayTime;
            totalLinkedTime += linkedTime;
        }

        System.out.println("Average ArrayList get time: " + (totalArrayTime / runs) + "s");
        System.out.println("Average LinkedList get time: " + (totalLinkedTime / runs) + "s");
    }

    public static double timeDifferenceWGet_ArrayList() {
        ArrayList<PartyResult> exampleArray = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) exampleArray.add(new PartyResult());

        Random random = new Random();
        int getAmount = 100_000;

        long startTime = System.nanoTime();
        for (int i = 0; i < getAmount; i++) {
            PartyResult result = exampleArray.get(random.nextInt(exampleArray.size()));
        }
        long endTime = System.nanoTime();

        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to get 100,000 random indices out of the ArrayList = " + seconds + "s");
        return seconds;
    }

    public static double timeDifferenceWGet_LinkedList() {
        List<PartyResult> exampleLinkedList = new LinkedList<>();
        for (int i = 0; i < 100_000; i++) exampleLinkedList.add(new PartyResult());

        Random random = new Random();
        int getAmount = 100_000;

        long startTime = System.nanoTime();
        for (int i = 0; i < getAmount; i++) {
            PartyResult result = exampleLinkedList.get(random.nextInt(exampleLinkedList.size()));
        }
        long endTime = System.nanoTime();

        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to get 100,000 random indices out of the LinkedList = " + seconds + "s");
        return seconds;
    }

    // -------------------- REMOVE --------------------
    public static void calculateAverageRemove(int runs) {
        double totalArrayTime = 0;
        double totalLinkedTime = 0;

        for (int i = 0; i < runs; i++) {
            double arrayTime = timeDifferenceWRemove_ArrayList();
            double linkedTime = timeDifferenceWRemove_LinkedList();

            totalArrayTime += arrayTime;
            totalLinkedTime += linkedTime;
        }

        System.out.println("Average ArrayList remove time: " + (totalArrayTime / runs) + "s");
        System.out.println("Average LinkedList remove time: " + (totalLinkedTime / runs) + "s");
    }

    public static double timeDifferenceWRemove_ArrayList() {
        ArrayList<PartyResult> exampleArray = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) exampleArray.add(new PartyResult());

        long startTime = System.nanoTime();
        for (int i = 0; i < 50_000; i++) {
            exampleArray.remove(0);
        }
        long endTime = System.nanoTime();

        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to remove the first element 50,000 times from the ArrayList = " + seconds + "s");
        return seconds;
    }

    public static double timeDifferenceWRemove_LinkedList() {
        List<PartyResult> exampleLinkedList = new LinkedList<>();
        for (int i = 0; i < 100_000; i++) exampleLinkedList.add(new PartyResult());

        long startTime = System.nanoTime();
        for (int i = 0; i < 50_000; i++) {
            exampleLinkedList.remove(0); // or removeFirst()
        }
        long endTime = System.nanoTime();

        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("The time it took to remove the first element 50,000 times from the LinkedList = " + seconds + "s");
        return seconds;
    }
}
