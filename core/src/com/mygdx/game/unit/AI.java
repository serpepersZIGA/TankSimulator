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
    public float TargetLine, TargetLineMinBuffer;
    private int TargetLineTotal,TotalIndex = 1;
    private ArrayList<float[]> OpenBlockList = new ArrayList<>();
    private ArrayList<int[]> CloseBlockList = new ArrayList<>();
    private ArrayList<int[]>BufferPathIteration = new ArrayList<>();
    private boolean conf,ConfPathAdd;
    public static int iteration;
    public int indMin;
    public float TargetLineMin;

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
        iteration = 0;
        TargetLineMin = TargetGet(x,y, target_xy[0],target_xy[1]);
        while (y != target_xy[1] || x != target_xy[0]) {
            xTotal = x+1;
            TargetLineMinBuffer = -1;
            if (!BlockList2D.get((int)y).get((int)xTotal).passability) {
                for (int[] ints : CloseBlockList) {
                    if (xTotal == ints[0] & y == ints[1]) {
                        conf = true;
                        break;
                    }
                }
                if(!conf){
                    TargetLine = TargetGet(xTotal,y, target_xy[0],target_xy[1]);
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
                    TargetLine = TargetGet(xTotal,y, target_xy[0],target_xy[1]);
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
                    TargetLine = TargetGet(x,yTotal, target_xy[0],target_xy[1]);
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
                    TargetLine = TargetGet(x,yTotal, target_xy[0],target_xy[1]);
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
                if(iteration != 0) {
                    iteration -= 1;
                }
            }
            else {
                for (float[] ints : OpenBlockList) {
                    if (TargetLineMinBuffer == -1 || ints[2] < TargetLineMinBuffer) {
                        TargetLineMinBuffer = ints[2];
                        x = ints[0];
                        y = ints[1];

                    }
                }
                if(TargetLineMin+22>=TargetLineMinBuffer) {
                    TargetLineMin = TargetLineMinBuffer;
                    iteration = 0;
                    indMin = CloseBlockList.size()-1;
                }
                else{
                    iteration+= 1;
                }
                if(iteration>=5){
                    x = CloseBlockList.get(indMin)[0];
                    y = CloseBlockList.get(indMin)[1];
                    for(int i = iteration;i>0;i--){
                        if(ai.path.size()>1) {
                            ai.path.remove(ai.path.size() - 1);
                        }
                    }
                }
                else {
                    CloseBlockList.add(new int[]{(int) x, (int) y});
                    ai.path.add(new int[]{(int) x, (int) y});
                }
                TotalIndex = 1;
                OpenBlockList.clear();
            }
        }
//        for (int i = 0;i<ai.path.size();i++){
//            if(i+3<ai.path.size()) {
//
//                for (int i2 = i+2; i2 < ai.path.size(); i2++) {
//                    if(abs(ai.path.get(i)[0]-ai.path.get(i2)[0])<=1&abs(ai.path.get(i)[1]-ai.path.get(i2)[1])<=1){
//                        int ic = i2-i;
//                        System.out.println(ai.path.size()+" 1");
//                        ai.path.subList(i2, i + ic).clear();
//                        System.out.println(ai.path.size()+" 2");
//                    }
//                }
//            }
//        }


//        for (ArrayList<Block> blockY : BlockList2D){
//            for (Block block : blockY) {
//                block.render_block = UpdateRegister.GrassUpdate;
//            }
//        }
//        for (int i = 0;i<ai.path.size();i++){
//            BlockList2D.get(ai.path.get(i)[1]).get(ai.path.get(i)[0]).render_block =
//                    UpdateRegister.Update3;
//        }
        TargetLineMinBuffer = -1;
        conf = false;
        yTotal = 0;
        xTotal = 0;
        OpenBlockList.clear();
        CloseBlockList.clear();
        TotalIndex = 1;

    }
    public static float TargetGet(float x,float y,float x2,float y2){
        return  abs(x2-x)+abs(y2-y);
    }
    public static float TargetGet2(float x,float y,float x2,float y2){
        return  (float) sqrt(pow2(x2-x)+pow2(y2-y));
    }
    public void pathAISoldat(Soldat ai, Unit target, float x_ai, float y_ai){
        ai.path.clear();
        int[] target_xy = block_detected_2Soldat(target);
        int[] ai_xy = block_detected_3(x_ai, y_ai);
        x = ai_xy[0];
        y = ai_xy[1];
        TargetLineMinBuffer = -1;
        conf = false;
        OpenBlockList.clear();
        CloseBlockList.clear();
        while (y != target_xy[1] || x != target_xy[0]) {
            ConfPathAdd = true;
            xTotal = x+1;
            TargetLineMinBuffer = -1;
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
                if (TargetLineMinBuffer == -1 || (float)ints[2] > TargetLineMinBuffer) {
                    TargetLineMinBuffer = (float)ints[2];
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
