package project2dana.model;

public abstract class Food {

    private String name;
    private String category;
    private String price;
    private String tmp = null;

    public Food(String name, String category, String price) {
        this.name = name;
        this.category = category;
        this.price = price;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getDrink() {
        if ("Drink".equals(category)) {
            return name;
        }
        else{
            return "";
        }

    }

    public String getMainMeal() {
        if ("Main course".equals(category)) {
            return name;
        }
        else{
            return "";
        }

    }

    public String getAppetizer() {
        if ("Appetizer".equals(category)) {
            return name;
        }
        else{
            return "";
        }

    }

    public String getDessert() {
        if ("Dessert".equals(category)) {
            return name;
        }
        else{
            return "";
        }

    }

    public String getExtras() {
        if ("Extras".equals(category)) {
            return name;
        }
        else{
            return "";
        }

    }


}
