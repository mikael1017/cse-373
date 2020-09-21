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
        //  use central theorem
        if ((0 < x) && (x < picture.width - 1) && (0 < y) && (y < picture.height - 1)) {
            Color x1 = picture.get(x + 1, y);
            Color x2 = picture.get(x - 1, y);
            Color y1 = picture.get(x, y + 1);
            Color y2 = picture.get(x, y - 1);
            xR = x1.getRed() - x2.getRed();
            xG = x1.getGreen() - x2.getGreen();
            xB = x1.getBlue() - x2.getBlue();
            yR = y1.getRed() - y2.getRed();
            yG = y1.getGreen() - y2.getGreen();
            yB = y1.getBlue() - y2.getBlue();
            double energy = Math.sqrt((xR * xR) + (xG * xG) + (xB * xB) + (yR * yR) + (yG * yG) + (yB * yB ));
            return energy;
        } else if (()

        )
    }

    private double fDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {

        } else {

        }
    }

    private double bDifference(Picture picture, int x, int y, boolean xy) {
        if (xy) {

        } else {

        }
    }
}
