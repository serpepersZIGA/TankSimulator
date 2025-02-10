package Data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DataImage {
    public Sprite tower_player, tower_player_auxiliary_1,tower_enemy, tower_enemy_auxiliary_1;
    public Sprite corpus_player,corpus_track_remount_enemy,corpus_track_soldat_enemy,corpus_enemy,helicopter_enemy_t1;
    public Sprite build_1, big_build_wood_1;
    public Sprite track_enemy_1lvl;
    public Sprite grass,dirt_2,dirt_3,dirt_4;
    public Sprite asphalt1;
    public Sprite soldat_1;
    public Texture buff;
    public DataImage(){
        corpus_track_soldat_enemy = new Sprite(LoadImage("image/enemy/machine_enemy_1lvl.png"));
        corpus_track_remount_enemy = new Sprite(LoadImage("image/enemy/corpus_enemy_medic_1.png"));
        tower_player = new Sprite(LoadImage("image/player/tower_player_1.png"));
        tower_enemy = new Sprite(LoadImage("image/enemy/tower_enemy_1.png"));
        corpus_player = new Sprite(LoadImage("image/player/corpus_player_many_tower_1.png"));
        corpus_enemy = new Sprite(LoadImage("image/enemy/corpus_enemy_many_tower_1.png"));
        build_1 = new Sprite(LoadImage("image/build/home_3.png"));
        big_build_wood_1 = new Sprite(LoadImage("image/build/big_build_wood_1.png"));
        grass = new Sprite(LoadImage("image/other/grass_256_1.png"));
        dirt_2 = new Sprite(LoadImage("image/other/dirt_256_1.png"));
        dirt_3 = new Sprite(LoadImage("image/other/dirt_256_2.png"));
        dirt_4 = new Sprite(LoadImage("image/other/dirt_2.png"));
        asphalt1 = new Sprite(LoadImage("image/other/asphalt1.png"));
        tower_player_auxiliary_1 = new Sprite(LoadImage("image/player/tower_auxiliart_player_1.png"));
        tower_enemy_auxiliary_1 = new Sprite(LoadImage("image/enemy/tower_auxiliart_enemy_1.png"));
        soldat_1 = new Sprite(LoadImage("image/infantry/soldat_enemy.png"));
        helicopter_enemy_t1 = new Sprite(LoadImage("image/enemy/helicopter_corpus.png"));
        track_enemy_1lvl = new Sprite(LoadImage("image/enemy/machine_enemy_1lvl.png"));
        new Sprite(LoadImage("image/enemy/machine_enemy_1lvl.png"));
        buff.dispose();
    }
    private Texture LoadImage(String image){
        buff = new Texture(image);
        return buff;
    }
    public void dispose(){
        corpus_track_soldat_enemy = null;
        corpus_track_remount_enemy = null;
        tower_player = null;
        tower_enemy = null;
        corpus_player =null;
        build_1 = null;
        big_build_wood_1 = null;
        grass = null;
        dirt_2 = null;
        dirt_3 = null;
        dirt_4 = null;
        asphalt1 = null;
        tower_player_auxiliary_1 = null;
        tower_enemy_auxiliary_1 = null;
        soldat_1 = null;
        track_enemy_1lvl = null;

    }
}
