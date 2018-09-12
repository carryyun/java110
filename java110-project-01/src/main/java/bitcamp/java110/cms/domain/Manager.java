package bitcamp.java110.cms.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Manager extends Member implements Serializable {
    protected String position;
    protected String tel;

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}

