package sword.graphicsmatrixsample.matrix_composer;

import android.graphics.Matrix;

import sword.graphicsmatrixsample.MatrixComposer;
import sword.graphicsmatrixsample.Size;

public class Rotate20 implements MatrixComposer {
    @Override
    public Matrix composeMatrix(Size frameSize, Size bitmapSize) {
        final float fitXScale = ((float) frameSize.width) / bitmapSize.width;
        final float fitYScale = ((float) frameSize.height) / bitmapSize.height;
        final float fitScale = Math.min(fitXScale, fitYScale);

        final float marginX = (frameSize.width - bitmapSize.width * fitScale) / 2;
        final float marginY = (frameSize.height - bitmapSize.height * fitScale) / 2;

        Matrix matrix = new Matrix();
        matrix.setTranslate(-bitmapSize.width / 2, -bitmapSize.height / 2);
        matrix.postScale(fitScale, fitScale);
        matrix.postRotate(20);
        matrix.postTranslate(marginX + (bitmapSize.width * fitScale) / 2, marginY + (bitmapSize.height * fitScale) / 2);

        return matrix;
    }
}
