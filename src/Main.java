import java.util.Random;

public class Main {
    public static int bossHealth = 1700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {300, 280, 250, 400};
    public static int[] heroesDamage = {30, 25, 20, 0, };
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        showStatistics();
        System.out.print("\n");
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void healing() {
        int medicHeall = 100;
        int skipMedic = 3;

        for (int i = 0; i < heroesDamage.length; i++) {
            if (i == skipMedic) {
                continue;
            }

            if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + medicHeall;
                System.out.println(heroesAttackType[i] + " healing " + heroesHealth[i]);
                break;
            }
        }
    }


    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttacks();
        heroesAttack();


        System.out.print("\n");
        showStatistics();
        System.out.print("\n");

        if (heroesHealth[3] > 0) {
            healing();
        }

    }
    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }




    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coefficient = random.nextInt(8) + 2; // 2, 3, 4, 5, 6, 7, 8, 9
                    damage = heroesDamage[i] * coefficient;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }




    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");

        System.out.println("Boss health: " + bossHealth + " damage: "
                + bossDamage + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
}
