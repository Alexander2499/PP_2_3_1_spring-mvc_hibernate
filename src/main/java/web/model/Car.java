package web.model;

public class Car {

    private String model;

    private int series;

    private int yearOfRelease;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Car() {}
    public Car(String model, int series, int yearOfRelease) {
        this.model = model;
        this.series = series;
        this.yearOfRelease = yearOfRelease;
    }


}
