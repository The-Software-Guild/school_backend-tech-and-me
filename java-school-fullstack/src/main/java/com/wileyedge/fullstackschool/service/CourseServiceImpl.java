package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

    	 return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE


    	return courseDao.findCourseById(id);

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

    	return courseDao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

    	 Course existingCourse = this.getCourseById(id);

    	    if (existingCourse != null && existingCourse.getCourseId() == course.getCourseId() && existingCourse.getCourseId() == id) {
    	        existingCourse.setCourseName(course.getCourseName());
    	        existingCourse.setCourseDesc(course.getCourseDesc());
    	        existingCourse.setTeacherId(course.getTeacherId());
    	        courseDao.updateCourse(existingCourse);
    	        return course;
    
    	    } else {
    	        course.setCourseName("IDs do not match, course not updated");
    	        course.setCourseDesc("IDs do not match, course not updated");
    	        return course;
    	    }
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
    	
    	courseDao.deleteCourse(id);


        //YOUR CODE ENDS HERE
    }
}
