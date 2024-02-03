public class RealDate {
    int Year;
    int Month;
    int Day;
    String[] MONTHS = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

    public RealDate(int Day, int Month, int Year){
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
    }
    public RealDate(String Date, boolean American){
        if(Date.split("/").length >= 3) {
            try {
                if (American) {
                    this.Day = Integer.parseInt(Date.split("/")[1]);
                    this.Month = Integer.parseInt(Date.split("/")[0]);
                    this.Year = Integer.parseInt(Date.split("/")[2]);
                } else {
                    this.Day = Integer.parseInt(Date.split("/")[0]);
                    this.Month = Integer.parseInt(Date.split("/")[1]);
                    this.Year = Integer.parseInt(Date.split("/")[2]);
                }
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Date requires integers");
            }
        } else {
            throw new IllegalArgumentException("Not enough data values");
        }
    }
    public boolean IsAfter(RealDate Compare){
        if (Compare.Year > this.Year){
            return true;
        } else if (Compare.Month > this.Month) {
            return true;
        } else if (Compare.Day > this.Day) {
            return true;
        }
        return false;
    }
    public String toSaveString(){return Month + "/" + Day+ "/" + Year;}
    public String toString(){
        return Day + " " + MONTHS[Month-1] + " " + Year;
    }
}
