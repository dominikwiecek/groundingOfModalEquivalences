package Package;

import java.util.ArrayList;

/**
 * @author Dominik
 */
public class KnowledgeState {
    Double degreeOfProcessing;
    ArrayList<BaseProfile> processed;
    ArrayList<BaseProfile> notProcessed;

    public KnowledgeState() {
        processed = new ArrayList<BaseProfile>();
        notProcessed = new ArrayList<BaseProfile>();
        this.degreeOfProcessing = 1.0;
    }

    public KnowledgeState(Double degreeOfProcessing, ArrayList<BaseProfile> Data) {
        int counter = (int) (Data.size() * degreeOfProcessing);
        if (counter < 0) counter = 0;
        this.degreeOfProcessing = degreeOfProcessing;
        processed = new ArrayList<BaseProfile>(Data.subList(0, counter));
        if (degreeOfProcessing < 1.0 && Data.size()>0)
            notProcessed = new ArrayList<BaseProfile>(Data.subList(counter, Data.size()-1));
        else
            notProcessed = new ArrayList<BaseProfile>();
    }

}

