package entity;

/**
 * Entity class that models a classroom with geo coordinates
 */
public class Classroom {

    private String name;
    private GeoCoordinate geoCoordinate;

    /**
     * Constructor with all fields
     * @param name the name of the classroom
     * @param geoCoordinate the coordinate of the classroom
     */
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
