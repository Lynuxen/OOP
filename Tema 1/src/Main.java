import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;
/**
 * @author Kirjan Dimovski
 * @since 11.11.2019
 * @see "README"
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File inp = new File("queue.in");
        Scanner input = new Scanner(inp);
        PrintStream output = new PrintStream("queue.out");
        System.setOut(output);

        ArrayList<Single> ArrayOfSingles = new ArrayList<>();
        ArrayList<Passenger> Parsed = new ArrayList<>();
        Heap heap = new Heap();
/*
    Creates an array of all the singles to be parsed into families or groups.
 */
        int i, k;
        int numberOfPassengers = input.nextInt();
        for (i = 0; i < numberOfPassengers; i++) {
            Single p1;
            p1 = new Single(input.next(), input.next(), input.nextInt(), input.next(), input.next(), input.next());

            ArrayOfSingles.add(i, p1);
        }
        boolean found = false;
        for (i = 0; i < numberOfPassengers; i++) {
            found = false;
            if (Parsed.size() != 0) {
                for (k = 0; k < Parsed.size(); k++) {
                    if (Parsed.get(k).id.equals(ArrayOfSingles.get(i).id)) {
                        if (Parsed.get(k).id.charAt(0) == 'f') {
                            ((Family) Parsed.get(k)).members.add(ArrayOfSingles.get(i));
                            Parsed.get(k).priority += ArrayOfSingles.get(i).priority - 10;
                            found = true;
                        } else if (Parsed.get(k).id.charAt(0) == 'g') {
                            ((Group) Parsed.get(k)).members.add(ArrayOfSingles.get(i));
                            Parsed.get(k).priority += ArrayOfSingles.get(i).priority - 5;
                            found = true;
                        }
                    }
                }
                if (found) {
                    continue;
                }
                if (ArrayOfSingles.get(i).id.charAt(0) == 'f') {
                    Passenger p1 = new Family();
                    p1.id = ArrayOfSingles.get(i).id;
                    ((Family) p1).members.add(ArrayOfSingles.get(i));
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                } else if (ArrayOfSingles.get(i).id.charAt(0) == 'g') {
                    Passenger p1 = new Group();
                    p1.id = ArrayOfSingles.get(i).id;
                    ((Group) p1).members.add(ArrayOfSingles.get(i));
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                } else if (ArrayOfSingles.get(i).id.contains("s")) {
                    Passenger p1 = new Single();
                    p1.id = ArrayOfSingles.get(i).id;
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                }
            } else {

                if (ArrayOfSingles.get(i).id.charAt(0) == 'f') {
                    Passenger p1 = new Family();
                    p1.id = ArrayOfSingles.get(i).id;
                    ((Family) p1).members.add(ArrayOfSingles.get(i));
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                } else if (ArrayOfSingles.get(i).id.charAt(0) == 'g') {
                    Passenger p1 = new Group();
                    p1.id = ArrayOfSingles.get(i).id;
                    ((Group) p1).members.add(ArrayOfSingles.get(i));
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                } else if (ArrayOfSingles.get(i).id.charAt(0) == 's') {
                    Passenger p1 = new Single();
                    p1.id = ArrayOfSingles.get(i).id;
                    p1.priority += ArrayOfSingles.get(i).priority;
                    Parsed.add(p1);
                }
            }
        }
        /*
        Reads the commands and applies the aforementioned.
         */
        while (input.hasNext()) {
            String command = input.next();
            if (command.equals("insert")) {
                String idCommand = input.next();
                for (i = 0; i < Parsed.size(); i++) {
                    if (idCommand.equals(Parsed.get(i).id)) {
                        heap.insert(Parsed.get(i), Parsed.get(i).priority);
                    }
                }
            } else if (command.equals("list")) {
                if (!input.hasNext()) {
                    heap.list();
                } else {
                    heap.list();
                    System.out.println();
                }
            } else if (command.equals("embark")) {
                heap.embark();
            } else if (command.equals("delete")) {
                String argu = input.nextLine();
                argu = argu.trim();
                if (argu.length() <= 2) {
                    for (i = 0; i < heap.sizeHeap(); i++) {
                        if (argu.equals(heap.sorted.get(i).id)) {
                            heap.delete(heap.sorted.get(i));
                        }
                    }
                } else {
                    String[] result = argu.split(" ");
                    for (i = 0; i < heap.sizeHeap(); i++) {
                        if (result[0].equals(heap.sorted.get(i).id)) {
                            if (result[0].contains("g")) {
                                for (k = 0; k < ((Group) heap.sorted.get(i)).members.size(); k++)
                                {
                                    if (result[1].equals(((Group) heap.sorted.get(i)).members.get(k).name)) {
                                        heap.sorted.get(i).priority -= ((Group) heap.sorted.get(i)).members.get(k).priority;
                                        heap.sorted.get(i).priority += 5;
                                        heap.delete(((Group) heap.sorted.get(i)).members.get(k));
                                    }
                                }
                            } else if(result[0].contains("f")){
                                for (k = 0; k < ((Family) heap.sorted.get(i)).members.size(); k++) {
                                    if (result[1].equals(((Family) heap.sorted.get(i)).members.get(k).name)) {
                                        heap.sorted.get(i).priority -= ((Family) heap.sorted.get(i)).members.get(k).priority;
                                        heap.sorted.get(i).priority += 10;
                                        heap.delete(((Family) heap.sorted.get(i)).members.get(k));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}