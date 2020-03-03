package cn.weiqiubin.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;


public class Student implements Comparable {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "年龄不能为空")
    private String age;

    @NumberFormat(pattern = "###,#")
    private Integer id;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public Student() {
    }

    public Student(String name, String age, Integer id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Student){

            if(this.id > ((Student) o).id){
                return 1;
            }else if(this.id < ((Student) o).id){
                return -1;
            }else {
                return -name.compareTo(((Student) o).name);
            }
        }
        throw new NumberFormatException("输入数据类型不一致");

    }
}
