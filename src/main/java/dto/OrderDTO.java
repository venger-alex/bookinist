package dto;

public class OrderDTO {
    public String firstName;
    public String lastName;
    public String address;
    public Integer quantity;
    public Integer book_id;

    public OrderDTO() {
    }

    public OrderDTO(String firstName, String lastName, String address, Integer quantity, Integer book_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.quantity = quantity;
        this.book_id = book_id;
    }
}
