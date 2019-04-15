package Package;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Episodes {
    String P;
    String P_attr;
    String Q;
    String Q_attr;


    public List<Map<String, String>> RawData;

    public Episodes(List<Map<String, String>> RawData, String P, String P_attr, String Q, String Q_attr) {
        this.RawData=RawData;
        this.P=P;
        this.P_attr=P_attr;
        this.Q=Q;
        this.Q_attr=Q_attr;
    }

    public ArrayList<BaseProfile> getEpisodes() {
        return this.getEpisodes(this.RawData);
    }

    public ArrayList<BaseProfile> getEpisodes(List<Map<String, String>> RawData) {
        ArrayList<BaseProfile> KS = new ArrayList<BaseProfile>();
        for (Map<String, String> row : RawData){
            Integer[] P_plus = new Integer[]{};
            Integer[] P_minus = new Integer[]{};
            Integer[] Q_plus = new Integer[]{};
            Integer[] Q_minus = new Integer[]{};

            if(this.P_attr.equals(row.get(this.P)))
                P_plus = new Integer[]{1};
            else
                P_minus = new Integer[]{1};

            if("?".equals(row.get(this.P)) || " ".equals(row.get(this.P))  || "NULL".equals(row.get(this.P)))
                P_plus = P_minus =  new Integer[]{};

            if(this.Q_attr.equals(row.get(this.Q)))
                Q_plus = new Integer[]{1};
            else
                Q_minus = new Integer[]{1};

            if("?".equals(row.get(this.Q)) || " ".equals(row.get(this.Q))  || "NULL".equals(row.get(this.Q)))
                Q_plus = Q_minus =  new Integer[]{};

            KS.add(new BaseProfile(P_plus, P_minus, Q_plus, Q_minus));
        }
        return KS;
    }

    // context only processed
    public KnowledgeState getContextKnowledgeState(Double process, String contextMethod, Map<String, String> contex) {
        KnowledgeState KS = new KnowledgeState();
        ArrayList<Map<String, String>> RawData = (ArrayList<Map<String,String>>)this.RawData;
        int counter = (int) (RawData.size() * process);
        ArrayList<Map<String, String>> RawDataProcessed = new ArrayList<Map<String, String>>();
        KS.degreeOfProcessing = process;
        if (KS.degreeOfProcessing < 1.0)
            KS.notProcessed = this.getEpisodes(new ArrayList<Map<String, String>>(RawData.subList(counter, RawData.size()-1)));
        else
            KS.notProcessed = this.getEpisodes(new ArrayList<Map<String, String>>());

        for (Map<String, String> row : new ArrayList<Map<String, String>>(RawData.subList(0, counter))){
            if(contextMethod.equals("A")){
                if(this.distanceBP(row, contex) <= Main.d_max)
                    RawDataProcessed.add(row);
            }
        }

        KS.processed = this.getEpisodes(RawDataProcessed);
        return KS;
    }

    // context all
    public KnowledgeState getContextKnowledgeStateAll(Double process, String contextMethod, Map<String, String> contex) {
        ArrayList<Map<String, String>> contextData = new ArrayList<Map<String, String>>();
        for (Map<String, String> row : new ArrayList<Map<String, String>>(this.RawData)){
            if(contextMethod.equals("A")){
                if(this.distanceBP(row, contex) <= Main.d_max)
                    contextData.add(row);
            }
        }
        return new KnowledgeState(process, this.getEpisodes(contextData));
    }

    private Double distanceBP(Map<String, String> a, Map<String, String> b){
        if(b.size() == 0)
            return 0.0;
        Double max = (double) b.size();
        Double counter = 0.00;
        for (Map.Entry<String, String> contex : b.entrySet()){
            if(!contex.getValue().equals(a.get(contex.getKey())))
                counter++;
        }
        return (double)(counter / max);
    }
}
