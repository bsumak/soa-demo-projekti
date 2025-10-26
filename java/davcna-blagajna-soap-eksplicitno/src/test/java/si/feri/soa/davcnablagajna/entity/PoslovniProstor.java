package si.feri.soa.davcnablagajna.entity;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "PoslovniProstor", 
    namespace = "http://si.feri.soa/davcnablagajna/data/poslovniprostor/1.0")
public class PoslovniProstor {

    private String ppId;
    private String davcnaSt;
    private String naslov;
    public String getPpId() {
        return ppId;
    }
    public void setPpId(String ppId) {
        this.ppId = ppId;
    }
    public String getDavcnaSt() {
        return davcnaSt;
    }
    public void setDavcnaSt(String davcnaSt) {
        this.davcnaSt = davcnaSt;
    }
    public String getNaslov() {
        return naslov;
    }
    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

}
