

// Methods for administrator will be called using this class and not directly.
// Will provide mechanisms to resolve collisions and error handling (will be covered in class)
// Will provide mechanism for sanitizing input and output
// Will provide built in security features such as hashing
public class Administrator {

	public static String createCourse(String department, String number, String name, String units, String cost) {
		return Courses.insert(department, number, name, units, cost);

	}

	public static void createRoster() {

	}

	public static String deleteCourse(String ID) {
		return Courses.delete(ID);

	}

	public static String updateCourseDept(String ID, String department) {
		return Courses.updateDept(ID, department);
	}

	public static String updateCourseNum(String ID, String number) {
		return Courses.updateNum(ID, number);
	}

	public static String updateCourseName(String ID, String name) {
		return Courses.updateName(ID, name);
	}

	public static boolean updateInstructorUserName(String ID, String username) {
		return Users.updateUserName(ID, username);
	}

	public static boolean updateInstructorPassword(String ID, String password) {
		return Users.updatePassword(ID, password);
	}

	public static boolean updateInstructorEmail(String ID, String email) {
		return Users.updateEmail(ID, email);
	}

	public static boolean deleteAdmin(String ID) {
		return Users.delete(ID);
	}
}

