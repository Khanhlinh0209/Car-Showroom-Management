package model;

import exception.FileOperationException;
import java.util.List;

public interface CarDAO {

    List<Car> getAllCars();

    boolean addCar(Car car);

    List<Car> findByBrandName(String carName);
    
    Car findCarById(String carId);

    boolean updateCarById(String carId, Car car);

    boolean deleteCarById(String carId);

    List<Car> listCarsByColor(String color);
    
    List<Car> listCarsByBrandNameAsc();

    boolean saveToFile() throws FileOperationException;

    boolean loadFromFile() throws FileOperationException;
}
