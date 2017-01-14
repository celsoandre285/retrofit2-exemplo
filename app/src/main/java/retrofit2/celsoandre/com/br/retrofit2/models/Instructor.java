package retrofit2.celsoandre.com.br.retrofit2.models;

/**
 * Created by Celso André on 13/01/2017.
 */
public class Instructor {
    public String name;
    public String bio;
    public String image;

    @Override
    public String toString() {
        return name +"\n - "+ bio;
    }
}
