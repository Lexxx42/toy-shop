package toyshop;

import java.util.Random;

public class Lottery {
    private final int[] items = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final Random random = new Random();

    private int chooseRandom() {
        int randomIndex = random.nextInt(items.length);
        return items[randomIndex];
    }

    protected boolean lottery(int num) {
        int choice = this.chooseRandom();
        return choice <= num;
    }
}
