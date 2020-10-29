package com.example.appdialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String DIALOGO_ALERTA_SIMPLE="Dialogo Alerta Simple";
    private static final String DIALOGO_ALERTA_ESTILO="Dialogo de Alerta con Estilo";
    private static final String DIALOGO_LISTA_SIMPLE="Dialogo con Lista Simple";
    private static final String DIALOGO_BOTONES_RADIO="Dialogo con Botones de Radio";
    private static final String DIALOGO_CHECK_BOX="Dialogo con CheckBox";
    private static final String DIALOGO_PERSONALIZADO="Dialogo Personalizado";
    private static final String DIALOGO_FECHA="Dialogo para la fecha";
    private static final String DIALOGO_HORA="Dialogo para la hora";

    private ListView listView;
    private List<String> elementosSeleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elementosSeleccionados = new ArrayList<>();
        listView = findViewById(R.id.listaPrincipal);
        String[]  opciones  = getResources().getStringArray(R.array.opciones);
        ArrayAdapter<String> modelo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opciones);
        listView.setAdapter(modelo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getAdapter().getItem(position).toString();
                switch (seleccion){
//__________________________________________________________________________________________________1
                    case DIALOGO_ALERTA_SIMPLE:
                        desplegarMensajes("Desea hacer mas registros?");
                        break;
//__________________________________________________________________________________________________2
                    case DIALOGO_ALERTA_ESTILO:
                        crearDialogoAlertaEstilo("Saludos, diagolo alerta estilo, salir?");
                        break;
//__________________________________________________________________________________________________3
                    case DIALOGO_LISTA_SIMPLE:
                        String[] colores = getResources().getStringArray(R.array.colores);
                        crearDialogoListaEstilo(colores);
                        break;
//__________________________________________________________________________________________________4
                    case DIALOGO_BOTONES_RADIO:
                        String[] estadoCivil = getResources().getStringArray(R.array.estadoCivil);
                        crearDialogoRadioEstilo(estadoCivil, "Estado Civil");
                        break;
//__________________________________________________________________________________________________5
                    case DIALOGO_CHECK_BOX:
                        String[] competencias = getResources().getStringArray(R.array.competencias);
                        crearDialogoCheckBoxEstilo(competencias,"Competencias");
                        break;
//__________________________________________________________________________________________________6
                }
            }
        });
    }

//__________________________________________________________________________________________________1
    private void desplegarMensajes(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Mensaje");
        alertDialogBuilder
                .setMessage(mensaje)
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                );
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//__________________________________________________________________________________________________2
    private void crearDialogoAlertaEstilo(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogThemeDos);
        alertDialogBuilder.setTitle("Titulo");
        alertDialogBuilder
                .setMessage(mensaje)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Boton positivo pulsado" + id, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Boton negativo pulsado" + id, Toast.LENGTH_LONG).show();
                            }
                        }
                )
                .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Boton neutral pulsado" + id, Toast.LENGTH_LONG).show();
                            }
                        }
                )
        ;
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//__________________________________________________________________________________________________3
    private void crearDialogoListaEstilo(final String[] colores){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogThemeDos);

        alertDialogBuilder.setTitle("Dialogo de lista")
                .setItems(colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Seleccionaste: " + colores[id], Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//__________________________________________________________________________________________________4
    private void crearDialogoRadioEstilo(final String[] estadoCivil, String titulo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogThemeDos);

        builder.setTitle(titulo)
                .setSingleChoiceItems(estadoCivil, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Seleccionaste: " + estadoCivil[id], Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//__________________________________________________________________________________________________5
    private void crearDialogoCheckBoxEstilo(final String[] items, String titulo){
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this,R.style.CustomDialogThemeDos);
        final ArrayList<Integer> itemsSeleccionados= new ArrayList<>();
        final boolean[] checkedItems={false,false,false};
        alertDialogBuilder.setTitle(titulo)
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            itemsSeleccionados.add(which);
                            elementosSeleccionados.add(items[which]);
                            Toast.makeText(getApplicationContext(),"Checks selecionados: ( "+itemsSeleccionados.size()+itemsSeleccionados.toString()+" )",Toast.LENGTH_LONG).show();
                        }else if (itemsSeleccionados.contains(which)){
                            itemsSeleccionados.remove(Integer.valueOf(which));
                            elementosSeleccionados.remove(items[which]);
                            Toast.makeText(getApplicationContext(),"Checks selecionados: ( "+itemsSeleccionados.size()+itemsSeleccionados.toString()+" )",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"which"+which,Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"which"+which,Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
//__________________________________________________________________________________________________6




}
