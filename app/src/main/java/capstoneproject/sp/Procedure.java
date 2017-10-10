package capstoneproject.sp;


public class Procedure {
    private String procedureID;
    private String procDesc;

    public Procedure(){
    }

    public Procedure(String procedureID, String procDesc) {
        this.procedureID = procedureID;
        this.procDesc = procDesc;
    }

    public String getProcedureID() {
        return procedureID;
    }

    public String getProcDesc() {
        return procDesc;
    }
}
