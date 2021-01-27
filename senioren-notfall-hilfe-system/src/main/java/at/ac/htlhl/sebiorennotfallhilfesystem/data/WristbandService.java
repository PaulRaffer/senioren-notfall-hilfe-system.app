package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Demo backend that accepts up to 100 fishing spots. Data is shared with all
 * users.
 */
@Service
@ApplicationScope
public class WristbandService {

	private final List<Wristband> wristbands = new ArrayList<>();

	@PostConstruct
	private void init()
	{
		wristbands.add(new Wristband("wristband1", new Location(48.561613, 16.077118)));
		wristbands.add(new Wristband("wristband1", new Location(48.393650, 16.214447)));
		wristbands.add(new Wristband("wristband1", new Location(48.354424, 16.322937)));
	}

	public List<Wristband> getAll()
	{
		return Collections.unmodifiableList(wristbands);
	}

	public void add(Wristband wristband)
	{
		// protect concurrent access since MapLocationService is a singleton
		synchronized (wristbands) {
			wristbands.add(wristband);

			/*if (wristbands.size() > 100) {
				wristbands.remove(0);
			}*/
		}
	}
}
