package com.example.mainactivity;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

//
public class Mimapa extends FragmentActivity implements GoogleMap.OnMarkerDragListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker markerPrueba, markerDrag;
    private Button btn_Hibrido, btn_Normal, btn_Satelital, btn_Terreno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn_Hibrido = (Button) findViewById(R.id.btn_Hibrido);
        btn_Normal = (Button) findViewById(R.id.btn_Normal);
        btn_Satelital = (Button) findViewById(R.id.btn_Satelital);
        btn_Terreno = (Button) findViewById(R.id.btn_Terreno);
    }

    //Metodo para crer el mapa hibrido
    public void CambiarHibrido(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    //Metodo para crear el mapa satelital
    public void CambiarSatelital(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    //Metodo para cambiar el mapa a terreno
    public void CambiarTerreno(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    //Metddo para cambiar a mapa a normal
    public void CambiarNormal(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Metodo para el zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //Se agrego permisos para la ubicacion en tiempo real
        if (ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //Metodos para acceder a la localizacion
        mMap.setMyLocationEnabled(true);
        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Loja = new LatLng(-3.99313, -79.20422);
        mMap.addMarker(new MarkerOptions().position(Loja).draggable(true)
                .title("Estoy en Loja Id:12").snippet("La bella ciudad de loja")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Loja));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Estadio = new LatLng(-4.0009866, -79.1987194);
        mMap.addMarker(new MarkerOptions().position(Estadio).draggable(true)
                .title("Estadio Reina del Cisne id:10").snippet("El Estadio Federativo Reina del Cisne, " +
                        "es un estadio multiusos. Está ubicado en la avenida Emiliano Ortega y calle Azuay de la ciudad de Loja a 2082 msnm." +
                        " Fue inaugurado el 7 de septiembre de 1980, Es usado mayoritariamente para la práctica del fútbol, " +
                        "y allí juega como local la Liga Deportiva Universitaria de Loja, equipo de la Serie B del fútbol ecuatoriano. " +
                        "Su capacidad es de 14 935 espectadores.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Estadio));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Eolico = new LatLng(-4.0009873, -79.2613992);
        mMap.addMarker(new MarkerOptions().position(Eolico).draggable(true)
                .title("Parque Eolico de Loja id:08")
                .snippet("Un parque donde podemos disfrutar de la vista")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.uno)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Eolico));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Jipiro = new LatLng(-3.9720295, -79.2056799);
        mMap.addMarker(new MarkerOptions().position(Jipiro).draggable(true)
                .title("Parque Jipiro id:01")
                .snippet("Un parque recreacional con mucha diversion")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dos)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jipiro));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Lineal = new LatLng(-4.0146452, -79.2378565);
        mMap.addMarker(new MarkerOptions().position(Lineal).draggable(true)
                .title("Parque Lineal de la Tebaida id:02")
                .snippet("Un hermoso parque donde contamos con un rio mucha area verde , etc.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dos)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Lineal));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Churo = new LatLng(-3.9988782, -79.19748565);
        mMap.addMarker(new MarkerOptions().position(Churo).draggable(true)
                .title("Mirador El Churo id:!5").snippet("Un mirador muy alto en donde se pued ver loja ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tres)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Churo));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Pileta = new LatLng(-3.9952607, -79.1992558);
        mMap.addMarker(new MarkerOptions().position(Pileta).draggable(true)
                .title("La Pileta id:05").snippet("Una pileta bien hermosa donde pueden disfrutar en familia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.valle)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pileta));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng Carigan_Villonaco = new LatLng(-3.9591207, -79.2465647);
        mMap.addMarker(new MarkerOptions().position(Carigan_Villonaco).draggable(true)
                .title("Parque Colinar Carigán Villonaco id:20")
                .snippet("Famoso parque donde disfrutamos de todo el espacio")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dos)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Carigan_Villonaco));

        //Se agrego latitud, longitud, titulo, descripcion, imagen
        LatLng U_nacional = new LatLng(-4.035749, -79.2050743);
        mMap.addMarker(new MarkerOptions().position(U_nacional).draggable(true)
                .title("Universidad Nacional de Loja id:18").snippet("La bella ciudad de loja")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(U_nacional));

        //
        LatLng prueba = new LatLng(-3.99313, -79.20422);
        markerPrueba = googleMap.addMarker(new MarkerOptions()
                .position(prueba)
                .title("Prueba")
        );


        LatLng Morelos = new LatLng(-4.313, -79.20422);
        markerDrag = googleMap.addMarker(new MarkerOptions()
                .position(Morelos)
                .title("Ubicacion")
                .draggable(true)
        );

        //Camara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Loja, 10));
        //clic en el marcador
        googleMap.setOnMarkerClickListener(this);
        //Arrastrar el marcador
        googleMap.setOnMarkerDragListener(this);
    }

    @Override

    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerPrueba)) {
            String lat, lng;
            lat = Double.toString(marker.getPosition().latitude);
            lng = Double.toString(marker.getPosition().longitude);
            Toast.makeText(this, lat + " , " + lng, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerDrag)) {
            Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerDrag)) {
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerDrag)) {
            Toast.makeText(this, "Finish", Toast.LENGTH_LONG).show();
            //para que aparesca de nuevo el nombre
            setTitle(R.string.sitios);
        }
    }
}
