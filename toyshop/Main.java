package toyshop;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

        Toy toy1 = new Toy("1 2 конструктор");
        Toy toy2 = new Toy("2 2 робот");
        Toy toy3 = new Toy("3 6 кукла");

        System.out.println(toy1.getIdToy());
        System.out.println(toy1.getDropToy());
        System.out.println(toy1.getNameToy());

        System.out.println(toy3.toString());

    }
}
