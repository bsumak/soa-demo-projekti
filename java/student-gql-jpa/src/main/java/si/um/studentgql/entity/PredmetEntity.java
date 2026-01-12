package si.um.studentgql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "predmeti")
public class PredmetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private Integer ects;

    public PredmetEntity() { }

    public PredmetEntity(String naziv, String semester, Integer ects) {
        this.naziv = naziv;
        this.semester = semester;
        this.ects = ects == null ? 0 : ects;
    }

    public Long getId() { return id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public Integer getEcts() { return ects; }
    public void setEcts(Integer ects) { this.ects = ects; }
}
