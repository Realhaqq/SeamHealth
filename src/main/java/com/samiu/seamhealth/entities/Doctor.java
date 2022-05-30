package com.samiu.seamhealth.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Data
public class Doctor {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=32, nullable = false)
    private String firstName;
    @Column(length=32, nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length=32, nullable = false)
    private String phone;
//    private Long addressId;

    private String uuid;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void setCreatedAt() {
        createdAt = new Date();
    }

    @PreUpdate
    private void setUpdatedAt() {
        updatedAt = new Date();
    }


}
