import java.util.List;

public class Order {
    private List<Product> products;
    private double totalPrice;
    private String status;

    public Order(Cart cart) {
        this.products = cart.getProducts();
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }

    public List<Product> getProducts() { return products; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Замовлення містить:\n");
        for (Product product : products) {
            sb.append(" - ").append(product.toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(totalPrice).append(" грн\n");
        sb.append("Статус: ").append(status);
        return sb.toString();
    }
}
