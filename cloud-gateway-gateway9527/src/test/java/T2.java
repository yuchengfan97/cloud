import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class T2 {
    public static void main(String[] args) {
        T2 t2 = new T2();
        String[] data1 = t2.getData("11|12|wji|wie");
        for (int i = 0; i<data1.length;i++){
            System.out.println(data1[i]);
        }
    }

    public String[] getData(String data){
        String[] data1 = data.split("\\|");
        return data1;
    }
}


