package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.ivn.diamondbattle.models.Bala;
import com.ivn.diamondbattle.models.Diamante;
import com.ivn.diamondbattle.models.Personaje;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import static com.ivn.diamondbattle.managers.SpriteManager.*;
import static com.ivn.diamondbattle.util.Constantes.TEXTURE_ATLAS_GAME_GUN;

public class NetworkManager extends Listener.ThreadedListener {

    static public final int tcpPort = 54555;
    static public final int udpPort = 54777;
    static public final int timeOut = 6000;

    static public int miId = 0;

    static public Client client;

    public NetworkManager(){
        super(new Listener());

        try {

            client = new Client();
            client.start();

            registrar();

            InetAddress address = client.discoverHost(udpPort, timeOut);


            //client.connect(timeOut, "25.103.219.29", tcpPort, udpPort);
            client.connect(timeOut, address, tcpPort, udpPort);


            client.addListener(this);

            client.sendTCP(new AddPersonaje(client.getID(),miTextura,miNombre));  // Le digo que me añada

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void connected (Connection connection) {
        System.out.println("Conectado al servidor.");
    }

    public void received (Connection connection, Object object) {

        if(object instanceof AddDiamante){
            AddDiamante addDiamante = (AddDiamante)object;

            diamantes.put(addDiamante.id, new Diamante(addDiamante.pos));
        }

        if(object instanceof RemoveDiamante){
            RemoveDiamante removeDiamante = (RemoveDiamante) object;

            diamantes.removeKey(removeDiamante.id);
        }


        // TODO
        if (object instanceof RemovePersonaje) {
            personajes.removeKey(((RemovePersonaje) object).id);
        }

        if (object instanceof AddPersonajes) {
            AddPersonajes addPersonajes = (AddPersonajes)object;

            System.out.println("tamnio array personajes - "+addPersonajes.ids.size());
            for(int i = 0; i < addPersonajes.ids.size(); i++){
                if(addPersonajes.ids.get(i) != miId)
                    personajes.put(addPersonajes.ids.get(i),new Personaje(ResourceManager.getRegion(addPersonajes.idsTexturas.get(i), TEXTURE_ATLAS_GAME_GUN),addPersonajes.nombres.get(i), false));
            }
        }

        if (object instanceof AddPersonaje) {

            AddPersonaje addPersonaje = (AddPersonaje)object;

            miId = connection.getID();
            personajes.put(miId,new Personaje(ResourceManager.getRegion(miTextura,TEXTURE_ATLAS_GAME_GUN),miNombre, true));

        }

        if(object instanceof UpdatePersonaje){
            UpdatePersonaje updatePersonaje = (UpdatePersonaje)object;

            if(personajes.get(updatePersonaje.id) != null) {
                personajes.get(updatePersonaje.id).setPosition(updatePersonaje.pos.x, updatePersonaje.pos.y);
                personajes.get(updatePersonaje.id).setRotation(updatePersonaje.rotation);
                personajes.get(updatePersonaje.id).setVida(updatePersonaje.vida);
                personajes.get(updatePersonaje.id).tamañoLaser = updatePersonaje.tamañoLaser;
                personajes.get(updatePersonaje.id).diamantes = updatePersonaje.diamantes;
            }

             //   TODO MOVER BALAS PJ, COMPROBAR QUE BALAS

            if(personajes.get(updatePersonaje.id) != null) {
                personajes.get(updatePersonaje.id).balas.clear();
                for (int i = 0; i < updatePersonaje.posBalas.size(); i++) {
                    Bala bala = new Bala(updatePersonaje.posBalas.get(i));
                    bala.setRotation(updatePersonaje.rotationBalas.get(i));
                    personajes.get(updatePersonaje.id).balas.add(bala);
                }
            }

        }

        /*

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
        kryo.register(MovePersonaje.class);
        kryo.register(Disparar.class);
        kryo.register(AddDiamante.class);
        kryo.register(RemoveDiamante.class);

    }


    // CLASAES PARA REGISTRAR

    static public class UpdatePersonaje {
        public int id;
        public Vector2 pos;
        public float rotation;
        public ArrayList<Vector2> posBalas;
        public ArrayList<Float> rotationBalas;
        public int vida;
        public float tamañoLaser;
        public int diamantes;

        public UpdatePersonaje(){}

        public UpdatePersonaje(int id, Vector2 pos, int vida){
            this.id = id;
            this.pos = pos;
            posBalas = new ArrayList<>();
            rotationBalas = new ArrayList<>();
            this.vida = vida;
        }
    }

    static public class AddPersonaje {
        public int id;
        public String idTexture;
        public String nombre;

        public AddPersonaje(){}

        public AddPersonaje(int id, String idTexture, String nombre){
            this.id = id;
            this.idTexture = idTexture;
            this.nombre = nombre;
        }
    }

    static public class RemovePersonaje {
        public int id;

        public RemovePersonaje(){}

        public RemovePersonaje(int id){
            this.id = id;
        }
    }

    static public class MovePersonaje {
        public int id;
        public Vector2 dir;
        public float rotation;
        public Vector2 poscionCursor;

        public MovePersonaje(){}

        public MovePersonaje(int id){
            this.id = id;
        }
    }

    static public class AddPersonajes {
        public ArrayList<Integer> ids = new ArrayList<>();
        public ArrayList<String> idsTexturas = new ArrayList<>();
        public ArrayList<String> nombres = new ArrayList<>();
    }

    static public class Disparar {
        public int id;
        public Vector2 dir;

        public Disparar(){}

        public Disparar(Vector2 dir){
            this.dir = dir;
        }
    }

    static public class AddDiamante {
        public int id;
        public Vector2 pos;

        public AddDiamante(){}

        public AddDiamante(int id, Vector2 pos){
            this.id = id;
            this.pos = pos;
        }
    }

    static public class RemoveDiamante {
        public int id;

        public RemoveDiamante(int id ){this.id = id;}
        public RemoveDiamante(){}
    }

}
