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
import bitcamp.java110.cms.dao.TeacherDao;
import bitcamp.java110.cms.domain.Teacher;

@Component
public class TeacherFile2Dao implements TeacherDao{
    static String defaultfilename="data/teacher2.dat";
    String filename;
    private List<Teacher> list = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public TeacherFile2Dao(String filename) {
        this.filename=filename;
        try( 
                FileInputStream in0 = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(in0);
                ){
            list = (List<Teacher>) in.readObject();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TeacherFile2Dao() {
        this(defaultfilename);
    }

    public void save() {
        try( 
                FileOutputStream out0 = new FileOutputStream(filename);
                BufferedOutputStream out1 = new BufferedOutputStream(out0);
                ObjectOutputStream out = new ObjectOutputStream(out1);
                ){
            out.writeObject(list);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public int insert(Teacher teacher) throws MandatoryValueDaoException, DuplicationDaoException {
      //필수 입력 항목이 비었을 때
        if(teacher.getName().length()==0
           || teacher.getEmail().length()==0
           || teacher.getPassword().length()==0) {
            
            //호줄자에게 예외 정보를 만들어 던진다.
            throw new MandatoryValueDaoException("");
        }
            
        for (Teacher item : list) {
            if (item.getEmail().equals(teacher.getEmail())) {
                //호줄자에게 예외 정보를 만들어 던진다.
                throw new DuplicationDaoException("같은 이메일이 이미 등록되었습니다.");
            }
        }
        
        list.add(teacher);
        save();
        return 1;
    }

    public List<Teacher> findAll() {
        return list;
    }

    public Teacher findByEmail(String email) {
        for (Teacher item : list) {
            if (item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }

    public int delete(String email) {
        for (Teacher item : list) {
            if (item.getEmail().equals(email)) {
                list.remove(item);
                save();
                return 1;
            }
        }
        return 0;
    }
}
