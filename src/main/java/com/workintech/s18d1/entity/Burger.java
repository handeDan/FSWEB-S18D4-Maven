package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "burgers", schema="workintech")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean isVegan;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Column(nullable = false)
    private String contents;

    public void setIsVegan(final boolean isVegan) {
        this.isVegan = isVegan;
    }
    public boolean getIsVegan() {
        return this.isVegan;
    }

}
