package sword.graphicsmatrixsample;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView _imageView;

    private Matrix _normalMatrix;
    private Matrix _zoomInMatrix;

    private static final class Size {
        final int width;
        final int height;

        Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    private Size getBitmapSize() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), R.drawable.sample_image, options);
        return new Size(options.outWidth, options.outHeight);
    }

    private Size getImageFrameSize() {
        return new Size(_imageView.getWidth(), _imageView.getHeight());
    }

    private void initMatrixes() {
        if (_normalMatrix == null) {
            final Size frameSize = getImageFrameSize();
            final Size bitmapSize = getBitmapSize();

            final float fitXScale = ((float) frameSize.width) / bitmapSize.width;
            final float fitYScale = ((float) frameSize.height) / bitmapSize.height;
            final float fitScale = Math.min(fitXScale, fitYScale);

            final float marginX = (frameSize.width - bitmapSize.width * fitScale) / 2;
            final float marginY = (frameSize.height - bitmapSize.height * fitScale) / 2;

            _normalMatrix = new Matrix();
            _normalMatrix.setScale(fitScale, fitScale);
            _normalMatrix.postTranslate(marginX, marginY);

            _zoomInMatrix = new Matrix();
            _zoomInMatrix.setScale(fitScale * 3.0f, fitScale * 3.0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        _imageView = findViewById(R.id.imageView);
        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnZoomIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        initMatrixes();
        final Matrix matrix;
        switch (view.getId()) {
            case R.id.btnZoomIn:
                matrix = _zoomInMatrix;
                break;

            default:
                matrix = _normalMatrix;
        }

        _imageView.setImageMatrix(matrix);
    }
}
