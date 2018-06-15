package dto;

import java.util.List;

public class BookDTO {
    public Integer id;
    public String title;
    public String description;
    public Double price;
    public List<String> authorsId;
    public List<String> genresId;

    public BookDTO() {
    }

    public BookDTO(Integer id) {
        this.id = id;
    }

    public BookDTO(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public BookDTO(Integer id, String title, String description, Double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public BookDTO(String title, String description, Double price, List<String> authorsId, List<String> genresId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.authorsId = authorsId;
        this.genresId = genresId;
    }


}
