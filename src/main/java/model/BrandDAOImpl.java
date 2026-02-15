package model;

import exception.FileOperationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAOImpl implements BrandDAO {

    private List<Brand> brands = new ArrayList<>();
    private static final String FILE_PATH = "brands.txt";

    @Override
    public List<Brand> getAllBrands() {
        return new ArrayList<>(brands);
    }

    /*
        Thêm Brand mới: B1: B id = findId()
                        B2: Check id isNull?
     */
    @Override
    public boolean addBrand(Brand brand) {
        Brand brandId = findBrandByID(brand.getId());
        if (brandId == null) {
            brands.add(brand);
            return true;
        }

        return false;

    }

    /*
        Tìm brand theo ID: B1 Loop theo brands
                           B2:
     */
    @Override
    public Brand findBrandByID(String brandId) {
        for (Brand b : brands) {
            if (b.getId().trim().equalsIgnoreCase(brandId)) {
                return b;
            }
        }
        return null;
    }

    /*
        Cập nhập Brand theo id:
        1. Tìm Brand hiện có qua findBrandByID(brandId).
        2. Nếu không tìm thấy (existing == null), trả về false.
        3. Chuyển đổi giá mới thành double để kiểm tra tính hợp lệ. convertPriceToDouble
        4. != null và !isEmpty {Name, sound} và price {!null và >0}
              existing.setName(brand.getName());
        5. true
     */
    @Override
    public boolean updateBrandById(String brandId, Brand brand) {

        Brand existing = findBrandByID(brandId);
        double newPrice = convertPriceToDouble(brand.getPrice());
        if (existing == null) {
            return false;
        }

        if (brand.getName() != null && !brand.getName().isEmpty()) {
            existing.setName(brand.getName());
        }
        if (brand.getSound() != null && !brand.getSound().isEmpty()) {
            existing.setSound(brand.getSound());
        }
        if (brand.getPrice() != null && newPrice > 0) {
            existing.setPrice(brand.getPrice());
        }

        return false;
    }

    /*
    B1: Tạo một List mới
    B2: Loop của brands
            String của newPrice =  replace từ "," thành ""
            ép kiểu thành double  D.pase
            Nếu <= 
                thêm vào list mới tạo 
     */
    @Override
    public List<Brand> listBrandsByMaxPrice(double maxPrice) {
        List<Brand> result = new ArrayList<>();
        for (Brand b : brands) {
            String priceStr = b.getPrice().replace(",", "");
            double brandPrice = Double.parseDouble(priceStr);
            if (brandPrice <= maxPrice) {
                result.add(b);
            }

        }
        return result;
    }

    @Override
    public boolean saveToFile() throws FileOperationException {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Brand b : brands) {
                bw.write(b.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new FileOperationException("Failed to save data to file: " + FILE_PATH, e);
        }
    }

    /*
    B1: Tạo File mới nhận FILE_PATH 
        Nếu !file đó .exist --> brands sẽ tạo một arraylist mới 
                                cuối cùng trả về false
                                
    B2:
        try(nhận tài nguyên : bufR br = (FileRead(file của B1) {
            brands sẽ tạo một arraylist mới 
            Tạo 1 string line
            Loop(While) ((line nhân = br sẽ readLine) is not null ) 
                Tạo một cái mảng String[] splitColon rồi line sẽ split tại ":" 
                --[  B5-99, BMW X5 xDrive40i XLine (2020), Sony  ]  :       [18B]
                Nếu (splitColon có .length is not = 2) --> continue
                
                Tiếp theo, splitColon[0] : brandInfo
                            splitColon[1] : priceStr
    
                priceStr sẽ replace("B" sang "")
    
                tương tự brandInfo : String[]
                Kiểm tra độ dài < 3 ko, nếu đúng thì continue
                id, name, sound
                
                kiểm tra id có bị rỗng ko, nếu đúng continue
                
                brands add (new Brand(thông tin vô))
    
            true
        }catch - throw (IOException | NumberFormatException e) 
           
     */
    @Override
    public boolean loadFromFile() throws FileOperationException {

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            brands = new ArrayList<>();
            return false;
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            brands = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitColon = line.split(":");
                if (splitColon.length != 2) {
                    continue;
                }

                String brandInfo = splitColon[0].trim();
                String priceStr = splitColon[1].trim();

                priceStr = priceStr.replace("B", "");

                String[] brandParts = brandInfo.split(",");
                if (brandParts.length < 3) {
                    continue;
                }

                String id = brandParts[0].trim();
                String name = brandParts[1].trim();
                String sound = brandParts[2].trim();

                if (id.isEmpty()) {
                    continue;
                }

                brands.add(new Brand(id, name, sound, priceStr));
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            throw new FileOperationException("Failed to load data from file: " + FILE_PATH, e);
        }
    }

    /*
        
     */
    public double convertPriceToDouble(String priceStr) throws NumberFormatException {
        priceStr = priceStr.replace("B", "").trim();
        priceStr = priceStr.replace(".", "").replace(",", "");
        return Double.parseDouble(priceStr);
    }

}
