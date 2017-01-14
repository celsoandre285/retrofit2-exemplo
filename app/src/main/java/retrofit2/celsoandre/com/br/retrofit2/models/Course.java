package retrofit2.celsoandre.com.br.retrofit2.models;

import java.util.List;

/**
 * Created by Celso André on 13/01/2017.
 */

public class Course {
    public String title;
    public String subtitle;

    public List<Instructor> instructors;

    @Override
    public String toString() {
        return "Titulo - "+ title +"\n"+
                "Subtitulo - "+ subtitle +"\n"+
               instructors.toString();
    }
}
