import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Coordinator {
    void registerStudent(Student student);
    void removeStudent(String studentId);
    Student getStudent(String studentId);

    void departmentConfirmed(String studentId);
    void issueOfficeOrder(String studentId);
    void issueTestimonial(String studentId);
    void issueCertificate(String studentId);
    void displayStudentProcessingStatus(String studentId);
}

enum ProcessingStage {
    DEPARTMENT_PENDING,
    OFFICE_ORDER_PENDING,
    TESTIMONIAL_PENDING,
    CERTIFICATE_PENDING,
    COMPLETED;

    @Override
    public String toString() {
        return switch (this) {
            case DEPARTMENT_PENDING -> "Department confirmation pending";
            case OFFICE_ORDER_PENDING -> "Office order pending";
            case TESTIMONIAL_PENDING -> "Testimonial pending";
            case CERTIFICATE_PENDING -> "Certificate and transcript pending";
            case COMPLETED -> "Process completed";
        };
    }
}

class DepartmentOffice {
    private Coordinator coordinator;

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void confirmStudent(String studentId) {
        if (coordinator != null) {
            coordinator.departmentConfirmed(studentId);
        }
        else {
            System.out.println("Coordinator not set. Cannot confirm student.");
        }
    }
}

class ExaminationController {
    private Coordinator coordinator;

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void issueOfficeOrder(String studentId) {
        if (coordinator != null) {
            coordinator.issueOfficeOrder(studentId);
        }
        else {
            System.out.println("Coordinator not set. Cannot issue office order.");
        }
    }

    public void issueCertificate(String studentId) {
        if (coordinator != null) {
            coordinator.issueCertificate(studentId);
        }
        else {
            System.out.println("Coordinator not set. Cannot issue certificate.");
        }
    }
}

class DSW {
    private Coordinator coordinator;

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void issueTestimonial(String studentId) {
        if (coordinator != null) {
            coordinator.issueTestimonial(studentId);
        }
        else {
            System.out.println("Coordinator not set. Cannot issue testimonial.");
        }
    }
}

class Student {
    private String studentId;
    private String name;

    private Coordinator coordinator;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void displayProcessingStatus() {
        if (coordinator != null) {
            coordinator.displayStudentProcessingStatus(studentId);
        } else {
            System.out.println("Student not registered in the system.");
        }
    }

    public void notify(String message) {
        System.out.println("Notification for " + name + " (ID: " + studentId + "):");
        System.out.println(message);
    }
}

class ResultCoordinator implements Coordinator {
    List<Student> students;
    Map<Student, ProcessingStage> studentProcessingStages;

    DepartmentOffice departmentOffice;
    ExaminationController examinationController;
    DSW dsw;

    ResultCoordinator() {
        students = new ArrayList<>();
        studentProcessingStages = new HashMap<>();
    }

    public void registerDepartmentOffice(DepartmentOffice departmentOffice) {
        if (this.departmentOffice != null) {
            this.departmentOffice.setCoordinator(null);
        }

        this.departmentOffice = departmentOffice;
        departmentOffice.setCoordinator(this);
    }

    public void registerExaminationController(ExaminationController examinationController) {
        if (this.examinationController != null) {
            this.examinationController.setCoordinator(null);
        }

        this.examinationController = examinationController;
        examinationController.setCoordinator(this);
    }

    public void registerDSW(DSW dsw) {
        if (this.dsw != null) {
            this.dsw.setCoordinator(null);
        }

        this.dsw = dsw;
        dsw.setCoordinator(this);
    }

    @Override
    public void registerStudent(Student student) {
        // Prevent duplicate
        if (studentProcessingStages.containsKey(student)) {
            System.out.println("Student with ID " + student.getStudentId() + " already exists.");
            return;
        }

        students.add(student);
        studentProcessingStages.put(student, ProcessingStage.DEPARTMENT_PENDING);
        student.setCoordinator(this);
    }

    @Override
    public Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void removeStudent(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            students.remove(student);
            studentProcessingStages.remove(student);
            student.setCoordinator(null);
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void departmentConfirmed(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            if (studentProcessingStages.get(student) == ProcessingStage.DEPARTMENT_PENDING) {
                studentProcessingStages.put(student, ProcessingStage.OFFICE_ORDER_PENDING);
                student.notify("Department confirmation received. Office order pending.");
            } else {
                System.out.println("Student with ID " + studentId + " is not in the correct stage for department confirmation.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void issueOfficeOrder(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            if (studentProcessingStages.get(student) == ProcessingStage.OFFICE_ORDER_PENDING) {
                studentProcessingStages.put(student, ProcessingStage.TESTIMONIAL_PENDING);
                student.notify("Office order issued. Testimonial pending.");
            } else {
                System.out.println("Student with ID " + studentId + " is not in the correct stage for issuing an office order.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void issueTestimonial(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            if (studentProcessingStages.get(student) == ProcessingStage.TESTIMONIAL_PENDING) {
                studentProcessingStages.put(student, ProcessingStage.CERTIFICATE_PENDING);
                student.notify("Testimonial issued. Certificate pending.");
            } else {
                System.out.println("Student with ID " + studentId + " is not in the correct stage for issuing a testimonial.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void issueCertificate(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            if (studentProcessingStages.get(student) == ProcessingStage.CERTIFICATE_PENDING) {
                studentProcessingStages.put(student, ProcessingStage.COMPLETED);
                student.notify("Certificate and transcript issued. Process completed.");
            } else {
                System.out.println("Student with ID " + studentId + " is not in the correct stage for issuing a certificate.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void displayStudentProcessingStatus(String studentId) {
        Student student = getStudent(studentId);
        if (student != null) {
            ProcessingStage stage = studentProcessingStages.get(student);
            System.out.println("Student ID: " + student.getStudentId() + ", Name: " + student.getName());
            System.out.println("Current Processing Stage: " + stage);
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
}



public class ResultSystem {
    public static void main(String[] args) {
        DepartmentOffice departmentOffice = new DepartmentOffice();
        ExaminationController examinationController = new ExaminationController();
        DSW dsw = new DSW();

        Student wasik = new Student("2305129", "Wasik");
        Student ananta = new Student("2305130", "Ananta");

        ResultCoordinator coordinator = new ResultCoordinator();
        coordinator.registerDepartmentOffice(departmentOffice);
        coordinator.registerExaminationController(examinationController);
        coordinator.registerDSW(dsw);
        coordinator.registerStudent(wasik);
        coordinator.registerStudent(ananta);


        System.out.println("---------------------------------------------");
        ananta.displayProcessingStatus();
        System.out.println("---------------------------------------------");

        examinationController.issueOfficeOrder("2305130");
        dsw.issueTestimonial("2305130");
        examinationController.issueCertificate("2305130");
        System.out.println();
        departmentOffice.confirmStudent("2305130");
        System.out.println("---------------------------------------------");

        ananta.displayProcessingStatus();
        System.out.println("---------------------------------------------");

        departmentOffice.confirmStudent("2305130");
        dsw.issueTestimonial("2305130");
        examinationController.issueCertificate("2305130");
        System.out.println();
        examinationController.issueOfficeOrder("2305130");
        System.out.println();
        departmentOffice.confirmStudent("2305129");
        System.out.println("---------------------------------------------");

        ananta.displayProcessingStatus();
        System.out.println("---------------------------------------------");

        departmentOffice.confirmStudent("2305130");
        examinationController.issueOfficeOrder("2305130");
        examinationController.issueCertificate("2305130");
        System.out.println();
        dsw.issueTestimonial("2305130");
        System.out.println("---------------------------------------------");

        ananta.displayProcessingStatus();
        System.out.println("---------------------------------------------");

        departmentOffice.confirmStudent("2305130");
        examinationController.issueOfficeOrder("2305130");
        dsw.issueTestimonial("2305130");
        System.out.println();
        examinationController.issueCertificate("2305130");
        System.out.println("---------------------------------------------");

        ananta.displayProcessingStatus();
        System.out.println("---------------------------------------------");

        wasik.displayProcessingStatus();
        System.out.println("---------------------------------------------");
    }
}
