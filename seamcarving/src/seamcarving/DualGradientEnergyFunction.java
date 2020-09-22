package seamcarving;

import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class DualGradientEnergyFunction implements EnergyFunction {
    @Override


    //  Color class
    //  getRed
    //  getGreen
    //  getBlue



    //  picture class
    //  picture.get(int col, int row)
    //  returns the color of pixel as a Color
    /**
        returns the energy of a given pixel (x,y)

     */
    public double apply(Picture picture, int x, int y) {
        double xE = 0.0;
        double yE = 0.0;
        //  use central theorem
        if ((0 < x) && (x < picture.width - 1) && (0 < y) && (y < picture.height - 1)) {
            Color x1 = picture.get(x + 1, y);
            Color x2 = picture.get(x - 1, y);
            Color y1 = picture.get(x, y + 1);
            Color y2 = picture.get(x, y - 1);
            double xR = x1.getRed() - x2.getRed();
            double xG = x1.getGreen() - x2.getGreen();
            double xB = x1.getBlue() - x2.getBlue();
            double yR = y1.getRed() - y2.getRed();
            double yG = y1.getGreen() - y2.getGreen();
            double yB = y1.getBlue() - y2.getBlue();
            double energy = Math.sqrt((xR * xR) + (xG * x G) + (xB * xB) + (yR * yR) + (yG * yG) + (yB * yB ));
            return energy;
        } else if (x < 2) {
            xE = fDifference(picture, x, y, true);
        } else if (x > picture.width -2) {
            xE = bDifference(picture, x, y, true);
        } else if (y < 2) {
            yE = fDifference(picture, x, y, false);
        } else {
            yE = bDifference(picture, x, y, false);
        }
        return Math.sqrt(xE + yE);
    }

    private double fDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            Color x1 = picture.get(x, y);
            Color x2 = picture.get(x + 1, y);
            Color x3 = picture.get(x + 2, y);
            double xR = (-3 * x1.getRed()) + (4 * x2.getRed()) + (-5 * x3.getRed());
            double xG = (-3 * x1.getGreen()) + (4 * x2.getGreen()) + (-5 * x3.getGreen());
            double xB = (-3 * x1.getBlue()) + (4 * x2.getBlue()) + (-5 * x3.getBlue());
            return Math.sqrt((xR * xR) + (xG * xG) + (xB * xB));
        } else {
            Color y1 = picture.get(x, y);
            Color y2 = picture.get(x, y + 1);
            Color y3 = picture.get(x, y + 2);
            double yR = (-3 * y1.getRed()) + (4 * y2.getRed()) + (-5 * y3.getRed());
            double yG = (-3 * y1.getGreen()) + (4 * y2.getGreen()) + (-5 * y3.getGreen());
            double yB = (-3 * y1.getBlue()) + (4 * y2.getBlue()) + (-5 * y3.getBlue());
            return Math.sqrt((yR * yR) + (yG * yG) + (yB * yB));
        }
    }

    private double bDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            Color x1 = picture.get(x, y);
            Color x2 = picture.get(x - 1, y);
            Color x3 = picture.get(x - 2, y);
            double xR = (-3 * x1.getRed()) + (4 * x2.getRed()) + (-5 * x3.getRed());
            double xG = (-3 * x1.getGreen()) + (4 * x2.getGreen()) + (-5 * x3.getGreen());
            double xB = (-3 * x1.getBlue()) + (4 * x2.getBlue()) + (-5 * x3.getBlue());
            return Math.sqrt((xR * xR) + (xG * xG) + (xB * xB));
        } else {
            Color y1 = picture.get(x, y);
            Color y2 = picture.get(x, y - 1);
            Color y3 = picture.get(x, y - 2);
            double yR = (-3 * y1.getRed()) + (4 * y2.getRed()) + (-5 * y3.getRed());
            double yG = (-3 * y1.getGreen()) + (4 * y2.getGreen()) + (-5 * y3.getGreen());
            double yB = (-3 * y1.getBlue()) + (4 * y2.getBlue()) + (-5 * y3.getBlue());
            return Math.sqrt((yR * yR) + (yG * yG) + (yB * yB));
        }
    }
}
