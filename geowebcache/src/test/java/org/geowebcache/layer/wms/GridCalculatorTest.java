package org.geowebcache.layer.wms;

import java.util.Arrays;

import junit.framework.TestCase;

import org.geowebcache.util.wms.BBOX;
import org.geowebcache.util.wms.GridCalculator;

public class GridCalculatorTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void test1gridLevels4326() throws Exception {
        BBOX bbox = new BBOX(0, 0, 180, 90);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 1;
        int metaWidth = 1;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);

        int[][] solution = { { 1, 0, 1, 0 }, { 2, 1, 3, 1 }, { 4, 2, 7, 3 },
                { 8, 4, 15, 7 }, { 16, 8, 31, 15 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test2gridLevels4326() throws Exception {
        BBOX bbox = new BBOX(0, 0, 180, 90);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);

        int[][] solution = { { 1, 0, 1, 0 }, { 0, 0, 3, 1 }, { 3, 0, 7, 3 },
                { 6, 3, 15, 7 }, { 15, 6, 31, 15 }, { 30, 15, 63, 31 },
                { 63, 30, 127, 63 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test3gridLevels4326() throws Exception {
        BBOX bbox = new BBOX(-10.0, -10.0, 10.0, 10.0);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
        
        int[][] solution = { { 0, 0, 1, 0 }, { 0, 0, 2, 1 }, { 3, 0, 5, 2 },
                { 6, 3, 8, 5 }, { 15, 6, 17, 8 }, { 30, 12, 35, 17 },
                { 60, 27, 68, 35 }, { 120, 54, 137, 71 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test4gridLevels4326() throws Exception {
        BBOX bbox = new BBOX(175.0, 87.0, 180.0, 90.0);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 4;
        int metaWidth = 4;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
                
        int[][] solution = { { 1, 0, 1, 0 }, { 3, 1, 3, 1 }, { 4, 0, 7, 3 },
                { 12, 4, 15, 7 }, { 28, 12, 31, 15 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test1gridLevels900913() throws Exception {
        BBOX bbox = new BBOX(0, 0, 20037508.34, 20037508.34);
        BBOX gridBase = new BBOX(
        		-20037508.34, -20037508.34, 
        		20037508.34, 20037508.34);
        int metaHeight = 1;
        int metaWidth = 1;
        double maxTileWidth = 20037508.34 * 2;
        double maxTileHeight = 20037508.34 * 2;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
                
        int[][] solution = { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 2, 2, 3, 3 },
                { 4, 4, 7, 7 }, { 8, 8, 15, 15 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println("test1gridLevels900913, level " + i);
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test2gridLevels900913() throws Exception {
        BBOX bbox = new BBOX(0, 0, 20037508.34, 20037508.34);
        BBOX gridBase = new BBOX(
        		-20037508.34, -20037508.34, 
        		20037508.34, 20037508.34);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 20037508.34 * 2;
        double maxTileHeight = 20037508.34 * 2;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
                
        int[][] solution = { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 3, 3 },
                { 3, 3, 7, 7 }, { 6, 6, 15, 15 }, { 15, 15, 31, 31 },
                { 30, 30, 63, 63 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println("test2gridLevels900913, level " + i);
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test3gridLevels900913() throws Exception {
        BBOX bbox = new BBOX(-500000, -500000, 500000, 500000);
        BBOX gridBase = new BBOX(
        		-20037508.34, -20037508.34, 
        		20037508.34, 20037508.34);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 20037508.34 * 2;
        double maxTileHeight = 20037508.34 * 2;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
                
        int[][] solution = { { 0, 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 2, 2 },
                { 3, 3, 5, 5 }, { 6, 6, 8, 8 }, { 15, 15, 17, 17 },
                { 30, 30, 32, 32 }, { 60, 60, 65, 65 } };

        for (int i = 0; i < solution.length; i++) {
            int[] bounds = gridCalc.getGridBounds(i);

            if (!Arrays.equals(solution[i], bounds)) {
                System.out.println("test3gridLevels900913, level " + i);
                System.out.println(Arrays.toString(solution[i]) + "  "
                        + Arrays.toString(bounds));
            }
            assertTrue(Arrays.equals(solution[i], bounds));
        }
    }

    public void test5gridBoundsLoc4326() throws Exception {
        BBOX bbox = new BBOX(-124.73, 24.96, -66.97, 49.37);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
        
        int[] gridLoc = gridCalc.getZoomedOutGridLoc();
        int[] solution = {0, 0, 0};
        assertTrue(Arrays.equals(gridLoc, solution));
    }
    
    public void test6gridLoctoBounds4326() throws Exception {
        BBOX bbox = new BBOX(-124.73, 24.96, -66.97, 49.37);
        BBOX gridBase = new BBOX(-180, -90, 180, 90);
        int metaHeight = 3;
        int metaWidth = 3;
        double maxTileWidth = 180.0;
        double maxTileHeight = 180.0;
        int zoomStart = 0;
        int zoomStop = 20;

        GridCalculator gridCalc = new GridCalculator(
                gridBase, bbox, 
                zoomStart, zoomStop, 
                metaWidth, metaHeight, 
                maxTileWidth, maxTileHeight);
        
        int[] gridLoc1 = {1, 1, 1};
        BBOX box1 = gridCalc.bboxFromGridLocation(gridLoc1);
        boolean box1_comparison = box1.equals(new BBOX(-90.0,0.0,0.0,90.0));
        assertTrue(box1_comparison);
        boolean box1_kml = box1.toKML().equals(
        		"<LatLonAltBox><north>90.0</north><south>0.0</south>"
        		+"<east>0.0</east><west>-90.0</west></LatLonAltBox>");
        assertTrue(box1_kml);
        
        int[] gridLoc2 = {5, 1, 2};        
        BBOX box2 = gridCalc.bboxFromGridLocation(gridLoc2);
        boolean box2_comparison = box2.equals(new BBOX(45.0,-45.0,90.0,0.0));
        assertTrue(box2_comparison);
        boolean box2_kml = box2.toKML().equals(
        		"<LatLonAltBox><north>0.0</north><south>-45.0</south>"
        		+"<east>90.0</east><west>45.0</west></LatLonAltBox>");
        assertTrue(box2_kml);
    }
    
}