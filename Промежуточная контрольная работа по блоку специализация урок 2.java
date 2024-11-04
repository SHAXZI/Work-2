import java.util.*;

class Toy {
    private String name;
    private int weight;

    public Toy(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

class ToyRaffle {
    private List<Toy> toys;

    public ToyRaffle() {
        this.toys = new ArrayList<>();
    }

    public void addToy(String name, int weight) {
        Toy newToy = new Toy(name, weight);
        toys.add(newToy);
        System.out.println("Добавлена игрушка: " + name + " с весом " + weight);
    }

    public Toy drawToy() {
        int totalWeight = toys.stream().mapToInt(Toy::getWeight).sum();
        int randomWeight = new Random().nextInt(totalWeight);
        
        int cumulativeWeight = 0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomWeight < cumulativeWeight) {
                return toy;
            }
        }
        return null; // Это не должно произойти
    }
}

public class ToyRaffleApp {
    public static void main(String[] args) {
        ToyRaffle toyRaffle = new ToyRaffle();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Добро пожаловать в магазин игрушек!");

        while (true) {
            System.out.println("Введите команду (add, draw, exit):");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("add")) {
                System.out.println("Введите название игрушки:");
                String name = scanner.nextLine();
                System.out.println("Введите вес игрушки:");
                int weight = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера
                toyRaffle.addToy(name, weight);
            } else if (command.equalsIgnoreCase("draw")) {
                Toy drawnToy = toyRaffle.drawToy();
                System.out.println("Выпала игрушка: " + drawnToy.getName());
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Выход из приложения.");
                break;
            } else {
                System.out.println("Неизвестная команда. Пожалуйста, попробуйте снова.");
            }
        }

        scanner.close();
    }
}