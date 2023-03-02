package toyshop;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.CSVtoArray();
        controller.addToy();
        controller.run();
    }
}
