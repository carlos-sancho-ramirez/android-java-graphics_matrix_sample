package sword.graphicsmatrixsample.matrix_composer;

import android.graphics.Matrix;

import sword.graphicsmatrixsample.MatrixComposer;
import sword.graphicsmatrixsample.Size;

public class Projection2D implements MatrixComposer {

    private static final float SIDE = 0.2f;

    @Override
    public Matrix composeMatrix(Size frameSize, Size bitmapSize) {
        final float deformedWidth = bitmapSize.width * (1 + SIDE);
        final float ratio = ((float) bitmapSize.width) / bitmapSize.height;
        final float fitXScale = ((float) frameSize.width) / deformedWidth;
        final float fitYScale = ((float) frameSize.height) / bitmapSize.height;
        final float fitScale = Math.min(fitXScale, fitYScale);

        final float marginX = (frameSize.width - deformedWidth * fitScale) / 2;
        final float marginY = (frameSize.height - bitmapSize.height * fitScale) / 2;

        final float[] values = new float[9];
        values[0] = fitScale;
        values[1] = ratio * SIDE * fitScale;
        values[2] = marginX;

        values[3] = 0;
        values[4] = fitScale;
        values[5] = marginY;

        values[6] = 0;
        values[7] = 0;
        values[8] = 1;

        final Matrix matrix = new Matrix();
        matrix.setValues(values);
        return matrix;
    }
}
