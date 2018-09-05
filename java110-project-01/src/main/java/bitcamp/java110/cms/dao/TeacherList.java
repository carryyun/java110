package bitcamp.java110.cms.dao;

import bitcamp.java110.cms.domain.Teacher;

public class TeacherList {
    static Teacher[] teachers = new Teacher[100];
    static int teacherIndex = 0; //Teacher
    
    public static void add(Teacher s) {
            if(teacherIndex==teachers.length) {
                increaseStorage();
            }
            
            teachers[teacherIndex++] = s;
    }
    private static void increaseStorage() {
        Teacher[] newList = new Teacher[teachers.length+3];
        
        for(int i=0; i<teachers.length;i++) {
            teachers[i]=newList[i];
        }
        teachers=newList;
    }
    public static Teacher get(int no) {
        return teachers[no];
    }
    public static int size() {
        return teacherIndex;
    }
    public static void remove(int no) {
        for (int i = no; i < teacherIndex - 1; i++) {
            teachers[i] = teachers[i + 1];
        }
        teacherIndex--;
    }
}
