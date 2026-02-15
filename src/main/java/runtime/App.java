package runtime;

import controller.ShowRoomController;
import exception.FileOperationException;
import helper.BrandTableFormatter;
import helper.CarTableFormatter;
import helper.InputPrompter;
import helper.InputValidator;
import model.BrandDAO;
import model.BrandDAOImpl;
import model.CarDAO;
import model.CarDAOImpl;
import service.BrandService;
import service.CarService;
import view.ShowRoomView;

public class App {

    public static void main(String[] args) throws FileOperationException {

        InputPrompter prompter = new InputPrompter();
        InputValidator inputValidator = new InputValidator(prompter);

        BrandDAO brandDAO = new BrandDAOImpl();
        CarDAO carDAO = new CarDAOImpl(brandDAO, inputValidator);

        ShowRoomView view = new ShowRoomView(
                inputValidator,
                new BrandTableFormatter(),
                new CarTableFormatter(brandDAO)
        );
        brandDAO.loadFromFile();
        carDAO.loadFromFile();

        BrandService brandService = new BrandService(brandDAO, view);
        CarService carService = new CarService(carDAO, brandDAO, view);

        ShowRoomController controller = new ShowRoomController(view, brandService, carService);

        controller.start();
    }
}
