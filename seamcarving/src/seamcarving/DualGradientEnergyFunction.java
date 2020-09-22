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
        double xE = determine(picture, x, y, true);
        double yE = determine(picture, x, y, false);
        return Math.sqrt(xE + yE);
    }

    private double determine(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            if ((0 < x) && (x < picture.width() - 1)) {
                return centralDifference(picture, x, y, true);
            } else if (x == 0) {
                return fDifference(picture, x, y, true);
            } else if (x == picture.width() - 1) {
                return bDifference(picture, x, y, true);
            }
        } else {
            if ((0 < y) && (y < picture.height() - 1)) {
                return centralDifference(picture, x, y, false);
            } else if (y == 0) {
                return fDifference(picture, x, y, false);
            } else {
                return bDifference(picture, x, y, false);
            }
        }
        return -1;
    }

    private double centralDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            Color x1 = picture.get(x + 1, y);
            Color x2 = picture.get(x - 1, y);
            double xR = x1.getRed() - x2.getRed();
            double xG = x1.getGreen() - x2.getGreen();
            double xB = x1.getBlue() - x2.getBlue();
            return ((xR * xR) + (xG * xG) + (xB * xB));

        } else {
            Color y1 = picture.get(x, y + 1);
            Color y2 = picture.get(x, y - 1);
            double yR = y1.getRed() - y2.getRed();
            double yG = y1.getGreen() - y2.getGreen();
            double yB = y1.getBlue() - y2.getBlue();
            return ((yR * yR) + (yG * yG) + (yB * yB));
        }
    }

    private double fDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            Color x1 = picture.get(x, y);
            Color x2 = picture.get(x + 1, y);
            Color x3 = picture.get(x + 2, y);
            double xR = (-3 * x1.getRed()) + (4 * x2.getRed()) + (-1 * x3.getRed());
            double xG = (-3 * x1.getGreen()) + (4 * x2.getGreen()) + (-1 * x3.getGreen());
            double xB = (-3 * x1.getBlue()) + (4 * x2.getBlue()) + (-1 * x3.getBlue());
            return ((xR * xR) + (xG * xG) + (xB * xB));
        } else {
            Color y1 = picture.get(x, y);
            Color y2 = picture.get(x, y + 1);
            Color y3 = picture.get(x, y + 2);
            double yR = (-3 * y1.getRed()) + (4 * y2.getRed()) + (-1 * y3.getRed());
            double yG = (-3 * y1.getGreen()) + (4 * y2.getGreen()) + (-1 * y3.getGreen());
            double yB = (-3 * y1.getBlue()) + (4 * y2.getBlue()) + (-1 * y3.getBlue());
            return ((yR * yR) + (yG * yG) + (yB * yB));
        }
    }

    private double bDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {
            Color x1 = picture.get(x, y);
            Color x2 = picture.get(x - 1, y);
            Color x3 = picture.get(x - 2, y);
            double xR = (-3 * x1.getRed()) + (4 * x2.getRed()) + (-1 * x3.getRed());
            double xG = (-3 * x1.getGreen()) + (4 * x2.getGreen()) + (-1 * x3.getGreen());
            double xB = (-3 * x1.getBlue()) + (4 * x2.getBlue()) + (-1 * x3.getBlue());
            return ((xR * xR) + (xG * xG) + (xB * xB));
        } else {
            Color y1 = picture.get(x, y);
            Color y2 = picture.get(x, y - 1);
            Color y3 = picture.get(x, y - 2);
            double yR = (-3 * y1.getRed()) + (4 * y2.getRed()) + (-1 * y3.getRed());
            double yG = (-3 * y1.getGreen()) + (4 * y2.getGreen()) + (-1 * y3.getGreen());
            double yB = (-3 * y1.getBlue()) + (4 * y2.getBlue()) + (-1 * y3.getBlue());
            return ((yR * yR) + (yG * yG) + (yB * yB));
        }
    }
}
