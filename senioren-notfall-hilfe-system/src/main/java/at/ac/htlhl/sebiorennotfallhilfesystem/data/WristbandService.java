package at.ac.htlhl.sebiorennotfallhilfesystem.data;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WristbandService {

	private final List<MQTTWristband> wristbands = new ArrayList<>();

	private void init()
	{
	}

	public List<MQTTWristband> getAll()
	{
		return Collections.unmodifiableList(wristbands);
	}

	public void add(MQTTWristband wristband)
	{
		// protect concurrent access since WristbandService is a singleton
		synchronized (wristbands) {
			wristbands.add(wristband);
		}
	}
}
