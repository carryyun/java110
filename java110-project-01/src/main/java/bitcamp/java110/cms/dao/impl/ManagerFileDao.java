package bitcamp.java110.cms.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.dao.ManagerDao;
import bitcamp.java110.cms.domain.Manager;

//@Component
public class ManagerFileDao implements ManagerDao{
    private List<Manager> list = new ArrayList<>();
    
    public ManagerFileDao() {
        File datafile =  new File("data/manager.dat");
        try( BufferedReader in = new BufferedReader(new FileReader(datafile)) ){
            while(true) {
                String line = in.readLine();
                if(line ==null) break;
                
                String[] values = line.split(",");
                
                Manager s= new Manager();
                s.setName(values[0]);
                s.setEmail(values[1]);
                s.setPassword(values[2]);
                s.setPosition(values[3]);
                s.setTel(values[4]);
                
                list.add(s);
                
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void save() {
        File datafile = new File("data/manager.dat");
        try( BufferedWriter out= new BufferedWriter(new FileWriter(datafile)) ){
            for(Manager s : list) {
                out.write(
                        String.format("%s,%s,%s,%s,%s\n", 
                            s.getEmail(),
                            s.getName(),
                            s.getPassword(),
                            s.getPosition(),
                            s.getTel() ));
            }
            out.flush();
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