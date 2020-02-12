package entity;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private GeoCoordinate geoCoordinate;

    public Student(String name, GeoCoordinate geoCoordinate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }
}
