package edu.employee.management.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.*;

@Table(name = "department")
@Entity
@Data
public class DepartmentEntity {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
