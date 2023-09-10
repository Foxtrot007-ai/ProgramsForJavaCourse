package zad9kalendarz;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListaMiesi�cy extends AbstractListModel {
    int rok;
    int miesi�c;

    ListaMiesi�cy() {
    }

    ListaMiesi�cy(Date data) {
       zmie�_date(data);
    }

    public void zmie�_date(Date data) {
        if (data == null) {
            rok = 0;
        } else {
            rok = data.getYear() + 1900;
            miesi�c = data.getMonth();
        }
        fireContentsChanged(this, 0, getSize() - 1);
    }

    @Override
    public int getSize() {
        if (rok == 0)
            return 0;
        if (rok == 1582 && miesi�c == 9)
            return 21;
        Calendar k = Calendar.getInstance();
        k.set(rok, miesi�c, 1);
        return k.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Object getElementAt(int i) {
        Calendar k = Calendar.getInstance();
        k.set(rok, miesi�c, 1);
        while (i != 0) {
        	i--;
        	k.add(Calendar.DAY_OF_MONTH, 1);
        }
           
        String temp = new SimpleDateFormat("EEEE").format(k.getTime())
                + " " + k.get(Calendar.DAY_OF_MONTH)
                + " " + new SimpleDateFormat("MMMM").format(k.getTime());
        return temp;
    }
}
