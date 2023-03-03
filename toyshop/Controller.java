package toyshop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;


public class Controller {
    Queue<Toy> toysQueue = new ArrayDeque<>();
    private final String csvToys = "StorageToys.csv";
    private ArrayList<Toy> toysList;

    protected void run() throws IOException {
        dropChance();
        int numberOfPrizes = 10;
        for (int i = 1; i <= numberOfPrizes; i++) {
            Toy chosenToy = chooseToy();
            manageToy(chosenToy);
        }
        System.out.println(toysQueue);
        writeWinners();
    }

    private void dropChance() {
        exportListToCSV();
    }

    protected Toy chooseToy() {
        Lottery lot = new Lottery();
        while (true) {
            for (Toy toy : toysList) {
                if (lot.lottery(toy.getDropChance())) {
                    return toy;
                }
            }
        }
    }

    protected void manageToy(Toy toy) {
        toy.setToyQuantity(toy.getToyQuantity() - 1);
        String info = '\n' +
                "Picked Toy: " +
                toy.getToyName() +
                ". " +
                "Toys left: " +
                toy.getToyQuantity() +
                '\n';
        System.out.println(info);
        if (toy.getToyQuantity() <= 0) {
            toysList.remove(toy);
        }
        toysQueue.add(toy);
        exportListToCSV();
    }

    private String makeStringForCSV(Toy toy) {
        return String.valueOf(toy.getToyId()) +
                ';' +
                toy.getDropChance() +
                ';' +
                toy.getToyName() +
                ';' +
                toy.getToyQuantity() +
                '\n';
    }

    protected void writeWinners() throws IOException {
        String csvWinners = "Winners.csv";
        File winCSV = new File(csvWinners);
        boolean fileCreated = winCSV.createNewFile();
        final Path path = Paths.get(csvWinners);
        if (!fileCreated && !winCSV.exists()) {
            throw new IOException("Unable to create a file at specified path.");
        }


        while (!toysQueue.isEmpty()) {
            Toy toy = toysQueue.poll();
            String str = makeStringForCSV(toy);
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                writer.append(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void exportListToCSV() {
        String str1 = makeStringForCSV(toysList.get(0));
        Path path = Paths.get(csvToys);
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.append(str1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < toysList.size(); i++) {
            String str2 = makeStringForCSV(toysList.get(i));
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                writer.append(str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void CSVtoArray() {
        toysList = new ArrayList<>();
        File csvFile = new File(csvToys);
        if (csvFile.isFile()) {
            BufferedReader csvReader;
            try {
                csvReader = new BufferedReader(new FileReader(csvToys));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String row;
            while (true) {
                try {
                    if ((row = csvReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] data = row.split(";");
                toysList.add(new Toy(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3])));
            }
            try {
                csvReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Toy toy : toysList) {
                System.out.println(toy);
            }
        }
    }


    private Toy addToyInstance() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter id:");
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("Enter drop chance:");
        int dropChance = Integer.parseInt(sc.nextLine());

        System.out.println("Enter name:");
        String name = sc.nextLine();

        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(sc.nextLine());

        sc.close();
        return new Toy(id,dropChance, name, quantity);
    }

    private void addToyToList(Toy toy) {
        toysList.add(toy);
    }

    private void addToyToCSV(Toy toy) {
        String str = makeStringForCSV(toy);
        try (Writer writer = Files.newBufferedWriter(Paths.get(csvToys), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addToy() {
        Toy newToy = this.addToyInstance();
        this.addToyToCSV(newToy);
        this.addToyToList(newToy);
    }
}