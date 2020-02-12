package entity;

public class Classroom {

    private String name;
    private GeoCoordinate geoCoordinate;

    public Classroom(String name, GeoCoordinate geoCoordinate) {
        this.name = name;
        this.geoCoordinate = geoCoordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }
}
