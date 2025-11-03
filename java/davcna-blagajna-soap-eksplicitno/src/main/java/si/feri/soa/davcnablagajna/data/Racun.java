package si.feri.soa.davcnablagajna.data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Racun",
    namespace = "http://si.feri.soa/davcnablagajna/data/racun/1.0")
public class Racun {
    private String id;
    private String davcnaSt;
    private String datum;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDavcnaSt() {
        return davcnaSt;
    }
    public void setDavcnaSt(String davcnaSt) {
        this.davcnaSt = davcnaSt;
    }
    public String getDatum() {
        return datum;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
    public double getZnesek() {
        return znesek;
    }
    public void setZnesek(double znesek) {
        this.znesek = znesek;
    }
    public String getPpId() {
        return ppId;
    }
    public void setPpId(String ppId) {
        this.ppId = ppId;
    }
    private double znesek;
    private String ppId;
}
