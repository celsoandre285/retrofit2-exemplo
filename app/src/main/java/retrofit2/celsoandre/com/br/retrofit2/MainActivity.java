package retrofit2.celsoandre.com.br.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.celsoandre.com.br.retrofit2.models.Course;
import retrofit2.celsoandre.com.br.retrofit2.models.Instructor;
import retrofit2.celsoandre.com.br.retrofit2.models.UdacityCatelog;
import retrofit2.celsoandre.com.br.retrofit2.services.CoursesInterface;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView listaItem;
    private ArrayAdapter<Course> adapter;
    private ArrayList<Course> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //inicio um Array list vazio
        list = new ArrayList<Course>();

        //configuro o Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CoursesInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CoursesInterface service = retrofit.create(CoursesInterface.class);


        Call<UdacityCatelog> requestCatalog =  service.listCatalog();


        //Metodo Assicrono
        requestCatalog.enqueue(new Callback<UdacityCatelog>() {
            @Override
            public void onResponse(Call<UdacityCatelog> call, Response<UdacityCatelog> response) {
                //verifico se esta tendo resposta
                if(!response.isSuccessful()){
                    Log.i("Code", "Codigo: "+ response.code());
                }else{
                    UdacityCatelog catalogo = response.body();
                    for(Course c : catalogo.courses ){
                        //Verifico no log se as informações estão ok!
                        Log.i("Code", "course: "+ c.title);
                        //ADICIONE NO ADAPTER
                        adapter.add(c);


                    }
                    //NOTIFIQUE AS ALTERACOES
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UdacityCatelog> call, Throwable t) {
                //Case de erro!
                Log.i("Code", "Codigo: "+ t.getMessage());
            }
        });


        //Vinculo a ListView
        listaItem = (ListView) findViewById(R.id.listItem);
        //configuro o adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        //Seto o Adapter
        listaItem.setAdapter(adapter);

        listaItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT);
            }
        });

    }
}
