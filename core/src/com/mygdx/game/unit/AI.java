package com.mygdx.game.unit;
import com.mygdx.game.block.Block;
import com.mygdx.game.block.UpdateRegister;
import com.mygdx.game.main.Main;
import com.mygdx.game.soldat.Soldat;

import java.util.ArrayList;

import static com.mygdx.game.main.Main.BlockList2D;
import static com.mygdx.game.method.pow2.pow2;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class AI {
    // Координаты ИИ
    private static int x, y,xTotal,yTotal;
    private static byte c;
    private static int TargetLine,TargetLineMin,TargetLineTotal;
    private static ArrayList<int[]> OpenBlockList = new ArrayList<>();
    private static ArrayList<int[]> CloseBlockList = new ArrayList<>();
    private static int[]MinPathTotal;
    private static boolean conf;

    // Метод для обновления позиции ИИ
    public void pathAIAStar(Unit ai, Unit target, float x_ai, float y_ai){
        ai.path.clear();
        int[] target_xy = block_detected_2(target);
        int[] ai_xy = block_detected_3(x_ai, y_ai);
        x = ai_xy[0];
        y = ai_xy[1];
        TargetLineMin = -1;
        conf = false;
        OpenBlockList.clear();
        CloseBlockList.clear();
        //ai.path.add(new int[]{x, y});
        while (y != target_xy[1] || x != target_xy[0]) {
            xTotal = x+1;
            if (!BlockList2D.get(y).get(xTotal).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x+1)+pow2(target_xy[1]-y));
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            xTotal = x-1;
            if (!BlockList2D.get(y).get(xTotal).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x-1)+pow2(target_xy[1]-y));
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            yTotal = y+1;
            if (!BlockList2D.get(yTotal).get(x).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y+1));
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            yTotal = y-1;
            if (!BlockList2D.get(yTotal).get(x).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y-1));
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            for (int[] ints : OpenBlockList) {
                if (TargetLineMin == -1 || ints[2] > TargetLineMin) {
                    TargetLineMin = ints[2];
                    x = ints[0];
                    y = ints[1];
                }
            }
            //TargetLineTotal += 1;
            TargetLineMin = -1;
            CloseBlockList.add(new int[]{x,y});
            ai.path.add(new int[]{x,y});
            OpenBlockList.clear();

        }
//        for (ArrayList<Block> blockY : BlockList2D){
//            for (Block block : blockY) {
//                block.render_block = UpdateRegister.GrassUpdate;
//            }
//        }
//        for (int i = 0;i<CloseBlockList.size();i++){
//            BlockList2D.get(CloseBlockList.get(i)[1]).get(CloseBlockList.get(i)[0]).render_block =
//                    UpdateRegister.Update3;
//        }
    }
    public void pathAISoldat(Soldat ai, Unit target, float x_ai, float y_ai){

        int[] target_xy = block_detected_2Soldat(target);
        int[] ai_xy = block_detected_3(x_ai, y_ai);
        x = ai_xy[0];
        y = ai_xy[1];
        TargetLineMin = -1;
        while (y != target_xy[1] || x != target_xy[0]) {
            xTotal = x+1;
            if (!BlockList2D.get(y).get(xTotal).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x+1)+pow2(target_xy[1]-y));
                for (int i = 0;i<CloseBlockList.size();i++) {
                    if(xTotal== CloseBlockList.get(i)[0]&y== CloseBlockList.get(i)[1]){
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            xTotal = x-1;
            if (!BlockList2D.get(y).get(xTotal).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x-1)+pow2(target_xy[1]-y));
                for (int i = 0;i<CloseBlockList.size();i++) {
                    if(xTotal== CloseBlockList.get(i)[0]&y== CloseBlockList.get(i)[1]){
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            yTotal = y+1;
            if (!BlockList2D.get(yTotal).get(x).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y+1));
                for (int i = 0;i<CloseBlockList.size();i++) {
                    if(x== CloseBlockList.get(i)[0]&yTotal== CloseBlockList.get(i)[1]){
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            yTotal = y-1;
            if (!BlockList2D.get(yTotal).get(x).passability) {
                TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y-1));
                for (int i = 0;i<CloseBlockList.size();i++) {
                    if(x== CloseBlockList.get(i)[0]&yTotal== CloseBlockList.get(i)[1]){
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    OpenBlockList.add(new int[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            for(int i = 0;i<OpenBlockList.size();i++){
                if(TargetLineMin == -1||OpenBlockList.get(i)[2]>TargetLineMin){
                    TargetLineMin = OpenBlockList.get(i)[2];
                    x = OpenBlockList.get(i)[0];
                    y = OpenBlockList.get(i)[1];
                    MinPathTotal = new int[]{OpenBlockList.get(i)[0],OpenBlockList.get(i)[1]};
                }
            }
            //TargetLineTotal += 10;
            TargetLineMin = -1;
            CloseBlockList.add(new int[]{MinPathTotal[0],MinPathTotal[1]});
            ai.path.add(new int[]{MinPathTotal[0],MinPathTotal[1]});
            OpenBlockList.clear();

        }
        CloseBlockList.clear();
    }
    public int[] block_detected_2(Unit tr){
        int i = (int)(tr.tower_y/Main.height_block)-1;
        int i2 = (int)(tr.tower_x/Main.width_block)-1;
        //Main.BlockList2D.get(i).get(i2).render_block = UpdateRegister.Update3;
        if(!BlockList2D.get(i).get(i2).passability) {
            return new int[]{i2, i};
        }
        else{
            if(!BlockList2D.get(i+1).get(i2).passability){
                return new int[]{i2, i-1};
            }
            else if(!BlockList2D.get(i-1).get(i2).passability){
                return new int[]{i2, i+1};
            }
            else if(!BlockList2D.get(i).get(i2+1).passability){
                return new int[]{i2-1, i};
            }
            else if(!BlockList2D.get(i).get(i2-1).passability){
                return new int[]{i2+1, i};
            }
        }
        return new int[]{i2, i};
    }
    public int[] block_detected_3(double x,double y){
        int i = (int)(y/Main.height_block)-1;
        int i2 = (int)(x/Main.width_block)-1;
        if(!BlockList2D.get(i).get(i2).passability) {
            return new int[]{i2, i};
        }
        else{
            if(!BlockList2D.get(i+1).get(i2).passability){
                return new int[]{i2, i+1};
            }
            else if(!BlockList2D.get(i-1).get(i2).passability){
                return new int[]{i2, i-1};
            }
            else if(!BlockList2D.get(i).get(i2+1).passability){
                return new int[]{i2+1, i};
            }
            else if(!BlockList2D.get(i).get(i2-1).passability){
                return new int[]{i2-1, i};
            }
        }
        return new int[]{i2, i};
    }
    public int[] block_detected_2Soldat(Unit tr){
        int i = (int)(tr.tower_y/Main.height_block)-1;
        int i2 = (int)(tr.tower_x/Main.width_block)-1;
        try {
            if (!BlockList2D.get(i).get(i2).passability) {
                return new int[]{i2, i};
            } else {
                if (!BlockList2D.get(i + 1).get(i2).passability) {
                    return new int[]{i2, i - 1};
                } else if (!BlockList2D.get(i - 1).get(i2).passability) {
                    return new int[]{i2, i + 1};
                } else if (!BlockList2D.get(i).get(i2 + 1).passability) {
                    return new int[]{i2 - 1, i};
                } else if (!BlockList2D.get(i).get(i2 - 1).passability) {
                    return new int[]{i2 + 1, i};
                }
            }
            return new int[]{i2, i};
        }
        catch (IndexOutOfBoundsException e){
            return new int[]{2, 2};
        }
    }
}
