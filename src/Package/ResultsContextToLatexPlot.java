package Package;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Dominik
 */
public class ResultsContextToLatexPlot {
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


    public ResultsContextToLatexPlot(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex) throws Exception {
        this(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 1);
    }

    public ResultsContextToLatexPlot(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex, int Example) throws Exception {
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
        fw.write("\\begin{tikzpicture}[scale=1.0]\\label{pl:plot"+this.Example+"}\n");
        if(Main.blackWhite)
                fw.write("\\selectcolormodel{gray}\n");
//        fw.write("\\begin{axis}[\n" +
//                "%title={Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega("+this.P.replace("_", "")+" \\Leftrightarrow "+this.Q.replace("_", "")+")$},\n" +
//                "%title style={text width=26em},\n" +
//                "%name=plot1,\n" +
//                "%height=3cm,width=3cm,\n" +
//                "xlabel={Stan przetworzenia [\\%]},\n" +
//                "ylabel={Względna liczność zbiorów gruntujących [\\%]},\n" +
//                "xmin=0,xmax=100,\n" +
//                "ymin=0.0,ymax=1.0,\n" +
//                "legend pos=outer north east,\n" +
//                "xmajorgrids=true,grid style=dashed,\n" +
//                "ymajorgrids=true,grid style=dashed\n" +
//            "]\n");
        fw.write("\\begin{groupplot}[\n" +
                "group style={group size=2 by 1, horizontal sep=4cm, vertical sep=0cm,},\n" +
//                "anchor=west,\n" +
                "xmin=0,ymin=0,\n" +
                "xmax=100,ymax=1.0,\n" +
                "height=6cm,width=6cm,\n" +
//                "no markers\n" +
                "%name=plot,\n" +
                "%title={Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega("+this.P.replace("_", "")+" \\Leftrightarrow "+this.Q.replace("_", "")+")$},\n" +
                "%title style={text width=26em},\n" +
                "xlabel={Stan przetworzenia [\\%]},\n" +
                "ylabel={Względna moc gruntowania [0,1]},\n" +
                "legend pos=outer north east,\n" +
                "xmajorgrids=true,grid style=dashed,\n" +
                "ymajorgrids=true,grid style=dashed\n" +
                "]\n");
        this.generate_plot(fw, new String[]{"C_1","C_2","C_3","C_4"});
//        fw.write("\\end{axis}\n");
//        fw.write("\\begin{axis}[\n" +
//                "%title={Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega("+this.P.replace("_", "")+" \\Leftrightarrow "+this.Q.replace("_", "")+")$},\n" +
//                "%title style={text width=26em},\n" +
//                "%name=plot2,\n" +
//                "%height=3cm,width=3cm,\n" +
//                "xlabel={Stan przetworzenia [\\%]},\n" +
//                "ylabel={Względna liczność zbiorów gruntujących [\\%]},\n" +
//                "xmin=0,xmax=100,\n" +
//                "ymin=0.0,ymax=1.0,\n" +
//                "legend pos=outer north east,\n" +
//                "xmajorgrids=true,grid style=dashed,\n" +
//                "ymajorgrids=true,grid style=dashed\n" +
//                "]\n");
        this.generate_plot(fw, new String[]{"\\lambda^1","\\lambda^2","\\lambda^3","\\lambda^4"});
        fw.write("\\end{groupplot}\n");
//        fw.write("\\end{axis}\n");
        fw.write(   "\\end{tikzpicture}\n\n");

//        fw.write("Query: \n" + query + "<br /><br />\n");
//        fw.write("Knowledge Base:<br />\n");
//        this.generate_knowledbase_table(fw);
//        fw.write("</body>\n</html>");
        fw.close();
    }

    public void generate_plot(FileWriter fw, String[] legend) throws IOException { // legend = {"C_1","C_2","C_3","C_4","\\lambda^1","\\lambda^2","\\lambda^3","\\lambda^4"}
        ArrayList<HashMap<String, Double>> coordinates = new  ArrayList<HashMap<String, Double>>();
        fw.write("\\nextgroupplot \n");
        fw.write("\\draw[black,dashed,thick] (axis cs:0,"+(set_1[4].MinBel)+") -- node[above,xshift=-1.5cm]{$\\lambda^{\\Leftrightarrow}_{MinBel}$} (axis cs:100,"+(set_1[4].MinBel)+");\n");
        for (double i = 0.00; ((double)Math.round(i * 100) / 100) <= 1.0; i = i + Main.step){
            coordinates.add(generate_coordinates(i, fw));
        }
        for(String l : legend){
            fw.write("\\addplot %"+l+" \n" +
                    "coordinates {");
            for (HashMap<String, Double> row : coordinates) {
                fw.write("("+row.get("t")+","+row.get(l)+")");
            }
            fw.write("};\n");
            fw.write("\\addlegendentry{$"+l+"$};\n");

        }
    }

    public HashMap<String, Double> generate_coordinates(Double process, FileWriter fw) throws IOException {
//        String col0 = String.valueOf(Math.round(process * 100));
//        String col0 = String.valueOf((int) (process * 100));
        HashMap<String, Double> result = new  HashMap<String, Double>();
        ArrayList<HashMap<Integer, Double>> actual = new ArrayList<HashMap<Integer, Double>>();
        switch(this.Example){
            case 1: actual = new SystemProcess(
                        Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4],
                        new KnowledgeState(process, this.KnowledgeBase.getEpisodes()),
                        Main.object_number
                    ).start_to_plot();
                    break;
            case 2: actual = new SystemProcess(
                    Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4],
                    this.KnowledgeBase.getContextKnowledgeState(process, this.ContextMethod, this.Contex),
                    Main.object_number
            ).start_to_plot();
                break;
            default: actual = new SystemProcess(
                    Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4],
                    this.KnowledgeBase.getContextKnowledgeStateAll(process, this.ContextMethod, this.Contex),
                    Main.object_number
            ).start_to_plot();
                break;
        }

                new SystemProcess(
                        Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4],
                        this.KnowledgeBase.getContextKnowledgeStateAll(process, this.ContextMethod, this.Contex),
                        Main.object_number
                ).start_to_plot();
        int valueNumber = 1;

        for (HashMap<Integer, Double> row : actual){
            for (Map.Entry<Integer, Double> c : row.entrySet()){
                result.put("C_"+valueNumber, c.getValue());
                result.put("\\lambda^"+valueNumber, (double)(c.getKey()) / 100);
            }
            valueNumber++;
        }
        result.put("t", (double)Math.round(process * 100));
        return result;
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