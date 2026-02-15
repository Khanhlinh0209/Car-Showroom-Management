package model;

public class Brand {

    private final String id;
    private String name;
    private String sound;
    private String price;

    public Brand(String id, String name, String sound, String price) {
        this.id = id;
        this.name = name;
        this.sound = sound;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s: %sB",
                this.id,
                this.name,
                this.sound,
                this.price);
    }

}
