package helper;

import java.util.List;
import model.Brand;

public class BrandTableFormatter {

    private static final String SEP = " | ";

    public void displayTable(List<Brand> brands) {
        int idW = "Brand ID".length();
        int nameW = "Name".length();
        int soundW = "Sound".length();
        int priceW = "Price".length();

        for (Brand b : brands) {
            idW = Math.max(idW, b.getId().length());
            nameW = Math.max(nameW, b.getName().length());
            soundW = Math.max(soundW, b.getSound().length());
            priceW = Math.max(priceW, b.getPrice().length());
        }

        String headerFmt = SEP + "%-" + idW + "s" + SEP + "%-" + nameW + "s"
                + SEP + "%-" + soundW + "s" + SEP + "%-" + priceW + "s" + SEP + "%n";

        String rowFmt = SEP + "%-" + idW + "s" + SEP + "%-" + nameW + "s"
                + SEP + "%-" + soundW + "s" + SEP + "%-" + priceW + "sB" + SEP + "%n";

        String border = buildBorder(idW, nameW, soundW, priceW);

        System.out.println(border);
        System.out.printf(headerFmt, "Brand ID", "Name", "Sound", "Price");
        System.out.println(border);

        for (Brand b : brands) {
            System.out.printf(rowFmt, b.getId(), b.getName(), b.getSound(), b.getPrice());
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
