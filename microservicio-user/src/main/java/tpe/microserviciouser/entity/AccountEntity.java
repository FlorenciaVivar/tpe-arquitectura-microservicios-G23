//package tpe.microserviciouser.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "account")
//public class AccountEntity {
//
//    @jakarta.persistence.Id
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String accountNumber; // Numero cuenta de MercadoPago
//    private Double moneyAvailable; // Plata en cuenta
//    private LocalDate registrationDate; // Fecha de alta de la cuenta
//
//    @ManyToMany(mappedBy = "accounts")
//    private List<UserEntity> users;
//}
//
