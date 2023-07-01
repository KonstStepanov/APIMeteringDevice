package com.javatpoint.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "updregtest")
public class MeteringDevice {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(name = "time")
    private String time;
    @Column(name = "full_name", columnDefinition = "TEXT")
    private String full_name;
    @Column(name = "dev_name", columnDefinition = "TEXT")
    private String dev_name;
    @Column(name = "dev_meter")
    @NumberFormat(pattern = "# ### ### ###.###")
    private Double dev_meter;
    @Column(name = "amount")
    @NumberFormat(pattern = "# ### ### ###.##")
    private Double amount;
}