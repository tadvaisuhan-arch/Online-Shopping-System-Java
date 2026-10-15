

Skip to content
Using Pimpri Chinchwad College of Engineering Mail with screen readers

7 of 764
java project
Inbox

Suhan Tadavi <suhan.tadavi25@pccoepune.org>
        10:14 AM (10 hours ago)
to me

import java.util.ArrayList;
import java.util.Scanner;

class Product {
    int id;
    String name;
    String category;
    double price;
    int stock;

    Product(int id, String name, String category,
            double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    void display() {
        System.out.printf(
                "%-5d %-25s %-16s Rs.%-9.2f %d%n",
                id, name, category, price, stock
        );
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();
    static int nextId = 101;

    public static void main(String[] args) {
        addSampleProducts();

        int choice = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("       ONLINE SHOPPING SYSTEM");
            System.out.println("========================================");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. Browse by Category");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. View Shopping Cart");
            System.out.println("6. Remove Product from Cart");
            System.out.println("7. Checkout");
            System.out.println("8. Admin - Add New Product");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        searchProduct();
                        break;
                    case 3:
                        browseCategory();
                        break;
                    case 4:
                        addToCart();
                        break;
                    case 5:
                        viewCart();
                        break;
                    case 6:
                        removeFromCart();
                        break;
                    case 7:
                        checkout();
                        break;
                    case 8:
                        adminAddProduct();
                        break;
                    case 9:
                        System.out.println("Thank you for shopping!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }
        } while (choice != 9);

        sc.close();
    }

    // Create 100 sample products
    static void addSampleProducts() {
        String[][] catalog = {
                {"Electronics", "Bluetooth Speaker",
                        "Wireless Headphones", "Smart Watch",
                        "Power Bank", "USB Cable"},

                {"Mobiles", "Samsung Galaxy Phone", "iPhone",
                        "Android Smartphone", "Tablet", "Phone Charger"},

                {"Computers", "Laptop", "Gaming Laptop",
                        "Desktop Computer", "Computer Monitor", "Webcam"},

                {"Accessories", "Laptop Bag", "Mouse Pad",
                        "Wireless Mouse", "Keyboard", "Laptop Stand"},

                {"Gaming", "Gaming Console", "Gaming Headset",
                        "Gaming Keyboard", "Gaming Mouse", "Controller"},

                {"Storage", "USB Flash Drive", "External SSD",
                        "Hard Disk", "Memory Card", "Internal SSD"},

                {"Office", "Printer", "Office Chair",
                        "Desk Lamp", "UPS", "Extension Board"},

                {"Cameras", "DSLR Camera", "Action Camera",
                        "Camera Lens", "Tripod", "Ring Light"},

                {"Networking", "WiFi Router", "Network Cable",
                        "WiFi Adapter", "Network Switch", "Repeater"},

                {"Components", "Processor", "Motherboard",
                        "Graphics Card", "Computer RAM", "CPU Cooler"}
        };

        double[] basePrices = {
                999, 14999, 2999, 1299, 299,
                15999, 49999, 19999, 14999, 999,
                45999, 69999, 39999, 8999, 2499,
                599, 1499, 799, 499, 999,
                29999, 2499, 3499, 1999, 1299,
                799, 2499, 3999, 1999, 5999,
                899, 4999, 2499, 1999, 1499,
                49999, 8999, 6999, 2499, 1499,
                1999, 399, 1499, 5999, 999,
                15999, 499, 899, 1999, 1499
        };

        for (int i = 0; i < 100; i++) {
            int categoryIndex = i / 10;
            int productIndex = i % 5;
            int variation = i / 50;

            String category = catalog[categoryIndex][0];
            String name = catalog[categoryIndex][1 + productIndex];

            if (variation == 1) {
                name = "Premium " + name;
            }

            double price = basePrices[i % 50]
                    + (variation * 500);
            int stock = 5 + (i * 7 % 46);

            addProduct(nextId, name, category, price, stock);
        }
    }

    // Add a product to the catalog
    static void addProduct(int id, String name,
                           String category, double price, int stock) {
        products.add(new Product(
                id, name, category, price, stock
        ));

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    static void printHeader() {
        System.out.println("\n==============================================================");
        System.out.println("                    ONLINE SHOPPING STORE");
        System.out.println("==============================================================");
        System.out.printf("%-5s %-25s %-16s %-12s %s%n",
                "ID", "PRODUCT NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println("--------------------------------------------------------------");
    }

    static void viewProducts() {
        printHeader();

        for (Product p : products) {
            p.display();
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Total products: " + products.size());
    }

    static Product findProduct(int id) {
        for (Product p : products) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    static void searchProduct() {
        System.out.print("Enter product ID: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            Product p = findProduct(id);

            if (p != null) {
                printHeader();
                p.display();
            } else {
                System.out.println("Product not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid product ID.");
        }
    }

    static void browseCategory() {
        System.out.println("\nAvailable Categories:");
        System.out.println("Electronics, Mobiles, Computers, Accessories");
        System.out.println("Gaming, Storage, Office, Cameras");
        System.out.println("Networking, Components");

        System.out.print("Enter category name: ");
        String category = sc.nextLine().trim();
        boolean found = false;

        printHeader();

        for (Product p : products) {
            if (p.category.equalsIgnoreCase(category)) {
                p.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found in this category.");
        }
    }

    static void addToCart() {
        System.out.print("Enter product ID: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            Product p = findProduct(id);

            if (p == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }

            CartItem existing = null;

            for (CartItem item : cart) {
                if (item.product.id == id) {
                    existing = item;
                    break;
                }
            }

            int alreadyInCart =
                    (existing == null) ? 0 : existing.quantity;

            if (quantity + alreadyInCart > p.stock) {
                System.out.println("Not enough stock available.");
                return;
            }

            if (existing == null) {
                cart.add(new CartItem(p, quantity));
            } else {
                existing.quantity += quantity;
            }

            System.out.println("Product added to cart successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Enter valid numeric values.");
        }
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("\n================ YOUR SHOPPING CART ================");
        double total = 0;

        for (CartItem item : cart) {
            double amount = item.getTotal();

            System.out.printf(
                    "ID: %d | %s | Qty: %d | Rs.%.2f%n",
                    item.product.id, item.product.name,
                    item.quantity, amount
            );

            total += amount;
        }

        System.out.println("----------------------------------------------------");
        System.out.printf("Grand Total: Rs.%.2f%n", total);
    }

    static void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        viewCart();
        System.out.print("Enter product ID to remove: ");

        try {
            int id = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).product.id == id) {
                    cart.remove(i);
                    System.out.println("Product removed from cart.");
                    return;
                }
            }

            System.out.println("Product not found in cart.");

        } catch (NumberFormatException e) {
            System.out.println("Enter a valid product ID.");
        }
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (CartItem item : cart) {
            if (item.quantity > item.product.stock) {
                System.out.println("Insufficient stock for "
                        + item.product.name);
                return;
            }
        }

        viewCart();

        System.out.println("\n----- PAYMENT OPTIONS -----");
        System.out.println("1. UPI (Demo)");
        System.out.println("2. Card (Demo)");
        System.out.println("3. Cash on Delivery");
        System.out.print("Select payment method: ");

        try {
            int payment = Integer.parseInt(sc.nextLine());

            if (payment < 1 || payment > 3) {
                System.out.println("Invalid payment option.");
                return;
            }

            double total = 0;

            for (CartItem item : cart) {
                total += item.getTotal();
            }

            System.out.print("Confirm order? (yes/no): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Order cancelled.");
                return;
            }

            for (CartItem item : cart) {
                item.product.stock -= item.quantity;
            }

            System.out.println("\n========== ORDER CONFIRMED ==========");

            if (payment == 1) {
                System.out.println("Payment method: UPI (Demo)");
            } else if (payment == 2) {
                System.out.println("Payment method: Card (Demo)");
            } else {
                System.out.println("Payment method: Cash on Delivery");
            }

            System.out.printf("Final Amount: Rs.%.2f%n", total);
            System.out.println("Thank you for shopping!");

            cart.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Order not placed.");
        }
    }

    static void adminAddProduct() {
        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        if (!password.equals("admin123")) {
            System.out.println("Access denied.");
            return;
        }

        try {
            System.out.print("Enter product name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Product name cannot be empty.");
                return;
            }

            System.out.print("Enter category: ");
            String category = sc.nextLine().trim();

            if (category.isEmpty()) {
                System.out.println("Category cannot be empty.");
                return;
            }

            System.out.print("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Enter stock quantity: ");
            int stock = Integer.parseInt(sc.nextLine());

            if (price <= 0 || stock < 0) {
                System.out.println("Invalid price or stock.");
                return;
            }

            int assignedId = nextId;
            addProduct(assignedId, name, category, price, stock);

            System.out.println("Product added successfully!");
            System.out.println("Assigned Product ID: " + assignedId);

        } catch (NumberFormatException e) {
            System.out.println("Enter valid price and stock values.");
        }
    }
}
