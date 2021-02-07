package at.ac.htlhl.sebiorennotfallhilfesystem.views.update;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.Data;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UpdateView<View> extends VerticalLayout {
	private UpdateThread updateThread;
	private UpdateFunction updateFunction;
	private long pauseMillis = 500;

	public interface UpdateFunction<View> {
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
		updateThread = new UpdateThread(e.getUI(), this);
		updateThread.start();
	}

	@Override
	protected void onDetach(DetachEvent e)
	{
		updateThread.interrupt();
		updateThread = null;
	}

	private class UpdateThread extends Thread {
		private final UI ui;
		private final UpdateView view;

		public UpdateThread(final UI ui, final UpdateView view)
		{
			this.ui = ui;
			this.view = view;
		}

		@Override
		public void run()
		{
			try {
				while (true) {
					Thread.sleep(view.pauseMillis);
					ui.access(() -> {
						updateFunction.update(view);
					});
				}
			}
			catch (InterruptedException e) {
				// We don't case if sleep was interrupted.
			}
		}
	}
}
