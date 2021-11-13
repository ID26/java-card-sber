package ru.sberbank.denisov26.javacard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min=4, max=4, message = "Passport series mast have 4 digits!")
    private String passportSeries;

    @NotNull
    @Size(min=6, max=6, message = "Passport number mast have 6 digits!")
    private String passportNumber;

    @NotNull(message = "Input issue date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @NotNull
    @Size(min=2, max=250, message = "Issue department mast have from 2 to 250 characters" )
    private String issueDepartment;

    @NotNull
    @Size(min=6, max=6, message = "Code department mast have 6 digits!")
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
