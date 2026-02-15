package model;

import exception.FileOperationException;
import helper.InputValidator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private List<Car> cars = new ArrayList<>();
    private BrandDAO brandDAO;
    private InputValidator inputValidator;
    private static final String FILE_PATH = "cars.txt";

    public CarDAOImpl(BrandDAO brandDao, InputValidator inputValidator) {
        this.brandDAO = brandDao;
        this.inputValidator = inputValidator;
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    /*
        Thêm car
    b1: check id của car  f((c.id))) is not null) 
        check id của brand(Dao.fId...)
        check color : null và trim.empty
        
        check ! validator: EngineId và FrameId
        cars add car
        
     */
    @Override
    public boolean addCar(Car car) {
        if (findCarById((car.getId())) != null) {
            return false;
        }

        if (brandDAO.findBrandByID(car.getBrandId()) == null) {
            return false;
        }

        if (car.getColor() == null || car.getColor().trim().isEmpty()) {
            return false;
        }

        if (!inputValidator.isValidEngineId(car.getEngineId())) {
            return false;
        }

        if (!inputValidator.isValidFrameId(car.getFrameId())) {
            return false;
        }

        cars.add(car);
        return true;
    }

    /*
        Cập nhập car
    
    b1: check id có tồn tại không
    b2:  
     */
    @Override
    public boolean updateCarById(String carId, Car car) {

        Car existing = findCarById(carId);
        if (existing == null) {
            return false;
        }
        String newColor = car.getColor();

        if (newColor != null && !newColor.trim().isEmpty()) {
            existing.setColor(newColor);
        }

        if (car.getFrameId() != null && !car.getFrameId().trim().isEmpty()) {
            existing.setFrameId(car.getFrameId());
        }

        if (car.getEngineId() != null && !car.getEngineId().trim().isEmpty()) {
            existing.setEngineId(car.getEngineId());
        }

        return true;
    }

    /*
        Tìm tên Brand
    B1: Tạo List để lưu car
    
    B2: Nếu Name của brand == null or isEmpty không -> list
    
    B3 : String = name của brand nhận  . lower. trim
    
    B4: Loop  Car của cars {
        Brand brand = tìm findBrandId(car.getBId)
        nếu brand trên is not null và   b.getName chuyển sang lower contains(String ở bước 3)
        listCars. add   
    }
     */
    @Override
    public List<Car> findByBrandName(String brandName) {
        List<Car> listCars = new ArrayList<>();

        if (brandName == null || brandName.trim().isEmpty()) {
            return listCars;
        }

        String lowerBrandName = brandName.toLowerCase().trim();

        for (Car c : cars) {
            Brand brand = brandDAO.findBrandByID(c.getBrandId());
            if (brand != null && brand.getName().toLowerCase().contains(lowerBrandName)) {
                listCars.add(c);
            }
        }

        return listCars;
    }

    /*
        Xóa car:
    B1:  Car existing  findId
    B2: Nếu ex khác null {
        cars remove  -> true
    }
     */
    @Override
    public boolean deleteCarById(String carId) {
        Car existing = findCarById(carId);
        if (existing != null) {
            cars.remove(existing);
            return true;
        }
        return false;
    }

    @Override
    public List<Car> listCarsByColor(String color) {

        List<Car> listCarByColor = new ArrayList<>();

        String newColor = color.trim();

        for (Car car : cars) {
            if (car.getColor() != null && car.getColor().trim().equalsIgnoreCase(newColor)) {
                listCarByColor.add(car);
            }

        }
        return listCarByColor;
    }

    /*
        try - tài nguyên cần dùng: bufw bw = (new FileWriter(path) 
            loop của cars
                   bw sẽ .write (chuyển car thành toString)
                   __    .newLine mới
        
            --> cuối cùng trả ra true 
        catch -> throw
     */
    @Override
    public boolean saveToFile() throws FileOperationException {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Car c : cars) {
                bw.write(c.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new FileOperationException("Failed to save data to file: " + FILE_PATH, e);
        }
    }

    /*
        B1: Tạo File mới nhận FILE_PATH 
        Nếu !file đó .exist --> cars sẽ tạo một arraylist mới 
                                cuối cùng trả về false
        
        B2:  cars sẽ tạo một arraylist mới 
             
        B3: try (bufr br = (FileReader(file lục tạo ở b1)
                line : String
                Loop(While) ((line nhân = br sẽ readLine) is not null ) 
                    parts : String[] nhận line split ","
                    Nếu parts is not = 5 --> continue
                    
                    carId, brandId, color, frameId, enineId
                    cars add(new car(thông tin))
                true
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean loadFromFile() throws FileOperationException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            cars = new ArrayList<>();
            return false;
        }

        cars = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    continue;
                }
                String carId = parts[0].trim();
                String brandId = parts[1].trim();
                String color = parts[2].trim();
                String frameId = parts[3].trim();
                String engineId = parts[4].trim();
                cars.add(new Car(carId, brandId, color, frameId, engineId));
            }
            return true;
        } catch (IOException e) {
            throw new FileOperationException("Failed to load data from file: " + FILE_PATH, e);
        }
    }

    @Override
    public Car findCarById(String carId) {
        for (Car c : cars) {
            if (c.getId().trim().equalsIgnoreCase(carId)) {
                return c;
            }
        }
        return null;
    }

    /*
    Sắp xếp danh sách xe (List<Car>) theo tên Brand tăng dần.
    Quy tắc sắp xếp:
    1. Sắp xếp chính: Tên Brand (Brand Name) theo thứ tự Alphabet TĂNG DẦN (Ascending).
    2. Sắp xếp phụ (khi tên Brand giống nhau): Giá Brand (Brand Price) GIẢM DẦN (Descending).
    
    Các bước thực hiện:
    1. Tạo một List mới (sortedCars) từ List cars hiện có.
    2. Sử dụng Collections.sort() với một Comparator tùy chỉnh.
    3. Trong Comparator:
       - Tìm Brand(b1, b2) của o1 và o2 qua brandDAO.findBrandByID().
       - Tạo int cmp  --> So sánh tên Brand (cmp).
       - Nếu cmp == 0 (tên Brand giống nhau), chuyển giá Brand sang double và so sánh ngược (price2, price1) ((BrandDAOImpl) brandDAO). để sắp xếp GIẢM DẦN.
       - Trả về cmp.
     */
    @Override
    public List<Car> listCarsByBrandNameAsc() {

        List<Car> sortedCars = new ArrayList<>(cars);

        Collections.sort(sortedCars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                Brand b1 = brandDAO.findBrandByID(o1.getBrandId());
                Brand b2 = brandDAO.findBrandByID(o2.getBrandId());

                int cmp = b1.getName().compareToIgnoreCase(b2.getName());
                if (cmp == 0) {
                    double price1 = ((BrandDAOImpl) brandDAO).convertPriceToDouble(b1.getPrice());
                    double price2 = ((BrandDAOImpl) brandDAO).convertPriceToDouble(b2.getPrice());
                    return Double.compare(price2, price1);
                }
                return cmp;
            }
        });
        return sortedCars;
    }

}
