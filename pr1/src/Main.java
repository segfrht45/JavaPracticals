import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> catalog = new ArrayList<>();
        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Cart cart = new Cart();
        Order order = null;

        while (true) {
            System.out.println("\n=== ІНТЕРНЕТ-МАГАЗИН ===");
            System.out.println("1. Переглянути каталог товарів");
            System.out.println("2. Додати товар до кошика");
            System.out.println("3. Переглянути кошик");
            System.out.println("4. Зробити замовлення");
            System.out.println("0. Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Каталог товарів ---");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("\nВведіть ID товару для додавання до кошика (1-3): ");
                    int id = scanner.nextInt();
                    if (id == 1) {
                        cart.addProduct(product1);
                        System.out.println("Товар додано до кошика!");
                    } else if (id == 2) {
                        cart.addProduct(product2);
                        System.out.println("Товар додано до кошика!");
                    } else if (id == 3) {
                        cart.addProduct(product3);
                        System.out.println("Товар додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Ваш кошик ---");
                    System.out.println(cart);
                    break;

                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("\nКошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        order = new Order(cart);
                        System.out.println("\nЗамовлення оформлено!");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}

