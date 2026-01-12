package si.um.studentgql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vpisnaStevilka;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String priimek;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String smer;

    public StudentEntity() { }

    public StudentEntity(String vpisnaStevilka, String ime, String priimek, String email, String smer) {
        this.vpisnaStevilka = vpisnaStevilka;
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.smer = smer;
    }

    public Long getId() { return id; }
    public String getVpisnaStevilka() { return vpisnaStevilka; }
    public void setVpisnaStevilka(String vpisnaStevilka) { this.vpisnaStevilka = vpisnaStevilka; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPriimek() { return priimek; }
    public void setPriimek(String priimek) { this.priimek = priimek; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSmer() { return smer; }
    public void setSmer(String smer) { this.smer = smer; }
}
