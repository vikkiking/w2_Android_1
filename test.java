import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Student A = new Student("s001", "AA", "cmcs"),
                B = new Student("s002", "BB", "cmcs");
        Teacher T = new Teacher("t001", "张三");
        DataStructure dataStructure = new DataStructure("张三", "R203");
        Java java = new Java("张三", "R306");
        Counselor mm = new Counselor("c001", "李四");
        dataStructure.students.add(A);
        dataStructure.students.add(B);
        java.students.add(A);
        java.students.add(B);
        T.courses.add(dataStructure);
        T.courses.add(dataStructure);
        System.out.println(T);
        System.out.println(dataStructure);
        try {
            java.beginClass();
        } catch (Absence absence) {
            System.out.println("开始点名");
            System.out.println(absence.getAbsence());
            System.out.println("以上同学旷课");
        }
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
        StringBuilder cs = new StringBuilder();
        for (Course c : courses) cs.append('\'').append(c.name).append("\' ");
        return "Teacher{\n" +
                " empNo='" + empNo + "'\n" +
                " name='" + name + "'\n" +
                " courses=" + cs +
                '}';
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
        System.out.println(name + "开始管理学生");
    }
}

abstract class Course {
    protected String name, teacher, classRoom;
    protected List<Student> students = new ArrayList<Student>();

    Course(String Name, String Teacher, String ClassRoom) {
        name = Name;
        teacher = Teacher;
        classRoom = ClassRoom;
    }

    @Override
    public abstract String toString();

    public void beginClass() throws Absence {
        System.out.println(teacher + "开始上" + name + "课");
        if (!students.isEmpty())
            throw new Absence(Collections.singletonList(students.get(0)));
    }
}

class Java extends Course {

    Java(String Teacher, String ClassRoom) {
        super("Java", Teacher, ClassRoom);
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

class DataStructure extends Course {

    DataStructure(String Teacher, String ClassRoom) {
        super("DataStructure", Teacher, ClassRoom);
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

class Absence extends Exception {
    private List<Student> list = new ArrayList<Student>();

    public Absence(List<Student> stu) {
        stu.forEach(this::add);
    }

    public void add(Student stu) {
        list.add(stu);
    }

    public List<String> getAbsence() {
        List<String> stus = new ArrayList<String>();
        list.forEach(k -> stus.add(k.name));
        return stus;
    }
}