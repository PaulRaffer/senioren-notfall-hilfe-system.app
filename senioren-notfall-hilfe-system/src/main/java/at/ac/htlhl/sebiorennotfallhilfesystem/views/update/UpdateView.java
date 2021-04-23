package at.ac.htlhl.sebiorennotfallhilfesystem.views.update;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

// Derive from this Class using the
// Curiously recurring template pattern!
// https://en.wikipedia.org/wiki/Curiously_recurring_template_pattern
// e.g. public class MyView extends UpdateView<MyView> { ... }
public class UpdateView<View> extends VerticalLayout {

    // Background thread, which updates the view
    private UpdateThread updateThread;

    // Function, which updates the view
    private UpdateFunction updateFunction;

    private long pauseMillis = 2000; // Update every 2 s

    public interface UpdateFunction<View> { // Lambda interface
        // Update object of subclass
        void update(View view);
    }
    protected void setUpdateFunction(UpdateFunction<View> f)
    {
        updateFunction = f;
    }
    protected void setUpdatePause(long millis)
    {
        pauseMillis = millis;
    }

    @Override
    protected void onAttach(AttachEvent e)
    {
        // Start new thread
        updateThread = new UpdateThread(e.getUI(), this);
        updateThread.start();
    }

    @Override
    protected void onDetach(DetachEvent e)
    {
        // Stop thread
        updateThread.interrupt();
        updateThread = null;
    }

    private class UpdateThread extends Thread {
        private final UI ui;
        private final UpdateView view;

        public UpdateThread(
                final UI ui, final UpdateView view)
        {
            this.ui = ui;
            this.view = view;
        }

        @Override
        public void run()
        {
            try {
                while (true) {
                    Thread.sleep(view.pauseMillis); // Wait
                    ui.access(() -> { // Lock session
                        updateFunction.update(view); // Update view
                    });
                }
            }
            catch (InterruptedException e) {
                // We don't care if sleep was interrupted.
            }
        }
    }
}
