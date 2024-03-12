package com.oura.backend.entity;

import com.oura.backend.id.KedHeavyId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ked_heavy")
@IdClass(KedHeavyId.class)
public class KedHeavyEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "week", nullable = false)
    private int week;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "set_1")
    private int set1;

    @Column(name = "set_2")
    private int set2;

    @Column(name = "set_3")
    private int set3;

    @Column(name = "set_4")
    private int set4;

    @Column(name = "set_5")
    private int set5;

    @Column(name = "set_6")
    private int set6;

    @Column(name = "set_7")
    private int set7;

    @Column(name = "set_8")
    private int set8;

    @Column(name = "set_9")
    private int set9;

    @Column(name = "notes")
    private String notes;
}
