
package model;


public class Car {
    private String id;
    private String brandId;
    private String color;
    private String frameId;
    private String engineId;

    public Car() {
    }

    public Car(String id, String brandId, String color, String frameId, String engineId) {
        this.id = id;
        this.brandId = brandId;
        this.color = color;
        this.frameId = frameId;
        this.engineId = engineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getEngineId() {
        return engineId;
    }

    public void setEngineId(String engineId) {
        this.engineId = engineId;
    }
    
    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s,%s", 
                id, brandId, color, frameId, engineId);
    }
}
