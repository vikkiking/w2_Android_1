import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Student A = new Student("s001", "AA", "cmcs"),
                B = new Student("s002", "BB", "cmcs");
        Teacher T = new Teacher("t001", "张三");
        Course database = new Course("database", "张三", "R203"),
                ds = new Course("ds", "张三", "R306");
        Counselor mm = new Counselor("c001", "李四");
        database.students.add(A);
        database.students.add(B);
        ds.students.add(A);
        ds.students.add(B);
        T.courses.add(database);
        T.courses.add(ds);
        System.out.println(T);
        System.out.println(database);
        T.beginClass(ds);
        mm.manageStudents();
    }
}

class Student {
    String number, name, college;

    Student(String Number, String Name, String College) {
        number = Number;
        name = Name;
        college = College;
    }

    @Override
    public String toString() {
        return "Student{\n" +
                " number='" + number + "'\n" +
                " name='" + name + "'\n" +
                " college='" + college +
                "'}\n";
    }
}

class Teacher {
    String empNo, name;
    List<Course> courses = new ArrayList<Course>();

    Teacher(String EmpNo, String Name) {
        empNo = EmpNo;
        name = Name;
    }

    @Override
    public String toString() {
        StringBuilder cs=new StringBuilder();
        for(Course c :courses)cs.append('\'').append(c.name).append("\' ");
        return "Teacher{\n" +
                " empNo='" + empNo +  "'\n" +
                " name='" + name +  "'\n" +
                " courses=" + cs +
                '}';
    }

    public void beginClass(Course c) {
        System.out.println(name+"开始上" + c.name + "课");
    }
}

interface Manage {
    void manageStudents();
}

class Counselor extends Teacher implements Manage {
    Counselor(String EmpNo, String Name) {
        super(EmpNo, Name);
    }

    @Override
    public void manageStudents() {
        System.out.println(name+"开始管理学生");
    }
}

class Course {
    String name, teacher, classRoom;
    List<Student> students = new ArrayList<Student>();

    Course(String Name, String Teacher, String ClassRoom) {
        name = Name;
        teacher = Teacher;
        classRoom = ClassRoom;
    }

    @Override
    public String toString() {
        StringBuilder stu = new StringBuilder();
        for (Student s : students)
            stu.append(" ").append(s);
        return "Course{\n" +
                " name='" + name + "'\n" +
                " teacher='" + teacher + "'\n" +
                " classRoom='" + classRoom + "'\n" +
                " students=\n" + stu +
                '}';
    }
}