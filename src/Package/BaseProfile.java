package Package;

/**
 * @author Dominik
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
//import java.util.Collection;

public class BaseProfile {
    public ArrayList<Integer> Pplus;
    public ArrayList<Integer> Pminus;
    public ArrayList<Integer> Qplus;
    public ArrayList<Integer> Qminus;

    public BaseProfile() {
        Pplus = new ArrayList<Integer>();
        Pminus = new ArrayList<Integer>();
        Qplus = new ArrayList<Integer>();
        Qminus = new ArrayList<Integer>();
    }

    public BaseProfile(Integer[] C1, Integer[] C2, Integer[] C3, Integer[] C4) {
        Pplus = new ArrayList<Integer>(Arrays.asList(C1));
        Pminus = new ArrayList<Integer>(Arrays.asList(C2));
        Qplus = new ArrayList<Integer>(Arrays.asList(C3));
        Qminus = new ArrayList<Integer>(Arrays.asList(C4));
    }

    public Boolean _P_Q(Integer object) {
        return Pplus.contains(object) && Qplus.contains(object) && !Pminus.contains(object) && !Qminus.contains(object);
    }

    public Boolean _P_nQ(Integer object) {
        return Pplus.contains(object) && !Qplus.contains(object) && !Pminus.contains(object) && Qminus.contains(object);
    }

    public Boolean _nP_Q(Integer object) {
        return !Pplus.contains(object) && Qplus.contains(object) && Pminus.contains(object) && !Qminus.contains(object);
    }

    public Boolean _nP_nQ(Integer object) {
        return !Pplus.contains(object) && !Qplus.contains(object) && Pminus.contains(object) && Qminus.contains(object);
    }

    public String toString(int i) {
        ArrayList<Integer> v;
        switch (i) {
            case 1:
                v = this.Pplus;
                break;
            case 2:
                v = this.Pminus;
                break;
            case 3:
                v = this.Qplus;
                break;
            case 4:
                v = this.Qminus;
                break;
            default:
                v = this.Pplus;
        }
        Iterator<Integer> it = v.iterator();
        if (!it.hasNext())
            return "---";
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            Integer e = it.next();
            sb.append(e);
            if (!it.hasNext())
                return sb.toString();
            sb.append(',').append(' ');
        }
    }
}