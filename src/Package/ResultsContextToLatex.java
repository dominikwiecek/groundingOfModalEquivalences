package Package;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dominik
 */
public class ResultsContextToLatex {
    String P;
    String P_attr;
    String Q;
    String Q_attr;
    String ContextMethod;
    Map<String, String> Contex;
    Episodes KnowledgeBase;
    int Example;


    Double e = 0.02;
    Double set1_minPos = 0.00;
    Thresholds[] set_1 = {
            new Thresholds(set1_minPos, 0.64, 0.64, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds((3 * set1_minPos + 2 * e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // alternative
            new Thresholds((2 * set1_minPos + e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // exclusive
            new Thresholds(0.0, 0.6, 0.6, 0.95, 0.95, e), // implication
            new Thresholds(0.0, 0.5, 0.5, 1.0, 1.0, e) // equivalence
    };

    public ResultsContextToLatex(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex) throws Exception {
        this(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 1);
    }

    public ResultsContextToLatex(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex, int Example) throws Exception {
        DataBase db = new DataBase();
        String query = "SELECT * FROM " + table + " " + additionalOptions;
        this.KnowledgeBase = new Episodes(db.query(query), P, P_attr, Q, Q_attr);
        this.P=P;
        this.P_attr=P_attr;
        this.Q=Q;
        this.Q_attr=Q_attr;
        this.ContextMethod=contextMethod;
        this.Contex=contex;
        this.Example=Example;

        FileWriter fw = new FileWriter("results.tex");
//        fw.write("<html><head><title>Grounding modal statements - example</title><style type=\"text/css\">table, td {BORDER: 1PX SOLID BLACK; BORDER-COLLAPSE:COLLAPSE; BORDER-SPACING: 10PX;}</style></head>\n<body>\n");
//        fw.write("$&Omega;("+P+" \\Leftrightarrow "+Q+")$\n");
//        fw.write("$&lambda: " + set_1[4].MinPos + " Pos " + set_1[4].MaxPos + " Bel " + set_1[4].MaxBel + " Know 1.0\n$");
////        fw.write("<i>shallow-level projection with deep sampling</i><br />\n");
//        fw.write("@params(step = "+(Main.step)+"; samplingFreq = "+(Main.samplingFreq)+"; d_max = "+(Main.d_max)+";)\n");
//        fw.write("\n");
//        this.Main.object_number = 1;
        this.generate(fw);
//        fw.write("Query: \n" + query + "<br /><br />\n");
//        fw.write("Knowledge Base:<br />\n");
//        this.generate_knowledbase_table(fw);
//        fw.write("</body>\n</html>");
        fw.close();
//        File texFile = new File("results.tex");
//        Desktop.getDesktop().open(texFile);
    }



    public void generate(FileWriter fw) throws IOException {
        String Protoform = Main.convertParams("$\\Omega([p] \\Leftrightarrow [q])$", this.P, this.P_attr, this.Q, this.Q_attr);
//        fw.write("\\begin{table}[H]\n" +
//                "   \\caption{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy "+ Protoform +", " +
//                "$\\lambda^{\\Leftrightarrow}_{minPos} = "+set_1[4].MinPos+" < \\lambda^{\\Leftrightarrow}_{minBel} = "+set_1[4].MinBel+" < \\lambda^{\\Leftrightarrow}_{maxBel} = \\lambda^{\\Leftrightarrow}_{Know} = "+set_1[4].MinKnow+"$}\n" +
//                "   \\label{tab:Zestawienie"+this.Example+"}\n" +
//                "   \\renewcommand{\\arraystretch}{0.8}\n" +
//                "   \\centering\n" +
//                "   \\hspace*{-2cm}\n");

        fw.write("\\LTcapwidth=\\textwidth\n");
        fw.write("\\setlength\\LTleft{-2cm}\n");
//        fw.write("\\setlength\\LTright{-1cm}\n");
        fw.write("\\begin{longtable}{|p{0.5cm}|p{5.5cm}|p{5.5cm}|p{5.5cm}|} \n");
        fw.write("\\hline \n \\%");
        switch(this.Example){
            case 1:
                fw.write(" & \\footnotesize " + "context free");
                fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for processed episodes \\newline $K="+this.contextToString()+"$");
                fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for all episodes \\newline $K="+this.contextToString()+"$");
                break;
            case 2:
                fw.write(" & \\footnotesize " + "context free");
                fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for processed episodes \\newline $K="+this.contextToString()+"$");
                fw.write(" & \\footnotesize " + "A'priori \\newline min. support: " + Main.min_support);
                break;
            default:
                fw.write(" & \\footnotesize " + "context free");
                fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for processed episodes \\newline $K="+this.contextToString()+"$");
                fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for all episodes \\newline $K="+this.contextToString()+"$");
                break;
        }
        fw.write("\\\\ \\hline \n");
        for (double i = 0.00; ((double)Math.round(i * 100) / 100) <= 1.0; i = i + Main.step){
            generate_row(i, fw);
        }
        fw.write("\n \\caption{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy "+ Protoform +", ");
        fw.write("$\\lambda^{\\Leftrightarrow}_{minPos} = "+set_1[4].MinPos+" < \\lambda^{\\Leftrightarrow}_{minBel} = "+set_1[4].MinBel+" < \\lambda^{\\Leftrightarrow}_{maxBel} = \\lambda^{\\Leftrightarrow}_{Know} = "+set_1[4].MinKnow+"$}\n");
        fw.write("\\label{tab:Zestawienie"+this.Example+"}\n");
        fw.write("\\end{longtable}\n");
//        fw.write("\\end{tabular}\n   \\end{table}\n");

    }

    public void generate_row(Double process, FileWriter fw) throws IOException {
        String col0 = String.valueOf(Math.round(process * 100));
        String col1 = "";
        String col2 = "";
        String col3 = "";
        switch(this.Example){
            case 1:
                col1 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, this.KnowledgeBase.getEpisodes()), Main.object_number).start_to_latex(false);
                col2 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeState(process, "A", this.Contex), Main.object_number).start_to_latex(true);
                col3 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_latex(true);
                break;
            case 2:
                col1 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, this.KnowledgeBase.getEpisodes()), Main.object_number).start_to_latex(false);
                col2 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_latex(true);
                col3 = new Apriori().generateLatex(process, this.KnowledgeBase.getEpisodes(), Main.object_number);
                break;
            default:
                col1 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, this.KnowledgeBase.getEpisodes()), Main.object_number).start_to_latex(false);
                col2 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeState(process, "A", this.Contex), Main.object_number).start_to_latex(true);
                col3 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_latex(true);
                break;
        }

        String write = "\\footnotesize " + col0 + " & " + col1 + " & " + col2 + " & " + col3 +  " \\\\ \\hline \n";
        write = Main.convertParams(write, this.P, this.P_attr, this.Q, this.Q_attr);
        fw.write(write);
    }

//    public void generate_knowledbase_table(FileWriter fw) throws IOException {
//        String write =  "";
//        write += "<table width=\"50%\">\n";
//        write += "<tr><th>T</th><th>p<sup>+</sup></th><th>p<sup>-</sup></th><th>q<sup>+</sup></th><th>q<sup>-</sup></th></tr>\n";
//        ArrayList<BaseProfile> KS = this.KnowledgeBase.getEpisodes();
//        for (int i = 0; i < KS.size(); i++) {
//            write += "<tr><th>t<sub>" + (KS.size() - i) + "</sub></th>";
//            BaseProfile BP = KS.get(i);
//            write += "<td>" + BP.toString(1) + "</td>\n";
//            write += "<td>" + BP.toString(2) + "</td>\n";
//            write += "<td>" + BP.toString(3) + "</td>\n";
//            write += "<td>" + BP.toString(4) + "</td>\n";
//            write += "</tr>\n";
//        }
//        write += "</table>\n";
//        write = Main.convertParams(write, this.P, this.P_attr, this.Q, this.Q_attr);
//        fw.write(write);
//    }

    private String contextToString(){
        String r = "";
        for (Map.Entry<String, String> c : this.Contex.entrySet()){
//            if(!c.getValue().equals(a.get(contex.getKey())))
//                counter++;
            r += c.getKey();
            if(c.getValue().equals(this.P_attr))
                r += "^{+},";
            else
                r += "^{-},";
        }
        if(r.isEmpty())
            r += "\\emptyset";
        return "\\{" +r + "\\}";
    }
}