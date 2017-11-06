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
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;


/**
 * User: hansolo
 * Date: 03.11.17
 * Time: 04:42
 */
public class Demo extends Application {
    private XYChart.Series<String, Number> series1;
    private XYChart.Series<String, Number> series2;
    private XYChart.Series<String, Number> series3;
    private XYChart.Series<String, Number> series4;

    private SmoothedChart<String, Number> lineChartNotSmoothed;
    private SmoothedChart<String, Number> lineChartSmoothed;
    private SmoothedChart<String, Number> areaChartNotSmoothed;
    private SmoothedChart<String, Number> areaChartSmoothed;

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

        lineChartSmoothed = new SmoothedChart<>(xAxis2, yAxis2);
        lineChartSmoothed.getData().addAll(series2);
        lineChartSmoothed.setSmoothed(true);
        lineChartSmoothed.setChartType(ChartType.LINE);
        lineChartSmoothed.setInteractive(true);
        lineChartSmoothed.setSubDivisions(8);
        lineChartSmoothed.setSnapToTicks(false);
        lineChartSmoothed.setSymbolColor(series2, new Background(new BackgroundFill(Color.BLUE, new CornerRadii(1024), Insets.EMPTY),
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
        areaChartSmoothed.setLegendVisible(false);

        lineChartNotSmoothed.addEventHandler(SmoothedChartEvent.DATA_SELECTED, e -> System.out.println("Selected value: " + e.getyValue()));
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(lineChartNotSmoothed, 0, 0);
        pane.add(lineChartSmoothed, 1, 0);
        pane.add(areaChartNotSmoothed, 0, 1);
        pane.add(areaChartSmoothed, 1, 1);

        Scene scene = new Scene(pane);

        stage.setTitle("Smooth Charts");
        stage.setScene(scene);
        stage.show();

        System.out.println(lineChartSmoothed.getSymbols(series2));
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
