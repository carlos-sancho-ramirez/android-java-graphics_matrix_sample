package sword.graphicsmatrixsample.matrix_composer;

import android.graphics.Matrix;

import sword.graphicsmatrixsample.MatrixComposer;
import sword.graphicsmatrixsample.Size;

public final class Zoom3x implements MatrixComposer {
    @Override
    public Matrix composeMatrix(Size frameSize, Size bitmapSize) {
        final float fitXScale = ((float) frameSize.width) / bitmapSize.width;
        final float fitYScale = ((float) frameSize.height) / bitmapSize.height;
        final float fitScale = Math.min(fitXScale, fitYScale);

        Matrix matrix = new Matrix();
        matrix.setScale(fitScale * 3.0f, fitScale * 3.0f);
        return matrix;
    }
}
