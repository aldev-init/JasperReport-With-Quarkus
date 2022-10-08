package com.aldev.Models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mahasiswa")
@NamedQuery(name = "Mahasiswa.findAll",query = "select m from Mahasiswa m order by m.nama_lengkap",
            hints = @QueryHint(name = "org.hibernate.cacheable",value = "true"))
@Cacheable
@Getter
@Setter
public class Mahasiswa extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "idMahasiswaSequence",sequenceName = "mahasiswa_id_sequence",allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "idMahasiswaSequence")
    private Long id;

    @Column(name = "nim",nullable = false)
    private String nim;

    @Column(name = "nama_lengkap",nullable = false)
    private String nama_lengkap;

    @Column(name = "jurusan",nullable = false)
    private String jurusan;
}
