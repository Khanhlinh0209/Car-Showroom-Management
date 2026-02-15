package service;

import constant.MessageConstants;
import exception.FileOperationException;
import model.Car;
import model.CarDAO;
import java.util.List;
import model.Brand;
import model.BrandDAO;
import view.ShowRoomView;

public class CarService {

    private final CarDAO carDAO;
    private final BrandDAO brandDAO;

    private final ShowRoomView view;

    public CarService(CarDAO carDAO, BrandDAO brandDAO, ShowRoomView view) {

        this.carDAO = carDAO;
        this.brandDAO = brandDAO;
        this.view = view;
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    /*
        Thêm một xe mới:
        1. Vòng lặp while(true) cho phép nhập lại nếu cần.
            2. Lấy ID từ view và kiểm tra tính duy nhất qua getUniqueId().
            3. Nếu ID không hợp lệ/đã tồn tại (id == null), hiển thị thông báo và thoát.
            4. Lấy danh sách Listb Brands (qua brandDAO.getAllBrands()).
            5. Lấy thông tin Car còn lại từ view.getCarInput(id, brands).
            6. Gọi carDAO.addCar(car) để thêm xe. = boolean
            7. Hiển thị thông báo kết quả (thành công/thất bại).
            break
     */
    public void addCar() {
        while (true) {
            String id = getUniqueId(view.getCarIdInput());
            if (id == null) {
                view.displayMessage(MessageConstants.CAR_ID_EXISTS);
                break;
            }
            List<Brand> brands = brandDAO.getAllBrands();
            Car car = view.getCarInput(id, brands);
            boolean success = carDAO.addCar(car);
            view.displayMessage(success ? MessageConstants.CAR_ADDED_SUCCESS
                    : MessageConstants.FAILED_TO_ADD_CAR);
            break;
        }
    }

    /*
        Xóa một xe theo ID:
        1. Lấy ID cần xóa từ view.getCarIdInput().
        2. Gọi carDAO.findCarById(id) để kiểm tra sự tồn tại của xe.
        3. Nếu xe không tồn tại (carId == null), hiển thị thông báo CAR_NOT_FOUND. -> return
        4. Hỏi người dùng xác nhận xóa qua view.getConfirmation().
        5. Nếu người dùng comfirm .equalsIgnoreCase("y"), gọi carDAO.deleteCarById(id) = boolean để xóa.
        6. Hiển thị thông báo kết quả (thành công/thất bại).
     */
    public void deleteCarById() {
        String id = view.getCarIdInput();
        Car carId = carDAO.findCarById(id);
        if (carId == null) {
            view.displayMessage(MessageConstants.CAR_NOT_FOUND);
            return;
        }
        String comfirm = view.getConfirmation(MessageConstants.DELETE_CONFIRMATION);
        if (comfirm.equalsIgnoreCase("y")) {
            boolean success = carDAO.deleteCarById(id);
            view.displayMessage(success ? MessageConstants.CAR_DELETED_SUCCESS
                    : MessageConstants.CAR_DELETE_FAILED);
        }
    }

    /*
        Cập nhật thông tin xe theo ID:
        1. Lấy ID cần cập nhật từ view.getCarIdInput().
        2. Gọi carDAO.findCarById(id) để kiểm tra sự tồn tại của xe.
        3. Nếu xe không tồn tại (car == null), hiển thị thông báo CAR_NOT_FOUND. -> return
        4. Gọi view.getUpdatedCarInput() để lấy thông tin mới.
        5. Gọi carDAO.updateCarById(id, updateCar) để thực hiện cập nhật.
        6. Hiển thị thông báo kết quả (thành công/thất bại).
     */
    public void updateCarById() {
        String id = view.getCarIdInput().trim();
        Car car = carDAO.findCarById(id);
        if (car == null) {
            view.displayMessage(MessageConstants.CAR_NOT_FOUND);
            return;
        }
        Car updateCar = view.getUpdatedCarInput(id, car);
        boolean success = carDAO.updateCarById(id, updateCar);
        view.displayMessage(success ? MessageConstants.CAR_UPDATED_SUCCESS
                : MessageConstants.CAR_UPDATE_FAILED);

    }

    /*
        Tìm kiếm xe theo tên Brand:
        1. Lấy tên Brand cần tìm từ view.getBrandNameInput().
        2. Gọi carDAO.findByBrandName(brandName) để lấy danh sách xe.
        3. Gọi view.displayCars(listCars) để hiển thị danh sách kết quả.
     */

    public void findByBrandName() {
        String brandName = view.getBrandNameInput().trim();
        List<Car> listCars = carDAO.findByBrandName(brandName);
        view.displayCars(listCars);

    }

    /*
        Lọc và hiển thị xe theo màu sắc:
        1. Lấy màu sắc cần lọc từ view.getColorInput().
        2. Gọi carDAO.listCarsByColor(color) để lấy danh sách xe.
        3. Nếu danh sách rỗng (listCar.isEmpty()), hiển thị thông báo CAR_NOT_FOUND.
        4. Gọi view.displayCars(listCar) để hiển thị danh sách kết quả.
     */
    public void listCarsByColor() {
        String color = view.getColorInput().trim();
        List<Car> listCar = carDAO.listCarsByColor(color);
        if (listCar.isEmpty()) {
            view.displayMessage(MessageConstants.CAR_NOT_FOUND);
        }
        view.displayCars(listCar);
    }

    /*
        Sắp xếp và hiển thị xe theo tên Brand tăng dần:
        1. Gọi carDAO.listCarsByBrandNameAsc() để lấy danh sách xe đã được sắp xếp.
        2. Nếu danh sách rỗng (listCars.isEmpty()), hiển thị thông báo CAR_NOT_FOUND.
        3. Gọi view.displayCars(listCars) để hiển thị danh sách kết quả.
     */
    public void listCarsByBrandNameAsc() {
        List<Car> listCars = carDAO.listCarsByBrandNameAsc();

        if (listCars.isEmpty()) {
            view.displayMessage(MessageConstants.CAR_NOT_FOUND);
        }
        view.displayCars(listCars);
    }

    /*
        Kiểm tra tính duy nhất và hợp lệ của ID xe:
        1. Nếu ID rỗng sau khi trim(), trả về null (báo hiệu lỗi ID không hợp lệ).
        2. Gọi carDAO.findCarById(id) để kiểm tra ID đã tồn tại.
        3. Nếu ID đã tồn tại, trả về null (báo hiệu lỗi ID đã tồn tại).
        4. Nếu hợp lệ và duy nhất, trả về ID đó.
     */
    private String getUniqueId(String id) {
        if (id.trim().isEmpty()) {
            return null;
        }
        if (carDAO.findCarById(id) != null) {
            return null;
        }
        return id;
    }

    public boolean saveToFile() throws FileOperationException {
        return carDAO.saveToFile();
    }

}
