package com.learning.user;

import jakarta.persistence.*;
import lombok.*;

@Entity // This class maps to a database table
@Table(name = "users") // Specifies the table name in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@Builder // Lombok annotation to implement the builder pattern for this class
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Let MySQL generate IDs automatically
    private Long id;
    private String name;
    private String email;
}
