package service;

import constant.MessageConstants;
import exception.DuplicateException;
import exception.FileOperationException;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.BrandDAO;
import view.ShowRoomView;

public class BrandService {

    private final BrandDAO brandDAO;
    private final ShowRoomView view;

    public BrandService(BrandDAO brandDAO, ShowRoomView view) {
        this.brandDAO = brandDAO;
        this.view = view;
    }

    public List<Brand> getAllBrands() {
        return brandDAO.getAllBrands();
    }

    /*
        Vòng lặp while (true) try {
            Gọi view.getBrandIdInput() để lấy ID từ người dùng, sau đó gọi getUniqueId() để kiểm tra tính hợp lệ và duy nhất của ID.
            Gọi view.getBrandInput(id) để lấy các thông tin còn lại của Brand 
            Gọi brandDAO.addBrand(brand) để lưu dữ liệu = boolean success
            Sử dụng view.displayMessage() để thông báo kết quả (thành công hay thất bại).
            break
    }
        Xử lý các ngoại lệ DuplicateException và InvalidInputException 
     */
    public void addBrand() {
        while (true) {
            try {
                String id = getUniqueId(view.getBrandIdInput()).trim();
                Brand brand = view.getBrandInput(id);
                boolean success = brandDAO.addBrand(brand);
                view.displayMessage(success ? MessageConstants.BRAND_ADDED_SUCCESS
                        : MessageConstants.FAILED_TO_ADD_BRAND);
                break;
            } catch (DuplicateException | InvalidInputException e) {
                view.displayMessage(e.getMessage());

            }
        }
    }

    /*
        Lấy ID cần tìm qua view.getBrandIdInput().
        Gọi brandDAO.findBrandByID(id).
        Nếu không tìm thấy (brand == null), hiển thị thông báo BRAND_NOT_FOUND. -> return
        Nếu tìm thấy, tạo một List chứa Brand rồi add vô List đó và gọi view.displayBrands() để hiển thị. 
     */
    public void findBrandByID() {
        String id = view.getBrandIdInput().trim();
        Brand brand = brandDAO.findBrandByID(id);

        if (brand == null) {
            view.displayMessage(MessageConstants.BRAND_NOT_FOUND);
            return;
        }
        List<Brand> listBrands = new ArrayList<>();
        listBrands.add(brand);
        view.displayBrands(listBrands);

    }

    /*
        //Lấy ID cần cập nhật 
        //Gọi brandDAO.findBrandByID(id) để kiểm tra sự tồn tại của Brand.  Nếu không tồn tại, hiển thị BRAND_NOT_FOUND. --> return
    
        Nếu tồn tại, gọi view.getUpdatedBrandInput(id, existing) để nhận thông tin cập nhật mới.
    
        Gọi brandDAO.updateBrandById(id, update) để thực hiện cập nhật. = boolean 
        Hiển thị thông báo kết quả (thành công hoặc thất bại).
     */
    public void updateBrandById() {

        String id = view.getBrandIdInput().trim();
        Brand existing = brandDAO.findBrandByID(id);
        if (existing == null) {
            view.displayMessage(MessageConstants.BRAND_NOT_FOUND);
            return;
        }
        Brand update = view.getUpdatedBrandInput(id, existing);
        boolean success = brandDAO.updateBrandById(id, update);
        view.displayMessage(success ? MessageConstants.BRAND_UPDATED_SUCCESS
                : MessageConstants.BRAND_UPDATE_FAILED);
    }

    /*
        Lấy mức giá tối đa từ người dùng qua view.getMaxPriceInput().
        Gọi brandDAO.listBrandsByMaxPrice(maxPrice) để lấy danh sách. List
        Nếu danh sách rỗng, hiển thị thông báo không có thương hiệu nào thỏa mãn.-> return
        Ngược lại, gọi view.displayBrands() để hiển thị danh sách kết quả.f
     */
    public void listBrandsByMaxPrice() {

        double maxPrice = view.getMaxPriceInput();
        List<Brand> listBrands = brandDAO.listBrandsByMaxPrice(maxPrice);

        if (listBrands.isEmpty()) {
            view.displayMessage(MessageConstants.NO_BRAND_UNDER_PRICE + maxPrice);
            return;
        }
        view.displayBrands(listBrands);
    }
    
    /*
        Kiểm tra ID có rỗng không (id.trim().isEmpty()). Nếu có, throw ra InvalidInputException.
        Gọi brandDAO.findBrandByID(id). Nếu ID đã tồn tại, ném ra DuplicateException.
        Nếu ID hợp lệ và duy nhất, trả về ID đã được cắt khoảng trắng (trim()).
    */

    private String getUniqueId(String id) throws DuplicateException, InvalidInputException {
        if (id.trim().isEmpty()) {
            throw new InvalidInputException(MessageConstants.ID_CANNOT_BE_EMPTY);
        }
        id = id.trim();
        if (brandDAO.findBrandByID(id) != null) {
            throw new DuplicateException(MessageConstants.ID_ALREADY_EXISTS);
        }
        return id;
    }

    public boolean saveToFile() throws FileOperationException {
        return brandDAO.saveToFile();
    }

}
