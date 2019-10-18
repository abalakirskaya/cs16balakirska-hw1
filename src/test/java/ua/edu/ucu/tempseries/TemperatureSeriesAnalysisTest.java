package ua.edu.ucu.tempseries;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import org.junit.Ignore;

public class TemperatureSeriesAnalysisTest {
    private double[] one_array = {20.0};
    private double[] bad_array = {20.0, -1.0, 4.5, 16.2, -273.0};
    private double[] big_array = {20.0, -1.0, 4.5, 16.2, 10.8, 12.2, -25.0, -16.2};
    private double[] empty_array = {};

    private double expected;
    private double result;

    private TemperatureSeriesAnalysis series;
    private TemperatureSeriesAnalysis one_element_series;

    @Before
    public void setUp() {
        series = new TemperatureSeriesAnalysis(Arrays.copyOf(big_array, big_array.length));
        one_element_series = new TemperatureSeriesAnalysis(Arrays.copyOf(one_array, 1));
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }


    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverageOneElementArray() {
        expected = one_array[0];
        result = one_element_series.average();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void testAverageBigArray() {
        expected = 2.6875;
        result = series.average();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestDeviationOneElement() {
        expected = 0.0;
        result = one_element_series.deviation();
        assertEquals(expected, result, 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestDeviationEmpty() {
        TemperatureSeriesAnalysis series_empty = new TemperatureSeriesAnalysis(empty_array);
        series_empty.deviation();
    }

    @Test
    public void TestDeviationGeneral() {
        expected = 14.911525533961976;
        result = series.deviation();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestMinOneArray() {
        expected = 20.0;
        result = one_element_series.min();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestMinGeneral() {
        expected = -25.0;
        result = series.min();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestMaxOneArray() {
        expected = 20.0;
        result = one_element_series.max();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestMaxGeneral() {
        expected = 20.0;
        result = series.max();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestFindTempClosestToZero() {
        expected = -1.0;
        result = series.findTempClosestToZero();
        assertEquals(expected, result, 0.000001);
    }

    @Test
    public void TestFindTempClosestToValue() {
        expected = 10.8;
        result = series.findTempClosestToValue(10);
        assertEquals(expected, result, 0.000001);
    }
    @Test
    public void TestFindTempsLessThen() {
        double[] expected = {-1.0, -25.0, -16.2};
        double[] result = series.findTempsLessThen(0.0);
        assertArrayEquals(expected, result, 0.000001);
    }
    @Test
        public void TestFindTempsGreaterThen() {
        double[] expected = {20.0};
        double[] result = series.findTempsGreaterThen(19.0);
        assertArrayEquals(expected, result, 0.000001);
    }
    @Test
    public void TestSummaryStatistics() {
        TempSummaryStatistics stat = series.summaryStatistics();
        assertArrayEquals(new double[]{2.6875, 14.911525533961976, 20.0, -25.0},
        new double[]{stat.get_average_temp(), stat.get_deviation_temp(), stat.get_max_temp(), stat.get_min_temp()}, 0.00001);
    }
    @Test
    public void TestAddTemps() {
        expected = 9.0;
        result = series.addTemps(one_array);
        assertEquals(expected, result, 0.000001);
    }


}
