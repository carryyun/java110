package ex07;

import java.beans.PropertyEditorSupport;

public class EnginePropertyEditor extends PropertyEditorSupport{
    public EnginePropertyEditor() {
        System.out.println("EnginePropertyEditor() 호출됨");
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // String을 java.sql.Date 객체로 만들어야 할 경우
        // 스프링 IoC 컨테이너는 이 메서드를 호출한다.
        
        // 그러면 다음과 같이 문자열을 java.sql.Date 객체로 만들어
        
        
        String[] s= text.split(",");
        Engine engine = new Engine(s[0],Integer.parseInt(s[1]),Boolean.parseBoolean(s[2]));
        System.out.println(engine.getMaker() + " " + engine.getValve() + " " + engine.isDiesel());
        //내부 필드에 저장한다.
        this.setValue(engine);
//        this.setValue(e);
        
        // 스프링 IoC 컨테이너는 이 메서드를 호출한 후,
        // 변환된 값을 꺼내기 위해 getvalue()를 호출하여 그 리턴 값을 사용한다.
    }
    
    @Override
    public Object getValue() {
        // 이 메서드는 오버라이딩 할 필요가 없다.
        // 단지 예제에서 언제 호출되는지를 알기 위해
        System.out.println("EnginePropertyEditor.getValue()");
        return super.getValue();
    }
}
