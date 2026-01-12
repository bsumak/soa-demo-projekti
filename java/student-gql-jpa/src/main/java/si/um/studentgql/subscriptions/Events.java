package si.um.studentgql.subscriptions;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import si.um.studentgql.entity.OcenaEntity;
import si.um.studentgql.entity.StudentEntity;

@Component
public class Events {

    private final Sinks.Many<StudentEntity> newStudent = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<StudentEntity> updatedStudent = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<StudentEntity> deletedStudent = Sinks.many().multicast().onBackpressureBuffer();

    private final Sinks.Many<OcenaEntity> newOcena = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<OcenaEntity> updatedOcena = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<OcenaEntity> deletedOcena = Sinks.many().multicast().onBackpressureBuffer();

    public void emitNewStudent(StudentEntity s) { newStudent.tryEmitNext(s); }
    public void emitUpdatedStudent(StudentEntity s) { updatedStudent.tryEmitNext(s); }
    public void emitDeletedStudent(StudentEntity s) { deletedStudent.tryEmitNext(s); }

    public void emitNewOcena(OcenaEntity o) { newOcena.tryEmitNext(o); }
    public void emitUpdatedOcena(OcenaEntity o) { updatedOcena.tryEmitNext(o); }
    public void emitDeletedOcena(OcenaEntity o) { deletedOcena.tryEmitNext(o); }

    public Flux<StudentEntity> onNewStudent() { return newStudent.asFlux(); }
    public Flux<StudentEntity> onUpdatedStudent() { return updatedStudent.asFlux(); }
    public Flux<StudentEntity> onDeletedStudent() { return deletedStudent.asFlux(); }

    public Flux<OcenaEntity> onNewOcena() { return newOcena.asFlux(); }
    public Flux<OcenaEntity> onUpdatedOcena() { return updatedOcena.asFlux(); }
    public Flux<OcenaEntity> onDeletedOcena() { return deletedOcena.asFlux(); }
}
