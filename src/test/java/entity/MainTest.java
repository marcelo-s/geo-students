package entity;

import org.junit.Test;
import utils.GeoCalculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testEngineeringClassroom() {
        //john_student = { 'name': 'John Wilson', 'latitude': 34.069149, 'longitude': -118.442639 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069149, -118.442639);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinate2);

        boolean withinLimits = GeoCalculator.isWithinLimits(student1.getGeoCoordinate(), classroom1.getGeoCoordinate());
        assertTrue(withinLimits);
    }

    @Test
    public void testPetrologyClassroom() {
        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069601, -118.441862);
        Student student1 = new Student("Jane Graham", geoCoordinate1);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom1 = new Classroom("Sedimentary Petrology", geoCoordinate2);

        boolean withinLimits = GeoCalculator.isWithinLimits(student1.getGeoCoordinate(), classroom1.getGeoCoordinate());
        assertTrue(withinLimits);
    }

    @Test
    public void testHumanitiesClassroom() {
        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.071513, -118.441181);
        Student student1 = new Student("Pam Bam", geoCoordinate1);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom1 = new Classroom("Art Hitory", geoCoordinate2);

        boolean withinLimits = GeoCalculator.isWithinLimits(student1.getGeoCoordinate(), classroom1.getGeoCoordinate());
        assertTrue(withinLimits);
    }

    @Test
    public void testNotInClassroomGeology() {
        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.071513, -118.441181);
        Student student1 = new Student("Pam Bam", geoCoordinate1);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom1 = new Classroom("Sedimentary Petrology", geoCoordinate2);

        boolean withinLimits = GeoCalculator.isWithinLimits(student1.getGeoCoordinate(), classroom1.getGeoCoordinate());
        assertFalse(withinLimits);
    }

    @Test
    public void testNotInClassroomHumanities() {
        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069601, -118.441862);
        Student student1 = new Student("Jane Graham", geoCoordinate1);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom1 = new Classroom("Art Hitory", geoCoordinate2);

        boolean withinLimits = GeoCalculator.isWithinLimits(student1.getGeoCoordinate(), classroom1.getGeoCoordinate());
        assertFalse(withinLimits);
    }

    @Test
    public void testListOfStudentsInClasrooms() {
        //john_student = { 'name': 'John Wilson', 'latitude': 34.069149, 'longitude': -118.442639 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069149, -118.442639);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069601, -118.441862);
        Student student2 = new Student("Jane Graham", geoCoordinate2);

        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate3 = new GeoCoordinate(34.071513, -118.441181);
        Student student3 = new Student("Pam Bam", geoCoordinate3);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinateEng = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinateEng);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinateGeo = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom2 = new Classroom("Sedimentary Petrology", geoCoordinateGeo);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinateHum = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom3 = new Classroom("Art Hitory", geoCoordinateHum);

        //music_classroom = { 'name': 'Art of Listening', 'latitude': 34.070223, 'longitude': -118.440193 }
        GeoCoordinate geoCoordinateMusic = new GeoCoordinate(34.070223, -118.440193);
        Classroom classroom4 = new Classroom("Art Hitory", geoCoordinateMusic);

        List<Student> students = Arrays.asList(student1, student2, student3);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        List<Student> studentsInClassrooms = GeoCalculator.getStudentsInClassrooms(students, classrooms);
        assertEquals(3, studentsInClassrooms.size());

        Collections.sort(students);
        Collections.sort(studentsInClassrooms);
        assertEquals(students, studentsInClassrooms);
    }

    @Test
    public void testListOfStudentsInClassroomsOneStudent() {
        // john_student = { 'name': 'John Wilson', 'latitude': 34.069849, 'longitude': -118.443539 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069849, -118.443539);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069901, 'longitude': -118.441562 } # geology
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069901, -118.441562);
        Student student2 = new Student("Jane Graham", geoCoordinate2);

        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071523, 'longitude': -118.441171 } # humanities
        GeoCoordinate geoCoordinate3 = new GeoCoordinate(34.071523, -118.441171);
        Student student3 = new Student("Pam Bam", geoCoordinate3);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinateEng = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinateEng);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinateGeo = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom2 = new Classroom("Sedimentary Petrology", geoCoordinateGeo);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinateHum = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom3 = new Classroom("Art Hitory", geoCoordinateHum);

        //music_classroom = { 'name': 'Art of Listening', 'latitude': 34.070223, 'longitude': -118.440193 }
        GeoCoordinate geoCoordinateMusic = new GeoCoordinate(34.070223, -118.440193);
        Classroom classroom4 = new Classroom("Art Hitory", geoCoordinateMusic);

        List<Student> students = Arrays.asList(student1, student2, student3);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        List<Student> studentsInClassrooms = GeoCalculator.getStudentsInClassrooms(students, classrooms);
        assertEquals(1, studentsInClassrooms.size());
        assertEquals(student3, studentsInClassrooms.get(0));
    }

    @Test
    public void testClusterOfStudentsWithZero() {
        //john_student = { 'name': 'John Wilson', 'latitude': 34.069149, 'longitude': -118.442639 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069149, -118.442639);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069601, -118.441862);
        Student student2 = new Student("Jane Graham", geoCoordinate2);

        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate3 = new GeoCoordinate(34.071513, -118.441181);
        Student student3 = new Student("Pam Bam", geoCoordinate3);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinateEng = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinateEng);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinateGeo = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom2 = new Classroom("Sedimentary Petrology", geoCoordinateGeo);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinateHum = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom3 = new Classroom("Art Hitory", geoCoordinateHum);

        //music_classroom = { 'name': 'Art of Listening', 'latitude': 34.070223, 'longitude': -118.440193 }
        GeoCoordinate geoCoordinateMusic = new GeoCoordinate(34.070223, -118.440193);
        Classroom classroom4 = new Classroom("Art Hitory", geoCoordinateMusic);

        List<Student> students = Arrays.asList(student1, student2, student3);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        int clusterSize = 2;
        List<Student> studentsInClassrooms = GeoCalculator.getStudentsClustersInClassrooms(students, classrooms, clusterSize);
        assertEquals(0, studentsInClassrooms.size());
    }

    @Test
    public void testClusterOfStudentsWith1Cluster() {
        //john_student = { 'name': 'John Wilson', 'latitude': 34.069149, 'longitude': -118.442639 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069149, -118.442639);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069149, -118.442639);
        Student student2 = new Student("Jane Graham", geoCoordinate2);

        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate3 = new GeoCoordinate(34.071513, -118.441181);
        Student student3 = new Student("Pam Bam", geoCoordinate3);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinateEng = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinateEng);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinateGeo = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom2 = new Classroom("Sedimentary Petrology", geoCoordinateGeo);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinateHum = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom3 = new Classroom("Art Hitory", geoCoordinateHum);

        //music_classroom = { 'name': 'Art of Listening', 'latitude': 34.070223, 'longitude': -118.440193 }
        GeoCoordinate geoCoordinateMusic = new GeoCoordinate(34.070223, -118.440193);
        Classroom classroom4 = new Classroom("Art Hitory", geoCoordinateMusic);

        List<Student> students = Arrays.asList(student1, student2, student3);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        int clusterSize = 2;
        List<Student> studentsInClassrooms = GeoCalculator.getStudentsClustersInClassrooms(students, classrooms, clusterSize);
        assertEquals(2, studentsInClassrooms.size());

        assertTrue(studentsInClassrooms.contains(student1));
        assertTrue(studentsInClassrooms.contains(student2));
    }

    @Test
    public void testClusterOfStudentsWith2Clusters() {
        //john_student = { 'name': 'John Wilson', 'latitude': 34.069149, 'longitude': -118.442639 } # engineering
        GeoCoordinate geoCoordinate1 = new GeoCoordinate(34.069149, -118.442639);
        Student student1 = new Student("John Wilson", geoCoordinate1);

        //jane_student = { 'name': 'Jane Graham', 'latitude': 34.069601, 'longitude': -118.441862 } # geology
        GeoCoordinate geoCoordinate2 = new GeoCoordinate(34.069149, -118.442639);
        Student student2 = new Student("Jane Graham", geoCoordinate2);

        //pam_student = { 'name': 'Pam Bam', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate3 = new GeoCoordinate(34.071513, -118.441181);
        Student student3 = new Student("Pam Bam", geoCoordinate3);

        //james_student = { 'name': 'James Hibert', 'latitude': 34.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate4 = new GeoCoordinate(34.071513, -118.441181);
        Student student4 = new Student("Pam Bam", geoCoordinate4);

        //dan_student = { 'name': 'Dan Lambert', 'latitude': 40.071513, 'longitude': -118.441181 } # humanities
        GeoCoordinate geoCoordinate5 = new GeoCoordinate(40.071513, -118.441181);
        Student student5 = new Student("Pam Bam", geoCoordinate5);

        //engineering_classroom = { 'name': 'Principles of computational geo-location analysis', 'latitude': 34.069140, 'longitude': -118.442689 }
        GeoCoordinate geoCoordinateEng = new GeoCoordinate(34.069140, -118.442689);
        Classroom classroom1 = new Classroom("Principles of computational geo-location analysis", geoCoordinateEng);

        //geology_classroom = { 'name': 'Sedimentary Petrology', 'latitude': 34.069585, 'longitude': -118.441878 }
        GeoCoordinate geoCoordinateGeo = new GeoCoordinate(34.069585, -118.441878);
        Classroom classroom2 = new Classroom("Sedimentary Petrology", geoCoordinateGeo);

        //humanities_classroom = { 'name': 'Art Hitory', 'latitude': 34.071528, 'longitude': -118.441211 }
        GeoCoordinate geoCoordinateHum = new GeoCoordinate(34.071528, -118.441211);
        Classroom classroom3 = new Classroom("Art Hitory", geoCoordinateHum);

        //music_classroom = { 'name': 'Art of Listening', 'latitude': 34.070223, 'longitude': -118.440193 }
        GeoCoordinate geoCoordinateMusic = new GeoCoordinate(34.070223, -118.440193);
        Classroom classroom4 = new Classroom("Art Hitory", geoCoordinateMusic);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        int clusterSize = 2;
        List<Student> studentsInClassrooms = GeoCalculator.getStudentsClustersInClassrooms(students, classrooms, clusterSize);
        assertEquals(4, studentsInClassrooms.size());
    }
}