package retrofit2.celsoandre.com.br.retrofit2.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.celsoandre.com.br.retrofit2.models.Course;
import retrofit2.celsoandre.com.br.retrofit2.models.UdacityCatelog;
import retrofit2.http.GET;

/**
 * Created by Celso André on 13/01/2017.
 */

public interface CoursesInterface {
    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";
    @GET("courses")
    Call<UdacityCatelog> listCatalog();

    //@GET("courses")
    //Call<List<Course>> getListCatalog();
}
