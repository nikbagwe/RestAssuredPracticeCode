package LombokPOJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


    public class ProductLombok {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private ProductLombok.RatingLombok rating;

@Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class RatingLombok
    {
        private double rate;
        private int count;
    }
}
