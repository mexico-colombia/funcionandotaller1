package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import Model.Container;

/**
 * Esta clase sirve para seleccionar una cancion de nuestra tarjeta sd, para posteriormente almacenarla
 * en nuestra clase Container para poder reproducirla con la alarma
 *
 * */
public class SelectSong extends AppCompatActivity {
//para acceder a la tarjeta sd necesitamos poner esto en el manifiesto
    // <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    //este es el listview donde mostraremos todas nuestras canciones
    private ListView lv;
    //items es un array de Strings donde, guardaremos una cadena de caracteres de cada una de nuestras canciones
    private String[] items;
    //container es nuestra clase donde guardamos los atributos que vamos a leer o a hacer sonar
    private Container container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_song);
        //obtenemos el valor del listview
        lv=(ListView)findViewById(R.id.lvSelectSongList);

        //guardamos en un arraylist todas las canciones obtenidas con findsongs
        final ArrayList<File> mySongs=findSongs(Environment.getExternalStorageDirectory());
        items=new String[mySongs.size()];
        for(int i=0;i<mySongs.size();i++){
            //el toast es para que cada vez que encuentre una cancion lo muestre por pantalla
            //toast(mySongs.get(i).getName().toString());
            //llenamos el array items con cadenas de nombres de nuestras canciones mp3
            //remplazamos el .mp3 por nada para que no moleste
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","");
        }

        //creamos un adaptador para poder poner todos los nombres de canciones.
        //primero ponesmo el context
        //song_layout que es donde esta el layout de como se pondra una cancion
        //el objeto textview que mostrara en el listView
        //items que son todos los string de nombres de nuestras canciones
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.song_layout,R.id.tvSongLayout,items);

        //se lo asignamos al listView
        lv.setAdapter(adapter);

        //obtenemos la instancia del contenedor
        container=Container.getInstance();

        //cuando selecionemos una cancion vamos a añadirla a nuestro contenedor
        //atraves de su posicion y partiendo del arrayList mysongs
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                container.setSong(mySongs.get(position));
                startActivity(new Intent(SelectSong.this, Options.class));
            }
        });
    }

    // metodo que buscara todas las canciones
    public ArrayList<File> findSongs(File root){
        ArrayList <File> songsList=new ArrayList<File>();
        File[]files=root.listFiles();

        //recorremos todos los archivos
        for(File singleFile : files){
            //si alguno es un directorio y no esta oculto entramos aqui
            if(singleFile.isDirectory()&&!singleFile.isHidden()){
                //llamara recursivamente a este metodo otra vez pero dentro del directorio
                songsList.addAll(findSongs(singleFile));
            }else{
                //si el nombre del fichero acaba en ".mp3" añadimos este archivo a la lista
                //tambien podriamos poner los que son .wav
                if(singleFile.getName().endsWith(".mp3")){
                    songsList.add(singleFile);
                }

            }
        }
        return songsList;
    }

    //metodo toast que sirve para mostrar como notificacion un texto
    public void toast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
