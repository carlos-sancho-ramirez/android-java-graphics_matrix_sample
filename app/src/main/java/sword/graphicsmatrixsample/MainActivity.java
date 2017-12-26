package sword.graphicsmatrixsample;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView _imageView;

    private Matrix _normalMatrix;
    private Matrix _zoomInMatrix;

    private void initMatrixes() {
        _normalMatrix = new Matrix();

        _zoomInMatrix = new Matrix();
        _zoomInMatrix.setScale(4.0f, 4.0f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        _imageView = findViewById(R.id.imageView);

        initMatrixes();

        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnZoomIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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
