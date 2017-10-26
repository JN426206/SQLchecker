public class MyQuery implements  Comparable<MyQuery>{
    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public String getZapytanie() {
        return zapytanie;
    }

    public void setZapytanie(String zapytanie) {
        this.zapytanie = zapytanie;
    }

    Integer numer;
    String zapytanie;
    public MyQuery(Integer numer, String zapytanie){
        this.numer = numer;
        this.zapytanie = zapytanie;
    }

    @Override
    public String toString() {
        return numer.toString()+" "+zapytanie;
    }

    public int compareTo(MyQuery o) {
        return numer.compareTo(o.numer);
    }
}
