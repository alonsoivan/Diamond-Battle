package com.ivn.diamondbattle.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ivn.diamondbattle.managers.CameraManager;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.managers.SpriteManager.personajes;

public class Util {

    public static float getRotation(){
        float angle = (float) Math.atan2(getMousePosInGameWorld().y - personajes.get(miId).getY(), getMousePosInGameWorld().x - personajes.get(miId).getX());
        return (float) Math.toDegrees(angle);
    }

    public static Vector3 getMousePosInGameWorld() {
        return CameraManager.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    public static Vector2 getPosInGameWorld(Vector2 pos){
        Vector3 aux = CameraManager.camera.unproject(new Vector3(pos.x,pos.y, 0));
        return new Vector2(aux.x,aux.y);
    }
}
