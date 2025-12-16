package si.um.feri.rest.racuni;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Racun {
    @Id
    private String id;
    private String datum;
    private String kraj;
    private double znesek;
    private String ppId;
    private String davcnaSt;
}
