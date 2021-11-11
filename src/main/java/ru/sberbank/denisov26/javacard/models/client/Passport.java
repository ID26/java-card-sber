package ru.sberbank.denisov26.javacard.models.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Passport {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String passportSeries;
    private String passportNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
    private String issueDepartment;
    private String codDepartment;

    @OneToOne(mappedBy = "passport", cascade = {
//            убираем CascadeType.ALL, и не указываем CascadeType.REMOVE чтоб не удалялся клиент с удалением пасспорта
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH } /*cascade = CascadeType.ALL*/, optional = false)
    @ToString.Exclude
    private Client client;

    public Passport(String passportSeries, String passportNumber, LocalDate issueDate, String issueDepartment, String codDepartment) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
        this.codDepartment = codDepartment;
    }
}
