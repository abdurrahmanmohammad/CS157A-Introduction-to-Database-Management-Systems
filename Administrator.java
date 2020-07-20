

// Methods for administrator will be called using this class and not directly.
// Will provide mechanisms to resolve collisions and error handling (will be covered in class)
// Will provide mechanism for sanitizing input and output
// Will provide built in security features such as hashing
public class Administrator {

	public static String createCourse(String department, String number, String name) {
		return Courses.insert(department, number, name);

	}

	public static void createRoster() {
	}

	public static String deleteCourse(String department, String number) {
		return Courses.delete(department, number);

	}
	public static String updateCourse(String department, String number, String name) {
		return Courses.update(department, number, name);

	}
	public static String updateInstructor(String username, String password, String email) {
		return Users.update(username, password, email);

	}
	public static String deleteAdministrator(String username, String password) {
		return Users.delete(username, password);

	}
}

