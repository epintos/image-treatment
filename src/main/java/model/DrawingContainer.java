package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian@redmintlabs.com on 23/05/13 at 12:02
 */
public class DrawingContainer {
    public List<Point> inner = new ArrayList<Point>();
    public java.util.List<Point> innerBorder = new ArrayList<Point>();
    public double[] avgIn;
    public double[] avgOut;
    public List<Point> in = new ArrayList<Point>();
}
