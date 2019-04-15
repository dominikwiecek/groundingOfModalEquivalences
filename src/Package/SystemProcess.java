package Package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Dominik
 */
public class SystemProcess {
    private KnowledgeState KS;
    private Double degreeOfProcessing;
    private Double samplingFreq;
//    private Thresholds Conjuction;
//    private Thresholds Alternative;
//    private Thresholds Exclusive;
//    private Thresholds Implication;
//    private Thresholds Equivalence;
    private Integer Obj;
    private Thresholds[] threshold;
    private Double[] lambda;
    private Boolean[][] conditions;

    public SystemProcess(Double samplingFreq, Thresholds Conjuction, Thresholds Alternative, Thresholds Exclusive, Thresholds Implication, Thresholds Equivalence, KnowledgeState Data, Integer Obj) {
        this.samplingFreq = samplingFreq;
//        this.Conjuction = Conjuction;
//        this.Alternative = Alternative;
//        this.Exclusive = Exclusive;
//        this.Implication = Implication;
//        this.Equivalence = Equivalence;
        this.threshold = new Thresholds[]{Conjuction, Alternative, Exclusive, Implication, Implication, Equivalence};
        this.KS = Data;
        this.Obj = Obj;
        this.degreeOfProcessing = Data.degreeOfProcessing;
        this.fill();
    }

    public void fill(){

        this.lambda = new Double[]{
//                // ∧
//                lambda_P_and_Q(), lambda_P_and_nQ(), lambda_nP_and_Q(), lambda_nP_and_nQ(),
//                // ∨
//                lambda_P_or_Q(), lambda_P_or_nQ(), lambda_nP_or_Q(), lambda_nP_or_nQ(),
//                // ⊻
//                lambda_P_nor_Q(), lambda_P_nor_nQ(), lambda_nP_nor_Q(), lambda_nP_nor_nQ(),
//                // ⇒
//                lambda_P_implies_Q(), lambda_P_implies_nQ(), lambda_nP_implies_Q(), lambda_nP_implies_nQ(),
//                // ⇒
//                lambda_Q_implies_P(), lambda_Q_implies_nP(), lambda_nQ_implies_P(), lambda_nQ_implies_nP(),
                // ⇔
                lambda_P_equivalent_Q(), lambda_P_equivalent_nQ(), lambda_nP_equivalent_Q(), lambda_nP_equivalent_nQ()
        };
        this.conditions = new Boolean[][] {
//                // ∧
//                {(card_processed_P_and_nQ()+card_processed_nP_and_Q()+card_processed_nP_and_nQ())==0,  card_processed_P_and_Q()!=0},
//                {(card_processed_P_and_Q()+card_processed_nP_and_Q()+card_processed_nP_and_nQ())==0,  card_processed_P_and_nQ()!=0},
//                {(card_processed_P_and_Q()+card_processed_P_and_nQ()+card_processed_nP_and_nQ())==0,	card_processed_nP_and_Q()!=0},
//                {(card_processed_P_and_Q()+card_processed_P_and_nQ()+card_processed_nP_and_Q())==0, card_processed_nP_and_nQ()!=0},
////                 // ∧
////                {card_processed_P_and_Q()!=0},
////                {card_processed_P_and_nQ()!=0},
////                {card_processed_nP_and_Q()!=0},
////                {card_processed_nP_and_nQ()!=0},
//                // ∨
//                {card_processed_nP_and_nQ()==0 && card_processed_P_and_nQ()!=0 && card_processed_nP_and_Q()!=0 && card_processed_P_and_Q()!=0 && this.epsilonCentred(threshold[1], new int[]{2, 3, 1}), true},
//                {card_processed_nP_and_Q()==0 && card_processed_P_and_Q()!=0 && card_processed_nP_and_nQ()!=0 && card_processed_P_and_nQ()!=0 && this.epsilonCentred(threshold[1], new int[]{1, 4, 2}), true},
//                {card_processed_P_and_nQ()==0 && card_processed_nP_and_nQ()!=0 && card_processed_P_and_Q()!=0 && card_processed_nP_and_Q()!=0 && this.epsilonCentred(threshold[1], new int[]{4, 1, 3}), true},
//                {card_processed_P_and_Q()==0 && card_processed_nP_and_Q()!=0 && card_processed_P_and_nQ()!=0 && card_processed_nP_and_nQ()!=0 && this.epsilonCentred(threshold[1], new int[]{3, 2, 4}), true},
////                // ∨
////                {card_processed_P_and_nQ()!=0 && card_processed_nP_and_Q()!=0 && card_processed_P_and_Q()!=0 && this.epsilonCentred(threshold[1], new int[]{2, 3, 1}), true},
////                {card_processed_P_and_Q()!=0 && card_processed_nP_and_nQ()!=0 && card_processed_P_and_nQ()!=0 && this.epsilonCentred(threshold[1], new int[]{1, 4, 2}), true},
////                {card_processed_nP_and_nQ()!=0 && card_processed_P_and_Q()!=0 && card_processed_nP_and_Q()!=0 && this.epsilonCentred(threshold[1], new int[]{4, 1, 3}), true},
////                {card_processed_nP_and_Q()!=0 && card_processed_P_and_nQ()!=0 && card_processed_nP_and_nQ()!=0 && this.epsilonCentred(threshold[1], new int[]{3, 2, 4}), true},
//                // ⊻
//                {card_processed_P_and_Q()==0 && card_processed_nP_and_nQ()==0 && card_processed_P_and_nQ()!=0 && card_processed_nP_and_Q()!=0 && this.epsilonCentred(threshold[2], new int[]{2, 3}), true},
//                {card_processed_P_and_nQ()==0 && card_processed_nP_and_Q()==0 && card_processed_P_and_Q()!=0 && card_processed_nP_and_nQ()!=0 && this.epsilonCentred(threshold[2], new int[]{1, 4}), true},
//                {card_processed_nP_and_Q()==0 && card_processed_P_and_nQ()==0 && card_processed_nP_and_nQ()!=0 && card_processed_P_and_Q()!=0 && this.epsilonCentred(threshold[2], new int[]{4, 1}), true},
//                {card_processed_nP_and_nQ()==0 && card_processed_P_and_Q()==0 && card_processed_nP_and_Q()!=0 && card_processed_P_and_nQ()!=0 && this.epsilonCentred(threshold[2], new int[]{3, 2}), true},
////                // ⊻
////                {card_processed_P_and_nQ()!=0 && card_processed_nP_and_Q()!=0 && this.epsilonCentred(threshold[2], new int[]{2, 3}), true},
////                {card_processed_P_and_Q()!=0 && card_processed_nP_and_nQ()!=0 && this.epsilonCentred(threshold[2], new int[]{1, 4}), true},
////                {card_processed_nP_and_nQ()!=0 && card_processed_P_and_Q()!=0 && this.epsilonCentred(threshold[2], new int[]{4, 1}), true},
////                {card_processed_nP_and_Q()!=0 && card_processed_P_and_nQ()!=0 && this.epsilonCentred(threshold[2], new int[]{3, 2}), true},
//                // ⇒
//                {card_processed_P_and_nQ() == 0, card_processed_P_and_Q() != 0},
//                {card_processed_P_and_Q() == 0, card_processed_P_and_nQ() != 0},
//                {card_processed_nP_and_nQ() == 0, card_processed_nP_and_Q() != 0},
//                {card_processed_nP_and_Q() == 0, card_processed_nP_and_nQ() != 0},
//                // ⇒
//                {card_processed_nP_and_Q() == 0, card_processed_P_and_Q() != 0},
//                {card_processed_P_and_Q() == 0, card_processed_nP_and_Q() != 0},
//                {card_processed_nP_and_nQ() == 0, card_processed_P_and_nQ() != 0},
//                {card_processed_P_and_nQ() == 0, card_processed_nP_and_nQ() != 0},
                // ⇔
                {(card_processed_P_and_nQ() + card_processed_nP_and_Q()) == 0, card_processed_P_and_Q() != 0},
                {(card_processed_P_and_Q() + card_processed_nP_and_nQ()) == 0, card_processed_P_and_nQ() != 0},
                {(card_processed_P_and_Q() + card_processed_nP_and_nQ()) == 0, card_processed_nP_and_Q() != 0},
                {(card_processed_P_and_nQ() + card_processed_nP_and_Q()) == 0, card_processed_nP_and_nQ() != 0}
        };
    }

    public Double card_processed_P_and_Q() {
        Double counter = 0.0;
        Iterator<BaseProfile> it = this.KS.processed.iterator();
        while (it.hasNext())
            if (it.next()._P_Q(this.Obj)) counter++;
        return counter;
    }

    public Double card_processed_P_and_nQ() {
        Double counter = 0.0;
        Iterator<BaseProfile> it = this.KS.processed.iterator();
        while (it.hasNext())
            if (it.next()._P_nQ(this.Obj)) counter++;
        return counter;
    }

    public Double card_processed_nP_and_Q() {
        Double counter = 0.0;
        Iterator<BaseProfile> it = this.KS.processed.iterator();
        while (it.hasNext())
            if (it.next()._nP_Q(this.Obj)) counter++;
        return counter;
    }

    public Double card_processed_nP_and_nQ() {
        Double counter = 0.0;
        Iterator<BaseProfile> it = this.KS.processed.iterator();
        while (it.hasNext())
            if (it.next()._nP_nQ(this.Obj)) counter++;
        return counter;
    }

    public Double card_notProcessed_P_and_Q() {
        Double counter = 1.0;
        for (int i = 0; (i + samplingFreq) < KS.notProcessed.size(); i += samplingFreq) {
            if (KS.notProcessed.get(i)._P_Q(Obj)) counter++;
        }
        Double w_processed = this.card_processed_P_and_Q() == 0 ? 0 : (this.card_processed_P_and_Q() / KS.processed.size());
        Double w_notProcessed = KS.notProcessed.isEmpty() ? 0 : (counter * samplingFreq) / KS.notProcessed.size();
        return this.roundToDouble((degreeOfProcessing * w_processed + (1 - degreeOfProcessing) * w_notProcessed) * KS.notProcessed.size());
    }

    public Double card_notProcessed_P_and_nQ() {
        Double counter = 1.0;
        for (int i = 0; (i + samplingFreq) < KS.notProcessed.size(); i += samplingFreq) {
            if (KS.notProcessed.get(i)._P_nQ(Obj)) counter++;
        }
        Double w_processed = this.card_processed_P_and_nQ() == 0 ? 0 : (this.card_processed_P_and_nQ() / KS.processed.size());
        Double w_notProcessed = KS.notProcessed.isEmpty() ? 0 : (counter * samplingFreq) / KS.notProcessed.size();
        return this.roundToDouble((degreeOfProcessing * w_processed + (1 - degreeOfProcessing) * w_notProcessed) * KS.notProcessed.size());
    }

    public Double card_notProcessed_nP_and_Q() {
        Double counter = 1.0;
        for (int i = 0; (i + samplingFreq) < KS.notProcessed.size(); i += samplingFreq) {
            if (KS.notProcessed.get(i)._nP_Q(Obj)) counter++;
        }
        Double w_processed = this.card_processed_nP_and_Q() == 0 ? 0 : (this.card_processed_nP_and_Q() / KS.processed.size());
        Double w_notProcessed = KS.notProcessed.isEmpty() ? 0 : (counter * samplingFreq) / KS.notProcessed.size();
        return this.roundToDouble((degreeOfProcessing * w_processed + (1 - degreeOfProcessing) * w_notProcessed) * KS.notProcessed.size());
    }

    public Double card_notProcessed_nP_and_nQ() {
        Double counter = 1.0;
        for (int i = 0; (i + samplingFreq) < KS.notProcessed.size(); i += samplingFreq) {
            if (KS.notProcessed.get(i)._nP_nQ(Obj)) counter++;
        }
        Double w_processed = this.card_processed_nP_and_nQ() == 0 ? 0 : (this.card_processed_nP_and_nQ() / KS.processed.size());
        Double w_notProcessed = KS.notProcessed.isEmpty() ? 0 : (counter * samplingFreq) / KS.notProcessed.size();
        return this.roundToDouble((degreeOfProcessing * w_processed + (1 - degreeOfProcessing) * w_notProcessed) * KS.notProcessed.size());
    }

    public Double card_all_PQ() {
        return this.roundToDouble((card_notProcessed_P_and_Q() + card_notProcessed_P_and_nQ() + card_notProcessed_nP_and_Q() + card_notProcessed_nP_and_nQ()
                + card_processed_P_and_Q() + card_processed_P_and_nQ() + card_processed_nP_and_Q() + card_processed_nP_and_nQ()));
        // poprawka na potrzeby CMST 2016
//        return (double)(KS.processed.size() + KS.notProcessed.size());
    }

    public Boolean epsilonCentred(Thresholds t, int[] values) {
        Double[] cardsAll = new Double[]{card_processed_P_and_Q(), card_processed_P_and_nQ(), card_processed_nP_and_Q(), card_processed_nP_and_nQ()};
        ArrayList<Double> cardsToCheck = new ArrayList<Double>();
        ArrayList<Double> cards = new ArrayList<Double>();
        for (int i = 1; i <= 4; i++) {
            if (this.contain(i, values))
                cards.add(cardsAll[i - 1]);
            else
                cardsToCheck.add(cardsAll[i - 1]);
        }
        if ((Collections.max(cards) - Collections.min(cards)) <= t.epsilon) {
            Iterator<Double> it = cardsToCheck.iterator();
            while (it.hasNext()) {
                ArrayList<Double> cardsTemp = new ArrayList<Double>();
                cardsTemp.addAll(cards);
                cardsTemp.add(it.next());
                if ((Collections.max(cardsTemp) - Collections.min(cardsTemp)) <= t.epsilon)
                    return false;  // not maximum set size
            }
            return true;
        } else
            return false;
    }

    private Boolean contain(int i, int[] array) {
        for (int a : array)
            if (a == i)
                return true;
        return false;
    }

    // OR P∨Q
    public Double lambda_P_or_Q() {
        Double counter = card_processed_P_and_Q() + card_processed_P_and_nQ() + card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_P_or_nQ() {
        Double counter = card_processed_P_and_Q() + card_processed_nP_and_nQ() + card_processed_P_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_or_Q() {
        Double counter = card_processed_nP_and_nQ() + card_processed_P_and_Q() + card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_or_nQ() {
        Double counter = card_processed_nP_and_Q() + card_processed_P_and_nQ() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    // NOR P⊻Q
    public Double lambda_P_nor_Q() {
        Double counter = card_processed_P_and_nQ() + card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_P_nor_nQ() {
        Double counter = card_processed_P_and_Q() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_nor_Q() {
        Double counter = card_processed_nP_and_nQ() + card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_nor_nQ() {
        Double counter = card_processed_nP_and_Q() + card_processed_P_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    // AND P∧Q
    public Double lambda_P_and_Q() {
        Double counter = card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_P_and_nQ() {
        Double counter = card_processed_P_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_and_Q() {
        Double counter = card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_and_nQ() {
        Double counter = card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    // IMPLIES P⇒Q
    public Double lambda_P_implies_Q() {
        Double counter = card_processed_P_and_Q() + card_processed_nP_and_Q() + card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_P_implies_nQ() {
        Double counter = card_processed_P_and_nQ() + card_processed_nP_and_Q() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_implies_Q() {
        Double counter = card_processed_nP_and_Q() + card_processed_P_and_nQ() + card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_implies_nQ() {
        Double counter = card_processed_nP_and_nQ() + card_processed_P_and_Q() + card_processed_P_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    // IMPLIES Q=>P
    public Double lambda_Q_implies_P() {
        Double counter = card_processed_P_and_Q() + card_processed_P_and_nQ() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_Q_implies_nP() {
        Double counter = card_processed_P_and_nQ() + card_processed_nP_and_Q() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nQ_implies_P() {
        Double counter = card_processed_nP_and_Q() + card_processed_P_and_nQ() + card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nQ_implies_nP() {
        Double counter = card_processed_nP_and_nQ() + card_processed_P_and_Q() + card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
        return this.roundToDouble(counter / card_all_PQ());
    }


    // EQUIVALENT P<=>Q
    public Double lambda_P_equivalent_Q() {
        Double counter = card_processed_P_and_Q() + card_processed_nP_and_nQ();
        if (counter == 0.0) return 0.0;
//        return counter / (card_processed_P_and_nQ() + card_notProcessed_P_and_nQ() + card_processed_P_and_Q() + card_notProcessed_P_and_Q() + card_processed_nP_and_nQ() + card_notProcessed_nP_and_nQ());
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_P_equivalent_nQ() {
        Double counter = card_processed_P_and_nQ() + card_processed_nP_and_Q();
        if (counter == 0.0) return 0.0;
//        return counter / (card_processed_P_and_nQ() + card_notProcessed_P_and_nQ() + card_processed_P_and_Q() + card_notProcessed_P_and_Q() + card_processed_nP_and_nQ() + card_notProcessed_nP_and_nQ());
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_equivalent_Q() {
        Double counter = card_processed_nP_and_Q() + card_processed_P_and_nQ();
        if (counter == 0.0) return 0.0;
//        return counter / (card_processed_P_and_nQ() + card_notProcessed_P_and_nQ() + card_processed_P_and_Q() + card_notProcessed_P_and_Q() + card_processed_nP_and_nQ() + card_notProcessed_nP_and_nQ());
        return this.roundToDouble(counter / card_all_PQ());
    }

    public Double lambda_nP_equivalent_nQ() {
        Double counter = card_processed_nP_and_nQ() + card_processed_P_and_Q();
        if (counter == 0.0) return 0.0;
//        return counter / (card_processed_P_and_nQ() + card_notProcessed_P_and_nQ() + card_processed_P_and_Q() + card_notProcessed_P_and_Q() + card_processed_nP_and_nQ() + card_notProcessed_nP_and_nQ());
        return this.roundToDouble(counter / card_all_PQ());
    }


    public String start() {
        String result = "";

        String[] sign = {
//                "p∧q", "p∧¬q", "¬p∧q", "¬p∧¬q",
//                "p∨q", "p∨¬q", "¬p∨q", "¬p∨¬q",
//                "p⊻q", "p⊻¬q", "¬p⊻q", "¬p⊻¬q",
//                "p⇒q", "p⇒¬q", "¬p⇒q", "¬p⇒¬q",
//                "q⇒p", "q⇒¬p", "¬q⇒p", "¬q⇒¬p",
                "q⇔p", "q⇔¬p", "¬q⇔p", "¬q⇔¬p"
        };

        for (int i = 0; i < sign.length; i++) {
//            result += lambda[i][0]+"; "+lambda[i][1]+"==0; "+lambda[i][2]+"!=0; "+"<br />";
            int t = threshold.length - (int)(i/4) - 1; // actual threshold
            if (threshold[t].getKnow(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "Know(" + sign[i] + ")\n"+ lambda[i] + "\n";
            else if (threshold[t].getBel(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "Bel(" + sign[i] + ")\n"+ lambda[i] + "\n";
            else if (threshold[t].getPos(lambda[i]) && conditions[i][0])
                result += "Pos(" + sign[i] + ")\n"+ lambda[i] + "\n";
        }
        if (result.isEmpty()) result += "";

//        result += "\nλ:\n";
//        for(int i=0; i<sign.length; i++){
//             result += sign[i]+": " + lambda[i] + "\n";
//        }

        return result;
    }

    public ArrayList<String[]> start_to_object() {
        this.fill();
        ArrayList<String[]> result = new ArrayList<String[]>();

        String[] sign = {
//                "p∧q", "p∧¬q", "¬p∧q", "¬p∧¬q",
//                "p∨q", "p∨¬q", "¬p∨q", "¬p∨¬q",
//                "p⊻q", "p⊻¬q", "¬p⊻q", "¬p⊻¬q",
//                "p⇒q", "p⇒¬q", "¬p⇒q", "¬p⇒¬q",
//                "q⇒p", "q⇒¬p", "¬q⇒p", "¬q⇒¬p",
                "p⇔q", "p⇔¬q", "¬p⇔q", "¬p⇔¬q"
        };



        for (int i = 0; i < sign.length; i++) {
//            result += lambda[i][0]+"; "+lambda[i][1]+"==0; "+lambda[i][2]+"!=0; "+"<br />";
            String[] c = new String[]{};
            int t = threshold.length - (int)(i/4) - 1; // actual threshold
            if (threshold[t].getKnow(lambda[i]) && conditions[i][0] && conditions[i][1])
                c = new String[]{"Know", (i%4==0 || i%4==1)?"[p]":"¬[p]", String.valueOf(sign[(int)(i / 4)].charAt(1)), (i%4==1 || i%4==3)?"[q]":"¬[q]" };
            else if (threshold[t].getBel(lambda[i]) && conditions[i][0] && conditions[i][1])
              c = new String[]{"Bel", (i%4==0 || i%4==1)?"[p]":"¬[p]", String.valueOf(sign[(int)(i / 4)].charAt(1)), (i%4==1 || i%4==3)?"[q]":"¬[q]" };
            else if (threshold[t].getPos(lambda[i]) && conditions[i][0])
                c = new String[]{"Pos", (i%4==0 || i%4==1)?"[p]":"¬[p]", String.valueOf(sign[(int)(i / 4)].charAt(1)), (i%4==1 || i%4==3)?"[q]":"¬[q]" };

            if(c.length>0)
                result.add(c);
        }

//        result += "\nλ:\n";
//        for(int i=0; i<sign.length; i++){
//             result += sign[i]+": " + lambda[i] + "\n";
//        }

        return result;

    }
    public ArrayList<HashMap<Integer, Double>> start_to_plot() {
        this.fill();
        ArrayList<HashMap<Integer, Double>> result = new  ArrayList<HashMap<Integer, Double>>();

        String[] sign = {
//                "p∧q", "p∧¬q", "¬p∧q", "¬p∧¬q",
//                "p∨q", "p∨¬q", "¬p∨q", "¬p∨¬q",
//                "p⊻q", "p⊻¬q", "¬p⊻q", "¬p⊻¬q",
//                "p⇒q", "p⇒¬q", "¬p⇒q", "¬p⇒¬q",
//                "q⇒p", "q⇒¬p", "¬q⇒p", "¬q⇒¬p",
                "p⇔q", "p⇔¬q", "¬p⇔q", "¬p⇔¬q"
        };

        for (int i = 0; i < sign.length; i++) {
//            result += lambda[i][0]+"; "+lambda[i][1]+"==0; "+lambda[i][2]+"!=0; "+"<br />";
            HashMap<Integer, Double> c = new HashMap<Integer, Double>();
            Double d = 0.00;
            switch (i){
                case 0: d=this.card_processed_P_and_Q();
                    break;
                case 1: d=this.card_processed_P_and_nQ();
                    break;
                case 2: d=this.card_processed_nP_and_Q();
                    break;
                case 3: d=this.card_processed_nP_and_nQ();
                    break;
            }
            c.put((int)((lambda[i])*100), this.roundToDouble(d / this.card_all_PQ() ));
//            if(!c.isEmpty())
            result.add(i, c);
        }

        return result;
    }

    public String start_to_html(boolean context) {
        String result = "";

        String[] sign = {
//         				"[p] &and; [q]","[p] &and; &not;[q]","&not;[p] &and; [q]","&not;[p] &and; &not;[q]",			// and
//     					"[p] &or; [q]","[p] &or; &not;[q]","&not;[p] &or; [q]","&not;[p] &or; &not;[q]",				// or
//     					"[p] &#8891; [q]","[p] &#8891; &not;[q]","&not;[p] &#8891; [q]","&not;[p] &#8891; &not;[q]",	// xor
//                "[p] &rArr; [q]", "[p] &rArr; &not;[q]", "&not;[p] &rArr; [q]", "&not;[p] &rArr; &not;[q]",        // implies
//                "[q] &rArr; [p]", "[q] &rArr; &not;[p]", "&not;[q] &rArr; [p]", "&not;[q] &rArr; &not;[p]",        // implies
                "[p] &hArr; [q]", "[p] &hArr; &not;[q]", "&not;[p] &hArr; [q]", "&not;[p] &hArr; &not;[q]"        // equivalnece
        };
        
        for (int i = 0; i < sign.length; i++) {
//            result += lambda[i]+":  "+"<br />";
            int t = threshold.length - (int)(i/4) - 1; // actual threshold
            if (threshold[t].getKnow(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "Know(" + sign[i] + "),<br />";
            else if (threshold[t].getBel(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "Bel(" + sign[i] + "),<br />";
            else if (threshold[t].getPos(lambda[i]) && conditions[i][0])
                result += "Pos(" + sign[i] + "),<br />";
        }
        if (result.isEmpty())
            result += "&#8709;";

        if(context){
            if(result.equals("&#8709;"))
                result = "K&rarr;{" + result + "}";
            else
                result = "K&rarr;{<br />" + result + "}";
        }

        if(Main.DEBUG) {
            result += "<BR />";
//            result += "<DIV STYLE='font-size: 60%;'>\n";
//            result += "<P><B>P &and; Q</B>: <SPAN STYLE='text-decoration: overline;'>C</SPAN>: " + card_processed_P_and_Q() + ",  <SPAN STYLE='text-decoration: underline;'>C</SPAN>: " + card_notProcessed_P_and_Q() + ",  <SPAN>&lambda;</SPAN>:" + (lambda[0]) + "</P>";
//            result += "<P><B>P &and; &not;Q</B>: <SPAN STYLE='text-decoration: overline;'>C</SPAN>: " + card_processed_P_and_nQ() + ",  <SPAN STYLE='text-decoration: underline;'>C</SPAN>: " + card_notProcessed_P_and_nQ() + ",  <SPAN>&lambda;</SPAN>:" + (lambda[1]) + "</P>";
//            result += "<P><B>&not;P &and; Q</B>: <SPAN STYLE='text-decoration: overline;'>C</SPAN>: " + card_processed_nP_and_Q() + ",  <SPAN STYLE='text-decoration: underline;'>C</SPAN>: " + card_notProcessed_nP_and_Q() + ",  <SPAN>&lambda;</SPAN>:" + (lambda[2]) + "</P>";
//            result += "<P><B>&not;P &and; &not;Q</B>: <SPAN STYLE='text-decoration: overline;'>C</SPAN>: " + card_processed_nP_and_nQ() + ",  <SPAN STYLE='text-decoration: underline;'>C</SPAN>: " + card_notProcessed_nP_and_nQ() + ",  <SPAN>&lambda;</SPAN>:" + (lambda[3]) + "</P>";
//            result += "</DIV>\n";
            result += "<TABLE STYLE='BORDER: 1PX HIDDEN BLACK;'><TR>\n";
            result += "<TD>\n";
                result += "<table>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%; text-decoration: underline;'>|C|</th>\n";
                        result += "<th STYLE='font-size: 60%;'>P</th>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;P</th>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_notProcessed_P_and_Q() / this.card_all_PQ() )) +");'>"+ (this.card_notProcessed_P_and_Q()) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_notProcessed_nP_and_Q() / this.card_all_PQ() )) +");'>"+ (this.card_notProcessed_nP_and_Q()) +"</td>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_notProcessed_P_and_nQ() / this.card_all_PQ() )) +");'>"+ (this.card_notProcessed_P_and_nQ()) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_notProcessed_nP_and_nQ() / this.card_all_PQ() )) +");'>"+ (this.card_notProcessed_nP_and_nQ()) +"</td>\n";
                    result += "</tr>\n";
                result += "</table>\n";
            result += "</TD>\n";
            result += "<TD>\n";
                result += "<table>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%; text-decoration: overline;'>|C|</th>\n";
                        result += "<th STYLE='font-size: 60%;'>P</th>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;P</th>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_processed_P_and_Q() / this.card_all_PQ() )) +");'>"+ (this.card_processed_P_and_Q()) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_processed_nP_and_Q() / this.card_all_PQ() )) +");'>"+ (this.card_processed_nP_and_Q()) +"</td>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_processed_P_and_nQ() / this.card_all_PQ() )) +");'>"+ (this.card_processed_P_and_nQ()) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (this.roundToDouble(this.card_processed_nP_and_nQ() / this.card_all_PQ() )) +");'>"+ (this.card_processed_nP_and_nQ()) +"</td>\n";
                    result += "</tr>\n";
                result += "</table>\n";
            result += "</TD>\n";
            result += "<TD>\n";
                result += "<table>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>&lambda;</th>\n";
                        result += "<th STYLE='font-size: 60%;'>P</th>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;P</th>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (lambda[0]) +");'>"+ (lambda[0]) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (lambda[1]) +");'>"+ (lambda[1]) +"</td>\n";
                    result += "</tr>\n";
                    result += "<tr>\n";
                        result += "<th STYLE='font-size: 60%;'>&not;Q</th>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (lambda[2]) +");'>"+ (lambda[2]) +"</td>\n";
                        result += "<td STYLE='background: rgba(0, 0, 0, "+ (lambda[3]) +");'>"+ (lambda[3]) +"</td>\n";
                    result += "</tr>\n";
                result += "</table>\n";
            result += "</TD>\n";
            result += "</TR></TABLE>\n";
        }
//        result += "\nλ:\n";
//        for(int i=0; i<sign.length; i++){
//             result += sign[i]+": " + lambda[i][0] + "\n";
//        }

        return result;
    }

    public String start_to_latex(boolean context) {
        String result = "";

        String[] sign = {
//                "[p] \\wedge [q]","[p] \\wedge \\neg [q]","\\neg [p] \\wedge [q]","\\neg [p] \\wedge \\neg [q]",			// and
//                "[p] \\vee [q]","[p] \\vee \\neg [q]","\\neg [p] \\vee [q]","\\neg [p] \\vee \\neg [q]",				// or
//                "[p] \\veebar [q]","[p] \\veebar \\neg [q]","\\neg [p] \\veebar [q]","\\neg [p] \\veebar \\neg [q]",	// xor
//                "[p] \\Rightarrow [q]", "[p] \\Rightarrow \\neg [q]", "\\neg [p] \\Rightarrow [q]", "\\neg [p] \\Rightarrow \\neg [q]" ,        // implies
//                "[p] \\Leftarrow [q]", "[p] \\Leftarrow \\neg [q]", "\\neg [p] \\Leftarrow [q]", "\\neg [p] \\Leftarrow \\neg [q]" ,        // implies
                "[p] \\Leftrightarrow [q]", "[p] \\Leftrightarrow \\neg [q]", "\\neg [p] \\Leftrightarrow [q]", "\\neg [p] \\Leftrightarrow \\neg [q]"        // equivalnece
        };


//        result += "C1: " + card_processed_P_and_Q() + " + " + card_notProcessed_P_and_Q() + ";<br/>";
//        result += "C2: " + card_processed_P_and_nQ() + " + " + card_notProcessed_P_and_nQ() + ";<br/>";
//        result += "C3: " + card_processed_nP_and_Q() + " + " + card_notProcessed_nP_and_Q() + ";<br/>";
//        result += "C4: " + card_processed_nP_and_nQ() + " + " + card_notProcessed_nP_and_nQ() + ";<br/>";
        for (int i = 0; i < sign.length; i++) {
//            result += lambda[i]+":  "+"<br />";
            int t = threshold.length - (int)(i/4) - 1; // actual threshold
            if (threshold[t].getKnow(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "$Know(" + sign[i] + ")$, \\newline ";
            else if (threshold[t].getBel(lambda[i]) && conditions[i][0] && conditions[i][1])
                result += "$Bel(" + sign[i] + ")$, \\newline ";
            else if (threshold[t].getPos(lambda[i]) && conditions[i][0])
                result += "$Pos(" + sign[i] + ")$, \\newline ";
        }
        if (result.isEmpty())
            result += "$\\emptyset$";

        if(context)
            result = "$K \\rightarrow $\\{" + result + "\\}";

//        result = "\n \\scalebox{0.7} {\n" + result + "\n } \n";
        result = "\\footnotesize" + result + "\n";

//        result = "\\pbox{5.5cm}{" + result + "} \n";

        if(Main.DEBUG) {
            String debug = "";
            String color = Main.blackWhite?"black":"";
//            debug += "\\\\ \n";
            if(!Main.blackWhite)
                color = "red";
            debug += "\\scalebox{0.6}{\n";
            debug +=         "\\begin{tabular}{c|c|c||} $\\left| \\underline{C} \\right|$ & $P$ & $\\neg P$ \\\\ \\hline $Q$ & \\cellcolor{"+color+"!"+ (int)((this.roundToDouble(this.card_notProcessed_P_and_Q() / this.card_all_PQ() ))*100) +"}"+ (this.card_notProcessed_P_and_Q().intValue()) +
                    " & \\cellcolor{"+color+"!"+ (int)((this.roundToDouble(this.card_notProcessed_nP_and_Q() / this.card_all_PQ() ))*100) +"}"+ (this.card_notProcessed_nP_and_Q().intValue()) +"\\\\ \\hline " +
                    "$\\neg Q$ & \\cellcolor{"+color+"!"+ (int)((this.roundToDouble(this.card_notProcessed_P_and_nQ() / this.card_all_PQ() ))*100) +"}"+ (this.card_notProcessed_P_and_nQ().intValue()) +
                    " & \\cellcolor{"+color+"!"+ (int)((this.roundToDouble(this.card_notProcessed_nP_and_nQ() / this.card_all_PQ() ))*100) +"}"+ (this.card_notProcessed_nP_and_nQ().intValue()) +"\\\\ \\hline " +
                    "\n \\end{tabular}\n";
            if(!Main.blackWhite)
                color = "green";
            debug +=         "\\begin{tabular}{c|c|c||} $\\left| \\overline{C} \\right|$ & $P$ & $\\neg P$ \\\\ \\hline $Q$ & \\cellcolor{"+color+"!"+ ((this.roundToDouble(this.card_processed_P_and_Q() / this.card_all_PQ() ))*100) +"}"+ (this.card_processed_P_and_Q().intValue()) +
                    " & \\cellcolor{"+color+"!"+ ((this.roundToDouble(this.card_processed_nP_and_Q() / this.card_all_PQ() ))*100) +"}"+ (this.card_processed_nP_and_Q().intValue()) +"\\\\ \\hline " +
                    "$\\neg Q$ & \\cellcolor{"+color+"!"+ ((this.roundToDouble(this.card_processed_P_and_nQ() / this.card_all_PQ() ))*100) +"}"+ (this.card_processed_P_and_nQ().intValue()) +
                    " & \\cellcolor{"+color+"!"+ ((this.roundToDouble(this.card_processed_nP_and_nQ() / this.card_all_PQ() ))*100) +"}"+ (this.card_processed_nP_and_nQ().intValue()) +"\\\\ \\hline " +
                    "\n \\end{tabular}\n";
//            result += "}\n";
//            result += "\\scalebox{0.6}{\n";
            if(!Main.blackWhite)
                color = "blue";
            debug +=         "\\begin{tabular}{c|c|c|} $\\lambda$\\% & $P$ & $\\neg P$ \\\\ \\hline $Q$ & \\cellcolor{"+color+"!"+ (int)((lambda[0])*100) +"}"+ ((int)((lambda[0])*100)) +
                    " & \\cellcolor{"+color+"!"+ (int)((lambda[2])*100) +"}"+ (int)((lambda[2])*100) +"\\\\ \\hline " +
                    "$\\neg Q$ & \\cellcolor{"+color+"!"+ (int)((lambda[1])*100) +"}"+ (int)((lambda[1])*100) +
                    " & \\cellcolor{"+color+"!"+ (int)((lambda[3])*100) +"}"+ (int)((lambda[3])*100) +"\\\\ \\hline " +
                    "\n \\end{tabular}\n";
            debug += "}\n";
            result = debug + result;
        }

        return result + "\n";
    }

    private double roundToDouble(double a){
        return (double)Math.round(a * 100) / 100;
    }

}