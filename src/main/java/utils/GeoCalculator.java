package utils;

import entity.Classroom;
import entity.GeoCoordinate;
import entity.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assumptions:
 * Distance NorthPole to Equator = 10000000 m
 * Then each degree in a latitude is 10000000/90 = 111111 m/degree
 * Equatorial circumference of the earth is more or less the same as the polar circumference
 * Then each degree in a longitude is the same as with latitude = 111111 m/degree
 * However longitude changes as moving away from the Equator. To correct this :
 * longitude = 111111*cos(latitude)
 */

/**
 * Utility class that provides methods for calculating which students are present in a classroom according to their
 * geo coordinates
 */
public class GeoCalculator {

    /**
     * Constants required for the calculation of geo coordinates limits
     */
    private static final double CIRCUMFERENCE = 10000000;
    private static final double METER_PER_DEGREE_LATITUDE = CIRCUMFERENCE / 90.0;
    private static final double METER_PER_DEGREE_LONGITUDE = CIRCUMFERENCE / 90.0;
    // The classroom is in the center of a 20 m square, therefore the limits are +/- 10
    private static final double LIMIT = 10.0;

    /**
     * Private constructor as it is an utility class
     */
    private GeoCalculator() {
    }

    /**
     * Return a list of all the students present in any classroom according to their geo coordinates
     * <p>
     * A classroom has a square dimension of 20m x 20m
     *
     * @param students   the list of students with geo coordinates
     * @param classrooms the list of classrooms with geo coordinates
     * @return the list of all students that are present in any classroom
     */
    public static List<Student> getStudentsInClassrooms(List<Student> students, List<Classroom> classrooms) {
        return students.stream().filter(student -> isInAClassroom(student, classrooms)).collect(Collectors.toList());
    }

    /**
     * Return a list of all the students present in any classroom with at least @clusterSize students, according to their geo coordinates
     *
     * @param students    the list of students with geo coordinates
     * @param classrooms  the list of classrooms with geo coordinates
     * @param clusterSize the amount of students in a classroom
     * @return the list of all students that are present in any classroom with at least @clusterSize students
     */
    public static List<Student> getStudentsClustersInClassrooms(List<Student> students, List<Classroom> classrooms, int clusterSize) {
        return getClassroomsWithStudents(students, classrooms)
                .stream()
                .filter(classroom -> classroom.size() >= clusterSize)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Helper method that returns a list where each element in the list represent a classroom with students
     *
     * @param students   the list of students with geo coordinates
     * @param classrooms the list of classrooms with geo coordinates
     * @return list representing classrooms with students
     */
    private static ArrayList<List<Student>> getClassroomsWithStudents(List<Student> students, List<Classroom> classrooms) {
        return classrooms.stream()
                .collect(
                        ArrayList::new,
                        (list, classroom) -> addStudentsToClassroom(students, classroom, list),
                        ArrayList::addAll
                );
    }

    /**
     * Helper method that returns all students that are in a specific classroom
     *
     * @param students  ths list of students with geo coordinates
     * @param classroom the classroom with geo coordinates
     * @param list      the list of all students that are within the geo coordinates of the classroom provided as a parameter
     */
    private static void addStudentsToClassroom(List<Student> students, Classroom classroom, ArrayList<List<Student>> list) {
        list.add(students.stream()
                .filter(student -> isWithinLimits(student.getGeoCoordinate(), classroom.getGeoCoordinate()))
                .collect(Collectors.toList())
        );
    }

    /**
     * Helper method that defines if a student is within any of the classrooms provided, given their geo coordinates
     *
     * @param student    the student with geo coordinates
     * @param classrooms the list of classrooms with geo coordinates
     * @return if the student is present in any of the classrooms provided as a param
     */
    private static boolean isInAClassroom(Student student, List<Classroom> classrooms) {
        return classrooms.stream().anyMatch(classroom -> isWithinLimits(student.getGeoCoordinate(), classroom.getGeoCoordinate()));
    }

    /**
     * Helper method that defines if a student is within the geo coordinates of a classroom
     *
     * @param studentGeoCoordinate   the student geo coordinates
     * @param classroomGeoCoordinate the classroom geo coordinates
     * @return if the student is within the geo coordinates of the classroom
     */
    public static boolean isWithinLimits(GeoCoordinate studentGeoCoordinate, GeoCoordinate classroomGeoCoordinate) {
        double[] latitudeLimits = getLatitudeLimits(classroomGeoCoordinate.getLatitude());
        double[] longitudeLimits = getLongitudeLimits(classroomGeoCoordinate.getLongitude(), classroomGeoCoordinate.getLatitude());
        return isWithinLatitude(studentGeoCoordinate, latitudeLimits) && isWithingLongitude(studentGeoCoordinate, longitudeLimits);
    }

    /**
     * Helper method that defines if a student is within a longitude limit
     *
     * @param studentGeoCoordinate the student geo coordinates
     * @param longitudeLimits      the limits of the longitude
     * @return if the student is within the longitude limits
     */
    private static boolean isWithingLongitude(GeoCoordinate studentGeoCoordinate, double[] longitudeLimits) {
        double longitude = studentGeoCoordinate.getLongitude();
        return longitude >= longitudeLimits[0] && longitude <= longitudeLimits[1];
    }

    /**
     * Helper method that defines if a student is withing a latitude limits
     *
     * @param studentGeoCoordinate the student geo coordinates
     * @param latitudeLimits       the limits of the latitude
     * @return if the student is withing the latitude limits
     */
    private static boolean isWithinLatitude(GeoCoordinate studentGeoCoordinate, double[] latitudeLimits) {
        double latitude = studentGeoCoordinate.getLatitude();
        return latitude >= latitudeLimits[0] && latitude <= latitudeLimits[1];
    }

    /**
     * Helper method that calculates the limits of classroom latitude
     *
     * @param classroomLatitude the classroomLatitude of the classroom
     * @return an array where index 0 represent the lower limit and index 1 represent the upper limit
     */
    private static double[] getLatitudeLimits(double classroomLatitude) {
        double offsetInDegrees = Math.abs(LIMIT / METER_PER_DEGREE_LATITUDE);
        double aboveLimit = classroomLatitude + offsetInDegrees;
        double belowLimit = classroomLatitude - offsetInDegrees;
        return new double[]{belowLimit, aboveLimit};
    }

    /**
     * Helper method that calculates the limits of a classroom classroomLongitude
     *
     * @param classroomLongitude the longitude of the classroom
     * @param classroomLatitude  the latitude of the classroom
     * @return an array where index 0 represents the lowe limit and index 1 represent the upper limit
     */
    private static double[] getLongitudeLimits(double classroomLongitude, double classroomLatitude) {
        double latitudeInRadians = Math.toRadians(classroomLatitude);
        double cosInRadians = Math.cos(latitudeInRadians);
        double degreesPerMeter = METER_PER_DEGREE_LONGITUDE * cosInRadians;
        double offsetInDegrees = Math.abs(LIMIT / degreesPerMeter);
        double aboveLimit = classroomLongitude + offsetInDegrees;
        double belowLimit = classroomLongitude - offsetInDegrees;
        return new double[]{belowLimit, aboveLimit};
    }

}
