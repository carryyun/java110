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
import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.dao.MandatoryValueDaoException;
import bitcamp.java110.cms.domain.Manager;

//@Component
public class ManagerFile2Dao implements ManagerDao{
    static String defaultFilename="data/manager2.dat";

    String filename;
    private List<Manager> list = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public ManagerFile2Dao(String filename) {
        this.filename=filename;
        try( FileInputStream in0 = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(in0);
                ){
            list = (List<Manager>) in.readObject();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ManagerFile2Dao() {
        this(defaultFilename);
    }

    public void save() {
        try(
                FileOutputStream out0 = new FileOutputStream(filename);
                BufferedOutputStream out1= new BufferedOutputStream(out0);
                ObjectOutputStream out= new ObjectOutputStream(out1);
                ){
            out.writeObject(list);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public int insert(Manager manager) throws MandatoryValueDaoException, DuplicationDaoException{
        //필수 입력 항목이 비었을 때
        if(manager.getName().length()==0
           || manager.getEmail().length()==0
           || manager.getPassword().length()==0) {
            
            //호줄자에게 예외 정보를 만들어 던진다.
            throw new MandatoryValueDaoException("필수 입력값 에러입니다.");
        }
            
        for (Manager item : list) {
            if (item.getEmail().equals(manager.getEmail())) {
                //호줄자에게 예외 정보를 만들어 던진다.
                throw new DuplicationDaoException("같은 이메일이 이미 등록되었습니다.");
            }
        }
        list.add(manager);
        save();
        return 1;
    }

    public List<Manager> findAll() {
        return list;
    }

    public Manager findByEmail(String email) {
        for (Manager item : list) {
            if (item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }

    public int delete(String email) {
        for (Manager item : list) {
            if (item.getEmail().equals(email)) {
                list.remove(item);
                save();
                return 1;
            }
        }
        return 0;
    }
}
