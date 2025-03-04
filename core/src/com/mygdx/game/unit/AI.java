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
    private float x, y,xTotal,yTotal;
    public float TargetLine,TargetLineMin;
    private int TargetLineTotal,TotalIndex = 1;
    private ArrayList<float[]> OpenBlockList = new ArrayList<>();
    private ArrayList<int[]> CloseBlockList = new ArrayList<>();
    private ArrayList<int[]>BufferPathIteration = new ArrayList<>();
    private boolean conf,ConfPathAdd;

    // Метод для обновления позиции ИИ
    public void pathAIAStar(Unit ai, Unit target, float x_ai, float y_ai){
        ai.path.clear();
        int[] target_xy = block_detected_2(target);
        int[] ai_xy = block_detected_3(x_ai, y_ai);
        x = ai_xy[0];
        y = ai_xy[1];
        conf = false;
        yTotal = 0;
        xTotal = 0;
        OpenBlockList.clear();
        CloseBlockList.clear();
        TotalIndex = 1;
        ai.path.add(new int[]{(int)x, (int)y});
        CloseBlockList.add(new int[]{(int)x,(int)y});
        while (y != target_xy[1] || x != target_xy[0]) {
            xTotal = x+1;
            TargetLineMin = -1;
            if (!BlockList2D.get((int)y).get((int)xTotal).passability) {
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf){
                    TargetLine = (float) sqrt(pow2(target_xy[0]-x+1)+pow2(target_xy[1]-y));
                    OpenBlockList.add(new float[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            xTotal = x-1;
            if (!BlockList2D.get((int)y).get((int)xTotal).passability) {
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (float) sqrt(pow2(target_xy[0]-x-1)+pow2(target_xy[1]-y));
                    OpenBlockList.add(new float[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            yTotal = y+1;
            if (!BlockList2D.get((int)yTotal).get((int)x).passability) {
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (float) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y+1));
                    OpenBlockList.add(new float[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            yTotal = y-1;
            if (!BlockList2D.get((int)yTotal).get((int)x).passability) {
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (float) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y-1));
                    OpenBlockList.add(new float[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            if(OpenBlockList.size()== 0){
                if(ai.path.size()== 0) {
                    break;
                }
                x = ai.path.get(ai.path.size()-1)[0];
                y = ai.path.get(ai.path.size()-1)[1];
                ai.path.remove(ai.path.size()-1);
                TotalIndex += 1;
            }
            else {
                for (float[] ints : OpenBlockList) {
                    if (TargetLineMin == -1 || ints[2] > TargetLineMin) {
                        TargetLineMin = ints[2];
                        x = ints[0];
                        y = ints[1];
                    }
                }
                CloseBlockList.add(new int[]{(int)x,(int)y});
                ai.path.add(new int[]{(int)x, (int)y});
                TotalIndex = 1;
                OpenBlockList.clear();
            }
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
        TargetLineMin = -1;
        conf = false;
        yTotal = 0;
        xTotal = 0;
        OpenBlockList.clear();
        CloseBlockList.clear();
        TotalIndex = 1;

    }
    public void pathAISoldat(Soldat ai, Unit target, float x_ai, float y_ai){
        ai.path.clear();
        int[] target_xy = block_detected_2Soldat(target);
        int[] ai_xy = block_detected_3(x_ai, y_ai);
        x = ai_xy[0];
        y = ai_xy[1];
        TargetLineMin = -1;
        conf = false;
        OpenBlockList.clear();
        CloseBlockList.clear();
        while (y != target_xy[1] || x != target_xy[0]) {
            ConfPathAdd = true;
            xTotal = x+1;
            TargetLineMin = -1;
            if (!BlockList2D.get((int)y).get((int)xTotal).passability) {
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf){
                    TargetLine = (int) sqrt(pow2(target_xy[0]-x+1)+pow2(target_xy[1]-y));
                    OpenBlockList.add(new float[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            xTotal = x-1;
            if (!BlockList2D.get((int)y).get((int)xTotal).passability) {
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (int) sqrt(pow2(target_xy[0]-x-1)+pow2(target_xy[1]-y));
                    OpenBlockList.add(new float[]{xTotal, y, TargetLine});
                }
                conf = false;
            }
            yTotal = y+1;
            if (!BlockList2D.get((int)yTotal).get((int)x).passability) {
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y+1));
                    OpenBlockList.add(new float[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            yTotal = y-1;
            if (!BlockList2D.get((int)yTotal).get((int)x).passability) {
                for (int[] ints : CloseBlockList) {
                    if (x == ints[0] & yTotal == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf) {
                    TargetLine = (int) sqrt(pow2(target_xy[0]-x)+pow2(target_xy[1]-y-1));
                    OpenBlockList.add(new float[]{x, yTotal, TargetLine});
                }
                conf = false;
            }
            if(OpenBlockList.size()== 0){
                //BufferPathIteration.add(CloseBlockList.get(CloseBlockList.size()-1));
                ConfPathAdd = false;
                x = CloseBlockList.get(CloseBlockList.size()-TotalIndex)[0];
                y = CloseBlockList.get(CloseBlockList.size()-TotalIndex)[1];
                //CloseBlockList.remove(CloseBlockList.size()-1);
            }
            for (float[] ints : OpenBlockList) {
                if (TargetLineMin == -1 || (float)ints[2] > TargetLineMin) {
                    TargetLineMin = (float)ints[2];
                    x = (int)ints[0];
                    y = (int)ints[1];
                }
            }
            //TargetLineTotal += 1;
            if(ConfPathAdd) {
                CloseBlockList.add(new int[]{(int)x,(int)y});
                ai.path.add(new int[]{(int)x, (int)y});
                TotalIndex = 2;
                System.out.println(OpenBlockList.size());
                System.out.println(target_xy[0]+"Target"+target_xy[1]);
                System.out.println(ai.path.get(ai.path.size() - 1)[0] + " " + ai.path.get(ai.path.size() - 1)[1]);
            }
            else{
                System.out.println(OpenBlockList.size());
                System.out.println(target_xy[0]+"Target"+target_xy[1]);
                if(ai.path.size()!= 0) {
                    System.out.println(ai.path.get(ai.path.size() - 1)[0] + " " + ai.path.get(ai.path.size() - 1)[1]);
                    ai.path.remove(ai.path.size()-1);
                }
                TotalIndex += 1;
            }
//            System.out.println(OpenBlockList.size());
//            System.out.println(x+" "+y);
            //System.out.println(target_xy[0]+"Target"+target_xy[1]);
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
    public int[] block_detected_3(float x,float y){
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
