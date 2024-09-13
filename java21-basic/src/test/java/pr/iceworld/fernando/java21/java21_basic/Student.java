package pr.iceworld.fernando.java21.java21_basic;

public record Student(String name, String gender) {


    public static void main(String[] args) {
        Student student = new Student("John", "Doe");
        System.out.println(student);
    }
}


