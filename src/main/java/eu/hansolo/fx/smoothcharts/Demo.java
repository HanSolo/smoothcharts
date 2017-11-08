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

import eu.hansolo.fx.smoothcharts.SmoothedChart.ChartType;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Random;

import static eu.hansolo.fx.smoothcharts.SmoothedChart.TRANSPARENT_BACKGROUND;


/**
 * User: hansolo
 * Date: 03.11.17
 * Time: 04:42
 */
public class Demo extends Application {
    private static final Random RND = new Random();

    private XYChart.Series<String, Number> series1;
    private XYChart.Series<String, Number> series2;
    private XYChart.Series<String, Number> series3;
    private XYChart.Series<String, Number> series4;
    private SmoothedChart<String, Number>  lineChartNotSmoothed;
    private SmoothedChart<String, Number>  lineChartSmoothed;
    private SmoothedChart<String, Number>  areaChartNotSmoothed;
    private SmoothedChart<String, Number>  areaChartSmoothed;

    private XYChart.Series<String, Number> tweakedSeries1;
    private XYChart.Series<String, Number> tweakedSeries2;
    private XYChart.Series<String, Number> tweakedSeries3;
    private SmoothedChart<String, Number>  tweakedChart;

    private long                           lastTimerCall;
    private AnimationTimer                 timer;


    @Override public void init() {
        series1 = new XYChart.Series();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data("MO", 24));
        series1.getData().add(new XYChart.Data("TU", 20));
        series1.getData().add(new XYChart.Data("WE", 23));
        series1.getData().add(new XYChart.Data("TH", 25));
        series1.getData().add(new XYChart.Data("FR", 21));
        series1.getData().add(new XYChart.Data("SA", 18));
        series1.getData().add(new XYChart.Data("SU", 20));

        series2 = new XYChart.Series();
        series2.setName("Series 2");
        series2.getData().add(new XYChart.Data("MO", 17));
        series2.getData().add(new XYChart.Data("TU", 19));
        series2.getData().add(new XYChart.Data("WE", 16));
        series2.getData().add(new XYChart.Data("TH", 21));
        series2.getData().add(new XYChart.Data("FR", 24));
        series2.getData().add(new XYChart.Data("SA", 18));
        series2.getData().add(new XYChart.Data("SU", 15));

        series3 = new XYChart.Series();
        series3.setName("Series 3");
        series3.getData().add(new XYChart.Data("MO", 24));
        series3.getData().add(new XYChart.Data("TU", 20));
        series3.getData().add(new XYChart.Data("WE", 23));
        series3.getData().add(new XYChart.Data("TH", 25));
        series3.getData().add(new XYChart.Data("FR", 21));
        series3.getData().add(new XYChart.Data("SA", 18));
        series3.getData().add(new XYChart.Data("SU", 20));

        series4= new XYChart.Series();
        series4.setName("Series 4");
        series4.getData().add(new XYChart.Data("MO", 17));
        series4.getData().add(new XYChart.Data("TU", 19));
        series4.getData().add(new XYChart.Data("WE", 16));
        series4.getData().add(new XYChart.Data("TH", 21));
        series4.getData().add(new XYChart.Data("FR", 24));
        series4.getData().add(new XYChart.Data("SA", 18));
        series4.getData().add(new XYChart.Data("SU", 15));

        CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis   yAxis1 = new NumberAxis();

        CategoryAxis xAxis2 = new CategoryAxis();
        NumberAxis   yAxis2 = new NumberAxis();

        CategoryAxis xAxis3 = new CategoryAxis();
        NumberAxis   yAxis3 = new NumberAxis();

        CategoryAxis xAxis4 = new CategoryAxis();
        NumberAxis   yAxis4 = new NumberAxis();

        lineChartNotSmoothed = new SmoothedChart<>(xAxis1, yAxis1);
        lineChartNotSmoothed.getData().addAll(series1);
        lineChartNotSmoothed.setSymbolsVisible(series1, false);
        lineChartNotSmoothed.setSeriesColor(series1, Color.MAGENTA);
        lineChartNotSmoothed.setSymbolsVisible(false);
        lineChartNotSmoothed.setSmoothed(false);
        lineChartNotSmoothed.setChartType(ChartType.LINE);
        lineChartNotSmoothed.setInteractive(true);
        lineChartNotSmoothed.setSubDivisions(8);
        lineChartNotSmoothed.setSnapToTicks(false);
        lineChartNotSmoothed.setLegendVisible(false);
        lineChartNotSmoothed.getChartPlotBackground().setBackground(TRANSPARENT_BACKGROUND);
        lineChartNotSmoothed.getHorizontalGridLines().setStroke(Color.rgb(0, 0, 0, 0.5));
        lineChartNotSmoothed.getVerticalGridLines().setStroke(Color.rgb(0, 0, 0, 0.5));
        lineChartNotSmoothed.getHorizontalZeroLine().setStroke(Color.RED);
        lineChartNotSmoothed.getVerticalZeroLine().setStroke(Color.BLUE);
        lineChartNotSmoothed.setAxisTickMarkFill(Color.rgb(255, 255, 0, 0.5));
        lineChartNotSmoothed.setTickLabelFill(Color.MAGENTA);
        lineChartNotSmoothed.setXAxisBorderColor(Color.CYAN);     // set to Color.TRANSPARENT to see the horizontalZeroLine
        lineChartNotSmoothed.setYAxisBorderColor(Color.DARKBLUE); // set to Color.TRANSPARENT to see the verticalZeroLine

        lineChartSmoothed = new SmoothedChart<>(xAxis2, yAxis2);
        lineChartSmoothed.getData().addAll(series2);
        lineChartSmoothed.setSmoothed(true);
        lineChartSmoothed.setChartType(ChartType.LINE);
        lineChartSmoothed.setInteractive(true);
        lineChartSmoothed.setSubDivisions(8);
        lineChartSmoothed.setSnapToTicks(false);
        lineChartSmoothed.setSymbolFill(series2, new Background(new BackgroundFill(Color.BLUE, new CornerRadii(1024), Insets.EMPTY),
                                                                new BackgroundFill(Color.LIME, new CornerRadii(1024), new Insets(2))));
        lineChartSmoothed.setSymbolSize(series2, 20);
        lineChartSmoothed.setLegendVisible(false);

        areaChartNotSmoothed = new SmoothedChart<>(xAxis3, yAxis3);
        areaChartNotSmoothed.getData().addAll(series3);
        areaChartNotSmoothed.setSmoothed(false);
        areaChartNotSmoothed.setChartType(ChartType.AREA);
        areaChartNotSmoothed.setInteractive(true);
        areaChartNotSmoothed.setSubDivisions(8);
        areaChartNotSmoothed.setSnapToTicks(true);
        areaChartNotSmoothed.setLegendVisible(false);

        areaChartSmoothed = new SmoothedChart<>(xAxis4, yAxis4);
        areaChartSmoothed.getData().addAll(series4);
        areaChartSmoothed.setSmoothed(true);
        areaChartSmoothed.setChartType(ChartType.AREA);
        areaChartSmoothed.setInteractive(true);
        areaChartSmoothed.setSubDivisions(8);
        areaChartSmoothed.setSnapToTicks(false);
        areaChartSmoothed.setLegendVisible(true);
        areaChartSmoothed.setChartPlotBackground(Color.rgb(31,31, 31));
        areaChartSmoothed.setSeriesColor(series4, Color.rgb(50, 240, 30), new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                                                                             new Stop(0, Color.rgb(50, 240, 30).darker()),
                                                                                             new Stop(1, Color.TRANSPARENT)));
        areaChartSmoothed.setSymbolSize(series4, 10);
        areaChartSmoothed.getHorizontalGridLines().setStroke(Color.DARKGRAY);
        areaChartSmoothed.getVerticalGridLines().setStroke(Color.DARKGRAY);
        areaChartSmoothed.setLegendBackground(Color.TRANSPARENT);
        areaChartSmoothed.setXAxisTickLabelFill(Color.WHITE);
        areaChartSmoothed.setYAxisTickLabelFill(Color.WHITE);
        areaChartSmoothed.setLegendTextFill(series4, Color.WHITE);
        areaChartSmoothed.setXAxisTickMarkFill(Color.WHITE);
        areaChartSmoothed.setYAxisTickMarkFill(Color.WHITE);
        areaChartSmoothed.setXAxisBorderColor(Color.WHITE);
        areaChartSmoothed.setYAxisBorderColor(Color.WHITE);
        areaChartSmoothed.getStrokePath(series4).setStrokeWidth(3);
        areaChartSmoothed.getStrokePath(series4).setStroke(Color.rgb(50, 240, 30));

        lineChartNotSmoothed.addEventHandler(SmoothedChartEvent.DATA_SELECTED, e -> System.out.println("Selected value: " + e.getyValue()));


        // Tweaked Chart data
        tweakedSeries1 = new XYChart.Series();
        tweakedSeries1.setName("Product 1");
        tweakedSeries1.getData().add(new XYChart.Data<>("MO", 105));
        tweakedSeries1.getData().add(new XYChart.Data<>("TU", 95));
        tweakedSeries1.getData().add(new XYChart.Data<>("WE", 112));
        tweakedSeries1.getData().add(new XYChart.Data<>("TH", 165));
        tweakedSeries1.getData().add(new XYChart.Data<>("FR", 132));
        tweakedSeries1.getData().add(new XYChart.Data<>("SA", 120));
        tweakedSeries1.getData().add(new XYChart.Data<>("SU", 153));

        tweakedSeries2 = new XYChart.Series();
        tweakedSeries2.setName("Product 2");
        tweakedSeries2.getData().add(new XYChart.Data<>("MO", 75));
        tweakedSeries2.getData().add(new XYChart.Data<>("TU", 98));
        tweakedSeries2.getData().add(new XYChart.Data<>("WE", 145));
        tweakedSeries2.getData().add(new XYChart.Data<>("TH", 150));
        tweakedSeries2.getData().add(new XYChart.Data<>("FR", 125));
        tweakedSeries2.getData().add(new XYChart.Data<>("SA", 141));
        tweakedSeries2.getData().add(new XYChart.Data<>("SU", 250));

        tweakedSeries3 = new XYChart.Series();
        tweakedSeries3.setName("Product 3");
        tweakedSeries3.getData().add(new XYChart.Data<>("MO", 150));
        tweakedSeries3.getData().add(new XYChart.Data<>("TU", 140));
        tweakedSeries3.getData().add(new XYChart.Data<>("WE", 125));
        tweakedSeries3.getData().add(new XYChart.Data<>("TH", 130));
        tweakedSeries3.getData().add(new XYChart.Data<>("FR", 127));
        tweakedSeries3.getData().add(new XYChart.Data<>("SA", 150));
        tweakedSeries3.getData().add(new XYChart.Data<>("SU", 165));

        // Tweaked Chart
        tweakedChart = new SmoothedChart<>(new CategoryAxis(), new NumberAxis());
        tweakedChart.getData().addAll(tweakedSeries1, tweakedSeries2, tweakedSeries3);

        // Set the chart type (AREA or LINE);
        tweakedChart.setChartType(SmoothedChart.ChartType.LINE);

        // Tweak the chart plot background
        tweakedChart.getChartPlotBackground().setBackground(TRANSPARENT_BACKGROUND);

        // Tweak the legend
        tweakedChart.setLegendBackground(TRANSPARENT_BACKGROUND);
        tweakedChart.setLegendTextFill(Color.WHITE);

        // Tweak the axis
        tweakedChart.setXAxisTickLabelFill(Color.web("#7A808D"));
        tweakedChart.setYAxisTickLabelFill(Color.web("#7A808D"));
        tweakedChart.setAxisTickMarkFill(Color.TRANSPARENT);
        tweakedChart.setXAxisBorderColor(Color.TRANSPARENT);
        tweakedChart.setYAxisBorderColor(Color.TRANSPARENT);

        // Tweak the grid lines
        tweakedChart.getHorizontalGridLines().setStroke(Color.TRANSPARENT);
        LinearGradient verticalGridLineGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                                                     new Stop(0, Color.TRANSPARENT),
                                                                     new Stop(0.35, Color.TRANSPARENT),
                                                                     new Stop(1, Color.web("#7A808D")));

        tweakedChart.getVerticalGridLines().setStroke(verticalGridLineGradient);
        tweakedChart.setHorizontalZeroLineVisible(false);
        tweakedChart.setSymbolsVisible(false);

        // Tweak series colors
        tweakedChart.setSeriesColor(tweakedSeries1, new LinearGradient(0, 0, 1, 0,
                                                                       true, CycleMethod.NO_CYCLE,
                                                                       new Stop(0, Color.web("#54D1FF")),
                                                                       new Stop(1, Color.web("#016AED"))),
                                    Color.TRANSPARENT);
        tweakedChart.setSeriesColor(tweakedSeries2, new LinearGradient(0, 0, 1, 0,
                                                                       true, CycleMethod.NO_CYCLE,
                                                                       new Stop(0, Color.web("#F9348A")),
                                                                       new Stop(1, Color.web("#EB123A"))),
                                    Color.TRANSPARENT);
        tweakedChart.setSeriesColor(tweakedSeries3, new LinearGradient(0, 0, 1, 0,
                                                                       true, CycleMethod.NO_CYCLE,
                                                                       new Stop(0, Color.web("#7BFB00")),
                                                                       new Stop(1, Color.web("#FCE207"))),
                                    Color.TRANSPARENT);

        // Tweak series strokes
        Path tweakedSeries1Path = tweakedChart.getStrokePath(tweakedSeries1);
        Path tweakedSeries2Path = tweakedChart.getStrokePath(tweakedSeries2);
        Path tweakedSeries3Path = tweakedChart.getStrokePath(tweakedSeries3);

        tweakedSeries1Path.setStrokeWidth(4);
        tweakedSeries2Path.setStrokeWidth(4);
        tweakedSeries3Path.setStrokeWidth(4);

        tweakedSeries1Path.setStrokeLineCap(StrokeLineCap.ROUND);
        tweakedSeries2Path.setStrokeLineCap(StrokeLineCap.ROUND);
        tweakedSeries3Path.setStrokeLineCap(StrokeLineCap.ROUND);

        InnerShadow lineLight  = new InnerShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.65), 3, 0, 0, 2);
        DropShadow  lineShadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.45), 10, 0.0, 0.0, 15.0);
        lineShadow.setInput(lineLight);
        tweakedSeries1Path.setEffect(lineShadow);
        tweakedSeries2Path.setEffect(lineShadow);
        tweakedSeries3Path.setEffect(lineShadow);

        // Tweak series symbols
        tweakedChart.setSymbolFill(tweakedSeries1, new Background(new BackgroundFill(Color.web("#26262D"), new CornerRadii(1024), Insets.EMPTY),
                                                                  new BackgroundFill(Color.web("#54D1FF"), new CornerRadii(1024), new Insets(2))));
        tweakedChart.setSymbolSize(tweakedSeries1, 10);
        //tweakedChart.setSymbolsVisible(tweakedSeries1, true);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 3_000_000_000l) {
                    tweakedSeries1.getData().forEach(data -> data.setYValue(RND.nextDouble() * 250));
                    tweakedSeries2.getData().forEach(data -> data.setYValue(RND.nextDouble() * 250));
                    tweakedSeries3.getData().forEach(data -> data.setYValue(RND.nextDouble() * 250));
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(lineChartNotSmoothed, 0, 0);
        pane.add(lineChartSmoothed, 1, 0);
        pane.add(areaChartNotSmoothed, 0, 1);
        pane.add(areaChartSmoothed, 1, 1);

        StackPane chartPane = new StackPane(tweakedChart);
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.25, 0.5, true, CycleMethod.NO_CYCLE,
                                                     new Stop(0, Color.web("#313A48")),
                                                     new Stop(1, Color.web("#26262D")));
        chartPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.add(chartPane, 0, 2);

        Scene scene = new Scene(pane);

        stage.setTitle("Smooth Charts");
        stage.setScene(scene);
        stage.show();

        timer.start();

        System.out.println(lineChartSmoothed.getSymbols(series2));
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
