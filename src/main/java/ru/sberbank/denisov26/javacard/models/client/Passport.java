package ru.sberbank.denisov26.javacard.models.client;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Passport {
    @Id
    @GeneratedValue
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
    private Client client;

}
