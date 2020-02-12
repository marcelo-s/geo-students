# Geo-Students
Given each student has a geolocation lat/lon point, how would you determine which students are physically in any classroom?  

Write a function that returns the students if they are in a classroom.  

## Assumptions

- Each classroom has a square shape of 20m X 20m and none of the classrooms intersect.
- Students are dimensionless outside of their latitude / longitude point.
- Height is not a concern for either the student or the classroom.
- It doesn’t matter which student was in which classroom, we only care about the list of students found.
This is intended to be performed in memory where you don’t have the usage of a database of some sort.
-  Distance NorthPole to Equator = 10000000 m
   * Then each degree in a latitude is 10000000/90 = 111111 m/degree
   * Equatorial circumference of the earth is more or less the same as the polar circumference.
   * Then each degree in a longitude is the same as with latitude = 111111 m/degree
   * However longitude changes as moving away from the Equator. To correct this :
   * longitude = 111111*cos(latitude) 
   
## Testing

There are 10 tests for the different functionalities implemented.
