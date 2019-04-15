package Package;

import java.util.*;

public class Apriori {

    public String generateHTML(Double process, ArrayList<BaseProfile> Data, Integer Obj) {
        String result="";
        AprioriFrequentItemsetGenerator<String> generator = new AprioriFrequentItemsetGenerator<String>();
        List<Set<String>> itemsetList = new ArrayList<>();

        KnowledgeState KS = new KnowledgeState(process, Data);
        ArrayList<BaseProfile> processed = KS.processed;

        Iterator<BaseProfile> it = processed.iterator();
        while(it.hasNext()){
            BaseProfile bp = it.next();
            ArrayList<String> al = new ArrayList<String>();
            if(bp.Pplus.contains(Obj))
                al.add("[p]");
            else if(bp.Pminus.contains(Obj))
                al.add("&not;[p]");
            if(bp.Qplus.contains(Obj))
                al.add("[q]");
            else if(bp.Qminus.contains(Obj))
                al.add("&not;[q]");
            itemsetList.add(new HashSet(Arrays.asList(al.toArray())));
        }

       if(itemsetList.isEmpty())
           result = "---";
       else {
            FrequentItemsetData<String> data = generator.generate(itemsetList, Main.min_support);
            for (Set<String> itemset : data.getFrequentItemsetList()) {
                String item = itemset.toString();
                result += String.format("%9s, %1.1f <br />\n", item.replace(",", "&rArr;"), data.getSupport(itemset));
            }
        }
        return result;
    }

    public String generateLatex(Double process, ArrayList<BaseProfile> Data, Integer Obj) {
        String result="";
        AprioriFrequentItemsetGenerator<String> generator = new AprioriFrequentItemsetGenerator<String>();
        List<Set<String>> itemsetList = new ArrayList<>();

        KnowledgeState KS = new KnowledgeState(process, Data);
        ArrayList<BaseProfile> processed = KS.processed;

        Iterator<BaseProfile> it = processed.iterator();
        while(it.hasNext()){
            BaseProfile bp = it.next();
            ArrayList<String> al = new ArrayList<String>();
            if(bp.Pplus.contains(Obj))
                al.add("$[p]$");
            else if(bp.Pminus.contains(Obj))
                al.add("$\\neg [p]$");
            if(bp.Qplus.contains(Obj))
                al.add("$[q]$");
            else if(bp.Qminus.contains(Obj))
                al.add("$\\neg [q]$");
            itemsetList.add(new HashSet(Arrays.asList(al.toArray())));
        }

        if(itemsetList.isEmpty())
            result = "---";
        else {
            FrequentItemsetData<String> data = generator.generate(itemsetList, Main.min_support);
            for (Set<String> itemset : data.getFrequentItemsetList()) {
                String item = itemset.toString().replace(",", "$\\rightarrow$");
                result += String.format("\\{%9s, %1.1f\\}, \n", item, data.getSupport(itemset));
            }
        }
        return "\\footnotesize "+result;
    }
}