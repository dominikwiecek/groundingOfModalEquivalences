package Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Dominik
 */
public class ResultsComplex {
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



    public ResultsComplex(String table, String P, String P_attr, String Q, String Q_attr, String additionalOptions, String contextMethod, Map<String, String> contex) throws Exception {
        DataBase db = new DataBase();
        String query = "SELECT * FROM " + table + " " + additionalOptions;
        this.KnowledgeBase = new Episodes(db.query(query), P, P_attr, Q, Q_attr);
        this.P=P;
        this.P_attr=P_attr;
        this.Q=Q;
        this.Q_attr=Q_attr;
        this.ContextMethod=contextMethod;
        this.Contex=contex;
        String desc;
        switch (table){
            case "zoo":
                desc = "Forsyth R., \\textit{Zoo Data Set [http://archive.ics.uci.edu/ml/datasets/zoo]}, UCI Machine Learning Repository, 1990.";
                break;
            case "adult":
                desc = "Kohavi R., Becker B., \\textit{Adult Data Set [http://archive.ics.uci.edu/ml/datasets/adult]}, UCI Machine Learning Repository, 1996.";
                break;
            case "housevotes":
                desc = "\\textit{Congressional Voting Records Data Set [http://archive.ics.uci.edu/ml/datasets/congressional+voting+records]}, UCI Machine Learning Repository, Congressional Quarterly Almanac, 98th Congress, 2nd session 1984, Volume XL: Congressional Quarterly Inc. Washington, D.C., 1985.";
                break;
            default:
                desc = "\\textit{Data Set [http://archive.ics.uci.edu/ml/datasets/]}";
                break;
        }
        String Protoform = Main.convertParams("$\\Omega([p] \\Leftrightarrow [q])$", this.P, this.P_attr, this.Q, this.Q_attr);

        String resultsComplex = "" +
                "\\documentclass[12pt, twoside, openright]{mwbk} % rozdział na nowej stronie\n" +
                "\n" +
                "% ustawienia kodowania pliku i języka\n" +
                "\\usepackage[T1]{polski}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{float}\n" +
                "\\usepackage{indentfirst}\n" +
                "\\usepackage[pdftex]{graphicx}\n" +
                "\n" +
                "% wymiary strony, marginesy\n" +
                "\\usepackage[a4paper,left=3.0cm,right=3.0cm,top=2.0cm,bottom=2.0cm,includefoot=false,includehead=false]{geometry}\n" +
                "\n" +
                "% tabele\n" +
                "\\usepackage[table,xcdraw]{xcolor}\n" +
                "\\usepackage{longtable}\n" +
//                "\\usepackage{caption}\n" +
//                "\\captionsetup{width=.75\\textwidth}\n" +
                "\n" +
                "% wykresy\n" +
                "\\usepackage{pgfplots}\n" +
                "% \\usepackage{groupplots}\n" +
                "\\usepgfplotslibrary{groupplots}\n" +
                "\\pgfplotsset{compat=1.16}\n" +
                "\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{amsmath}                % rozszerzona funkcjonalnosc matematyczna, \n" +
                "\\usepackage{amsthm}               % rozszerzona funkcjonalność matematyczna, np. wielolinijkowe równania\n" +
                "\\numberwithin{figure}{chapter}    % numerowanie obrazków, prefiksowane numerem rozdziału\n" +
                "\\numberwithin{table}{chapter}     % numerowanie tabel, prefiksowane numerem rozdziału\n" +
                "\\numberwithin{equation}{chapter}  % numerowanie wzorów, prefiksowane numerem rozdziału\n" +
                "\n" +
                "\\newtheorem{twierdzenie}{Twierdzenie}[chapter]\n" +
                "\\newtheorem{definicja}{Definicja}[chapter]\n" +
                "\\newtheorem{dowod}{Dowód}[chapter]\n" +
                "\\renewcommand{\\qedsymbol}{$\\blacksquare$} % czarny kawadrat (zamiast pustego) na koniec dowodu\n" +
                "\n" +
                "\n" +
                "\\usepackage{enumitem}\n" +
                "% \\usepackage{color}\n" +
                "\\usepackage{times}          % czcionka Times\n" +
                "% \\usepackage{arial}\n" +
                "\\usepackage{ifthen}         % umożliwia bloki warunkowe\n" +
                "\\usepackage{fancyhdr}       % własne definicje nagłówków\n" +
                "\\usepackage{multirow}\n" +
                "\\linespread{1.4}        % odstęp lini na 1.5\n" +
                "\n" +
                "% dzielenie wyrazów – większe odstępy, mniej dzielenia\n" +
                "\\hyphenpenalty=5000\n" +
                "\\tolerance=5000\n" +
                "% \\setcounter{secnumdepth}{3}\n" +
                "% nagłówki i stopki\n" +
                "\\pagestyle{fancy}\n" +
                "\\fancyhf{} % usun biezace ustawienia pagin\n" +
                "\\fancyhead[R]{\\small\\itshape\\mymark}\n" +
                "\\fancyfoot[R]{\\small\\bfseries\\thepage}\n" +
                "\\renewcommand{\\headrulewidth}{0.5pt}\n" +
                "\\renewcommand{\\footrulewidth}{0pt}\n" +
                "\\newcommand{\\mymark}{\\ifthenelse{\\equal{\\rightmark}{}}{\\leftmark}{\\rightmark}}\n" +
                "\\addtolength{\\headheight}{0.5pt} % pionowy odstep na kreske\n" +
                "\n" +
                "\\fancypagestyle{plain}{\n" +
                "\t\\fancyhead{} % usun p. górne na stronach pozbawionych numeracji (plain)\n" +
                "\t\\renewcommand{\\headrulewidth}{2pt} % pozioma kreska\n" +
                "}\n\n" +
//                "\\setlength{\\LTcapwidth}{\\linewidth}" +
//                "\n\n" +
                "\\begin{document}\n" +
                "\n" +
                "Dziedzina danych\\footnote{" + desc + "}:\n" +
//                "Dla obiektu \\textit{o} występuje dziedzina własności:\\\\ \n" +
//                "\\begin{equation}\n" +
//                "  \\begin{aligned}\n" +
//                "  P=\\{"+ String.join(",", db.Domain.toArray())+"\\}\n" +
//                "\\resizebox{.9 \\textwidth}{!} \n" +
//                "{\n" +
                "\\textit{P="+ Arrays.deepToString(db.Domain.toArray())+"}\\\\ \n" +
//                "} \n" +
//                "  \\end{aligned}\n" +
//                "\\end{equation}\n" +
                "\n";


        File file = new File("results.tex");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[]{};

//        resultsComplex+="\\textbf{Zestawienie}:\\footnote{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega(eggs \\Leftrightarrow milk)$, " +
//                "$\\lambda^{\\Leftrightarrow}_{minPos} = "+set_1[4].MinPos+" < \\lambda^{\\Leftrightarrow}_{minBel} = "+set_1[4].MinBel+" < \\lambda^{\\Leftrightarrow}_{maxBel} = \\lambda^{\\Leftrightarrow}_{Know} = "+set_1[4].MinKnow+"$} " +
//                "\\\\";
        new ResultsContextToLatex(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 1);
        file = new File("results.tex");
        fis = new FileInputStream(file);
        data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        resultsComplex+= new String(data, "UTF-8");

        resultsComplex+= "\\newpage \n" + "\\textbf{Strategia bezkontekstowa}:\\footnote{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy "+Protoform+", " +
                "$\\lambda^{\\Leftrightarrow}_{minPos} = "+set_1[4].MinPos+" < \\lambda^{\\Leftrightarrow}_{minBel} = "+set_1[4].MinBel+" < \\lambda^{\\Leftrightarrow}_{maxBel} = \\lambda^{\\Leftrightarrow}_{Know} = "+set_1[4].MinKnow+"$} " +
                "\\\\ \n\n";
        new ResultsContextToLatexPlot(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 1);
        file = new File("results.tex");
        fis = new FileInputStream(file);
        data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        resultsComplex+= new String(data, "UTF-8");

        resultsComplex+="\\textbf{Strategia "+this.ContextMethod+" dla przetworzonych epizodw oraz $K="+this.contextToString()+"$}\\\\ \n\n";
        new ResultsContextToLatexPlot(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 2);
        file = new File("results.tex");
        fis = new FileInputStream(file);
        data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        resultsComplex+= new String(data, "UTF-8");

        resultsComplex+="\\textbf{Strategia "+this.ContextMethod+" dla wszystkich epizodw oraz $K="+this.contextToString()+"$}\\\\ \n\n";
        new ResultsContextToLatexPlot(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 3);
        file = new File("results.tex");
        fis = new FileInputStream(file);
        data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        resultsComplex+= new String(data, "UTF-8");

//        resultsComplex+="\\textbf{Zestawienie 2}:\\footnote{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega(eggs \\Leftrightarrow milk)$, " +
//                "$\\lambda^{\\Leftrightarrow}_{minPos} = "+set_1[4].MinPos+" < \\lambda^{\\Leftrightarrow}_{minBel} = "+set_1[4].MinBel+" < \\lambda^{\\Leftrightarrow}_{maxBel} = \\lambda^{\\Leftrightarrow}_{Know} = "+set_1[4].MinKnow+"$} " +
//                "\\\\";
        new ResultsContextToLatex(table, P, P_attr, Q, Q_attr, additionalOptions, contextMethod, contex, 2);
        file = new File("results.tex");
        fis = new FileInputStream(file);
        data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        resultsComplex+= "\\newpage \n" + new String(data, "UTF-8");

        resultsComplex+="\\end{document}";

        FileWriter fw = new FileWriter("results.tex");
        fw.write(resultsComplex);
        fw.close();
    }



//    public void generate(FileWriter fw) throws IOException {
//        fw.write("\\begin{table}[H]\n" +
//                "   \\caption{Zestawienie podsumowań lingwistycznych w postaci okresu równoważnościowego dla protoformy $\\Omega("+this.P.replace("_", "")+" \\Leftrightarrow "+this.Q.replace("_", "")+")$}\n" +
//                "   \\label{tab:Zestawienie}\n" +
//                "   \\renewcommand{\\arraystretch}{0.8}\n" +
//                "   \\centering\n" +
//                "   \\hspace*{-2cm}\n");
//
//        fw.write("\\begin{tabular}{|p{0.5cm}|p{5.5cm}|p{5.5cm}|p{5.5cm}|} \n");
////        fw.write("\\begin{tabular}{|p{1cm}|p{3cm}|p{3cm}|p{3cm}|p{3cm}|} \n");
//        fw.write("\\hline \n \\%");
//        fw.write(" & \\footnotesize " + "context free");
//
////        fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for processed episodes \\newline $K="+this.contextToString()+"$");
//        fw.write(" & \\footnotesize " + "strategy "+this.ContextMethod+" for all episodes \\newline $K="+this.contextToString()+"$");
//        fw.write(" & \\footnotesize " + "A'priori \\newline min. support: " + Main.min_support);
//        fw.write("\\\\ \\hline \n");
//        for (double i = 0.00; ((double)Math.round(i * 100) / 100) <= 1.0; i = i + Main.step){
//            generate_row(i, fw);
//        }
//        fw.write("\\end{tabular}\n   \\end{table}\n");
//
//    }
//
//    public void generate_row(Double process, FileWriter fw) throws IOException {
//        String col0 = String.valueOf(Math.round(process * 100));
////        String col0 = String.valueOf((int) (process * 100));
//        String col1 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], new KnowledgeState(process, this.KnowledgeBase.getEpisodes()), Main.object_number).start_to_latex(false);
//        String col2 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeState(process, "A", this.Contex), Main.object_number).start_to_latex(true);
//        String col3 = new SystemProcess(Main.samplingFreq, set_1[0], set_1[1], set_1[2], set_1[3], set_1[4], this.KnowledgeBase.getContextKnowledgeStateAll(process, "A", this.Contex), Main.object_number).start_to_latex(true);
////        String col4 = new Apriori().generateLatex(process, this.KnowledgeBase.getEpisodes(), Main.object_number);
//        String write = "\\footnotesize " + col0 + " & " + col1 + " & " + col2 + " & " + col3 +  " \\\\ \\hline \n";
////        String write = "" + col0 + "\\% & " + col1 + " & " + col2 + " & " + col3 + " & " + col4 + " \\\\ \\hline \n";
//        write = Main.convertParams(write, this.P, this.P_attr, this.Q, this.Q_attr);
//        fw.write(write);
//    }

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