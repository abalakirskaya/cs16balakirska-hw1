package ua.edu.ucu.tempseries;
import java.util.Arrays;
import java.util.InputMismatchException;
public class TemperatureSeriesAnalysis {

    double[] temperatures;
    int len;

    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[10];
        this.len = 10;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.len = temperatureSeries.length;
        this.temperatures = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        if (this.len > 0) {
            double average = 0;
            double sum = 0;
            for (int i = 0; i < this.len; i++) {
                sum += this.temperatures[i];
            }
            average = sum / this.len;
            return average;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double deviation() {
        if (this.len > 0) {
            double deviation;
            double average = average();
            double sum = 0;
            for (int i = 0; i < this.len; i++) {
                sum += Math.pow((this.temperatures[i] - average), 2);
            }
            deviation = Math.sqrt(sum / this.len);
            return deviation;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double min() {
        if (this.len > 0) {
            double minimum = this.temperatures[0];
            for (int i = 0; i < this.len; i++) {
                if (minimum > this.temperatures[i]) {
                    minimum = this.temperatures[i];
                }
            }
            return minimum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double max() {
        if (this.len > 0) {
            double maximum = this.temperatures[0];
            for (int i = 0; i < this.len; i++) {
                if (maximum < this.temperatures[i]) {
                    maximum = this.temperatures[i];
                }
            }
            return maximum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double findTempClosestToZero() {
        if (this.len > 0) {
            double minimum = Math.abs(this.temperatures[0]);
            double number = this.temperatures[0];
            for (int i = 0; i < this.len; i++) {
                if (minimum > Math.abs(this.temperatures[i])) {
                    minimum = Math.abs(this.temperatures[i]);
                    number = this.temperatures[i];
                }
            }
            return number;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.len > 0) {
            double number_minimum = this.temperatures[0];
            double tempValue_abs = Math.abs(tempValue);
            double minimum_difference = Math.abs(tempValue_abs - Math.abs(this.temperatures[0]));
            for (int i = 0; i < this.len; i++) {
                if (minimum_difference > Math.abs(tempValue_abs - Math.abs(this.temperatures[i]))) {
                    minimum_difference = Math.abs(tempValue_abs - Math.abs(this.temperatures[i]));
                    number_minimum = Math.abs(this.temperatures[i]);
                }
            }
            return number_minimum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        if (this.len > 0) {
            for (int i = 0; i < this.len; i++) {
                if (this.temperatures[i] < tempValue) {
                    counter++;
                }
            }
            double[] less_temps = new double[counter];
            counter = 0;
            for (int i = 0; i < this.len; i++) {
                if (this.temperatures[i] < tempValue) {
                    less_temps[counter] = this.temperatures[i];
                    counter++;
                }
            }
            return less_temps;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        if (this.len > 0) {
            for (int i = 0; i < this.len; i++) {
                if (this.temperatures[i] > tempValue) {
                    counter++;
                }
            }
            double[] greater_temps = new double[counter];
            counter = 0;
            for (int i = 0; i < this.len; i++) {
                if (this.temperatures[i] >= tempValue) {
                    greater_temps[counter] = this.temperatures[i];
                    counter++;
                }
            }
            return greater_temps;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.len > 0) {
            right_temp(this.temperatures);
            TempSummaryStatistics new_temp = new TempSummaryStatistics(average(), deviation(), min(), max());
            return new_temp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void right_temp(double[] temps) {
        for (double i : temps) {
            if (i < -273) {
                throw new InputMismatchException();
            }
        }
    }

    public int addTemps(double... temps) {
        if (this.len > 0) {
            int counter = 0;
            for (int i = 0; i < this.len; i++) {
                if (this.temperatures[i] != Double.MIN_VALUE) {
                    counter++;
                }
            }
            for (int i = 0; i < temps.length; i++) {
                if (counter >= this.len) {
                    double[] new_temperatures = Arrays.copyOf(temperatures, temperatures.length);
                    int temperatures_len = temperatures.length;
                    temperatures = new double[counter * 2];
                    for (int j = 0; j < temperatures_len; j++) {
                        temperatures[j] = new_temperatures[j];
                    }
                }
                temperatures[counter] = temps[i];
                counter++;
            }
            return counter;
        } else {
            return 0;
        }
    }
}

