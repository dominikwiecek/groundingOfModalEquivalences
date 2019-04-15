package Package;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Dominik
 */
public class ResultsToLatex {
    String P;
    String P_attr;
    String Q;
    String Q_attr;
    Episodes KnowledgeBase;

    Double step = 0.10;
    Double samplingFreq = 2.0;
    int object_number = 1;

    Double e = 0.02;
    Double set1_minPos = 0.00;
    Thresholds[] set_1 = {
            new Thresholds(set1_minPos, 0.64, 0.64, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds((3 * set1_minPos + 2 * e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // alternative
            new Thresholds((2 * set1_minPos + e), (1 - 3 * set1_minPos - 3 * e), (1 - 3 * set1_minPos - 3 * e), 0.95, 0.95, e), // exclusive
            new Thresholds(0.2, 0.6, 0.6, 0.95, 0.95, e), // implication
            new Thresholds(0.2, 0.6, 0.6, 0.95, 0.95, e) // equivalence
    };
    Double set2_minPos = 0.15;
    Thresholds[] set_2 = {
            new Thresholds(set2_minPos, 0.51, 0.51, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds((2 * set2_minPos + e), (1 - 3 * set2_minPos - 3 * e), (1 - 3 * set2_minPos - 3 * e), 0.95, 0.95, e), // alternative
            new Thresholds((2 * set2_minPos + e), (1 - 3 * set2_minPos - 3 * e), (1 - 3 * set2_minPos - 3 * e), 0.95, 0.95, e), // exclusive
            new Thresholds(0.2, 0.5, 0.5, 0.95, 0.95, e), // implication
            new Thresholds(0.2, 0.5, 0.5, 0.95, 0.95, e) // equivalence
    };
    Double set3_minPos = 0.20;
    Thresholds[] set_3 = {
            new Thresholds(set3_minPos, 0.40, 0.40, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds(0.10, 0.2, 0.2, 0.95, 0.95, e), // alternative
            new Thresholds(0.10, 0.2, 0.2, 0.95, 0.95, e), // exclusive
            new Thresholds(0.2, 0.6, 0.6, 0.95, 0.95, e), // implication
            new Thresholds(0.2, 0.5, 0.5, 0.95, 0.95, e) // equivalence
    };
    Double set4_minPos = 0.20;
    Thresholds[] set_4 = {
            new Thresholds(set4_minPos, 0.30, 0.30, 0.95, 0.95, e), // conjunction  e=0.05
            new Thresholds(0.4, 0.70, 0.70, 0.97, 0.97, e), // alternative
            new Thresholds(0.4, 0.70, 0.70, 0.97, 0.97, e), // exclusive
            new Thresholds(0.2, 0.4, 0.5, 0.95, 0.95, e), // implication
            new Thresholds(0.2, 0.7, 0.7, 0.95, 0.95, e) // equivalence
    };

//    ArrayList<BaseProfile> KnowledgeBase = new ArrayList<BaseProfile>(Arrays.asList(new BaseProfile[]{
//                    //P+             //P-             // Q+             // Q-
//                    new BaseProfile(new Integer[]{1, 4}, new Integer[]{3}, new Integer[]{1, 3, 4}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1, 4}, new Integer[]{3}, new Integer[]{1, 3}, new Integer[]{4}),
//                    new BaseProfile(new Integer[]{2}, new Integer[]{1, 3, 4}, new Integer[]{2, 3, 4}, new Integer[]{1}),
//                    new BaseProfile(new Integer[]{2}, new Integer[]{1, 3}, new Integer[]{2, 3}, new Integer[]{1,}),
//                    new BaseProfile(new Integer[]{2}, new Integer[]{3}, new Integer[]{2, 3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1, 4}, new Integer[]{3}, new Integer[]{1, 3, 4}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{4}, new Integer[]{2, 3}, new Integer[]{3}, new Integer[]{2, 4}),
//                    new BaseProfile(new Integer[]{1,}, new Integer[]{2, 3, 4}, new Integer[]{1, 3, 4}, new Integer[]{2}),
//                    new BaseProfile(new Integer[]{}, new Integer[]{1, 2, 3}, new Integer[]{3}, new Integer[]{1, 2}),
//                    new BaseProfile(new Integer[]{}, new Integer[]{1, 3}, new Integer[]{3,}, new Integer[]{1}),
//                    new BaseProfile(new Integer[]{}, new Integer[]{3}, new Integer[]{3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{}, new Integer[]{3}, new Integer[]{3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{4}, new Integer[]{3}, new Integer[]{3, 4}, new Integer[]{}),
//
//                    new BaseProfile(new Integer[]{1}, new Integer[]{3}, new Integer[]{1, 3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{3}, new Integer[]{3}, new Integer[]{1}),
//                    new BaseProfile(new Integer[]{1, 2}, new Integer[]{3}, new Integer[]{1, 2, 3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1, 2}, new Integer[]{3}, new Integer[]{2, 3}, new Integer[]{1}),
//                    new BaseProfile(new Integer[]{1, 2}, new Integer[]{3}, new Integer[]{1, 2, 3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{2, 3}, new Integer[]{3}, new Integer[]{1, 2}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{2, 3}, new Integer[]{1, 3}, new Integer[]{2}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{2, 3}, new Integer[]{3}, new Integer[]{1, 2}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{3}, new Integer[]{1, 3}, new Integer[]{}),
//                    new BaseProfile(new Integer[]{1}, new Integer[]{3}, new Integer[]{3}, new Integer[]{1}),
//
//            }
//    ));

    public ResultsToLatex(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions) throws Exception {
        DataBase db = new DataBase();
        String query = "SELECT * FROM " + table + " " + additionalOptions;
        this.KnowledgeBase = new Episodes(db.query(query), P, P_attr, Q, Q_attr);
        this.P=P;
        this.P_attr=P_attr;
        this.Q=Q;
        this.Q_attr=Q_attr;

        FileWriter fw = new FileWriter("results.txt");
//        fw.write("Knowledge Base:<br />\n");
//        this.generate_knowledbase_table(fw);
        this.object_number = 1;
        this.generate(fw);
        fw.close();
        File htmlFile = new File("results.txt");
        Desktop.getDesktop().open(htmlFile);
    }

    public void generate(FileWriter fw) throws IOException {
        fw.write("\\begin{tabular}{| c | c | c | c |} \n");
        fw.write("\\hline \n");
        fw.write("Processed & ");
        fw.write("Threshold set 1" + " & ");
        fw.write("Threshold set 2" + " & ");
        fw.write("A'priori, min. support: " + Main.min_support);
        fw.write("\\\\ \\hline \n");
        for (double i = 0; i <= 1.0; i += this.step)
            generate_row(i, fw);

    }

    public void generate_row(Double process, FileWriter fw) throws IOException {
        String col0 = String.valueOf((int) (process * 100));
        String col1 = new SystemProcess(samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, KnowledgeBase.getEpisodes()), object_number).start_to_latex(false);
        String col2 = new SystemProcess(samplingFreq, set_2[0], set_2[1], set_2[2], set_2[3], set_2[4], new KnowledgeState(process, KnowledgeBase.getEpisodes()), object_number).start_to_latex(false);
        String col4 = new Apriori().generateLatex(process, KnowledgeBase.getEpisodes(), object_number);
        String write = "" + col0 + "\\% & " + col1 + " & " + col2 + " & " + col4 + "\\\\ \\hline \n";
//        write = write.replace("\\neg p", this.P + " \\neq " + this.P_attr);
//        write = write.replace("\\neg q", this.Q + " \\neq " + this.Q_attr);
//        write = write.replace("p", this.P + " = " + this.P_attr);
//        write = write.replace("q", this.Q + " = " + this.Q_attr);
        fw.write(write);
    }
//
//    public void generate_knowledbase_table(FileWriter fw) throws IOException {
//        fw.write("<table width=\"50%\">\n");
//        fw.write("<tr><th>T</th><th>P<sup>+</sup></th><th>P<sup>-</sup></th><th>Q<sup>+</sup></th><th>Q<sup>-</sup></th></tr>\n");
//        for (int i = 0; i < KnowledgeBase.size(); i++) {
//            fw.write("<tr><th>t<sub>" + (KnowledgeBase.size() - i) + "</sub></th>");
//            BaseProfile BP = KnowledgeBase.get(i);
//            fw.write("<td>" + BP.toString(1) + "</td>\n");
//            fw.write("<td>" + BP.toString(2) + "</td>\n");
//            fw.write("<td>" + BP.toString(3) + "</td>\n");
//            fw.write("<td>" + BP.toString(4) + "</td>\n");
//            fw.write("</tr>\n");
//        }
//        fw.write("</table>\n");
//    }
}