package si.um.feri.racuni;

public class Racun {
    private String id;
    private String datum;
    private String kraj;
    private double znesek;
    private String ppId;
    private String davcnaSt;

    public Racun() {
    }

    public Racun(String id, String datum, String kraj,
                 double znesek, String ppId, String davcnaSt) {
        this.id = id;
        this.datum = datum;
        this.kraj = kraj;
        this.znesek = znesek;
        this.ppId = ppId;
        this.davcnaSt = davcnaSt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
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

    public String getDavcnaSt() {
        return davcnaSt;
    }

    public void setDavcnaSt(String davcnaSt) {
        this.davcnaSt = davcnaSt;
    }
}
