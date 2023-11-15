package com.example.speciesmvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "common_name")
    @NotEmpty(message = "Common name must not be empty")
    @NotNull
    @Size(max = 50)
    private String commonName;
    @Column(name = "latin_name")
    @NotEmpty(message = "Latin name must not be empty")
    @NotNull
    @Size(max = 120)
    private String latinName;

    public Species() {}

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Species{");
        sb.append("id=").append(id);
        sb.append(", commonName='").append(commonName).append('\'');
        sb.append(", latinName='").append(latinName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
