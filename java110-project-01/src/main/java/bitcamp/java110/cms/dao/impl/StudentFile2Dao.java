package bitcamp.java110.cms.dao.impl;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.dao.DuplicationDaoException;
import bitcamp.java110.cms.dao.MandatoryValueDaoException;
import bitcamp.java110.cms.dao.StudentDao;
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


    public int insert(Student student) throws MandatoryValueDaoException, DuplicationDaoException{
      //필수 입력 항목이 비었을 때
        if(student.getName().length()==0
           || student.getEmail().length()==0
           || student.getPassword().length()==0) {
            
            //호줄자에게 예외 정보를 만들어 던진다.
            throw new MandatoryValueDaoException();
        }
            
        for (Student item : list) {
            if (item.getEmail().equals(student.getEmail())) {
                //호줄자에게 예외 정보를 만들어 던진다.
                throw new DuplicationDaoException("같은 이메일이 이미 등록되었습니다.");
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








