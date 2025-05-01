package com.mygdx.game.bull.Updater;

import com.mygdx.game.bull.Bullet;
import com.mygdx.game.main.Main;

public class Circle extends UpdaterBullet{

    public void Update(Bullet bullet) {
        Main.Render.setColor(bullet.r,bullet.g,bullet.b,1);
        bullet.center_render();
        Main.Render.circle(bullet.x_rend,bullet.y_rend,bullet.size_render,bullet.size);
    }
}
