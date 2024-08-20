package com.app.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grounds")
@Data
@NoArgsConstructor
public class Ground extends BaseEntity {

   

    private String name;
    private String location;
    private int capacity;



}
