public class LegendsDate {
    public boolean ABY;
    public int StartDate;
    public boolean EndABY;
    public int EndDate = 30000000;

    public LegendsDate(boolean ABY, int Date) {
        this.ABY = ABY;
        this.StartDate = Date;
        this.EndDate = Date;
        this.EndABY = ABY;
    }
    public LegendsDate(boolean StartABY, int StartDate, boolean EndABY, int EndDate){
        this.ABY = StartABY;
        this.StartDate = StartDate;
        this.EndABY = EndABY;
        this.EndDate = EndDate;
    }
    public LegendsDate(String Date) {
        String[] SplitDate = Date.split("-");
        if(SplitDate.length == 2){
            StartDate = Integer.parseInt(SplitDate[0].split(" ")[0]);
            ABY = SplitDate[0].split(" ")[1] == "ABY" ? true : false;
            EndDate = Integer.parseInt(SplitDate[1].strip().split(" ")[0]);
            EndABY = SplitDate[1].strip().split(" ")[1] == "ABY" ? true : false;
        } else {
            StartDate = Integer.parseInt(Date.split(" ")[0]);
            ABY = Date.split(" ")[1] == "ABY" ? true : false;
        }
    }

    @Override
    public String toString() {
        if (EndDate == 30000000){
            return StartDate  + " " + (ABY?"ABY":"BBY");
        } else {
            return StartDate + " " + (ABY?"ABY":"BBY") + " - " + EndDate + " " + (EndABY?"ABY":"BBY");
        }
    }
}
