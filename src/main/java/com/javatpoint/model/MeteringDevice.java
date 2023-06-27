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
    @Column
    @GeneratedValue
    private int id;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column
    private String time;
    @Column(columnDefinition = "TEXT")
    private String full_name;
    @Column(columnDefinition = "TEXT")
    private String dev_name;
    @Column
    @NumberFormat(pattern = "# ### ### ###.###")
    private Double dev_meter;
    @Column
    @NumberFormat(pattern = "# ### ### ###.##")
    private Double amount;
}