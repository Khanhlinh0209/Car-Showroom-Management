package model;

import exception.FileOperationException;
import java.util.List;

public interface BrandDAO {

    List<Brand> getAllBrands();

    boolean addBrand(Brand brand);

    Brand findBrandByID(String brandId);

    boolean updateBrandById(String brandId, Brand brand);

    List<Brand> listBrandsByMaxPrice(double maxPrice);

    boolean saveToFile() throws FileOperationException;
    
    boolean loadFromFile() throws FileOperationException;
}
