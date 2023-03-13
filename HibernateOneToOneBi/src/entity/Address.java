package entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Student student;

    public Address() {

    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }


    public Address(String state, String city) {
        super();
        this.state = state;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", state=" + state + ", city=" + city + "]";
    }

}
