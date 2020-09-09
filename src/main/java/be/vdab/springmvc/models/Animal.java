package be.vdab.springmvc.models;


public class Animal {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String Name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
