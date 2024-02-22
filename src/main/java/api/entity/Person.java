package api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String personName;
    private String city;
    private Double money;

//    @OneToMany(mappedBy = "person")
//    private List<Bank> banks;
    public Person(){}

    public Person(Long personId, String personName, String city, Double money) {
        this.personId = personId;
        this.personName = personName;
        this.city = city;
        this.money = money;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
