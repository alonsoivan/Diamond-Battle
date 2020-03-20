package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.ivn.diamondbattle.models.Personaje;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import static com.ivn.diamondbattle.managers.SpriteManager.miTextura;
import static com.ivn.diamondbattle.managers.SpriteManager.personajes;

public class NetworkManager extends Listener.ThreadedListener {

    static public final int tcpPort = 54555;
    static public final int udpPort = 54777;
    static public final int timeOut = 6000;
    static public final int maxIntentos = 3;

    static public int miId = 0;
    private boolean yo = true;

    static public Client client;

    private int intentos;

    public NetworkManager(){
        super(new Listener());

        try {

            client = new Client();
            client.start();

            registrar();

            InetAddress address = null;
            while(address == null && intentos < maxIntentos){
                address = client.discoverHost(udpPort, timeOut);
                intentos ++;
            }

            client.connect(timeOut, address, tcpPort, udpPort);

            client.addListener(this);

            client.sendTCP(new AddPersonaje(miId,miTextura));  // Le digo que me añada

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void connected (Connection connection) {
        System.out.println("Conectaado aal servidor.");
    }

    public void received (Connection connection, Object object) {
        // TODO
        if (object instanceof RemovePersonaje) {
            personajes.removeKey(((RemovePersonaje) object).id);
        }

        if (object instanceof AddPersonajes) {
            AddPersonajes addPersonajes = (AddPersonajes)object;
            for(int i = 0; i < addPersonajes.ids.size(); i++){
                if(addPersonajes.ids.get(i) != miId)
                    personajes.put(addPersonajes.ids.get(i),new Personaje(ResourceManager.getRegion(addPersonajes.idsTexturas.get(i))));
            }
        }

        if (object instanceof AddPersonaje) {

            AddPersonaje addPersonaje = (AddPersonaje)object;

            if(yo){
                miId = addPersonaje.id;
                personajes.put(miId,new Personaje(ResourceManager.getRegion(miTextura)));
                yo = false;
            }
            else
                personajes.put(addPersonaje.id,new Personaje(ResourceManager.getRegion(addPersonaje.idTexture)));

        }

        if(object instanceof UpdatePersonaje){
            UpdatePersonaje updatePersonaje = (UpdatePersonaje)object;

            System.out.println( personajes.get(updatePersonaje.id) +" -- "+ updatePersonaje.pos);
            personajes.get(updatePersonaje.id).setPosition(updatePersonaje.pos.x,updatePersonaje.pos.y);

            /*
                TODO MOVER BALAS PJ, COMPROBAR QUE BALAS
            if(personajes.get(updatePersonaje.id).balas.size == updatePersonaje.posBalas.size())
                for(int i = 0; i< updatePersonaje.posBalas.size() ; i++){
                    personajes.get(updatePersonaje.id).balas.get(i).position = updatePersonaje.posBalas.get(i);
                }

             */
        }

        /*
        if(object instanceof ArrayList){

            ArrayList<Integer> array = (ArrayList<Integer>)object;
            for(int id : array)
                personajes.put(id,new Personaje(textureE,balaTexture));
        }

        if(object instanceof Disparar){
            Disparar disparar = ((Disparar) object);

            personajes.get(disparar.id).disparar();
        }

        if(object instanceof BorrarBala){
            BorrarBala borrarBala = (BorrarBala)object;

            personajes.get(borrarBala.idPersonaje).balas.removeIndex(borrarBala.posBala);
        }

        */
    }

    public void disconnected (Connection connection) {
        System.out.println("Desconectado del servidor.");
        //System.exit(0);
    }


    // This registers objects that are going to be sent or received over the network.
    public void registrar() {
        Kryo kryo = client.getKryo();

        kryo.register(AddPersonajes.class);
        kryo.register(AddPersonaje.class);
        kryo.register(RemovePersonaje.class);
        kryo.register(UpdatePersonaje.class);
        kryo.register(Vector2.class);
        kryo.register(ArrayList.class);

    }


    // CLASAES PARA REGISTRAR

    static public class UpdatePersonaje {
        public int id;
        public Vector2 pos;
        public ArrayList<Vector2> posBalas;

        public UpdatePersonaje(){}

        public UpdatePersonaje(int id, Vector2 pos){
            this.id = id;
            this.pos = pos;
            posBalas = new ArrayList<>();

        }
    }

    static public class AddPersonaje {
        public int id;
        public String idTexture;

        public AddPersonaje(){}

        public AddPersonaje(int id, String idTexture){
            this.id = id;
            this.idTexture = idTexture;
        }
    }

    static public class RemovePersonaje {
        public int id;

        public RemovePersonaje(){}

        public RemovePersonaje(int id){
            this.id = id;
        }
    }

    static public class AddPersonajes {
        public ArrayList<Integer> ids = new ArrayList<>();
        public ArrayList<String> idsTexturas = new ArrayList<>();
    }
}
