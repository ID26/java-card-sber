package ru.sberbank.denisov26.javacard.models.client;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Passport {
    @Id
    @GeneratedValue
    private Long id;
    private String passportSeries;

//    @Column(unique = true, nullable = false)
    private String passportNumber;
    private LocalDate issueDate;
    private String issueDepartment;
    private String codDepartment;

    @OneToOne(mappedBy = "passport", cascade = {
//            убираем CascadeType.ALL, и не указываем CascadeType.REMOVE чтоб не удалялся клиент с удалением пасспорта
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH } /*cascade = CascadeType.ALL*/, optional = false)
    private Client client;

    public Passport() {
    }

    public Passport(String passportSeries, String passportNumber, LocalDate issueDate, String issueDepartment, String codDepartment) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
        this.codDepartment = codDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getCodDepartment() {
        return codDepartment;
    }

    public void setCodDepartment(String codDepartment) {
        this.codDepartment = codDepartment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "\nPassport{" +
                "id=" + id +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issueDate=" + issueDate +
                ", issueDepartment='" + issueDepartment + '\'' +
                ", codDepartment='" + codDepartment + '\'' +
                ", client=" + client +
                '}';
    }
}
