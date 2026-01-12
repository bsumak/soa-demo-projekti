package si.um.studentgql.graphql;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import si.um.studentgql.entity.*;
import si.um.studentgql.repo.*;
import java.util.List;

@Controller
public class StudentGraphqlController {

    private final StudentRepository studentRepo;
    private final PredmetRepository predmetRepo;
    private final OcenaRepository ocenaRepo;

    public StudentGraphqlController(StudentRepository studentRepo, PredmetRepository predmetRepo, OcenaRepository ocenaRepo) {
        this.studentRepo = studentRepo;
        this.predmetRepo = predmetRepo;
        this.ocenaRepo = ocenaRepo;
    }

    // -------- Query --------

    @QueryMapping
    public StudentEntity getStudent(@Argument String id) {
        return studentRepo.findById(Long.parseLong(id)).orElse(null);
    }

    @QueryMapping
    public List<PredmetEntity> vsiPredmeti(@Argument String id) {
        studentRepo.findById(Long.parseLong(id)).orElseThrow();
        return predmetRepo.findAll();
    }

    @QueryMapping
    public List<OcenaEntity> vseOcene(@Argument String id) {
        return ocenaRepo.findByStudent_Id(Long.parseLong(id));
    }

    // -------- Mutation --------

    @MutationMapping
    public StudentEntity createStudent(@Argument String ime, @Argument String priimek, @Argument String smer) {
        String vpisna = "V" + System.currentTimeMillis();
        String email = (ime + "." + priimek).toLowerCase() + "@student.um.si";
        StudentEntity s = studentRepo.save(new StudentEntity(vpisna, ime, priimek, email, smer));
        return s;
    }

    @MutationMapping
    public StudentEntity updateStudent(@Argument String id, @Argument String ime, @Argument String priimek,
                                       @Argument String smer, @Argument String email) {
        StudentEntity s = studentRepo.findById(Long.parseLong(id)).orElseThrow();
        s.setIme(ime);
        s.setPriimek(priimek);
        s.setSmer(smer);
        s.setEmail(email);
        s = studentRepo.save(s);
        return s;
    }

    @MutationMapping
    public StudentEntity deleteStudent(@Argument String id) {
        StudentEntity s = studentRepo.findById(Long.parseLong(id)).orElseThrow();
        studentRepo.delete(s);
        return s;
    }

    @MutationMapping
    public PredmetEntity createPredmet(@Argument String naziv, @Argument String semester, @Argument Integer ects) {
        return predmetRepo.save(new PredmetEntity(naziv, semester, ects));
    }

    @MutationMapping
    public PredmetEntity updatePredmet(@Argument String id, @Argument String naziv, @Argument String semester, @Argument Integer ects) {
        PredmetEntity p = predmetRepo.findById(Long.parseLong(id)).orElseThrow();
        p.setNaziv(naziv);
        p.setSemester(semester);
        if (ects != null) p.setEcts(ects);
        return predmetRepo.save(p);
    }

    @MutationMapping
    public PredmetEntity deletePredmet(@Argument String id) {
        PredmetEntity p = predmetRepo.findById(Long.parseLong(id)).orElseThrow();
        predmetRepo.delete(p);
        return p;
    }

    @MutationMapping
    public OcenaEntity createOcena(@Argument String naziv, @Argument Float ocena, @Argument String datum, @Argument String studentId) {
        StudentEntity s = studentRepo.findById(Long.parseLong(studentId)).orElseThrow();
        OcenaEntity o = ocenaRepo.save(new OcenaEntity(naziv, ocena, datum, s));
        return o;
    }

    @MutationMapping
    public OcenaEntity updateOcena(@Argument String id, @Argument String naziv, @Argument Float ocena, @Argument String datum,
                                   @Argument String studentId) {
        StudentEntity s = studentRepo.findById(Long.parseLong(studentId)).orElseThrow();
        OcenaEntity o = ocenaRepo.findById(Long.parseLong(id)).orElseThrow();
        o.setNaziv(naziv);
        o.setOcena(ocena);
        o.setDatum(datum);
        o.setStudent(s);
        o = ocenaRepo.save(o);
        return o;
    }

    @MutationMapping
    public OcenaEntity deleteOcena(@Argument String id) {
        OcenaEntity o = ocenaRepo.findById(Long.parseLong(id)).orElseThrow();
        ocenaRepo.delete(o);
        return o;
    }

    @SchemaMapping(typeName = "Student", field = "predmeti")
    public List<PredmetEntity> predmeti(StudentEntity student) {
        return predmetRepo.findAll();
    }

    @SchemaMapping(typeName = "Student", field = "ocene")
    public List<OcenaEntity> ocene(StudentEntity student) {
        return ocenaRepo.findByStudent_Id(student.getId());
    }
}
