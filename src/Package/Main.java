package Package;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Double step = 0.10;
    public static Double samplingFreq = 5.0;
    public static int object_number = 1; // in case multiobject approach =[Main(object_number.1), Main(object_number.2)]
    public static Double d_max = 0.50; // context max distance
    public static Double min_support = 0.20; // a'priori minimum support

    public static boolean DEBUG = true;
    public static int displayParam = 2;
    public static boolean blackWhite = true;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //Wyniki ciekawe:
//
//        Map<String, String> contex = new HashMap<String,String>();
//        //context: attribute, value
//        contex.put("predator","0");
//        contex.put("domestic","1");
//        contex.put("aquatic","1");
//
//        new ResultsComplex("zoo",
//                "eggs","1",
//                "milk","1",
//                "",
//                "A", contex);

//        new ResultsToHTML("adult", "race", " Asian-Pac-Islander", "native-country"," Vietnam", "ORDER BY race LIMIT 100");
//        new ResultsToHTML("adult", "race", " Asian-Pac-Islander", "native-country"," Vietnam", "ORDER BY race LIMIT 10000");

        Map<String, String> contex = new HashMap<String,String>();
        //context: attribute, value
        contex.put("sex"," Male");
//        contex.put("race"," Asian-Pac-Islander");
//        contex.put("native-country"," Vietnam");

        new ResultsComplex("adult",
                "race", " Asian-Pac-Islander",
                "native-country"," Vietnam",
                "ORDER BY race LIMIT 500",
                "A", contex);

//
//        Map<String, String> contex = new HashMap<String,String>();
//        //context: attribute, value
////        contex.put("party","republican");
//        contex.put("party","republican");
//
//        new ResultsComplex("housevotes",
//                "el-salvador-aid","y",
//                "aid-to-nicaraguan_contras","y",
//                "LIMIT 500",
//                "A", contex);


//      Map<String, String> contex = new HashMap<String,String>();
//        //context: attribute, value
//        contex.put("party","republican");
//
//        new ResultsContextToLatex("housevotes",
//                "adoption_budget_resolution","y", "physician_fee_freeze","y", "LIMIT 500",
//                "A", contex);

//        Map<String, String> contex = new HashMap<String,String>();
//        //context: attribute, value
////        contex.put("predator","0");
//        contex.put("domestic","1");
////        contex.put("aquatic","1");
//
//        new ResultsContextToLatex("zoo",
//                "eggs","1",
//                "milk","1",
//                "",
//                "A", contex);

//        MainFrame Frame = new MainFrame();
//        Frame.setVisible(true);
//        new ResultsToLatex("users", "smoker","true", "drink_level","casual drinker", "LIMIT 10");
//        new ResultsToHTML("users", "smoker","true", "drink_level","casual drinker", "LIMIT 10");
//        new ResultsToHTML("student", "activities","yes", "paid","yes", "LIMIT 100");
//        new ResultsToHTML("student", "nursery","yes", "higher","yes", "LIMIT 100");
//        new ResultsToHTML("housevotes", "Education_spending","y", "Class","democrat", "LIMIT 500");
//        new ResultsToHTML("housevotes", "Immigration","y", "Religious_groups_in_schools","y", "LIMIT 10");
//        new ResultsToHTML("housevotes", "Handicapped_infants","y", "Water_project_cost","y", "LIMIT 100");
//        new ResultsToHTML("housevotes", "Duty_free_exports","y", "Export_south_africa","y", "LIMIT 100");
//        new ResultsToHTML("housevotes", "Adoption_budget_resolution","y", "Physician_fee_freeze","y", "LIMIT 500"); // BEST
//        new ResultsToHTML("housevotes", "Crime","y", "Education_spending","y", "LIMIT 100");
//        new ResultsToHTML("housevotes", "El_salvador_aid","n", "Aid_to_nicaraguan_contras","y", "WHERE Class = 'democrat' LIMIT 100");
//        new ResultsToHTML("housevotes", "El_salvador_aid","n", "Aid_to_nicaraguan_contras","y", "WHERE Class = 'republican' LIMIT 100");
//        new ResultsToHTML("housevotes", "El_salvador_aid","y", "Physician_fee_freeze","y", "WHERE Class = 'republican' LIMIT 500");
//        new ResultsToHTML("housevotes", "Physician_fee_freeze","n", "Class","democrat", "LIMIT 500");
//        new ResultsToHTML("housevotes", "El_salvador_aid","y", "Aid_to_nicaraguan_contras","y", "LIMIT 10");


//        new ResultsToHTML("adult", "sex"," Male", "sex"," Female", "LIMIT 100");
//        new ResultsToHTML("adult", "marital-status"," Never-married", "relationship"," Unmarried", "LIMIT 100");
//        new ResultsToHTML("adult", "education"," HS-grad", "workclass","Private", "LIMIT 100");
//        new ResultsToHTML("adult", "marital-status"," Divorced", "relationship"," Not-in-family", "LIMIT 100");
//        new ResultsToHTML("adult", "race", " White", "native-country"," Yugoslavia", "LIMIT 1000");
//        new ResultsToHTML("adult", "race", " Asian-Pac-Islander", "native-country"," Vietnam", "ORDER BY race LIMIT 100");

        File texFile = new File("results.tex");
//        File texFile = new File("results.html");
        Desktop.getDesktop().open(texFile);
    }

    public static String convertParams(String write, String P, String P_attr, String Q, String Q_attr){
        switch (Main.displayParam){
            case 1:
                write = write.replace("[p]", P_attr);
                write = write.replace("[q]", Q_attr);
                break;
            case 2:
                write = write.replace("[p]", P);
                write = write.replace("[q]", Q);
                break;
            default:
                write = write.replace("&not;p", P + "&ne;" + P_attr);
                write = write.replace("&not;q", Q + "&ne;" + Q_attr);
                write = write.replace("p", P + "=" + P_attr);
                write = write.replace("q", Q + "=" + Q_attr);
                break;
        }
        write = write.replace("_", "").replace("-", "");
        return write;
    }

}

