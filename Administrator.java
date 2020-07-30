

// Methods for administrator will be called using this class and not directly.
// Will provide mechanisms to resolve collisions and error handling (will be covered in class)
// Will provide mechanism for sanitizing input and output
// Will provide built in security features such as hashing
public class Administrator {

	public static boolean createCourse(String department, String number, String name, String units, String cost) {
		return Courses.insert(department, number, name, units, cost);

	}

	public static void createRoster() {

	}

	public static boolean deleteCourse(String ID) {
		return Courses.delete(ID);

	}

	public static boolean updateCourseDept(String oldDepartment, String number, String newDepartment) {
		return Courses.updateDepartment(oldDepartment, number, newDepartment);
	}

	public static boolean updateCourseNum(String department, String oldNumber, String newNumber) {
		return Courses.updateNumber(department, oldNumber, newNumber);
	}

	public static boolean updateCourseName(String department, String number, String title) {
		return Courses.updateTitle(department, number, title);
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

