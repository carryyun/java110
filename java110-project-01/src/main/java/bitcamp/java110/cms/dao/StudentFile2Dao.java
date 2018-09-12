package bitcamp.java110.cms.dao;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.domain.Student;

@Component
public class StudentFile2Dao implements StudentDao{
    static String defaultfilename="data/student2.dat";
    String filename;
    private List<Student> list = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public StudentFile2Dao(String filename) {
        this.filename=filename;
        //try autocloseable 메소드가 있는 인터페이스라면 try뒤 ( )에 내용을 넣으면 finally에서 닫아주지않아도 명령실행 후 자동으로 close된다.
        try(
                FileInputStream in0 = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(in0);
                ){  
            list = (List<Student>) in.readObject();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public StudentFile2Dao() {
        this(defaultfilename);
    }

    private void save() {
        try (
                FileOutputStream out0 = new FileOutputStream(filename);
                BufferedOutputStream out1 = new BufferedOutputStream(out0);
                ObjectOutputStream out = new ObjectOutputStream(out1);
                ){
            out.writeObject(list);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int insert(Student student) {
        for (Student item : list) {
            if (item.getEmail().equals(student.getEmail())) {
                return 0;
            }
        }
        list.add(student);
        save();
        return 1;
    }

    public List<Student> findAll() {
        return list;
    }

    public Student findByEmail(String email) {
        for (Student item : list) {
            if (item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }

    public int delete(String email) {
        for (Student item : list) {
            if (item.getEmail().equals(email)) {
                list.remove(item);
                save();
                return 1;
            }
        }
        return 0;
    }
}








