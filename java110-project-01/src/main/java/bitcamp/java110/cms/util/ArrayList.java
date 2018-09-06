package bitcamp.java110.cms.util;

public class ArrayList {
    Object[] list=new Object[100];
    int index = 0;

    public void add(Object obj) {
        if(index==list.length) {
            increaseStorage();
        }
        list[index++] = obj;
    }
    private void increaseStorage() {
        Object[] newList = new Object[list.length+3];
        for(int i=0; i<list.length;i++) {
            list[i]=newList[i];
        }
        list=newList;
    }

    public int size() {
        return index;
    }
    public  Object get(int no) {
        if(no<0 || no >= index) {
            return null;
        }
        return list[no];
    }
    public void remove(int no) {
        for (int i = no; i < index - 1; i++) {
            list[i] = list[i + 1];
        }
        index--;
    }
}
