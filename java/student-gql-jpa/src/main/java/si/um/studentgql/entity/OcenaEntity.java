package si.um.studentgql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ocene")
public class OcenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private Float ocena;

    @Column(nullable = false)
    private String datum;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    public OcenaEntity() { }

    public OcenaEntity(String naziv, Float ocena, String datum, StudentEntity student) {
        this.naziv = naziv;
        this.ocena = ocena;
        this.datum = datum;
        this.student = student;
    }

    public Long getId() { return id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Float getOcena() { return ocena; }
    public void setOcena(Float ocena) { this.ocena = ocena; }

    public String getDatum() { return datum; }
    public void setDatum(String datum) { this.datum = datum; }

    public StudentEntity getStudent() { return student; }
    public void setStudent(StudentEntity student) { this.student = student; }
}
