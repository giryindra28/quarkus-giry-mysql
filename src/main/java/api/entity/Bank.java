package api.entity;

import api.enumeration.StatusBank;
import jakarta.persistence.*;

import java.util.Stack;

@Entity
@Table(name = "Banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    private String bankName;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusBank statusBank;


    public Bank(){}

    public Bank(Long bankId, String bankName, String description, StatusBank statusBank) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.description = description;
        this.statusBank = statusBank;

    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusBank getStatusBank() {
        return statusBank;
    }

    public void setStatusBank(StatusBank statusBank) {
        this.statusBank = statusBank;
    }


}
