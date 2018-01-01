package sword.graphicsmatrixsample.matrix_composer;

import android.graphics.Matrix;

import sword.graphicsmatrixsample.MatrixComposer;
import sword.graphicsmatrixsample.Size;

public class VerticalFlip implements MatrixComposer {
    @Override
    public Matrix composeMatrix(Size frameSize, Size bitmapSize) {
        final float fitXScale = ((float) frameSize.width) / bitmapSize.width;
        final float fitYScale = ((float) frameSize.height) / bitmapSize.height;
        final float fitScale = Math.min(fitXScale, fitYScale);

        final float marginX = (frameSize.width - bitmapSize.width * fitScale) / 2;
        final float marginY = (frameSize.height - bitmapSize.height * fitScale) / 2;

        final Matrix matrix = new Matrix();
        matrix.setScale(fitScale, -fitScale);
        matrix.postTranslate(marginX, marginY + bitmapSize.height * fitScale);
        return matrix;
    }
}
