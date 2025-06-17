// GraphicalPrimitives.java - Custom ImageView to draw shapes based on touch events
package lab.cahcet.edu.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GraphicalPrimitives extends ImageView {
    private String shape = "";
    private PointF point;
    private Paint painttext = new Paint();
    private Paint paintrectangle = new Paint();
    private Paint paintcircle = new Paint();

    public GraphicalPrimitives(Context context) { super(context); }
    public GraphicalPrimitives(Context context, AttributeSet attrs) { super(context, attrs); }
    public GraphicalPrimitives(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        painttext.setColor(Color.GRAY);
        painttext.setTextSize(28);
        paintrectangle.setColor(Color.MAGENTA);
        paintcircle.setColor(Color.CYAN);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                point = new PointF(x, y);
                shape = "text";
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                point = new PointF(x, y);
                shape = "rectangle";
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                point.set(x, y);
                shape = "circle";
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (shape.equals("rectangle"))
            canvas.drawRect(point.x, point.y, point.x / 4, point.y / 4, paintrectangle);
        else if (shape.equals("text"))
            canvas.drawText("Touch Me!", point.x, point.y, painttext);
        else if (shape.equals("circle"))
            canvas.drawCircle(point.x, point.y, 100, paintcircle);
    }
}
