package com.samiu.seamhealth.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "doctor")
@Data
public class Doctor {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length=32, nullable = false)
    String firstName;
    @Column(length=32, nullable = false)
    String lastName;
    @Column(nullable = false, unique = true)
    String email;
    @Column(length=32, nullable = false)
    String phone;
    Long addressId;

    String uuid;

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
