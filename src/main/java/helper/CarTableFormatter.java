package helper;

import java.util.List;
import model.Car;
import model.Brand;
import model.BrandDAO;

public class CarTableFormatter {

    private static final String SEP = " | ";
    private BrandDAO brandDao;

    public CarTableFormatter(BrandDAO brandDao) {
        this.brandDao = brandDao;
    }

    public void displayTable(List<Car> cars) {
        int idW = "Car ID".length();
        int brandIdW = "Brand ID".length();
        int brandNameW = "Brand Name".length();
        int colorW = "Color".length();
        int frameW = "Frame ID".length();
        int engineW = "Engine ID".length();

        for (Car c : cars) {
            Brand b = brandDao.findBrandByID(c.getBrandId());
            String brandName = (b != null) ? b.getName() : "Unknown";

            idW = Math.max(idW, c.getId().length());
            brandIdW = Math.max(brandIdW, c.getBrandId().length());
            brandNameW = Math.max(brandNameW, brandName.length());
            colorW = Math.max(colorW, c.getColor().length());
            frameW = Math.max(frameW, c.getFrameId().length());
            engineW = Math.max(engineW, c.getEngineId().length());
        }

        String headerFmt = SEP + "%-" + idW + "s"
                + SEP + "%-" + brandIdW + "s"
                + SEP + "%-" + brandNameW + "s"
                + SEP + "%-" + colorW + "s"
                + SEP + "%-" + frameW + "s"
                + SEP + "%-" + engineW + "s"
                + SEP + "%n";

        String rowFmt = SEP + "%-" + idW + "s"
                + SEP + "%-" + brandIdW + "s"
                + SEP + "%-" + brandNameW + "s"
                + SEP + "%-" + colorW + "s"
                + SEP + "%-" + frameW + "s"
                + SEP + "%-" + engineW + "s"
                + SEP + "%n";

        String border = buildBorder(idW, brandIdW, brandNameW, colorW, frameW, engineW);

        System.out.println(border);
        System.out.printf(headerFmt, "Car ID", "Brand ID", "Brand Name", "Color", "Frame ID", "Engine ID");
        System.out.println(border);

        for (Car c : cars) {
            Brand b = brandDao.findBrandByID(c.getBrandId());
            String brandName = (b != null) ? b.getName() : "Unknown";

            System.out.printf(rowFmt,
                    c.getId(),
                    c.getBrandId(),
                    brandName,
                    c.getColor(),
                    c.getFrameId(),
                    c.getEngineId());
        }
        System.out.println(border);
    }

    private String buildBorder(int... widths) {
        StringBuilder sb = new StringBuilder(" +");
        for (int w : widths) {
            sb.append(repeat('-', w + 2)).append("+");
        }
        return sb.toString();
    }

    private String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
