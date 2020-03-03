package cn.weiqiubin.domain;


import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
    private boolean sexName;
    private List<Integer> userids;


    public boolean isSexName() {
        return sexName;
    }

    public void setSexName(boolean sexName) {
        this.sexName = sexName;
    }



    public User(){
        this(0,null,null,null,null);
    };

    public User(Integer id,String userName,String passWord,String realName,List<Integer> list){
        this.id=id;
        this.realName=realName;
        this.passWord=passWord;
        this.userName=userName;
        this.userids = list;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", sexName='" + sexName + '\'' +
                '}';
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }
}