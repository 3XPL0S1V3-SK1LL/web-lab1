package back;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FieldExtractor {
    private  Short x = 1;
    private Double y = 1.0;
    private Short r = 1;
    private Boolean hit = false;
    private String err = null;

    public FieldExtractor(String jsonString) {
        String[] args = jsonString.split(",");
        String rowX = args[0].split(":")[1].replace("\"", "");
        String rowR = args[2].split(":")[1].replace("\"", "").replace("}", "");
        try {
            if (rowX.equals("null")) throw new NumberFormatException();
            x = Short.parseShort(rowX);
        }
        catch (NumberFormatException e) {
            try {
                x = (short) Double.parseDouble(rowX);
                if ((double) x != Double.parseDouble(rowX)) throw new NumberFormatException();
            }
            catch (NumberFormatException e2) {
                err = "Incorrect x value";
            }
        }
         try {
             String rowY = args[1].split(":")[1].replace("\"", "");
             if (rowY.equals("null")) throw new NumberFormatException();
             y = Double.parseDouble(rowY);
         }
         catch (NumberFormatException e) {
             if (err != null) {
                 err += ", Incorrect y value";
             }
             else {
                 err = "Incorrect y value";
             }
         }
         try {
             if (rowR.equals("null")) throw new NumberFormatException();
             r = Short.parseShort(rowR);
         }
         catch (NumberFormatException e) {
             try {
                 r = (short) Double.parseDouble(rowR);
                 if ((double) r != Double.parseDouble(rowR)) throw new NumberFormatException();
             }
             catch (NumberFormatException e1) {
                 if (err != null) {
                     err += ", Incorrect r value";
                 } else {
                     err = "Incorrect r value";
                 }
             }
         }
        hit = new AreaHitChecker(x,y,r).validate();
    }

    public short getX() {
        return x;
    }

    public String getY() {
        return String.valueOf(y);
    }

    public short getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }
    public String getErr() {
        return err;
    }
}
