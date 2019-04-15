package Package;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Dominik
 */
public class ResultsContextToHTML {
    String P;
    String P_attr;
    String Q;
    String Q_attr;
    String ContextMethod;
    Map<String, String> Contex;
    Episodes KnowledgeBase;



    Double e = 0.02;
    Double set1_minPos = 0.00;
    Thresholds[] set_1 = {
            new Thresholds(set1_minPos, 0.64, 0.64, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds((3 * set1_minPos + 2 * e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // alternative
            new Thresholds((2 * set1_minPos + e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // exclusive
            new Thresholds(0.0, 0.6, 0.6, 0.95, 0.95, e), // implication
            new Thresholds(0.0, 0.5, 0.5, 1.0, 1.0, e) // equivalence
    };



    public ResultsContextToHTML(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex) throws Exception {
        DataBase db = new DataBase();
        String query = "SELECT * FROM " + table + " " + additionalOptions;
        this.KnowledgeBase = new Episodes(db.query(query), P, P_attr, Q, Q_attr);
        this.P=P;
        this.P_attr=P_attr;
        this.Q=Q;
        this.Q_attr=Q_attr;
        this.ContextMethod=contextMethod;
        this.Contex=contex;

        FileWriter fw = new FileWriter("results.html");
        fw.write("<html><head><title>Grounding modal statements - example</title><style type=\"text/css\">table, td {BORDER: 1PX SOLID BLACK; BORDER-COLLAPSE:COLLAPSE; BORDER-SPACING: 10PX;}</style></head>\n<body>\n");
        fw.write("&Omega;("+P+" &hArr; "+Q+")<br />\n");
        fw.write("&lambda;<sub>&hArr;</sub>: " + set_1[4].MinPos + " &le;Pos&le;" + set_1[4].MaxPos + " &le;Bel&le;" + set_1[4].MaxBel + " &le;Know&le;1.0<br />\n");
        fw.write("<i>shallow-level projection with deep sampling</i><br />\n");
        fw.write("@params(step = "+(Main.step)+"; samplingFreq = "+(Main.samplingFreq)+"; d_max = "+(Main.d_max)+";)<br />\n");
        fw.write("<br />\n");
//        this.Main.object_number = 1;
        this.generate(fw);
//        fw.write("Query: \n" + query + "<br /><br />\n");
//        fw.write("Knowledge Base:<br />\n");
//        this.generate_knowledbase_table(fw);
        fw.write("</body>\n</html>");
        fw.close();
//        File htmlFile = new File("results.html");
//        Desktop.getDesktop().open(htmlFile);
    }



    public void generate(FileWriter fw) throws IOException {
        fw.write("<table>\n");
        fw.write("<tr>\n");
        fw.write("  <td>Results:</td>");
        fw.write("  <td>context free</td>");
        fw.write("  <td>strategy <b>"+this.ContextMethod+"</b> for processed episodes<br />K="+this.Contex.toString()+"</td>");
        fw.write("  <td>strategy <b>"+this.ContextMethod+"</b> for all episodes<br />K="+this.Contex.toString()+"</td>");
        fw.write("  <td>A'priori <br /> Min. support: "+Main.min_support+"</td>");
        fw.write("</tr>\n");
        for (double i = 0.00; ((double)Math.round(i * 100) / 100) <= 1.0; i = i + Main.step){
            generate_row(i, fw);
        }
        fw.write("</table><br />\n");

    }

    public void generate_row(Double process, FileWriter fw) throws IOException {
        String col0 = String.valueOf(Math.round(process * 100));
//        String col0 = String.valueOf((int) (process * 100));
        String col1 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, this.KnowledgeBase.getEpisodes()), Main.object_number).start_to_html(false);
        String col2 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeState(process, "A", this.Contex), Main.object_number).start_to_html(true);
        String col3 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_html(true);
//        String col3 = Arrays.deepToString(new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_object().toArray());
        String col4 = new Apriori().generateHTML(process, this.KnowledgeBase.getEpisodes(), Main.object_number);
        String write = "<tr><td>" + col0 + "%</td>\n<td>" + col1 + "</td>\n<td>" + col2 + "</td>\n<td>" + col3 + "</td>\n<td>" + col4 + "</td>\n</tr>";
        write = Main.convertParams(write, this.P, this.P_attr, this.Q, this.Q_attr);
        fw.write(write);
    }

    public void generate_knowledbase_table(FileWriter fw) throws IOException {
        String write =  "";
        write += "<table width=\"50%\">\n";
        write += "<tr><th>T</th><th>p<sup>+</sup></th><th>p<sup>-</sup></th><th>q<sup>+</sup></th><th>q<sup>-</sup></th></tr>\n";
        ArrayList<BaseProfile> KS = this.KnowledgeBase.getEpisodes();
        for (int i = 0; i < KS.size(); i++) {
            write += "<tr><th>t<sub>" + (KS.size() - i) + "</sub></th>";
            BaseProfile BP = KS.get(i);
            write += "<td>" + BP.toString(1) + "</td>\n";
            write += "<td>" + BP.toString(2) + "</td>\n";
            write += "<td>" + BP.toString(3) + "</td>\n";
            write += "<td>" + BP.toString(4) + "</td>\n";
            write += "</tr>\n";
        }
        write += "</table>\n";
        write = Main.convertParams(write, this.P, this.P_attr, this.Q, this.Q_attr);
        fw.write(write);
    }

    private String contextToString(){
        String r = "";
        for (Map.Entry<String, String> c : this.Contex.entrySet()){
//            if(!c.getValue().equals(a.get(contex.getKey())))
//                counter++;
            r += c.getValue();
            if(c.getValue().equals(this.P_attr))
                r += "<sup>+</sup>,";
            else
                r += "<sup>-</sup>,";
        }
        if(r.isEmpty())
            r += "&#8709;";
        return "{" +r + "}";
    }
}