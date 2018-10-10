package bitcamp.java110.cms.service;

import java.util.List;

import bitcamp.java110.cms.domain.Student;

public interface StudentService {
    // 서비스 인터페이스는 업무 로직을 규칙으로 정의한다.
    // 따라서 메서드 이름은 업무에 가깝게 명명해야 한다.
    void add(Student manager);
    List<Student> list();
    Student get(int no);
    void delete(int no);
    
}
