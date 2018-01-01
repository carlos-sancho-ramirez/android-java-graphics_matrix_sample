package sword.graphicsmatrixsample;

import android.graphics.Matrix;

public interface MatrixComposer {
    Matrix composeMatrix(Size frameSize, Size bitmapSize);
}
