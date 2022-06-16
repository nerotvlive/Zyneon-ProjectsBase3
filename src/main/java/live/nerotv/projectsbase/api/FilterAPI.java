
package live.nerotv.projectsbase.api;

import java.util.ArrayList;

public class FilterAPI {
    public static ArrayList<String> blockedStrings = new ArrayList();
    public static ArrayList<String> blockedNames = new ArrayList();
    public static ArrayList<String> blockedJobs = new ArrayList();

    public static boolean isJobBlocked(String DYM) {
        String Job2 = DYM.toLowerCase();
        if (Job2.contains("b\u00fcrgermeister")) {
            return true;
        }
        if (Job2.contains("buergermeister")) {
            return true;
        }
        if (Job2.contains("buerger meister")) {
            return true;
        }
        if (Job2.contains("b\u00fcrger meister")) {
            return true;
        }
        if (Job2.contains("k\u00f6nig")) {
            return true;
        }
        if (Job2.contains("koenig")) {
            return true;
        }
        if (Job2.contains("buergermeisterin")) {
            return true;
        }
        if (Job2.contains("b\u00fcrgermeisterin")) {
            return true;
        }
        if (Job2.contains("buerger meisterin")) {
            return true;
        }
        if (Job2.contains("b\u00fcrger meisterin")) {
            return true;
        }
        if (Job2.contains("k\u00f6nigin")) {
            return true;
        }
        if (Job2.contains("koenigin")) {
            return true;
        }
        if (Job2.contains("?")) {
            return true;
        }
        if (Job2.contains("/")) {
            return true;
        }
        if (Job2.contains("\\")) {
            return true;
        }
        if (Job2.contains("skuse")) {
            return true;
        }
        if (Job2.contains("a.s.b.")) {
            return true;
        }
        if (Job2.contains("geheim")) {
            return true;
        }
        if (Job2.contains("graf")) {
            return true;
        }
        if (Job2.contains("graph")) {
            return true;
        }
        if (Job2.contains("f\u00fchrer")) {
            return true;
        }
        if (Job2.contains("fuehrer")) {
            return true;
        }
        if (Job2.contains("president")) {
            return true;
        }
        if (Job2.contains("pr\u00e4sident")) {
            return true;
        }
        if (Job2.contains("praesident")) {
            return true;
        }
        if (Job2.contains("hure")) {
            return true;
        }
        if (Job2.contains("fotze")) {
            return true;
        }
        if (Job2.contains("schwanz")) {
            return true;
        }
        if (Job2.contains("sir")) {
            return true;
        }
        if (Job2.contains("lady")) {
            return true;
        }
        if (Job2.contains("attentaeter")) {
            return true;
        }
        if (Job2.contains("attent\u00e4ter")) {
            return true;
        }
        if (Job2.contains("zwang")) {
            return true;
        }
        if (Job2.contains("skalve")) {
            return true;
        }
        if (Job2.contains("buergermeister")) {
            return true;
        }
        if (Job2.contains("gesetz")) {
            return true;
        }
        if (Job2.contains("poliz")) {
            return true;
        }
        if (Job2.contains("killer")) {
            return true;
        }
        if (Job2.contains("imperator")) {
            return true;
        }
        if (Job2.contains("lord")) {
            return true;
        }
        if (Job2.contains("lordt")) {
            return true;
        }
        if (Job2.contains("lort")) {
            return true;
        }
        if (Job2.contains("_")) {
            return true;
        }
        if (Job2.contains("fick")) {
            return true;
        }
        if (Job2.contains("fresse")) {
            return true;
        }
        if (Job2.contains("nutte")) {
            return true;
        }
        if (Job2.contains("sohn")) {
            return true;
        }
        if (Job2.contains("schwanz")) {
            return true;
        }
        if (Job2.contains("miliz")) {
            return true;
        }
        if (Job2.contains("sterben")) {
            return true;
        }
        if (Job2.contains("selbstmord")) {
            return true;
        }
        if (Job2.contains("suizid")) {
            return true;
        }
        if (Job2.contains("fuck")) {
            return true;
        }
        if (Job2.contains("you")) {
            return true;
        }
        if (Job2.contains("tube")) {
            return true;
        }
        if (Job2.contains("porn")) {
            return true;
        }
        if (Job2.contains("ideallauch")) {
            return true;
        }
        if (Job2.contains("nero")) {
            return true;
        }
        if (Job2.contains("schleimer")) {
            return true;
        }
        if (Job2.contains("nyuun")) {
            return true;
        }
        if (Job2.contains("nina")) {
            return true;
        }
        if (Job2.contains("gomme")) {
            return true;
        }
        if (Job2.contains("pewdie")) {
            return true;
        }
        if (Job2.contains("arsch")) {
            return true;
        }
        return FilterAPI.isStringBlocked(Job2);
    }

    public static boolean isNameBlocked(String DYM) {
        String Name2 = DYM.toLowerCase();
        return FilterAPI.isJobBlocked(Name2);
    }

    public static boolean isStringBlocked(String DYM) {
        String string = DYM.toLowerCase();
        if (string.contains("nigga")) {
            return true;
        }
        if (string.contains("niga")) {
            return true;
        }
        if (string.contains("nega")) {
            if (string.contains("negativ")) {
                if (string.contains("ohne")) {
                    return string.contains("tiv");
                }
                return false;
            }
            return true;
        }
        if (string.contains("neger")) {
            return true;
        }
        if (string.contains("huso")) {
            return true;
        }
        if (string.contains("n3ger")) {
            return true;
        }
        if (string.contains("neg3r")) {
            return true;
        }
        if (string.contains("n3g3r")) {
            return true;
        }
        if (string.contains("n3gger")) {
            return true;
        }
        if (string.contains("n3gg3r")) {
            return true;
        }
        if (string.contains("negg3r")) {
            return true;
        }
        if (string.contains("n3ga")) {
            return true;
        }
        if (string.contains("n3gga")) {
            return true;
        }
        if (string.contains("hur3nsohn")) {
            return true;
        }
        if (string.contains("hur3ns0hn")) {
            return true;
        }
        if (string.contains("hurens0hn")) {
            return true;
        }
        if (string.contains("nigger")) {
            return true;
        }
        if (string.contains("niger")) {
            return !string.contains("weniger");
        }
        if (string.contains("nazi")) {
            return true;
        }
        if (string.contains("hitler")) {
            return true;
        }
        if (string.contains("hure")) {
            return true;
        }
        if (string.contains("fotze")) {
            return true;
        }
        if (string.contains("vergewalti")) {
            return true;
        }
        if (string.contains("misgeburt")) {
            return true;
        }
        if (string.contains("mistgeburt")) {
            return true;
        }
        if (string.contains("missgeburt")) {
            return true;
        }
        if (string.contains("misstgeburt")) {
            return true;
        }
        if (string.contains("misset")) {
            return true;
        }
        if (string.contains("miset")) {
            return true;
        }
        if (string.contains("missed")) {
            return true;
        }
        if (string.contains("mised")) {
            return true;
        }
        if (string.contains("faggot")) {
            return true;
        }
        if (string.contains("schwuchtel")) {
            return true;
        }
        if (string.contains("spast")) {
            return true;
        }
        if (string.contains("spasst")) {
            return true;
        }
        if (string.contains("cancer")) {
            return true;
        }
        if (string.contains("krebs")) {
            return true;
        }
        if (string.contains("corona")) {
            return true;
        }
        if (string.contains("corinski")) {
            return true;
        }
        if (string.contains("atilla")) {
            return true;
        }
        if (string.contains("hildmann")) {
            return true;
        }
        if (string.contains("hildman")) {
            return true;
        }
        return string.contains("atila");
    }
}

