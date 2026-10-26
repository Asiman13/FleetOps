package com.example.fleet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String brand;
    @NotBlank
    @Column(nullable = false)
    private String model;
    @NotBlank
    @Column(nullable = false, unique=true)
    private String plateNumber;
    private int year;
    @NotBlank
    private String status;
    @PositiveOrZero
    private double mileage;
}
