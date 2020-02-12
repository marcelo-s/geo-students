package utils;

import entity.Classroom;
import entity.GeoCoordinate;
import entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class GeoCalculator {

    private static final double CIRCUMFERENCE = 10000000;
    private static final double METER_PER_DEGREE_LATITUDE = CIRCUMFERENCE / 90.0;
    private static final double METER_PER_DEGREE_LONGITUDE = CIRCUMFERENCE / 90.0;
    // The classroom is in the center of a 20 m square, therefore the limits are +/- 10
    private static final double LIMIT = 10.0;

    private GeoCalculator() {
    }

    public static List<Student> getStudentsInClassrooms(List<Student> students, List<Classroom> classrooms) {
        return students.stream().filter(student -> isInAClassroom(student, classrooms)).collect(Collectors.toList());
    }

    public static List<Student> getStudentsClustersInClassrooms(List<Student> students, List<Classroom> classrooms, int clusterSize) {
        Map<Integer, List<Student>> classroomsWithStudents = getStudentsInClassroomsMap(students, classrooms);
        return getStudentsInClusters(classroomsWithStudents, clusterSize);
    }

    private static List<Student> getStudentsInClusters(Map<Integer, List<Student>> classroomsWithStudents, int clusterSize) {
        ArrayList<Student> studentsInCluster = new ArrayList<>();
        for (List<Student> studentsInClassroom : classroomsWithStudents.values()) {
            if (studentsInClassroom.size() >= clusterSize) {
                studentsInCluster.addAll(studentsInClassroom);
            }
        }
        return studentsInCluster;
    }

    private static Map<Integer, List<Student>> getStudentsInClassroomsMap(List<Student> students, List<Classroom> classrooms) {
        Map<Integer, List<Student>> classroomsWithStudents = new HashMap<>();
        for (Student student : students) {
            for (int i = 0; i < classrooms.size(); i++) {
                Classroom classroom = classrooms.get(i);
                if (isWithinLimits(student.getGeoCoordinate(), classroom.getGeoCoordinate())) {
                    if (!classroomsWithStudents.containsKey(i)) {
                        classroomsWithStudents.put(i, new ArrayList<>());
                    }
                    classroomsWithStudents.get(i).add(student);
                }
            }
        }
        return classroomsWithStudents;
    }


    private static boolean isInAClassroom(Student student, List<Classroom> classrooms) {
        return classrooms.stream().anyMatch(classroom -> isWithinLimits(student.getGeoCoordinate(), classroom.getGeoCoordinate()));
    }

    public static boolean isWithinLimits(GeoCoordinate studentGeoCoordinate, GeoCoordinate classroomGeoCoordinate) {
        double[] latitudeLimits = getLatitudeLimits(classroomGeoCoordinate.getLatitude());
        double[] longitudeLimits = getLongitudeLimits(classroomGeoCoordinate.getLongitude(), classroomGeoCoordinate.getLatitude());
        return isWithinLatitude(studentGeoCoordinate, latitudeLimits) && isWithingLongitude(studentGeoCoordinate, longitudeLimits);
    }

    private static boolean isWithingLongitude(GeoCoordinate studentGeoCoordinate, double[] longitudeLimits) {
        double longitude = studentGeoCoordinate.getLongitude();
        return longitude >= longitudeLimits[0] && longitude <= longitudeLimits[1];
    }

    private static boolean isWithinLatitude(GeoCoordinate studentGeoCoordinate, double[] latitudeLimits) {
        double latitude = studentGeoCoordinate.getLatitude();
        return latitude >= latitudeLimits[0] && latitude <= latitudeLimits[1];
    }

    private static double[] getLatitudeLimits(double latitude) {
        double offsetInDegrees = Math.abs(LIMIT / METER_PER_DEGREE_LATITUDE);
        double aboveLimit = latitude + offsetInDegrees;
        double belowLimit = latitude - offsetInDegrees;
        return new double[]{belowLimit, aboveLimit};
    }

    private static double[] getLongitudeLimits(double longitude, double latitude) {
        double latitudeInRadians = Math.toRadians(latitude);
        double cosInRadians = Math.cos(latitudeInRadians);
        double degreesPerMeter = METER_PER_DEGREE_LONGITUDE * cosInRadians;
        double offsetInDegrees = Math.abs(LIMIT / degreesPerMeter);
        double aboveLimit = longitude + offsetInDegrees;
        double belowLimit = longitude - offsetInDegrees;
        return new double[]{belowLimit, aboveLimit};
    }

}
