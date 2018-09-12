package bitcamp.java110.cms.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.domain.Manager;

@Component
public class ManagerFile2Dao implements ManagerDao{
    static String defaultFilename="data/manager2.dat";

    String filename;
    private List<Manager> list = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public ManagerFile2Dao(String filename) {
        this.filename=filename;
        File datafile =  new File(filename);
        try( FileInputStream in0 = new FileInputStream(datafile);
                ObjectInputStream in = new ObjectInputStream(in0);
                ){
            //list 전체를 읽어와도 됨
            list = (List<Manager>) in.readObject();
//            while(true) {
//                try {
//                    Manager m = (Manager) in.readObject();
//                    list.add(m);
//                }catch (Exception e) {
//
//                }
//            }
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
            //list 전체를 넘겨줘도 됨
            out.writeObject(list);
//            for(Manager s : list) {
//                out.writeObject(s);
//                out.flush();
//            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public int insert(Manager manager) {
        for (Manager item : list) {
            if (item.getEmail().equals(manager.getEmail())) {
                return 0;
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
