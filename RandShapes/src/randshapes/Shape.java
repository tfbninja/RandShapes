package randshapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Tim Barber
 */
public class Shape {
    
    private double x;
    private double y;
    private double w;
    private double h;
    private double r;
    private int t; // type
    public static final int STAR = 0;
    public static final int RECT = 1;
    public static final int TRI = 2;
    
    public Shape(double x, double y, double w, double h, int type) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.t = type;
        this.r = 0;
    }
    
    public Shape(double x, double y, double w, double h, double r, int type) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.t = type;
        this.r = r;
    }
    
    public void draw(Canvas c) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.rotate(r);
        switch (t) {
            case STAR:
                double[] xPs = {10};
                double[] yPs = {};
                gc.strokePolygon(xPoints, yPoints, t);
                break;
            case RECT:
                gc.strokeRect(x, y, w, h);
                break;
            case TRI:
                double[] xPs = {};
                double[] yPs = {};
                gc.strokePolygon(xPoints, yPoints, t);
                break;
            default:
                break;
        }
        gc.rotate(-r);
    }
    
    public void rotate(double degrees) {
        r = degrees;
    }
}

/*
 * The MIT License
 *
 * Copyright (c) 2019 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
