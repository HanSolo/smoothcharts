/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.smoothcharts;

import javafx.geometry.Point2D;


/**
 * User: hansolo
 * Date: 03.11.17
 * Time: 04:45
 */
public class Helper {

    public static final int clamp(final int MIN, final int MAX, final int VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }
    public static final double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }

    public static int roundDoubleToInt(final double VALUE){
        double dAbs = Math.abs(VALUE);
        int    i      = (int) dAbs;
        double result = dAbs - (double) i;
        if (result < 0.5) {
            return VALUE < 0 ? -i : i;
        } else {
            return VALUE < 0 ? -(i + 1) : i + 1;
        }
    }

    public static final Point2D[] subdividePoints(final Point2D[] POINTS, final int SUB_DEVISIONS) {
        assert POINTS != null;
        assert POINTS.length >= 3;

        int    noOfPoints = POINTS.length;

        Point2D[] subdividedPoints = new Point2D[((noOfPoints - 1) * SUB_DEVISIONS) + 1];

        double increments = 1.0 / (double) SUB_DEVISIONS;

        for (int i = 0 ; i < noOfPoints - 1 ; i++) {
            Point2D p0 = i == 0 ? POINTS[i] : POINTS[i - 1];
            Point2D p1 = POINTS[i];
            Point2D p2 = POINTS[i + 1];
            Point2D p3 = (i+2 == noOfPoints) ? POINTS[i + 1] : POINTS[i + 2];

            CatmullRom crs = new CatmullRom(p0, p1, p2, p3);

            for (int j = 0; j <= SUB_DEVISIONS; j++) {
                subdividedPoints[(i * SUB_DEVISIONS) + j] = crs.q(j * increments);
            }
        }
        return subdividedPoints;
    }
}
