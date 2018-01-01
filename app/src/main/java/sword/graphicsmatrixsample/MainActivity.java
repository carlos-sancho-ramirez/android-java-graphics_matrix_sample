package sword.graphicsmatrixsample;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import sword.graphicsmatrixsample.matrix_composer.HorizontalFlip;
import sword.graphicsmatrixsample.matrix_composer.Normal;
import sword.graphicsmatrixsample.matrix_composer.VerticalFlip;
import sword.graphicsmatrixsample.matrix_composer.Zoom3x;

public final class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private interface Options {
        int NORMAL = 0;
        int ZOOM_3X = 1;
        int FLIP_HORIZONTAL = 2;
        int FLIP_VERTICAL = 3;
    }

    private ImageView _imageView;

    private static final class Option {
        final int text;
        final MatrixComposer composer;

        private Matrix _matrix;

        public Option(int text, MatrixComposer composer) {
            if (composer == null) {
                throw new IllegalArgumentException();
            }

            this.text = text;
            this.composer = composer;
        }

        public Matrix getMatrix() {
            if (_matrix == null) {
                throw new UnsupportedOperationException("Matrix not initialised");
            }

            return _matrix;
        }

        public void initMatrix(Size frameSize, Size bitmapSize) {
            if (_matrix == null) {
                _matrix = composer.composeMatrix(frameSize, bitmapSize);
                if (_matrix == null) {
                    throw new AssertionError("Matrix composer returned null");
                }
            }
        }
    }

    private static final SparseArray<Option> options = new SparseArray<>();
    static {
        options.put(Options.NORMAL, new Option(R.string.btnNormal, new Normal()));
        options.put(Options.ZOOM_3X, new Option(R.string.btnZoomIn, new Zoom3x()));
        options.put(Options.FLIP_HORIZONTAL, new Option(R.string.btnFlipHorizontal, new HorizontalFlip()));
        options.put(Options.FLIP_VERTICAL, new Option(R.string.btnFlipVertical, new VerticalFlip()));
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

    private void initMatrices() {
        final int optionCount = options.size();
        final Size frameSize = getImageFrameSize();
        final Size bitmapSize = getBitmapSize();

        for (int i = 0; i < optionCount; i++) {
            options.valueAt(i).initMatrix(frameSize, bitmapSize);
        }
    }

    private class OptionAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return options.size();
        }

        @Override
        public Option getItem(int position) {
            return options.valueAt(position);
        }

        @Override
        public long getItemId(int position) {
            return options.keyAt(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView view;
            if (convertView == null) {
                view = (TextView) getLayoutInflater().inflate(R.layout.option_item, parent, false);
            }
            else {
                view = (TextView) convertView;
            }

            view.setText(getItem(position).text);
            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        _imageView = findViewById(R.id.imageView);

        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new OptionAdapter());
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemId) {
        initMatrices();

        final OptionAdapter adapter = (OptionAdapter) adapterView.getAdapter();
        _imageView.setImageMatrix(adapter.getItem(position).getMatrix());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Nothing to be done
    }
}
